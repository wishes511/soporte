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
public class pistola_cod {
static ArrayList<Object> lista = new ArrayList<Object>();
    
public pistola_cod(){
if(lista.isEmpty()){
System.out.println("no hay nada perringo :V");
}
}

public ArrayList<Object> getCod(){       
        return lista;
    }

   public void setcods(String cod, String almacen, String factor) {
    try {
//        if(tipo.equals("Stock")){
//        tipo="S";
//        }else{
//        tipo="P";
//        }
        lista.add(cod);
        lista.add(almacen);
        lista.add(factor);
        
        System.out.println("li "); 
        
        System.out.println(lista.size()+"/"+lista.get(0)+"/"+lista.get(1)+"/"+lista.get(2)+"/pistolacod");
    } catch (Exception ex) {
    System.out.println(ex);
    } 
        this.lista = lista;
}
   
public void vaciar_codigos(){

lista.clear();
        }
}
