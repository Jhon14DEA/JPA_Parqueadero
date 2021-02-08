/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.modelo;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 */

@Entity
@Table(name = "clientes")
@NamedQueries({
    @NamedQuery(name = "cliente.findAll", query = "SELECT c FROM Cliente c")
})
public class Cliente extends Persona {
    @Column(name = "tipo_CLiente")
    private String TipoCliente;

    public Cliente() {
    }

    public Cliente(String TipoCliente, 
            int codigo, 
            String codigoIdentificacion, 
            String nombre, 
            String apellido, 
            String correo, 
            String numeroTelefono) {
        super(codigo, codigoIdentificacion, nombre, apellido, correo, numeroTelefono);
        this.TipoCliente = TipoCliente;
    }

    public String getTipoCliente() {
        return TipoCliente;
    }

    public void setTipoCliente(String TipoCliente) {
        this.TipoCliente = TipoCliente;
    } 
    
    @Override
    public String toString() {
        return "Cliente{" + "nombre=" + super.getNombre() + ", apellido\u00f1a=" + super.getApellido() + '}';
    }
    
     @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.getCodigo());
        hash = 97 * hash + Objects.hashCode(this.getCodigoIdentificacion());
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
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.getCodigo(), other.getCodigo())) {
            return false;
        }
        if (!Objects.equals(this.getCodigoIdentificacion(), other.getCodigoIdentificacion())) {
            return false;
        }
        return true;
    }
    
}
