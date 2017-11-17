/*
 * Licencia pendiente, para cambiar la licencia ir a -> Tools | Templates
 */

package pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Ilich Arredondo
 */
@Entity
@Table(name="usuario")
public class Usuario {
    
    @Id @GeneratedValue
    @Column(name="idusuario")
    private int idUsuario;
    
    @Column(name="nickname")
    private String nickname;
    
    @Column(name="password")
    private String password;
    
    public Usuario() {}
    
    public Usuario(String n, String pass) {
        this.nickname = n;
        this.password = pass;
    }

    /**
     * @return the idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    

}