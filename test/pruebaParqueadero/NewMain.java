/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaParqueadero;

import ec.edu.ups.interfaz.IValidar;
import ec.edu.ups.validar.ValidarFabrica;

/**
 *
 * @author ASUS
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ValidarFabrica fabrica = new ValidarFabrica();
        IValidar validamail = fabrica.getTipo("texto");
        System.out.println(validamail.validar("benigno malo 637"));
    }    
}
