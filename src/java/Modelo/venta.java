/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controladores.Venta;
import static Modelo.Producion.lista;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mich
 */
public class venta {
    private int id,id_cliente,id_usuario,id_producto,cantidad;
    private String fecha,status,inhr,outhr;
    private float total;
    static ArrayList<Object> lista = new ArrayList<Object>();
    static ArrayList<Object> listaid = new ArrayList<Object>();

    public int getId() {
        return id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
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

    public String getInhr() {
        return inhr;
    }

    public void setInhr(String inhr) {
        this.inhr = inhr;
    }

    public String getOuthr() {
        return outhr;
    }

    public void setOuthr(String outhr) {
        this.outhr = outhr;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    static public ArrayList<Object> getVenta(){       
        return lista;
    }

   public void setventas(int id, float tot, int cant,String fecha, String h) {
    try {
       

//        float sub = cant * costo;
//        lista.add(id);
//        lista.add(n);
//        lista.add(cant);
//        lista.add(sub);
        lista.add(id);
        lista.add(tot);
        lista.add(cant);
        lista.add(fecha);
        lista.add(h);

        
        //System.out.println("li "+n); 
        
        System.out.println(lista.size()+"/"+lista.get(0)+"/"+lista.get(1)+"/"+lista.get(2)+"/"+lista.get(3)+"/");
    } catch (Exception ex) {
        Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
    } 
        this.lista = lista;
}
   public void vaciarventa(){

lista.clear();
        }
   
   static public ArrayList<Object> getIds(){       
        return listaid;
    }
        public void setIds(ArrayList lista1) {
    try {
       
listaid=lista1;
        
        System.out.println(listaid.size()+"/"+listaid.get(0)+"/"+listaid.get(1)+"/"+listaid.get(2)+"/"+listaid.get(3)+"/");
    } catch (Exception ex) {
         System.out.println(ex);
    } 
        this.listaid = listaid;
}
   
   public void vaciarIDs(){

listaid.clear();
        }
   
   
}
