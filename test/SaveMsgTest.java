/*
 * Licencia pendiente, para cambiar la licencia ir a -> Tools | Templates
 */

import dao.MensajeDAO;
import dao.UsuarioDAO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pojo.Mensaje;
import pojo.Usuario;

/**
 *
 * @author Ilich Arredondo
 */
public class SaveMsgTest {
    
    public SaveMsgTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void hello() {
        MensajeDAO mensajeDAO = new MensajeDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario remitente = usuarioDAO.getUsuarioById(1);
        Usuario destinatario = usuarioDAO.getUsuarioById(7);
        
        Mensaje mensaje = new Mensaje("Holi crayoli", remitente, destinatario);
        
        mensajeDAO.saveMensaje(mensaje);
        
    }
}

