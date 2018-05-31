/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladorest;

import Modelo.Producto;
import Modelo.Producto_compra;
import Modelo.Producto_comprat;
import Modelo.productot;
import Persistencia.DB;
import Persistencia.DBt;
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
@WebServlet(name = "Utilidadest", urlPatterns = {"/Utilidadest"})
public class Utilidadest extends HttpServlet {

    String cant, id, tipo;
    productot p = new productot();

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
        Producto_comprat pc = new Producto_comprat();
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=UTF-8");
            HttpSession objSesion = request.getSession(false);
            ArrayList<Object> lista;
            lista = (ArrayList<Object>) objSesion.getAttribute("carrosalida");
            id = request.getParameter("ids").toUpperCase();
            cant = request.getParameter("cant");
            DBt bd = new DBt();
            p = bd.buscarproducto(Integer.parseInt(id));
            if (p.getStock() < (Integer.parseInt(cant))) {
                PrintWriter outer = response.getWriter();
                outer.println("<script type=\"text/javascript\">");
                outer.println("alert('No puedes pedir mas de lo que hay en stock');");
                outer.println("location='admin/Utilidades_Donacionest.jsp';");
                outer.println("</script>");
                // response.sendRedirect("admin/proveedores.jsp");
            } else {
                //System.out.println("MENUDEO "+p.getCosto());
                lista = pc.setprodssesion(Integer.parseInt(id), Integer.parseInt(cant), p.getCosto(), p.getModelo(), lista);
                objSesion.setAttribute("carrosalida", lista);
                response.sendRedirect("admin/Utilidades_Donacionest.jsp");
            }
        } catch (Exception ex) {
//            
            System.err.println(ex);
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
