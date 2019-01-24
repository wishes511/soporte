<%-- 
    Document   : Log
    Created on : 19-ago-2016, 12:51:49
    Author     : mich
--%>
<%
    HttpSession objSesion = request.getSession(false);
    String usuario = (String) objSesion.getAttribute("usuario");
    String tipos = (String) objSesion.getAttribute("tipo");
    String ids = String.valueOf(objSesion.getAttribute("i_d"));

    // out.println("" + tipos+"/"+ids);
    int idprodu = 0;
    int idprodu1 = 0;
    String tipo = "";
    int stoc = 0;
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ingreso al Sistema</title>
        <link rel="shortcut icon" type="image/x-icon" href="supp.ico" />
        <link rel="stylesheet" type="text/css" href="css/fondos.css">
        <link rel="stylesheet" type="text/css" href="css/loginn.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="css/opcional.css">
        <script type="text/javascript">
            function valida_envia() {
                

                valor = document.form.contrasenalog.value;
                if (!(/^([A-Z\a-z]+)$/i.test(valor))) {
                    alert("Contraseña invalida! ");
                    document.form.contrasenalog.focus();
                    return 0;
                }
            }
        </script>
    </head>
    <body class="" style="background-image: linear-gradient(rgb(255, 255, 255), rgb(153, 153, 255)); background-repeat: no-repeat">
        <div align="middle"><img src="images/AF.png" class="img-responsive"/></div>
        <br>

        <div align="center">
            <%
                if (usuario != null && tipos != null && (tipos.equals("ADMIN")||tipos.equals("AATH"))) {
                    out.println("<div class=container-fluid align=left> ");

                    out.println("<div class=row>");
                    out.println("<form action=admin/home_admin.jsp> ");
                    out.println("<label>Conectado como:</label>");
                    out.println("<input type=submit value=" + usuario + " class=btn btn-default>");
                    out.println("</form>");
                    out.println("</div>");
                    out.println("</div>");

                } else {

                }
            %>
            <div class="container-fluid" align="center">
                <div class="container" style="padding-bottom: 50px">
                    <div class="row" >
                        <div class="col-lg-offset-4 col-lg-4" >
                            <div style="padding-top: 20%">


                                <div class="form-login" >

                                    <h3 class="h3" >Login</h3>

                                    <form name="logiing" action="Validart" method="POST" class="form-login ">
                                        <input  type="text" id="nombrelog" class="form-control input-sm chat-input" name="nombrelog" placeholder="username" onsubmit="valida_envia()" required/>
                                        <br>
                                        <input type="password" id="contrasenalog" class="form-control input-sm chat-input" name="contrasenalog" placeholder="password" onsubmit="valida_envia()" required/>
                                        <br>
                                        <div class="wrapper">
                                            <span class="group-btn">     
                                                <input type="submit" value="Entrar" class="btn btn-default navbar-btn" />
                                            </span>
                                            <br>
                                            <label style="font-size:50; font-family: monospace;">
                                                ${error}                      
                                            </label>
                                        </div> </form>
                                </div>
                            </div>
                        </div>
                    </div>    
                </div>   
                <div class="row">
                    <div style="background-color:#F0F0F0;" >
                        <ul class="nav navbar-nav" style="background-color:#F0F0F0;" >
                            <li class="active"><a href="cotizacion.htm">Cotizacion Etiq</a></li>
                            <li ><a href="Vigilante/EntradaSalidaP.jsp">Entrada / Salidas</a></li>
                            <li ><a href="Pistola/">Pistola</a></li>
                        </ul>
                    </div>  
                </div>
            </div>
        </div>
    </body>
</html>
