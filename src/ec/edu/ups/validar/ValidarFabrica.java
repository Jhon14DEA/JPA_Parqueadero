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
public class ValidarFabrica {
    public IValidar getTipo(String Tipo)
    {
        switch(Tipo)
        {
            case "email": return new ValidarEmail();
            case "cedula": return new ValidarCedula();
            case "entero": return new ValidarEntero();
            case "texto": return new ValidarTexto();
            case "float": return new ValidarFloat();
            case "placa": return new ValidarPlaca();
            default:return null;
        }
    }
    
}
