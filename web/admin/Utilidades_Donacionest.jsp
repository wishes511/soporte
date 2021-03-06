<%-- 
    Document   : Realizar_ventas
    Created on : 16/05/2017, 11:17:46 AM
    Author     : mich
--%>

<%@page import="Modelo.Producto_comprat"%>
<%@page import="Persistencia.DBt"%>
<%@page import="Modelo.Producto_compra"%>
<%@page import="java.util.LinkedList"%>
<%@page import="Modelo.Producto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Controladores.Venta"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%int id_produc =0;
String usuarios="";
HttpSession objSesion = request.getSession(false);
//sesiones a usar
boolean estado;
try{
    String usuario = (String) objSesion.getAttribute("usuario");
    String tipos = (String) objSesion.getAttribute("tipo");
    String ids = String.valueOf(objSesion.getAttribute("i_d"));
    if (usuario != null && tipos != null && (tipos.equals("ADMIN")) || tipos.equals("APLASTISOL") || tipos.equals("AMECANICA")|| tipos.equals("AATH")) {
       
    } else {
        response.sendRedirect("../index.jsp");
    }
    ArrayList<Object> lista;
    lista = (ArrayList<Object>) objSesion.getAttribute("carrosalida");
    DBt bd = new DBt();
    estado=bd.alerta();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Catalogo de Productos</title>
    
        <link rel="shortcut icon" type="image/x-icon" href="../supp.ico" />
        <link rel="stylesheet" type="text/css" href="../css/bootstrap.css">
        <link rel='stylesheet' type="text/css" href="../css/bootstrap.min.css">
        <link rel='stylesheet' type="text/css" href="../css/responsive.css">
        <link rel='stylesheet' type="text/css" href="../css/opcional.css">
        <script type="text/javascript" src="../js/bootstrap.min.js"></script>
        <script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="../js/bootstrap.min.js"></script>
        <script>
            // /^[a-zAZ0-9_\.\-]+@[a-zA-Z0-9\-]+
            
            $(document).ready(function () {
                $("#benviar").click(function () {
                    var nombre = $("#names").val();
                    var ape = $("#apes").val();
                    var calle = $("#calles").val();
                    var colo = $("#colos").val();
                    var tele = $("#teles").val();
                    var cp = $("#cps").val();
                    if (!(/^([A-Z\a-z\s]*)$/.test(nombre))) {
                        document.form1.names.focus();
                        alert("Nombre invalido solo puedes utilizar espacios y letras");
                        return false;
                    }
                    if (!(/^([A-Z\a-z\s]*)$/.test(ape))) {
                        document.form1.apes.focus();
                        alert("apellido invalido solo puedes utilizar espacios y letras");
                        return false;
                    }
                    if (!(/^([A-Z\a-z\s]*)\#([0-9\s]*)$/.test(calle))) {
                        document.form1.calles.focus();
                        alert("calle invalido Puedes utilizar el siguiente formato calle # 102");
                        return false;
                    }
                    if (!(/^([A-Z\a-z\s]*)$/.test(colo))) {
                        document.form1.colos.focus();
                        alert("colonia invalida invalido solo puedes utilizar espacios y letras");
                        return false;
                    }
                    if (!(/^\d\d\d\d\d\d\d\d\d\d$/.test(tele))) {
                        document.form1.teles.focus();
                        alert("telefono invalido!.Coloca tu telefono sin espacios y 10 digitos");
                        return false;
                    }
                    if (!(/^\d\d\d\d\d$/.test(cp))) {
                        document.form.cps.focus();
                        alert("codigo postal invalido. utiliza el siguiente formato con 5 digitos 37220");
                        return false;
                    }
                    
                });
                
                 $("#benviar0").click(function () {
                    var cant = $("#cantis").val();
                    if (!(/^([1-9]+)([0-9]*)$/.test(cant))) {
                        document.form.cantis.focus();
                        //alert("Porfavor coloca la canidad de productos que deseas");
                        return false;
                    }
                });

            });
        </script>
        <script type="text/javascript">
            function valida_nom() {
                var texto = document.form1.names.value;
                if (!(/^([A-Z\a-z]+)$/i.test(texto))) {
                    alert("nombre invalido! ");
                    response.sendRedirect("../index.jsp");
                    document.form1.names.focus();
                    return false;
                } else
                    return true;
            }
            function valida_calle() {
                valor = document.form1.apes.value;
                if (!(/^([A-Z\a-z]+)$/i.test(valor))) {
                    alert("nombresdf invalido! ");
                    document.form1.apes.focus();
                    return false;
                } else
                    return true;
            }

        </script>
    </head>
    <body>
        <div class="container-fluid">
<nav class="navbar navbar-default">
                <div class="navbar-header">
                    <a class="navbar-brand" href="../index.jsp"><img src="../images/home.png" class="" width="25"></a>
                </div>
                <ul class="nav navbar-nav">
                    <%
                    if(tipos.equals("ADMIN")){
                    %>
                    <li class="dropdown ">
                        <a  class="dropdown-toggle" data-toggle="dropdown" href="#80">
                            Usuarios<span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" id="#90" role="menu">
                            <li class="index.jsp"><a href="">Usuarios</a></li>
                            <li><a href="virtuales.jsp">Vista de direcciones IP</a></li>
                            <li><a href="reporteusuarios.jsp">Reporte de usuarios</a></li>
                        </ul>
                    </li>
                    <%
                    }
                    %>
                    <li class=""><a href="productos_admint.jsp">Productos</a></li>
                    <%
                    if(tipos.equals("ADMIN") || tipos.equals("AMECANICA") || tipos.equals("AATH")){
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
                    <li class="active"><a href="Utilidades_Donacionest.jsp">Nueva Compra Interna</a></li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#80">
                            Reportes <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" id="#80" role="menu">
                            <li ><a href="Ver_ventast.jsp">Ver ventas</a></li>
                            <li ><a href="Ver_entradast.jsp">Ver Entradas</a></li>
                            <%if(tipos.equals("ADMIN")){%>
                            <li><a href="reporte.jsp">reporte productos</a></li>
                            <%}%>
                            
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
            <hr> <br>
            <div class="row" >
                <div class="row espas-search-prods">
                    <div class="col-sm-4">
                        <input type="text" id="catalogo" placeholder="Busqueda de productos" class="form-control" onkeypress="to_searchprod()"> 
                    </div>                    
                </div>
                <div class="espacio1">
                    <div class="col-lg-8 espas" id="get_catalogo">
                        <%
                            try {
                                String nom="";
                                DBt uDB = new DBt();
                                Connection c;
                                uDB.abrir();
                                Statement smt;
                                ResultSet rs;
                                c = uDB.getConexion();
                                String sentenciaSQL = "";
                                    if(tipos.equals("ADMIN")){
                                        sentenciaSQL="SELECT * FROM producto where stock != 0 and status='Y' and (tipo_producto='SISTEMAS' or tipo_producto='ETIQUETAS') ORDER BY nombre";
                                    }else if(tipos.equals("APLASTISOL")){ sentenciaSQL="SELECT * FROM producto where stock != 0 and status='Y' and tipo_producto='PLASTISOL'  ORDER BY nombre";
                                    } else if  (tipos.equals("AMECANICA")){
                                        sentenciaSQL = "SELECT * FROM producto where tipo_producto='MECANICA' ORDER BY nombre";
                                    }else sentenciaSQL = "SELECT * FROM producto where tipo_producto='ATH' and stock != 0 and status='Y' ORDER BY nombre";
                                //System.out.println(sentenciaSQL);
                                smt = c.createStatement();
                                rs = smt.executeQuery(sentenciaSQL);
                                    while(rs.next()){
                                    out.println("<div class=col-md-4>");
                                    out.println("<div class=>");
                                    out.println("<div class=thumbnail azul style=width:70% height:70% >");
                                    out.println("<h4 class=h4 align=center>" + rs.getObject("nombre")+ "</h4>");
                                    out.println("<h4 class=h4 align=center>" + rs.getObject("modelo")+ "</h4>");
                                    out.println("<a class=btn name=id value=" + rs.getObject("id_producto") + " onclick= mostrarVentanas(" + rs.getObject("ID_PRODUCTO") + ")><img width=60% height=60% class=img-responsive  src=" + rs.getObject("imagen") + "></a>");
                                    out.println("<div align=center>");
                                    out.println("<h4 class=h4 align=cente>stock</h4>");
                                    out.println("<input type=text name=prec class=form-control input-sm chat-input placeholder=$  value=" + rs.getObject("stock") + " disabled=disabled> ");  
                                    out.println(" </div></div></div></div>");
                                   // out.println("<input type=submit class=form-control name=id value=" + rs.getObject("id_producto") + " onclick= mostrarVentana(" + rs.getObject("id_producto") + ")></div></div></div></div>");
                                    nom="";
                                    } 
                                }           
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        %>
                    </div> 
                </div>
                <div class="espacio" ><div class="col-md-offset-8 espascompra "  style="overflow: auto">
                        <h1 class="h1" align="center">Compra Interna</h1><hr>
                        <table class="table table-responsive form-login ">
                            <thead class=""><tr>
                                    <td>nombre</td>
                                    <td>cantidad</td>
                                    <td>costo</td>
                                    <td>Borrar</td>
                                </tr></thead>
                            <%
                                
                                float total = 0;
                                int cont = 0;
                                int aux = 1;
                                
                                if (lista.isEmpty()) {
                                    System.out.print("no hay datos" + lista.size());
                                } else {
                                    for (int i = 0; i < lista.size(); i++) {
                                        if (cont == 3) {
                                            out.println("<tr>");
                                            out.println("<td>" + lista.get(i - 2) + "</td>");
                                            out.println("<td>" + lista.get(i - 1) + "</td>");
                                            out.println("<td>" + lista.get(i) + "</td>");
                                            out.println("<td><a class=btn name=erase value=" + lista.get(i - 3) + " onclick=erase("+aux+")><img src=../images/delete.png class=img-responsive width=30 height=30></a></td>");
                                            //out.println("<td><input type=submit class=form-control name=erase value=" + lista.get(i - 3) + " onclick=eliminar(" + aux + ") ></td>");
                                            out.println("</tr>");
                                            //System.out.println(lista.get(i - 3) + "-" +lista.get(i - 2) + "-" + lista.get(i - 1) + "-" + lista.get(i));
                                            total = total + Float.parseFloat(lista.get(i).toString());
                                            cont = 0;
                                            aux++;
                                        } else {
                                            cont++;
                                        }
                                    }
                                }
                            %>
                        </table>
                    </div>
                    <div class="row ">
                        <div class="col-sm-offset-8 espascompra">
                            <h3 class="h3" style="padding-left: 30px">Total:  <%=total%></h3>
                        </div>
                        <div class="row ">
                            <div class="col-sm-offset-8 " align="center">      
                                    <input type="submit" name="aceptar" class="btn btn-danger" onclick="mostrar()" value="Realizar Utilidad">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            function to_searchprod() {
                var catalogo = $('#catalogo').val();
                var uso = "catalogo";
                $.ajax({
                    type: 'post',
                    data: {p: catalogo, uso: uso},
                    url: '../Getregs',
                    success: function (result) {
                        $('#get_catalogo').html(result);
                    }
                });
            }
            function erase(id)
            {
                window.location.href = "../BorrarCarrot?eliminar="+id;
            }
            function carrito(id)
            {
                window.location.href = "?total=" + id;
            }
            function tomaralcarro()
            {
                var cant = $("#cantis").val();
                    if (!(/^([1-9]+)([0-9]*)$/.test(cant))) {
                        document.form.cantis.focus();
                        alert("Porfavor coloca la cantidad de productos que deseas");
                        return false;
                    }else{
                        var ids = $("#benviar0").val();
                var canti = $("#cantis").val();
                
                window.location.href = "../Utilidadest?ids="+ids+"&cant="+canti+"&enviar=7";
                    }
            } 
            function mostrarVentanas(id)
            {
                document.getElementById("benviar0").value = id;
                document.getElementById("cant1").value = id;
                var ventana = document.getElementById("miVentana");
                ventana.style.marginTop = "100px";
                //ventana.style.left = ((document.body.clientWidth) / 2) +  "px";
                ventana.style.display = "block";
                ventana.style.left = 40 + "%";
                //document.getElementById("cant1").focus();
                document.getElementById("cantis").focus();
            }
            function ocultarVentanas()
            {
                var ventana = document.getElementById("miVentana");
                ventana.style.display = "none";
            }      
            function mostrar()
            {  
                var ventana = document.getElementById("miVen");
                ventana.style.marginTop = "10%";
                //ventana.style.left = ((document.body.clientWidth) / 2) +  "px";
                ventana.style.display = "block";
                ventana.style.left = 35 + "%";
                document.getElementById("tipos").focus();
            }
            function ocultars(){
                var vent=document.getElementById("miVen");
                vent.style.display="none";
    }
            function resultado(total)
            {
               var pago = $("#cobro").val();
               var totaltotal=total-pago;
               if(totaltotal <0){
                   totaltotal=totaltotal *(-1);
               }
               document.getElementById("cambio").value = totaltotal;
            }
                function ru() {
                var pro = $('#tipos').val();
                $.ajax({
                    type: 'post',
                    data: {id: pro},
                    url: '../Getregs1',
                    success: function (result) {
                       document.getElementById("idu").value=result;
                       document.getElementById("idu").focus();
                    }
                });
            }
            function salto_carro(){}              
        </script>
        <div id="miVentana" style="position: fixed; width: 25%; height: 30%; top: 0; left: 0; font-family:Verdana, Arial, Helvetica, sans-serif; font-size: 12px; font-weight: normal; background-color: #FAFAFA; color: white; display:none;">
            <div class="row " style="background-color: #616185;padding-left: 2%;padding-right: 2%;border-radius: 10px;">
                <a class="btn" onclick="ocultarVentanas()"><img src="../images/right.png" width="50" height="50"></a>
               <form action="../Vent" method="get" name="form">
                    <div class=" modal fade modal-content"></div>
                    <h5 class="h5" align="center">ID del producto</h5>
                    <input type=text name=ids id="cant1" class="form-control input-sm chat-input" placeholder="Id del producto" disabled="disabled"> <br>
                    <h5 class="h5" align="center">Cantidad a comprar</h5>
                    <input type=text name=cant id="cantis"  class="form-control input-sm chat-input" onchange="tomaralcarro()" placeholder=Cantidad required> <br><br>
                    <div align="center" >
                       
<a class="btn" name="envio" onclick="tomaralcarro()" id="benviar0"><img   class="img-responsive"  src="../images/ok.png" width=75 height=75></a>
                </form>
            </div>
        </div>
            </div>
        
        <div id="miVen" class="modal-header" style="position: fixed; width: 35%; height: 30%; top: 0; left: 0; font-family:Verdana, Arial, Helvetica, sans-serif; font-size: 12px; font-weight: normal;color:white; display:none;">
            <div class="row " style="background-color: #616185;padding-left: 1%;padding-right: 1%;border-radius: 10px;">
                <a class="btn" onclick="ocultars()"><img src="../images/right.png" width="50" height="50"></a>
               <form action="../Nuevavtat2" method="post" name="form">
                    <div class=" modal fade modal-content"></div>
                    <h5 class="h5" align="center">ID del usuario</h5>
                    <input type=text name=idu id="idu" class="form-control input-sm chat-input" placeholder="Id del Usuario" required> <br>
                    
                    Usuario: <select class=" text-capitalize" name="tipos" id="tipos" style="color:black" onchange="ru()">
                <%
                            try {
                                DBt uDB = new DBt();
                                Connection c;
                                uDB.abrir();
                                Statement smt;
                                ResultSet rs;
                                c = uDB.getConexion();
                                String sentenciaSQL = "";
                                if(tipos.equals("ADMIN")||tipos.equals("AATH")){
                                sentenciaSQL="SELECT * from usuario where activo = 'Y' order by usuario";
                                }else if(tipos.equals("APLASTISOL")){ sentenciaSQL ="SELECT u.usuario as 'usuario' from usuario u join departamento d on d.ID_DEP = u.ID_DEP"
                                        + "  where u.activo = 'Y' and d.nombre ='ALMACEN GENERAL'  order by usuario ";
                                }else sentenciaSQL ="SELECT u.usuario as 'usuario' from usuario u join departamento d on d.ID_DEP = u.ID_DEP"
                                        + "  where u.activo = 'Y' and d.nombre ='MECANICA'  order by usuario ";
                                //System.out.println(sentenciaSQL);
                                smt = c.createStatement();
                                rs = smt.executeQuery(sentenciaSQL);
                                out.println("<option></option>");
                                    while(rs.next()){
                                    usuarios=rs.getObject("usuario").toString();
                                    out.println("<option Onchange=ru()>"+rs.getObject("usuario")+"</option>");
                                    }    
                                }
                            catch (Exception e) {
                                e.printStackTrace();
                            }          
                        %>
                    </select><br><div align="center" >
                        <br><br><input type="submit" class="btn btn-success" name="" value="Completar"><br><br><h4></h4>
  </div>
                </form>
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