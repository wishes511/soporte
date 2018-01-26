<%-- 
    Document   : gastos
    Created on : 15/11/2016, 11:56:49 PM
    Author     : mich
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% HttpSession objSesion = request.getSession(false);
//i_d
    String usuario = (String) objSesion.getAttribute("usuario");
    String tipos = (String) objSesion.getAttribute("tipo");
    String ids = String.valueOf(objSesion.getAttribute("i_d"));

    
    int idprodu=0;
    int idprodu1=0;
    String tipo="";
    int stoc=0;

    if (usuario != null && tipos != null && tipos.equals("ADMIN")) {
       
    } else {
        response.sendRedirect("../log.jsp");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gastos</title>
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
        <script>
               $(document).ready(function () {
                $("#benviar").click(function () {
                    var motivo = $("#motivo").val();
                    var monto = $("#monto").val();
                    if (!(/^([A-Z\a-z\s]*)$/.test(motivo))) {
                        document.form.motivo.focus();
                        alert("Nombre invalido solo puedes utilizar espacios y letras");
                        return false;
                    }                    
                    if (!(/^([0-9]+|([0-9]*\.[0-9]*))$/.test(monto))) {
                        document.form.monto.focus();
                        alert("monto invalido");
                        return false;
                    }
                    
                    
                    
                });
                
                

            });
            
        </script>
    </head>
    <body class="body1">
        <div class="container">
            
      <nav class="navbar navbar-default">
    <div class="navbar-header">
      <a class="navbar-brand" href="#"></a>
    </div>
    <ul class="nav navbar-nav nav-pills">
      <li ><a href="home_admin.jsp">Usuarios</a></li>
      <li ><a href="productos_admin.jsp">Productos</a></li>
      <li class=""><a href="proveedores.jsp">Proveedores</a></li>
      <li class="dropdown">
      <a class="dropdown-toggle " data-toggle="dropdown" href="#90">
        Gastos <span class="caret"></span>
      </a>
      <ul class="dropdown-menu" id="#90" role="menu">
        <li><a href="">Nuevo Gasto</a></li>
        <li><a href="Utilidades_Donaciones.jsp">Donaciones,Utilidades</a></li>
      
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
                <div class="col-md-8">
                    <div class="col-md-offset-5 ">
                        <form action="../Nuevogasto" class="" name="form">
                            <h4 class="h4">Motivo</h4>
                            <input class="form-control input-sm chat-input" type="text" name="motivo" id="motivo" value="" required/><br>
                            <h4 class="h4">Monto</h4>
                            <input class="form-control input-sm chat-input" type="text" name="monto" id="monto" value=""  required/><br>
                            <div align="center"><input type="submit" class="btn btn-danger" name="benviar" id="benviar" value="Agregar Gasto"></div>
                        </form>
                </div>
                </div>
                
            </div>
        </div>
    </body>
</html>
