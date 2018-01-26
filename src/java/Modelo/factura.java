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
public class factura {
int ID_FACTURA,ID_USUARIO_,ID_PRODUCTO,ID_DEPA,cantidad,ID_USUARIOC;

    public int getID_USUARIOC() {
        return ID_USUARIOC;
    }

    public void setID_USUARIOC(int ID_USUARIOC) {
        this.ID_USUARIOC = ID_USUARIOC;
    }
String fecha,status,tipo;
double total;

    public int getID_FACTURA() {
        return ID_FACTURA;
    }

    public void setID_FACTURA(int ID_FACTURA) {
        this.ID_FACTURA = ID_FACTURA;
    }

    public int getID_USUARIO_() {
        return ID_USUARIO_;
    }

    public void setID_USUARIO_(int ID_USUARIO_) {
        this.ID_USUARIO_ = ID_USUARIO_;
    }

    public int getID_PRODUCTO() {
        return ID_PRODUCTO;
    }

    public void setID_PRODUCTO(int ID_PRODUCTO) {
        this.ID_PRODUCTO = ID_PRODUCTO;
    }

    public int getID_DEPA() {
        return ID_DEPA;
    }

    public void setID_DEPA(int ID_DEPA) {
        this.ID_DEPA = ID_DEPA;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
