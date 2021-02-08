/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.excepcion;

/**
 *
 */
public class EmailExcepcion extends Exception{
    public static String mensaje = "El email ingresado no tiene formato correcto";
    
    public EmailExcepcion() {
        super(mensaje);
    }
    
}
