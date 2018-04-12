<%-- 
    Document   : reporte
    Created on : Jul 25, 2017, 12:35:06 PM
    Author     : gateway1
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="Persistencia.DBt"%>
<%@page contentType="application/pdf"%>
<%@page trimDirectiveWhitespaces="true"%>
<%@page import="net.sf.jasperreports.engine.*"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.SQLException"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" type="image/x-icon" href="../supp.ico" />
        <title>Usuarios actuales</title>
    </head>
    <body>
<% //select p.modelo,SUM(d.cantidad),MONTH(f.fecha) from factura f join detalle_fact d on d.ID_FACTURA = f.ID_FACTURA join producto p on d.ID_PRODUCTO = p.ID_PRODUCTO group by  MONTH(f.fecha),p.modelo order by MONTH(f.fecha)
DBt db = new DBt();
db.abrir();
try{   
  System.out.println(db.getConexion());
     File reportfile = new File (application.getRealPath("admin/usuario_listado.jasper"));
        
        Map<String,Object> parameter = new HashMap<String,Object>();
        
        //String valor = request.getParameter("txtparametro");
        //parameter.put("mar",new String(valor));
        
        byte[] bytes = JasperRunManager.runReportToPdf(reportfile.getPath(), parameter, db.getConexion());
        
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outputstream = response.getOutputStream();
        outputstream.write(bytes,0,bytes.length);
        
        outputstream.flush();
        outputstream.close();
    /*
String jrxm =session.getServletContext().getRealPath("admin/Reporte.jrxml");
System.out.print(""+jrxm);
InputStream input =new FileInputStream(new File(jrxm));
// generando reporte :v?
JasperReport jasperReport = JasperCompileManager.compileReport(input);
JasperPrint jasperprint = JasperFillManager.fillReport(jasperReport,null ,db.getConexion());
JasperExportManager.exportReportToPdfStream(jasperprint, response.getOutputStream());
response.getOutputStream().flush();
response.getOutputStream().close();
*/
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
