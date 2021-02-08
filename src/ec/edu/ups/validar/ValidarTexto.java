/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.validar;

import ec.edu.ups.interfaz.IValidar;

/**
 *
 */
public class ValidarTexto implements IValidar{
    @Override
    public boolean validar(String texto) {
        return texto.matches("[a-zA-Z\\s]+");
    }    
}
