/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.Caja;
import Persistencia.DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "Cierredecaja", urlPatterns = {"/Cierredecaja"})
public class Cierredecaja extends HttpServlet {
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
        HttpSession objSesion = request.getSession(false);
    String usuario = (String) objSesion.getAttribute("usuario");
    String tipos = (String) objSesion.getAttribute("tipo");
    String ids = String.valueOf(objSesion.getAttribute("i_d"));


    if (usuario != null && tipos != null && tipos.equals("ADMIN")) {
       
    } else {
        response.sendRedirect("../log.jsp");
    }
     try{
        String usar = request.getParameter("uso").toUpperCase();
        
        if(usar.equals("NEW")){
         ArrayList<Object> lista = new ArrayList<Object>();
       
        DB bd = new DB();
        Caja ca = new Caja();
        bd.agregaringresoauto(fechac);
        ca.setcajaarray(bd.retornodatos(fechac));
        lista=ca.getCaja();
        for(int i =0;i<lista.size();i++){
            System.out.println(lista.size()+" l "+lista.get(i)+"/"+i);
        System.out.println("lista de caja "+lista.get(i));
        }
        response.sendRedirect("admin/Cierredecaja.jsp");
        }else{
         ArrayList<Object> lista = new ArrayList<Object>();
       
        DB bd = new DB();
        Caja ca = new Caja();
        
        ca.setcajaarray(bd.retornodatos(fechac));
        lista=ca.getCaja();
        for(int i =0;i<lista.size();i++){
            System.out.println(lista.size()+" l "+lista.get(i)+"/"+i);
        System.out.println("lista de caja "+lista.get(i));
        }
        response.sendRedirect("admin/Cierredecaja.jsp");
        }
       
        }catch(Exception e){
        System.out.println(e);
         PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Problema al generar cierre no hay ventas, gastos o ingresos.');");
            out.println("location='admin/Cierredecaja.jsp';");
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
