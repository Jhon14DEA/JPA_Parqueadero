/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.modelo;

import ec.edu.ups.controlador.ControladorJfTicket;
import ec.edu.ups.interfaz.ITicket;
import ec.edu.ups.vista.jfTicket;

/**
 *
 */
public class TicketReserva implements ITicket {

    String texto;

    public TicketReserva(String texto) {
        this.texto = texto;
    }

    @Override
    public void mostrar() {
        jfTicket ticket = new jfTicket();
        ControladorJfTicket controladorJfticket = new ControladorJfTicket("TICKET DE RESERVA", texto, ticket);
        controladorJfticket.iniciar();

    }

}
