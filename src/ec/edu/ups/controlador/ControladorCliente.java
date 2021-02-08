/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import ec.edu.ups.modelo.Cliente;
import javax.persistence.EntityManager;

/**
 *
 */
public class ControladorCliente extends AbstractControlador<Cliente> {

    private List<Cliente> listaGenerica;
    public ControladorCliente(EntityManager em) {
        super(Cliente.class,em);
    }

    /*
     public Controlador() {
        listaGenerica = new ArrayList<>();
    }
     */
    public boolean crearCliente(Cliente nuevoCliente)
    {
        nuevoCliente.setCodigo(getListado().size()+1);
        return super.crear(nuevoCliente);
    }
    
    public boolean modificarCliente(Cliente cliente_actual, Cliente cliente_modificado)
    {
        return super.actualizar( cliente_modificado);
    }
    
    public boolean eliminarCliente(Cliente cliente)
    {
        return super.eliminar(cliente);
    }    

    @Override
    public List<Cliente> findAll() {
        return getEm().createNamedQuery("cliente.findAll").getResultList(); 
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
        modelo.addColumn("Tipo");
        int numcolumnas = 7;
        getListado().stream().map(object -> {
            Object[] filas = new Object[numcolumnas];
            filas[0]=object.getCodigo();
            filas[1]=object.getCodigoIdentificacion();
            filas[2]=object.getNombre();
            filas[3]=object.getApellido();
            filas[4]=object.getNumeroTelefono();
            filas[5]=object.getCorreo();
            filas[6]=object.getTipoCliente();
            return filas;
        }).forEachOrdered(filas -> {
            modelo.addRow(filas);
        });
        return modelo;
    }
    
    public Cliente BuscarCedula(Cliente clienteBuscado)
    {
        for (Cliente object : findAll()) {
            if(object.getCodigoIdentificacion().equals(clienteBuscado.getCodigoIdentificacion()))
                return object;
        }
        return null;
    }
    
}
