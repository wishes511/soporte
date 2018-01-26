/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.Usuario;
import Persistencia.DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "Validar", urlPatterns = {"/Validar"})
public class Validar extends HttpServlet {

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
     
        // definir una pagina de respuesta localhost:8080/Dmorales/Validar?nombrelog=&contrasenalog=' OR 1 =1--

        // Recibir los valores desde el formulario
        String nombre = request.getParameter("nombrelog");
        String contrasena = request.getParameter("contrasenalog");
        System.out.println(","+nombre+","+contrasena+",");
        char arr[];
        char arr1[];
         System.out.println(","+nombre+","+contrasena+",");
        if(nombre.equals(null) || contrasena.equals(null) || nombre.equals("") || contrasena.equals("")){
            System.out.println("nulos");
        response.sendRedirect("log.jsp");
        }else{
            
        arr=nombre.toCharArray();
        arr1=contrasena.toCharArray();
        
       for(int i =0;i<arr.length;i++){
           System.out.println("caracteres");
           if(arr[i]=='|' || arr[i]=='\''||arr[i]=='\"' ||arr[i]=='=' ||arr[i]=='!'){
               i=arr.length;
               response.sendRedirect("log.jsp");
           }
       }
       for(int i =0;i<arr1.length;i++){
           System.out.println("caracteres");
           if(arr1[i]=='|' || arr1[i]=='\''||arr1[i]=='\"' ||arr1[i]=='=' ||arr1[i]=='!'){
               i=arr1.length;
               response.sendRedirect("log.jsp");
           }
       }
        
        }
       
                    
        String tipo ="";
        // Definir variable de referencia a un objeto de tipo Usuario
        Usuario u =null;

        // Consultar Base de datos
        DB uDB = new DB();
        try {
            u = uDB.buscar(nombre, contrasena);
            if (u == null) {    
                
                request.setAttribute("error", "usuario o contraseña incorrectos.");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/log.jsp");               
                rd.include(request,response);
            } else {
                tipo=u.getTipo();
                System.out.println(tipo);
                
                if(tipo.equals("ADMIN")){
                    System.out.println(tipo+"ADMIN");
     objSesion.setAttribute("usuario", nombre);
    objSesion.setAttribute("tipo", tipo );
    objSesion.setAttribute("i_d", u.getId());
                             // Pasa el objeto a la pagina de respuesta         
                request.setAttribute("usuario1", u);
                response.sendRedirect("admin/home_admin.jsp");
                
                }else if(tipo.equals("USUARIO")){
                    System.out.println(tipo);
    objSesion.setAttribute("usuario", nombre);
    objSesion.setAttribute("tipo", tipo );
    objSesion.setAttribute("i_d", u.getId());
                request.setAttribute("usuario1", u);
                 response.sendRedirect("usuario/home_usuario.jsp");
                 
              }else if(tipo.equals("PANADERO")){
    objSesion.setAttribute("usuario", nombre);
    objSesion.setAttribute("tipo", tipo );
    objSesion.setAttribute("i_d", u.getId());
                System.out.println(tipo+"pana");
                request.setAttribute("usuario1", u);
                response.sendRedirect("produccion_panadero.jsp");
               
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
             request.setAttribute("error", "Mensaje del servidor: "+ex.getMessage());
             RequestDispatcher rd = getServletContext().getRequestDispatcher("/log.jsp");               
             rd.include(request,response);
              
//            request.setAttribute("error", ex);
//            paginaRespuesta = "error.jsp";        
        }
        
        // Redireccionar a una pagina de respuesta
      

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
