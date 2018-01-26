/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Persistencia.DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
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
@WebServlet(name = "Nuevodetallebaja", urlPatterns = {"/Nuevodetallebaja"})
public class Nuevodetallebaja extends HttpServlet {
Calendar fecha = Calendar.getInstance();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String fechac =año+"-"+mes+"-"+dia;
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
        
       //id_producto=6&tipo=HARINA&cant=&botonAceptar=Aceptar
        try{
        int total=0;
        HttpSession objSesion = request.getSession(false);
        String ids = String.valueOf(objSesion.getAttribute("i_d"));
        String usuario = (String) objSesion.getAttribute("usuario");
        String tipos = (String) objSesion.getAttribute("tipo");
        
        if (usuario != null && tipos != null && tipos.equals("PANADERO")) {
       
    } else {
        response.sendRedirect("../log.jsp");
    }
       
        System.out.println(total);
        int id_prod = Integer.parseInt(request.getParameter("id_producto"));
        int cant = Integer.parseInt(request.getParameter("cant1"));
        String tipes = request.getParameter("tipes");
        DB bd =new DB();
        System.out.println(id_prod+"/"+ids+"/"+cant+"/"+fechac);
        bd.agregardetallebaja(id_prod,Integer.parseInt(ids),cant,fechac);
        
            response.sendRedirect("produccion_panadero.jsp");
        total++;
        }catch(Exception e){
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Error al procesar su petición verifique sus campos!.');");
            out.println("location='produccion_panadero.jsp';");
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
