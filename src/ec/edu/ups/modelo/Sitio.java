/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.modelo;

import ec.edu.ups.excepcion.FloatExcepcion;
import ec.edu.ups.excepcion.RequeridoExcepcion;
import ec.edu.ups.interfaz.IValidar;
import java.io.Serializable;
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

/**
 *
 */
@Entity
@Table(name = "sitios")
@NamedQueries({
    @NamedQuery(name = "Sitio.findAll", query = "SELECT s FROM Sitio s"),
    @NamedQuery(name = "Sitio.findBycodigo", query = "SELECT s FROM Sitio s WHERE s.codigo = :codigo"),})
public class Sitio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private int codigo;
    @Column(name = "costoNormal")
    private float costoNormal;
    @Column(name = "costoReserva")
    private float costoReserva;
    @Column(name = "estado")
    private String estado;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToOne
    @JoinColumn(name = "registro_id")
    private Registro registro;
    @OneToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

    public Sitio() {

    }

    public Sitio(int codigo, String descripcion, float costoNormal, float costoReserva, String estado) {
        this.codigo = codigo;
        this.costoNormal = costoNormal;
        this.costoReserva = costoReserva;
        this.estado = estado;
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public float getCostoNormal() {
        return costoNormal;
    }

    public void setCostoNormal(String costoNormal) throws FloatExcepcion, RequeridoExcepcion {
        if (costoNormal.length() != 0) {
            ec.edu.ups.validar.ValidarFabrica validarFabrica = new ec.edu.ups.validar.ValidarFabrica();
            IValidar validarTexto = validarFabrica.getTipo("float");
            if (validarTexto.validar(costoNormal)) {
                this.costoNormal = Float.valueOf(costoNormal);
            } else {
                throw new FloatExcepcion();
            }
        } else {
            throw new RequeridoExcepcion("costo normal");
        }

    }

    public float getCostoReserva() {
        return costoReserva;
    }

    public void setCostoReserva(String costoReserva) throws FloatExcepcion, RequeridoExcepcion {
        if (costoReserva.length() != 0) {
            ec.edu.ups.validar.ValidarFabrica validarFabrica = new ec.edu.ups.validar.ValidarFabrica();
            IValidar validar = validarFabrica.getTipo("float");
            if (validar.validar(costoReserva)) {
                this.costoReserva = Float.valueOf(costoReserva);
            } else {
                throw new FloatExcepcion();
            }
        } else {
            throw new RequeridoExcepcion("costo normal");
        }
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "S:" + codigo + " " + costoNormal + "$";
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
        final Sitio other = (Sitio) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
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

}
