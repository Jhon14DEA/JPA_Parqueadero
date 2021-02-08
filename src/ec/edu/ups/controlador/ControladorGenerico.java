/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public abstract class ControladorGenerico<E>{
    private List<E> listado;
    private Class<E> tipo;


    public ControladorGenerico( Class<E> tipo) {
        this.listado = new ArrayList<>();
        this.tipo = tipo;
        this.listado = findAll();
    }
    
    
    protected boolean crear(E obj){
       return listado.add(obj);
    }
    
    protected boolean eliminar(E obj){
         return listado.remove(obj);  
    }
    
    protected E buscar(E objeto){
        return listado.stream().filter(obj -> obj.equals(objeto)).findFirst().get();

    }
     protected boolean actualizar(E obj, E nuevoObjeto){
        E objeto = buscar(obj);
         for (int i = 0; i < listado.size(); i++) {
             if (listado.get(i)== objeto){
                 listado.set(i, nuevoObjeto);
                 return true;
                 
             }
             }
         return false;
     }
    protected abstract List<E> findAll();

    protected List<E> getListado() {
        return listado;
    }

    protected void setListado(List<E> listado) {
        this.listado = listado;
    }

    protected Class<E> getTipo() {
        return tipo;
    }

    protected void setTipo(Class<E> tipo) {
        this.tipo = tipo;
    }
   
}
