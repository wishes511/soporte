/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.Cliente;
import Modelo.Producto_compra;
import Modelo.Usuario;
import Modelo.venta;
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
@WebServlet(name = "Nuevavta2", urlPatterns = {"/Nuevavta2"})
public class Nuevavta2 extends HttpServlet {
         ArrayList<Object> lista;
    float total=0;
    int totalprod=0;
    Calendar fecha = Calendar.getInstance();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        String fechac =año+"-"+mes+"-"+dia;
        String horas =hora+":"+minuto;

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


    if (usuario != null && tipos != null && tipos.equals("ADMIN")) {
       
    } else {
        response.sendRedirect("../log.jsp");
    }
        
        Cliente c = new Cliente();       
        c.setNombre("UTILIDADES");
        c.setApellido("UTILIDADES");
        c.setCalle("UTILIDADES");
        c.setColonia("UTILIDADES");
        c.setTelefono("VENTA_PISO");
        c.setCp(37219);

        try {
            Producto_compra pc =new Producto_compra();
            lista=pc.getProd();
            if(lista.isEmpty()){
                
                System.out.print("No hay articulos");
            PrintWriter out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('No hay Productos el carro de utilidades !!');");
                out.println("location='admin/Utilidades_Donaciones.jsp';");
                out.println("</script>");
            
            }else{
            int cont =0;
            
        
                
               System.out.println("total: "+total+" /"+totalprod); 
           DB db = new DB();
           venta v = new venta();
          
           Usuario u = new Usuario();
          
           u=db.buscarusuvta();
           db.agregarcliente(c);
            System.out.print("Insertado hecho , total:"+total+" /"+u.getId());
            v.setId_cliente(db.buscarcliente());
            v.setId_usuario(Integer.parseInt(ids));
            v.setFecha(fechac);
            v.setInhr(horas);
            v.setTotal(total);
            v.setCantidad(totalprod);
            v.setStatus("UTILIDADES");
            db.agregarventa(v);
            
           
           db.agregardetalle(db.buscarvta(),lista);
           db.modificarstock(lista);
           
           
            pc.vaciar_carro();
            total=0;
            totalprod=0;
            PrintWriter out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Donación o Utilidad Realizada !!');");
                out.println("location='usuario/Realizar_ventas.jsp';");
                out.println("</script>");
           // response.sendRedirect("productos.jsp");
            }
        }catch(Exception e){
            PrintWriter out = response.getWriter();
          
                out.println("<script type=\"text/javascript\">");
                out.println("alert('No se completo el envio verifique los campos a llenar');");
                out.println("location='usuario/Realizar_ventas.jsp';");
                out.println("</script>");
           // response.sendRedirect("productos.jsp");
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
