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
public class Salida {
    String Fecha,Estatus,Tipo,Clase;
    int folio,vale,Foliosalida;

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public int getVale() {
        return vale;
    }

    public void setVale(int vale) {
        this.vale = vale;
    }

    public int getFoliosalida() {
        return Foliosalida;
    }

    public void setFoliosalida(int Foliosalida) {
        this.Foliosalida = Foliosalida;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getEstatus() {
        return Estatus;
    }

    public void setEstatus(String Estatus) {
        this.Estatus = Estatus;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getClase() {
        return Clase;
    }

    public void setClase(String Clase) {
        this.Clase = Clase;
    }
}
