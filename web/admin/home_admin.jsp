<%-- 
    Document   : home_admin
    Created on : 16-jun-2017, 16:50:08
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
    String tipos = (String) objSesion.getAttribute("tipo");
    ArrayList<Object> carrito;
    carrito = (ArrayList<Object>) objSesion.getAttribute("carro");
    //out.print(carrito.size());
    //out.println("" + tipos+"/"+ids);
   if (usuario != null && tipos != null && tipos.equals("ADMIN")) {
       
    } else {
        response.sendRedirect("../index.jsp");
    }
    try{
    
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
        <script type="text/javascript" src="http://librosweb.es/ejemplos/bootstrap_3/js/bootstrap.min.js"></script>
        <script>
            // /^[a-zAZ0-9_\.\-]+@[a-zA-Z0-9\-]+
            $(document).ready(function () {
            $("#benviar").click(function () {
            var nombres = $("#names").val();
            var calles = $("#calles").val();
            var uso = $("#uso").val();
            //var passs = $("#passs").val();
            if (!(/^([A-Z\a-z\ñ\ ]+)$/.test(uso))) {
            document.form2.uso.focus();
            alert("Usuario invalido solo puedes utilizar letras");
            return false;
            }

            if (!(/^([A-Z\a-z\ \ñ]+)$/.test(nombres))) {
            document.form2.names.focus();
            alert("Nombre invalido solo puedes utilizar letras o puedes utilizar guien bajo(_) para espacios");
            return false;
            }

            if (!(/^([A-Z\a-z\ \ñ]+)$/.test(calles))) {
            document.form2.calles.focus();
            alert("Apellido invalido usar solo letras y espacios");
            return false;
            }
            });
            $("#benviar1").click(function () {
            var id = $("#id").val();
            var n = $("#n").val();
            var p = $("#p").val();
            var calle = $("#calle").val();
            var t = $("#t").val();
            if (!(/^([A-Z\a-z\ ]+)$/.test(n))) {
            document.form10.n.focus();
            alert("Nombre invalido solo puedes utilizar letras o puedes utilizar guien bajo( _ ) para espacios");
            return false;
            }

            if (!(/^([A-Z\a-z\_]+)([0-9]+)$/.test(calle))) {
            document.form10.calle.focus();
            alert("calle invalida Puedes utilizar el siguiente formato: calle_102 o calle102");
            return false;
            }

            if (!(/^\d\d\d\d\d\d\d\d\d\d$/.test(t))) {
            document.form10.t.focus();
            alert("telefono invalido!.Coloca tu telefono sin espacios y 10 digitos");
            return false;
            }
            });
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
                    <li class="dropdown active">
                        <a  class="dropdown-toggle" data-toggle="dropdown" href="#80">
                            Usuarios<span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" id="#90" role="menu">

                     <li class="active"><a href="">Usuarios</a></li>
                    <li><a href="virtuales.jsp">Vista de direcciones IP</a></li>
                    <li><a href="reporteusuarios.jsp">Reporte de usuarios</a></li>
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

                    <h2 class="h2" align="center">Nuevo Usuario</h2>
                    <div class="form-login esp1" >
                        ${oka}
                        ${error}<br>
                        <form  action="../Nuevousuariot" method="post" name="form2"><big>
                            <label>Usuario</label><input class="form-control input-sm chat-input" type="text" name="uso" id="uso" value=""  required/><br>
                            <label>Nombre</label><input class="form-control input-sm chat-input" type="text" name="names" id="names" value=""  required/><br>
                            <label>apellido</label><input class="form-control input-sm chat-input" type="text" name="calles" id="calles" value=""  required/><br>
                            <label>Contraseña</label><input class="form-control input-sm chat-input" type="text" name="passs" id="passs" value="" required /><br>
                            <label>Ip</label><input class="form-control input-sm chat-input" type="text" name="ips" id="ips" value="" required /><br>                    
                            <label>Tipo de usuario :</label> <select name="tipos" >
                                <option>USUARIO</option>
                                <option>ADMIN</option>
                            </select><br><br>
                            <label>Departamento : </label> <select name="tips" id="tips">

                                <%
                                    try {
                                        DBt uDB = new DBt();
                                        Connection c;

                                        uDB.abrir();
                                        Statement smt;
                                        ResultSet rs;
                                        c = uDB.getConexion();
                                        String sentenciaSQL = "SELECT * FROM departamento ORDER BY nombre";

                                        smt = c.createStatement();
                                        rs = smt.executeQuery(sentenciaSQL);

                                        while (rs.next()) {
                                            out.println("<option>" + rs.getObject("Nombre") + "</option>");
                                        }
                                    } catch (Exception a) {
                                    }
                                %>
                            </select><br><br><br>
                            <input type="submit" value="Aceptar" name="benviar" id="benviar" class="btn btn-success textoboton"/>
                       </big> </form>
                        <br>    <input type="submit" type="submit" Onclick="mostrarVentana()" class="btn btn-primary textoboton" value="Nuevo departamento">
                        <br><br>
                    </div>
                    <br>
                    <big><div id="result1" style="background-color: rgb(51, 122, 183);color: white" class="jumbis" align="middle"></div></big>
                </div>
                <div class="col-sm-7">
                    <h3 class="h3" align="center">Vista general de usuarios</h3>
                    <div class="col-sm-offset-1 esp1" style="overflow: auto">

                        <table class="table table-responsive ">
                            <thead><tr>

                                    <td>Usuario</td>
                                    <td>Nombre</td>
                                    <td>IP</td>
                                    <td>Tipo</td>
                                    <td>Borrar</td>
                                    <td>modificar</td>
                                    <td>baja</td>
                                </tr></thead>

                            <%
                                try {

                                    DBt uDB = new DBt();
                                    Connection c;
                                    uDB.abrir();
                                    Statement smt;
                                    ResultSet rs;
                                    c = uDB.getConexion();
                                    String sentenciaSQL = "SELECT * FROM usuario where activo= 'Y' ORDER BY usuario";
                                    smt = c.createStatement();
                                    rs = smt.executeQuery(sentenciaSQL);

                                    while (rs.next()) {
                                        char[] arr;
                                        String calles = "";
                                        String nombress = "";
                                        int id = Integer.parseInt(rs.getObject("ID_USUARIO").toString());
                                        String n = rs.getObject("nombre").toString();
                                        String pas = rs.getObject("usuario").toString();
                                        String calle = rs.getObject("tipo").toString();
                                        arr = calle.toCharArray();
                                        out.println("<font size=3>");
                                        out.println("<tr>");
                                        out.println("<td>" + rs.getObject("usuario") + "</td>");
                                        out.println("<td>" + rs.getObject("nombre") + "</td>");
                                        out.println("<td>" + rs.getObject("ip") + "</td>");
                                        out.println("<td>" + rs.getObject("tipo") + "</td>");
                                        out.println("<td><a name=borrar value=" + id + " onclick=eliminar(" + id + ") class=btn><img src=../images/delete.png  width=30 height=30></a></td>");
                            %>
                            <td><a name="modf" value="<%=id%>" onclick=modi(<%=id%>) class="btn"><img src="../images/modificar.png" class="s" width=30 height=30></a>
                            <td><a name="baj" value="<%=id%>" onclick=baja(<%=id%>) class="btn"><img src="../images/down.png" class="" width=30 height=30></a>

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
            </div>
            <div class="row">
                <div class="col-sm-12" style="overflow: auto">
                    <br><br><br>
                    <h3 class="h3" align="center">Usuarios en suspension</h3>
                    <table class="table table-responsive ">
                        <thead><tr>
                                <td>Usuario</td>
                                <td>Nombre</td>
                                <td>IP</td>
                                <td>Tipo</td>
                                <td>Borrar</td>
                                <td>alta</td>
                            </tr></thead>

                        <%
                            try {

                                DBt uDB = new DBt();
                                Connection c;

                                uDB.abrir();
                                Statement smt;
                                ResultSet rs;
                                c = uDB.getConexion();
                                String sentenciaSQL = "SELECT * FROM usuario where activo= 'N' ORDER BY nombre";

                                smt = c.createStatement();
                                rs = smt.executeQuery(sentenciaSQL);

                                while (rs.next()) {
                                    char[] arr;
                                    String calles = "";
                                    String nombress = "";
                                    int id = Integer.parseInt(rs.getObject("ID_USUARIO").toString());
                                    String n = rs.getObject("nombre").toString();
                                    String pas = rs.getObject("usuario").toString();
                                    String calle = rs.getObject("tipo").toString();

                                    arr = calle.toCharArray();

                                    out.println("<tr>");
                                    out.println("<td>" + rs.getObject("usuario") + "</td>");
                                    out.println("<td>" + rs.getObject("nombre") + "</td>");
                                    out.println("<td>" + rs.getObject("ip") + "</td>");
                                    out.println("<td>" + rs.getObject("tipo") + "</td>");
                                    out.println("<td><a name=borrar value=" + id + " onclick=eliminar(" + id + ") class=btn><img src=../images/delete.png class= width=30 height=30></a></td>");
                        %>
                        <td><a name="baj" value="<%=id%>" onclick=alta(<%=id%>) class="btn"><img src="../images/up.png" class="" width=30 height=30></a>

                            <%
                                        out.println("</tr>");
                                    }
                                    uDB.cerrar();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            %> 
                            </div>    
                            </div>


                            <div id="miVentana1" class="modal-scrollbar-measure" style="position: fixed; width: 50%; height: 80%; top: 0; left: 0; font-family:Verdana, Arial, Helvetica, sans-serif; font-size: 5px; font-weight: normal; background-color: white; color: white; display:none;">
                                <div class="">      
                                    <div class="" style="background-color: #616185;padding-left: 2%;padding-right: 2%;border-radius: 10px;">
                                        <a onclick="ocultarVentana1()"><img src="../images/der.png"></a>        
                                        <form action="../Modificarusu" onsubmit="" name="form10"  style="">
                                            <div class=" modal fade modal-content "></div>
                                            <h5 class="h5" align="center">ID del Empleado</h5>
                                            <input type="text" name="id" id="id" class="form-control input-sm chat-input" placeholder=""  disabled="disabled" required> <br>
                                            <h5 class="h5" align="center">Nombre</h5>
                                            <input type="text" name="n" id="n"  class="form-control input-sm chat-input" placeholder="" required> <br>
                                            <h5 class="h5" align="center">Contraseña</h5>
                                            <input type=text name=p id="p" class="form-control input-sm chat-input" placeholder="" required> <br>
                                            <h5 class="h5" align="center">Calle</h5>
                                            <input type=text name=calle id="calle" class="form-control input-sm chat-input" placeholder="" required> <br>
                                            <h5 class="h5" align="center">Telefono</h5>
                                            <input type=text name=t id="t" class="form-control input-sm chat-input" placeholder="" required> <br>
                                            <h4 class="h4">  Tipo de usuario : <select name="tipos" style="color: gray" id="tipos">
                                                    <option>PANADERO</option>
                                                    <option>USUARIO</option>
                                                    <option>ADMIN</option>
                                                </select></h4>
                                            <div align="center"><a class=" btn alert-success" name="benviar1" id="benviar1" onclick="agregarmodificacion()" value=""><img src="../images/modificar.png" class="img-responsive" width=30 height=30></a><br><br></div>

                                        </form>
                                    </div>
                                </div>  
                            </div>    

                            </div>

                            <script>
                                function baja(id){

                                window.location.href = "../Bajausut?baji=" + id + "&uso=baja";
                                }
                                function alta(id){

                                window.location.href = "../Bajausut?baji=" + id + "&uso=alta";
                                }

                                function modi(id){
                                window.location.href = "Editarusu.jsp?modis=" + id;
                                }
                                function mostrarVentana1(id)
                                {
                                document.getElementById("id").value = id;
                                document.getElementById("benviar1").value = id;
                                window.location.href = "../Borrarusuario?borrar=" + id;
                                }
                                function ocultarVentana1()
                                {
                                var ventana = document.getElementById("miVentana1");
                                ventana.style.display = "none";
                                }
                                function eliminar(id)
                                {
                                window.location.href = "../Borrarusuariot?borrar=" + id;
                                }
                                function mostrarVentana()
                                {

                                var ventana = document.getElementById("miVentana");
                                ventana.style.marginTop = "200px";
                                //ventana.style.left = ((document.body.clientWidth) / 2) +  "px";
                                ventana.style.display = "block";
                                ventana.style.left = 40 + "%";
                                document.form5.pos.focus();
                                }
                                function ocultarVentana()
                                {
                                var ventana = document.getElementById("miVentana");
                                ventana.style.display = "none";
                                }
                            </script>
                            <script type="text/javascript">
                                function okas(){
                                var pro = $('#pos').val();
                                var pro1 = $('#marcas').val();
                                $.ajax({
                                type:'post',
                                        data: {bueno:pro, marca:pro1},
                                        url:'../Nuevodepa',
                                        success: function(result){
                                        document.form2.uso.focus();
                                        $('#result1').fadeIn(1500);
                                        $('#result1').html('Exito al guardar el departamento');
                                        $('#result1').fadeOut(3000);
                                        $('#tips').html(result)
                                                ocultarVentana();
                                        }
                                });
                                }


                            </script>

                            <div id="miVentana" style="position: fixed; width: 25%; height: 10%; top: 0; left: 0; font-family:Verdana, Arial, Helvetica, sans-serif; font-size: 12px; font-weight: normal; background-color: #FAFAFA; color: white; display:none;">
                                <div class="row " style="background-color: #616185;padding-left: 2%;padding-right: 2%;border-radius: 10px;">
                                    <a onclick="ocultarVentana()"><img src="../images/der.png"></a>

                                    <div class=" modal fade modal-content"></div>

                                    <h5 class="h5" align="center">Nombre del departamento</h5>
                                    
                                        <input type=text name="pos" id="pos" class="form-control input-sm chat-input" placeholder="Nombre del depa" Onchange="okas()"> <br>
                                   
                                    <div align="center" >
                                        EMPRESA:<select style="color:black" name="marcas" id="marcas">
                                            <option>ATHLETIC</option>
                                            <option>FATIMA</option>
                                        </select>
                                        <br><br>
                                        <input type="submit" name="blabla" id="blabla" class="btn btn-success" onclick="okas()">   
                                    </div>

                                </div>
                            </div>     
                            </body>
                            </html>
<%
}catch(Exception e){ 
    System.out.println(e);
}
%>