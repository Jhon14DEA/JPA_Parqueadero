/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import ec.edu.ups.excepcion.CedulaExcepcion;
import ec.edu.ups.excepcion.EmailExcepcion;
import ec.edu.ups.excepcion.FloatExcepcion;
import ec.edu.ups.excepcion.PlacaExcepcion;
import ec.edu.ups.excepcion.RequeridoExcepcion;
import ec.edu.ups.excepcion.TelefonoExcepcion;
import ec.edu.ups.excepcion.TextoExcepcion;
import ec.edu.ups.interfaz.ITicket;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JOptionPane;
import ec.edu.ups.modelo.Cliente;
import ec.edu.ups.modelo.Factura;
import ec.edu.ups.modelo.Registro;
import ec.edu.ups.modelo.Reserva;
import ec.edu.ups.modelo.Sitio;
import ec.edu.ups.modelo.TicketFabrica;
import ec.edu.ups.modelo.Vehiculo;
import ec.edu.ups.vista.jfRegistro;
import ec.edu.ups.vista.jfTicket;
import ec.edu.ups.vista.jfVerTablaPrecios;

/**
 * controlador que maneja el formulario para hacer mantenimiento de la tabla
 * usuario implementa actionlistener para atrapar eventos como acción del mouse
 */
public class ControladorJfRegistro implements ActionListener {
    private Registro mod,modaux;
    private Factura factura;
    private final jfRegistro  frm;
    private Cliente cliente;
    public boolean nuevo;
    private final ControladorRegistro controladorRegistro;
    private final ControladorCliente controladorCliente;
    private final ControladorReserva controladorReserva;
    private final ControladorVehiculo controladorVehiculo;
    private final ControladorTablaPrecios controladorTablaPrecios;
    private final ControladorFactura controladorFactura;
    private final jbutSitio jbutsitio;
    Sitio sitio;
    String comando;
    jfTicket jfFactura;

    //constructor
    public ControladorJfRegistro(jbutSitio jbutsitio,Sitio sitio, jfRegistro frm, ControladorRegistro controladorRegistro,ControladorCliente controladorCliente, 
            String comando,ControladorReserva controladorReserva,ControladorVehiculo controladorVehiculo, ControladorTablaPrecios controladorTablaPrecios,
            ControladorFactura controladorFactura) {
        //asignar valor a las variables 
        this.comando=comando;
        this.sitio = sitio;
        this.frm = frm;
        this.controladorRegistro=controladorRegistro;
        this.controladorCliente=controladorCliente;
        this.controladorReserva = controladorReserva;
        this.controladorVehiculo=controladorVehiculo;
        this.controladorTablaPrecios=controladorTablaPrecios;
        this.controladorFactura=controladorFactura;
        //agregar controlador de eventos a elementos del formulario
        this.frm.jbutBuscar.addActionListener(this);
        this.frm.jbutGuardar.addActionListener(this);
        this.frm.jbutCancelar.addActionListener(this);
        this.frm.jbutReservar.addActionListener(this);
        this.frm.jbutVerTabla.addActionListener(this);
        jfFactura=new jfTicket();
        jfFactura.jbutCerrar.addActionListener(this);
       this.jbutsitio=jbutsitio;
       this.mod = jbutsitio.registro;
    }

    //método local para mostrar el formulario
    public void iniciar() {
        nuevo = true;
        frm.jtxtDescripcionsitio.setText(sitio.getDescripcion());
        frm.jtxtCostoNormal.setText(String.valueOf(sitio.getCostoNormal()));
        frm.jtxtCostoReserva.setText(String.valueOf(sitio.getCostoReserva()));
        Calendar calendario = Calendar.getInstance();
        
        if(mod.getIngreso() != null)
        {
            frm.jdIngreso.setCalendar(mod.getIngreso());
            frm.jdSalida.setCalendar(calendario);
            frm.jtxtTotal.setText(String.valueOf(mod.getCostoparqueo()));
            llenarDatosCliente();
            llenarDatosVehiculo();
            frm.jbutReservar.setEnabled(false);
        }
        else
            frm.jdIngreso.setCalendar(calendario);
        if(jbutsitio.reserva.getCliente() != null)
        {
            mod.setCliente(jbutsitio.reserva.getCliente());
            llenarDatosCliente();
        }
        frm.setVisible(true);
        frm.setLocationRelativeTo(null);
    }
//controlador de eventos del formulario

    @Override
    public void actionPerformed(ActionEvent e) {
int dialogButton = JOptionPane.YES_NO_OPTION;
       if (e.getSource() == frm.jbutGuardar) {
           
           try {
               
    
               if(sitio.getEstado().equals("ocupado"))
               {
                   AsignarDatosCliente(mod.getCliente());
                   AsignarDatosVehiculo(mod.getVehiculo());
                   modaux=mod;
                   
                         if(jbutsitio.reserva.getCliente() != null)
                   {
                       sitio.setEstado("reservado");
                       jbutsitio.setBackground(Color.blue);
                   }
                   else
                   {
                       sitio.setEstado("libre");
                       jbutsitio.setBackground(Color.green);
                   }
                            jbutsitio.registro=new Registro();
             
               frm.dispose();
               Calendar calendario = Calendar.getInstance();
               modaux.setSalida(calendario);
               modaux.setCostoparqueo(frm.jtxtTotal.getText());
               controladorRegistro.modificarRegistro(mod,modaux);
               JOptionPane.showConfirmDialog (null, "GENERAR LA FACTURA?","Warning",dialogButton);
               if(dialogButton == JOptionPane.YES_OPTION)
               {
                   GenerarFactura();
                   jfFactura.setVisible(true);
               }
                   
               
                   MostrarTicket("salida", "Id:" + modaux.getCodigo() + ", \nCosto por hora=" + modaux.getSitio().getCostoNormal() + 
                "\nIngreso:"+modaux.getIngreso().getTime().toString()+"\nSalida:"+modaux.getSalida().getTime().toString()+"\nTiempo:"+
                           modaux.getTiempoEstancia()+"\nA pagar:"+modaux.getCostoparqueo());    
               }
               else
               {
                   if(cliente == null)
               {
                   Cliente clienteNuevo = new Cliente();
                   clienteNuevo = AsignarDatosCliente(clienteNuevo);
                   controladorCliente.crearCliente(clienteNuevo);
                   mod.setCliente(clienteNuevo);
               }
               else
               {
                   mod.setCliente(cliente);
               }
               Vehiculo vehiculo = new Vehiculo();
               AsignarDatosVehiculo(vehiculo);
              mod.setVehiculo(vehiculo);
              controladorVehiculo.crear(vehiculo);
              sitio.setEstado("ocupado");
               mod.setSitio(sitio);
               jbutsitio.setBackground(Color.red);
               frm.dispose();
               mod.setCostoparqueo(String.valueOf(sitio.getCostoNormal()));
               Calendar calendario = Calendar.getInstance();
               mod.setIngreso(calendario);
               controladorRegistro.crearRegistro(mod);
               MostrarTicket("ingreso", "Id:" + mod.getCodigo() + ", \nCosto por hora=" + mod.getSitio().getCostoNormal() + "\nIngreso:"+mod.getIngreso().getTime().toString());
               }
                
           } catch (CedulaExcepcion | RequeridoExcepcion | TextoExcepcion | TelefonoExcepcion | EmailExcepcion | FloatExcepcion | PlacaExcepcion ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
           }
       }
       if (e.getSource() == frm.jbutBuscar) {
           try {
               cliente = new Cliente();
               cliente.setCodigoIdentificacion(frm.jtxtCedula.getText());
               cliente=controladorCliente.BuscarCedula(cliente);
               if(cliente != null)
               {
                   frm.jtxtNombre.setText(cliente.getNombre());
                   frm.jtxtApellido.setText(cliente.getApellido());
                   frm.jtxtNumeroTelefono.setText(cliente.getNumeroTelefono());
                   frm.jtxtCorreo.setText(cliente.getCorreo());
               }
               else
               {
                   JOptionPane.showMessageDialog(null, "No se encontró concidencias, ingrese los datos");
               }
           } catch (CedulaExcepcion | RequeridoExcepcion ex) {
               JOptionPane.showMessageDialog(null, ex.getMessage());
           }
           
       }
       if (e.getSource() == frm.jbutCancelar) {
           frm.dispose();
       }
       if(e.getSource() == frm.jbutReservar)
       {
           if(sitio.getEstado().equals("libre")){
                try {
                if(cliente == null)
               {
                   
                        Cliente clienteNuevo = new Cliente();
                        clienteNuevo = AsignarDatosCliente(clienteNuevo);
                        controladorCliente.crearCliente(clienteNuevo);
                        jbutsitio.reserva.setCliente(clienteNuevo);
                    
               }
               
               else
               {
                   jbutsitio.reserva.setCliente(cliente);
               }
              sitio.setEstado("reservado");
               jbutsitio.reserva.setSitio(sitio);
              
               jbutsitio.reserva.setFechaInicio(frm.jdIngreso.getCalendar());
               jbutsitio.reserva.setFechaFin(frm.jdSalida.getCalendar());
               controladorReserva.crearReserva(jbutsitio.reserva);
               JOptionPane.showConfirmDialog (null, "GENERAR LA FACTURA?","Warning",dialogButton);
               if(dialogButton == JOptionPane.YES_OPTION)
               {
                   GenerarFactura();
                   jfFactura.setVisible(true);
               }
               MostrarTicket("reserva","Id:" + jbutsitio.reserva.getCodigo() +",\nCosto por dia=" + jbutsitio.reserva.getSitio().getCostoReserva()+", \nNombre=" + 
                       jbutsitio.reserva.getCliente().getNombre()+",\nApellido=" + jbutsitio.reserva.getCliente().getApellido()+ "\nInicio:"+jbutsitio.reserva.getFechaInicio().getTime().toString()+
                       "\nFin:"+jbutsitio.reserva.getFechaFin().getTime().toString()+"\nDias: "+jbutsitio.reserva.getDias()+"\nCosto:"+jbutsitio.reserva.getTotal());
               jbutsitio.setBackground(Color.blue);
               frm.dispose();
               } catch (CedulaExcepcion | RequeridoExcepcion | TextoExcepcion | TelefonoExcepcion | EmailExcepcion ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
           }
       }
    }
       if(e.getSource() == frm.jbutVerTabla){
           jfVerTablaPrecios frmaux = new jfVerTablaPrecios();
           ControladorJfVerTablaPrecios controladorJfVerTablaPrecios = new ControladorJfVerTablaPrecios(frmaux, controladorTablaPrecios);
           controladorJfVerTablaPrecios.iniciar();
       }
       
       if(e.getSource()==jfFactura.jbutCerrar)
           jfFactura.setVisible(false);
    }
           private Cliente AsignarDatosCliente(Cliente cliente) throws CedulaExcepcion, RequeridoExcepcion, TextoExcepcion, TelefonoExcepcion, EmailExcepcion
       {
           cliente.setCodigoIdentificacion(frm.jtxtCedula.getText());
           cliente.setNombre(frm.jtxtNombre.getText());
           cliente.setApellido(frm.jtxtApellido.getText());
           cliente.setNumeroTelefono(frm.jtxtNumeroTelefono.getText());
           cliente.setCorreo(frm.jtxtCorreo.getText());
           return cliente;
       }
             private Vehiculo AsignarDatosVehiculo(Vehiculo vehiculo) throws PlacaExcepcion, RequeridoExcepcion
       {           
           vehiculo.setPlaca(frm.jtxtPlaca.getText());
           vehiculo.setDetalle(frm.jtxtDescripcion.getText());
           return vehiculo;
       }
             private void llenarDatosCliente(){
                 if(mod.getCliente() != null)
                 {
                     frm.jtxtCedula.setText(mod.getCliente().getCodigoIdentificacion());
                 frm.jtxtNombre.setText(mod.getCliente().getNombre());
                 frm.jtxtApellido.setText(mod.getCliente().getApellido());
                 frm.jtxtNumeroTelefono.setText(mod.getCliente().getNumeroTelefono());
                 frm.jtxtCorreo.setText(mod.getCliente().getCorreo());
                 }
                 
             }
             private void llenarDatosVehiculo(){
                 if(mod.getVehiculo() != null)
                 {
                     frm.jtxtPlaca.setText(mod.getVehiculo().getPlaca());
                     frm.jtxtDescripcion.setText(mod.getVehiculo().getDetalle());
                 }
             }
             private void MostrarTicket(String opcion, String contenido){
                 TicketFabrica ticketFabrica = new TicketFabrica();
                 ITicket TicketMostrar = ticketFabrica.getTipo(opcion,contenido);
                 TicketMostrar.mostrar();
             }
            private void GenerarFactura()
             {
                  if(modaux!=null)
                  {
                      if(modaux.getSalida()!=null)
                {
                    factura = new Factura();
                    factura.setRegistro(modaux);
                    factura.setFecha(modaux.getSalida());
                    factura.setIva();
                    controladorFactura.crearFactura(factura);
                    jfFactura.jtxtTicket.setText("NUMERO FACTURA: "+factura.getCodigo()+
                            "\ncedula:"+modaux.getCliente().getCodigoIdentificacion()+
                            "\nCliente:"+modaux.getCliente().getNombre() + " "+modaux.getCliente().getApellido()+
                            "\nTeléfono:"+modaux.getCliente().getNumeroTelefono()+
                            "\nEmail:"+modaux.getCliente().getCorreo()+
                            "\nPlaca"+modaux.getVehiculo().getPlaca()+
                            "\nFechaIngreso"+modaux.getIngreso().getTime().toString()+
                            "\nFechaSalida"+modaux.getSalida().getTime().toString()+
                            "\nSubtotal:"+modaux.getCostoparqueo()+
                            "\nIva:"+factura.getIva()+
                            "\nTotal:"+factura.getTotalapagar()
                            );
                    
                }
                  }
                
                else 
            if(sitio.getReserva().getCliente()!=null)
            {
                Calendar calendario = Calendar.getInstance();
                    factura = new Factura();
                    sitio.getReserva().setPagado(true);
                    factura.setReserva(sitio.getReserva());
                    factura.setFecha(calendario);
                    factura.setIva();
                    controladorFactura.crearFactura(factura);
                    jfFactura.jtxtTicket.setText("NUMERO FACTURA: "+factura.getCodigo()+
                            "\ncedula:"+sitio.getReserva().getCliente().getCodigoIdentificacion()+
                            "\nCliente:"+sitio.getReserva().getCliente().getNombre() + " "+sitio.getReserva().getCliente().getApellido()+
                            "\nTeléfono:"+sitio.getReserva().getCliente().getNumeroTelefono()+
                            "\nEmail:"+sitio.getReserva().getCliente().getCorreo()+
                            "\nFechaIngreso"+sitio.getReserva().getFechaInicio().getTime().toString()+
                            "\nFechaFin"+sitio.getReserva().getFechaFin().getTime().toString()+
                            "\nDias:"+sitio.getReserva().getDias()+
                            "\nSubtotal:"+sitio.getReserva().getTotal()+
                            "\nIva:"+factura.getIva()+
                            "\nTotal:"+factura.getTotalapagar()
                            );
                    
            }
             }
    
                     
}
