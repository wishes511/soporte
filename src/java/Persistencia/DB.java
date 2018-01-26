/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;


import Modelo.Caja;
import Modelo.Cliente;
import Modelo.Compra_prod;
import Modelo.Producion;
import Modelo.Producto;
import Modelo.Proveedor;
import Modelo.Usuario;
import Modelo.venta;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author
 */
public class DB {

    private String URL = "jdbc:mysql://localhost:3306/morales";
    private String drive = "com.mysql.jdbc.Driver";
    private Connection conexion;

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public void abrir() throws ClassNotFoundException, SQLException {
        Class.forName(drive);
        conexion = DriverManager.getConnection(URL,"root","Wishespe5");
    }

    public void cerrar() throws SQLException {
        conexion.close();

    }

    public void nuevaventa(venta v){
    
    }
    
    public ArrayList<Object> retornombrepro(String f1,String f2) throws ClassNotFoundException, SQLException{
     ArrayList<Object> lista = new ArrayList<Object>();
         Connection c;
    
        Statement smt;
        ResultSet rs;
        Usuario u = null;
        DB uDB = new DB();
        uDB.abrir();
        c = uDB.getConexion();
        String query="select c.id_compraprod,p.nombre from producto p JOIN compra_producto c ON p.id_producto =c.id_producto  where fecha BETWEEN '"+f1+"' AND '"+f2+"' ORDER BY p.nombre";
        System.out.println(query);
       smt = c.createStatement();
        rs = smt.executeQuery(query);
         while (rs.next()) {
            //total=total+ Float.parseFloat(rs.getString("monto"));
            lista.add(rs.getString("c.id_compraprod"));
        }
         
         smt.close();
         return lista;
    }
    
    public ArrayList<Object> retornoidventa(String f1,String f2) throws ClassNotFoundException, SQLException{
    ArrayList<Object> lista = new ArrayList<Object>();
         Connection c;
    
        Statement smt;
        ResultSet rs;
        Usuario u = null;
        DB uDB = new DB();
        uDB.abrir();
        c = uDB.getConexion();
        String query="SELECT id_venta from venta where fecha BETWEEN '"+f1+"' AND '"+f2+"' ORDER BY id_venta";
        System.out.println(query);
       smt = c.createStatement();
        rs = smt.executeQuery(query);
         while (rs.next()) {
            //total=total+ Float.parseFloat(rs.getString("monto"));
            lista.add(Integer.parseInt(rs.getString("id_venta").toString()));
        }
         
         smt.close();
         return lista;
    }
    
    public void agregaringresoauto(String fecha) throws ClassNotFoundException, SQLException{
     Connection c;
    
        Statement smt;
        ResultSet rs;
        float total=0;
        String flotante="";
        
        int id=0;

        Usuario u = null;
        DB uDB = new DB();
        uDB.abrir();
        c = uDB.getConexion();
        String query="SELECT SUM(total) as total FROM venta where fecha='"+fecha+"'";
        System.out.println(query);
       smt = c.createStatement();
        rs = smt.executeQuery(query);
         while (rs.next()) {
            total=total+ Float.parseFloat(rs.getString("total"));
        }if(rs==null){
            System.out.println("ENtro al nulo de ventas ");
        total=0;
        }
         System.out.println("total: "+total);
         query="insert into ingresos values("+id+",'VENTAS',"+total+",'"+fecha+"')";
         System.out.println(query);
         smt = c.createStatement();
         smt.executeUpdate(query);
        System.out.println("insercion ingreso");
        total=0;
        
         query="SELECT SUM(ganancia) as ganancia FROM production where fecha='"+fecha+"'";
        System.out.println(query);
       smt = c.createStatement();
        rs = smt.executeQuery(query);
         while (rs.next()) {
             if(rs==null){
                 System.out.println("nulo ");
             total=total+ Float.parseFloat(rs.getString("ganancia"));
             }else{
             System.out.println("o.o");
             }
            
        }
        
         System.out.println("total: "+total);
         query="insert into Gastos values("+id+",'SUELDOS DE PRODUCCIÓN',"+total+",'"+fecha+"')";
         System.out.println(query);
         smt = c.createStatement();
         smt.executeUpdate(query);
        System.out.println("insercion gasto");
                total=0;
                
         query="SELECT SUM(costo) as costo FROM compra_producto where fecha='"+fecha+"'";
        System.out.println(query);
       smt = c.createStatement();
        rs = smt.executeQuery(query);
         while (rs.next()) {
            if(rs==null){
                 System.out.println("nulo ");
             total=total+ Float.parseFloat(rs.getString("costo"));
             }else{
             System.out.println("o.o");
             }
        }
         
         System.out.println("total: "+total);
         query="insert into Gastos values("+id+",'COMPRA PROVEEDOR',"+total+",'"+fecha+"')";
         System.out.println(query);
         smt = c.createStatement();
         smt.executeUpdate(query);
        System.out.println("insercion gasto proveedor");
        smt.close();
        rs.close();
    }
    
    public ArrayList<Object> retornodatos(String fecha) throws ClassNotFoundException, SQLException{
        ArrayList<Object> lista = new ArrayList<Object>();
         Connection c;
    
        Statement smt;
        ResultSet rs;
        float total=0;
        
        int contables=0;

        Usuario u = null;
        DB uDB = new DB();
        uDB.abrir();
        c = uDB.getConexion();
        String query="select MAX(id_ingreso),monto from ingresos where fecha='"+fecha+"'";
        System.out.println(query);
       smt = c.createStatement();
        rs = smt.executeQuery(query);
         while (rs.next()) {
             
            total=total+ Float.parseFloat(rs.getString("monto"));
        }
         lista.add(total);
         System.out.println("total: "+total);
         total=0;
        
         query="select sum(monto) as monto from gastos where fecha='"+fecha+"'";
        System.out.println(query);
       smt = c.createStatement();
        rs = smt.executeQuery(query);
         while (rs.next()) {
            total=total+ Float.parseFloat(rs.getString("monto"));
        }
         lista.add(total);
         System.out.println("total: "+total);
         total=0;
         
         query="select SUM(cantidad) as cantidad from venta where fecha='"+fecha+"'";
        System.out.println(query);
       smt = c.createStatement();
        rs = smt.executeQuery(query);
         while (rs.next()) {
            contables=contables+ Integer.parseInt(rs.getString("cantidad"));
        }
         lista.add(contables);
         System.out.println("total: "+contables);
         contables=0;
         
         query="select SUM(cantidad) as cantidad from compra_producto where fecha='"+fecha+"'";
        System.out.println(query);
       smt = c.createStatement();
        rs = smt.executeQuery(query);
         while (rs.next()) {
             
             if(rs==null){
                 System.out.println("nulo ");
             contables=contables+ Integer.parseInt(rs.getString("cantidad"));
             }else{
             System.out.println("o.o");
             }
            //contables=contables+ Integer.parseInt(rs.getString("cantidad"));
        }
         lista.add(contables);
         System.out.println("total: "+contables);
         contables=0;
         
         int conta=0;
          query="select id_venta from venta where fecha='"+fecha+"'";
        System.out.println(query);
       smt = c.createStatement();
        rs = smt.executeQuery(query);
         while (rs.next()) {
            conta++;
        }
         lista.add(conta);
         System.out.println("total: "+conta);
         contables=0;
         
         
         query="select SUM(producido) as producido from production where fecha='"+fecha+"'";
        System.out.println(query);
       smt = c.createStatement();
        rs = smt.executeQuery(query);
         while (rs.next()) {
              if(rs==null){
                 System.out.println("nulo ");
             contables=contables+ Integer.parseInt(rs.getString("producido"));
             }else{
             System.out.println("o.o");
             }
           // contables=contables+ Integer.parseInt(rs.getString("producido"));
        }
         lista.add(contables);
         System.out.println("total: "+contables);
         contables=0;
         
         query="select SUM(stock) as stock from producto ";
        System.out.println(query);
       smt = c.createStatement();
        rs = smt.executeQuery(query);
         while (rs.next()) {
            contables=contables+ Integer.parseInt(rs.getString("stock"));
        }
         lista.add(contables);
         System.out.println("total: "+contables);
         
         
        smt.close();
        rs.close();
        return lista;
    }
    
    public ArrayList<Object> retornoingreso(String fi,String ff) throws ClassNotFoundException, SQLException{
    ArrayList<Object> lista = new ArrayList<Object>();
    
    Connection c;
    
        Statement smt;
        ResultSet rs;
        float total=0;
        
        int contables=0;

        Usuario u = null;
        DB uDB = new DB();
        uDB.abrir();
        c = uDB.getConexion();
        String query="select * from ingresos where fecha BETWEEN '"+fi+"' AND '"+ff+"' ORDER BY id_ingreso";
        System.out.println(query);
       smt = c.createStatement();
        rs = smt.executeQuery(query);
         while (rs.next()) {
            lista.add(Integer.parseInt(rs.getString("id_ingreso").toString()));
            lista.add(rs.getString("motivo").toString());
            lista.add(Float.parseFloat(rs.getString("monto").toString()));
            lista.add(rs.getString("fecha").toString());
        }
         lista.add(total);
         System.out.println(lista.get(0)+"/"+lista.get(1)+"/"+lista.get(2)+"/"+lista.get(3));
    
    return lista;
    }
    
    public ArrayList<Object> retornogasto(String fi,String ff) throws ClassNotFoundException, SQLException{
    ArrayList<Object> lista = new ArrayList<Object>();
    
    Connection c;
    
        Statement smt;
        ResultSet rs;
        float total=0;
        
        int contables=0;

        Usuario u = null;
        DB uDB = new DB();
        uDB.abrir();
        c = uDB.getConexion();
        String query="select * from gastos where fecha BETWEEN '"+fi+"' AND '"+ff+"' ORDER BY id_gasto";
        System.out.println(query);
       smt = c.createStatement();
        rs = smt.executeQuery(query);
         while (rs.next()) {
            lista.add(Integer.parseInt(rs.getString("id_gasto").toString()));
            lista.add(rs.getString("motivo").toString());
            lista.add(Float.parseFloat(rs.getString("monto").toString()));
            lista.add(rs.getString("fecha").toString());
        }
         lista.add(total);
         System.out.println(lista.get(0)+"/"+lista.get(1)+"/"+lista.get(2)+"/"+lista.get(3));
    
    return lista;
    }
    
    public void agregar(Usuario u) throws ClassNotFoundException, SQLException {

        Statement smt;
        Connection c;

        abrir();
        int id = 0;
        String nombre = u.getNombre().toUpperCase();
        String contrasena = u.getContrasena();
        String tipo = u.getTipo().toUpperCase();
        String calle=u.getCalle().toUpperCase();
        String tel =u.getTel();
//        Ejecutar la busqueda
        String sentenciaSQL = "INSERT INTO usuario VALUES("
                +id + ",'" + nombre + "',"
                + "'" + calle + "',"
                + "'" + contrasena + "','"+tipo+"','"+tel+"')";

        System.out.print(sentenciaSQL);

        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        cerrar();
    }

    public void agregargasto(String m,float monto,String fecha) throws ClassNotFoundException, SQLException{
     Statement smt;
        Connection c;
        abrir();
        int id = 0;
        
//        Ejecutar la busqueda
        String sentenciaSQL = "INSERT INTO gastos VALUES("
                +id + ",'" + m + "',"
                + "" + monto + ","
                + "'" + fecha + "')";

        System.out.print(sentenciaSQL);

        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        smt.close();
    }
   
    public void agregarprove(Proveedor u) throws ClassNotFoundException, SQLException {
        Statement smt;
        Connection c;
//        Abrir BD
        abrir();
//        Recuperar atributos
        int id = u.getId();
        String nombre = u.getNombre();
//        String calle = u.getCalle();
//        String col = u.getColonia();
//        String tel = u.getTelefono();

//        Ejecutar la busqueda
        String sentenciaSQL = "";
//                "INSERT INTO PROVEEDOR VALUES("
//                + id + ","
//                + "'" + nombre + "',"
//                + "'" + calle + "',"
//                + "'" + col + "','" + tel + "')";

        System.out.print(sentenciaSQL);

        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        cerrar();

    }

    public void agregarproduccion(Producion u) throws ClassNotFoundException, SQLException{
    Statement smt;
        Connection c;

        abrir();
        int id = 0;
        int idusu = u.getId_usuario();
        int idprod = u.getId_producto();
        int producion = u.getProducido();
        float ganancia=u.getGanancia();
        String fecha =u.getFecha();
//        Ejecutar la busqueda
        String sentenciaSQL = "INSERT INTO production VALUES("
                +id + "," + idusu+ ","
                + "" + idprod + ","
                + "" + producion + ","+ganancia+",'"+fecha+"')";

        System.out.print(sentenciaSQL);

        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        smt.close();
    }
    
    public void agregarcliente(Cliente u) throws ClassNotFoundException, SQLException {
        Statement smt;
        Connection c;
//        Abrir BD
        abrir();
        int id=0;
//        Ejecutar la busqueda
        String sentenciaSQL = "insert into cliente values("+id+",'"+u.getNombre()+"','"+u.getApellido()+"','"+u.getCalle()+"','"+u.getColonia()+"','"+u.getTelefono()+"',"+u.getCp()+")";
//                "INSERT INTO CLIENTES VALUES("
//                + id + ","
//                + "'" + nombre + "',"
//                + "'" + calle + "',"
//                + "'" + tele + "','" + rfc + "')";

        System.out.print(sentenciaSQL);

        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        smt.close();
        //cerrar();

    }

    public void agregarcompraproveedor(Compra_prod u) throws ClassNotFoundException, SQLException{
     Statement smt;
        Connection c;
//        Abrir BD
        abrir();
        int id=0;
//        Ejecutar la busqueda
        String sentenciaSQL = "insert into compra_producto values("+id+","
                +u.getId_producto()+","+u.getId_proveedor()+",'"
                +u.getFecha()+"',"+u.getCosto()+","+u.getCantidad()+","+u.getTotal()+")";
        System.out.print(sentenciaSQL);
        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        
        sentenciaSQL = "update producto set stock=stock+"+u.getCantidad()+" where id_producto ="+u.getId_producto();
        System.out.print(sentenciaSQL);
        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        smt.close();
        
    }
    
    public void agregarproveedor(Proveedor u) throws ClassNotFoundException, SQLException{
    Statement smt;
        Connection c;
        abrir();
        int id=0;
        String sentenciaSQL = "insert into proveedor values("+id+",'"+u.getNombre()+"','"+u.getTel()+"','"+u.getCalle()+"','"+u.getColonia()+"')";
        System.out.print(sentenciaSQL);
        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        smt.close();
        
        
    }
    
    public void agregarproductoventa(Producto u) throws ClassNotFoundException, SQLException {

        Statement smt;
        Connection c;
        DB uDB = new DB();
        c = uDB.getConexion();
        ResultSet rs1;
        int idventa = 0;

//        Abrir BD
        abrir();

//        Recuperar atributos
        int id = u.getId();
        String nombre = u.getNombre();
//        int precio = u.getPrecio();
        int stock = u.getStock();

//        Ejecutar la busqueda
        String sentenciaSQL1 = "SELECT * FROM VENTA";
        smt = c.createStatement();
        rs1 = smt.executeQuery(sentenciaSQL1);
        while (rs1.next()) {
            idventa = (Integer.parseInt(rs1.getString("ID_VENTA")));
        }
        uDB.cerrar();

        String sentenciaSQL = "INSERT INTO VENTA_PRODUCTO VALUES("
                + idventa + ","
                + "" + id + ","
                + "" + stock + ")";

        System.out.print(sentenciaSQL);

        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        cerrar();

    }

    public void agregardetalle(int idv,ArrayList lista) throws ClassNotFoundException, SQLException {
        Connection c;
        //Abrir la conexion
        // Hacer la consulta
        // Si encontro el registro, regresar el objeto correspondiente
        // En caso contrario regresar "null"
        Statement smt = null;
        ResultSet rs;
        venta v = new venta();
        int id=0,ids=0;

        DB uDB = new DB();

        //--Abrir BD
        uDB.abrir();
        c = uDB.getConexion();

        //--Ejecutar la busqueda
        String sentenciaSQL;
        int cont =0;
        for(int i =0;i<lista.size();i++){
        if (cont == 3) {
         sentenciaSQL = "insert into detalle_venta values("+ids+","+lista.get(i-3)+","+idv+","+lista.get(i-1)+")"; 
         System.out.println(lista.get(i-3)+","+idv+","+lista.get(i-1));
         smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
       // uDB.cerrar();
         cont=0;
        } else {
        cont++;
        }
        }
        smt.close();
    }

    public void agregardetallebaja(int idp,int ids, int cant,String fecha) throws SQLException, ClassNotFoundException{
        Connection c;
    int id=0;
    DB uDB = new DB();
        uDB.abrir();
        c = uDB.getConexion();
    
        Statement smt;
        String sentenciaSQL = "insert into detalle_baja values("+id+","+idp+","+ids+","+cant+",'"+fecha+"','SI')"; 
    System.out.println(sentenciaSQL);
        smt = c.createStatement();
        smt.executeUpdate(sentenciaSQL);
        
        sentenciaSQL = "update producto set stock=stock-"+cant+" where id_producto="+idp; 
            System.out.println(sentenciaSQL);
        smt = c.createStatement();
        smt.executeUpdate(sentenciaSQL);
        
        smt.close();
    }
    
    public Usuario buscar(String nombre, String contrasena) throws ClassNotFoundException, SQLException {
        Connection c;
        //Abrir la conexion
        // Hacer la consulta
        // Si encontro el registro, regresar el objeto correspondiente
        // En caso contrario regresar "null"
        Statement smt;
        ResultSet rs;

        Usuario u = null;

        DB uDB = new DB();

        //--Abrir BD
        uDB.abrir();
        c = uDB.getConexion();

        //--Ejecutar la busqueda
        String sentenciaSQL = "SELECT * FROM usuario WHERE NOMBRE=" + "'" + nombre + "'"
                + " AND CONTRASENA = " + "'" + contrasena + "'";
        System.out.println(sentenciaSQL);
        smt = c.createStatement();
        rs = smt.executeQuery(sentenciaSQL);

        // Crear el objeto
        while (rs.next()) {
            u = new Usuario();
            u.setId(Integer.parseInt(rs.getString("ID_USUARIO")));
            u.setNombre(rs.getString("NOMBRE"));
            u.setContrasena(rs.getString("CONTRASENA"));
            u.setTipo(rs.getString("TIPO"));
            u.setTel(rs.getString("TEL"));
            System.out.println("/" + u.getTipo() + "/");
        }

        uDB.cerrar();
        return u;

    }
    
    public void modificarprod(Producto p) throws ClassNotFoundException, SQLException{
        Statement smt;
        Connection c;
        abrir();
        String sentenciaSQL = "update producto set nombre='"+p.getNombre()+
                "', costomay="+p.getCostomay()+
                ",costomin="+p.getCostomin()+",costoprod="+p.getCostoprod()+
                ",stock="+p.getStock()+",habilitar='"+p.getHabilitado()+"' where id_producto="+p.getId(); 
        
        System.out.print(sentenciaSQL);

        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        smt.close();
    
    }
    
    public void borrarpedido(int id) throws ClassNotFoundException, SQLException{
        ArrayList<Object> lista = new ArrayList<Object>();
    Connection c;
        Statement smt;
        ResultSet rs;
        int ids=0;

        Usuario u = null;
        DB uDB = new DB();
        uDB.abrir();
        c = uDB.getConexion();
        String query="SELECT id_producto,cantidad from detalle_venta where id_venta="+id;
        System.out.println(query);
       smt = c.createStatement();
        rs = smt.executeQuery(query);
         while (rs.next()) {
            lista.add(Integer.parseInt(rs.getString("id_producto")));
            lista.add(Integer.parseInt(rs.getString("cantidad")));
        }
         int cont =0;
         for(int i =0;i<lista.size();i++){
         if(cont ==1){
            query = "update producto set stock=stock+"+lista.get(i)+" where id_producto="+lista.get(i-1); 
        
        System.out.print(query+",cantidad"+lista.get(i)+","+lista.get(i-1));
        smt = c.createStatement();
        smt.executeUpdate(query);
         }else{
         cont++;
         }
         }
         
          query = "delete from detalle_venta where id_venta="+id; 
        
        System.out.print(query);
        smt = c.createStatement();
        smt.executeUpdate(query);
         
        query = "delete from venta where id_venta="+id; 
        
        System.out.print(query);
        smt = c.createStatement();
        smt.executeUpdate(query);
        
        rs.close();
        smt.close();
        
       
    }
    
    public void modificardetalleprod(int id) throws ClassNotFoundException, SQLException{
    Statement smt;
        Connection c;
        abrir();
        String sentenciaSQL = "update detalle_baja set habilitar='NO' where id_detalleb="+id; 
        
        System.out.print(sentenciaSQL);

        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        smt.close();
    }
    
    public void modificarprod(int ids,int cantidad) throws ClassNotFoundException, SQLException{
    
         Statement smt;
        Connection c;
        abrir();
        String sentenciaSQL = "update producto set stock=stock+"+cantidad+" where id_producto="+ids; 
        
        System.out.print(sentenciaSQL);

        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        smt.close();
    
    }
    
    public void usarpedido(int usuario,int id,String uso) throws ClassNotFoundException, SQLException{
     Statement smt;
        Connection c;
        abrir();
        
        String sentenciaSQL = "update venta set status='PENDIENTE', id_usuario="+usuario+" where id_venta="+id; 
        
        System.out.print(sentenciaSQL);

        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        smt.close();
    }
    
    public void actualizarpedido(int id) throws ClassNotFoundException, SQLException{
     Statement smt;
        Connection c;
        abrir();
        
        String sentenciaSQL = "update venta set status='ENVIADO' where id_venta="+id; 
        
        System.out.print(sentenciaSQL);

        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        smt.close();
    }
    
    public void modificarusu(Usuario p) throws ClassNotFoundException, SQLException{
    Statement smt;
        Connection c;
        abrir();
        String sentenciaSQL = "update usuario set nombre='"+p.getNombre()+
                "', calle='"+p.getCalle()+
                "',contrasena='"+p.getContrasena()+
                "',tipo='"+p.getTipo()+"',tel='"+p.getTel()+"' where id_usuario="+p.getId(); 
        
        System.out.print(sentenciaSQL);

        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        smt.close();
    }
    
    public void modificarstock(ArrayList lista) throws ClassNotFoundException, SQLException{
   Statement smt;
        Connection c;
        abrir();
        int id = 0;
        Producto p;
        int stock =0;
        
        String sentenciaSQL="";
        int cont =0;
        for(int i =0;i<lista.size();i++){
        if (cont == 3) {
        p =new Producto();
        p=buscarproducto(Integer.parseInt(lista.get(i-3).toString()));
        stock = p.getStock()-Integer.parseInt(lista.get(i-1).toString());
        System.out.println("stok:"+stock+"/stock p:"+p.getStock()+"/"+lista.get(i-1));
        // uDB.abrir();
         sentenciaSQL = "update producto set stock="+stock+" where id_producto ="+lista.get(i-3); 
         System.out.println(lista.get(i-3)+"*"+lista.get(i-1));
         smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
       // uDB.cerrar();
         cont=0;
        } else {
        cont++;
        }
        }

        System.out.print(sentenciaSQL);

        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        smt.close();
    
    }
    
    public int buscarcliente() throws ClassNotFoundException, SQLException{
        Connection c;
        Statement smt;
        ResultSet rs;
        int id=0;

        Usuario u = null;
        DB uDB = new DB();
        uDB.abrir();
        c = uDB.getConexion();
        String query="SELECT id_cliente FROM CLIENTE";
        System.out.println(query);
       smt = c.createStatement();
        rs = smt.executeQuery(query);
         while (rs.next()) {
            id=Integer.parseInt(rs.getString("ID_CLIENTE"));
        }
        uDB.cerrar();
        return id;
    }
    
    public void buscarproduccion(String fi,String ff) throws ClassNotFoundException, SQLException{
    Connection c;
    ArrayList<Object> lista;
        Statement smt;
        ResultSet rs;
        int id=0;
        Producion pr = new Producion();

        Usuario u = null;
        DB uDB = new DB();
        uDB.abrir();
        c = uDB.getConexion();
        String query = "SELECT p.nombre,u.nombre,prod.ganancia,prod.producido,prod.fecha FROM producto p JOIN production prod ON p.id_producto =prod.id_producto JOIN usuario u ON u.id_usuario=prod.id_usuario WHERE fecha BETWEEN '"+fi+"' AND '"+ff+"' ORDER BY u.nombre";    
        System.out.println(query);
       smt = c.createStatement();
        rs = smt.executeQuery(query);
         while (rs.next()) {
            pr.setproducion(rs.getObject("u.nombre").toString(), rs.getObject("p.nombre").toString(), Integer.parseInt(rs.getObject("prod.producido").toString()), Float.parseFloat(rs.getObject("prod.ganancia").toString()), rs.getObject("prod.fecha").toString());
            System.out.println("BD "+rs.getObject("u.nombre")+"/" +rs.getObject("p.nombre")+"/"+rs.getObject("prod.producido")+"/"+rs.getObject("prod.ganancia")+"/"+ rs.getObject("prod.fecha"));
        }
        smt.close();
        lista=pr.getProd();
        int cont =0;
       
    }
    
    public void buscarventa(String fi,String ff) throws ClassNotFoundException, SQLException{
        Connection c;
    ArrayList<Object> lista;
        Statement smt;
        ResultSet rs;
        int id=0;
        Producion pr = new Producion();
        venta v = new venta();

        Usuario u = null;
        DB uDB = new DB();
        uDB.abrir();
        c = uDB.getConexion();
        String query = " select * from venta where fecha BETWEEN '"+fi+"' AND '"+ff+"' ORDER BY id_venta ";    
        System.out.println(query);
       smt = c.createStatement();
        rs = smt.executeQuery(query);
         while (rs.next()) {
            v.setventas(Integer.parseInt(rs.getObject("id_venta").toString()),Float.parseFloat(rs.getObject("total").toString()),Integer.parseInt(rs.getObject("cantidad").toString()),rs.getObject("fecha").toString(),rs.getObject("hora").toString());
           
        }
        smt.close();
    }
    
    public Usuario buscarusuvta() throws SQLException, ClassNotFoundException{
     Statement smt;
     Connection c;
        ResultSet rs;

        Usuario u =null;

        DB uDB = new DB();

        //--Abrir BD
        uDB.abrir();
        c = uDB.getConexion();

        //--Ejecutar la busqueda
        String sentenciaSQL = "SELECT * FROM usuario WHERE tipo='ADMIN'";
        System.out.println(sentenciaSQL);
        smt = c.createStatement();
        rs = smt.executeQuery(sentenciaSQL);

        // Crear el objeto
        while (rs.next()) {
            
            u = new Usuario();
            u.setId(Integer.parseInt(rs.getString("ID_USUARIO")));
            System.out.println("/" + u.getId() + "/");
        }

        uDB.cerrar();
        return u;
        
    }
    
    public void eliminarusuario(int u) throws ClassNotFoundException, SQLException {
        Statement smt;
        Connection c;
        abrir();
        int id = u;
        String sentenciaSQL = "DELETE from usuario where id_usuario =("
                + id + ")";

        System.out.print(sentenciaSQL);

        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        cerrar();
    }

    public void eliminarprove(Proveedor u) throws ClassNotFoundException, SQLException {
        Statement smt;
        Connection c;
        abrir();
        int id = u.getId();
        String sentenciaSQL = "DELETE from PROVEEDOR where ID =("
                + id + ")";

        System.out.print(sentenciaSQL);

        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        cerrar();
    }

    public void eliminarcliente(Cliente u) throws ClassNotFoundException, SQLException {
        Statement smt;
        Connection c;
        abrir();
        //int id = u.getID_CLIENTE();
        String sentenciaSQL = "DELETE from CLIENTES where ID_CLIENTE =(id  )";

        System.out.print(sentenciaSQL);

        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        cerrar();
    }

    public void modificarprove(Proveedor p) throws ClassNotFoundException, SQLException {
      Statement smt;
        Connection c;
        abrir();
        String sentenciaSQL = "update proveedor set nombre='"+p.getNombre()+
                "',tel='"+p.getTel()+
                "',calle='"+p.getCalle()+
                "',colonia='"+p.getColonia()+"' where id_proveedor="+p.getId(); 
        
        System.out.print(sentenciaSQL);

        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        smt.close();
    }

    public void modificarclientes(Cliente u) throws ClassNotFoundException, SQLException {
//        int id = u.getID_CLIENTE();
//        String nombre = u.getNombre();
//        String calle = u.getDireccion();
//        String col = u.getTelefono();
//        String tel = u.getRFC();
        Connection c;
        Statement smt;
        ResultSet rs;
        DB uDB = new DB();
        uDB.abrir();
        c = uDB.getConexion();

        String sentenciaSQL = "";
//                "UPDATE CLIENTES SET ID_CLIENTE=" + id + ""
//                + ",NOMBRE='" + nombre + "',DIRECCION='" + calle + "',TELEFONO='" + col + ""
//                + "',RFC='" + tel + "' where ID_CLIENTE =" + id;
        System.out.println(sentenciaSQL);
        smt = c.createStatement();
        smt.executeUpdate(sentenciaSQL);
    }
    
    public int buscarvta() throws SQLException{
    int id=0;
     DB uDB = new DB();
     Statement smt;
        ResultSet rs;
    String query = "SELECT max(id_venta) as lol FROM venta";
        //Connection c1 = uDB.getConexion();
        System.out.println(query);
        //Statement smt1;
        smt = conexion.createStatement();
        rs = smt.executeQuery(query);
        
        System.out.println("entre smt");
        while (rs.next()) {
            System.out.println("entre rs");
            id= rs.getInt("lol");
        }
        System.out.println(id);
    return id;
    }
    
    public void agregarventa(venta v) throws SQLException, ClassNotFoundException {
        
       
            
        Statement smt;
      
        int id=0,ids=0;
        DB uDB = new DB();
        //Usuario u = null;
      
       // uDB.abrir();
        
        String query="insert into venta values("+id+","+v.getId_cliente()+","
                +v.getId_usuario()+",'"+v.getFecha()+"','"+v.getInhr()+"',"
                +v.getTotal()+",'"+v.getStatus()+"',"+v.getCantidad()+")";
        
        System.out.print(query);
        smt = conexion.createStatement();
        smt.executeUpdate(query);
        

        
        
        
            smt.close();
        
        
         
//        int cont =0;
//        for(int i =0;i<lista.size();i++){
//        if (cont == 3) {
//            
//         query = "insert into detalle_venta values("+id+","+lista.get(i-3)+","+ids+","+lista.get(i-1)+")"; 
//         System.out.println(lista.get(i-3)+","+id+","+lista.get(i-1));
//         smt = conexion.createStatement();
//        smt.executeUpdate(query);
//         cont=0;
//        } else {
//        cont++;
//        }
//        }
        
        
        }
    
    public void agregarproducto(Producto u) throws ClassNotFoundException, SQLException {

        Statement smt;
        Connection c;
        abrir();
        int id = 0;
        String nombre = u.getNombre();
        float preciomay=u.getCostomay();
        float preciomen=u.getCostomin();
        String desc = u.getDescrip();
        int stock = u.getStock();
        String tipo= u.getTipo();
        float precioprod=u.getCostoprod();
        String ruta = u.getUrl();
        String sentenciaSQL = "INSERT INTO producto VALUES("+id+","
                + "'"+nombre+"',"+preciomay+","+preciomen+","+precioprod+",'"+desc+"',"+stock
                +",'"+tipo+"','"+ruta+"','"+u.getHabilitado()+"')";


        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        cerrar();
        
    }

    public void eliminarproducto(Producto u) throws ClassNotFoundException,MySQLIntegrityConstraintViolationException, SQLException {
        Statement smt;
        Connection c;
        abrir();
        int id = u.getId();
        String sentenciaSQL = "DELETE from producto where id_producto=("+ id + ")";

        System.out.print(sentenciaSQL);

        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        
        cerrar();
    }

    public void eliminarproveedor(int id) throws ClassNotFoundException, SQLException{
    Statement smt;
        Connection c;
        abrir();
        
        String sentenciaSQL = "DELETE from proveedor where id_proveedor=("+ id + ")";

        System.out.print(sentenciaSQL);

        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        smt.close();
    }
    
    public Producto buscarproducto(int id) throws ClassNotFoundException, SQLException {
        Connection c;
        //Abrir la conexion
        // Hacer la consulta
        // Si encontro el registro, regresar el objeto correspondiente
        // En caso contrario regresar "null"
        Statement smt;
        ResultSet rs;

        Producto u = null;

        DB uDB = new DB();

        //--Abrir BD
        uDB.abrir();
        c = uDB.getConexion();

        //--Ejecutar la busqueda
        String sentenciaSQL = "SELECT * FROM producto WHERE id_producto="+id;
        System.out.println(sentenciaSQL);
        smt = c.createStatement();
        rs = smt.executeQuery(sentenciaSQL);

        // Crear el objeto
        while (rs.next()) {
            u = new Producto();
            u.setId(Integer.parseInt(rs.getString("id_producto")));
            u.setNombre(rs.getString("nombre"));
            u.setCostomay(Float.parseFloat(rs.getString("costomay")));
            u.setCostomin(Float.parseFloat(rs.getString("costomin")));
            u.setCostoprod(Float.parseFloat(rs.getString("costoprod")));
            u.setStock(Integer.parseInt(rs.getString("stock")));
            u.setTipo(rs.getString("tipo"));
            
            System.out.println("producto "+u.getNombre());

        }
        
        return u;

    }

    public static LinkedList<Producto> getContactos() {
        LinkedList<Producto> listaContactos = new LinkedList<Producto>();
        try {
            DB uDB = new DB();
            Connection c;
            //Abrir la conexion
            // Hacer la consulta
            // Si encontro el registro, regresar el objeto correspondiente
            // En caso contrario regresar "null"
            uDB.abrir();
            Statement smt;
            ResultSet rs;
            c = uDB.getConexion();

            //--Ejecutar la busqueda
            String sentenciaSQL = "SELECT * FROM PRODUCTO";
            System.out.println(sentenciaSQL);
            smt = c.createStatement();
            rs = smt.executeQuery(sentenciaSQL);

            while (rs.next()) {
                Producto u = new Producto();
                u.setId(Integer.parseInt(rs.getString("ID_PRODUCTO")));
                u.setNombre(rs.getString("NOMBRE"));
                u.setId(Integer.parseInt(rs.getString("PRECIO")));
                u.setNombre(rs.getString("DESCRIPCION"));
                u.setId(Integer.parseInt(rs.getString("STOCK")));
                u.setNombre(rs.getString("MARCA"));
                u.setId(Integer.parseInt(rs.getString("PESO")));
                listaContactos.add(u);
            }
            uDB.cerrar();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaContactos;
    }
 

    public Cliente buscarmodc(int id) throws ClassNotFoundException, SQLException {
        Connection c;
        //Abrir la conexion
        // Hacer la consulta
        // Si encontro el registro, regresar el objeto correspondiente
        // En caso contrario regresar "null"
        Statement smt;
        ResultSet rs;

        Cliente p = null;

        DB uDB = new DB();

        //--Abrir BD
        uDB.abrir();
        c = uDB.getConexion();

        //--Ejecutar la busqueda
        String sentenciaSQL = "SELECT * FROM CLIENTES WHERE ID_CLIENTE=" + id;
        System.out.println(sentenciaSQL);
        smt = c.createStatement();
        rs = smt.executeQuery(sentenciaSQL);

        // Crear el objeto
        while (rs.next()) {
            p = new Cliente();
//            p.setID_CLIENTE(Integer.parseInt(rs.getString("ID_CLIENTE")));
            p.setNombre(rs.getString("NOMBRE"));
//            p.setDireccion(rs.getString("DIRECCION"));
            p.setTelefono(rs.getString("TELEFONO"));
//            p.setRFC(rs.getString("RFC"));

        }
        uDB.cerrar();
        return p;

    }

    public Proveedor buscarmodprove(int id) throws ClassNotFoundException, SQLException {
        Connection c;
        //Abrir la conexion
        // Hacer la consulta
        // Si encontro el registro, regresar el objeto correspondiente
        // En caso contrario regresar "null"
        Statement smt;
        ResultSet rs;

        Proveedor p = null;

        DB uDB = new DB();

        //--Abrir BD
        uDB.abrir();
        c = uDB.getConexion();

        //--Ejecutar la busqueda
        String sentenciaSQL = "SELECT * FROM PROVEEDOR WHERE ID=" + id;
        System.out.println(sentenciaSQL);
        smt = c.createStatement();
        rs = smt.executeQuery(sentenciaSQL);

        // Crear el objeto
        while (rs.next()) {
            p = new Proveedor();
            p.setId(Integer.parseInt(rs.getString("ID")));
            p.setNombre(rs.getString("NOMBRE"));
//            p.setCalle(rs.getString("CALLE"));
//            p.setColonia(rs.getString("COLONIA"));
//            p.setTelefono(rs.getString("TELEFONO"));

        }
        uDB.cerrar();
        return p;

    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }

}
