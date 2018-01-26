<%-- 
    Document   : Ver_produccion
    Created on : 12/11/2016, 07:17:08 PM
    Author     : mich
--%>

<%@page import="java.util.Calendar"%>
<%@page import="Modelo.Producion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="Persistencia.DB"%>
<% HttpSession objSesion = request.getSession(false);
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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vista de produccion</title>
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
         <script type="text/javascript" src="../js/dhtmlgoodies_calendar.js?random=20161118"></script>
         <link type="text/css" rel="stylesheet" href="../css/dhtmlgoodies_calendar.css?random=20161118" media="screen"></link>

    </head>
    <body class="body1">
                <script type="text/javascript">
            // /^[a-zAZ0-9_\.\-]+@[a-zA-Z0-9\-]+
           
      $(document).ready(function() 
         { 
          $("#tablesorter-demo").tablesorter({widgets: ['zebra']});
          $("#tablesorter-demo").tablesorterPager({container: $("#pager")}); 
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
        <li><a href="Utilidades_Donaciones.jsp">Donaciones,Utilidades</a></li>
      
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
                <form action="../Verproduccion">
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
            <div class="row esp" style="overflow: auto">
                <table  id="tablesorter-demo" class="table table-hover table-responsive table-condensed table-bordered" >
                    <thead class="redondeado" style="background-color:white">
                    <tr>
                        <td>Usuario</td>
                        <td>Producto</td>
                        <td>Produccion</td>
                        <td>Ganancia</td>
                        <td>Fecha</td>
                    </tr>
                    </thead>
                    <tbody>
                   
                    
                    <%
                    ArrayList<Object> lista;
                                float total = 0;
                                int cont =0;
                                Producion pr = new Producion();
                                lista=pr.getProd();
                    if (lista.isEmpty()) {
                                    System.out.print("no hay datos" + lista.size());
                                } else {
                        System.out.print("tamaño lista" + lista.size());
                                    for (int i = 0; i < lista.size()-1; i++) {
                                        System.out.print("cont" +cont);
                                        if (cont == 4) {
                                            out.println("<tr>");
                                            out.println("<td>" + lista.get(i - 4) + "</td>");
                                            out.println("<td>" + lista.get(i - 3) + "</td>");
                                            out.println("<td>" + lista.get(i - 2) + "</td>");
                                            out.println("<td>" + lista.get(i - 1) + "</td>");
                                            out.println("<td>" + lista.get(i) + "</td>");
                                            out.println("</tr>");
                                            System.out.println(lista.get(i) + "*" + lista.get(i - 1) + "*" + lista.get(i+2)+ "*" + lista.get(i - 3)+ "*" + lista.get(i - 4)+" i"+i);
                                            total = total + Float.parseFloat(lista.get(i-1).toString());
                                            cont = 0;
                                           
                                        } else {
                                            System.out.print("conteo i" + i);
                                            cont++;
                                        }
                                        

                                    }
                                    out.println("<tr>");
                                            out.println("<td>" + lista.get(lista.size()-5) + "</td>");
                                            out.println("<td>" + lista.get(lista.size()-4) + "</td>");
                                            out.println("<td>" + lista.get(lista.size()-3) + "</td>");
                                            out.println("<td>" + lista.get(lista.size()-2) + "</td>");
                                            out.println("<td>" + lista.get(lista.size()-1) + "</td>");
                                            out.println("</tr>");
                                            total = total + Float.parseFloat(lista.get(lista.size()-2).toString());
                                }
                    
                    %>
                   
                  </tbody> 
                </table>
            </div>
                    <div class="row">
                        <h3 class="h3">Total : de ganancia para Producción: <%=total%> <br><br></h3>
                    </div>
            
            <div class="row">
                
                 
  <%
    try {
    cont=0;
    int can=0;
   String nombres="";
 total =0;
  
   ArrayList<Object> lista1;
   lista1=pr.getProd();
             
                    if (lista1.isEmpty()) {
                                    System.out.print("no hay datos" + lista1.size());
                                } else {
                        System.out.print("tamaño " + lista1.size());
                                 
                                        for (int j = 0; j < lista1.size()-1; j++) {
                                            System.out.print("i"+j);
                                        if (cont == 4) {
                                            out.println("<div class=col-sm-5 style=overflow:auto> <table class=table table-responsive table-condensed>");
                                             out.println("<tr>");
                                            out.println("<td>Usuario</td>");
                                            out.println("<td>Producto</td>");
                                            out.println("<td>Producción</td>");
                                            out.println("<td>Ganancia</td>");
                                            out.println("<td>Fecha</td>");
                                            out.println("</tr>");
                                            out.println("<tr>");
                                            out.println("<td>" + lista1.get(j - 4) + "</td>");
                                            out.println("<td>" + lista1.get(j - 3) + "</td>");
                                            out.println("<td>" + lista1.get(j - 2) + "</td>");
                                            out.println("<td>" + lista1.get(j - 1) + "</td>");
                                            out.println("<td>" + lista1.get(j) + "</td>");
                                            out.println("</tr>");
                                            System.out.println(lista1.get(j) + "*" + lista1.get(j - 1) + "*" + lista1.get(j+2)+ "*" + lista1.get(j - 3)+ "*" + lista1.get(j - 4));
                                            total = total + Float.parseFloat(lista.get(4-1).toString());
                                            can = can+ Integer.parseInt(lista1.get(j - 2).toString());
                                            cont = 0;j++;
                                            nombres=lista1.get(j - 4).toString();
                                            while(j<lista1.size()){
                                                System.out.print("i"+j);
                                                if(cont==4){
                                                    System.out.print("nombres"+nombres+"/"+lista1.get(j - 4));
                                                if(nombres.equals(lista1.get(j-4).toString())){
                                                out.println("<tr>");
                                            out.println("<td>" + lista1.get(j - 4) + "</td>");
                                            out.println("<td>" + lista1.get(j - 3) + "</td>");
                                            out.println("<td>" + lista1.get(j - 2) + "</td>");
                                            out.println("<td>" + lista1.get(j - 1) + "</td>");
                                            out.println("<td>" + lista1.get(j) + "</td>");
                                            out.println("</tr>");
                                            nombres=lista1.get(j - 4).toString();
                                            can = can+ Integer.parseInt(lista1.get(j - 2).toString());
                                            total = total + Float.parseFloat(lista.get(j-1).toString());
                                                cont=0;
                                                j++;
                                                }else{
                                                out.println("</table><h4 class=h4>Total :"+total+"</h4> <h4 class=h4>cantidad :"+can+"</h4></div>");
                                                System.out.print("nombres"+nombres+"/"+lista1.get(j - 4));
                                                total=0;
                                                 can=0;
                                            out.println("<div class=col-sm-5 style=overflow:auto> <table class=table table-responsive table-condensed>");
                                             out.println("<tr>");
                                            out.println("<td>Usuario</td>");
                                            out.println("<td>Producto</td>");
                                            out.println("<td>Producción</td>");
                                            out.println("<td>Ganancia</td>");
                                            out.println("<td>Fecha</td>");
                                            out.println("</tr>");
                                            out.println("<tr>");
                                            out.println("<td>" + lista1.get(j - 4) + "</td>");
                                            out.println("<td>" + lista1.get(j - 3) + "</td>");
                                            out.println("<td>" + lista1.get(j - 2) + "</td>");
                                            out.println("<td>" + lista1.get(j - 1) + "</td>");
                                            out.println("<td>" + lista1.get(j) + "</td>");
                                            out.println("</tr>");
                                            total = total + Float.parseFloat(lista.get(j-1).toString());
                                            can = can+ Integer.parseInt(lista1.get(j - 2).toString());
                                                nombres=lista1.get(j - 4).toString();
                                                cont=0;
                                                j++;
                                                }
                                                }else{
                                                cont++;
                                                j++;
                                                }
                                            }out.println(" </table><h4 class=h4>Total :"+total+"</h4><h4 class=h4>cantidad :"+can+"</h4></div>");
                                           
                                        } else {
                                            cont++;
                                            
                                        }
                                        
                                    }
                    
                                }
                  
 
    }catch (Exception e)
      {
         e.printStackTrace();
      }
%>
                       
                
            </div>
            
        </div>
       
    </body>
</html>
