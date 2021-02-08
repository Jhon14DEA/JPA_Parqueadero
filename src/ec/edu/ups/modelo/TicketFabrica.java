/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.modelo;

import ec.edu.ups.interfaz.ITicket;

/**
 *
 */
public class TicketFabrica {

    public ITicket getTipo(String Tipo, String contenido) {
        switch (Tipo) {
            case "ingreso":
                return new TicketIngreso(contenido);
            case "salida":
                return new TicketSalida(contenido);
            case "reserva":
                return new TicketReserva(contenido);
            default:
                return null;
        }
    }
}
