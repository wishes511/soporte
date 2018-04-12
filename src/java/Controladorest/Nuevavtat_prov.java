/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladorest;


import Modelo.entrada_prov;
import Persistencia.DBt;
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
@WebServlet(name = "Nuevavtat_prov", urlPatterns = {"/Nuevavtat_prov"})
public class Nuevavtat_prov extends HttpServlet {
        // ArrayList<Object> lista;
    float total=0;
    int totalprod=0;
    
        

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
   
              HttpSession objSesion = request.getSession(true);
//i_d
    String usuario = (String) objSesion.getAttribute("usuario");
    String tipos = (String) objSesion.getAttribute("tipo");
    String ids = String.valueOf(objSesion.getAttribute("i_d"));


    if (usuario != null && tipos != null && tipos.equals("ADMIN")) {
       
    } else {
        response.sendRedirect("../index.jsp");
    }
    try {
            String usuarioc = request.getParameter("idu").toUpperCase();
            String ref = request.getParameter("ref").toUpperCase();
            
             ArrayList<Object> lista;
            lista = (ArrayList<Object>) objSesion.getAttribute("carro");
            if(lista.isEmpty()){            
                System.out.print("No hay articulos");
            PrintWriter out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('No hay Productos el carro !!');");
                out.println("location='admin/Eprovedor.jsp';");
                out.println("</script>");
            
            }else{
            int cont =0;
            
                for(int i =0;i<lista.size();i++){
        if (cont == 3) {
           total = total+ Float.parseFloat(lista.get(i).toString());
           totalprod=totalprod+Integer.parseInt((lista.get(i-1).toString()));
          cont =0;
             } else {
              cont++;
             }
        }
        Calendar fecha = Calendar.getInstance();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        String fechac =año+"-"+mes+"-"+dia;
            DBt db = new DBt();
            entrada_prov fac = new entrada_prov();
            fac.setCantidad(totalprod);
            fac.setFecha(fechac);
            fac.setTotal(total);
            fac.setUsuario(usuario);
            fac.setReferencia(ref);
            fac.setHora(tiempo(hora,minuto));
            fac.setID_PROVEEDOR(Integer.parseInt(usuarioc));
            db.agregarfacturat(fac);
            db.agregardetallefact_prov(db.buscarfacturat_prov(),lista);
//            db.modificarstock_prov(lista);
            lista.clear();
            objSesion.setAttribute("carro", lista);

            total=0;
            totalprod=0;
            PrintWriter out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Venta interna realizada !!');");
                out.println("location='admin/Eprovedor.jsp';");
                out.println("</script>");
           // response.sendRedirect("productos.jsp");
            }
        }catch(Exception e){
            System.out.println(e);
            PrintWriter out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('No se completo el envio verifique los campos a llenar');");
                out.println("location='admin/Eprovedor.jsp';");
                out.println("</script>");
           // response.sendRedirect("productos.jsp");
        }
    }

    private String tiempo(int hora,int minuto){
    String horas="";
        if(hora >9 ){
            horas =hora+":";
        }if(hora<10){
            horas="0"+hora+":";
        }if(minuto <10){
            horas=horas+"0"+minuto;
        }
        if(minuto >9){
            horas+=minuto;
        }
        return horas;
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
