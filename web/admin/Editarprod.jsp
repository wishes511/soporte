<%-- 
    Document   : Editarusu
    Created on : Feb 24, 2017, 5:00:58 PM
    Author     : gateway1
--%>
<%@page import="Modelo.productot"%>
<%@page import="Modelo.usuariot"%>
<%@page import="Persistencia.DBt"%>
<% HttpSession objSesion = request.getSession(false);
//i_d
    String usuario = (String) objSesion.getAttribute("usuario");
    String tipos = (String) objSesion.getAttribute("tipo");
    String ids = String.valueOf(objSesion.getAttribute("i_d"));
    String id_modi = request.getParameter("modi").toUpperCase();
try{
    if (usuario != null && tipos != null && (tipos.equals("ADMIN") || tipos.equals("APLASTISOL")) || tipos.equals("AMECANICA")|| tipos.equals("AATH")) {

    } else {
        response.sendRedirect("../index.jsp");
    }
    
    DBt bd = new DBt();
    productot us = new productot();
    us = bd.buscarprod(Integer.parseInt(id_modi));

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
            <a href="productos_admint.jsp"  class="eldiv grow"><img src="../images/right.png" ></a>
            <div class="col-sm-offset-4">
                <div class="col-sm-5 ">
                    <h3 class="h3" align="center">Editar Producto</h3>
                    <form  action="../Modificarprodut" method="post" class="form-login esp1" name="form2">
                        <input type="text" name="idss" id="idss" required value="<%=us.getID_PRODUCTO()%>" style="display: none"><br>
                        Nombre<input class="form-control input-sm chat-input" type="text" name="nombre" id="nombre" value="<%=us.getNombre()%>"  required/><br>
                        modelo<input class="form-control input-sm chat-input" type="text" name="modelo" id="modelo" value="<%=us.getModelo()%>"  required/><br>
                        marca<input class="form-control input-sm chat-input" type="text" name="marca" id="marca" value="<%=us.getMarca()%>"  required/><br>
                        stock<input class="form-control input-sm chat-input" type="text" name="stock" id="stock" value="<%=us.getStock()%>" required /><br>
                        costo<input class="form-control input-sm chat-input" type="text" name="costo" id="costo" value="<%=us.getCosto()%>" required /><br>
                        descripcion<input class="form-control input-sm chat-input" type="text" name="desc" id="desc" value="<%=us.getDescripcion()%>" required /><br>
                        <br><br><br>
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
}catch(Exception e){}
%>