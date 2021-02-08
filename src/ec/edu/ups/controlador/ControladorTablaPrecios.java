/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import ec.edu.ups.modelo.TablaPrecios;
import javax.persistence.EntityManager;

/**
 *
 */
public class ControladorTablaPrecios extends AbstractControlador<TablaPrecios> {

    private List<TablaPrecios> listaGenerica;
    public ControladorTablaPrecios(EntityManager em) {
        super(TablaPrecios.class,em);
    }

    /*
     public Controlador() {
        listaGenerica = new ArrayList<>();
    }
     */
    public boolean crearTablaPrecio(TablaPrecios nuevoTabla)
    {
        nuevoTabla.setCodigo(getListado().size()+1);
        return super.crear(nuevoTabla);
    }
    
    public boolean modificarTablaPrecio(TablaPrecios tablaActual, TablaPrecios tablaModificado)
    {
        return super.actualizar( tablaModificado);
    }
    
    public boolean eliminarTabla(TablaPrecios tablaPrecios)
    {
        return super.eliminar(tablaPrecios);
    }        

    @Override
    public List<TablaPrecios> findAll() {
        return getEm().createNamedQuery("TablaPrecios.findAll").getResultList(); 
    }
    public TableModel Listar()
    {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Código");
        modelo.addColumn("Horas");
        modelo.addColumn("Precio");
        modelo.addColumn("Observacion");
        int numcolumnas = 5;
        getListado().stream().map(object -> {
            Object[] filas = new Object[numcolumnas];
            filas[0]=object.getCodigo();
            filas[1]=object.getHoras();
            filas[2]=object.getPrecio();
            filas[3]=object.getObservacion();
            return filas;
        }).forEachOrdered(filas -> {
            modelo.addRow(filas);
        });
        return modelo;
    }
}
