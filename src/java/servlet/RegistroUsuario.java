/*
 * Licencia pendiente, para cambiar la licencia ir a -> Tools | Templates
 */
package servlet;

import dao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import pojo.Usuario;

/**
 *
 * @author Ilich Arredondo
 */
@WebServlet(name = "RegistroUsuario", urlPatterns = {"/registro"})
public class RegistroUsuario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // The browser expects some JSON as response
        response.setContentType("application/json;charset=UTF-8");
        
        String nickname = request.getParameter("nickname");
        String pass = request.getParameter("password");
        String passConf = request.getParameter("password-conf");
        
        JSONObject jsonResponse = new JSONObject();
        
        //Validamos que los campos tengan un valor y no esten vacios
        if (
                (nickname != null && !nickname.isEmpty()) &&
                (pass != null && !pass.isEmpty()) &&
                (passConf != null && !pass.isEmpty())) {
            // Validamos que las contraseñas coincidan
            if(pass.equals(passConf)) {
                Usuario nuevoUsuario = new Usuario(nickname, pass);
                if (new UsuarioDAO().saveUsuario(nuevoUsuario)) {
                    //En caso que todo sea exitoso
                    jsonResponse.put("registro", true);
                } else {
                    jsonResponse.put("registro", false);
                    jsonResponse.put("errors", new String[]{"Ocurrió un error en el sistema, por favor inténtelo más tarde."});
                }
            // Else en caso que las contraseñas no coincidan
            } else {
                jsonResponse.put("registro", false);
                jsonResponse.put("errors", new String[]{"Las contraseñas no coinciden."});
            }
            
        //Mensaje en caso que los campos esten incompletos
        } else {
            jsonResponse.put("registro", false);
            jsonResponse.put("errors", new String[]{"Por favor complete todos los campos."});
        }
        
        try (PrintWriter out = response.getWriter()) {
            out.print(jsonResponse.toString());
            out.close();
        }
        
        this.destroy();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}