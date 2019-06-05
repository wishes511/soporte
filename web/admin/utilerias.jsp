<%-- 
    Created on : 12/04/2018, 11:17:46 AM
    Author     : mich
--%>

<%@page import="Modelo.Producto_comprat"%>
<%@page import="Persistencia.DBt"%>
<%@page import="Modelo.Producto_compra"%>
<%@page import="java.util.LinkedList"%>
<%@page import="Modelo.Producto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Controladores.Venta"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
      HttpSession objSesion = request.getSession(false);
    try {
    String usuario = (String) objSesion.getAttribute("usuario");
    String tiposs= (String) objSesion.getAttribute("tipo");
    String ids = String.valueOf(objSesion.getAttribute("i_d"));
    ArrayList<Object> lista;
    if (usuario == null ) {

            } else {

                if (usuario != null && tiposs != null && (tiposs.equals("ADMIN") || tiposs.equals("APLASTISOL") || tiposs.equals("AMECANICA") || tiposs.equals("AATH"))) {

                } else {
                    response.sendRedirect("../index.jsp");
                }
            }
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Utilerias</title>
        <link rel="shortcut icon" type="image/x-icon" href="../supp.ico" />
        <link rel="stylesheet" type="text/css" href="../css/bootstrap.css">
        <link rel='stylesheet' type="text/css" href="../css/bootstrap.min.css">
        <link rel='stylesheet' type="text/css" href="../css/responsive.css">
        <link rel='stylesheet' type="text/css" href="../css/opcional.css">
        <script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="../js/bootstrap.min.js"></script>

    </head>
    <body>
        <div class="container-fluid">
            <!--<button onclick="nuevomostrar()">lolo</button>--> 
            <nav class="navbar navbar-default">
                <div class="navbar-header">
                    <a class="navbar-brand" href="../index.jsp"><img src="images/home.png" class="" width="25"></a>
                </div>

            </nav>
            <hr> <br>
            <div class="row" >
                <div class="row espas-search-prods">
                    <div class="col-sm-4">
                        <input type="text" id="catalogo" placeholder="Busqueda Utilerias" class="form-control" onkeypress="to_searchprod()"> 
                    </div>                    
                </div>
                <div class="espacio1">
                    <div class="col-sm-12 espas" id="get_catalogo">
                        <%try {
                                DBt uDB = new DBt();
                                Connection c;
                                uDB.abrir();
                                Statement smt;
                                ResultSet rs;
                                c = uDB.getConexion();
                                String sentenciaSQL = "SELECT * FROM utilerias where permiso='0' ORDER BY tipo";
                                smt = c.createStatement();
                                rs = smt.executeQuery(sentenciaSQL);
                                int filas = 0;
                                while (rs.next()) {
                                    if (filas == 4) {
                                        out.println("</div>");
                                        filas = 0;
                                    }
                                    if (filas == 0) {
                                        out.println("<div class=row>");
                                    }
                                    out.println("<div class=col-sm-3>");
                                    out.println("<div class=>");
                                    out.println("<div class=thumbnail azul style='width:90% height:90%'>");
                                    out.println("<h4 class=h4 align=center>" + rs.getObject("descripcion") + "</h4>");
                                    if (rs.getString("tipo").equals("video")) {
                                        out.println(" <video onpause='' controls width='100%' height='100'>"
                                                + "<source  type='video/mp4; codecs='avc1.4D401E, mp4a.40.2' src='" + rs.getString("imagen") + "' codecs='avc1.4D401E, mp4a.40.2'/>"
                                                + "</video>");
                                    } else {
                                        out.println("<div ><a class=btn name=id href='" + rs.getString("archivo") + "'><img width=100% height=100%  src=" + rs.getObject("imagen") + " style=max-width: 70%; max-height: 70% ></a></div>");
                                    }
                                    out.println("<div align=center>");
                                    out.println(" </div></div></div></div>");
                                    // out.println("<input type=submit class=form-control name=id value=" + rs.getObject("id_producto") + " onclick= mostrarVentana(" + rs.getObject("id_producto") + ")></div></div></div></div>");
                                    filas++;
                                }
                                smt.close();
                                rs.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        %>

                    </div> 
                </div>

            </div>
        </div>
        <script>
            function to_searchprod() {
                var catalogo = $('#catalogo').val();
                var uso = "utileria";
                $.ajax({
                    type: 'post',
                    data: {p: catalogo, uso: uso},
                    url: '../Getregs',
                    success: function (result) {
                        $('#get_catalogo').html(result);
                    }
                });
            }


        </script>
        <!-- modal de cuantos productos al hacer clic -->

    </div>
</body>
</html>
<%
    } catch (Exception e) {
        System.out.println(e);
    }
%>