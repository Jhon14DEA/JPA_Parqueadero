/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.excepcion;

/**
 *
 */
public class TelefonoExcepcion extends Exception{
    public static String mensaje = "Debe ingresar solo numeros en el campo telefono";
    
    public TelefonoExcepcion() {
        super(mensaje);
    }
    
}
