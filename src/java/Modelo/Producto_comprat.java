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
public class Producto_comprat {
   static ArrayList<Object> lista = new ArrayList<Object>();
    
static public ArrayList<Object> getProd(){       
        return lista;
    }

   public void setprods(int id, int cant, double costo, String n) {
    try {
          //Producto p = new Producto();
//        Producto p2= new Producto();
        //p=bd.buscarproducto(id);
        double sub = cant * costo;
        lista.add(id);
        lista.add(n);
        lista.add(cant);
        lista.add(sub);
     } catch (Exception ex) {
        Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
    } 
        this.lista = lista;
}
   
public void delprod(int id){
    ArrayList<Object> listas = new ArrayList<Object>();
       int lol = 4* id;
       int lala= lol-4;
      // System.out.println("PRIMERA ENTRADA/lala="+lala+"/lol ="+lol+"/size"+lista.size()+"!"+lista.get(lala));
       
       if(lala!=0 || lol != lista.size()){
           for(int i =0; i<lala;i++){
       //      System.out.println("$$$"+lista.get(i));
             listas.add(lista.get(i));        
       }
            for(int i =lol; i<lista.size();i++){
     //        System.out.println("$$$"+lista.get(i));
             listas.add(lista.get(i));        
       }
       }
       lista.clear();
       
       lista=listas;
   }
public void vaciar_carro(){

lista.clear();
        }


// con session

   public ArrayList<Object> setprodssesion(int id, int cant, double costo, String n,ArrayList<Object> carro) {
    try {
        double sub = cant * costo;
        carro.add(id);
        carro.add(n);
        carro.add(cant);
        carro.add((float)sub);
        //System.out.println("li "+n); 
        
        System.out.println(carro.size()+"/"+carro.get(0)+"/"+carro.get(1)+"/"+carro.get(2)+"/"+carro.get(3)+"/"+sub);
    } catch (Exception ex) {
        Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
    } 
    return carro;
}
   public ArrayList<Object> delprodsesion(int id,ArrayList<Object> carro){
    ArrayList<Object> listas = new ArrayList<>();
       int lol = 4* id;
       int lala= lol-4;
       //System.out.println("PRIMERA ENTRADA/lala="+lala+"/lol ="+lol+"/size"+carro.size()+"!"+carro.get(lala));
       
       if(lala!=0 || lol != carro.size()){
           for(int i =0; i<lala;i++){
         //    System.out.println("$$$"+carro.get(i));
             listas.add(carro.get(i));        
       }
            for(int i =lol; i<carro.size();i++){
           //  System.out.println("$$"+carro.get(i));
             listas.add(carro.get(i));        
       }
            
       }
       carro.clear();
       carro=listas;
     // System.out.println("size2"+carro.size());
       return carro;
   }
   }