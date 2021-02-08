/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import ec.edu.ups.modelo.Factura;
import ec.edu.ups.modelo.Registro;
import javax.persistence.EntityManager;

/**
 *
 */
public class ControladorFactura extends AbstractControlador<Factura> {

    private List<Factura> listaGenerica;
    public ControladorFactura(EntityManager em) {
        super(Factura.class,em);
    }

    /*
     public Controlador() {
        listaGenerica = new ArrayList<>();
    }
     */
    public boolean crearFactura(Factura nuevoFactura)
    {
        nuevoFactura.setCodigo(getListado().size()+1);
        return super.crear(nuevoFactura);
    }
    
    public boolean modificarFactura(Factura facturaActual, Factura facturaModificado)
    {
        return super.actualizar( facturaModificado);
    }
    
    public boolean eliminarFactura(Factura factura)
    {
        return super.eliminar(factura);
    } 
    

    @Override
    public List<Factura> findAll() {
        return getEm().createNamedQuery("Factura.findAll").getResultList();
    }
    public TableModel ListarFacturaRegistro()
    {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Código");
        modelo.addColumn("Fecha");
        modelo.addColumn("Cedula");
        modelo.addColumn("Cliente");
        modelo.addColumn("Telefono");
        modelo.addColumn("correo");
        modelo.addColumn("Detalle");
        modelo.addColumn("Tiempo");
        modelo.addColumn("Subtotal");
        modelo.addColumn("Iva");
        modelo.addColumn("Total");
        int numcolumnas = 11;
        getListado().stream().map(object -> {
            Object[] filas = new Object[numcolumnas];
            if (object.getRegistro()!=null)
            {
                 filas[0]=object.getCodigo();
            filas[1]=object.getFecha().getTime().toString();
            filas[2]=object.getRegistro().getCliente().getCodigoIdentificacion();
            filas[3]=object.getRegistro().getCliente().getNombre()+" "+object.getRegistro().getCliente().getApellido();
            filas[4]=object.getRegistro().getCliente().getNumeroTelefono();
            filas[5]=object.getRegistro().getCliente().getCorreo();
            filas[6]=object.getRegistro().getVehiculo().getPlaca();
            filas[7]=object.getRegistro().getTiempoEstancia();
            filas[8]=object.getRegistro().getCostoparqueo();
            filas[9]=object.getIva();
            filas[10]=object.getTotalapagar();
            }
            else
            {
                filas[0]=object.getCodigo();
            filas[1]=object.getFecha().getTime().toString();
            filas[2]=object.getReserva().getCliente().getCodigoIdentificacion();
            filas[3]=object.getReserva().getCliente().getNombre()+" "+object.getReserva().getCliente().getApellido();
            filas[4]=object.getReserva().getCliente().getNumeroTelefono();
            filas[5]=object.getReserva().getCliente().getCorreo();
            filas[6]=object.getReserva().getFechaInicio().getTime().toString()+" "+object.getReserva().getFechaFin().getTime().toString();
            filas[7]=object.getReserva().getDias();
            filas[8]=object.getReserva().getTotal();
            filas[9]=object.getIva();
            filas[10]=object.getTotalapagar();
            }
            return filas;
        }).forEachOrdered(filas -> {
            modelo.addRow(filas);
        });
        return modelo;
    }
     
}
