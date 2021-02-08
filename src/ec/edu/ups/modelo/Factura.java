/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.modelo;

import java.io.Serializable;
import java.util.Calendar;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 */
@Entity
@Table(name = "facturas")
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f"),
    @NamedQuery(name = "Factura.findByCodigo", query = "SELECT f FROM Factura f WHERE f.codigo= :codigo"),})
public class Factura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private int codigo;
    @Column(name = "fecha")
    @Temporal(javax.persistence.TemporalType.TIME)
    private Calendar fecha;
    @OneToOne
    @JoinColumn(name="registro_id", nullable = false)
    private Registro registro;
    @OneToOne
    @JoinColumn(name="reserva_id", nullable = false)
    private Reserva reserva;
    private double iva;
    @Column(name ="total_Pagar")
    private double totalapagar;

    public Factura() {

    }

    public Factura(int codigo, Calendar fecha
    ) {
        this.codigo = codigo;
        this.fecha = fecha;

    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Id:" + codigo;

    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Factura other = (Factura) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public Registro getRegistro() {
        return registro;
    }

    public void setRegistro(Registro registro) {
        this.registro = registro;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public double getIva() {
        return iva;
    }

    public void setIva() {

        if (reserva != null) {
            iva = reserva.getTotal() * 0.12f;
            totalapagar = reserva.getTotal() + iva;
            if (fecha.getTimeInMillis() > reserva.getFechaFin().getTimeInMillis()) {
                reserva.setMulta();
            }

        } else if (registro != null) {

            iva = registro.getCostoparqueo() * 0.12f;
            totalapagar = registro.getCostoparqueo() + iva;
        }

    }

    public double getTotalapagar() {
        return totalapagar;
    }

    public void setTotalapagar(float totalapagar) {
        this.totalapagar = totalapagar;
    }

}
