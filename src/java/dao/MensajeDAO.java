/*
 * Licencia pendiente, para cambiar la licencia ir a -> Tools | Templates
 */
package dao;

import hbm.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Mensaje;

/**
 *
 * @author Ilich Arredondo
 */
public class MensajeDAO {

    private Session session;

    public MensajeDAO() {
        this.session = HibernateUtil.getLocalSession();
    }

    public boolean saveMensaje(Mensaje m) {
        try {
            Transaction trans = this.session.beginTransaction();
            this.session.save(m);
            trans.commit();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
