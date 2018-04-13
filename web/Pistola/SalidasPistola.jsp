<%-- 
    Document   : EntradasPistola
    Created on : Apr 5, 2017, 8:58:14 AM
    Author     : gateway1
--%>
<%@page import="Modelo.codigopersis"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.pistola_cod"%>
<%
    int total;
    String cods="";
    codigopersis cp = new codigopersis();
    
pistola_cod pd = new pistola_cod();
   int j =0; 
   ArrayList<Object> lista = new ArrayList<Object>();
   
   lista=pd.getCod();
   if(lista.isEmpty()){
   System.out.println("Nandemo");
   }else{
       System.out.println(":)");
   for(int i = 0;i<lista.size();i++){
       if(j==2){
       System.out.println(cods+"$"+lista.get(i-2));    
       cods=cods + lista.get(i-2)+"\n";
       j=0;
       }else{
       j++;
       }
   }
   }
   total=lista.size()/3;
   
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../css/bootstrap.css">
        <link rel='stylesheet' type="text/css" href="../css/bootstrap.min.css">
        <link rel='stylesheet' type="text/css" href="../css/responsive.css">
        <link rel="stylesheet" type="text/css" href="../css/fondos.css">
        <link rel="stylesheet" type="text/css" href="../css/loginn.css">
        <script type="text/javascript" src="../js/bootstrap.js"></script>
        <script type="text/javascript" src="../js/bootstrap.min.js"></script>
        <script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="../js/bootstrap.min.js"></script>
        <%
        if(!lista.isEmpty()){
        out.println("<script>");
        out.println("$(document).ready(function () {");
        out.println("document.form2.in.focus();}")                                                                                                                                                                                                                                                                                                                                                                                      ;
        out.println("</script>");
        }
        
        
        %>
        <title>Salidas</title>
    </head>
    <body class="body2">
        <div class="container-fluid">
            <a href="../"><input type="submit" class="btn btn-toolbar" value="Inicio"></a>
            <div class="row"  align="middle" style="margin: 0 auto;"> 
                <div class="col-md-offset-3">
                    <div class="col-xs-2">
                    <div class="row">
                        <form action="../SalidaPistola" method="post" name="form2">
                            <div align="center" >
                                <br>
                          <label>Almacen</label>
                          <input type="text" class="form-control input-sm chat-input"  name="alm" id="alm" size="10" required value="<%=cp.getAlmacen()%>">
                              <hr>
                            <label>Factor</label><br>
                            <label>1</label> 
                            <%
                            if(cp.isFactor()==true){
                            out.println("<input type=radio name=factor id=factor value=1 checked=checked/>");
                            }else{
                            out.println("<input type=radio name=factor id=factor value=1 />");
                            }

                            %>
                          <br>
                            
                            <label>6</label>    
                           
                            <%
                            
                            if(cp.isFactor()==false){
                            out.println("<input type=radio name=factor id=factor value=1 checked=checked/>");
                            }else{
                            out.println("<input type=radio name=factor id=factor value=6 />");
                            }

                            %>
                            <input type="text" class="form-control input-sm chat-input" required name="in" id="in" maxlength="13">
                            <br>
                            <input type="submit" value="a" style="display:none;">
                            </div> 
                        </form>
                    </div>
                </div>
                <div class=" col-xs-5" style="padding-left: 20px">
                    <div class="row">
                        <h5 class="h5" align="center">Codigos</h5>
                        <textarea id="codigos" name="codigos"  class="form-control input-sm chat-input" rows="9"  required disabled="disabled" STYLE="font-size: 8px"><%=cods%></textarea>
                        <label>Folio</label><input type="text" name="folio" id="folio" class="form-control input-sm chat-input" style="font-size: 9px" value="${folios}">    
                        <label>Pares</label><input type="text" name="pares" id="pares" class="form-control input-sm chat-input" style="font-size: 9px" value="<%=total%>">
                        <br><br>
                        <input type="submit" class="btn btn-success" onclick="mostrarmodal()" value="nueva salida">
                        <input type="submit" class="btn btn-danger">
                    </div>
                </div>
                </div>
            </div>
        </div>
        <script>
            function mostrarmodal()
            {  
                var ventana = document.getElementById("mymodal");
                ventana.style.marginTop = "10%";
                //ventana.style.left = ((document.body.clientWidth) / 2) +  "px";
                ventana.style.display = "block";
                ventana.style.left = 35 + "%";
            }
            function ocultar(){
                var vent=document.getElementById("mymodal")
                vent.style.display="none"
                
    }
            function cod(){
            window.location.href = "../NuevaSalida?salida=true";
            } 
        </script>
        
        <div id="mymodal" class="modal-header" style="position: fixed; width: 40%; height: 30%; top: 0; left: 0; font-family:Verdana, Arial, Helvetica, sans-serif; font-size: 12px; font-weight: normal;color:white; display:none;">
            <div class="row " style="background-color: #616185;padding-left: 1%;padding-right: 1%;border-radius: 10px;" >
               <a onclick="ocultar()"><img src="../images/der.png"></a>
               <form action="NuevaSalida" method="get" name="form" align="middle">                   
                            <label>Stock</label> <input type="radio" name="Tipo" id="Tipo" value="Stock" checked="checked" /><br>
                            <label>Pedido</label> <input type="radio" name="Tipo" id="Tipo" value="Pedido" />                              
                   
                        <br><br><input type="submit" class="btn btn-success" name="" value="Completar"><br><br>
                </form>
                        </div>
                
            </div>
       
    </body>
</html>
