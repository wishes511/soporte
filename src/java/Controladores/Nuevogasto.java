/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.Proveedor;
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
@WebServlet(name = "Nuevogasto", urlPatterns = {"/Nuevogasto"})
public class Nuevogasto extends HttpServlet {
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
//i_d
    String usuario = (String) objSesion.getAttribute("usuario");
    String tipos = (String) objSesion.getAttribute("tipo");
    String ids = String.valueOf(objSesion.getAttribute("i_d"));


    int idprodu=0;
    int idprodu1=0;
    String tipo="";
    int stoc=0;

    if (usuario != null && tipos != null && tipos.equals("ADMIN")) {
       
    } else {
        response.sendRedirect("../log.jsp");
    }
        try {
            PrintWriter out = response.getWriter();
            
            //name=bollos&calle=&col=&tel=&botonAceptar=Aceptar
            String motivo = request.getParameter("motivo").toUpperCase();
            float monto = Float.parseFloat(request.getParameter("monto"));
            
           
            DB udb = new DB();  String omg ="";
            udb.agregargasto(motivo, monto, fechac);
            
            
            
           // udb.agregarproveedor(p);
//    request.setAttribute("oka","Producto guardado exitosamente" );
//    RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/home_admin.jsp");               
//    rd.include(request,response);

            
           // response.sendRedirect("admin/home_admin.jsp");
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Gasto Completo!');");
            out.println("location='admin/gastos.jsp';");
            out.println("</script>");
            
        }catch (Exception ex) {
            
                PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Problemas al ingresar un campo verifique sus datos.');");
            out.println("location='admin/gastos.jsp';");
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
