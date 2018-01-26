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
public class proveedores {
    private String Nombre_prove,activo,fecha,codigo;
    private int id_prove, id_es;

    public String getNombre_prove() {
        return Nombre_prove;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre_prove(String Nombre_prove) {
        this.Nombre_prove = Nombre_prove;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId_prove() {
        return id_prove;
    }

    public void setId_prove(int id_prove) {
        this.id_prove = id_prove;
    }

    public int getId_es() {
        return id_es;
    }

    public void setId_es(int id_es) {
        this.id_es = id_es;
    }
    
}
