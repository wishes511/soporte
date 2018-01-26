/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author mich
 */
public class Producto {

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }
    private int id,stock,cantidad;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }
    private String nombre,tipo,url,descrip,actividad,habilitado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getCostomay() {
        return costomay;
    }

    public void setCostomay(float costomay) {
        this.costomay = costomay;
    }

    public float getCostomin() {
        return costomin;
    }

    public void setCostomin(float costomin) {
        this.costomin = costomin;
    }
    private float costomay, costomin,costoprod;

    public float getCostoprod() {
        return costoprod;
    }

    public void setCostoprod(float costoprod) {
        this.costoprod = costoprod;
    }
}
