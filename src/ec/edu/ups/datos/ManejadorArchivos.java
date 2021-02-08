/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.datos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @param <T>
 */
public class ManejadorArchivos<T> {
    private static ManejadorArchivos instancia;
    private ObjectOutputStream salida;//envía los datos a un archivo
    ObjectInputStream entrada;//lee datos de un archivo
    private List<T> listado;
    private Class<T> tipo;
    //permite al usuario epsecificar el nombre del archivo
    private ManejadorArchivos(String nombreArchivo){
        try
        {
            salida = new ObjectOutputStream(new FileOutputStream(nombreArchivo));
            entrada = new ObjectInputStream(new FileInputStream(nombreArchivo));
        }catch(IOException ioException){
            System.err.print("Error al abrir el archivo: "+ioException.getMessage());
        }
    }
    public static ManejadorArchivos getInstancia(String nombreArchivo)
    {
        if(instancia == null)
            instancia = new ManejadorArchivos(nombreArchivo);
        return instancia;
    }

    public ObjectOutputStream getSalida() {
        return salida;
    }
    public <T> boolean Escribir()
    {
        try {
            for (int i = 0; i < listado.size(); i++) {
                try {
                    salida.writeObject(listado.get(i));
                } catch (IOException ex) {
                    Logger.getLogger(ManejadorArchivos.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
            salida.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ManejadorArchivos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean Leer()
    {
        try {
            // Se lee el primer objeto
            T aux = (T) entrada.readObject();
// Mientras haya objetos
            while (aux != null) {
                try{
                listado.add(aux);
                aux = (T) entrada.readObject();
                }catch(Exception ex){break;}
            }
            entrada.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ManejadorArchivos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManejadorArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<T> getListado() {
        return listado;
    }

    public void setListado(List<T> listado) {
        this.listado = listado;
    }
        
}
