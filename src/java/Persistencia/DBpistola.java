/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author gateway1
 */
public class DBpistola {
     String url = "jdbc:sqlserver://192.168.6.75:1433;" +
            "databaseName=RCPT;user=mich; password=mich;";  
     String drive ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    // Declaramos los sioguientes objetos
    Connection conexion = null;
    Statement stmt = null;
    ResultSet rs = null;
    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public void abrir() throws ClassNotFoundException, SQLException {
        Class.forName(drive);
        conexion = DriverManager.getConnection(url,"mich","mich");
        System.out.println("hecho :]");
    }

    public void cerrar() throws SQLException {
        conexion.close();
    }
public static void main(String [] argos) throws ClassNotFoundException, SQLException{
    DBpistola dv =new DBpistola();
dv.abrir();
}
   

}
