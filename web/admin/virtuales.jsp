<%-- 
    Document   : home_admin
    Created on : 16-oct-2016, 16:50:08
    Author     : mich
--%>

<%@page import="java.sql.Array"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Persistencia.DBt"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Persistencia.DB"%>
<%@page import="java.sql.Connection"%>
<%@page import="Controladorest.Nuevodep"%>
<% HttpSession objSesion = request.getSession(true);
//i_d
    boolean estado;
    String usuario = (String) objSesion.getAttribute("usuario");
    String usuario1 = (String) objSesion.getAttribute("usuario1");
    String tipos = (String) objSesion.getAttribute("tipo");
    String ids = String.valueOf(objSesion.getAttribute("i_d"));
    ArrayList<Object> carrito;
    carrito = (ArrayList<Object>) objSesion.getAttribute("carro");
    //out.print(carrito.size());
    //out.println("" + tipos+"/"+ids);
    if (usuario != null && tipos != null && tipos.equals("ADMIN")) {

    } else {
        response.sendRedirect("../index.jsp");
    }
    try {
        DBt bd = new DBt();
        estado = bd.alerta();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Usuarios</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
        <link rel="shortcut icon" type="image/x-icon" href="../supp.ico" />
        <link rel="stylesheet" type="text/css" href="../css/bootstrap.css">
        <link rel='stylesheet' type="text/css" href="../css/bootstrap.min.css">
        <link rel='stylesheet' type="text/css" href="../css/responsive.css">
        <link rel="stylesheet" type="text/css" href="../css/fondos.css">
        <link rel="stylesheet" type="text/css" href="../css/loginn.css">
        <link rel="stylesheet" type="text/css" href="../css/letras.css">
        <script type="text/javascript" src="../js/bootstrap.js"></script>
        <script type="text/javascript" src="../js/bootstrap.min.js"></script>
        <script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="../js/bootstrap.min.js"></script>

        <script>
            $(document).ready(function () {
                document.getElementById("names").focus();
            });
            function nombres() {
                var n = $("#names").val();
                if (!(/^([A-Z\a-z\ \0-9]+)$/.test(n))) {
                    document.getElementById("names").focus();
                    alert("Nombre invalido solo puedes utilizar letras o puedes utilizar guien bajo( _ ) para espacios");
                    return false;
                } else {
                    return true;
                }
            }
            function ip() {
                var n = $("#ips").val();
                if (!(/^([0-9]+)$/.test(n))) {
                    document.getElementById("ips").focus();
                    alert("Solamente puedes introducir numeros");
                    return false;
                } else {
                    return true;
                }
            }function ipmod() {
                var n = $("#ip-mod").val();
                if (!(/^([0-9]+)$/.test(n))) {
                    document.getElementById("ip-mod").focus();
                    alert("Solamente puedes introducir numeros");
                    return false;
                } else {
                    return true;
                }
            }

        </script>

    </head>
    <body class="body1">
        <div class="container-fluid">

            <nav class="navbar navbar-default">
                <div class="navbar-header">
                    <a class="navbar-brand" href="../index.jsp"><img src="../images/home.png" class="" width="25"></a>
                </div>

                <ul class="nav navbar-nav">
                    <li class="dropdown active">
                        <a  class="dropdown-toggle" data-toggle="dropdown" href="#80">
                            Usuarios<span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" id="#90" role="menu">

                            <li ><a href="home_admin.jsp">Usuarios</a></li>
                            <li class="active"><a href="">Vista de direcciones IP</a></li>
                            <li class=><a href="reporteusuarios.jsp">Reporte de usuarios</a></li>
                            
                        </ul>
                    </li>

                    <li ><a href="productos_admint.jsp">Productos</a></li>

                    <li class="dropdown">
                        <a  class="dropdown-toggle" data-toggle="dropdown" href="#80">
                            Proveedores<span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" id="#90" role="menu">

                            <li><a href="proveedores.jsp">Proveedores</a></li>
                            <li><a href="Eprovedor.jsp">Entrada Proveedor</a></li>
                        </ul>
                    </li>
                    <li><a href="Utilidades_Donacionest.jsp">Nueva Compra Interna</a></li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#80">
                            Reportes <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" id="#80" role="menu">
                            <li><a href="Ver_ventast.jsp">Ver ventas</a></li>
                            <li><a href="reporte.jsp">reporte</a></li>
                        </ul>
                    </li>
                    <li class="">
                        <%
                            if (estado) {
                                out.println("<a href=tareas.jsp STYLE=background-color:rgb(255,89,89);color:white >Tareas</a></li>");
                            } else {
                                out.println("<a href=tareas.jsp>Tareas</a></li>");
                            }
                        %>
                    <li><a href="../Cierresesion">Salir</a></li>
                </ul>
            </nav>
            <div class="row">
                <div class="col-sm-4 ">
                    <big><div id="result1" style="background-color: rgb(51, 122, 183);color: white" class="jumbis" align="middle"></div></big>
                    <h2 class="h2" align="center">Nueva maquina virtual</h2>
                    <div class="form-login esp1" >
                        ${oka}
                        ${error}<br>
                        <big>
                            <label>Nombre virtual</label><input class="form-control input-sm chat-input" type="text" name="names" id="names" value="" onchange="saltoip()" required/><br>
                            <label>Ip</label><input class="form-control input-sm chat-input" type="text" name="ips" id="ips" value="" required onchange="saltousuario()" /><br>                    
                            <br>
                            <label>Usuario : </label> <select name="tips" id="tips" onchange="mostrarid()">
                                <%
                                    try {
                                        DBt uDB = new DBt();
                                        Connection c;
                                        uDB.abrir();
                                        Statement smt;
                                        ResultSet rs;
                                        c = uDB.getConexion();
                                        String sentenciaSQL = "SELECT ID_USUARIO,usuario from usuario where activo= 'Y' order by usuario";
                                        smt = c.createStatement();
                                        rs = smt.executeQuery(sentenciaSQL);
                                        while (rs.next()) {
                                            out.println("<option value=" + rs.getString("ID_USUARIO") + " >" + rs.getObject("usuario") + "</option>");
                                        }
                                    } catch (Exception a) {
                                    }
                                %>
                            </select><br><br><br>
                            <input type="submit" value="Aceptar" name="benviar" id="benviar" class="btn btn-danger textoboton" onclick="alta()"/>
                        </big> 
                        <br><br>
                    </div>
                    <br>
                    <big><div id="result1" style="background-color: rgb(51, 122, 183);color: white" class="jumbis" align="middle"></div></big>
                </div>
                <div class="col-sm-7">
                    <h3 class="h3" align="center">Vista usuario con maquinas virtuales</h3>
                    <div class="col-sm-offset-1 esp1" style="overflow: auto">

                        <table class="table table-responsive ">
                            <thead><tr>
                                    <td>Usuario</td>
                                    <td>Departamento</td>
                                    <td>IP</td>
                                    <td>Borrar</td>
                                    <td>modificar</td>
                                </tr></thead>
                                <%
                                    try {

                                        DBt uDB = new DBt();
                                        Connection c;
                                        uDB.abrir();
                                        Statement smt;
                                        ResultSet rs;
                                        c = uDB.getConexion();
                                        String sentenciaSQL = "select d.nombre,u.usuario,v.ip,v.id_virtual from departamento d"
                                                + " join usuario u on u.ID_DEP=d.ID_DEP join "
                                                + "virtuales v on v.ID_USUARIO=u.ID_USUARIO where "
                                                + "u.activo='Y' order by d.nombre";
                                        smt = c.createStatement();
                                        rs = smt.executeQuery(sentenciaSQL);
                                        while (rs.next()) {
                                            int id = Integer.parseInt(rs.getString("v.id_virtual"));
                                            out.println("<font size=3>");
                                            out.println("<tr>");
                                            out.println("<td>" + rs.getObject("u.usuario") + "</td>");
                                            out.println("<td>" + rs.getObject("d.nombre") + "</td>");
                                            out.println("<td>" + rs.getObject("v.ip") + "</td>");
                                            out.println("<td><a name=borrar value=" + id + " onclick=eliminar(" + id + ") class=btn><img src=../images/delete.png  width=30 height=30></a></td>");
                                %>
                            <td><a name="modf" value="<%=id%>" onclick=vermodal(<%=id%>,<%=rs.getObject("v.ip")%>) class="btn"><img src="../images/modificar.png" class="s" width=30 height=30></a>
                                    <%
                                                out.println("</tr>");
                                                out.println("</font>");
                                            }
                                            uDB.cerrar();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    %> 
                        </table>
                    </div></div>

                <div class="col-sm-12" style="overflow: auto">

                </div>
                <script>
                    function mostrarid() {
                        document.getElementById("benviar").focus();
                    }
                    function alta() {
                        if(nombres() && ip()){
                        var nombre = $('#names').val();
                        var ips = $('#ips').val();
                        var usuario = $('#tips').val();
                        var uso = "nuevo";
                        $.ajax({
                            type: 'post',
                            data: {nombre: nombre, ip: ips, usuario: usuario, uso: uso},
                            url: '../Virtuales',
                            success: function (result) {
                                $('#result1').fadeIn(50);
                                $('#result1').html(result);
                                $('#result1').fadeOut(500);
                                document.location.reload();

                            }
                        });  
                        }
                    }
                    function modivirtual() {
                        if(ipmod()){
                            var ids = $('#stealth-id').val();
                        var ips = $('#ip-mod').val();
                        var usuario = $('#u-mod').val();
                        var uso = "modificar";
                        $.ajax({
                            type: 'post',
                            data: {id:ids,ip: ips, usuario: usuario, uso: uso},
                            url: '../Virtuales',
                            success: function (result) {
                                 $('#result1').fadeIn(50);
                                $('#result1').html(result);
                                $('#result1').fadeOut(500);
                                document.location.reload();

                            }
                        }); 
                        }
                    }

                    function eliminar(id)
                    {
                        var uso = "eliminar";
                        $.ajax({
                            type: 'post',
                            data: {id:id, uso: uso},
                            url: '../Virtuales',
                            success: function (result) {
                                document.location.reload();
                            }
                        });  
                    }
                    function saltoip() {
                        if (nombres()) {
                            document.getElementById("ips").focus();
                        }
                    }
                    function saltousuario() {
                        if (ip()) {
                            document.getElementById("tips").focus();
                        }
                    }
                    function vermodal(id,ipp){
                document.getElementById("ip-mod").focus();
                var ids = document.getElementById("stealth-id"); 
                ids.style.display="none";
                document.getElementById("stealth-id").value =id;
                document.getElementById("ip-mod").value=ipp;
                var ventana = document.getElementById("modal");
                ventana.style.marginTop = "100px";
                //ventana.style.left = ((document.body.clientWidth) / 2) +  "px";
                ventana.style.display = "block";
                ventana.style.left = 40 + "%";
                    }
                    function ocultarmodal()
            {
                var ventana = document.getElementById("modal");
                ventana.style.display = "none";
            }
                function saltoumod(){
                    document.getElementById("u-mod").focus();
                }
                </script>
                <div id="modal" style="position: fixed; width: 25%; height: 30%; top: 0; left: 0; font-family:Verdana, Arial, Helvetica, sans-serif; font-size: 12px; font-weight: normal; color: white; display:none;">
                    <div class="container-fluid"><div class="row" align="center" style="background-color: #616185;padding-left: 2%;padding-right: 2%;border-radius: 10px;">
                        <a class="btn" onclick="ocultarmodal()"><img src="../images/right.png" width="50" height="50"></a>
                        <input type="text" class="form-control" id="stealth-id" style="color:black"><br>
                        <h5 class="h5" align="center" >ip :</h5>
                        <input type=text name=ip-mod id="ip-mod"  class="form-control input-sm chat-input" onchange="saltoumod()"> <br>
                    
                        <h5>Usuario : </h5> <select name="u-mod" id="u-mod" class="form-control" onchange="mostrarid()" style="color:black">
                                <%
                                    try {
                                        DBt uDB = new DBt();
                                        Connection c;
                                        uDB.abrir();
                                        Statement smt;
                                        ResultSet rs;
                                        c = uDB.getConexion();
                                        String sentenciaSQL = "SELECT ID_USUARIO,usuario from usuario where activo= 'Y' order by usuario";
                                        smt = c.createStatement();
                                        rs = smt.executeQuery(sentenciaSQL);
                                        while (rs.next()) {
                                            
                                            out.println("<option value=" + rs.getString("ID_USUARIO") + " style=color:black>" + rs.getObject("usuario") + "</option>");
                                        }
                                    } catch (Exception a) {
                                    }
                                %>
                            </select><br>
                            <button class="btn btn-success" onclick="modivirtual()">Completar</button><br>
                            <br>
                    </div></div>
                </div>       
                </body>
                </html>
                <%
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                %>