/*
 * Licencia pendiente, para cambiar la licencia ir a -> Tools | Templates
 */

package util;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Ilich Arredondo
 */

@WebListener //thanks to http://stackoverflow.com/questions/20240591/websocket-httpsession-returns-null
public class ServletRequestListenerImp implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        //System.out.println("requestDestroyed()");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        //HttpServletRequest servletRequest = (HttpServletRequest) sre.getServletRequest();
        //System.out.println("requestInitialized() Current thread " + Thread.currentThread().getName() + " with sesison id " + servletRequest.getSession().getId());
    }

}