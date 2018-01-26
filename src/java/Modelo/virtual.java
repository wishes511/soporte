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
public class virtual {
    String nombre;
    int ip,usuario,id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIp() {
        return ip;
    }

    public void setIp(int ip) {
        this.ip = ip;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }
    public void setallvirtual(int usuario, int ip, String nombre){
        this.usuario=usuario;
        this.ip=ip;
        this.nombre=nombre;
    }
    public void setallvirtual(int usuario, int ip, int id){
        this.usuario=usuario;
        this.ip=ip;
        this.id=id;
    
    }
}
