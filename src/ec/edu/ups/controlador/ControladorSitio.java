/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import ec.edu.ups.modelo.Sitio;
import javax.persistence.EntityManager;

/**
 *
 */
public class ControladorSitio extends AbstractControlador<Sitio> {

    private List<Sitio> listaGenerica;
    public ControladorSitio(EntityManager em) {
        super(Sitio.class,em);
    }

    /*
     public Controlador() {
        listaGenerica = new ArrayList<>();
    }
     */
    public boolean crearSitio(Sitio nuevoSitio)
    {
        nuevoSitio.setCodigo(getListado().size()+1);
        nuevoSitio.setEstado("libre");
        return super.crear(nuevoSitio);
    }
    
    public boolean modificarSitio(Sitio sitioActual, Sitio sitioModificado)
            
    {
        Sitio objeto = buscar(sitioActual.getCodigo());
        objeto.setEstado("ocupado");
        return super.actualizar(sitioModificado);
        /*
         for (int i = 0; i < listaGenerica.size(); i++) {
             if (listaGenerica.get(i)== objeto){
                 listaGenerica.set(i, sitioModificado);
                return super.actualizar(sitioModificado);
             }
         }
        return false;
*/
    }

    
    public boolean eliminarSitio(Sitio sitio)
    {
        return super.eliminar(sitio);
    }        

    @Override
    public List<Sitio> findAll() {
        return getEm().createNamedQuery("Sitio.findAll").getResultList(); 
    }
    public TableModel Listar()
    {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Código");
        modelo.addColumn("Descripción");
        modelo.addColumn("Costo Normal");
        modelo.addColumn("Costo Reserva");
        modelo.addColumn("Estado");
        int numcolumnas = 5;
        getListado().stream().map(object -> {
            Object[] filas = new Object[numcolumnas];
            filas[0]=object.getCodigo();
            filas[1]=object.getDescripcion();
            filas[2]=object.getCostoNormal();
            filas[3]=object.getCostoReserva();
            filas[4]=object.getEstado();
            return filas;
        }).forEachOrdered(filas -> {
            modelo.addRow(filas);
        });
        return modelo;
    }
}
