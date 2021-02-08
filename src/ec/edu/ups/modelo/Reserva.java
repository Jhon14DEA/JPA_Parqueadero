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
@Table(name = "reservas")
@NamedQueries({
    @NamedQuery(name = "Reserva.findAll", query = "SELECT r FROM Reserva r"),
    @NamedQuery(name = "Reserva.findByCodigo", query = "SELECT r FROM Reserva r WHERE r.codigo= :codigo"),})
public class Reserva implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private int codigo;
    @Column(name = "fecha_inicio")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar fechaFin;
    @OneToOne
    @JoinColumn(name = "sitio_id")
    private Sitio sitio;
    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @Column(name = "total")
    private double total;
    @Column(name = "dias")
    private int dias;
    @Column(name = "estado")
    private boolean estado;
    @Column(name = "pagado")
    private boolean pagado;
    @Column(name = "multa")
    private double multa;

    public Reserva() {

    }

    public Reserva(int codigo, Calendar fechaInicio, Calendar fechaFin, Sitio sitio, Cliente cliente, Usuario usuario) {
        this.codigo = codigo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.sitio = sitio;
        this.cliente = cliente;
        this.usuario = usuario;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Calendar getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Calendar fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Calendar getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Calendar fechaFin) {
        this.fechaFin = fechaFin;
        setTotal();
    }

    public Sitio getSitio() {
        return sitio;
    }

    public void setSitio(Sitio sitio) {
        this.sitio = sitio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Servicio{" + "codigo=" + codigo + ", fecha inicio=" + fechaInicio + "}";
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
        final Reserva other = (Reserva) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    public double getTotal() {
        return total;
    }

    private void setTotal() {
        long diff = fechaFin.getTimeInMillis() - fechaInicio.getTimeInMillis();
        long diffDays = diff / (24 * 60 * 60 * 1000);
        double ndias = Math.ceil(diffDays);
        total = ndias * sitio.getCostoReserva();
        setDias((int) ndias);
    }

    public int getDias() {
        return dias;
    }

    private void setDias(int dias) {
        this.dias = dias;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta() {
        multa = total * 1.10f;
    }

}
