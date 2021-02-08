/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistema;

import ec.edu.ups.controlador.ControladorPrincipal;
import ec.edu.ups.vista.jfPrincipal;
/**
 *
 */
public class sistemaparqueadero {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            // TODO code application logic here
        jfPrincipal frm = new jfPrincipal();
        ControladorPrincipal controladorprincipal = new ControladorPrincipal(frm);
        controladorprincipal.iniciar();

        
    }
    
}
