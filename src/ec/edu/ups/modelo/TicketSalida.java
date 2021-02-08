/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.modelo;

import ec.edu.ups.controlador.ControladorJfTicket;
import ec.edu.ups.interfaz.ITicket;
import ec.edu.ups.vista.jfTicket;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 */
@Entity
@Table(name = "registros")
@NamedQueries({
    @NamedQuery(name = "TicketSalida.findAll", query = "SELECT s FROM TicketSalida s"),
    @NamedQuery(name = "TicketSalida.findBycodigo", query = "SELECT s FROM TicketSalida s WHERE s.codigo = :codigo"),})
public class TicketSalida implements Serializable, ITicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    int codigo;
    @Column(name = "texto")
    String texto;

    public TicketSalida() {
    }

    public TicketSalida(String texto) {
        this.texto = texto;
    }

    @Override
    public void mostrar() {
        jfTicket ticket = new jfTicket();
        ControladorJfTicket controladorJfticket = new ControladorJfTicket("TICKET DE SALIDA", texto, ticket);
        controladorJfticket.iniciar();

    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

}
