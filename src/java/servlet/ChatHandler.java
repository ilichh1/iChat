/*
 * Licencia pendiente, para cambiar la licencia ir a -> Tools | Templates
 */
package servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.Session;
import javax.xml.registry.infomodel.User;
import org.json.JSONObject;
import pojo.Usuario;
import util.HttpSessionConfigurator;
import util.UserSession;

/**
 *
 * @author Ilich Arredondo
 */
@ServerEndpoint(value = "/chat", configurator = HttpSessionConfigurator.class)
public class ChatHandler {
    public static Map<String,UserSession> usuariosConectados = Collections.synchronizedMap(new HashMap<String,UserSession>());
            
    @OnOpen
    public void onOpen(Session usuario, EndpointConfig config) {
        Usuario user = (Usuario) config.getUserProperties().get("user");
        
        int id = user.getIdUsuario();
        String nickname = user.getNickname();
        
        usuariosConectados.put(usuario.getId(), new UserSession(id,usuario));
        try {
            usuario.getBasicRemote().sendText(new JSONObject()
                    .put("abierto", true)
                    .put("nickname", "test").toString()
            );
        } catch (IOException ex) {
            Logger.getLogger(ChatHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println( nickname + " conectado a traves de WebSocket");
    }

    @OnError
    public void onError(Throwable t) {
    }

    @OnClose
    public void onClose(Session clientSession) {
        //System.out.println("Desconectando a "+((HttpSession)usuariosConectados.get(clientSession.getId())).getId());
        System.out.print("Desconectando usuario.");
        usuariosConectados.remove(clientSession.getId());
    }

    @OnMessage
    public void onMessage(Session session, String mensaje) {
        JSONObject jsonRequest = new JSONObject(mensaje);
        
        int action = jsonRequest.getInt("action");
        int userId = jsonRequest.getInt("userID");
        String msg = jsonRequest.getString("msg");
        switch (action) {
            case 1:
                Collection<UserSession> usuarios = usuariosConectados.values();
                for (UserSession usuario : usuarios) {
                    int currentUserId = usuario.getUserID();
                    if (currentUserId == userId) {
                        try {
                            Session usuarioSession = usuario.getSession();
                            JSONObject response = new JSONObject();
                            response.put("responseType", 1);
                            response.put("msg", msg);
                            usuarioSession.getBasicRemote().sendText(response.toString());
                            return;
                        } catch (IOException ex) {
                            Logger.getLogger(ChatHandler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                break;
                
            default:
                throw new AssertionError();
        }
        
    }
    
}