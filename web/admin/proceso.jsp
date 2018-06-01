<%@page import="Persistencia.DBt"%>
<%@page import="Modelo.productot"%>
<%@ page import="java.util.List"%>
<%@ page import="org.apache.commons.fileupload.FileItemFactory"%>
<%@ page import="org.apache.commons.fileupload.FileItem"%>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@ page import="java.io.File" %>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>

<% HttpSession objSesion = request.getSession(false);
//i_d
    String usuario = (String) objSesion.getAttribute("usuario");
    String tipos = (String) objSesion.getAttribute("tipo");
    String ids = String.valueOf(objSesion.getAttribute("i_d"));

    if (usuario != null && tipos != null && (tipos.equals("ADMIN")) || tipos.equals("APLASTISOL") || tipos.equals("AMECANICA")) {

    } else {
        response.sendRedirect("../index.jsp");
    }
%>

<% 
    DBt udb = new DBt();
    String omg = "";
    String valor = "";
    String arr[] = new String[10];
    productot p = new productot();
    String ruta = "", rutabd = "";
    try {
        FileItemFactory file_factory = new DiskFileItemFactory();
        ServletFileUpload servlet_up = new ServletFileUpload(file_factory);
        List items = servlet_up.parseRequest(request);
        HashMap<String, String> parametros = new HashMap<String, String>();
        for (int i = 0; i < items.size(); i++) {
            FileItem item = (FileItem) items.get(i);
            if (item.isFormField()) {
                arr[i] = item.getString();
                omg += item.getString() + "[" + i + "]'";
            } else {
                //C:\Users\gateway1\Documents\NetBeansProjects\soporte
                String ite="";
                for(int j=0;j<item.getName().length();j++){
                    if(item.getName().charAt(j)==' '){
                        ite+="_";
                    }else{ ite+=item.getName().charAt(j);}
                }
                valor = (new Date().getTime()) + ite;
                
               ruta= getServletContext().getRealPath("");
               // ruta = "C:/Users/gateway1/Documents/NetBeansProjects/soporte/web/imagesbd/" + valor;
                
                File archivo_server = new File(ruta+"/imagesbd/"+valor);
                System.out.println("valor : "+valor);
                item.write(archivo_server);
                
                rutabd = "../imagesbd/" + valor;
            }
            parametros.put(item.getFieldName().toLowerCase(), valor);
        }
        //System.out.println(arr[0]+"/"+arr[1]+"/"+arr[2]+"/"+arr[3]+"/"+arr[4]+"/"+arr[5]+"/"+arr[6]);
        p.setNombre(arr[0].toUpperCase());
        p.setModelo(arr[1].toUpperCase());
        p.setMarca(arr[2].toUpperCase());
        p.setStock(Integer.parseInt(arr[4]));
        p.setCosto(Double.parseDouble(arr[3]));
        p.setStatus("Y");
        p.setDescripcion(arr[5].toUpperCase());
        p.setTipo(arr[6].toUpperCase());
        p.setUrl(rutabd);
     //  out.println(nombre + "," + modelo + "," + marca + "," + costo + "," + stock + "," + ruta + "/" + rutabd);
  udb.agregarprodt(p);
        // udb.agregarproducto(p);
        request.setAttribute("oka", "Producto guardado exitosamente");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/productos_admint.jsp");
        rd.include(request, response);
        //response.sendRedirect("/admin/productos_admin.jsp");
    } catch (Exception e) {
        System.out.println(e);
        request.setAttribute("error", "Problemas con el servidor" );
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/productos_admint.jsp");
        rd.include(request, response);
    }
%>
