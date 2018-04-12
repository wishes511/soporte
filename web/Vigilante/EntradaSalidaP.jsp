<%-- 
    Document   : EntradaSalida
    Created on : 19-ago-2017, 12:51:49
    Author     : mich
--%>
<%
    HttpSession objSesion = request.getSession(false);

    // out.println("" + tipos+"/"+ids);
    int idprodu = 0;
    int idprodu1 = 0;
    String tipo = "";
    int stoc = 0;


%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ingreso al Sistema</title>
        <link rel="shortcut icon" type="image/x-icon" href="../prov.ico" />
        <link rel="stylesheet" type="text/css" href="../css/fondos.css">
        <link rel="stylesheet" type="text/css" href="../css/loginn.css">
        <link rel="stylesheet" type="text/css" href="../css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="../css/opcional.css">
        <script type="text/javascript" src="../js/bootstrap.js"></script>
        <script type="text/javascript" src="../js/bootstrap.min.js"></script>
        <script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="../js/bootstrap.js"></script>
        <script type="text/javascript" src="../js/bootstrap.min.js"></script>


        <script type="text/javascript">
            $(document).ready(function () {
                document.getElementById("pos").focus();
            });
                    
            function valida_envia() {
                valor = document.form.nombrelog.value;
                if (!(/^([0-9]+)$/i.test(valor))) {
                    alert("codigo invalido! ");
                    document.form.nombrelog.focus();
                    return 0;
                }

            }
        </script>
        <script type="text/javascript">
            function okasi() {
                var pro = $('#pos').val();

                $.ajax({
                    type: 'post',
                    data: {bueno: pro},
                    url: '../NuevaES',
                    success: function (result) {
                        // document.form2.uso.focus();
                        //alert("-"+result+"-");
                        if(result==="ZIGMA"){
                        $("#bodies").css("background-image", 'linear-gradient(rgb(153, 153, 255), rgb(255, 255, 255))');
                        }else if(result ==="INTERPACK"){
                        $("#bodies").css("background-image", 'linear-gradient(rgb(237,8,52), rgb(255, 255, 255))');
                        }else{
                        $("#bodies").css("background-image", 'linear-gradient(rgb(73, 180, 37), rgb(255, 255, 255))');    
                        }        

                        $('#res').html(result);
                        document.getElementById("pos").value = "";

                    }
                });
                
            }
        </script>
    </head>
    <body class="body3" id="bodies">
        <br><br>

        <div align="center">

            <a href="../" class=""><img src="../images/right.png" alt="" width="70px" align="left" /></a>                
            <a href="" class=""><img src="../images/ok.png" alt="" width="40px" align="right" /></a>                
            <div class="container" align="center">
                <div class="row" >
                    <div class="col-md-offset-4 col-md-4" >
                        <div style="padding-top: 50%">
                            <h4 id="pru">Entrada y Salida</h4>
                            <input type=text name="pos" id="pos" class="form-control input-sm chat-input" placeholder="Codigo proveedor" onchange="okasi()" maxlength="14" /><br>
                            <div></div>  
                        </div>
                    </div>
                </div>
            </div>
            <h3 id="result1"></h3>
            <h3 id="res"></h3>
        </div>
        <div style="padding-top: 20%">
            <br>
        </div>

    </body>
</html>
