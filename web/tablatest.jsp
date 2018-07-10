<%-- 
    Document   : tablatest
    Created on : Jul 5, 2018, 1:30:28 PM
    Author     : gateway1
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
        <title>JSP Page</title>
       
    </head>
    <body>
        <script>
            function averdato(){
                
                var total=document.getElementsByTagName("td").length;
                for(var i =0;i<total;i++){
                    var variable=document.getElementsByTagName("td")[i].innerHTML; 
                    $.ajax({
                    type: 'post',
                    data: {v: variable},
                    url: 'Tablaprueba',
                    success: function (result) {
                         //alert(variable);
                    }
                });   
                   
                }
                
                
            }
        </script>
        <%
        HttpSession objSesion = request.getSession(false);
    ArrayList<Object> venta = (ArrayList<Object>) objSesion.getAttribute("venta");
    if(venta!= null){
        
       // ArrayList<String> arr = new ArrayList<>();
        //arr=venta;
        out.println("<label>"+venta.get(0)+"</label>");
    }else{
        System.out.println("aver");
    }
        %>
        <table class="table table-hover table-bordered " border="1" width="600" style="max-width: 500px" cellspacing="3" >
            <thead>
                <tr>
                    <th>25</th>
                    <th>25.5</th>
                    <th>26</th>
                    <th>26.5</th>
                    <th>27</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td contenteditable=""></td>
                    <td contenteditable=""></td>
                    <td contenteditable=""></td>
                    <td contenteditable=""></td>
                    <td contenteditable="" onclick="averdato()"></td>
                </tr>
            </tbody>
        </table>

    </body>
</html>
