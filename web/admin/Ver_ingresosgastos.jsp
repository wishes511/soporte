<%-- 
    Document   : Ver_ingresosgastos
    Created on : 15/11/2016, 05:06:30 PM
    Author     : mich
--%>
<%@page import="Modelo.Caja"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Calendar"%>
<%HttpSession objSesion = request.getSession(false);
//i_d
    String usuario = (String) objSesion.getAttribute("usuario");
    String tipos = (String) objSesion.getAttribute("tipo");
    String ids = String.valueOf(objSesion.getAttribute("i_d"));

    

    if (usuario != null && tipos != null && tipos.equals("ADMIN")) {
       
    } else {
        response.sendRedirect("../log.jsp");
    }
    
Calendar fecha = Calendar.getInstance();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String fechac =año+"-"+mes+"-"+dia;
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mis ingresos y gastos</title>
          <link rel="shortcut icon" type="image/x-icon" href="../images/dm2.ico" />
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
         <script type="text/javascript" src="../js/dhtmlgoodies_calendar.js?random=20170118"></script>
         <link type="text/css" rel="stylesheet" href="../css/dhtmlgoodies_calendar.css?random=20170118" media="screen"></link>

    </head>
    <body class="body1">
                        <script type="text/javascript">
            // /^[a-zAZ0-9_\.\-]+@[a-zA-Z0-9\-]+
           
      $(document).ready(function() 
         { 
          $("#tablesorter-demo").tablesorter({widgets: ['zebra']});
          $("#tablesorter-demo").tablesorterPager({container: $("#pager")}); 
     
         });
         $(document).ready(function() 
         { 
         
          $("#tablesorter-demo1").tablesorter({widgets: ['zebra']});
          $("#tablesorter-demo1").tablesorterPager({container: $("#pager")});
         });


        </script>
        <div class="container">
     <nav class="navbar navbar-default">
    <div class="navbar-header">
      <a class="navbar-brand" href="#"></a>
    </div>
    <ul class="nav navbar-nav nav-pills">
      <li ><a href="home_admin.jsp">Usuarios</a></li>
      <li ><a href="productos_admin.jsp">Productos</a></li>
      <li ><a href="proveedores.jsp">Proveedores</a></li>
                  <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown" href="#90">
        Gastos <span class="caret"></span>
      </a>
      <ul class="dropdown-menu" id="#0" role="menu">
        <li><a href="gastos.jsp">Nuevo Gasto</a></li>
        <li><a href="">Donaciones,Utilidades</a></li>
      
      </ul>
    </li>
      <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown" href="#80">
        Reportes <span class="caret"></span>
      </a>
      <ul class="dropdown-menu" id="#80" role="menu">
        <li><a href="">Ver produccion</a></li>
        <li><a href="Ver_ventas.jsp">Ver ventas</a></li>
        <li><a href="Ver_proveedor.jsp">Ver Venta proveedores</a></li>
        <li><a href="Ver_ingresosgastos.jsp">Ver gastos e ingresos</a></li>
        <li class="divider"></li>
        <li><a href="Cierredecaja.jsp">Cierre de caja</a></li>
      </ul>
    </li>
      
      <li><a href="../index.html">Salir</a></li>
    </ul>

</nav>
            <div class="row">
                <form action="../Ingresosgastos" method="post">
                    <div class="col-md-12 " > 
                        <div class="col-md-offset-3">
                               <div class="col-md-2" >
                            fecha de inicio<input class="form-control input-sm chat-input" type="text" name="f1" id="" value="<%=fechac%>"  />
                    </div>
                        <div class="col-md-3">
                            <br>
                                <input type="button" class="btn btn-toolbar alert-success" value="Cal" onclick="displayCalendar(document.forms[0].f1,'yyyy-mm-dd',this)"/>
                    </div>
                         <div class="col-md-2">
                             fecha final<input class="form-control input-sm chat-input" type="text" name="f2" id="f2" value="<%=fechac%>"  />
                    </div>
                        <div class="col-md-3">
                            <br>
                        <input type="button" value="Cal" class="btn btn-toolbar alert-success" onclick="displayCalendar(document.forms[0].f2,'yyyy-mm-dd',this)"/>
                    </div>
                            
                        </div>
                     
                    </div>
                  
                    <div class=" esp1" align="center">
                        <br><input type="submit" name="envio" class="btn btn-success" id="envio"/>
                    </div>
                </form>
                    </div>
                    <div class="row esp" >
                        <div class="col-md-6" style="overflow: auto">
                            <h3 class="h3" align="center">Ingresos</h3>
                            <table  id="tablesorter-demo" class="table table-hover table-responsive table-condensed table-bordered" >
                    <thead class="redondeado" style="background-color:white">
                    <tr>
                        <td>ID</td>
                        <td>Motivo</td>
                        <td>Monto</td>
                        <td>Fecha</td>
                    </tr>
                    </thead>
                    <tbody>
                   
                    
                    <%
                    ArrayList<Object> lista;
                                float total = 0;
                                int cont =0;
                                Caja ca = new Caja();
                                lista=ca.getI();
                    if (lista.isEmpty()) {
                                    System.out.print("no hay datos" + lista.size());
                                } else {
                        System.out.print("tamaño lista" + lista.size());
                                    for (int i = 0; i < lista.size()-1; i++) {
                                        System.out.print("cont" +cont);
                                        if (cont == 3 ) {
                                            out.println("<tr>");
                                            
                                            out.println("<td>" + lista.get(i - 3) + "</td>");
                                            out.println("<td>" + lista.get(i - 2) + "</td>");
                                            out.println("<td>" + lista.get(i - 1) + "</td>");
                                            out.println("<td>" + lista.get(i) + "</td>");
                                            out.println("</tr>");
                                           // System.out.println(lista.get(i) + "*" + lista.get(i - 1) + "*" + lista.get(i+2)+ "*" + lista.get(i - 3)+ " i"+i);
                                            total = total + Float.parseFloat(lista.get(i-1).toString());
                                            cont = 0;
                                           
                                        } else {
                                            System.out.print("conteo i" + i);
                                            cont++;
                                        }
                                        

                                    }
                                   
                                            //total = total + Float.parseFloat(lista.get(lista.size()-2).toString());
                                }
                    
                    %>
                   
                  </tbody> 
                </table>
                         <h3 class="h3" align="center">Total de Ingresos: <%=total%></h3>   
                        </div>
                
                        
                        <div class="col-md-6" style="overflow: auto">
                            <h3 class="h3" align="center">Gastos</h3>
                            <table  id="tablesorter-demo1" class="table table-hover table-responsive table-condensed table-bordered" >
                    <thead class="redondeado" style="background-color:white">
                    <tr>
                        <td>ID</td>
                        <td>Motivo</td>
                        <td>Monto</td>
                        <td>Fecha</td>
                    </tr>
                    </thead>
                    <tbody>
                   
                    
                    <%
                    ArrayList<Object> listag;
                                float total1 = 0;
                                int cont1 =0;
                                
                                listag=ca.getG();
                    if (listag.isEmpty()) {
                                    System.out.print("no hay datos" + listag.size());
                                } else {
                        System.out.print("tamaño lista de G" + listag.size());
                                    for (int i = 0; i < listag.size()-1; i++) {
                                        System.out.print("cont" +cont);
                                        if (cont1 == 3 ) {
                                            out.println("<tr>");
                                            
                                            out.println("<td>" + listag.get(i - 3) + "</td>");
                                            out.println("<td>" + listag.get(i - 2) + "</td>");
                                            out.println("<td>" + listag.get(i - 1) + "</td>");
                                            out.println("<td>" + listag.get(i) + "</td>");
                                            out.println("</tr>");
                                           // System.out.println(lista.get(i) + "*" + lista.get(i - 1) + "*" + lista.get(i+2)+ "*" + lista.get(i - 3)+ " i"+i);
                                            total1 = total1 + Float.parseFloat(listag.get(i-1).toString());
                                            cont1 = 0;
                                           
                                        } else {
                                            System.out.print("conteo i" + i);
                                            cont1++;
                                        }
                                        

                                    }
                                   
                                            //total = total + Float.parseFloat(lista.get(lista.size()-2).toString());
                                }
                    
                    %>
                   
                  </tbody> 
                </table>
                         <h3 class="h3" align="center">Total de Gastos: <%=total1%></h3>   
                        </div>
            </div>
    </body>
</html>
