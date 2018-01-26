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
public class Proveedor {
    private int id;
    private String nombre,tel,calle,colonia;
    static ArrayList<Object> lista = new ArrayList<Object>();
    static ArrayList<Object> listaid = new ArrayList<Object>();

    public int getId() {
        return id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getApellido() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

static public ArrayList<Object> getIdpro(){       
        return lista;
    }
        public void setIdpro(ArrayList lista1) {
    try {
       
lista=lista1;
        
        System.out.println(lista.size()+"/"+lista.get(0)+"/"+lista.get(1));
    } catch (Exception ex) {
         System.out.println(ex);
    } 
        this.lista = lista;
}
   
   public void vaciarIDpro(){

lista.clear();
        }
}
