/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Usuario;

import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 */
public class ControladorUsuario extends AbstractControlador<Usuario> {

    private List<Usuario> listaGenerica;
    public ControladorUsuario(EntityManager em) {
        super(Usuario.class,em);
    }

    /*
     public Controlador() {
        listaGenerica = new ArrayList<>();
    }
     */
    public boolean crearUsuario(Usuario nuevoUsuario)
    {
        nuevoUsuario.setCodigo(getListado().size()+1);
        return super.crear(nuevoUsuario);
    }
    
    public boolean modificarUsuario(Usuario usuario_actual, Usuario usuario_modificado)
    {
        return actualizar(usuario_modificado);
    }
    
    public boolean eliminarUsuario(Usuario usuario)
    {
        return eliminar(usuario);
    }

    @Override
    public List<Usuario> findAll() {
        return getEm().createNamedQuery("Usuario.findAll").getResultList();
    }

    public Usuario Login(Usuario usuario) {
        for (Usuario object : getListado()) {
            if(object.getNombreUsuario().equals(usuario.getNombreUsuario()) && object.getContraseña().equals(usuario.getContraseña()))
                return object;
        }
        return null;
    }
    
    public TableModel Listar()
    {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Código");
        modelo.addColumn("Cedula");
        modelo.addColumn("Nombre");
        modelo.addColumn("apellido");
        modelo.addColumn("Telefono");
        modelo.addColumn("Email");
        modelo.addColumn("Alias");
        modelo.addColumn("Contraseña");
        modelo.addColumn("Perfil");
        int numcolumnas = 9;
        getListado().stream().map(object -> {
            Object[] filas = new Object[numcolumnas];
            filas[0]=object.getCodigo();
            filas[1]=object.getCodigoIdentificacion();
            filas[2]=object.getNombre();
            filas[3]=object.getApellido();
            filas[4]=object.getNumeroTelefono();
            filas[5]=object.getCorreo();
            filas[6]=object.getNombreUsuario();
            filas[7]=object.getContraseña();
            filas[8]=object.getPerfilUsuario();
            return filas;
        }).forEachOrdered(filas -> {
            modelo.addRow(filas);
        });
        return modelo;
    }
    
    
}
