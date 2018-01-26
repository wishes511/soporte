<%-- 
    Document   : home_admin
    Created on : 16-oct-2016, 16:50:08
    Author     : mich
--%>

<%@page import="java.util.Calendar"%>
<%@page import="Persistencia.DBt"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Persistencia.DB"%>
<%@page import="java.sql.Connection"%>
<% HttpSession objSesion = request.getSession(false);
//i_d
    String usuario = (String) objSesion.getAttribute("usuario");
    String tipos = (String) objSesion.getAttribute("tipo");
    String ids = String.valueOf(objSesion.getAttribute("i_d"));

   // out.println("" + tipos+"/"+ids);
    int idprodu=0;
    int idprodu1=0;
    String tipo="";
    int stoc=0;

    if (usuario != null && tipos != null && tipos.equals("ADMIN")) {    
    } else {
        response.sendRedirect("../index.jsp");
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
        <title>Tareas</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
           <script>
            // /^[a-zAZ0-9_\.\-]+@[a-zA-Z0-9\-]+
            $(document).ready(function () {
                $("#benviar").click(function () {

                    var uso = $("#descripcion").val();
                    //var passs = $("#passs").val();
                    if (!(/^([A-Z\a-z\ñ\0-9\,\(\)\.\+)$/.test(uso))) {
                        document.form2.descripcion.focus();
                        alert("Usuario invalido solo puedes utilizar letras");
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
      <li class=""><a href="home_admin.jsp">Usuarios</a></li>
      <li ><a href="productos_admint.jsp">Productos</a></li>
      
      <li><a href="proveedores.jsp">Proveedores</a></li>
        <li><a href="Utilidades_Donacionest.jsp">Nueva Compra Interna</a></li>
            <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown" href="#80">
        Reportes <span class="caret"></span>
      </a>
      <ul class="dropdown-menu" id="#80" role="menu">

        <li><a href="Ver_ventast.jsp">Ver ventas</a></li>
      </ul>
    </li>
    <li class="active"><a href="">Tareas</a></li>
      <li><a href="../CierreSesion">Salir</a></li>
    </ul>

</nav>
            <div class="row">
                <div class="col-md-3 ">
                    <h3 class="h3" align="center">Nueva Tarea</h3>
                    
                    <form  action="../Nuevotareat" method="post" class="form-login esp1" name="form2">
                    Tipo de tarea<select name="tipos" class="form-control">
               
               <option>COMPRA</option>
               <option>ENCARGO</option>
               <option>SOPORTE</option>
               <option>MANTENIMIENTO</option>
               <option>ENTREGA</option>
                    </select><br>
                    Descripcion<textarea class="form-control input-sm chat-input" type="text" name="descripcion" id="descripcion" value="" required rows="10" maxlength="500" draggable=""/></textarea><br>
                    
                    <input type="submit" value="Aceptar" name="benviar" id="benviar" class="btn btn-success "/>
                    <br><br>${oka}
                        ${error}
                    </form>
                </div>
                    <div class="col-md-8">
                        <h3 class="h3" align="center">Tareas pendientes y realizadas</h3>
                <div class="col-lg-offset-1 esp1" style="overflow: auto">
                  
                    <table class="table table-responsive " >
                        <thead><tr>
                        
                        <td>Tipo</td>
                        <td>Descripcion</td>
                       
                        <td>Fecha</td>
                        <td>Status</td>
                        <td>usar</td>
                        
                        
                        </tr></thead>
                        
                                                <%
    try {

        
DBt uDB = new DBt();
   Connection c;

        uDB.abrir();
        Statement smt;
        ResultSet rs;
        c = uDB.getConexion();
        String sentenciaSQL = "SELECT * FROM tareas where status= 'PENDIENTE' OR fecha='"+fechac+"' ORDER BY fecha";
             
        smt = c.createStatement();
        rs = smt.executeQuery(sentenciaSQL);
   
while (rs.next())
      {
          char[] arr;
          String calles="";
          String nombress="";
String id=(rs.getObject("id_tarea").toString());

   out.println("<tr>");
   out.println("<td>"+rs.getObject("tipo")+"</td>");
   out.println("<td>"+rs.getObject("descripcion")+"</td>");
   out.println("<td>"+rs.getObject("fecha")+"</td>");
   out.println("<td>"+rs.getObject("status")+"</td>");
if(rs.getObject("status").equals("PENDIENTE")){
    out.println("<td><a name=borrar value="+id+" onclick=modi("+id+") class=btn><img src=../images/ok.png  width=30 height=30></a></td>");
  }else {
    out.println("<td ><a name=borrar value="+id+" class=btn><img src=../images/okno.png  width=30 height=30 disabled=disabled></a></td>");
  }
   id="";                                          
    %>

  <%

   out.println("</tr>");
      }
 uDB.cerrar();
    }catch (Exception e)
      {
         e.printStackTrace();
      }
%> 
                    </table>
                  
                </div></div>
            </div>
             
                <div id="miVentana1" class="modal-scrollbar-measure" style="position: fixed; width: 50%; height: 80%; top: 0; left: 0; font-family:Verdana, Arial, Helvetica, sans-serif; font-size: 5px; font-weight: normal; background-color: white; color: white; display:none;">
    <div class="">      
    <div class="" style="background-color: #616185;padding-left: 2%;padding-right: 2%;border-radius: 10px;">
        <a onclick="ocultarVentana1()"><img src="../images/der.png"></a>        
        <form action="" onsubmit="" name="form10"  style="">
                    <div class=" modal fade modal-content "></div>
                    <h5 class="h5" align="center">Nombre del proveedor</h5>
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
                       function modi(id){
                window.location.href = "../Conftareat?modis="+id;
                           
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
           
                        
                    </script>
                    
                    
    </body>
</html>
