<%-- 
    Document   : Cierredecaja
    Created on : 15/11/2016, 12:18:18 AM
    Author     : mich
--%>

<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.Caja"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="Persistencia.DB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        float in=0,ou=0;
        int pv=0,pc=0,vr=0,pp=0,ps=0;
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String fechac =año+"-"+mes+"-"+dia;
        Caja ca = new Caja();
        ArrayList<Object> lista = new ArrayList<Object>();
        lista=ca.getCaja();
        if(lista.isEmpty()){
       
        }else{
        in =Float.parseFloat(lista.get(0).toString());
        ou =Float.parseFloat(lista.get(1).toString());
        pv =Integer.parseInt(lista.get(2).toString());
        pc =Integer.parseInt(lista.get(3).toString());
        vr =Integer.parseInt(lista.get(4).toString());
        pp =Integer.parseInt(lista.get(5).toString());
        ps =Integer.parseInt(lista.get(6).toString());
        
        }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cierre de Caja</title>
        <link rel="shortcut icon" type="image/x-icon" href="../images/dm2.ico" />
        <link rel="stylesheet" type="text/css" href="../css/bootstrap.css">
        <link rel='stylesheet' type="text/css" href="../css/bootstrap.min.css">
        <link rel='stylesheet' type="text/css" href="../css/responsive.css">
         <link rel="stylesheet" type="text/css" href="../css/fondos.css">
        <link rel="stylesheet" type="text/css" href="../css/loginn.css">
        <script type="text/javascript" src="../js/bootstrap.js"></script>
        <script type="text/javascript" src="../js/bootstrap.min.js"></script>        
        <script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script> 
        <script type="text/javascript" src="../js/bootstrap.min.js"></script>
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
      <a class="dropdown-toggle " data-toggle="dropdown" href="#90">
        Gastos <span class="caret"></span>
      </a>
      <ul class="dropdown-menu" id="#90" role="menu">
        <li><a href="gastos.jsp">Nuevo Gasto</a></li>
        <li><a href="">Donaciones,Utilidades</a></li>
      
      </ul>
    </li>
      <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown" href="#80">
        Reportes <span class="caret"></span>
      </a>
      <ul class="dropdown-menu" id="#80" role="menu">
        <li><a href="Ver_produccion.jsp">Ver produccion</a></li>
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
                        <div align="center">
                            <%
                            try {
                                String datfecha="-";
                                DB uDB = new DB();
                                Connection c;

                                uDB.abrir();
                                Statement smt;
                                ResultSet rs;
                                c = uDB.getConexion();
                                //String sentenciaSQL = "SELECT * FROM producto where tipo != 'UNIDAD'";
                                String sentenciaSQL = "SELECT * FROM ingresos where fecha='"+fechac+"'";
                                System.out.println(sentenciaSQL);
                                smt = c.createStatement();
                                rs = smt.executeQuery(sentenciaSQL);

                                //AQUI ME QUEDE!!!!!! :3
                                while (rs.next()) {
                                 datfecha=rs.getObject("fecha").toString();
                                }
                                if(rs!=null){
                                    if(datfecha.equals("-")){
                                        System.out.println("entre aqui"+datfecha);
                                
                                    out.println("<input type=submit class=btn btn-danger name=envio onclick=nuevo() value=Cerrar Caja>");
                                out.println("<input type=submit class=btn btn-danger name=envio onclick=ver() value=Ver disabled=disabled>");
                                
                                    }else{
                                        System.out.println("entre aca"+datfecha);
                                     out.println("<input type=submit class=btn btn-danger name=envio onclick=nuevo() value=Cerrar Caja disabled=disabled>");
                                   out.println("<input type=submit class=btn btn-danger name=envio onclick=ver() value=Ver >");
                                
                                    }
                                }else {
                               System.out.println("OMG "+datfecha);
                                }
                                uDB.cerrar();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            %>
                                
                           
                        </div>
                    </div>
                    <div class="row">
                        <br>
                        <div class="col-md-5" style="overflow: auto">
                            <table class="table table-hover table-responsive" id="tablesorter-demo1">
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
                            try {
                                DB uDB = new DB();
                                Connection c;

                                uDB.abrir();
                                Statement smt;
                                ResultSet rs;
                                c = uDB.getConexion();
                                //String sentenciaSQL = "SELECT * FROM producto where tipo != 'UNIDAD'";
                                String sentenciaSQL = "SELECT * FROM ingresos where fecha='"+fechac+"'";
                                System.out.println(sentenciaSQL);
                                smt = c.createStatement();
                                rs = smt.executeQuery(sentenciaSQL);

                                //AQUI ME QUEDE!!!!!! :3
                                while (rs.next()) {
                                    out.println("<tr>");
  
                                out.println("<td>"+rs.getObject("id_ingreso")+"</td>");
                                out.println("<td>"+rs.getObject("motivo")+"</td>");
                                out.println("<td>"+rs.getObject("monto")+"</td>");
                                 out.println("<td>"+rs.getObject("fecha")+"</td>");
                                 out.println("</tr>");             
                                }
                                uDB.cerrar();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        %>
                                </tbody>
                            </table>
                                <h3 class="h3">Total de Ingresos $ <%=in%></h3>
                        </div>
                        <div class="col-md-5" style="overflow: auto">
                            <table class="table table-hover table-responsive" id="tablesorter-demo" >
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
                            try {
                                DB uDB = new DB();
                                Connection c;

                                uDB.abrir();
                                Statement smt;
                                ResultSet rs;
                                c = uDB.getConexion();
                                //String sentenciaSQL = "SELECT * FROM producto where tipo != 'UNIDAD'";
                                String sentenciaSQL = "SELECT * FROM gastos where fecha='"+fechac+"'";
                                System.out.println(sentenciaSQL);
                                smt = c.createStatement();
                                rs = smt.executeQuery(sentenciaSQL);

                                //AQUI ME QUEDE!!!!!! :3
                                while (rs.next()) {
                                    out.println("<tr>");
  
                                out.println("<td>"+rs.getObject("id_gasto")+"</td>");
                                out.println("<td>"+rs.getObject("motivo")+"</td>");
                                out.println("<td>"+rs.getObject("monto")+"</td>");
                                 out.println("<td>"+rs.getObject("fecha")+"</td>");
                                 out.println("</tr>");             
                                }
                                uDB.cerrar();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        %>
                                </tbody>
                            </table>
                            <h3 class="h3">Total de gastos $ <%=ou%></h3>
                        </div>
                    </div>
                        <div class="row esp" >
                            <div class=" col-md-6">
                             <h4 class="h4" > Total de Productos Vendidos:  <%=pv%></h4>
                             </div><div class=" col-md-6">
                            <h4 class="h4" > Total de Productos Comprados:  <%=pc%></h4></div>
                            <div class=" col-md-6">
                                <h4 class="h4"> Total de Ventas Realizadas:  <%=vr%></h4></div>
                            <div class=" col-md-6">
                            <h4 class="h4"> Total de Productos Producidos:  <%=pp%></h4></div>
                            <div class=" col-md-6">
                            <h4 class="h4"> Total de Productos Restantes:  <%=(ps-pv)%></h4></div>
                            
                        </div>
                </div>
                            <script>
                                
                                function nuevo(){
                            window.location.href = "../Cierredecaja?uso=new";
                        }
                        function ver(){
                            window.location.href = "../Cierredecaja?uso=vista";
                        }
                            </script>
    </body>
</html>
