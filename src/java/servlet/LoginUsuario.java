/*
 * Licencia pendiente, para cambiar la licencia ir a -> Tools | Templates
 */
package servlet;

import dao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import pojo.Usuario;

/**
 *
 * @author Ilich Arredondo
 */
@WebServlet(name = "LoginUsuario", urlPatterns = {"/login"})
public class LoginUsuario extends HttpServlet {

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
        
        response.setContentType("application/json;charset=UTF-8;");
        
        JSONObject jsonResponse = new JSONObject();
        Usuario user = null;

        String nickname = request.getParameter("nickname");
        String password = request.getParameter("password");

        List<String> errors = new ArrayList<>();
        // Si el campo de contraseña esta vacio
        if (password == null && password.isEmpty()) {
            errors.add("Por favor llene el campo de contraseña");
        }
        // Si el campo de usuario esta vacio
        if (nickname == null && nickname.isEmpty()) {
            errors.add("Por favor llene el campo de usuario");
        } else {
            // Si el password NO es nulo y tampoco es un String vacio
            user = new UsuarioDAO().getUsuarioByNickname(nickname);
        }
        // Si no se encontró al usuario
        if (user == null) {
            errors.add("Ese usuario no existe.");
        }
        // Si la contraseña no coincide
        if(!user.getPassword().equals(password)) {
            errors.add("Contraseña incorrecta.");
        }
        //Si hay errores, mandamos errores al frontend
        if (!errors.isEmpty()) {
            try (PrintWriter out = response.getWriter()) {
                out.print(jsonResponse.put("login", false)
                                                    .put("errors", errors).toString());
                out.close();
            }
            return;
        }
        
        jsonResponse.put("login", true);
        
        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);
        
        try (PrintWriter out = response.getWriter()) {
            out.print(jsonResponse.toString());
            out.close();
        }

        this.destroy();
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
