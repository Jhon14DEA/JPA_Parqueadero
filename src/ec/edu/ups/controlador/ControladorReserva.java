/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import ec.edu.ups.modelo.Registro;
import ec.edu.ups.modelo.Reserva;
import javax.persistence.EntityManager;

/**
 *
 */
public class ControladorReserva extends AbstractControlador<Reserva> {

    private List<Reserva> listaGenerica;
    public ControladorReserva(EntityManager em) {
        super(Reserva.class,em);
    }

    /*
     public Controlador() {
        listaGenerica = new ArrayList<>();
    }
     */
    public boolean crearReserva(Reserva nuevoReserva)
    {
        nuevoReserva.setCodigo(getListado().size()+1);
        return super.crear(nuevoReserva);
    }
    
    public boolean modificarReserva(Reserva reservaActual, Reserva ReservaModificado)
    {
        return super.actualizar( ReservaModificado);
    }
    
    public boolean eliminarRegistro(Reserva reserva)
    {
        return super.eliminar(reserva);
    }        

    @Override
    public List<Reserva> findAll() {
        return getEm().createNamedQuery("Reserva.findAll").getResultList(); 
    }
    public TableModel Listar()
    {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Código");
        modelo.addColumn("Cliente");
        modelo.addColumn("FechaInicio");
        modelo.addColumn("FechaFin");
        modelo.addColumn("Dias");
        modelo.addColumn("Estado");
        int numcolumnas = 6;
        getListado().stream().map(object -> {
            Object[] filas = new Object[numcolumnas];
            filas[0]=object.getCodigo();
            filas[1]=object.getCliente().getNombre() + " " + object.getCliente().getApellido();
            filas[2]=object.getFechaInicio().getTime().toString();
            filas[3]=object.getFechaFin().getTime().toString();
            filas[4]=object.getDias();
            return filas;
        }).forEachOrdered(filas -> {
            modelo.addRow(filas);
        });
        return modelo;
    }
}
