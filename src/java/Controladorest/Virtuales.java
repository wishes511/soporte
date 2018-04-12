/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladorest;

import Modelo.virtual;
import Persistencia.persistencia_virtual;
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
 * @author gateway1
 */
@WebServlet(name = "Virtuales", urlPatterns = {"/Virtuales"})
public class Virtuales extends HttpServlet {

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
        try {
            HttpSession objSesion = request.getSession(false);
            objSesion.invalidate();
            response.sendRedirect("index.jsp");
        } catch (Exception e) {
            response.sendRedirect("index.jsp");
        }
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
        HttpSession objSesion = request.getSession(true);
        String usuario = (String) objSesion.getAttribute("usuario");
        String tipos = (String) objSesion.getAttribute("tipo");
        String ids = String.valueOf(objSesion.getAttribute("i_d"));
        ArrayList<Object> carrito;
        carrito = (ArrayList<Object>) objSesion.getAttribute("carro");
        System.out.println(usuario+"/"+tipos+"/"+ids);
        if (usuario != null && tipos != null && tipos.equals("ADMIN")) {
        } else {
            response.sendRedirect("index.jsp");
        }
        try {
            
            String uso = request.getParameter("uso");
            PrintWriter out = response.getWriter();
            virtual v = new virtual();
            persistencia_virtual pv = new persistencia_virtual();
            if (uso.equals("nuevo")) {
            String nombre = request.getParameter("nombre");
            int ip = Integer.parseInt( request.getParameter("ip"));
            int user =Integer.parseInt(request.getParameter("usuario"));
            if(pv.Checkip(ip,"nuevo")){
           out.println("<script type=\"text/javascript\">");
            out.println("alert('Esa direccion IP ya existe!, porfavor ingrese otro.');");
            out.println("</script>");
            }else{
            v.setallvirtual(user, ip, nombre);
            pv.agregarvirtual(v);
            }
            }else if(uso.equals("eliminar")){
                int id =Integer.parseInt(request.getParameter("id"));
                pv.eliminar_virtual(id);
            }else if(uso.equals("modificar")){
                int ip = Integer.parseInt( request.getParameter("ip"));
                int user =Integer.parseInt(request.getParameter("usuario"));
                int id =Integer.parseInt(request.getParameter("id"));
                //System.out.println(pv.Checkip(ip));
                if(pv.Checkip(ip,"mod")){
                    System.out.println("ok");
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Esa direccion IP ya existe!, porfavor ingrese otro.');");
                out.println("</script>");
                }else{
                v.setallvirtual(user, ip, id);
                pv.actualizar_virtual(v);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
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
