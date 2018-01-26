/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.Proveedor;
import Modelo.venta;
import Persistencia.DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "Ver_proveedorcompra", urlPatterns = {"/Ver_proveedorcompra"})
public class Ver_proveedorcompra extends HttpServlet {

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
        HttpSession objSesion = request.getSession(false);
//i_d
    String usuario = (String) objSesion.getAttribute("usuario");
    String tipos = (String) objSesion.getAttribute("tipo");
    String ids = String.valueOf(objSesion.getAttribute("i_d"));

   

    if (usuario != null && tipos != null && tipos.equals("ADMIN")) {
       
    } else {
        response.sendRedirect("../log.jsp");
    }
        
       try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=UTF-8");
            ArrayList<Object> lista;
            Proveedor p= new Proveedor();
            
            
            lista=p.getIdpro();
            if(lista.isEmpty()){
            }else{
            p.vaciarIDpro();
            }
            
           String f1 = request.getParameter("f1");
           String f2 = request.getParameter("f2");
                    System.out.println("si llega");
                    
        DB bd = new DB();
         
          p.setIdpro(bd.retornombrepro(f1, f2));
                    System.out.println("llega");
          
            
            response.sendRedirect("admin/Ver_proveedor.jsp");
//            out.println("<script type=\"text/javascript\">");
//            out.println("alert('Usua.');");
//            out.println("location='productos.jsp';");
//            out.println("</script>");
            
        }catch (Exception ex) {
             PrintWriter out = response.getWriter();
             System.out.println(ex);
//             response.sendRedirect("Ver_produccion.jsp");
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Pendejote :v');");
            out.println("location='admin/Ver_proveedor.jsp';");
            out.println("</script>");
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
