/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controladores.Venta;
import Persistencia.DB;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mich
 */
public class Producto_compra {
   static ArrayList<Object> lista = new ArrayList<Object>();
    
static public ArrayList<Object> getProd(){       
        return lista;
    }

   public void setprods(int id, int cant, float costo, String n) {
    try {
       
        DB bd = new DB();
          //Producto p = new Producto();
//        Producto p2= new Producto();
        //p=bd.buscarproducto(id);
        float sub = cant * costo;
        lista.add(id);
        lista.add(n);
        lista.add(cant);
        lista.add(sub);
        
        //lista.add(sub);
////        p2.setId(p.getId());
////        p2.setNombre(p.getNombre());
////        p2.setCostomay(p.getCostomay());
////        p2.setCantidad(cant);
        
        System.out.println("li "+n); 
        
        System.out.println(lista.size()+"/"+lista.get(0)+"/"+lista.get(1)+"/"+lista.get(2)+"/"+lista.get(3)+"/"+sub);
    } catch (Exception ex) {
        Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
    } 
        this.lista = lista;
}
   
public void delprod(int id){
    ArrayList<Object> listas = new ArrayList<Object>();
       int lol = 4* id;
       int lala= lol-4;
       System.out.println("PRIMERA ENTRADA/lala="+lala+"/lol ="+lol+"/size"+lista.size()+"!"+lista.get(lala));
       
       if(lala!=0 || lol != lista.size()){
           for(int i =0; i<lala;i++){
             System.out.println("$$$"+lista.get(i));
             listas.add(lista.get(i));        
       }
            for(int i =lol; i<lista.size();i++){
             System.out.println("$$$"+lista.get(i));
             listas.add(lista.get(i));        
       }
       }
       lista.clear();
       
       lista=listas;

     
   }
public void vaciar_carro(){

lista.clear();
        }
}
