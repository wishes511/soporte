<%-- 
    Document   : depcosto
    Created on : Sep 15, 2017, 9:51:16 AM
    Author     : gateway1
--%>

<%@page import="net.sf.jasperreports.engine.JasperRunManager"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.io.File"%>
<%@page import="Persistencia.DBt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
DBt db = new DBt();
db.abrir();
try{   
  System.out.println(db.getConexion());
     File reportfile = new File (application.getRealPath("admin/costodep.jasper"));
     Map para = new HashMap(); 
        String f1 = request.getParameter("f1");
        String f2 = request.getParameter("f2");
        para.put("f1",new String(f1));
        para.put("f2",new String(f2));
        byte[] bytes = JasperRunManager.runReportToPdf(reportfile.getPath(), para, db.getConexion());
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outputstream = response.getOutputStream();
        outputstream.write(bytes,0,bytes.length);
        
        outputstream.flush();
        outputstream.close();
}catch(Exception e){

e.printStackTrace();

}finally{
if(db.getConexion()!= null){
    db.cerrar();
}
}
        %>
     </body>
            
</html>
