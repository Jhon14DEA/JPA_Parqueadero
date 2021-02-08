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
public class TicketIngreso implements ITicket {

    String texto;

    public TicketIngreso(String texto) {
        this.texto = texto;
    }

    @Override
    public void mostrar() {
        jfTicket ticket = new jfTicket();
        ControladorJfTicket controladorJfticket = new ControladorJfTicket("TICKET DE INGRESO", texto, ticket);
        controladorJfticket.iniciar();

    }

}
