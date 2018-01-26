package Persistencia;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Persistencia.*;
import Modelo.Usuario;
import Modelo.virtual;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author
 */
public class persistencia_virtual {

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

    public boolean Checkip(int ip) throws ClassNotFoundException, SQLException {
        boolean estado = false;
        abrir();
        Statement smt;
        ResultSet rs;
        int ip1=0;
        int p2=0;
        if(ip >=0 && ip <=255){
        String query = "select ip from virtuales where ip="+ip;
        //System.out.println(query);
        smt = conexion.createStatement();
        rs = smt.executeQuery(query);
        while (rs.next()) {
            estado = true;
            ip1++;
        }
        query = "select ip from usuario where ip="+ip;
        //System.out.println(query);
        smt = conexion.createStatement();
        rs = smt.executeQuery(query);
        while (rs.next()) {
            estado = true;
            p2++;
        }
        if(ip1==1 || p2==1){
            estado=false;
        }
        }else estado=true;
        
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
        System.out.println(query);
        smt = c.createStatement();
        rs = smt.executeQuery(query);
        while (rs.next()) {
            //total=total+ Float.parseFloat(rs.getString("monto"));
            lista.add(rs.getString("c.id_compraprod").toString());
        }

        smt.close();
        return lista;
    }

    public void agregarvirtual(virtual v) throws ClassNotFoundException, SQLException {
        try{
             PreparedStatement smt =null;
        int a = 0;
        abrir();
        String sentenciaSQL = "INSERT INTO virtuales VALUES("+a+","+v.getUsuario()+",'"+v.getNombre()+"',"+v.getIp()+")";
        smt = conexion.prepareStatement(sentenciaSQL);
        smt.executeUpdate(sentenciaSQL);
        conexion.commit();
        smt.close();
        }catch(Exception e){
        Logger.getLogger(persistencia_virtual.class.getName()).log(Level.SEVERE, null, e);
            try {
                conexion.rollback();
            } catch (Exception o) {
                System.out.println(o.getMessage());
            }
    }  
    }

    public void eliminar_virtual(int id) throws ClassNotFoundException, SQLException {
        PreparedStatement st = null;
        try{//modificar status de programa
             abrir();
            conexion.setAutoCommit(false);
            String s = "delete from virtuales where id_virtual="+id;
            st = conexion.prepareStatement(s);
            st.executeUpdate();
            conexion.commit();
            st.close();
    }catch(Exception e){
        Logger.getLogger(persistencia_virtual.class.getName()).log(Level.SEVERE, null, e);
            try {
                conexion.rollback();
            } catch (Exception o) {
                System.out.println(o.getMessage());
            }
    }

    }

    public void actualizar_virtual(virtual v) throws ClassNotFoundException, SQLException {
         try{
             PreparedStatement smt =null;
        int a = 0;
        abrir();
        conexion.setAutoCommit(false);
        String sentenciaSQL = "update virtuales set ID_USUARIO="+v.getUsuario()+" , ip="+v.getIp()+" where id_virtual="+v.getId();
        smt = conexion.prepareStatement(sentenciaSQL);
        
        smt.executeUpdate(sentenciaSQL);
        conexion.commit();
        smt.close();
        }catch(Exception e){
        Logger.getLogger(persistencia_virtual.class.getName()).log(Level.SEVERE, null, e);
            try {
                conexion.rollback();
            } catch (Exception o) {
                System.out.println(o.getMessage());
            }
    } 
    }

    public void modiproduct(virtual v) throws SQLException, ClassNotFoundException {
        Statement smt;
        Connection c;
        abrir();
        String sentenciaSQL = "update producto set nombre='";

        System.out.print(sentenciaSQL);

        smt = conexion.createStatement();
        smt.executeUpdate(sentenciaSQL);
        smt.close();
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
