<%-- 
    Document   : home_admin
    Created on : 16-oct-2016, 16:50:08
    Author     : mich
--%>

<%@page import="java.sql.Connection"%>
<%@page import="Persistencia.DB"%>
<%@page import="Modelo.Proveedor"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.util.Calendar"%>
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
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String fechac =año+"-"+mes+"-"+dia;
        float total1 = 0;
                                int canti1=0;
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Compra de producto</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
                    <script type="text/javascript">
            // /^[a-zAZ0-9_\.\-]+@[a-zA-Z0-9\-]+
           
      $(document).ready(function() 
         { 
          $("#tablesorter-demo").tablesorter({widgets: ['zebra']});
          $("#tablesorter-demo").tablesorterPager({container: $("#pager")}); 
         });


        </script>
    <body class="body1">
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
        <li><a href="">Ver Venta proveedores</a></li>
        <li><a href="Ver_ingresosgastos.jsp">Ver gastos e ingresos</a></li>
        
        <li class="divider"></li>
        <li><a href="Cierredecaja.jsp">Cierre de caja</a></li>
      </ul>
    </li>
      
      <li><a href="../index.html">Salir</a></li>
    </ul>

</nav>
            <div class="row " align="middle"> 
          <form action="../Ver_proveedorcompra">
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
                       <br><br> <br><input type="submit" name="envio" class="btn btn-success" id="envio"/>
                    </div>
                
                </form>

            </div>
                 <div class="row" >
                
                <% try {
                    Proveedor pr = new Proveedor();
                    String namae=" ";
                 ArrayList<Object> lista1;
                 Statement smt =null;
                    ResultSet rs;
                                 total1 = 0;
                                 canti1=0;
                                int cont1 =0;
                                
                               
                                lista1=pr.getIdpro();
                                if(lista1.isEmpty()){
                                System.out.print("no hay datos" + lista1.size());
                                }else{
                                   
int i =0;
        while(i<lista1.size()){
        DB uDB = new DB();
   Connection c;

        uDB.abrir();
       
        c = uDB.getConexion();
        String sentenciaSQL = "select p.nombre,d.id_compraprod,d.cantidad,d.costo,d.fecha,d.total,pro.nombre from producto p JOIN compra_producto d ON p.id_producto=d.id_producto JOIN proveedor pro ON d.id_proveedor = pro.id_proveedor where d.id_compraprod="+lista1.get(i)+" ORDER BY pro.nombre";
        System.out.println(sentenciaSQL);        
        smt = c.createStatement();
        rs = smt.executeQuery(sentenciaSQL);
         
        while (rs.next())
      {
          System.out.println(namae+"/"+rs.getObject("pro.nombre").toString());
            if(namae.equals(rs.getObject("pro.nombre").toString())){
            System.out.println("okas");
                out.println("<tr>");
                                            out.println("<td>"+rs.getObject("p.nombre").toString()+"</td>");
                                            out.println("<td>"+rs.getObject("d.cantidad").toString()+"</td>");
                                            out.println("<td>"+rs.getObject("d.costo").toString()+"</td>");
                                            out.println("<td>"+rs.getObject("d.total").toString()+"</td>");
                                            out.println("<td>"+rs.getObject("pro.proveedor").toString()+"</td>");
                                            out.println("<td>"+rs.getObject("d.fecha").toString()+"</td>");
                                            out.println("</tr>");
                
            }else{
                out.println("<div class=col-sm-10 style=overflow:auto;background-color:gray;border-radius:10px  > <table class=table table-responsive table-condensed table-hover style=;padding:10px>");
                out.println("<tr style=background-color:white>");
                out.println("<td>Producto</td>");
                out.println("<td>Cantidad</td>");
                out.println("<td>Costo</td>");
                out.println("<td>Total</td>");
                out.println("<td>Proveedor</td>");
                out.println("<td>Fecha</td>");
                out.println("</tr>");
            out.println("<td>"+rs.getObject("p.nombre").toString()+"</td>");
            out.println("<td>"+rs.getObject("d.cantidad").toString()+"</td>");
            out.println("<td>"+rs.getObject("d.costo").toString()+"</td>");
            out.println("<td>"+rs.getObject("d.total").toString()+"</td>");
            out.println("<td>"+rs.getObject("pro.nombre").toString()+"</td>");
            out.println("<td>"+rs.getObject("d.fecha").toString()+"</td>");
            out.println("</tr>");
            
            }
            
                                            

      total1=total1 + Float.parseFloat(rs.getObject("d.total").toString()) ;
      canti1=canti1+ Integer.parseInt(rs.getObject("d.cantidad").toString());
      }
        out.println("</table> </div>");
        
        i++;
        }
        smt.close();
              
                                }
                }catch(Exception e){
                System.out.println(e);
                }
                %>  
                </div> 
                <div class="row">
                    <div class="col-md-4">
                        <h4 class="h4">Cantidad Total: <%=canti1%></h4>
                        
                    </div>
                    <div class="col-md-4">
                        <h4 class="h4"> Total: <%=total1%></h4>
                    </div>
                </div>
              
        </div>
    </body>
</html>
