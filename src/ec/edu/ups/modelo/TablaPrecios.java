/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.modelo;

import ec.edu.ups.excepcion.FloatExcepcion;
import ec.edu.ups.excepcion.RequeridoExcepcion;
import ec.edu.ups.interfaz.ITicket;
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
@Table(name = "TablaPrecioss")
@NamedQueries({
    @NamedQuery(name = "TablaPrecios.findAll", query = "SELECT r FROM TablaPrecios r"),
    @NamedQuery(name = "TablaPrecios.findByCodigo", query = "SELECT r FROM TablaPrecios r WHERE r.codigo= :codigo"),})
public class TablaPrecios implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private int codigo;
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "horas")
    private int horas;
    @Column(name = "precio")
    private float precio;

    public TablaPrecios() {

    }

    public TablaPrecios(int codigo, String observacion, float precio, int horas) {
        this.codigo = codigo;
        this.observacion = observacion;
        this.precio = precio;
        this.horas = horas;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(String horas) throws FloatExcepcion, RequeridoExcepcion {
        if (horas.length() != 0) {
            ec.edu.ups.validar.ValidarFabrica validarFabrica = new ec.edu.ups.validar.ValidarFabrica();
            IValidar validarTexto = validarFabrica.getTipo("entero");
            if (validarTexto.validar(horas)) {
                this.horas = Integer.valueOf(horas);
            } else {
                throw new FloatExcepcion();
            }
        } else {
            throw new RequeridoExcepcion("horas");
        }
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) throws FloatExcepcion, RequeridoExcepcion {
        if (precio.length() != 0) {
            ec.edu.ups.validar.ValidarFabrica validarFabrica = new ec.edu.ups.validar.ValidarFabrica();
            IValidar validarTexto = validarFabrica.getTipo("float");
            if (validarTexto.validar(precio)) {
                this.precio = Float.valueOf(precio);
            } else {
                throw new FloatExcepcion();
            }
        } else {
            throw new RequeridoExcepcion("precio");
        }
    }

    @Override
    public String toString() {
        return "Servicio{" + "codigo=" + codigo + ", Denominacion=" + observacion + ", Costo=" + precio + "}";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.codigo);
        hash = 97 * hash + Objects.hashCode(this.horas);
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
        final TablaPrecios other = (TablaPrecios) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.horas, other.horas)) {
            return false;
        }
        return true;
    }

}
