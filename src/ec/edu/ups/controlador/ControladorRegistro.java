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
import javax.persistence.EntityManager;

/**
 *
 */
public class ControladorRegistro extends AbstractControlador<Registro> {

    private List<Registro> listaGenerica;
    public ControladorRegistro(EntityManager em) {
        super(Registro.class,em);
    }

    /*
     public Controlador() {
        listaGenerica = new ArrayList<>();
    }
     */
    public boolean crearRegistro(Registro nuevoRegistro)
    {
        nuevoRegistro.setCodigo(getListado().size()+1);
        return super.crear(nuevoRegistro);
    }
    
    public boolean modificarRegistro(Registro registroActual, Registro registroModificado)
    {
        return super.actualizar( registroModificado);
    }
    
    public boolean eliminarRegistro(Registro registro)
    {
        return super.eliminar(registro);
    }        

    @Override
    public List<Registro> findAll() {
        return getEm().createNamedQuery("Registro.findAll").getResultList();
    }
    public TableModel Listar()
    {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Código");
        modelo.addColumn("Cliente");
        modelo.addColumn("FechaIngreso");
        modelo.addColumn("FechaSalida");
        modelo.addColumn("Tiempo");
        modelo.addColumn("Costo");
        int numcolumnas = 5;
        getListado().stream().map(object -> {
            Object[] filas = new Object[numcolumnas];
            filas[0]=object.getCodigo();
            filas[1]=object.getCliente().getNombre() + " " + object.getCliente().getApellido();
            filas[2]=object.getIngreso().getTime().toString();
            if(object.getSalida()!=null)
            {
                filas[3]=object.getSalida().getTime().toString();
                filas[4]=object.getCostoparqueo();
            }
            else
                filas[3]="";
                filas[4]="";
            
            return filas;
        }).forEachOrdered(filas -> {
            modelo.addRow(filas);
        });
        return modelo;
    }
}
