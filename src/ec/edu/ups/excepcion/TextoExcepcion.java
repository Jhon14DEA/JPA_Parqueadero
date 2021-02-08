/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.excepcion;

/**
 *
 */
public class TextoExcepcion extends Exception{
    public static String mensaje = "EL campo texto no debe contener caracteres especiales o numeros";
    
    public TextoExcepcion() {
        super(mensaje);
    }
    
}
