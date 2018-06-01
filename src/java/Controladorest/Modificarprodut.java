/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladorest;

import Controladores.*;
import Modelo.Producto;
import Modelo.Usuario;
import Modelo.productot;
import Modelo.usuariot;
import Persistencia.DB;
import Persistencia.DBt;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
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
@WebServlet(name = "Modificarprodut", urlPatterns = {"/Modificarprodut"})
public class Modificarprodut extends HttpServlet {
     char[] arr;
          String calles="";

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
        //n=mich&p=123&calle=fgdfgdfgdf&t=4772727573&tipos=PANADERO&benviar=1
               HttpSession objSesion = request.getSession(false);
//i_d
    String usuario = (String) objSesion.getAttribute("usuario");
    String tiposs = (String) objSesion.getAttribute("tipo");
    String ids = String.valueOf(objSesion.getAttribute("i_d"));

    out.println("" + tiposs+"/"+ids);
    int idprodu=0;
    int idprodu1=0;
    int stoc=0;

    if (usuario != null && tiposs != null && (tiposs.equals("ADMIN") || tiposs.equals("APLASTISOL") || tiposs.equals("AMECANICA")) ) {
       
    } else {
        response.sendRedirect("../index.jsp");
    }
        
         try{
        String idss= request.getParameter("idss");
        String usu= request.getParameter("nombre").toUpperCase();
        String nombre = request.getParameter("modelo").toUpperCase();
         String apellido = request.getParameter("marca").toUpperCase();
        String pass = request.getParameter("stock");
        String ip = request.getParameter("costo");
        String tipo = request.getParameter("desc").toUpperCase();

       productot p = new productot();
       p.setID_PRODUCTO(Integer.parseInt(idss));
       p.setNombre(usu);
       p.setModelo(nombre);
       p.setMarca(apellido);
       p.setStock(Integer.parseInt(pass));
       p.setCosto(Double.parseDouble(ip));
       p.setDescripcion(tipo);
        
           DBt bd = new DBt();
          bd.modiproduct(p);
        //bd.modificarprod(p);
      
        
            
              PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Exito al modificar un producto.');");
            out.println("location='admin/productos_admint.jsp';");
            out.println("</script>");
           // response.sendRedirect("admin/home_admin.jsp");
           
       } catch(Exception e){
             PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Problemas al modificar un producto.');");
            out.println("location='admin/Editarprod.jsp';");
            out.println("</script>");
       System.out.println(e);
      
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
