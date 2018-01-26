/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.Producto;
import Modelo.Producto_compra;
import Persistencia.DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "Venta2", urlPatterns = {"/Venta2"})
public class Venta2 extends HttpServlet {
    String cant ,id,tipo;
Producto p = new Producto();


 ArrayList<Object> lista = new ArrayList<Object>();

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

   

    if (usuario != null && tipos != null && tipos.equals("USUARIO")) {
       
    } else {
        response.sendRedirect("../log.jsp");
    }
        System.out.println("entro");
        Producto_compra pc =new Producto_compra();
                try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=UTF-8");
            
            
            id = request.getParameter("idss").toUpperCase();
            cant = request.getParameter("cant").toUpperCase();
            tipo = request.getParameter("tipes").toUpperCase();
                    System.out.println("si llega");
                    
                     
       
        DB bd = new DB();
          if(tipo.equals("MENUDEO")){
              
          p.setCantidad(Integer.parseInt(cant));
        p=bd.buscarproducto(Integer.parseInt(id));
        if(p.getStock()<=0 || p.getStock()< Integer.parseInt(cant)){
            
            
            out.println("<script type=\"text/javascript\">");
            out.println("alert('No hay productos en inventario o excedio el numero de productos disponibles');");
            out.println("location='usuario/Realizar_ventas.jsp';");
            out.println("</script>");
        }else{
        lista.add(p.getNombre());
        lista.add(cant);
        lista.add(p.getCostomay());
       System.out.println("MENUDEO "+p.getCostomay());
        pc.setprods(Integer.parseInt(id), Integer.parseInt(cant),p.getCostomay(),p.getNombre());
        }
        
          }else{
              System.out.println("llega");
          p.setCantidad(Integer.parseInt(cant));
        p=bd.buscarproducto(Integer.parseInt(id));
        if(p.getStock()<=0 || p.getStock()< Integer.parseInt(cant)){
            request.setAttribute("error", "No hay productos en inventario o excedio el numero de productos disponibles !!");
             RequestDispatcher rd = getServletContext().getRequestDispatcher("/usuario/Realizar_ventas.jsp");               
             rd.include(request,response);
              
            
//            out.println("<script type=\"text/javascript\">");
//            out.println("alert('No hay productos en inventario o excedio el numero de productos disponibles');");
//            out.println("location='usuario/Realizar_ventas.jsp';");
//            out.println("</script>");
        }else{
        lista.add(p.getNombre());
        lista.add(cant);
        lista.add(p.getCostomin());
       System.out.println("mayoreo "+p.getCostomin());
        pc.setprods(Integer.parseInt(id), Integer.parseInt(cant),p.getCostomin(),p.getNombre());
        }
        
          }
//        
////        p2.setId(p.getId());
////        p2.setNombre(p.getNombre());
////        p2.setCostomay(p.getCostomay());
////        p2.setCantidad(cant);
        
        System.out.println("li "+p.getNombre()); 
        
        System.out.println(lista.size()+"/"+lista.get(0));
    
                    
//                    pc=new Producto_compra();
//                    pc.setprods(Integer.parseInt(id),Integer.parseInt(cant));
            //setprods(Integer.parseInt(id),Integer.parseInt(cant));
                    System.out.println("llega");
          

            
           
//    request.setAttribute("oka","Producto guardado exitosamente" );
//    RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/home_admin.jsp");               
//    rd.include(request,response);

            
            response.sendRedirect("usuario/Realizar_ventas.jsp");
//            out.println("<script type=\"text/javascript\">");
//            out.println("alert('Usua.');");
//            out.println("location='usuario/Realizar_pedidopiso.jsp';");
//            out.println("</script>");
            
        }catch (Exception ex) {
            System.out.println(ex);
//                PrintWriter out = response.getWriter();
//            out.println("<script type=\"text/javascript\">");
//            out.println("alert('Problemas al ingresar un campo verifique sus datos.');");
//            out.println("location='admin/home_admin.jsp';");
//            out.println("</script>");
//            
//            request.setAttribute("error","Problemas al ingresar un campo" );
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
