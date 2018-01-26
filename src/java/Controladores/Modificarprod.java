/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.Producto;
import Persistencia.DB;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
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
@WebServlet(name = "Modificarprod", urlPatterns = {"/Modificarprod"})
public class Modificarprod extends HttpServlet {

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
    String tiposs = (String) objSesion.getAttribute("tipo");
    String ids = String.valueOf(objSesion.getAttribute("i_d"));

    
    int idprodu=0;
    int idprodu1=0;
    String tipo="";
    int stoc=0;

    if (usuario != null && tiposs != null && tiposs.equals("ADMIN")) {
       
    } else {
        response.sendRedirect("../log.jsp");
    }
        try{
            //benviar1=7&n=BOLILLO&cmay=2&cmen=1.5&cprod=1.0&tipos=SI&stock=491
        String id = request.getParameter("benviar1");
        String nombre = request.getParameter("n");
        String cmay = request.getParameter("cmay");
        String cmin = request.getParameter("cmen");
        String stock = request.getParameter("stock");
        String cprod = request.getParameter("cprod");
        String tipos = request.getParameter("tipos");
        
        Producto p = new Producto();
        p.setId(Integer.parseInt(id));
        p.setNombre(nombre.toUpperCase());
        p.setCostomay(Float.parseFloat(cmay));
        p.setCostomin(Float.parseFloat(cmin));
        p.setStock(Integer.parseInt(stock));
        p.setCostoprod(Float.parseFloat(cprod));
        p.setHabilitado(tipos);
       
           DB bd = new DB();
        bd.modificarprod(p);
            System.out.println("EXITOS PRRO");
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Exito al modificar un producto.');");
            out.println("location='admin/productos_admin.jsp';");
            out.println("</script>");
          
          
            
       }catch(MySQLIntegrityConstraintViolationException ex){
           PrintWriter out = response.getWriter();
       out.println("<script type=\"text/javascript\">");
            out.println("alert('No puedes borrar un producto que ya ha hecho una operación.');");
            out.println("location='admin/productos_admin.jsp';");
            out.println("</script>");
       } 
        catch(Exception e){
       System.out.println(e);
       PrintWriter out = response.getWriter();
       out.println("<script type=\"text/javascript\">");
            out.println("alert('Error al modificar verifica los campos .');");
            out.println("location='admin/productos_admin.jsp';");
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
