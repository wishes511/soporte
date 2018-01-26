/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladorest;

import Controladores.*;
import Modelo.Producto;
import Modelo.productot;
import Persistencia.DB;
import Persistencia.DBt;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mich
 */
@WebServlet(name = "Borrarproductot", urlPatterns = {"/Borrarproductot"})
public class Borrarproductot extends HttpServlet {

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
         HttpSession objSesion = request.getSession(false);
//i_d
    String usuario = (String) objSesion.getAttribute("usuario");
    String tipos = (String) objSesion.getAttribute("tipo");
    String ids = String.valueOf(objSesion.getAttribute("i_d"));

    if (usuario != null && tipos != null && tipos.equals("ADMIN")) {
       
    } else {
        response.sendRedirect("../index.jsp");
    }

        try {
            response.setContentType("text/html;charset=UTF-8");
            
            String id = request.getParameter("borrar");
            DBt udb = new DBt();  String omg ="";
            productot p = new productot();
            
            p.setID_PRODUCTO(Integer.parseInt(id));
            udb.eliminarproducto(p);
            response.sendRedirect("admin/productos_admint.jsp");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Borrarproductot.class.getName()).log(Level.SEVERE, null, ex);
        }catch(MySQLIntegrityConstraintViolationException ex){
             PrintWriter out = response.getWriter();
             
       out.println("<script type=\"text/javascript\">");
            out.println("alert('No puedes borrar un producto que ya ha hecho una operación.');");
            out.println("location='admin/productos_admint.jsp';");
            out.println("</script>");
            //response.sendRedirect("admin/productos_admin.jsp");
        }
        catch (SQLException ex) {
                PrintWriter out = response.getWriter();
             
       out.println("<script type=\"text/javascript\">");
            out.println("alert('No puedes borrar un producto que ya ha hecho una operación.');");
            out.println("location='admin/productos_admint.jsp';");
            out.println("</script>");
            
            Logger.getLogger(Borrarproductot.class.getName()).log(Level.SEVERE, null, ex);
        }

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
