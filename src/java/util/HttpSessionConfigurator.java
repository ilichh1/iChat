/*
 * Licencia pendiente, para cambiar la licencia ir a -> Tools | Templates
 */

package util;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import pojo.Usuario;

public class HttpSessionConfigurator extends ServerEndpointConfig.Configurator
{
    @Override
    public void modifyHandshake(ServerEndpointConfig config, 
                                HandshakeRequest request, 
                                HandshakeResponse response)
    {
        HttpSession httpSession = (HttpSession)request.getHttpSession();
        Usuario user = (Usuario) httpSession.getAttribute("user");
        
        config.getUserProperties().put("user", user);
        
    }
}