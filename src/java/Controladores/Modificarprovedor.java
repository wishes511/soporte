/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.Producto;
import Modelo.Proveedor;
import Persistencia.DB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mich
 */
@WebServlet(name = "Modificarprovedor", urlPatterns = {"/Modificarprovedor"})
public class Modificarprovedor extends HttpServlet {


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
       try{
        String id = request.getParameter("benviar");
        String nombre = request.getParameter("n");
        String tel = request.getParameter("tel");
        String calle = request.getParameter("calle");
        String col = request.getParameter("colo");
        Proveedor p = new Proveedor();
        p.setId(Integer.parseInt(id));
        p.setNombre(nombre.toUpperCase());
        p.setTel(tel);
        p.setCalle(calle.toUpperCase());
        p.setColonia(col.toUpperCase());
        
        DB bd = new DB();
        bd.modificarprove(p);
  
//            response.sendRedirect("admin/proveedores.jsp");
            PrintWriter out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                
                out.println("alert('Modificación de proveedor exitosa');");
                out.println("location='admin/proveedores.jsp';");
                out.println("</script>");
           
       } catch(Exception e){
       System.out.println(e);
       PrintWriter out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Error al modificar el proveedor, verifique los campos ingresados');");
                out.println("location='admin/proveedores.jsp';");
                out.println("</script>");
       response.sendRedirect("admin/proveedores.jsp");
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
