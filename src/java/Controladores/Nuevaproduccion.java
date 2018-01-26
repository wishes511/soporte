/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.Producion;
import Modelo.Producto;
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
@WebServlet(name = "Nuevaproduccion", urlPatterns = {"/Nuevaproduccion"})
public class Nuevaproduccion extends HttpServlet {
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
        //prod=12&id_prod=2&tipo=DONAS&benviar=Aceptar
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
        
        String idprod=request.getParameter("id_prod");
        String prol=request.getParameter("prod");
        
        String pima = request.getParameter("pima");
        
        if(idprod.equals(null) || prol.equals(null) ||pima.equals(null)){
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Ningun campo debe de estar vacio');");
            out.println("location='produccion_panadero.jsp';");
            out.println("</script>");
        }else{
            int id_prod = Integer.parseInt(request.getParameter("id_prod"));
        int produc = Integer.parseInt(request.getParameter("prod"));
        String tipes = request.getParameter("tipes");
        int prima=Integer.parseInt(pima);
        if(tipes.equals("BOLILLO")){
            total= produc*36;
        }else { total=produc; }
        Producto p = new Producto();
        Producion pro = new Producion();
        DB bd = new DB();
        p=bd.buscarproducto(id_prod);
        pro.setId_usuario(Integer.parseInt(ids));
        pro.setId_producto(id_prod);
        pro.setProducido(total);
        pro.setGanancia(p.getCostoprod()*total);
        pro.setFecha(fechac);
        System.out.println(ids+"/"+id_prod+"/"+total+"/"+(p.getCostoprod()*total)+"/"+fechac);
        bd.agregarproduccion(pro);        
        bd.modificarprod(id_prod, total);
        bd.modificardetalleprod(prima);
        
        total=0;
            response.sendRedirect("produccion_panadero.jsp");
        }
        
        
    }catch(Exception e){
         PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Ningun campo debe de estar vacio');");
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
