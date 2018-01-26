/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladorest;

import Modelo.codigopersis;
import Modelo.pistola_cod;
import Persistencia.DBpistola;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gateway1
 */
@WebServlet(name = "NuevaSalida", urlPatterns = {"/NuevaSalida"})
public class NuevaSalida extends HttpServlet {
    
        Calendar fecha = Calendar.getInstance();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        String fechau=String.valueOf(año)+String.valueOf(mes)+String.valueOf(dia);
        String convert="";
        String convert1="";
        String convert2="";
        String convert3="";
        String convert4="";
        int folios =0;
        
       // String fechac =año+"-"+mes+"-"+dia+" "+hora+":"+minuto+":"+segundo+".000";
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
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        String tip=request.getParameter("Tipo");
        ArrayList <Object>lista=new ArrayList<Object>();
        pistola_cod pisc= new pistola_cod();
        lista=pisc.getCod();
        codigopersis cp = new codigopersis();
        if(lista.isEmpty()){
            response.sendRedirect("SalidasPistola.jsp");
        }else{
            
            
            if(tip.equals("Stock")){
                tip="S";
            }else {
                tip="P";
            }
            
            System.out.println(mes);
            if(mes <10){
                convert=String.valueOf(mes);
                convert="0"+convert;
                System.out.println(convert);
            }else
                convert=String.valueOf(mes);
            
            if(dia <10){
                convert1=String.valueOf(dia);
                convert1="0"+convert1;
                System.out.println(convert1);
            }else
                convert1=String.valueOf(dia);
            
            if(hora <10){
                convert2=String.valueOf(hora);
                convert2="0"+convert2;
                System.out.println(convert2);
            }else
                convert2=String.valueOf(hora);
            
            if(minuto <10){
                convert3=String.valueOf(minuto);
                convert3="0"+convert3;
                System.out.println(convert3);
            }else
                convert3=String.valueOf(minuto);
            
            if(segundo <10){
                convert4=String.valueOf(segundo);
                convert4="0"+convert4;
                System.out.println(convert4);
            }else
                convert4=String.valueOf(segundo);
            
            String fechac =año+"-"+convert+"-"+convert1+" "+convert2+":"+convert3+":"+convert4;
            String fechac1 =año+convert+convert1+" "+convert2+":"+convert3+":"+convert4+".000";
            DBpistola bd = new DBpistola();
            //folios= bd.Agregarcodigos(lista, fechac1,tip);
            
            
            lista.clear();
            pisc.vaciar_codigos();
            
            cp.setFactor(true);
            request.setAttribute("folios",folios);
            PrintWriter out = response.getWriter();
//                out.println("<script type=\"text/javascript\">");
//                out.println("alert(El folio es:"+folios+");");
//                out.println("location='SalidasPistola.jsp';");
//                out.println("</script>");
response.sendRedirect("SalidasPistola.jsp");      
//                RequestDispatcher rd = getServletContext().getRequestDispatcher("/SalidasPistola.jsp");
//                rd.include(request,response);
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
