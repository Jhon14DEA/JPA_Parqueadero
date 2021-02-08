/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.excepcion;

/**
 *
 */
public class RequeridoExcepcion extends  Exception{
    public static String mensaje = "Los datos no puueden ser vacios";
    public RequeridoExcepcion(String campo) {
        super(campo+":"+ mensaje);
    }
    
}
