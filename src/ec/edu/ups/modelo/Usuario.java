/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.modelo;

import ec.edu.ups.excepcion.RequeridoExcepcion;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.apache.commons.codec.digest.DigestUtils;


/**
 *
 */

@Entity
@Table(name ="Usuarios")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByCedula", query = "SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario"),
})
public class Usuario extends Persona {
    @Column(name = "nombreUsuario")
    private String nombreUsuario;
     @Column(name = "contraseña")
    private String contraseña;
      @Column(name = "perfilUsuario")
    private String perfilUsuario;

    public Usuario() {
    }

    public Usuario(String nombreUsuario, String contraseña, int codigo, String codigoIdentificacion, String nombre, String apellido, String correo, String numeroTelefono) {
        super(codigo, codigoIdentificacion, nombre, apellido, correo, numeroTelefono);
        this.nombreUsuario = nombreUsuario;
        this.contraseña = DigestUtils.md5Hex(contraseña);//encriptando contraseña
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) throws RequeridoExcepcion {
        if(nombreUsuario.length() != 0)
        this.nombreUsuario = nombreUsuario;
        else
            throw new RequeridoExcepcion("nombre de usuario");
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) throws RequeridoExcepcion {
        if(contraseña.length() != 0)
        this.contraseña = DigestUtils.md5Hex(contraseña);//encriptando la contraseña
        else
            throw new RequeridoExcepcion("contraseña");
    }

    public String getPerfilUsuario() {
        return perfilUsuario;
    }

    public void setPerfilUsuario(String perfilUsuario) {
        this.perfilUsuario = perfilUsuario;
    }
    
    

    @Override
    public String toString() {
        return "Usuario{" + "nombreUsuario=" + nombreUsuario + ", contrase\u00f1a=" + contraseña + '}';
    }
    
     @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.nombreUsuario);
        hash = 97 * hash + Objects.hashCode(this.contraseña);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.nombreUsuario, other.nombreUsuario)) {
            return false;
        }
        if (!Objects.equals(this.contraseña, other.contraseña)) {
            return false;
        }
        return true;
    }
    
}
