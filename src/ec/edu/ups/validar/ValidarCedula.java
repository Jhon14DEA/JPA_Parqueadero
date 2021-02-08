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
public class ValidarCedula implements IValidar{

    @Override
    public boolean validar(String texto) {
        return validarCedula(texto);
    }
    public boolean validarCedula(String cedula) {
        char digito[] = cedula.toCharArray();
        int total = 0;
        for (int i = 0; i < digito.length - 1; i++) {
            int dato = Integer.parseInt(digito[i] + "");
            if (i % 2 == 0) {
                if (dato * 2 > 9) {
                    dato = (dato * 2) - 9;
                } else {
                    dato = dato * 2;
                }
            }
            total += dato;
        }
        int ultimo = Integer.parseInt(digito[digito.length - 1] + "");
        if (total % 10 == 0 && 0 == ultimo) {
            return true;
        } else {
            total = 10 - total % 10;
            if (total == ultimo) {
                return true;
            }
        }
        return false;
    }
}
