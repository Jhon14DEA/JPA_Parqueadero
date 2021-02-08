/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.excepcion;

/**
 *
 */
public class CedulaExcepcion extends Exception{
    public static String mensaje = "La cedula es invalida,Recuerde ingresar los 10 digitos sin guion";
    
    public CedulaExcepcion() {
        super(mensaje);
    }
    
}
