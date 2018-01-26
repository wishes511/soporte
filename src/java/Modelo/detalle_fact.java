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
public class detalle_fact {
    int ID_DETALLEFACT,ID_FACTURA,ID_PRODUCTO,cantidad;

    public int getID_DETALLEFACT() {
        return ID_DETALLEFACT;
    }

    public void setID_DETALLEFACT(int ID_DETALLEFACT) {
        this.ID_DETALLEFACT = ID_DETALLEFACT;
    }

    public int getID_FACTURA() {
        return ID_FACTURA;
    }

    public void setID_FACTURA(int ID_FACTURA) {
        this.ID_FACTURA = ID_FACTURA;
    }

    public int getID_PRODUCTO() {
        return ID_PRODUCTO;
    }

    public void setID_PRODUCTO(int ID_PRODUCTO) {
        this.ID_PRODUCTO = ID_PRODUCTO;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
