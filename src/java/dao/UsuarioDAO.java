/*
 * Licencia pendiente, para cambiar la licencia ir a -> Tools | Templates
 */

package dao;

import hbm.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import pojo.Usuario;

/**
 *
 * @author Ilich Arredondo
 */
public class UsuarioDAO {
    Session session;
    
    
    public UsuarioDAO() {
        session = HibernateUtil.getLocalSession();
    }
    
    public Usuario getUsuarioByNickname(String nickname) {
        return (Usuario) this.session.createCriteria(Usuario.class)
                                                .add(Restrictions.eq("nickname", nickname))
                                                .uniqueResult();
    }
    
    public Usuario getUsuarioById(int id) {
        return (Usuario) this.session.load(Usuario.class, id);
    }
    
    public boolean saveUsuario(Usuario user) {
        try {
            Transaction transaction = this.session.beginTransaction();
            this.session.save(user);
            transaction.commit();
        } catch (Exception e) { return false; }
        return true;
    }
    
}