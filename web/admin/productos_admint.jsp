<%-- 
    Document   : prods
    Author     : mich
--%>
<%@page import="Persistencia.DBt"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="Persistencia.DB"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<% HttpSession objSesion = request.getSession(false);
//i_d
    boolean estado;
    String usuario = (String) objSesion.getAttribute("usuario");
    String tipos = (String) objSesion.getAttribute("tipo");
    String ids = String.valueOf(objSesion.getAttribute("i_d"));
try {

    if (usuario != null && tipos != null && (tipos.equals("ADMIN") || tipos.equals("APLASTISOL")) || tipos.equals("AMECANICA")) {

    } else {
        response.sendRedirect("../index.jsp");
    }
    DBt bd = new DBt();
    estado = bd.alerta();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Productos</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" type="image/x-icon" href="../supp.ico" />
        <link rel="stylesheet" type="text/css" href="../css/bootstrap.css">
        <link rel='stylesheet' type="text/css" href="../css/bootstrap.min.css">
        <link rel='stylesheet' type="text/css" href="../css/responsive.css">
        <link rel="stylesheet" type="text/css" href="../css/fondos.css">
        <link rel="stylesheet" type="text/css" href="../css/loginn.css">
        <script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="../js/bootstrap.min.js"></script>
        <script>
            // /^[a-zAZ0-9_\.\-]+@[a-zA-Z0-9\-]+
            $(document).ready(function(){
                document.getElementById('catalogo').focus();
            });
            $(document).ready(function () {
                $("#benviar").click(function () {
                    var nombres = $("#name").val();
                    var cmenu = $("#cmenu").val();
                    var cmayo = $("#cmayo").val();
                    var cprodu = $("#cprodu").val();
                    var desc = $("#desc").val();
                    var stock = $("#stock").val();
                    //var passs = $("#passs").val();
                    if (!(/^([A-Z\a-z\ \ñ]+)$/.test(nombres))) {
                        document.form2.name.focus();
                        alert("Nombre invalido solo puedes utilizar letras y guion bajo");
                        return false;
                    }
                    if (!(/^([0-9]+)\.([0-9\s]*)|([0-9]+)$/.test(cmenu))) {
                        document.form2.cmenu.focus();
                        alert("Coloca solo numeros");
                        return false;
                    }
                    if (!(/^([0-9]+)\.([0-9\s]*)|([0-9]+)$/.test(cmayo))) {
                        document.form2.cmayo.focus();
                        alert("Coloca solo numeros");
                        return false;
                    }
                    if (!(/^([0-9]+)\.([0-9\s]*)|([0-9]+)$/.test(cprodu))) {
                        document.form2.cprodu.focus();
                        alert("Coloca solo numeros");
                        return false;
                    }

                    if (!(/^([A-Z\a-z\s\ñ]*)$/.test(desc))) {
                        document.form2.desc.focus();
                        alert("Descripción invalida solo puedes utilizar espacios y letras");
                        return false;
                    }
                    if (!(/^([0-9]+)$/.test(stock))) {
                        document.form2.stock.focus();
                        alert("Coloca solo numeros");
                        return false;
                    }
                });
            });
            $("#benviar10").click(function () {
                var nombres = $("#n").val();
                var cmenu = $("#cmin").val();
                var cmayo = $("#cmay").val();
                var cprodu = $("#cprod").val();
                var stock = $("#stockk").val();
                if (!(/^([A-Z\a-z\_]+)$/.test(nombres))) {
                    document.form1.n.focus();
                    alert("Nombre invalido solo puedes utilizar letras y guion bajo");
                    return false;
                }
                if (!(/^([0-9]+)\.([0-9\s]*)|([0-9]+)$/.test(cmenu))) {
                    document.form1.cmin.focus();
                    alert("Coloca solo numeros");
                    return false;
                }
                if (!(/^([0-9]+)\.([0-9\s]*)|([0-9]+)$/.test(cmayo))) {
                    document.form1.cmay.focus();
                    alert("Coloca solo numeros");
                    return false;
                }
                if (!(/^([0-9]+)\.([0-9\s]*)|([0-9]+)$/.test(cprodu))) {
                    document.form1.cprod.focus();
                    alert("Coloca solo numeros");
                    return false;
                }
                if (!(/^([0-9]+)$/.test(stock))) {
                    document.form1.stockk.focus();
                    alert("Coloca solo numeros");
                    return false;
                }
            });
        </script>
    </head>
    <body class="body1">
        <div class="container-fluid">
            <nav class="navbar navbar-default">
                <div class="navbar-header">
                    <a class="navbar-brand" href="../index.jsp"><img src="../images/home.png" class="" width="25"></a>
                </div>
                <ul class="nav navbar-nav">
                    <%
                    if(tipos.equals("ADMIN")){
                    %>
                    <li class="dropdown">
                        <a  class="dropdown-toggle" data-toggle="dropdown" href="#80">
                            Usuarios<span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" id="#90" role="menu">
                            <li class=""><a href="">Usuarios</a></li>
                            <li><a href="virtuales.jsp">Vista de direcciones IP</a></li>
                            <li><a href="reporteusuarios.jsp">Reporte de usuarios</a></li>
                        </ul>
                    </li>
                    <%
                    }
                    %>
                    <li class="active"><a href="">Productos</a></li>
                    <%
                    if(tipos.equals("ADMIN") || tipos.equals("AMECANICA")){
                    %>
                    <li class="dropdown">
                        <a  class="dropdown-toggle" data-toggle="dropdown" href="#80">
                            Proveedores<span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" id="#90" role="menu">
                            <li class=""><a href="Eprovedor.jsp">Entrada Proveedor</a></li>
                        </ul>
                    </li>
                    <%
                    }
                    %>
                    <li><a href="Utilidades_Donacionest.jsp">Nueva Compra Interna</a></li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#80">
                            Reportes <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" id="#80" role="menu">
                            <li><a href="Ver_ventast.jsp">Ver ventas</a></li>
                            <li active><a href="Ver_entradast.jsp">Ver Entradas</a></li>
                            <li><a href="reporte.jsp">reporte productos</a></li>
                        </ul>
                    </li>
                    <li class="">
                        <%
                            if(tipos.equals("ADMIN")){
                            if (estado) {
                                out.println("<a href=tareas.jsp STYLE=background-color:rgb(255,89,89);color:white >Tareas</a></li>");
                            } else {
                                out.println("<a href=tareas.jsp>Tareas</a></li>");
                            }
                            }
                            

                        %>
                    <li><a href="../Cierresesion">Salir</a></li>
                </ul>
            </nav>
            <div class="row">
                <div class="col-sm-4 ">
                    <h3 class="h3" align="center">Nuevo producto</h3>
                    <form name="form2" action="proceso.jsp" method="post" class="form-login esp1" enctype="multipart/form-data" style="overflow: hidden">
                        Nombre<input class="form-control input-sm chat-input" type="text" name="name" id="name" value="" maxlength="12"  required/><br>
                        Modelo<input class="form-control input-sm chat-input" type="text" name="modelo" id="modelo" value="" maxlength="13"  required/><br>
                        Marca<input class="form-control input-sm chat-input" type="text" name="marca" id="marca" value=""  required/><br>
                        Costo<input class="form-control input-sm chat-input   " type="text" name="costo" id="costo" value=""  required/><br>   
                        Stock<input class="form-control input-sm chat-input" type="text" name="stock" id="stock" value=""  required/><br>   
                        Descripcion<textarea class="form-control input-sm chat-input" type="text" name="descripcion" id="descripcion" value="" required rows="7" maxlength="500" draggable=""/></textarea><br>
                        Tipo producto: <select name="tipos" >
                        <%
                    if(tipos.equals("ADMIN")){
                    %>    
                            <option>ETIQUETAS</option>
                            <option>SISTEMAS</option>
                    <%
                    }else if(tipos.equals("APLASTISOL")){
                    %>    
                            <option>PLASTISOL</option>
                    <%
                        }else out.print("<option>MECANICA</option>");
                    %>        
                        </select><br><br>
                        Archivo: <input type="file" name="imagen" /><br><br>
                        <input type="submit" class="btn btn-success" value="Aceptar" name="benviar" id="benviar" />
                        <br><br>${oka}
                        ${error}
                    </form>
                </div>
                <div class="col-sm-8">
                    <h3 class="h3" align="center">Vista general de productos</h3>
                    <div class="row espas-search-prods">
                    <div class="col-sm-4">
                        <input type="text" id="catalogo" placeholder="Busqueda de productos" class="form-control" onkeypress="to_searchprod()"> 
                    </div>                    
                </div>
                    <div class="col-sm-offset-1z esp1"  style="overflow: auto" >
                        <table class="table table-responsive mapa" id="tabla-prods">
                            <tr>
                                <td>modelo</td>
                                <td>marca</td>
                                <td>stock</td>
                                <td>costo</td>
                                <td>descripcion</td>
                                <td>Vista</td>
                               <!--<td>Borrar</td>--> 
                                <td>Modificar</td>
                            </tr>
                            <%       try {
                                    DBt uDB = new DBt();
                                    Connection c;
                                    uDB.abrir();
                                    Statement smt;
                                    ResultSet rs;
                                    c = uDB.getConexion();
                                    
                                    String sentenciaSQL = "";
                                    if(tipos.equals("ADMIN")){
                                        sentenciaSQL="SELECT * FROM producto where tipo_producto='SISTEMAS' or tipo_producto='ETIQUETAS' ORDER BY nombre";
                                    }else if(tipos.equals("APLASTISOL")){
                                        sentenciaSQL="SELECT * FROM producto where tipo_producto='PLASTISOL' ORDER BY nombre";
                                    }else sentenciaSQL="SELECT * FROM producto where tipo_producto='MECANICA' ORDER BY nombre";
                                    smt = c.createStatement();
                                    rs = smt.executeQuery(sentenciaSQL);
                                    while (rs.next()) {
                                        int lol = Integer.parseInt(rs.getObject("ID_PRODUCTO").toString());
                                        out.println("<tr>");
                                        // out.println("<td>"+rs.getObject("nombre")+"</td>");
                                        out.println("<td>" + rs.getObject("modelo") + "</td>");
                                        out.println("<td>" + rs.getObject("marca") + "</td>");
                                        out.println("<td>" + rs.getObject("stock") + "</td>");
                                        out.println("<td>" + rs.getObject("costo") + "</td>");
                                        out.println("<td>" + rs.getObject("descripcion") + "</td>");
                                        out.println("<td><a href=" + rs.getObject("imagen") + " ><img class=\"imagen_cata\" src=" + rs.getObject("imagen") + "></a></td>");
                            %>
                            <form action="../Borrarproductot"> 
                                <%
                                //    out.println("<td><a name=borrar  value=" + rs.getObject("id_producto") + " onclick=eliminar(" + rs.getObject("id_producto") + ") class=btn><img src=../images/delete.png  width=30 height=30></a></td>");

                                %>   
                            </form>
                            <td><a name=mod value="<%=lol%>" class="btn" onclick="modi(<%=lol%>)"><img src="../images/modificar.png" width=30 height=30></a></td>
                                    <%
                                                out.println("</tr>");
                                                lol = 0;
                                            }
                                            uDB.cerrar();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    %>
                        </table>
                    </div>
                </div>
            </div>
            <script>
                function to_searchprod() {
                var catalogo = $('#catalogo').val();
                var uso = "catalago_general";
                $.ajax({
                    type: 'post',
                    data: {p: catalogo, uso: uso},
                    url: '../Getregs',
                    success: function (result) {
                        $('#tabla-prods').html(result);
                    }
                });
            }
                function mostrarVentana1(id, n, cmay, cmin, st, cprod)
                {
                    document.getElementById("cprod").value = cprod;
                    document.getElementById("id").value = id;
                    document.getElementById("benviar10").value = id;
                    document.getElementById("n").value = n;
                    document.getElementById("cmay").value = cmay;
                    document.getElementById("cmin").value = cmin;
                    document.getElementById("stockk").value = st;
                    var ventana = document.getElementById("miVentana1");
                    ventana.style.marginTop = "30px";
                    //ventana.style.left = ((document.body.clientWidth) / 2) +  "px";
                    ventana.style.display = "block";
                    ventana.style.left = 20 + "%";
                }
                function ocultarVentana1()
                {
                    var ventana = document.getElementById("miVentana1");
                    ventana.style.display = "none";
                }
                function agregarmodificacion() {
                    var benviar1 = $("#id").val();
                    var n = $("#n").val();
                    var p = $("#cmay").val();
                    var calle = $("#cmin").val();
                    var tel = $("#stockk").val();
                    var tipos = $("#tipos").val();
                    var cprod = $("#cprod").val();

                    if (n != "" || p != "" || calle != "" || tel != "" || cprod != "") {
                        if (!(/^([A-Z\a-z\_]+)$/.test(n)) || !(/^([0-9]+)\.([0-9\s]*)|([0-9]+)$/.test(p)) || !(/^([0-9]+)\.([0-9\s]*)|([0-9]+)$/.test(calle)) || !(/^([0-9]+)\.([0-9\s]*)|([0-9]+)$/.test(cprod)) || !(/^([0-9]+)$/.test(tel))) {
                            alert("Verifique los campos");
                            return false;
                        } else {
                            window.location.href = "../Modificarprod?benviar1=" + benviar1 + "&n=" + n + "&cmay=" + p + "&cmen=" + calle + "&cprod=" + cprod + "&tipos=" + tipos + "&stock=" + tel;
                        }
                    } else {
                        alert("No puedes modificar un usuario con campos vacios");
                    }
                }
                function modi(id)
                {
                    window.location.href = "Editarprod.jsp?modi=" + id;
                }
                function eliminar(id)
                {
                    window.location.href = "../Borrarproductot?borrar=" + id;
                }
            </script>
            <div id="miVentana1" class="modal-scrollbar-measure" style="position: fixed; width: 60%; height: 90%; top: 0; left: 0; font-family:Verdana, Arial, Helvetica, sans-serif; font-size: 5px; font-weight: normal; color: white; display:none;">
                <div class="">      
                    <div class="" style="background-color: #616185;padding-left: 2%;padding-right: 2%;border-radius: 10px;">
                        <a onclick="ocultarVentana1()"><img src="../images/der.png"></a> 
                        <form action="" onsubmit="" name="form1"  style="">
                            <div class=" modal fade modal-content "></div>
                            <h5 class="h5" align="center">ID del producto</h5>
                            <input type=text name="id" id="id" class=form-control input-sm chat-input placeholder=""  disabled="disabled" required> <br>
                            <h5 class="h5" align="center">Nombre</h5>
                            <input type=text name="n" id="n"  class=form-control input-sm chat-input placeholder="" required> <br>
                            <h5 class="h5" align="center">Costo menudeo</h5>
                            <input type=text name=cmay id="cmay" class=form-control input-sm chat-input placeholder="" required> <br>
                            <h5 class="h5" align="center">Costo mayoreo</h5>
                            <input type=text name=cmen id="cmin" class=form-control input-sm chat-input placeholder="" required> <br>
                            <h5 class="h5" align="center">Costo Producción</h5>
                            <input type=text name=cprod id="cprod" class=form-control input-sm chat-input placeholder="" required> <br>
                            <h5 class="h5" align="center">Stock</h5>
                            <input type=text name=stock id="stockk" class=form-control input-sm chat-input placeholder="4772727573"> <br>
                            <h4 class="h4">  Habilitar Producto : <select name="tipos" style="color: gray" id="tipos">
                                    <option>SI</option>
                                    <option>NO</option>
                                </select></h4> 
                            <div align="center">
                                <a class=" btn" name="benviar" id="benviar10" onclick="agregarmodificacion()"><img src="../images/modificar.png" class="img-responsive" width=50 height=50></a>
                                <br><br></div>
                        </form>
                    </div>
                </div>  
            </div>
        </div>
    </body>
</html>
<%
}catch(Exception e){
    System.out.println(e);
response.sendRedirect("../index.jsp");
}
%>
