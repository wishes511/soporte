/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controladores.Venta;
import Persistencia.DBt;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mich
 */
public class Producion {
    private int id,id_usuario,id_producto,producido;
           static ArrayList<Object> lista = new ArrayList<Object>();
  

    public int getId() {
        return id;
    }

    public int getProducido() {
        return producido;
    }

    public void setProducido(int producido) {
        this.producido = producido;
    }

   

    public void setId(int id) {
        this.id = id;
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

    public String getTipo_prod() {
        return tipo_prod;
    }

    public void setTipo_prod(String tipo_prod) {
        this.tipo_prod = tipo_prod;
    }

    public float getGanancia() {
        return ganancia;
    }

    public void setGanancia(float ganancia) {
        this.ganancia = ganancia;
    }
    private String fecha,tipo_prod;
    private float ganancia;
    

    
static public ArrayList<Object> getProd(){       
        return lista;
    }

   public void setproducion(String nn, String np, int pro,float ganancia, String f) {
    try {
       

//        float sub = cant * costo;
//        lista.add(id);
//        lista.add(n);
//        lista.add(cant);
//        lista.add(sub);
        lista.add(nn);
        lista.add(np);
        lista.add(pro);
        lista.add(ganancia);
        lista.add(f);

        
        //System.out.println("li "+n); 
        
        System.out.println(lista.size()+"/"+lista.get(0)+"/"+lista.get(1)+"/"+lista.get(2)+"/"+lista.get(3)+"/");
    } catch (Exception ex) {
        Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
    } 
        this.lista = lista;
}
   public void vaciarprodu(){

lista.clear();
        }
    
}
