/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.excepcion;

/**
 *
 */
public class FloatExcepcion extends Exception{
    public static String mensaje = "Los costos deben ser valores enteros o decimales";
    
    public FloatExcepcion() {
        super(mensaje);
    }
    
}
