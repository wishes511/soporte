/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author gateway1
 */
public class usuariot {

    public int getID_USUARIO() {
        return ID_USUARIO;
    }

    public void setID_USUARIO(int ID_USUARIO) {
        this.ID_USUARIO = ID_USUARIO;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public void setall(int ID_USUARIO,String usuario,String nombre, String apellido,
            String tipo, String contrasena,String ip, String namedepa){
    this.ID_USUARIO=ID_USUARIO;
    this.usuario=usuario;
    this.nombre=nombre;
    this.apellido=apellido;
    this.tipo=tipo;
    this.contrasena=contrasena;
    this.ip=ip;
    this.namedepa=namedepa;
    }
int ID_USUARIO;

    public String getNamedepa() {
        return namedepa;
    }

    public void setNamedepa(String namedepa) {
        this.namedepa = namedepa;
    }
String usuario,nombre,apellido,tipo,activo,contrasena,ip,namedepa;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
