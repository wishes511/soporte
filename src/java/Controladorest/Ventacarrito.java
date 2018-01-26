/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladorest;

import Controladores.*;
import Modelo.Producto;
import Modelo.Producto_compra;
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
@WebServlet(name = "Ventacarrito", urlPatterns = {"/Ventacarrito"})
public class Ventacarrito extends HttpServlet {
String cant ,id;
productot p = new productot();

 ArrayList<Object> lista = new ArrayList<>();
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
    ArrayList<Object> carrito;
    carrito = (ArrayList<Object>) objSesion.getAttribute("carro");
    if (usuario != null && tipos != null && tipos.equals("ADMIN")) {
       
    } else {
        response.sendRedirect("../index.jsp");
    }
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=UTF-8");
            
            id = request.getParameter("ids").toUpperCase();
            cant = request.getParameter("cant").toUpperCase();
                    System.out.println("si llega");
        
        DBt bd = new DBt();

        p=bd.buscarproductot(Integer.parseInt(id));
        if(p.getStock()<=0 || p.getStock()< Integer.parseInt(cant)){
        out.println("<script type=\"text/javascript\">");
            out.println("alert('No hay productos en el carrito o excedio el numero de productos disponibles');");
            out.println("location='admin/Proveedort.jsp';");
            out.println("</script>");
        }else{
        lista.add(p.getModelo());
        lista.add(cant);
        lista.add(p.getCosto());
       
        //pc.setprods(Integer.parseInt(id), Integer.parseInt(cant),p.getCostomay(),p.getNombre())
        System.out.println("li "+p.getNombre()); 
        
        System.out.println(lista.size()+"/"+lista.get(0));
        System.out.println("llega");
            objSesion.setAttribute("carro", lista);
            response.sendRedirect("admin/Proveedort.jsp");
                     
        }
        
        }catch (Exception ex) {
            System.out.println(ex);
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
