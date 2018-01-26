/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladorest;

import Controladores.*;
import Modelo.Producto;
import Modelo.Usuario;
import Modelo.usuariot;
import Persistencia.DB;
import Persistencia.DBt;
import Persistencia.persistencia_virtual;
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
@WebServlet(name = "Modificarusut", urlPatterns = {"/Modificarusut"})
public class Modificarusut extends HttpServlet {
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

    if (usuario != null && tiposs != null && tiposs.equals("ADMIN")) {
       
    } else {
        response.sendRedirect("../index.jsp");
    }
        
         try{
        String idss= request.getParameter("idss");
        String usu= request.getParameter("uso").toUpperCase();
        String nombre = request.getParameter("names").toUpperCase();
        String apellido = request.getParameter("apes").toUpperCase();
        String pass = request.getParameter("passs");
        String ip = request.getParameter("ip");
        String tipo = request.getParameter("tipos").toUpperCase();
        String depa = request.getParameter("departamento").toUpperCase();

       usuariot p = new usuariot();
       p.setID_USUARIO(Integer.parseInt(idss));
       p.setUsuario(usu);
       p.setNombre(nombre);
       p.setApellido(apellido);
       p.setContrasena(pass);
       p.setIp(ip);
       p.setTipo(tipo);
       PrintWriter out = response.getWriter(); 
           DBt bd = new DBt();
           persistencia_virtual pv = new persistencia_virtual();
           System.out.println(bd.buscarusuariorepe(usu)+"*"+pv.Checkip(Integer.parseInt(ip)));
           if(bd.buscarusuariorepe(usu) || pv.Checkip(Integer.parseInt(ip))){
            out.println("<script type=\"text/javascript\">");
            out.println("alert('No se puedo modificar el usuario verifique sus datos');");
            out.println("location='admin/home_admin.jsp';");
            out.println("</script>");
           }else{
            p.setActivo(bd.buscarid_depa(depa));
            bd.modificarusut(p);
            
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Exito al modificar un usuario.');");
            out.println("location='admin/home_admin.jsp';");
            out.println("</script>");
           }
       } catch(Exception e){
             PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Problemas al modificar un usuario.');");
            out.println("location='admin/home_admin.jsp';");
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
