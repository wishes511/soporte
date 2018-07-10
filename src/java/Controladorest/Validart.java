/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladorest;

import Modelo.usuariot;
import Persistencia.DBt;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "Validart", urlPatterns = {"/Validart"})
public class Validart extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

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

        HttpSession objSesion = request.getSession(true);

        // definir una pagina de respuesta localhost:8080/Dmorales/Validar?nombrelog=&contrasenalog=' OR 1 =1--
        // Recibir los valores desde el formulario
        String nombre = request.getParameter("nombrelog");
        String contrasena = request.getParameter("contrasenalog");
//        System.out.println("," + nombre + "," + contrasena + ",");
        char arr[];
        char arr1[];
//        System.out.println("," + nombre + "," + contrasena + ",");
        if (nombre.equals(null) || contrasena.equals(null) || nombre.equals("") || contrasena.equals("")) {
            //    System.out.println("nulos");
            response.sendRedirect("index.jsp");
        } else {
            arr = nombre.toCharArray();
            arr1 = contrasena.toCharArray();

            for (int i = 0; i < arr.length; i++) {
                // System.out.println("caracteres");
                if (arr[i] == '|' || arr[i] == '\'' || arr[i] == '\"' || arr[i] == '=' || arr[i] == '!') {
                    i = arr.length;
                    response.sendRedirect("index.jsp");
                }
            }
            for (int i = 0; i < arr1.length; i++) {
                //   System.out.println("caracteres");
                if (arr1[i] == '|' || arr1[i] == '\'' || arr1[i] == '\"' || arr1[i] == '=' || arr1[i] == '!') {
                    i = arr1.length;
                    response.sendRedirect("index.jsp");
                }
            }
        }

        String tipo = "";
        // Definir variable de referencia a un objeto de tipo Usuario
        usuariot u = null;
        // Consultar Base de datos
        DBt uDB = new DBt();
        PrintWriter out = response.getWriter();
        try {
            u = uDB.buscar(nombre, contrasena);
            if (u == null) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Usuario o contrasena incorrectos');");
                out.println("location='index.jsp';");
                out.println("</script>");
            } else {
                tipo = u.getTipo();
//                System.out.println(tipo);
                switch (tipo) {
                    case "ADMIN": {
                        ArrayList<Object> lista = new ArrayList<>();
                        ArrayList<Object> lista1 = new ArrayList<>();
                        objSesion.setAttribute("usuario", nombre);
                        objSesion.setAttribute("tipo", tipo);
                        objSesion.setAttribute("i_d", u.getID_USUARIO());
                        objSesion.setAttribute("carro", lista);
                        objSesion.setAttribute("carrosalida", lista1);
                        out.print("<script>location = \"admin/home_admin.jsp\"</script>");
                        break;
                    }
                    case "USUARIO":
                        objSesion.setAttribute("usuario", nombre);
                        objSesion.setAttribute("tipo", tipo);
                        objSesion.setAttribute("i_d", u.getID_USUARIO());
                        response.sendRedirect("usuario/home_usuario.jsp");
                        break;
                    case "APLASTISOL": {

                        ArrayList<Object> lista = new ArrayList<>();
                        ArrayList<Object> lista1 = new ArrayList<>();
                        objSesion.setAttribute("usuario", nombre);
                        objSesion.setAttribute("tipo", tipo);
                        objSesion.setAttribute("i_d", u.getID_USUARIO());
                        objSesion.setAttribute("carro", lista);
                        objSesion.setAttribute("carrosalida", lista1);
                        out.print("<script>window.location.href = \"admin/home_admin.jsp\"</script>");
                        break;
                    }
                    case "AMECANICA": {
                        ArrayList<Object> lista = new ArrayList<>();
                        ArrayList<Object> lista1 = new ArrayList<>();
                        objSesion.setAttribute("usuario", nombre);
                        objSesion.setAttribute("tipo", tipo);
                        objSesion.setAttribute("i_d", u.getID_USUARIO());
                        objSesion.setAttribute("carro", lista);
                        objSesion.setAttribute("carrosalida", lista1);
                        out.print("<script>window.location.href = \"admin/home_admin.jsp\"</script>");
                        break;
                    }
                    default:
                        break;
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
            out.println("<script type=\"text/javascript\">");
            out.println("alert('"+ex+"');");
            out.println("location='index.jsp';");
            out.println("</script>");

//            request.setAttribute("error", ex);
//            paginaRespuesta = "error.jsp";        
        } catch (Exception ex) {
            System.out.println(ex);
            out.println("<script type=\"text/javascript\">");
            out.println("alert('"+ex+"');");
            out.println("location='index.jsp';");
            out.println("</script>");
        }
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
