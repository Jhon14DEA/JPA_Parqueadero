/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.modelo;

import ec.edu.ups.excepcion.CedulaExcepcion;
import ec.edu.ups.excepcion.EmailExcepcion;
import ec.edu.ups.excepcion.RequeridoExcepcion;
import ec.edu.ups.excepcion.TelefonoExcepcion;
import ec.edu.ups.excepcion.TextoExcepcion;
import ec.edu.ups.interfaz.IValidar;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 */
@Entity
@Table(name = "Personas")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
    @NamedQuery(name = "Persona.findByCedula", query = "SELECT p FROM Persona p WHERE p.codigoIdentificacion = :cedula"),
})
public class Persona implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private int codigo;
    @Column(name = "cedula", nullable = false, length = 10)
    private String codigoIdentificacion;
     @Column(name = "nombre")
    private String nombre;
      @Column(name = "apellido")
    private String apellido;
    @Column(name = "correo")
    private String correo;
    @Column(name = "numeroTelefono")
    private String numeroTelefono;

    public Persona() {

    }

    public Persona(int codigo, String codigoIdentificacion, String nombre, String apellido, String correo, String numeroTelefono) {
        this.codigo = codigo;
        this.codigoIdentificacion = codigoIdentificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.numeroTelefono = numeroTelefono;
    }
    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCodigoIdentificacion() {
        return codigoIdentificacion;
    }

    public void setCodigoIdentificacion(String codigoIdentificacion) throws CedulaExcepcion, RequeridoExcepcion {
        if(codigoIdentificacion.length() != 0)
        {
            ec.edu.ups.validar.ValidarFabrica validarFabrica = new ec.edu.ups.validar.ValidarFabrica();
        IValidar validarCedula = validarFabrica.getTipo("cedula");
        if(validarCedula.validar(codigoIdentificacion))
        this.codigoIdentificacion = codigoIdentificacion;
        else
            throw new CedulaExcepcion(); 
        }
        else throw new RequeridoExcepcion("cedula");
       
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws TextoExcepcion, RequeridoExcepcion {
        if(nombre.length() != 0)
        {
            ec.edu.ups.validar.ValidarFabrica validarFabrica = new ec.edu.ups.validar.ValidarFabrica();
            IValidar validarTexto = validarFabrica.getTipo("texto");
            if(validarTexto.validar(nombre))
                this.nombre = nombre;
        else
            throw new TextoExcepcion();
        }
        else
            throw new RequeridoExcepcion("nombre");
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) throws TextoExcepcion, RequeridoExcepcion {
         if(apellido.length() != 0)
        {
            ec.edu.ups.validar.ValidarFabrica validarFabrica = new ec.edu.ups.validar.ValidarFabrica();
            IValidar validarTexto = validarFabrica.getTipo("texto");
            if(validarTexto.validar(nombre))
                this.apellido = apellido;
        else
            throw new TextoExcepcion();
        }
        else
            throw new RequeridoExcepcion("apellido");
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) throws EmailExcepcion {
        if(correo.length() != 0)
        {
            ec.edu.ups.validar.ValidarFabrica validarFabrica = new ec.edu.ups.validar.ValidarFabrica();
            IValidar validarCorreo = validarFabrica.getTipo("email");
            if(validarCorreo.validar(correo))
                this.correo = correo;
        
        else
            throw new EmailExcepcion();
        }
        else
            this.correo = correo;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) throws TelefonoExcepcion {
        if(numeroTelefono.length() != 0)
        {
            ec.edu.ups.validar.ValidarFabrica validarFabrica = new ec.edu.ups.validar.ValidarFabrica();
            IValidar validarNumero = validarFabrica.getTipo("entero");
            if(validarNumero.validar(numeroTelefono))
                this.numeroTelefono = numeroTelefono;
        
        else
            throw new TelefonoExcepcion();
        }
        else
            this.numeroTelefono = numeroTelefono;
    }

    @Override
    public String toString() {
        return "Persona{" + "codigo=" + codigo + ", codigoIdentificacion=" + codigoIdentificacion + ", nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo + ", numeroTelefono=" + numeroTelefono + '}';
    }

}
