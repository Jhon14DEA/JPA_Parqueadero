/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.excepcion;

/**
 *
 */
public class PlacaExcepcion extends Exception{
    public static String mensaje = "EL campo placa debe contener tres letras y cuatro números";
    
    public PlacaExcepcion() {
        super(mensaje);
    }
    
}
