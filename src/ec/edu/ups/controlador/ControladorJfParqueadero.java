/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import ec.edu.ups.modelo.Sitio;
import ec.edu.ups.vista.jfParqueadero;

/**
 *
 */
public class ControladorJfParqueadero implements ActionListener{
    jbutSitio[] boton;
    ControladorSitio controladorSitio;
    ControladorRegistro controladorRegistro;
    ControladorCliente controladorCliente;
    ControladorReserva controladorReserva;
    ControladorVehiculo controladorVehiculo;
    ControladorTablaPrecios controladorTablaPrecios;
    ControladorFactura controladorFactura;
    jfParqueadero frm;
    private final String palabra;
    
    
    //método local para mostrar el formulario
    public void iniciar() {
        //asignar valor a propiedades del formulario
        botones("ver");
        frm.setVisible(true);
        frm.setLocationRelativeTo(null);
    }

    public ControladorJfParqueadero(String palabra, ControladorSitio controladorSitio, jfParqueadero frm, ControladorRegistro controladorRegistro,ControladorCliente controladorCliente, 
            ControladorReserva controladorReserva,ControladorVehiculo controladorVehiculo,ControladorTablaPrecios controladorTablaPrecios,
            ControladorFactura controladorFactura) {
        this.palabra= palabra;
        this.controladorSitio = controladorSitio;
        this.frm = frm;
        this.controladorCliente=controladorCliente;
        this.controladorRegistro=controladorRegistro;
        this.controladorReserva=controladorReserva;
        this.controladorVehiculo=controladorVehiculo;
        this.boton = new jbutSitio[controladorSitio.getListado().size()];
        this.controladorTablaPrecios=controladorTablaPrecios;
        this.controladorFactura=controladorFactura;
//        if (palabra.equals("reservarSitio")){
//            botonesReserva("reservarSitio");
//        }else if(palabra.equals("verParqueadero")){
//            botones("verParqueadero");
//        }else if(palabra.equals("cambiarPrecioHora")){
//            botonesPrecio("cambiarPrecioHora");
//        }
        
    }
    int c = 0;

    public void botones(String palabra) {
        int posicion=0;
        int columnas = 10; 
        int fila=0,columna=0;
        Calendar calendario = Calendar.getInstance();
        for (Sitio sitio : controladorSitio.getListado()) {
            c++;
                boton[posicion] = new jbutSitio(100 * columna, 100 * fila, 100, 55, c,palabra,sitio,controladorRegistro,controladorCliente,controladorVehiculo,controladorReserva,controladorTablaPrecios,controladorFactura);
                if(sitio.getReserva()!=null)
                    if(sitio.getReserva().getFechaFin()!= null)
                {
                    if(calendario.getTimeInMillis() > sitio.getReserva().getFechaFin().getTimeInMillis())
                    {
                        sitio.setEstado("libre");
                        sitio.setReserva(null);
                    }
                }
                switch (sitio.getEstado()) {
                    case "libre":
                        boton[posicion].setBackground(Color.green);
                        break;
                    case "ocupado":
                        boton[posicion].setBackground(Color.red);
                        break;
                    case "reservado":
                        boton[posicion].setBackground(Color.blue);
                        break;
                    default:
                        break;
        }
                if(columna == columnas)
                {
                    columna = 0;
                    fila++;
                }
                else
                                   columna++; 
                
               
                boton[posicion].setText(sitio.toString());
                frm.jpSitios.add(boton[posicion]);     
                posicion++;
                

        }          
            
        }
  

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
