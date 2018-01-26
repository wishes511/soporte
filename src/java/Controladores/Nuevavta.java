/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.Cliente;
import Modelo.Producto;
import Persistencia.DB;
import Modelo.Producto_compra;
import Modelo.Usuario;
import Modelo.venta;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mich
 */
@WebServlet(name = "Nuevavta", urlPatterns = {"/Nuevavta"})
public class Nuevavta extends HttpServlet {
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
        //names=dfd&apes=df&calle=&col=&tel=&cp=numeros
        String nombre = request.getParameter("names").toUpperCase();
        String number = request.getParameter("numeros").toUpperCase();
        String ap = request.getParameter("apes").toUpperCase();
        String calle = request.getParameter("calle").toUpperCase();
        String col = request.getParameter("col").toUpperCase();
        String tel = request.getParameter("tel");
        String cp = request.getParameter("cp");
        Cliente c = new Cliente();       
        c.setNombre(nombre);
        c.setApellido(ap);
        c.setCalle(calle+number);
        c.setColonia(col);
        c.setTelefono(tel);
        c.setCp(Integer.parseInt(cp));

        try {
            Producto_compra pc =new Producto_compra();
            lista=pc.getProd();
            if(lista.isEmpty()){
                
                System.out.print("No hay articulos");
            PrintWriter out = response.getWriter();
                out.println("<script>");
                out.println("alert('No hay articulos :3');");
                out.println("</script>");
            response.sendRedirect("productos.jsp");
            
            }else{
            int cont =0;
            
        for(int i =0;i<lista.size();i++){
        if (cont == 3) {
            System.out.println("i: "+i+"/"+lista.get(i).toString());
           total = total+ Float.parseFloat(lista.get(i).toString());
           totalprod=totalprod+Integer.parseInt((lista.get(i-1).toString()));
          System.out.println("total: "+total+" /"+totalprod);
          cont =0;
             } else {
              cont++;
             }
        }
                
               System.out.println("total: "+total+" /"+totalprod); 
           DB db = new DB();
           venta v = new venta();
          
           Usuario u = new Usuario();
          
           u=db.buscarusuvta();
           db.agregarcliente(c);
            System.out.print("Insertado hecho , total:"+total+" /"+u.getId());
            v.setId_cliente(db.buscarcliente());
            v.setId_usuario(u.getId());
            v.setFecha(fechac);
            v.setInhr(horas);
            v.setTotal(total);
            v.setCantidad(totalprod);
            v.setStatus("----");
            db.agregarventa(v);
            
           
           db.agregardetalle(db.buscarvta(),lista);
           db.modificarstock(lista);
           
           
            pc.vaciar_carro();
            total=0;
            totalprod=0;
            PrintWriter out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                
                out.println("alert('Envio completo!. En unos 30 a 40 min estara listo su pedido');");
                out.println("location='productos.jsp';");
                out.println("</script>");
           // response.sendRedirect("productos.jsp");
            }
        }catch(Exception e){
            PrintWriter out = response.getWriter();
            
                out.println("<script type=\"text/javascript\">");
                out.println("alert('No se completo el envio verifique los campos a llenar');");
                out.println("location='productos.jsp';");
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
