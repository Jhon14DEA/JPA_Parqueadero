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
@Table(name = "registros")
@NamedQueries({
    @NamedQuery(name = "Registro.findAll", query = "SELECT s FROM Registro s"),
    @NamedQuery(name = "Registro.findBycodigo", query = "SELECT s FROM Registro s WHERE s.codigo = :codigo"),})
public class Registro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private int codigo;
    @Column(name = "ingreso")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar ingreso;
    @Column(name = "salida")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar salida;
    @Column(name = "costoparqueo")

    private double costoparqueo;
    @Column(name = "costoservicios")
    private float costoservicios;
    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @OneToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @OneToOne
    @JoinColumn(name = "sitio_id")
    private Sitio sitio;
    @Column(name = "TiempoEstancia")
    private double TiempoEstancia;

    public Registro() {

    }

    public Registro(int codigo,
            Calendar ingreso,
            Cliente cliente,
            Vehiculo vehiculo,
            Usuario usuario,
            Sitio sitio
    ) {
        this.codigo = codigo;
        this.ingreso = ingreso;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.usuario = usuario;
        this.sitio = sitio;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Calendar getIngreso() {
        return ingreso;
    }

    public void setIngreso(Calendar ingreso) {
        this.ingreso = ingreso;
    }

    public Calendar getSalida() {
        return salida;
    }

    public void setSalida(Calendar salida) {
        this.salida = salida;
        CalcularTiempo();
    }

    public double getTiempoEstancia() {
        return TiempoEstancia;
    }

    public double getCostoparqueo() {
        return costoparqueo;
    }

    public void setCostoparqueo(String costoparqueo) throws FloatExcepcion, RequeridoExcepcion {
        if (costoparqueo.length() != 0) {
            ec.edu.ups.validar.ValidarFabrica validarFabrica = new ec.edu.ups.validar.ValidarFabrica();
            IValidar validarTexto = validarFabrica.getTipo("float");
            if (validarTexto.validar(costoparqueo)) {
                this.costoparqueo = Double.valueOf(costoparqueo);
            } else {
                throw new FloatExcepcion();
            }
        } else {
            throw new RequeridoExcepcion("Total");
        }
    }

    public float getCostoservicios() {
        return costoservicios;
    }

    public void setCostoservicios(float costoservicios) {
        this.costoservicios = costoservicios;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Sitio getSitio() {
        return sitio;
    }

    public void setSitio(Sitio sitio) {
        this.sitio = sitio;
    }

    @Override
    public String toString() {
        return "Id:" + codigo + ", \nCosto por hora=" + sitio.getCostoNormal() + "\nIngreso:" + ingreso.getTime().toString() + "\nSalida:" + salida.getTime().toString() + "\nTiempo:" + TiempoEstancia + "\nA pagar:" + costoparqueo;

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
        final Registro other = (Registro) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    private void CalcularTiempo() {
        TiempoEstancia = (salida.getTimeInMillis() - ingreso.getTimeInMillis());
        TiempoEstancia = TiempoEstancia / 3600000;
        costoparqueo = Math.ceil(TiempoEstancia) * sitio.getCostoNormal();
    }

}
