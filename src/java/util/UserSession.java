/*
 * Licencia pendiente, para cambiar la licencia ir a -> Tools | Templates
 */

package util;

import javax.websocket.Session;

/**
 *
 * @author Ilich Arredondo
 */
public class UserSession {
    
    private int userID;
    private Session session;
    
    public UserSession(int ui, Session s) {
        this.userID = ui;
        this.session = s;
    }

    /**
     * @return the userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * @return the session
     */
    public Session getSession() {
        return session;
    }

    /**
     * @param session the session to set
     */
    public void setSession(Session session) {
        this.session = session;
    }

}