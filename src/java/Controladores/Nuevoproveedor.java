/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.Proveedor;
import Modelo.Usuario;
import Persistencia.DB;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "Nuevoproveedor", urlPatterns = {"/Nuevoproveedor"})
public class Nuevoproveedor extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
       try {
            PrintWriter out = response.getWriter();
            HttpSession objSesion = request.getSession(false);
            //name=bollos&calle=&col=&tel=&botonAceptar=Aceptar
            String name = request.getParameter("name").toUpperCase();
            String calle = request.getParameter("calle").toUpperCase();
            String col = request.getParameter("col");
            String tel = request.getParameter("tel").toUpperCase();
            String numeros = request.getParameter("numeros").toUpperCase();
           
            DB udb = new DB();  String omg ="";
            Proveedor p = new Proveedor();
            
            p.setNombre(name);
            p.setCalle(calle+numeros);
            p.setColonia(col);
            p.setTel(tel);
            
            
            udb.agregarproveedor(p);
//    request.setAttribute("oka","Producto guardado exitosamente" );
//    RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/home_admin.jsp");               
//    rd.include(request,response);

            
           // response.sendRedirect("admin/home_admin.jsp");
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Usuario ingresado correctamente.');");
            out.println("location='admin/proveedores.jsp';");
            out.println("</script>");
            
        }catch (Exception ex) {
            
                PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Problemas al ingresar un campo verifique sus datos.');");
            out.println("location='admin/proveedores.jsp';");
            out.println("</script>");
            
            request.setAttribute("error","Problemas al ingresar un campo" );
           // response.sendRedirect("admin/home_admin.jsp");
//    request.setAttribute("error","Problemas al ingresar un campo" );
//    RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/productos_admin.jsp");               
//    rd.include(request,response);
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
