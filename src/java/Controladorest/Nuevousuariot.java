/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladorest;

import Modelo.usuariot;
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
@WebServlet(name = "Nuevousuariot", urlPatterns = {"/Nuevousuariot"})
public class Nuevousuariot extends HttpServlet {
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
        HttpSession objSesion = request.getSession(false);
//i_d
    String usuario = (String) objSesion.getAttribute("usuario");
    String tiposs = (String) objSesion.getAttribute("tipo");
    String ids = String.valueOf(objSesion.getAttribute("i_d"));

    out.println("" + tiposs+"/"+ids);
    int idprodu=0;
    int idprodu1=0;
    String tipo="";
    int stoc=0;

    if (usuario != null && tiposs != null && tiposs.equals("ADMIN")) {
       
    } else {
        response.sendRedirect("../index.jsp");
    }
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=UTF-8");
            
            String usuarios = request.getParameter("uso").toUpperCase();
            String name = request.getParameter("names").toUpperCase();
            String apellido = request.getParameter("calles").toUpperCase();
            String pass = request.getParameter("passs").toUpperCase();
            String tipos = request.getParameter("tipos").toUpperCase();
            String ips = request.getParameter("ips").toUpperCase();
            String tips = request.getParameter("tips").toUpperCase();
            

            
            DBt udb = new DBt();  
            persistencia_virtual pv = new persistencia_virtual();
           
            if(udb.buscarusuariorepe(usuarios,"nuevo") || pv.Checkip(Integer.parseInt(ips),"nuevo")){
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Ese nombre de usuario o IP ya existe!, porfavor ingrese otro.');");
            out.println("location='admin/home_admin.jsp';");
            out.println("</script>");
            }else {
        usuariot p = new usuariot();
            p.setUsuario(usuarios);
            p.setNombre(name.toUpperCase());
            p.setApellido(apellido.toUpperCase());
            p.setContrasena(pass);
            p.setTipo(tipos.toUpperCase());
            p.setActivo("Y");
            p.setIp(ips);
           
            
            udb.agregar(p,tips);
//    request.setAttribute("oka","Producto guardado exitosamente" );
//    RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/home_admin.jsp");               
//    rd.include(request,response);

            
           // response.sendRedirect("admin/home_admin.jsp");
           response.sendRedirect("admin/home_admin.jsp");
//            out.println("<script type=\"text/javascript\">");
//            out.println("alert('Usuario ingresado correctamente.');");
//            out.println("location='admin/home_admin.jsp';");
//            out.println("</script>");
        }
            
            
        }catch (Exception ex) {
            System.out.println(ex);
                PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Problemas al ingresar un campo verifique sus datos.');");
            out.println("location='admin/home_admin.jsp';");
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
