/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author mich
 */
public class Caja {
    static ArrayList<Object> lista = new ArrayList<Object>();
    static ArrayList<Object> listai = new ArrayList<Object>();
     static ArrayList<Object> listag = new ArrayList<Object>();
    
        static public ArrayList<Object> getCaja(){       
        return lista;
    }

   public void setcaja(float in, float out, int pv,int pc, int pp,int vr) {
    try {
       

//        float sub = cant * costo;
//        lista.add(id);
//        lista.add(n);
//        lista.add(cant);
//        lista.add(sub);
        lista.add(in);
        lista.add(out);
        lista.add(pv);
        lista.add(pc);
        lista.add(pp);
        lista.add(vr);

        
        //System.out.println("li "+n); 
        
        System.out.println(lista.size()+"/"+lista.get(0)+"/"+lista.get(1)+"/"+lista.get(2)+"/"+lista.get(3)+"/");
    } catch (Exception ex) {
         System.out.println(ex);
    } 
        this.lista = lista;
}
   
      public void setcajaarray(ArrayList lista1) {
    try {
       
lista=lista1;
        
        System.out.println(lista.size()+"/"+lista.get(0)+"/"+lista.get(1)+"/"+lista.get(2)+"/"+lista.get(3)+"/");
    } catch (Exception ex) {
         System.out.println(ex);
    } 
        this.lista = lista;
}
   
   public void vaciarCaja(){

lista.clear();
        }
   // metodos para ingresos y gastos
     static public ArrayList<Object> getI(){       
        return listai;
    }
           public void setI(ArrayList lista1) {
    try {
       
listai=lista1;
        
        System.out.println(listai.size()+"/"+listai.get(0)+"/"+listai.get(1)+"/"+listai.get(2)+"/"+listai.get(3)+"/");
    } catch (Exception ex) {
         System.out.println(ex);
    } 
        this.listai = listai;
}
   
   public void vaciarI(){

listai.clear();
        }
   
        static public ArrayList<Object> getG(){       
        return listag;
    }
           public void setG(ArrayList lista1) {
    try {
       
listag=lista1;
        
        System.out.println(listag.size()+"/"+listag.get(0)+"/"+listag.get(1)+"/"+listag.get(2)+"/"+listag.get(3)+"/");
    } catch (Exception ex) {
         System.out.println(ex);
    } 
        this.listag = listag;
}
   
   public void vaciarG(){

listag.clear();
        }
}

