/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import java.util.List;
import ec.edu.ups.modelo.Vehiculo;
import javax.persistence.EntityManager;

/**
 *
 */
public class ControladorVehiculo extends AbstractControlador<Vehiculo> {

    private List<Vehiculo> listaGenerica;
    public ControladorVehiculo(EntityManager em) {
        super(Vehiculo.class,em);
    }

    /*
     public Controlador() {
        listaGenerica = new ArrayList<>();
    }
     */
    public boolean crearVehiculo(Vehiculo nuevoVehiculo)
    {
        return super.crear(nuevoVehiculo);
    }
    
    public boolean modificarVehiculo(Vehiculo vehiculoActual, Vehiculo vehiculoModificado)
    {
        return super.actualizar( vehiculoModificado);
    }
    
    public boolean eliminarVehiculo(Vehiculo vehiculo)
    {
        return super.eliminar(vehiculo);
    }        

    @Override
    public List<Vehiculo> findAll() {
         return getEm().createNamedQuery("Vehiculo.findAll").getResultList(); 
    }
}
