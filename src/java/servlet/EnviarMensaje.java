/*
 * Licencia pendiente, para cambiar la licencia ir a -> Tools | Templates
 */
package servlet;

import dao.MensajeDAO;
import dao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import pojo.Mensaje;
import pojo.Usuario;
import util.UserSession;

/**
 *
 * @author Ilich Arredondo
 */
@WebServlet(name = "EnviarMensaje", urlPatterns = {"/mensaje"})
public class EnviarMensaje extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8;");
        
        // Utilidades para esta peticion
        HttpSession session = request.getSession();
        JSONObject jsonResponse = new JSONObject();

        String contenidoMensaje = request.getParameter("mensaje");
        String dest = request.getParameter("destinatario");
        
        List<String> errors = new ArrayList<>();
        if(contenidoMensaje == null || contenidoMensaje.isEmpty()) {
            errors.add("Contenido del mensaje sin especificar.");
        }
        if(dest == null || dest.isEmpty()) {
            errors.add("Mensaje sin destinatario.");
        }
        if(!errors.isEmpty()) {
            try (PrintWriter out = response.getWriter()) {
                out.print(
                        jsonResponse.put("errors", errors).toString()
                );
            }
            // End of the function
            return;
        }
        
        // Convertimos el String recibido del request a Integer
        int desti = Integer.parseInt(dest);
        Usuario remitente = (Usuario) session.getAttribute("user");
        
        // Prueba
        System.out.println(session.getAttribute("user"));
        
        Usuario destinatario = new UsuarioDAO().getUsuarioById(desti);
        // Mensaje que guardaremos en la base de datos
        Mensaje msg = new Mensaje(contenidoMensaje, remitente, destinatario);
        
        boolean msgStatus = new MensajeDAO().saveMensaje(msg);
        
        try (PrintWriter out = response.getWriter()) {
            out.print(
                    jsonResponse.put("mensajeStatus", msgStatus).toString()
            );
            out.close();
        }
        
        if(msgStatus) {
            
            Collection<UserSession> usuarios = ChatHandler.usuariosConectados.values();
            
            // Por cada usuario conectado
            for (UserSession usuario : usuarios) {
                if(usuario.getUserID() == destinatario.getIdUsuario()) {
                    HashMap<String,String> remitenteMap = new HashMap<>();
                    remitenteMap.put("id", Integer.toString(remitente.getIdUsuario()));
                    remitenteMap.put("nickname", remitente.getNickname());
                    JSONObject jsonMsg = new JSONObject()
                            .put("responseType", 1)
                            .put("msg", msg.getContenido())
                            .put("remitente", remitenteMap);
                    
                    usuario.getSession().getBasicRemote().sendText(jsonMsg.toString());
                }
            }
            
        }
        
        this.destroy();
        //System.out.println("Nickname: "+usuarioRemitente.getNickname()+" ID: "+usuarioRemitente.getIdUsuario());
        
    }

}
