/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.modelo;

import ec.edu.ups.excepcion.CedulaExcepcion;
import ec.edu.ups.excepcion.PlacaExcepcion;
import ec.edu.ups.excepcion.RequeridoExcepcion;
import ec.edu.ups.interfaz.IValidar;
import java.io.Serializable;
import java.util.Objects;
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
@Table(name = "vehiculos")
@NamedQueries({
    @NamedQuery(name = "Vehiculo.findAll", query = "SELECT a FROM Vehiculo a"),
    @NamedQuery(name = "Vehiculo.findByCedula", query = "SELECT a FROM Vehiculo a WHERE a.codigo = :codigo"),})
public class Vehiculo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codigo")
    private int codigo;
    @Column(name = "placa", nullable = false, length = 255)
    private String placa;
    @Column(name = "detalle", nullable = false, length = 255)
    private String detalle;

    public Vehiculo() {

    }

    public Vehiculo(int codigo, String placa, String detalle) {
        this.codigo = codigo;
        this.placa = placa;
        this.detalle = detalle;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) throws PlacaExcepcion, RequeridoExcepcion {
        if (placa.length() != 0) {
            ec.edu.ups.validar.ValidarFabrica validarFabrica = new ec.edu.ups.validar.ValidarFabrica();
            IValidar validarCedula = validarFabrica.getTipo("placa");
            if (validarCedula.validar(placa)) {
                this.placa = placa;
            } else {
                throw new PlacaExcepcion();
            }
        } else {
            throw new RequeridoExcepcion("placa");
        }
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    @Override
    public String toString() {
        return "Servicio{" + "codigo=" + codigo + ", placa=" + placa + "}";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.codigo);
        hash = 97 * hash + Objects.hashCode(this.placa);
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
        final Vehiculo other = (Vehiculo) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.placa, other.placa)) {
            return false;
        }
        return true;
    }
}
