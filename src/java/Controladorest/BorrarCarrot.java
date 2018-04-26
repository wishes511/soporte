/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladorest;


import Modelo.Producto_comprat;
import java.io.IOException;
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
@WebServlet(name = "BorrarCarrot", urlPatterns = {"/BorrarCarrot"})
public class BorrarCarrot extends HttpServlet {

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
       String id = request.getParameter("eliminar").toUpperCase();
       ArrayList<Object> lista;
       HttpSession objSesion = request.getSession(true);
        lista =(ArrayList<Object>) objSesion.getAttribute("carrosalida");
        Producto_comprat pc =new Producto_comprat();
            
            System.out.println("borraritem");
            lista=pc.delprodsesion(Integer.parseInt(id), lista);
            System.out.println("item borrado");
            objSesion.setAttribute("carrosalida", lista);
            
//           // System.out.println("borraritem");
//            pc1.delprod(Integer.parseInt(ideliminar));
//            //System.out.println("item borrado");
            response.sendRedirect("admin/Utilidades_Donacionest.jsp");
       
       
       }catch(Exception e){
       //System.out.println("borraritem no men ;[ "+e);
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
