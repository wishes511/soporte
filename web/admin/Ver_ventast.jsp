<%-- 
    Document   : Ver_ventas
    Created on : 14/11/2016, 06:01:15 PM
    Author     : mich
--%>
<%@page import="Persistencia.DBt"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="Persistencia.DB"%>
<%@page import="Modelo.venta"%>
<%@page import="Modelo.Producion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Calendar"%>
<% HttpSession objSesion = request.getSession(false);
//select p.modelo,SUM(d.cantidad),MONTH(f.fecha) from factura f join detalle_fact d on d.ID_FACTURA = f.ID_FACTURA join producto p on d.ID_PRODUCTO = p.ID_PRODUCTO where f.fecha > '2017-10-1' group by MONTH(f.fecha),p.modelo order by MONTH(f.fecha)
    boolean estado;
    String usuario = (String) objSesion.getAttribute("usuario");
    String tipos = (String) objSesion.getAttribute("tipo");
    String ids = String.valueOf(objSesion.getAttribute("i_d"));

    if (usuario != null && tipos != null && tipos.equals("ADMIN")) {

    } else {
        response.sendRedirect("../index.jsp");
    }
    Calendar fecha = Calendar.getInstance();
    int año = fecha.get(Calendar.YEAR);
    int mes = fecha.get(Calendar.MONTH) + 1;
    int dia = fecha.get(Calendar.DAY_OF_MONTH);
    String fechac = año + "-" + mes + "-" + dia;
    String fechaca = año + "-" + (mes - 1) + "-" + (dia - 1);
    DBt bd = new DBt();
    estado = bd.alerta();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ver Ventas</title>
        <link rel="shortcut icon" type="image/x-icon" href="../supp.ico" />
        <link rel="stylesheet" type="text/css" href="../css/bootstrap.css">
        <link rel='stylesheet' type="text/css" href="../css/bootstrap.min.css">
        <link rel='stylesheet' type="text/css" href="../css/responsive.css">
        <link rel="stylesheet" type="text/css" href="../css/fondos.css">
        <link rel="stylesheet" type="text/css" href="../css/loginn.css">
        <script type="text/javascript" src="../js/bootstrap.js"></script>
        <script type="text/javascript" src="../js/bootstrap.min.js"></script>        
        <script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script> 
        <script type="text/javascript" src="http://librosweb.es/ejemplos/bootstrap_3/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.9.1/jquery.tablesorter.min.js"></script>
        <script type="text/javascript" src="../js/dhtmlgoodies_calendar.js?random=20171118"></script>
        <link type="text/css" rel="stylesheet" href="../css/dhtmlgoodies_calendar.css?random=20171118" media="screen"></link>

    </head>
    <body class="body1">
        <script type="text/javascript">
            // /^[a-zAZ0-9_\.\-]+@[a-zA-Z0-9\-]+

            $(document).ready(function ()
            {
                $("#tablesorter-demo").tablesorter({widgets: ['zebra']});
                $("#tablesorter-demo").tablesorterPager({container: $("#pager")});
            });


        </script>
        <div class="container-fluid">
            <nav class="navbar navbar-default">
                <div class="navbar-header">
                    <a class="navbar-brand" href="../index.jsp"><img src="../images/home.png" class="" width="25"></a>
                </div>
                <ul class="nav navbar-nav nav-pills">
                    <li ><a href="home_admin.jsp">Usuarios</a></li>
                    <li ><a href="productos_admint.jsp">Productos</a></li>
                    <li ><a href="">Proveedores</a></li>
                    <li><a href="Utilidades_Donacionest.jsp">Nueva Compra Interna</a></li>     
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#80">
                            Reportes <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" id="#80" role="menu">
                            <li><a href="">Ver ventas</a></li>
                            <li><a href="reporte.jsp">reporte</a></li>
                        </ul>
                    </li>
                    <li>
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
                <a><input type="submit" target="costodep.jsp" style="position:relative;float: right" onclick="costo()"  class="btn btn-primary" value="Costo por departamento"></a>
                <div class="col-md-12 " >
                    <div class="col-md-offset-3">
                   
                            <form >
                                     <div class="col-md-2" >
                                fecha de inicio<input class="form-control input-sm chat-input" type="text" name="f1" id="f1" value="<%=fechaca%>"  />
                        </div>
                        <div class="col-md-3">
                            <br>
                            <input type="button" class="btn btn-toolbar alert-success" value="Cal" onclick="displayCalendar(document.forms[0].f1, 'yyyy-mm-dd', this)"/>
                        </div>
                        <div class="col-md-2">
                            fecha final<input class="form-control input-sm chat-input" type="text" name="f2" id="f2" value="<%=fechac%>"  />
                        </div>
                        <div class="col-md-3">
                            <br>
                            <input type="button" value="Cal" class="btn btn-toolbar alert-success" onclick="displayCalendar(document.forms[0].f2, 'yyyy-mm-dd', this)"/>
                        </div>

                        </form>
                    </div>

                </div>

                <div class=" esp1" align="center">
                    <br><input type="submit" name="envio" class="btn btn-success" id="envio" onclick="okas()"/>
                </div>
            </div>
                        
                        <div class="row">
                            <div class="container">
                                <div class="col-sm-3">
                                    Buscar Producto
                                <input type="text" name="buscaprodd" id="buscaprodd" class=" form-control input-sm chat-input" Onkeypress="buscaprod()">
                                </div>
                                </div>
                        </div>
            <div class="row esp" style="overflow: auto">
                <table  id="tablesorter-demo" class="table table-hover table-responsive table-condensed table-bordered" >
                    <thead class="redondeado" style="background-color:white">
                        <tr>
                            <td>venta</td>
                            <td>usuario</td>
                            <td>Cantidad</td>                                                    
                            <td>costo</td>
                            <td>fecha</td>
                        </tr>
                    </thead>
                    <tbody id="llenado">
                    </tbody> 
                </table>
            </div>
           

        </div>
        <script type="text/javascript">
            function okas() {
                var pro = $('#f1').val();
                var pro1 = $('#f2').val();
                var uso="fechas";
                $.ajax({
                    type: 'post',
                    data: {f1: pro, f2: pro1,uso:uso},
                    url: '../Getregs',
                    success: function (result) {
                        $('#llenado').html(result);
                    }
                });
            }

            function buscaprod() {
             
                var pro = $('#f1').val();
                var pro1 = $('#f2').val();
                var prod = $('#buscaprodd').val();
                var uso="buscarp";
                $.ajax({
                    type: 'post',
                    data: {f1: pro, f2: pro1,p:prod,uso:uso},
                    url: '../Getregs',
                    success: function (result) {
                        $('#llenado').html(result);
                    }
                });
            }
        </script>
        <script>
            function mostrarVentanas(id)
            {
                
                var ventana = document.getElementById("miVentana");
                ventana.style.marginTop = "100px";
                //ventana.style.left = ((document.body.clientWidth) / 2) +  "px";
                ventana.style.display = "block";
                ventana.style.left = 10 + "%";
                
                var pro = id;
                var pro1 = "";
                var uso="detalle";
                $.ajax({
                    type: 'post',
                    data: {f1: pro, f2: pro1,uso:uso},
                    url: '../Getregs',
                    success: function (result) {
                        $('#llenadodetalle').html(result);
                    }
                });
            }
            function ocultarVentanas()
            {
                var ventana = document.getElementById("miVentana");
                ventana.style.display = "none";
            }
            function costo(){
                var f1 = $('#f1').val();
                var f2 = $('#f2').val();
                window.location.href = "depcosto.jsp?f1="+f1+"&f2="+f2;
                
            }

        </script>
        <div id="miVentana" style="position: fixed; width: 80%; height: 10%; top: 0; left: 0; font-family:Verdana, Arial, Helvetica, sans-serif; font-size: 12px; font-weight: normal; background-color: #FAFAFA; color: black; display:none;">
            <div class="row " style="background-color:rgb(248,191,22);padding-left: 2%;padding-right: 2%;border-radius: 10px;">
                <a class="btn" onclick="ocultarVentanas()"><img src="../images/right.png" width="50" height="50"></a>
                <h4 class="h4">Detalle de venta</h4>
                <table  id="tablesorter-demo" class="table table-hover table-responsive table-condensed table-bordered" style="overflow: auto">
                    <thead class="redondeado" style="background-color:white">
                        <tr>
                            <td>venta</td>
                            <td>usuario</td>
                            <td>Cantidad</td>
                            <td>nombre</td>
                            <td>modelo</td>
                            <td>importe</td>
                            <td>fecha</td>
                        </tr>
                    </thead>
                    <tbody id="llenadodetalle">
                    </tbody> 
                </table>
           
        </div>
    </div>
</body>
</html>
