<%-- 
    Document   : prods
    Created on : 16-oct-2016, 16:50:08
    Author     : mich
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="Persistencia.DB"%>
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
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Proveedores</title>
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
        
                <script>
            // /^[a-zAZ0-9_\.\-]+@[a-zA-Z0-9\-]+
            $(document).ready(function () {
                $("#nuevoprov").click(function () {
                    var nombre = $("#name").val();
                     var numeros =$("#numeros").val();
                    var calle = $("#calles").val();
                    var colo = $("#cols").val();
                    var tele = $("#tels").val();
                  
                   
                    if (!(/^([A-Z\a-z\_\ñ]+)$/.test(nombre))) {
                        document.formr.name.focus();
                        alert("Nombre invalido solo puedes utilizar letras y guien bajo");
                        return false;
                    }
                    if (!(/^([A-Z\a-z\_\ñ]+)$/.test(calle))) {
                        document.formr.calles.focus();
                        alert("calle invalida ingresa solo letras y guion bajo para especificar espacios");
                        return false;
                    }
                    
                    if (!(/^([A-Z\a-z\_\ñ]+)$/.test(colo))) {
                        document.formr.cols.focus();
                        alert("colonia invalida invalido solo puedes utilizar letras y guion bajo para especificar espacios");
                        return false;
                    }
                    if (!(/^\d\d\d\d\d\d\d\d\d\d$/.test(tele))) {
                        document.formr.tels.focus();
                        alert("telefono invalido!.Coloca tu telefono sin espacios y 10 digitos");
                        return false;
                    }
                    if (!(/^([0-9]+)$/.test(numeros))) {
                        document.formr.numeros.focus();
                        alert("Introduce solo numeros");
                        return false;
                    }
                   
                    
                });
                
                 $("#aceptar1").click(function () {
                    var idprov = $("#id_producto_prov").val();
                    var cant = $("#cant").val();
                    var costo = $("#costo").val();
                    var idprovv = $("#id_proveedor_valor").val();
                    
                    if (!(/^([0-9]+)$/.test(cant))) {
                        document.formn.cant.focus();
                        alert("Porfavor ingresa solo numeros para la cantidad a comprar");
                        return false;
                    }
                    if (!(/^([0-9]+)$/.test(idprov))) {
                        document.formn.id_producto_prov.focus();
                        alert("Porfavor coloca el id de algun producto que estan en la base de registro");
                        return false;
                    }
                     if (!(/^([0-9]+)$/.test(idprovv))) {
                        document.formn.id_proveedor_valor.focus();
                        alert("Porfavor coloca el id de algun proveedor que estan en la base de registro");
                        return false;
                    }
                     if (!(/^([1-9]+)([0-9]*)|([1-9]+)|([0-9]*)\.([0-9]*)$/.test(costo))) {
                        document.formn.costo.focus();
                        alert("Ingresa solo numeros");
                        return false;
                    }
                    
                });
                
                    $("#benviar1").click(function () {
                    var nombre = $("#n").val();
                     var calle =$("#t").val();
                    var colonia = $("#c").val();
                    var tele = $("#col").val();
                    
                    if (!(/^([A-Z\a-z\_]+)$/.test(nombre))) {
                        document.form15.n.focus();
                        alert("Nombre invalido solo puedes utilizar letras y guion bajo");
                        return false;
                    }
                    if (!(/^([A-Z\a-z\_]+)([0-9]+)$/.test(calle))) {
                        document.form15.t.focus();
                        alert("calle invalida ingresa solo letras y guion bajo para especificar espacios");
                        return false;
                    }
                    if (!(/^([A-Z\a-z\_]+)$/.test(colonia))) {
                        document.form15.c.focus();
                        alert("colonia invalida invalido solo puedes utilizar letras y guion bajo para especificar espacios");
                        return false;
                    }
                    if (!(/^\d\d\d\d\d\d\d\d\d\d$/.test(tele))) {
                        document.form15.col.focus();
                        alert("telefono invalido!.Coloca tu telefono sin espacios y 10 digitos");
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
      <li class="active"><a href="">Proveedores</a></li>
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
                <div class="col-sm-5 ">
                    <h3 class="h3" align="center">Nuevo proveedor</h3>
                    <form name="formr" action="../Nuevoproveedor" class="form-login esp1">
                    Nombre<input class="form-control input-sm chat-input" type="text" name="name" id="name" value=""  required/><br>
                    Calle
                    <div class="col-md-12">
                        <div class="col-md-5">
                        <input type=text name=calle id="calles" class="form-control input-sm chat-input" placeholder="" required> <br>
                    </div><div class="col-md-1">
                        <big><big><big>
                        <p>#</p></big></big></big>
                    </div>
                        <div class="col-md-4">
                        
                        <input type=text name=numeros id="numeros" class="form-control input-sm chat-input" placeholder="" required> <br>
                    
                    </div>
                        
                    </div>
                    <br >
                    Colonia<input class="form-control input-sm chat-input" type="text" name="col" id="cols" value=""  required/><br>
                    Telefono<input class="form-control input-sm chat-input" type="text" name="tel" id="tels" value=""  required/><br>
                    
                    <input type="submit" value="Aceptar" name="nuevoprov" id="nuevoprov" class="btn btn-success"/>
                    <br><br>${oka}
                        ${error}
                    </form>
                </div>
                    <div class="col-sm-7  esp1 ">
                        <h3 class="h3" align="center">Realizar nueva compra</h3>
                    <form name="formn" action="../Compraprove" class=" col-sm-offset-1">
                        <div class="col-xs-6">
                    ID del producto<input class="form-control input-sm chat-input" type="text" name="id_producto_prov" id="id_producto_prov" value=""  required/><br>
                    Nombre prod:<select name="tipoprod" onchange="regresarprod(<%=idprodu%>,<%=stoc%>)" required>
                        <%        
                            try {
                                DB uDB = new DB();
                                Connection c;

                                uDB.abrir();
                                Statement smt;
                                ResultSet rs;
                                c = uDB.getConexion();
                                //String sentenciaSQL = "SELECT * FROM producto where tipo != 'UNIDAD'";
                                String sentenciaSQL = "SELECT * FROM producto where tipo != 'UNIDAD'";
                                System.out.println(sentenciaSQL);
                                smt = c.createStatement();
                                rs = smt.executeQuery(sentenciaSQL);

                                //AQUI ME QUEDE!!!!!! :3
                                while (rs.next()) {
                                    idprodu=Integer.parseInt(rs.getObject("id_producto").toString());
                                    stoc=Integer.parseInt(rs.getObject("stock").toString());
                                    
                                out.println("<option onclick=regresarprod("+idprodu+","+stoc+")>"+rs.getObject("Tipo")+","+rs.getObject("nombre")+"</option>");             
                                }
                                uDB.cerrar();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        %>
                        
                        
                    </select>
                    <br> <br>Cantidad<input class="form-control input-sm chat-input" type="text" name="cant" id="cant" value=""  required/><br>
                     Costo x Unidad<input class="form-control input-sm chat-input" type="text" name="costo" id="costo" value="" required /><br>
                     <div align="center"><input type="submit" value="Aceptar" name="botonAceptar" id="aceptar1"  class="btn btn-success"/></div>
                    <br><br>${oka}
                        ${error}
                        </div>
                        <div class="col-xs-6">
                            ID del proveedor<input class="form-control input-sm chat-input" type="text" name="id_proveedor_valor" id="id_proveedor_valor" value=""  required/><br>
                    Nombre proveedor:<select name="tipoprov" onchange="regresarprod1(<%=idprodu%>)">
                        
                         <%        
                            try {
                                DB uDB = new DB();
                                Connection c;

                                uDB.abrir();
                                Statement smt;
                                ResultSet rs;
                                c = uDB.getConexion();
                                //String sentenciaSQL = "SELECT * FROM producto where tipo != 'UNIDAD'";
                                String sentenciaSQL = "SELECT * FROM proveedor ";
                                System.out.println(sentenciaSQL);
                                smt = c.createStatement();
                                rs = smt.executeQuery(sentenciaSQL);

                                //AQUI ME QUEDE!!!!!! :3
                                while (rs.next()) {
                                    idprodu1=Integer.parseInt(rs.getObject("id_proveedor").toString());
                           
                                    
                                out.println("<option onclick=regresarprod1("+idprodu1+")>"+rs.getObject("nombre")+"</option>");             
                                }
                                uDB.cerrar();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        %>
                       
                    </select>
                        </div>
                    
                    
                    <br><br>${okas}
                        ${errors}
                    </form>
          
                    </div>
            </div>
                    
                    <div class="row" style="padding: 5%;overflow:auto" >
                        <table class="table table-responsive" >
                            <tr>
                                <td>nombre</td>
                                <td>calle</td>
                                <td>colonia</td>
                                <td>Telefono</td>
                                <td>borrar</td>
                                <td>modificar</td>
                            </tr>
  <%
      char[] arr;
          String calles="";
          String nombres="";
          String cols="";
    try {
        
DB uDB = new DB();
   Connection c;
        uDB.abrir();
        Statement smt;
        ResultSet rs;
        c = uDB.getConexion();
        String sentenciaSQL = "SELECT * FROM proveedor ORDER BY nombre";
        System.out.println(sentenciaSQL);        
        smt = c.createStatement();
        rs = smt.executeQuery(sentenciaSQL);
   
        //AQUI ME QUEDE!!!!!! :3
while (rs.next())
      {
int lol=Integer.parseInt(rs.getObject("id_proveedor").toString());
String nomb=rs.getObject("nombre").toString();
String tel =rs.getObject("tel").toString();
String calle =rs.getObject("calle").toString();
String col=rs.getObject("colonia").toString();

              
   out.println("<tr>");
  
   out.println("<td>"+rs.getObject("nombre")+"</td>");
   out.println("<td>"+calle+"</td>");
   out.println("<td>"+col+"</td>");
    out.println("<td>"+tel+"</td>");
   out.println("<td><a class=btn name=borrar  value="+lol+" onclick=eliminar("+lol+")><img src=../images/delete.png class=img-responsive width=30 height=30></a></td>");
   out.println("<td><a class=btn name=modi  value="+lol+" onclick=mostrarVentana1("+rs.getObject("id_proveedor").toString()+",'"+nomb+"','"+calle+"','"+col+"','"+tel+"')><img src=../images/modificar.png class=img-responsive width=30 height=30></a></td>");
   out.println("</tr>");
   calles="";
   cols="";
   tel="";
   nombres="";
      }
 uDB.cerrar();
    }catch (Exception e)
      {
         e.printStackTrace();
      }
    calles="";
           nombres="";
          cols="";
%>
                        </table>
                        
                    </div>
        
                        <script>
                        function regresarprod(id,s){
                            document.getElementById("id_producto_prov").value = id;
                        }
                        function regresarprod1(id){
                            document.getElementById("id_proveedor_valor").value = id;
                           
                        }
                        function eliminar(id){
                            window.location.href = "../Borrarproveedor?borrar=" + id;
                        }
                         function modi(id){
                            
                        }
                        function mostrarVentana1(id,n,t,c,col)
            {
               
                document.getElementById("id").value = id;
                document.getElementById("benviar1").value = id;
                document.getElementById("n").value = n;
                document.getElementById("t").value = t;
                document.getElementById("c").value = c;
                document.getElementById("col").value = col;
                var ventana = document.getElementById("miVentana1");
                ventana.style.marginTop = "30px";
                //ventana.style.left = ((document.body.clientWidth) / 2) +  "px";
                ventana.style.display = "block";
                ventana.style.left = 10 + "%";
            }
            function ocultarVentana1()
            {
                var ventana = document.getElementById("miVentana1");
                ventana.style.display = "none";
            }
            function agregarmodificacion(){
               var benviar1= $("#id").val();
               var n= $("#n").val();             
               var calle= $("#t").val();
               var colo= $("#c").val();
               var tel= $("#col").val();
              
              if(n!="" || calle!="" || colo!= "" || tel!= ""){
                  if (!(/^([A-Z\a-z\_]+)$/.test(n)) || !(/^([A-Z\a-z\_]+)([0-9]+)$/.test(calle)) || !(/^([A-Z\a-z\_]+)$/.test(colo)) || !(/^\d\d\d\d\d\d\d\d\d\d$/.test(tel))) {
                        
                        return false;
                    }else{
                        window.location.href = "../Modificarprovedor?benviar="+benviar1+"&n="+n+"&calle="+calle+"&colo="+colo+"&tel="+tel;
                    }
                  
              }else{
                  alert("No puedes modificar un proveedor con campos vacios");
              }
                
                //benviar1=7&n=BOLILLO&p=2&calle=1.5&t=491&tipos=SI
            }
                        </script>
                        <div id="miVentana1" class="modal-scrollbar-measure" style="position: fixed; width: 80%; height: 70%; top: 0; left: 0; font-family:Verdana, Arial, Helvetica, sans-serif; font-size: 5px; font-weight: normal;color: white; display:none;">
    <div class="">      
    <div class="" style="background-color: #616185;padding-left: 2%;padding-right: 2%;border-radius: 10px;">
        <a class="btn" onclick="ocultarVentana1()"><img src="../images/der.png"></a> 
                <form action="../Modificarprovedor" onsubmit="" name="form15"  style="">
                    <div class=" modal fade modal-content "></div>
                    <h5 class="h5" align="center">ID del proveedor</h5>
                    <input type="text" name="id" id="id" class="form-control input-sm chat-input" placeholder=""  disabled="disabled"> <br>
                    <h5 class="h5" align="center">Nombre</h5>
                    <input type=text name="n" id="n"  class="form-control input-sm chat-input" placeholder="" required> <br>
                    <h5 class="h5" align="center">Calle</h5>
                    <input type=text name=t id="t" class="form-control input-sm chat-input" placeholder="" required> <br>
                    <h5 class="h5" align="center">Colonia</h5>
                    <input type=text name=c id="c" class="form-control input-sm chat-input" placeholder="" required> <br>
                    <h5 class="h5" align="center">telefono</h5>
                    <input type=text name=col id="col" class="form-control input-sm chat-input" placeholder="" required> <br>
                    <div align="center">
                        <a class=" btn alert-success" name="benviar" id="benviar1" onclick="agregarmodificacion()"><img src="../images/modificar.png" class="img-responsive" width=30 height=30></a>
                        <br><br></div>

                </form>
            </div>
</div>  
        </div>
               </div>         
    </body>
</html>
