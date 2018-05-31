/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Cliente;
import Modelo.Compra_prod;
import Modelo.Producion;
import Modelo.Producto;
import Modelo.Proveedor;
import Modelo.Usuario;
import Modelo.entrada_prov;
import Modelo.factura;
import Modelo.productot;
import Modelo.proveedores;
import Modelo.usuariot;
import Modelo.venta;
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
public class DBt {

    private String URL = "jdbc:mysql://localhost:3306/soporte";
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
        conexion = DriverManager.getConnection(URL, "root", "416cronos");

    }

    public void cerrar() throws SQLException {
        conexion.close();

    }

    public void nuevaventa(venta v) {

    }

    public ArrayList<Object> getdepa(ArrayList<Object> arr) throws ClassNotFoundException, SQLException {
        abrir();
        Statement smt;
        ResultSet rs;
        String sentenciaSQL = "SELECT nombre FROM departamento ORDER BY nombre";
        smt = conexion.createStatement();
        rs = smt.executeQuery(sentenciaSQL);
        while (rs.next()) {
            arr.add(rs.getString("nombre"));
        }
        return arr;
    }

    public boolean alerta() throws ClassNotFoundException, SQLException {
        boolean estado = false;
        abrir();
        Statement smt;
        ResultSet rs;
        String query = "select descripcion from  tareas where status = 'PENDIENTE'";
        smt = conexion.createStatement();
        rs = smt.executeQuery(query);
        while (rs.next()) {
            estado = true;
        }
        return estado;
    }

    public ArrayList<Object> retornombrepro(String f1, String f2) throws ClassNotFoundException, SQLException {
        ArrayList<Object> lista = new ArrayList<Object>();
        Connection c;
        Statement smt;
        ResultSet rs;
        Usuario u = null;
        DB uDB = new DB();
        uDB.abrir();
        c = uDB.getConexion();
        String query = "select c.id_compraprod,p.nombre from producto p JOIN compra_producto c ON p.id_producto =c.id_producto  where fecha BETWEEN '" + f1 + "' AND '" + f2 + "' ORDER BY p.nombre"; 
        smt = c.createStatement();
        rs = smt.executeQuery(query);
        while (rs.next()) {
            lista.add(rs.getString("c.id_compraprod").toString());
        }
        smt.close();
        return lista;
    }

    public ArrayList<Object> retornoidventa(String f1, String f2) throws ClassNotFoundException, SQLException {
        ArrayList<Object> lista = new ArrayList<Object>();
        Connection c;
        Statement smt;
        ResultSet rs;
        Usuario u = null;
        DB uDB = new DB();
        uDB.abrir();
        c = uDB.getConexion();
        String query = "SELECT id_venta from venta where fecha BETWEEN '" + f1 + "' AND '" + f2 + "' ORDER BY id_venta";
        smt = c.createStatement();
        rs = smt.executeQuery(query);
        while (rs.next()) {
            //total=total+ Float.parseFloat(rs.getString("monto"));
            lista.add(Integer.parseInt(rs.getString("id_venta").toString()));
        }
        smt.close();
        return lista;
    }

    public ArrayList<Object> verventast(String f1, String f2,String tipo_p,String extra,String extras) throws ClassNotFoundException, SQLException {
        ArrayList<Object> lista = new ArrayList<>();
        Connection c;
        Statement smt;
        ResultSet rs;
        Usuario u = null;
        abrir();
        String query = "select f.ID_FACTURA,u.usuario,f.cantidad,f.total,f.fecha\n"
                + "from factura f join usuario u on f.ID_USUARIOC=u.ID_USUARIO join detalle_fact df on df.ID_FACTURA=f.ID_FACTURA join PRODUCTO p ON df.ID_PRODUCTO =p.ID_PRODUCTO\n"
                + "where f.fecha between '" + f1 + "' and '" + f2 + "' and p.tipo_producto='"+tipo_p+"' group by f.ID_FACTURA";
        smt = conexion.createStatement();
        rs = smt.executeQuery(query);
        while (rs.next()) {
            //total=total+ Float.parseFloat(rs.getString("monto"));
            lista.add(Integer.parseInt(rs.getString("f.ID_FACTURA")));
            lista.add((rs.getString("u.usuario")));
            lista.add(Integer.parseInt(rs.getString("f.cantidad")));
            lista.add(Double.parseDouble(rs.getString("f.total")));
            lista.add((rs.getString("f.fecha")));
        }
        smt.close();
        return lista;
    }
    //consula del onkeypress para llenado de ventas por fechas
    public ArrayList<Object> verventast(String f1, String f2, String prod,String tipo_p) throws ClassNotFoundException, SQLException {
        ArrayList<Object> lista = new ArrayList<>();
        Statement smt;
        ResultSet rs;
        abrir();
        String query = "select distinct f.ID_FACTURA,u.usuario,f.cantidad,f.total,f.fecha\n"
                + "from factura f join usuario u on f.ID_USUARIOC=u.ID_USUARIO "
                + "join detalle_fact df on df.id_factura=f.id_factura join producto pr on pr.id_producto = df.id_producto join departamento dep on dep.ID_DEP=u.ID_DEP\n"
                + "where f.fecha between '" + f1 + "' and '" + f2 + "' and (pr.nombre like '" + prod + "%' or pr.modelo like '" + prod + "%' or dep.nombre like '%" + prod + "%' or pr.marca like '%"+prod+"%' or pr.descripcion like '%"+prod+"%') and pr.tipo_producto='"+tipo_p+"' order by f.id_factura";
        smt = conexion.createStatement();
        rs = smt.executeQuery(query);
        while (rs.next()) {
            //total=total+ Float.parseFloat(rs.getString("monto"));
            lista.add(Integer.parseInt(rs.getString("f.ID_FACTURA")));
            lista.add((rs.getString("u.usuario")));
            lista.add(Integer.parseInt(rs.getString("f.cantidad")));
            lista.add(Double.parseDouble(rs.getString("f.total")));
            lista.add((rs.getString("f.fecha")));
        }
        smt.close();
        return lista;
    }

    public ArrayList<Object> ver_catalogo_prov(String prod, String tipo_p) throws ClassNotFoundException, SQLException {
        ArrayList<Object> lista = new ArrayList<>();
        Statement smt;
        ResultSet rs;
        Usuario u = null;
        abrir();
        String query = "select * from producto where status ='Y' and ( nombre like '%" + prod + "%' or modelo like '%" + prod + "%') and tipo_producto='"+tipo_p+"' order by nombre";        
        smt = conexion.createStatement();
        rs = smt.executeQuery(query);
        while (rs.next()) {
            lista.add(rs.getString("ID_PRODUCTO"));
            lista.add(rs.getString("nombre"));
            lista.add(rs.getString("modelo"));
            lista.add(rs.getString("stock"));
            lista.add(rs.getString("imagen"));
        }
        smt.close();
        return lista;
    }
    public ArrayList<Object> ver_catalogo_prod(String prod,String tipo) throws ClassNotFoundException, SQLException {
        ArrayList<Object> lista = new ArrayList<>();
        Statement smt;
        ResultSet rs;
        Usuario u = null;
        abrir();
        String query = "select * from producto where status ='Y' and  tipo_producto='"+tipo+"' and ( nombre like '%" + prod + "%' or modelo like '%" + prod + "%' or marca like '%"+prod+"%') order by nombre";        
        smt = conexion.createStatement();
        rs = smt.executeQuery(query);
        while (rs.next()) {
            lista.add(rs.getString("ID_PRODUCTO"));
            //lista.add(rs.getString("nombre"));
            lista.add(rs.getString("modelo"));
            lista.add(rs.getString("marca"));
            lista.add(rs.getString("stock"));
            lista.add(rs.getString("costo"));
            lista.add(rs.getString("descripcion"));
            lista.add(rs.getString("imagen"));
        }
        smt.close();
        return lista;
    }
    
    public ArrayList<Object> verventastdep(String f1, String f2) throws ClassNotFoundException, SQLException {
        ArrayList<Object> lista = new ArrayList<>();
        Connection c;
        Statement smt;
        ResultSet rs;
        Usuario u = null;
        abrir();
        String query = "select distinct sum(f.total) as 'Importe',d.Nombre\n"
                + "from factura f join usuario u on f.ID_USUARIOC=u.ID_USUARIO \n"
                + "join departamento d on d.ID_DEP=u.ID_DEP\n"
                + "where f.fecha between '" + f1 + "' and '" + f2 + "' \n"
                + "group by d.Nombre "
                + "order by Importe";
        smt = conexion.createStatement();
        rs = smt.executeQuery(query);
        while (rs.next()) {
            lista.add((rs.getString("Nombre")));
            lista.add(Float.parseFloat(rs.getString("Importe")));
        }
        smt.close();
        return lista;
    }

    // consulta para el onkeypress costos por departamento
    public ArrayList<Object> verventastdep(String f1, String f2, String depa) throws ClassNotFoundException, SQLException {
        ArrayList<Object> lista = new ArrayList<>();
        Statement smt;
        ResultSet rs;
        Usuario u = null;
        abrir();
        String query = "select distinct d.Nombre,df.id_factura, case when f.cantidad >1 then (sum(f.total)/f.cantidad) else sum(f.total) end as Importe\n"
                + "from factura f join usuario u on f.ID_USUARIOC=u.ID_USUARIO \n"
                + "join departamento d on d.ID_DEP=u.ID_DEP\n"
                + "join detalle_fact df on df.id_factura=f.id_factura join producto pr on pr.id_producto = df.id_producto \n"
                + "where f.fecha between '" + f1 + "' and '" + f2 + "' and ( pr.modelo like '" + depa + "%' ) and (pr.tipo_producto='SISTEMAS' or pr.tipo_producto='ETIQUETAS') \n"
                + "group by d.Nombre "
                + "order by Importe DESC";
        smt = conexion.createStatement();
        rs = smt.executeQuery(query);
        while (rs.next()) {
            lista.add((rs.getString("Nombre")));
            lista.add(Float.parseFloat(rs.getString("Importe")));
        }
        smt.close();
        return lista;
    }

    public ArrayList<Object> verventastd(int ids) throws ClassNotFoundException, SQLException {
        ArrayList<Object> lista = new ArrayList<>();
        Statement smt;
        ResultSet rs;
        abrir();
        String query = "select f.ID_FACTURA,u.usuario,d.cantidad,p.nombre,p.modelo,p.costo,f.fecha\n"
                + "from factura f join usuario u on f.ID_USUARIOC=u.ID_USUARIO\n"
                + "join detalle_fact d on d.ID_FACTURA=f.ID_FACTURA\n"
                + "join producto p on p.ID_PRODUCTO=d.ID_PRODUCTO\n"
                + "where d.ID_FACTURA=" + ids;       
        smt = conexion.createStatement();
        rs = smt.executeQuery(query);
        while (rs.next()) {
            //total=total+ Float.parseFloat(rs.getString("monto"));
            lista.add(Integer.parseInt(rs.getString("f.ID_FACTURA")));
            lista.add((rs.getString("u.usuario")));
            lista.add(Integer.parseInt(rs.getString("d.cantidad")));
            lista.add((rs.getString("p.nombre")));
            lista.add((rs.getString("p.modelo")));
            lista.add(Float.parseFloat(rs.getString("p.costo")) * Integer.parseInt(rs.getString("d.cantidad")));
            lista.add((rs.getString("f.fecha")));
        }
        smt.close();
        return lista;
    }

    public ArrayList<Object> retornodatos(String fecha) throws ClassNotFoundException, SQLException {
        ArrayList<Object> lista = new ArrayList<Object>();
        Connection c;
        Statement smt;
        ResultSet rs;
        float total = 0;
        int contables = 0;
        Usuario u = null;
        DB uDB = new DB();
        uDB.abrir();
        c = uDB.getConexion();
        String query = "select MAX(id_ingreso),monto from ingresos where fecha='" + fecha + "'";
        smt = c.createStatement();
        rs = smt.executeQuery(query);
        while (rs.next()) {
            total = total + Float.parseFloat(rs.getString("monto"));
        }
        lista.add(total);
        total = 0;
        query = "select sum(monto) as monto from gastos where fecha='" + fecha + "'";
        smt = c.createStatement();
        rs = smt.executeQuery(query);
        while (rs.next()) {
            total = total + Float.parseFloat(rs.getString("monto"));
        }
        lista.add(total);
        total = 0;
        query = "select SUM(cantidad) as cantidad from venta where fecha='" + fecha + "'";
        smt = c.createStatement();
        rs = smt.executeQuery(query);
        while (rs.next()) {
            contables = contables + Integer.parseInt(rs.getString("cantidad"));
        }
        lista.add(contables);
        contables = 0;
        query = "select SUM(cantidad) as cantidad from compra_producto where fecha='" + fecha + "'";
        smt = c.createStatement();
        rs = smt.executeQuery(query);
        while (rs.next()) {
            if (rs == null) {
                contables = contables + Integer.parseInt(rs.getString("cantidad"));
            } else {
            }
            //contables=contables+ Integer.parseInt(rs.getString("cantidad"));
        }
        lista.add(contables);
        contables = 0;
        int conta = 0;
        query = "select id_venta from venta where fecha='" + fecha + "'";
        smt = c.createStatement();
        rs = smt.executeQuery(query);
        while (rs.next()) {
            conta++;
        }
        lista.add(conta);
        contables = 0;
        query = "select SUM(producido) as producido from production where fecha='" + fecha + "'";
        smt = c.createStatement();
        rs = smt.executeQuery(query);
        while (rs.next()) {
            if (rs == null) {
                contables = contables + Integer.parseInt(rs.getString("producido"));
            }
        }
        lista.add(contables);
        contables = 0;
        query = "select SUM(stock) as stock from producto ";
        smt = c.createStatement();
        rs = smt.executeQuery(query);
        while (rs.next()) {
            contables = contables + Integer.parseInt(rs.getString("stock"));
        }
        lista.add(contables);
        smt.close();
        rs.close();
        return lista;
    }

    public ArrayList<Object> retornoingreso(String fi, String ff) throws ClassNotFoundException, SQLException {
        ArrayList<Object> lista = new ArrayList<Object>();
        Connection c;
        Statement smt;
        ResultSet rs;
        float total = 0;
        int contables = 0;
        Usuario u = null;
        DB uDB = new DB();
        uDB.abrir();
        c = uDB.getConexion();
        String query = "select * from ingresos where fecha BETWEEN '" + fi + "' AND '" + ff + "' ORDER BY id_ingreso";
        smt = c.createStatement();
        rs = smt.executeQuery(query);
        while (rs.next()) {
            lista.add(Integer.parseInt(rs.getString("id_ingreso").toString()));
            lista.add(rs.getString("motivo").toString());
            lista.add(Float.parseFloat(rs.getString("monto").toString()));
            lista.add(rs.getString("fecha").toString());
        }
        lista.add(total);
        return lista;
    }

    public ArrayList<Object> retornogasto(String fi, String ff) throws ClassNotFoundException, SQLException {
        ArrayList<Object> lista = new ArrayList<Object>();
        Connection c;
        Statement smt;
        ResultSet rs;
        float total = 0;
        int contables = 0;
        Usuario u = null;
        DB uDB = new DB();
        uDB.abrir();
        c = uDB.getConexion();
        String query = "select * from gastos where fecha BETWEEN '" + fi + "' AND '" + ff + "' ORDER BY id_gasto";
        smt = c.createStatement();
        rs = smt.executeQuery(query);
        while (rs.next()) {
            lista.add(Integer.parseInt(rs.getString("id_gasto").toString()));
            lista.add(rs.getString("motivo").toString());
            lista.add(Float.parseFloat(rs.getString("monto").toString()));
            lista.add(rs.getString("fecha").toString());
        }
        lista.add(total);
        return lista;
    }

    // agregar
    public void agregaringresoauto(String fecha) throws ClassNotFoundException, SQLException {
        Connection c;
        Statement smt;
        ResultSet rs;
        float total = 0;
        String flotante = "";
        int id = 0;
        Usuario u = null;
        DB uDB = new DB();
        uDB.abrir();
        c = uDB.getConexion();
        String query = "SELECT SUM(total) as total FROM venta where fecha='" + fecha + "'";
        smt = c.createStatement();
        rs = smt.executeQuery(query);
        while (rs.next()) {
            total = total + Float.parseFloat(rs.getString("total"));
        }
        if (rs == null) {
            total = 0;
        }
        query = "insert into ingresos values(" + id + ",'VENTAS'," + total + ",'" + fecha + "')";
        
        smt = c.createStatement();
        smt.executeUpdate(query);
        total = 0;
        query = "SELECT SUM(ganancia) as ganancia FROM production where fecha='" + fecha + "'";
        smt = c.createStatement();
        rs = smt.executeQuery(query);
        while (rs.next()) {
            if (rs == null) {
                total = total + Float.parseFloat(rs.getString("ganancia"));
            } else {
            }

        }
        query = "insert into Gastos values(" + id + ",'SUELDOS DE PRODUCCIÓN'," + total + ",'" + fecha + "')";
        smt = c.createStatement();
        smt.executeUpdate(query);
        total = 0;
        query = "SELECT SUM(costo) as costo FROM compra_producto where fecha='" + fecha + "'";
        smt = c.createStatement();
        rs = smt.executeQuery(query);
        while (rs.next()) {
            if (rs == null) {
                total = total + Float.parseFloat(rs.getString("costo"));
            } else {
            }
        }
        query = "insert into Gastos values(" + id + ",'COMPRA PROVEEDOR'," + total + ",'" + fecha + "')";
        smt = c.createStatement();
        smt.executeUpdate(query);
        smt.close();
        rs.close();
    }

    public void agregar(usuariot u, String depa) throws ClassNotFoundException, SQLException {
        Statement smt;
        ResultSet rs;
        int a = 0;
        abrir();
        int id = 0;
        String nombre = u.getNombre().toUpperCase();
        String contrasena = u.getContrasena();
        String tipo = u.getTipo().toUpperCase();
        String usuario = u.getUsuario();
        String apellido = u.getApellido();
        String sentenciaSQL = "select ID_DEP from departamento where Nombre='" + depa + "'";
        smt = conexion.createStatement();
        rs = smt.executeQuery(sentenciaSQL);
        while (rs.next()) {
            a = Integer.parseInt(rs.getString("ID_DEP"));
        }
        sentenciaSQL = "INSERT INTO usuario VALUES("
                + id + ",'" + usuario + "','" + nombre + "',"
                + "'" + apellido + "',"
                + "'" + tipo + "','" + u.getActivo() + "','" + contrasena + "','" + u.getIp() + "'," + a + ")";
        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        smt.close();
    }

    public void agregargasto(String m, float monto, String fecha) throws ClassNotFoundException, SQLException {
        Statement smt;
        abrir();
        int id = 0;
        String sentenciaSQL = "INSERT INTO gastos VALUES("
                + id + ",'" + m + "',"
                + "" + monto + ","
                + "'" + fecha + "')";
        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        smt.close();
    }

    public void agregartarea(String m, String desc, String fecha) throws ClassNotFoundException, SQLException {
        Statement smt;
        abrir();
        int id = 0;
        String sentenciaSQL = "INSERT INTO tareas VALUES("
                + id + ",'" + m + "',"
                + "'" + desc + "',"
                + "'PENDIENTE','" + fecha + "')";
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


        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        cerrar();

    }

    public void agregarproduccion(Producion u) throws ClassNotFoundException, SQLException {
        Statement smt;
        abrir();
        int id = 0;
        int idusu = u.getId_usuario();
        int idprod = u.getId_producto();
        int producion = u.getProducido();
        float ganancia = u.getGanancia();
        String fecha = u.getFecha();
        String sentenciaSQL = "INSERT INTO production VALUES("
                + id + "," + idusu + ","
                + "" + idprod + ","
                + "" + producion + "," + ganancia + ",'" + fecha + "')";
        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        smt.close();
    }

    public void agregarcliente(Cliente u) throws ClassNotFoundException, SQLException {
        Statement smt;
        abrir();
        int id = 0;
        String sentenciaSQL = "insert into cliente values(" + id + ",'" + u.getNombre() + "','" + u.getApellido() + "','" + u.getCalle() + "','" + u.getColonia() + "','" + u.getTelefono() + "'," + u.getCp() + ")";
        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        smt.close();
    }

    public void agregarcompraproveedor(Compra_prod u) throws ClassNotFoundException, SQLException {
        Statement smt;
        abrir();
        int id = 0;
        String sentenciaSQL = "insert into compra_producto values(" + id + ","
                + u.getId_producto() + "," + u.getId_proveedor() + ",'"
                + u.getFecha() + "'," + u.getCosto() + "," + u.getCantidad() + "," + u.getTotal() + ")";
        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        sentenciaSQL = "update producto set stock=stock+" + u.getCantidad() + " where id_producto =" + u.getId_producto();
        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        smt.close();

    }

    public void agregarproveedor(Proveedor u) throws ClassNotFoundException, SQLException {
        Statement smt;
        abrir();
        int id = 0;
        String sentenciaSQL = "insert into proveedor values(" + id + ",'" + u.getNombre() + "','" + u.getTel() + "','" + u.getCalle() + "','" + u.getColonia() + "')";
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
        abrir();
        int id = u.getId();
        String nombre = u.getNombre();
        int stock = u.getStock();
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
        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        cerrar();
    }

    public void agregardetalle(int idv, ArrayList lista) throws ClassNotFoundException, SQLException {
        Statement smt = null;
        ResultSet rs;
        venta v = new venta();
        int id = 0, ids = 0;
        abrir();
        String sentenciaSQL;
        int cont = 0;
        for (int i = 0; i < lista.size(); i++) {
            if (cont == 3) {
                sentenciaSQL = "insert into detalle_venta values(" + ids + "," + lista.get(i - 3) + "," + idv + "," + lista.get(i - 1) + ")";
                smt = conexion.createStatement();
                smt.executeUpdate(sentenciaSQL);
                cont = 0;
            } else {
                cont++;
            }
        }
        smt.close();
    }

    public void agregardetallefact(int idv, ArrayList lista) throws ClassNotFoundException, SQLException {
        Statement smt = null;
        ResultSet rs;
        venta v = new venta();
        int id = 0, ids = 0;
        abrir();
        String sentenciaSQL;
        int cont = 0;
        for (int i = 0; i < lista.size(); i++) {
            if (cont == 3) {
                sentenciaSQL = "insert into detalle_fact values(" + ids + "," + idv + "," + lista.get(i - 3) + "," + lista.get(i - 1) + ")";
                smt = conexion.createStatement();
                smt.executeUpdate(sentenciaSQL);
                cont = 0;
            } else {
                cont++;
            }
        }
        smt.close();
    }
    public void agregardetallefact_prov(int idv, ArrayList lista) throws ClassNotFoundException, SQLException {
        Statement smt = null;
        ResultSet rs;
        int ids = 0;
        abrir();
        getConexion();
        String sentenciaSQL="";
        int cont = 0;
        for (int i = 0; i < lista.size(); i++) {
            if (cont == 3) {
                if((int)lista.get(i-1)>1){
                sentenciaSQL = "insert into detalle_fact_prov(ID_producto,ID_FACT_IN,cantidad,preciouni,subtotal) values("+ lista.get(i - 3) + "," + idv+ ","+lista.get(i-1)+","+((float)lista.get(i)/(int)lista.get(i-1))+","+lista.get(i)+")";
                }else{
                sentenciaSQL = "insert into detalle_fact_prov(ID_producto,ID_FACT_IN,cantidad,preciouni,subtotal) values("+ lista.get(i - 3) + "," + idv+ ","+lista.get(i-1)+","+lista.get(i)+","+lista.get(i)+")";                
                }
                smt = conexion.createStatement();
                smt.executeUpdate(sentenciaSQL);
                cont = 0;
            } else {
                cont++;
            }
        }smt.close();
        
    }
    public void agregardetallebaja(int idp, int ids, int cant, String fecha) throws SQLException, ClassNotFoundException {
        Connection c;
        int id = 0;
        DB uDB = new DB();
        uDB.abrir();
        c = uDB.getConexion();
        Statement smt;
        String sentenciaSQL = "insert into detalle_baja values(" + id + "," + idp + "," + ids + "," + cant + ",'" + fecha + "','SI')";
        smt = c.createStatement();
        smt.executeUpdate(sentenciaSQL);
        sentenciaSQL = "update producto set stock=stock-" + cant + " where id_producto=" + idp;
        smt = c.createStatement();
        smt.executeUpdate(sentenciaSQL);
        smt.close();
    }

    ////////ENtrada y salida
    public String agregares(proveedores p, String hora) throws ClassNotFoundException, SQLException {
        String re = "";
        Statement smt;
        ResultSet rs;
        abrir();
        int conta = 0;
        String n = "";
        String idprov = null;
        String query = "SELECT p.id_proveedor,p.Nombre from proveedor p join entradasalida e on e.ID_PROVEEDOR=p.ID_PROVEEDOR\n"
                + " where p.codigo='" + p.getCodigo() + "' and e.fecha ='" + p.getFecha() + "' ";
        smt = conexion.createStatement();
        rs = smt.executeQuery(query);
        while (rs.next()) {
            conta++;
            idprov = (rs.getString("id_proveedor"));
            n = rs.getString("nombre");
        }
        rs.close();
        int id = 0;

        if (conta == 0) {
            p = bprov(p);
            //busquedade prov
            if (p.getNombre_prove() != null) {

                rs.close();
                String sentenciaSQL = "insert into entradasalida values(" + id + ",'" + p.getId_prove() + "','" + p.getFecha() + "','" + hora + "','E')";
                smt = conexion.createStatement();
                smt.executeUpdate(sentenciaSQL);
                smt.close();
                re = "" + p.getNombre_prove();
            } else {
                re = "No existe el proveedor!";
            }

        } else if (idprov != null && conta != 0) {
            if (conta % 2 == 0) {
                String sentenciaSQL = "insert into entradasalida values(" + id + ",'" + Integer.parseInt(idprov) + "','" + p.getFecha() + "','" + hora + "','E')";
                smt = conexion.createStatement();
                smt.executeUpdate(sentenciaSQL);
                smt.close();

                re = "" + n;
            } else if (conta % 2 == 1) {
                String sentenciaSQL = "insert into entradasalida values(" + id + ",'" + Integer.parseInt(idprov) + "','" + p.getFecha() + "','" + hora + "','S')";
                smt = conexion.createStatement();
                smt.executeUpdate(sentenciaSQL);
                smt.close();
                re = "salida: " + n;
            } else {
                re = "No existe el proveedor! ";
            }
        } else {
            re = "No existe el proveedor!";
        }
        return re;
    }

    //// ENtrada y salida
    public proveedores bprov(proveedores p) throws ClassNotFoundException, SQLException {
        p.setNombre_prove(null);
        Statement smt;
        ResultSet rs;
        abrir();
        String n = null;
        String query = "SELECT nombre,ID_PROVEEDOR from proveedor where codigo='" + p.getCodigo() + "'";
        smt = conexion.createStatement();
        rs = smt.executeQuery(query);
        while (rs.next()) {
            p.setNombre_prove(rs.getString("nombre"));
            p.setId_prove(Integer.parseInt(rs.getString("ID_PROVEEDOR")));
        }
        return p;
    }

    public String buscarid_depa(String p) throws ClassNotFoundException, SQLException {

        Statement smt;
        ResultSet rs;
        abrir();
        String query = "SELECT ID_DEP from departamento where nombre ='" + p + "'";
        smt = conexion.createStatement();
        rs = smt.executeQuery(query);
        while (rs.next()) {
            p = rs.getString("ID_DEP");
        }
        return p;
    }

    public void agregarprodt(productot p) throws ClassNotFoundException, SQLException {
        Statement smt;
        Connection c;
        abrir();
        int id = 0;
        String sentenciaSQL = "insert into producto values(" + id + ",'" + p.getNombre() + "','" + p.getModelo() + "','" + p.getMarca() + "'," + p.getStock() + "," + p.getCosto() + ",'" + p.getStatus() + "','" + p.getDescripcion() + "','" + p.getUrl() + "','" + p.getTipo() + "')";
        System.out.println(sentenciaSQL);
        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        smt.close();
    }

    public ArrayList<Object> depamarca(String marca, String depa) throws ClassNotFoundException, SQLException {
        ArrayList<Object> lolo = new ArrayList<>();

        Statement smt;
        abrir();
        int id = 0;
        String sentenciaSQL = "insert into departamento values(" + id + ",'" + depa + "','" + marca + "')";
        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        smt.close();

        ResultSet rs;
        //--Abrir BD
        abrir();
        //--Ejecutar la busqueda
        sentenciaSQL = "SELECT Nombre FROM departamento";
        
        smt = conexion.createStatement();
        rs = smt.executeQuery(sentenciaSQL);
        // Crear el objeto
        while (rs.next()) {
            lolo.add(rs.getString("Nombre"));
        }
        smt.close();
        rs.close();
        return lolo;
    }

    //Terminar agregar
    public usuariot buscar(String nombre, String contrasena) throws ClassNotFoundException, SQLException {
        Statement smt;
        ResultSet rs;
        usuariot u = null;
abrir();
        String sentenciaSQL = "SELECT * FROM usuario WHERE usuario=" + "'" + nombre + "'"
                + " AND contrasena = " + "'" + contrasena + "'";
        smt = conexion.createStatement();
        rs = smt.executeQuery(sentenciaSQL);
        while (rs.next()) {
            u = new usuariot();
            u.setID_USUARIO(Integer.parseInt(rs.getString("ID_USUARIO")));
            u.setNombre(rs.getString("NOMBRE"));
            u.setContrasena(rs.getString("CONTRASENA"));
            u.setTipo(rs.getString("TIPO"));
            u.setActivo("ACTIVO");
        }
        rs.close();
        return u;

    }

    public productot buscarprod(int p) throws ClassNotFoundException, SQLException {
        Statement smt;
        ResultSet rs;
        productot u = null;
        abrir();
        String sentenciaSQL = "SELECT * FROM producto WHERE id_producto=" + p;
        
        smt = conexion.createStatement();
        rs = smt.executeQuery(sentenciaSQL);
        // Crear el objeto
        while (rs.next()) {
            u = new productot();
            u.setID_PRODUCTO(Integer.parseInt(rs.getString("ID_PRODUCTO")));
            u.setNombre(rs.getString("NOMBRE"));
            u.setModelo(rs.getString("MODELO"));
            u.setMarca(rs.getString("MARCA"));
            u.setStock(Integer.parseInt(rs.getString("stock")));
            u.setCosto(Double.parseDouble(rs.getString("costo")));
            u.setTipo(rs.getString("TIPO_PRODUCTO"));
            u.setDescripcion(rs.getString("DESCRIPCION"));
        }
        cerrar();
        return u;
    }

    public boolean buscarusuariorepe(String ut, String uso) throws ClassNotFoundException, SQLException {
        boolean regreso = false;
        int usuario = 0;
        Statement smt;
        ResultSet rs;
        abrir();
        String sentenciaSQL = "SELECT usuario FROM usuario WHERE usuario='" + ut + "'";
        //
        smt = conexion.createStatement();
        rs = smt.executeQuery(sentenciaSQL);
        // Crear el objeto
        while (rs.next()) {
            regreso = true;
            usuario++;
        }
        smt.close();
        rs.close();
        if (uso.equals("mod")) {
            if (usuario == 1) {
                regreso = false;
            }
        }

        return regreso;
    }

    public int obtenerid(String usu) throws ClassNotFoundException, SQLException {
        Connection c;
        int usuario = 0;
        Statement smt;
        ResultSet rs;
        abrir();
        c = getConexion();

        //--Ejecutar la busqueda
        String sentenciaSQL = "SELECT * FROM usuario WHERE usuario='" + usu + "'";
        ;
        smt = c.createStatement();
        rs = smt.executeQuery(sentenciaSQL);
        // Crear el objeto
        while (rs.next()) {
            usuario = (Integer.parseInt(rs.getString("ID_USUARIO")));
        }
        cerrar();
        return usuario;
    }

    public usuariot buscar(int id) throws ClassNotFoundException, SQLException {
        Connection c;
        Statement smt;
        ResultSet rs;
        usuariot u = null;
        DBt uDB = new DBt();
        uDB.abrir();
        c = uDB.getConexion();
        String sentenciaSQL = "select u.ID_USUARIO,u.nombre,u.contrasena,u.usuario,u.apellido,u.ip,d.Nombre from usuario u join departamento d on d.ID_DEP=u.ID_DEP\n"
                + "where u.ID_USUARIO=" + id;
        smt = c.createStatement();
        rs = smt.executeQuery(sentenciaSQL);
        while (rs.next()) {
            u = new usuariot();
            u.setID_USUARIO(Integer.parseInt(rs.getString("u.ID_USUARIO")));
            u.setNombre(rs.getString("u.NOMBRE"));
            u.setContrasena(rs.getString("u.CONTRASENA"));
            u.setUsuario(rs.getString("u.USUARIO"));
            u.setApellido(rs.getString("u.APELLIDO"));
            u.setIp(rs.getString("u.IP"));
            u.setActivo(rs.getString("d.nombre"));
        }
        uDB.cerrar();
        return u;
    }

    public void modificarprod(Producto p) throws ClassNotFoundException, SQLException {
        Statement smt;
        abrir();
        String sentenciaSQL = "update producto set nombre='" + p.getNombre()
                + "', costomay=" + p.getCostomay()
                + ",costomin=" + p.getCostomin() + ",costoprod=" + p.getCostoprod()
                + ",stock=" + p.getStock() + ",habilitar='" + p.getHabilitado() + "' where id_producto=" + p.getId();
        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        smt.close();

    }

    public void modibajausut(int id) throws ClassNotFoundException, SQLException {
        Statement smt;
        abrir();
        String sentenciaSQL = "update usuario set activo='N' where id_usuario=" + id;
        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        smt.close();
    }

    public void modialtausut(int id) throws ClassNotFoundException, SQLException {
        Statement smt;
        Connection c;
        abrir();
        String sentenciaSQL = "update usuario set activo='Y' where id_usuario=" + id;
        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        smt.close();
    }

    public void borrarpedido(int id) throws ClassNotFoundException, SQLException {
        ArrayList<Object> lista = new ArrayList<Object>();
        Connection c;
        Statement smt;
        ResultSet rs;
        int ids = 0;
        Usuario u = null;
        DB uDB = new DB();
        uDB.abrir();
        c = uDB.getConexion();
        String query = "SELECT id_producto,cantidad from detalle_venta where id_venta=" + id;
        smt = c.createStatement();
        rs = smt.executeQuery(query);
        while (rs.next()) {
            lista.add(Integer.parseInt(rs.getString("id_producto")));
            lista.add(Integer.parseInt(rs.getString("cantidad")));
        }
        int cont = 0;
        for (int i = 0; i < lista.size(); i++) {
            if (cont == 1) {
                query = "update producto set stock=stock+" + lista.get(i) + " where id_producto=" + lista.get(i - 1);
                smt = c.createStatement();
                smt.executeUpdate(query);
            } else {
                cont++;
            }
        }
        query = "delete from detalle_venta where id_venta=" + id;
        smt = c.createStatement();
        smt.executeUpdate(query);
        query = "delete from venta where id_venta=" + id;
        smt = c.createStatement();
        smt.executeUpdate(query);
        rs.close();
        smt.close();
    }

    public void modificardetalleprod(int id) throws ClassNotFoundException, SQLException {
        Statement smt;
        abrir();
        String sentenciaSQL = "update detalle_baja set habilitar='NO' where id_detalleb=" + id;
        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        smt.close();
    }

    public void modificarprod(int ids, int cantidad) throws ClassNotFoundException, SQLException {
        Statement smt;
        abrir();
        String sentenciaSQL = "update producto set stock=stock+" + cantidad + " where id_producto=" + ids;
        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        smt.close();

    }

    public void usarpedido(int usuario, int id, String uso) throws ClassNotFoundException, SQLException {
        Statement smt;
        Connection c;
        abrir();
        String sentenciaSQL = "update venta set status='PENDIENTE', id_usuario=" + usuario + " where id_venta=" + id;
        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        smt.close();
    }

    public void actualizarpedido(int id) throws ClassNotFoundException, SQLException {
        Statement smt;
        abrir();
        String sentenciaSQL = "update venta set status='ENVIADO' where id_venta=" + id;
        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        smt.close();
    }

    public void modificarusut(usuariot p) throws ClassNotFoundException, SQLException {
        Statement smt;
        abrir();
        String sentenciaSQL = "update usuario set usuario='" + p.getUsuario()
                + "', nombre='" + p.getNombre()
                + "',apellido='" + p.getApellido()
                + "',tipo='" + p.getTipo() + "',contrasena='" + p.getContrasena() + "',ip='" + p.getIp() + "', ID_DEP=" + Integer.parseInt(p.getActivo()) + " where id_usuario=" + p.getID_USUARIO();
        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        smt.close();
    }

    public void modiproduct(productot p) throws SQLException, ClassNotFoundException {
        Statement smt;
        abrir();
        String sentenciaSQL = "update producto set nombre='" + p.getNombre()
                + "', modelo='" + p.getModelo()
                + "',marca='" + p.getMarca()
                + "',stock=" + p.getStock()
                + ",costo='" + p.getCosto() + "',descripcion='" + p.getDescripcion() + "' where id_producto=" + p.getID_PRODUCTO();
        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        smt.close();
    }

    public void modtarea(int id) throws ClassNotFoundException, SQLException {
        Statement smt;
        abrir();
        String sentenciaSQL = "update tareas set status='COMPLETADO' where id_tarea=" + id;
        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        smt.close();
    }

    public void modificarstock(ArrayList lista) throws ClassNotFoundException, SQLException {
        Statement smt;
        Connection c;
        abrir();
        int id = 0;
        productot p;
        int stock = 0;
        String sentenciaSQL = "";
        int cont = 0;
        for (int i = 0; i < lista.size(); i++) {
            if (cont == 3) {
                p = new productot();
                p = buscarproducto(Integer.parseInt(lista.get(i - 3).toString()));
                stock = p.getStock() - Integer.parseInt(lista.get(i - 1).toString());
                // uDB.abrir();
                sentenciaSQL = "update producto set stock=" + stock + " where id_producto =" + lista.get(i - 3);
                smt = conexion.createStatement();
                smt.executeUpdate(sentenciaSQL);
                // uDB.cerrar();
                cont = 0;
            } else {
                cont++;
            }
        }
        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        smt.close();

    }

    public void modificarstock_prov(ArrayList lista) throws ClassNotFoundException, SQLException {
        Statement smt=null;
        abrir();
        productot p;
        int stock = 0;
        String sentenciaSQL = "";
        int cont = 0;
        for (int i = 0; i < lista.size(); i++) {
            if (cont == 3) {
                p = new productot();
                p = buscarproducto(Integer.parseInt(lista.get(i - 3).toString()));
                stock = p.getStock() + Integer.parseInt(lista.get(i - 1).toString());
                sentenciaSQL = "update producto set stock=" + stock + " where id_producto =" + lista.get(i - 3);
                smt = conexion.createStatement();
                smt.executeUpdate(sentenciaSQL);
                cont = 0;
            } else {
                cont++;
            }
        }
        smt.close();
    }
    
    public void modificarstockt(ArrayList lista) throws ClassNotFoundException, SQLException {
        Statement smt;
        Connection c;
        abrir();
        int id = 0;
        productot p;
        int stock = 0;
        String sentenciaSQL = "";
        int cont = 0;
        for (int i = 0; i < lista.size(); i++) {
            if (cont == 3) {
                p = new productot();
                p = buscarproducto(Integer.parseInt(lista.get(i - 3).toString()));
                stock = p.getStock() - Integer.parseInt(lista.get(i - 1).toString());
                sentenciaSQL = "update producto set stock=" + stock + " where id_producto =" + lista.get(i - 3);
                smt = conexion.createStatement();
                smt.executeUpdate(sentenciaSQL);
                cont = 0;
            } else {
                cont++;
            }
        }
        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        smt.close();

    }

    public int buscarcliente() throws ClassNotFoundException, SQLException {
        Connection c;
        Statement smt;
        ResultSet rs;
        int id = 0;
        Usuario u = null;
        DB uDB = new DB();
        uDB.abrir();
        c = uDB.getConexion();
        String query = "SELECT id_cliente FROM CLIENTE";
        smt = c.createStatement();
        rs = smt.executeQuery(query);
        while (rs.next()) {
            id = Integer.parseInt(rs.getString("ID_CLIENTE"));
        }
        uDB.cerrar();
        return id;
    }

    public void buscarproduccion(String fi, String ff) throws ClassNotFoundException, SQLException {
        Connection c;
        ArrayList<Object> lista;
        Statement smt;
        ResultSet rs;
        int id = 0;
        Producion pr = new Producion();
        Usuario u = null;
        DB uDB = new DB();
        uDB.abrir();
        c = uDB.getConexion();
        String query = "SELECT p.nombre,u.nombre,prod.ganancia,prod.producido,prod.fecha FROM producto p JOIN production prod ON p.id_producto =prod.id_producto JOIN usuario u ON u.id_usuario=prod.id_usuario WHERE fecha BETWEEN '" + fi + "' AND '" + ff + "' ORDER BY u.nombre";
        smt = c.createStatement();
        rs = smt.executeQuery(query);
        while (rs.next()) {
            pr.setproducion(rs.getObject("u.nombre").toString(), rs.getObject("p.nombre").toString(), Integer.parseInt(rs.getObject("prod.producido").toString()), Float.parseFloat(rs.getObject("prod.ganancia").toString()), rs.getObject("prod.fecha").toString());
        }
        smt.close();
        lista = pr.getProd();
        int cont = 0;

    }

    public void buscarventa(String fi, String ff) throws ClassNotFoundException, SQLException {
        Connection c;
        Statement smt;
        ResultSet rs;
        venta v = new venta();
        Usuario u = null;
        DB uDB = new DB();
        uDB.abrir();
        c = uDB.getConexion();
        String query = " select * from venta where fecha BETWEEN '" + fi + "' AND '" + ff + "' ORDER BY id_venta ";   
        smt = c.createStatement();
        rs = smt.executeQuery(query);
        while (rs.next()) {
            v.setventas(Integer.parseInt(rs.getObject("id_venta").toString()), Float.parseFloat(rs.getObject("total").toString()), Integer.parseInt(rs.getObject("cantidad").toString()), rs.getObject("fecha").toString(), rs.getObject("hora").toString());
        }
        smt.close();
    }

    public Usuario buscarusuvta() throws SQLException, ClassNotFoundException {
        Statement smt;
        Connection c;
        ResultSet rs;
        Usuario u = null;
        DB uDB = new DB();
        uDB.abrir();
        c = uDB.getConexion();
        String sentenciaSQL = "SELECT * FROM usuario WHERE tipo='ADMIN'";
        smt = c.createStatement();
        rs = smt.executeQuery(sentenciaSQL);
        while (rs.next()) {
            u = new Usuario();
            u.setId(Integer.parseInt(rs.getString("ID_USUARIO")));
        }
        uDB.cerrar();
        return u;

    }

    public void eliminarusuario(int u) throws ClassNotFoundException, SQLException {
        Statement smt;
        abrir();
        int id = u;
        String sentenciaSQL = "DELETE from usuario where ID_USUARIO =("+ id + ")";
        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        cerrar();
    }

    public void eliminarprove(Proveedor u) throws ClassNotFoundException, SQLException {
        Statement smt;
        abrir();
        int id = u.getId();
        String sentenciaSQL = "DELETE from PROVEEDOR where ID =(" + id + ")";
        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        cerrar();
    }

    public void eliminarcliente(Cliente u) throws ClassNotFoundException, SQLException {
        Statement smt;
        abrir();
        String sentenciaSQL = "DELETE from CLIENTES where ID_CLIENTE =(id  )";
        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        cerrar();
    }

    public void modificarprove(Proveedor p) throws ClassNotFoundException, SQLException {
        Statement smt;
        abrir();
        String sentenciaSQL = "update proveedor set nombre='" + p.getNombre()
                + "',tel='" + p.getTel()
                + "',calle='" + p.getCalle()
                + "',colonia='" + p.getColonia() + "' where id_proveedor=" + p.getId();
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
        smt = c.createStatement();
        smt.executeUpdate(sentenciaSQL);
    }

    public int buscarvta() throws SQLException {
        int id = 0;
        Statement smt;
        ResultSet rs;
        String query = "SELECT max(id_venta) as lol FROM venta";
        smt = conexion.createStatement();
        rs = smt.executeQuery(query);
        while (rs.next()) {
            id = rs.getInt("lol");
        }
        return id;
    }

    public int buscarfacturat() throws SQLException {
        int id = 0;
        Statement smt;
        ResultSet rs;
        String query = "SELECT max(ID_FACTURA) as lol FROM factura";
        smt = conexion.createStatement();
        rs = smt.executeQuery(query);
        while (rs.next()) {
            id = rs.getInt("lol");
        }
        return id;
    }
    public int buscarfacturat_prov() throws SQLException, ClassNotFoundException {
        int id = 0;
        Statement smt;
        ResultSet rs;
        abrir();
        String query = "SELECT max(ID_FACT_IN) as folio FROM fact_prov";
        smt = conexion.createStatement();
        rs = smt.executeQuery(query);
        while (rs.next()) {
            id = rs.getInt("folio");
        }
        return id;
    }

    public void agregarventa(venta v) throws SQLException, ClassNotFoundException {
        Statement smt;
        int id = 0, ids = 0;
        DB uDB = new DB();
        String query = "insert into venta values(" + id + "," + v.getId_cliente() + ","
                + v.getId_usuario() + ",'" + v.getFecha() + "','" + v.getInhr() + "',"
                + v.getTotal() + ",'" + v.getStatus() + "'," + v.getCantidad() + ")";
        smt = conexion.createStatement();
        smt.executeUpdate(query);
        smt.close();
    }

    public void agregarfacturat(factura t) throws SQLException, ClassNotFoundException {
        Statement smt;
        int id = 0;
        abrir();
        String query = "insert into factura values(" + id + "," + t.getID_USUARIO_() + ","
                + t.getID_USUARIOC() + "," + t.getCantidad() + ",'" + t.getFecha() + "',"
                + t.getTotal() + ",'" + t.getStatus() + "','" + t.getTipo() + "')";
        smt = conexion.createStatement();
        smt.executeUpdate(query);
        smt.close(); 
    }
    public void agregarfacturat(entrada_prov t) throws SQLException, ClassNotFoundException {
        Statement smt;
        int id = 0;
        abrir();
        String query = "insert into fact_prov values(" + id + "," + t.getID_PROVEEDOR()+ ",'"
                + t.getUsuario() + "','" + t.getFecha() + "','" + t.getHora() + "',"
                + t.getCantidad() + "," + t.getTotal()+",'"+t.getReferencia()+"')";
        smt = conexion.createStatement();
        smt.executeUpdate(query);
        smt.close();
    }

    public void agregarproducto(Producto u) throws ClassNotFoundException, SQLException {
        Statement smt;
        abrir();
        int id = 0;
        String nombre = u.getNombre();
        float preciomay = u.getCostomay();
        float preciomen = u.getCostomin();
        String desc = u.getDescrip();
        int stock = u.getStock();
        String tipo = u.getTipo();
        float precioprod = u.getCostoprod();
        String ruta = u.getUrl();
        String sentenciaSQL = "INSERT INTO producto VALUES(" + id + ","
                + "'" + nombre + "'," + preciomay + "," + preciomen + "," + precioprod + ",'" + desc + "'," + stock
                + ",'" + tipo + "','" + ruta + "','" + u.getHabilitado() + "')";
        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        cerrar();
    }

    public void eliminarproducto(productot u) throws ClassNotFoundException, SQLException {
        Statement smt;
        abrir();
        String sentenciaSQL = "DELETE from producto where id_producto=(" + u.getID_PRODUCTO() + ")";
        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        cerrar();
    }

    public void eliminarproveedor(int id) throws ClassNotFoundException, SQLException {
        Statement smt;
        abrir();
        String sentenciaSQL = "DELETE from proveedor where id_proveedor=(" + id + ")";
        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        smt.close();
    }

    public productot buscarproducto(int id) throws ClassNotFoundException, SQLException {
        Connection c;
        Statement smt;
        ResultSet rs;
        productot u = null;
        abrir();
        c = getConexion();        
        String sentenciaSQL = "SELECT * FROM producto WHERE id_producto=" + id;
        smt = c.createStatement();
        rs = smt.executeQuery(sentenciaSQL);
        while (rs.next()) {
            u = new productot();
            u.setID_PRODUCTO(Integer.parseInt(rs.getString("ID_PRODUCTO")));
            u.setNombre(rs.getString("nombre"));
            u.setCosto(Double.parseDouble(rs.getString("costo")));
            u.setStock(Integer.parseInt(rs.getString("stock")));
            u.setModelo(rs.getString("modelo"));
        }
        rs.close();
        return u;
    }

    public productot buscarproductot(int id) throws ClassNotFoundException, SQLException {
        Connection c;
        Statement smt;
        ResultSet rs;
        productot u = null;
        abrir();
        c = getConexion();     
        String sentenciaSQL = "SELECT * FROM producto WHERE ID_PRODUCTO=" + id;
        smt = c.createStatement();
        rs = smt.executeQuery(sentenciaSQL);
        while (rs.next()) {
            u = new productot();
            u.setID_PRODUCTO(Integer.parseInt(rs.getString("ID_PRODUCTO")));
            u.setNombre(rs.getString("nombre"));
            u.setCosto(Double.parseDouble(rs.getString("costo")));
            u.setStock(Integer.parseInt(rs.getString("stock")));
            u.setModelo(rs.getString("modelo"));
        }
        rs.close();
        return u;
    }

    public static LinkedList<Producto> getContactos() {
        LinkedList<Producto> listaContactos = new LinkedList<Producto>();
        try {
            DB uDB = new DB();
            Connection c;
            uDB.abrir();
            Statement smt;
            ResultSet rs;
            c = uDB.getConexion();
            String sentenciaSQL = "SELECT * FROM PRODUCTO";
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
        Statement smt;
        ResultSet rs;
        Cliente p = null;
        DB uDB = new DB();
        //--Abrir BD
        uDB.abrir();
        c = uDB.getConexion();
        //--Ejecutar la busqueda
        String sentenciaSQL = "SELECT * FROM CLIENTES WHERE ID_CLIENTE=" + id;
        smt = c.createStatement();
        rs = smt.executeQuery(sentenciaSQL);
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
        Statement smt;
        ResultSet rs;
        Proveedor p = null;
        DB uDB = new DB();
        //--Abrir BD
        uDB.abrir();
        c = uDB.getConexion();
        //--Ejecutar la busqueda
        String sentenciaSQL = "SELECT * FROM PROVEEDOR WHERE ID=" + id;
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
