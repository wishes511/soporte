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
public class codigopersis {
 static private int almacen=0;

    public int getAlmacen() {
        return almacen;
    }

    public void setAlmacen(int almacen) {
        this.almacen = almacen;
    }

    public boolean isFactor() {
        return factor;
    }

    public void setFactor(boolean factor) {
        this.factor = factor;
    }

   public boolean isStock() {
        return stock;
    }

    public void setStock(boolean stock) {
        this.stock = stock;
    }
 static private boolean factor = true;
 static private boolean stock=true;
}
