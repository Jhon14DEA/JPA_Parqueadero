/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import ec.edu.ups.modelo.Registro;
import ec.edu.ups.modelo.Reserva;
import ec.edu.ups.modelo.Sitio;
import ec.edu.ups.vista.jfRegistro;

/**
 *
 */
public class jbutSitio extends JButton implements ActionListener {
    private int n;
    private String palabra;
    Sitio sitio;
    ControladorRegistro controladorRegistro;
    ControladorCliente controladorCliente;
    ControladorVehiculo controladorVehiculo;
    ControladorReserva controladorReserva;
    ControladorTablaPrecios controladorTablaPrecios;
    ControladorFactura controladorFactura;
     
       
        Registro registro;
    Reserva reserva;

    public jbutSitio(int posx, int posy, int ancho, int alto, int n, String palabra,Sitio sitio,ControladorRegistro controladorRegistro, ControladorCliente controladorCliente, 
            ControladorVehiculo controladorVehiculo, ControladorReserva controladorReserva, ControladorTablaPrecios controladorTablaPrecios,
            ControladorFactura controladorFactura) {
        setBounds(posx, posy, ancho, alto);
        this.palabra = palabra;
        this.n = n;
        addActionListener(this);
        this.sitio=sitio;
        this.controladorCliente=controladorCliente;
        this.controladorRegistro=controladorRegistro;
        this.controladorReserva=controladorReserva;
        this.controladorVehiculo=controladorVehiculo;
        this.controladorTablaPrecios=controladorTablaPrecios;
        this.controladorFactura = controladorFactura;
        if(sitio.getRegistro() == null)
        {
            registro = new Registro();
            sitio.setRegistro(registro);
        }else
            registro = sitio.getRegistro();
        if(sitio.getReserva()==null)
        {
            reserva = new Reserva();
            sitio.setReserva(reserva);
        }
        else
            reserva=sitio.getReserva();
            
    }

    public void cambiarNombre(int x, int y) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(getBackground()==Color.green)
        {
            jfRegistro frmaux=new jfRegistro();
            ControladorJfRegistro controladorJfRegistro = new ControladorJfRegistro(this, sitio, frmaux,controladorRegistro,controladorCliente,"ingresonormal",controladorReserva,controladorVehiculo,controladorTablaPrecios,controladorFactura);
            controladorJfRegistro.iniciar();
        }
        if(getBackground()==Color.red)
        {
            jfRegistro frmaux=new jfRegistro();
            ControladorJfRegistro controladorJfRegistro = new ControladorJfRegistro(this, sitio, frmaux,controladorRegistro,controladorCliente,"salida",controladorReserva,controladorVehiculo,controladorTablaPrecios,controladorFactura);
            controladorJfRegistro.iniciar();
        }
        if(getBackground()==Color.blue)
        {
            jfRegistro frmaux=new jfRegistro();
            ControladorJfRegistro controladorJfRegistro = new ControladorJfRegistro(this, sitio, frmaux,controladorRegistro,controladorCliente,"ingresoreserva",controladorReserva,controladorVehiculo,controladorTablaPrecios,controladorFactura);
            controladorJfRegistro.iniciar();
        }

    }

}
