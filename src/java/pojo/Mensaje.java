/*
 * Licencia pendiente, para cambiar la licencia ir a -> Tools | Templates
 */
package pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Ilich Arredondo
 */
@Entity
@Table(name="mensaje")
public class Mensaje {
    
    @Id @GeneratedValue
    @Column(name="idmensaje")
    private int idMensaje;
    
    @Column(name="contenido")
    private String contenido;
    
    @ManyToOne
    @JoinColumn(name="remitente")
    private Usuario remitente;
    
    @ManyToOne
    @JoinColumn(name="destinatario")
    private Usuario destinatario;
    
    public Mensaje() {}
    
    public Mensaje(String c, Usuario r, Usuario d) {
        this.contenido = c;
        this.remitente = r;
        this.destinatario = d;
    }
    
    /**
     * @return the idMensaje
     */
    public int getIdMensaje() {
        return idMensaje;
    }

    /**
     * @param idMensaje the idMensaje to set
     */
    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }

    /**
     * @return the contenido
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * @param contenido the contenido to set
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    /**
     * @return the remitente
     */
    public Usuario getRemitente() {
        return remitente;
    }

    /**
     * @param remitente the remitente to set
     */
    public void setRemitente(Usuario remitente) {
        this.remitente = remitente;
    }

    /**
     * @return the destinatario
     */
    public Usuario getDestinatario() {
        return destinatario;
    }

    /**
     * @param destinatario the destinatario to set
     */
    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }
}
