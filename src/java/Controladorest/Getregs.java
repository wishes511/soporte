/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladorest;

import Modelo.Utileria;
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
 * @author gateway1
 */
@WebServlet(name = "Getregs", urlPatterns = {"/Getregs"})
public class Getregs extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.o
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

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
        HttpSession objSesion = request.getSession(false);
//i_d
        try {

            String usuario = (String) objSesion.getAttribute("usuario");
            String tiposs = (String) objSesion.getAttribute("tipo");
            String ids = String.valueOf(objSesion.getAttribute("i_d"));
            String uso = request.getParameter("uso");
            if (usuario == null && uso.equals("utileria")) {

            } else {

                if (usuario != null && tiposs != null && (tiposs.equals("ADMIN") || tiposs.equals("APLASTISOL") || tiposs.equals("AMECANICA") || tiposs.equals("AATH"))) {

                } else {
                    response.sendRedirect("../index.jsp");
                }
            }

            PrintWriter out = response.getWriter();
            String f1 = request.getParameter("f1");
            String f2 = request.getParameter("f2");
            String produ = request.getParameter("p");
            // ver ventas general
            //System.out.println(uso);
            if (uso.equals("catalago_general")) {
                String tipo_p = "";
                if (tiposs.equals("ADMIN")) {
                    tipo_p = "SISTEMAS";
                } else if (tiposs.equals("APLASTISOL")) {
                    tipo_p = "PLASTISOL";
                } else if (tiposs.equals("AMECANICA")) {
                    tipo_p = "MECANICA";
                } else if (tiposs.equals("AATH")) {
                    tipo_p = "ATH";
                }
                DBt db = new DBt();
                int cont = 0;
                ArrayList<Object> lista;
                lista = db.ver_catalogo_prod(produ, tipo_p);
                out.print("<table class=\"table table-responsive mapa\" id=\"tabla-prods\">\n"
                        + "                            <tr>\n"
                        + "                                <td>modelo</td>\n"
                        + "                                <td>marca</td>\n"
                        + "                                <td>stock</td>\n"
                        + "                                <td>costo</td>\n"
                        + "                                <td>descripcion</td>\n"
                        + "                                <td>Vista</td>\n"
                        + //"                                <td>Borrar</td>\n" +
                        "                                <td>Modificar</td>\n"
                        + "                            </tr>");
                if (!lista.isEmpty()) {
                    for (int i = 0; i < lista.size(); i++) {
                        if (cont == 6) {
                            out.print("<tr><td>" + lista.get(i - 5) + "</td><td>" + lista.get(i - 4) + "</td><td>" + lista.get(i - 3) + "</td><td>" + lista.get(i - 2) + "</td>"
                                    + "<td>" + lista.get(i - 1) + "</td><td><a href=" + lista.get(i) + " ><img class=\"imagen_cata\" src=" + lista.get(i) + "></a></td>"
                                    // + "<td><a name=borrar  value="+lista.get(i-6)+"  onclick=eliminar("+lista.get(i-6)+") class=btn><img src=../images/delete.png  width=30 height=30></a></td>"
                                    + "<td><a name=mod value=" + lista.get(i - 6) + " class=\"btn\" onclick=modi(" + lista.get(i - 6) + ")><img src=\"../images/modificar.png\" width=30 height=30></a></td></tr>");
                            cont = 0;
                        } else {
                            cont++;
                        }

                    }
                }
                out.print("</table>");
            } else if (uso.equals("catalogo")) {
                String tipo_p = "";
                if (tiposs.equals("ADMIN")) {
                    tipo_p = "SISTEMAS";
                } else {
                    tipo_p = "PLASTISOL";
                }
                DBt db = new DBt();
                int cont = 0;
                ArrayList<Object> lista;
                lista = db.ver_catalogo_prov(produ, tipo_p);
                if (!lista.isEmpty()) {
                    for (int i = 0; i < lista.size(); i++) {
                        if (cont == 4) {
                            out.println("<div class=col-md-4>");
                            out.println("<div class=>");
                            out.println("<div class=thumbnail azul style=width:70% height:70% >");
                            out.println("<h4 class=h4 align=center>" + lista.get(i - 3) + "</h4>");
                            out.println("<h4 class=h4 align=center>" + lista.get(i - 2) + "</h4>");
                            out.println("<a class=btn name=id value=" + lista.get(i - 4) + " onclick= mostrarVentanas(" + lista.get(i - 4) + ")><img width=60% height=60% class=img-responsive  src=" + lista.get(i) + "></a>");
                            out.println("<div align=center>");
                            out.println("<h4 class=h4 align=cente>stock</h4>");
                            out.println("<input type=text name=prec class=form-control input-sm chat-input placeholder=$  value=" + lista.get(i - 1) + " disabled=disabled> ");
                            out.println(" </div></div></div></div>");
                            cont = 0;
                        } else {
                            cont++;
                        }

                    }
                }

            } else if (uso.equals("buscarp")) {
                String tipo_p = "";
                if (tiposs.equals("ADMIN")) {
                    tipo_p = "SISTEMAS";
                } else if (tiposs.equals("PLASTISOL")) {
                    tipo_p = "PLASTISOL";
                } else if (tiposs.equals("AATH")) {
                    tipo_p = "ATH";
                }
                ArrayList<Object> lista;
                DBt db = new DBt();
                lista = db.verventast(f1, f2, produ, tipo_p);
                int cont = 0;
                float total = 0;
                int totalp = 0;
                for (int i = 0; i < lista.size(); i++) {

                    if (cont == 4) {
                        out.print("<tr onclick=mostrarVentanas(" + lista.get(i - 4) + ") ><a>");
                        out.print("<td align=center>" + lista.get(i - 4) + "</td>");
                        out.print("<td>" + lista.get(i - 3) + "</td>");
                        out.print("<td>" + lista.get(i - 2) + "</td>");
                        out.print("<td>" + Float.parseFloat(lista.get(i - 1).toString()) + "</td>");
                        out.print("<td>" + lista.get(i) + "</td>");
                        out.print("</a></tr>");
                        totalp += Integer.parseInt(lista.get(i - 2).toString());
                        total += Double.parseDouble(lista.get(i - 1).toString());
                        cont = 0;
                    } else {
                        cont++;
                    }
                }
                if (lista.isEmpty()) {
                } else {

                    out.print("<tr>");
                    out.print("<td></td>");
                    out.print("<td><h4>Total:</h4></td>");
                    out.print("<td><h4><a href=>" + totalp + "</a></h4></td>");
                    out.print("<td><h4><a href=>" + total + "</a></h4></td>");
                    out.print("<td></td>");
                    out.print("</tr>");

                    // llenado de vta de departamento por onkey press
                    if (tiposs.equals("ADMIN")) {
                        out.print("<tr>");
                        out.print("<td colspan=2 align=center><h3>Costo por departamento</h3></td>");
                        out.print("</tr>");
                        ArrayList<Object> lista1;
                        lista1 = db.verventastdep(f1, f2, produ);
                        int cont1 = 0;
                        float total1 = 0;
                        for (int i = 0; i < lista1.size(); i++) {
                            out.println("e.e");
                            if (cont1 == 1) {
                                out.print("<tr>");
                                out.print("<td>" + lista1.get(i - 1) + "</td>");
                                out.print("<td>" + lista1.get(i) + "</td>");
                                out.print("</tr>");

                                total1 += Float.parseFloat(lista1.get(i).toString());
                                cont1 = 0;
                            } else {
                                cont1++;
                            }
                        }
                        out.print("<tr>");
                        out.print("<td>");
                        out.print("</tr>");
                        out.print("<tr>");
                        out.print("<td><h3>Total<h3></td>");
                        out.print("<td><h3 class=h3>" + total1 + "<h3></td>");
                        out.print("</tr>");
                    }

                }
                // Acaba buscar p, ahora sigue fechas por boton
            } else if (uso.equals("fechas")) {
                String tipo_p = "";
                if (tiposs.equals("ADMIN")) {
                    tipo_p = "SISTEMAS";
                } else if (tiposs.equals("APLASTISOL")) {
                    tipo_p = "PLASTISOL";
                } else if (tiposs.equals("AMECANICA")) {
                    tipo_p = "MECANICA";
                } else if (tiposs.equals("AATH")) {
                    tipo_p = "ATH";
                }
                ArrayList<Object> lista;
                DBt db = new DBt();
                lista = db.verventast(f1, f2, tipo_p, "", "");
                int cont = 0;
                float total = 0;
                int totalp = 0;
                for (int i = 0; i < lista.size(); i++) {
                    out.println("e.e");
                    if (cont == 4) {
                        out.print("<tr onclick=mostrarVentanas(" + lista.get(i - 4) + ") ><a>");
                        out.print("<td>" + lista.get(i - 4) + "</td>");
                        out.print("<td>" + lista.get(i - 3) + "</td>");
                        out.print("<td>" + lista.get(i - 2) + "</td>");
                        out.print("<td>" + Float.parseFloat(lista.get(i - 1).toString()) + "</td>");
                        out.print("<td>" + lista.get(i) + "</td>");
                        out.print("</a></tr>");
                        totalp += Integer.parseInt(lista.get(i - 2).toString());
                        total += Double.parseDouble(lista.get(i - 1).toString());
                        cont = 0;
                    } else {
                        cont++;
                    }
                }
                if (lista.isEmpty()) {
                } else {
                    out.print("<tr>");

                    out.print("<td></td>");
                    out.print("<td><h4>Total:</h4></td>");
                    out.print("<td><h4><a href=>" + totalp + "</a></h4></td>");
                    out.print("<td><h4><a href=>" + total + "</a></h4></td>");
                    out.print("<td></td>");
                    out.print("</tr>");
                    if (tiposs.equals("ADMIN")) {
                        ArrayList<Object> lista1;
                        lista1 = db.verventastdep(f1, f2, produ);
                        int cont1 = 0;
                        float total1 = 0;
                        for (int i = 0; i < lista1.size(); i++) {
                            out.println("e.e");
                            if (cont1 == 1) {
                                out.print("<tr>");
                                out.print("<td>" + lista1.get(i - 1) + "</td>");
                                out.print("<td>" + lista1.get(i) + "</td>");
                                out.print("</tr>");
                                total1 += Float.parseFloat(lista1.get(i).toString());
                                cont1 = 0;
                            } else {
                                cont1++;
                            }
                        }
                        out.print("<tr>");
                        out.print("<td>");
                        out.print("</tr>");

                        out.print("<tr>");
                        out.print("<td colspan=2 align=center><h3>Costo por departamento</h3></td>");
                        out.print("</tr>");

                        // llenado depas clic boton
                        ArrayList<Object> lista2;
                        lista2 = db.verventastdep(f1, f2);
                        int cont2 = 0;
                        float total2 = 0;
                        for (int i = 0; i < lista2.size(); i++) {
                            out.println("e.e");
                            if (cont2 == 1) {
                                out.print("<tr><a>");
                                out.print("<td>" + lista2.get(i - 1) + "</td>");
                                out.print("<td>" + lista2.get(i) + "</td>");
                                out.print("</a></tr>");
                                total2 += Float.parseFloat(lista2.get(i).toString());
                                cont2 = 0;
                            } else {
                                cont2++;
                            }
                        }
                        out.print("<tr>");
                        out.print("<td>");
                        out.print("</tr>");
                        out.print("<tr>");
                        out.print("<td><h3>Total</h3></td>");
                        out.print("<td><h3><a href=>" + total + "</a></h4></td>");
                        out.print("</tr>");
                    }

                }
            } else if (uso.equals("fechasp")) {
                String tipo_p = "";
                String tipo_prov = "";
                if (tiposs.equals("ADMIN")) {
                    tipo_p = "SISTEMAS";
                    tipo_prov = "SIS";
                } else if (tiposs.equals("APLASTISOL")) {
                    tipo_p = "PLASTISOL";
                    tipo_prov = "PLA";
                } else if (tiposs.equals("AMECANICA")) {
                    tipo_p = "MECANICA";
                    tipo_prov = "GEN";
                } else if (tiposs.equals("AATH")) {
                    tipo_p = "ATH";
                    tipo_prov = "SIS";
                }
                ArrayList<Object> lista;
                DBt db = new DBt();
                lista = db.verventastp(f1, f2, tipo_p, tipo_prov, "");
                int cont = 0;
                float total = 0;
                int totalp = 0;
                for (int i = 0; i < lista.size(); i++) {
                    out.println("e.e");
                    if (cont == 4) {
                        out.print("<tr onclick=mostrarVentanas(" + lista.get(i - 4) + ") ><a>");
                        out.print("<td>" + lista.get(i - 4) + "</td>");
                        out.print("<td>" + lista.get(i - 3) + "</td>");
                        out.print("<td>" + lista.get(i - 2) + "</td>");
                        out.print("<td>" + Float.parseFloat(lista.get(i - 1).toString()) + "</td>");
                        out.print("<td>" + lista.get(i) + "</td>");
                        out.print("</a></tr>");
                        totalp += Integer.parseInt(lista.get(i - 2).toString());
                        total += Double.parseDouble(lista.get(i - 1).toString());
                        cont = 0;
                    } else {
                        cont++;
                    }
                }
                if (lista.isEmpty()) {
                } else {
                    out.print("<tr>");

                    out.print("<td></td>");
                    out.print("<td><h4>Total:</h4></td>");
                    out.print("<td><h4><a href=>" + totalp + "</a></h4></td>");
                    out.print("<td><h4><a href=>" + total + "</a></h4></td>");
                    out.print("<td></td>");
                    out.print("</tr>");
                    if (tiposs.equals("ADMIN")) {
                        ArrayList<Object> lista1;

                        lista1 = db.verventastdep(f1, f2, produ);
                        int cont1 = 0;
                        float total1 = 0;
                        for (int i = 0; i < lista1.size(); i++) {
                            out.println("e.e");
                            if (cont1 == 1) {
                                out.print("<tr>");
                                out.print("<td>" + lista1.get(i - 1) + "</td>");
                                out.print("<td>" + lista1.get(i) + "</td>");
                                out.print("</tr>");
                                total1 += Float.parseFloat(lista1.get(i).toString());
                                cont1 = 0;
                            } else {
                                cont1++;
                            }
                        }
                        out.print("<tr>");
                        out.print("<td>");
                        out.print("</tr>");

                        out.print("<tr>");
                        out.print("<td colspan=2 align=center><h3>Costo por departamento</h3></td>");
                        out.print("</tr>");

                        // llenado depas clic boton
                        ArrayList<Object> lista2;

                        lista2 = db.verventastdep(f1, f2);
                        int cont2 = 0;
                        float total2 = 0;
                        for (int i = 0; i < lista2.size(); i++) {
                            out.println("e.e");
                            if (cont2 == 1) {
                                out.print("<tr><a>");
                                out.print("<td>" + lista2.get(i - 1) + "</td>");
                                out.print("<td>" + lista2.get(i) + "</td>");
                                out.print("</a></tr>");
                                total2 += Float.parseFloat(lista2.get(i).toString());
                                cont2 = 0;
                            } else {
                                cont2++;
                            }
                        }
                        out.print("<tr>");
                        out.print("<td>");
                        out.print("</tr>");
                        out.print("<tr>");
                        out.print("<td><h3>Total</h3></td>");
                        out.print("<td><h3><a href=>" + total + "</a></h4></td>");
                        out.print("</tr>");
                    }

                }
            } else if (uso.equals("detallep")) {
                // detalle de la vta
                ArrayList<Object> listas;
                DBt db = new DBt();
                listas = db.verventastdp(Integer.parseInt(f1));
                int cont = 0;
                float total = 0;
                for (int i = 0; i < listas.size(); i++) {
                    out.println("e.e");
                    if (cont == 6) {
                        out.print("<tr><a>");
                        out.print("<td>" + listas.get(i - 6) + "</td>");
                        out.print("<td>" + listas.get(i - 5) + "</td>");
                        out.print("<td>" + listas.get(i - 4) + "</td>");
                        out.print("<td>" + listas.get(i - 3) + "</td>");
                        out.print("<td>" + listas.get(i - 2) + "</td>");
                        out.print("<td>" + listas.get(i - 1) + "</td>");
                        out.print("<td>" + listas.get(i) + "</td>");
                        out.print("</a></tr>");
                        total += Double.parseDouble(listas.get(i - 1).toString());
                        cont = 0;
                    } else {
                        cont++;
                    }
                }
                out.print("<tr>");
                out.print("<td></td>");
                out.print("<td></td>");
                out.print("<td></td>");
                out.print("<td></td>");
                out.print("<td><h4>Total:</h4></td>");
                out.print("<td><h4><a>" + total + "</a></h4></td>");
                out.print("<td></td>");
                out.print("</tr>");
            } else if (uso.equals("utileria")) {
                DBt db = new DBt();
                String util = request.getParameter("p");
                ArrayList<Utileria> lista;
                lista = db.getutileria(util);
                if (!lista.isEmpty()) {
                    //System.out.println("entre");
                    int filas = 0;
                    for (int i = 0; i < lista.size(); i++) {
                        if (filas == 4) {
                            out.print("</div>");
                            filas = 0;
                        }
                        if (filas == 0) {
                            out.print("<div class=row>");
                        }
                        out.print("<div class=col-sm-3>");
                        out.print("<div class=>");
                        out.print("<div class=thumbnail azul style='width:90% height:90%'>");
                        out.print("<h4 class=h4 align=center>" + lista.get(i).getDescripcion() + "</h4>");
                        if (lista.get(i).getTipo().equals("video")) {
                            out.println(" <video onpause='' controls width='100%' height='100'>"
                                    + "<source  type='video/mp4; codecs='avc1.4D401E, mp4a.40.2' src='" + lista.get(i).getImagen() + "' codecs='avc1.4D401E, mp4a.40.2'/>"
                                    + "</video>");
                        } else {
                            out.print("<div ><a class=btn name=id href='" + lista.get(i).getArchivo() + "'><img width=100% height=100%  src=" + lista.get(i).getImagen() + " style=max-width: 70%; max-height: 70% ></a></div>");
                        }

                        out.print("<div align=center>");
                        out.print(" </div></div></div></div>");
                        filas++;
                    }
                }
            } else {
                // detalle de la vta
                ArrayList<Object> listas;
                DBt db = new DBt();
                listas = db.verventastd(Integer.parseInt(f1));
                int cont = 0;
                float total = 0;
                for (int i = 0; i < listas.size(); i++) {
                    out.println("e.e");
                    if (cont == 6) {
                        out.print("<tr><a>");
                        out.print("<td>" + listas.get(i - 6) + "</td>");
                        out.print("<td>" + listas.get(i - 5) + "</td>");
                        out.print("<td>" + listas.get(i - 4) + "</td>");
                        out.print("<td>" + listas.get(i - 3) + "</td>");
                        out.print("<td>" + listas.get(i - 2) + "</td>");
                        out.print("<td>" + listas.get(i - 1) + "</td>");
                        out.print("<td>" + listas.get(i) + "</td>");
                        out.print("</a></tr>");
                        total += Double.parseDouble(listas.get(i - 1).toString());
                        cont = 0;
                    } else {
                        cont++;
                    }
                }
                out.print("<tr>");
                out.print("<td></td>");
                out.print("<td></td>");
                out.print("<td></td>");
                out.print("<td></td>");
                out.print("<td><h4>Total:</h4></td>");
                out.print("<td><h4><a>" + total + "</a></h4></td>");
                out.print("<td></td>");
                out.print("</tr>");
            }
        } catch (Exception e) {
            System.out.println(e);
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
