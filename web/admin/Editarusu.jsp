<%-- 
    Document   : Editarusu
    Created on : Feb 24, 2017, 5:00:58 PM
    Author     : gateway1
--%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.usuariot"%>
<%@page import="Persistencia.DBt"%>
<% HttpSession objSesion = request.getSession(true);
//i_d
    String usuario = (String) objSesion.getAttribute("usuario");
    String tipos = (String) objSesion.getAttribute("tipo");
    String ids = String.valueOf(objSesion.getAttribute("i_d"));
    String id_modi = request.getParameter("modis").toUpperCase();
    if (usuario != null && tipos != null && tipos.equals("ADMIN")) {
    } else {
        response.sendRedirect("../index.jsp");
    }
    try {
        DBt bd = new DBt();
        usuariot us = new usuariot();
        us = bd.buscar(Integer.parseInt(id_modi));
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" type="image/x-icon" href="../supp.ico" />
        <link rel="stylesheet" type="text/css" href="../css/bootstrap.css">
        <link rel='stylesheet' type="text/css" href="../css/bootstrap.min.css">
        <link rel='stylesheet' type="text/css" href="../css/responsive.css">
        <link rel="stylesheet" type="text/css" href="../css/fondos.css">
        <link rel="stylesheet" type="text/css" href="../css/loginn.css">
        <script type="text/javascript" src="../js/bootstrap.js"></script>
        <script type="text/javascript" src="../js/bootstrap.min.js"></script>
        <script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="../js/bootstrap.min.js"></script>
        <style type="text/css">
            .grow:hover { -webkit-transform: scale(1.1); -ms-transform: scale(1.1); transform: scale(1.1); }
            .growinicio:hover { -webkit-transform: scale(1.0); -ms-transform: scale(1.0); transform: scale(1.5); }
            .eldiv { 
                transition: all 0.3s ease; 
            }
            .eldivinicio { 
                transition: all 0.7s ease; 
            }
        </style>
        <title>Editar usuario</title>
    </head>
    <body style="background-repeat: no-repeat">
        <div class="container" style="padding:50px">
            <a href="home_admin.jsp" style="position: absolute" class="eldiv grow"><img src="../images/right.png"></a>
            <div class="col-sm-offset-4">
                <div class="col-sm-5 ">
                    <h3 class="h3" align="center">Editar Usuario</h3>
                    <form  action="../Modificarusut" method="post" class="form-login esp1" name="form2">
                        <input type="text" name="idss" id="idss" required value="<%=us.getID_USUARIO()%>" style="display: none"><br>
                        Usuario<input class="form-control input-sm chat-input" type="text" name="uso" id="uso" value="<%=us.getUsuario()%>"  required/><br>
                        Nombre<input class="form-control input-sm chat-input" type="text" name="names" id="names" value="<%=us.getNombre()%>"  required/><br>
                        apellido<input class="form-control input-sm chat-input" type="text" name="apes" id="apes" value="<%=us.getApellido()%>"  required/><br>
                        Contraseña<input class="form-control input-sm chat-input" type="text" name="passs" id="passs" value="<%=us.getContrasena()%>" required /><br>
                        Ip<input class="form-control input-sm chat-input" type="text" name="ip" id="ip" value="<%=us.getIp()%>" required /><br>
                        Tipo de usuario : <select name="tipos" >
                            <option>USUARIO</option>
                            <option>ADMIN</option>
                        </select><br><br><br>
                        Departamento : <select name="departamento" >
                            <%
                                try {
                                    DBt uDB = new DBt();
                                    Connection c;
                                    uDB.abrir();
                                    Statement smt;
                                    ResultSet rs;
                                    c = uDB.getConexion();
                                    String sentenciaSQL = "SELECT ID_DEP,nombre FROM departamento ORDER BY nombre";
                                    smt = c.createStatement();
                                    rs = smt.executeQuery(sentenciaSQL);
                                    while (rs.next()) {
                                        if (us.getActivo().equals(rs.getString("nombre"))) {
                                            out.println("<option selected value=" + rs.getObject("Nombre") + ">" + rs.getObject("Nombre") + "</option>");
                                        }
                                        out.println("<option value=" + rs.getObject("Nombre") + ">" + rs.getObject("Nombre") + "</option>");
                                    }
                                } catch (Exception a) {
                                }
                            %>
                        </select><br><br><br>
                        <input type="submit" value="Aceptar" name="benviar" id="benviar" class="btn btn-success "/>
                        <br><br>${oka}
                        ${error}
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
<%
    } catch (Exception e) {
        System.out.println("Try editar usuario\n " + e);
    }
%>