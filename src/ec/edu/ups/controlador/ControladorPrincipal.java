/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import ec.edu.ups.datos.ManejadorArchivos;
import ec.edu.ups.interfaz.IValidar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JOptionPane;
import ec.edu.ups.modelo.Cliente;
import ec.edu.ups.modelo.Registro;
import ec.edu.ups.modelo.Reserva;
import ec.edu.ups.modelo.Sitio;
import ec.edu.ups.modelo.TablaPrecios;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.modelo.Vehiculo;
import ec.edu.ups.validar.ValidarFabrica;
import ec.edu.ups.vista.jfCliente;
import ec.edu.ups.vista.jfLogin;
import ec.edu.ups.vista.jfParqueadero;
import ec.edu.ups.vista.jfPrincipal;
import ec.edu.ups.vista.jfReportes;
import ec.edu.ups.vista.jfSitio;
import ec.edu.ups.vista.jfTablaPrecios;
import ec.edu.ups.vista.jfUsuario;
import javax.persistence.EntityManager;
import utils.JPAUtils;

/**
 *
 */
public class ControladorPrincipal implements ActionListener {;//clase que controla el formulario principal
    private final jfPrincipal frm;//variable instancia del formulario principal
    private final ControladorCliente controladorCliente;
    private final ControladorUsuario controladorUsuario;
    private final ControladorSitio controladorSitio;
    private final ControladorRegistro controladorRegistro;
    private final ControladorVehiculo controladorVehiculo;
    private final ControladorReserva controladorReserva;
    private final ControladorTablaPrecios controladorTablaPrecios;
    private final ControladorFactura controladorFactura;
    private Usuario usuario;
     private EntityManager em;
    
    public ControladorPrincipal(jfPrincipal frm)  {//constructor
        em = JPAUtils.getEntityManager();
        this.frm = frm;
        this.frm.jmenClientes.addActionListener(this);
        this.frm.jmenPrecios.addActionListener(this);
        this.frm.jmenRepFactura.addActionListener(this);
        this.frm.jmenRepIngresos.addActionListener(this);
        this.frm.jmenRepReserva.addActionListener(this);
        this.frm.jmenRepSitios.addActionListener(this);
        this.frm.jmenSitios.addActionListener(this);
        this.frm.jmenUsuarios.addActionListener(this);
        this.frm.jmenVehiculos.addActionListener(this);
        this.frm.jmenVerSitios.addActionListener(this);
        this.frm.jmenInicioSesion.addActionListener(this);
        this.frm.jmenCerrarSesion.addActionListener(this);
        this.frm.jmenSalir.addActionListener(this);
        this.frm.jmenGenerarSitios.addActionListener(this);
        
        controladorCliente = new ControladorCliente(em);
        controladorUsuario = new ControladorUsuario(em);
        controladorSitio = new ControladorSitio(em);
        controladorRegistro = new ControladorRegistro(em);
        controladorVehiculo = new ControladorVehiculo(em);
        controladorReserva = new ControladorReserva(em);
        controladorTablaPrecios = new ControladorTablaPrecios(em);
        controladorFactura = new ControladorFactura(em);
    }
    public void iniciar() {//método que muestra el formulario 
        
        generarUsuarioPrueba();
        GenerarTablaPrecios();
        bloquearMenus();
        frm.setVisible(true);
        frm.setLocationRelativeTo(null);
    }
    @Override//manejador de eventos
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frm.jmenUsuarios) {//evento clic menu evaluar enfermedad
            Usuario usuariomod = new Usuario();
            jfUsuario frmaux = new jfUsuario();
            ControladorJfUsuario controladorJfUsuario = new ControladorJfUsuario(usuariomod, frmaux,controladorUsuario);
            controladorJfUsuario.iniciar();
        }
        if (e.getSource() == frm.jmenClientes) {//evento clic menu evaluar enfermedad
            Cliente cliente = new Cliente();
            jfCliente frmaux = new jfCliente();
            ControladorJfCliente controladorJfCliente = new ControladorJfCliente(cliente, frmaux,controladorCliente);
            controladorJfCliente.iniciar();
        }
        if (e.getSource() == frm.jmenSitios) {//evento clic menu evaluar enfermedad
            Sitio sitio = new Sitio();
            jfSitio frmaux = new jfSitio();
            ControladorJfSitio controladorJfSitio = new ControladorJfSitio(sitio, frmaux,controladorSitio);
            controladorJfSitio.iniciar();
        }
        if (e.getSource() == frm.jmenVerSitios) {//evento clic menu evaluar enfermedad
            jfParqueadero frmaux = new jfParqueadero();
            ControladorJfParqueadero controladorJfSitio = new ControladorJfParqueadero("ver",controladorSitio,frmaux,controladorRegistro,
                    controladorCliente,controladorReserva,controladorVehiculo,controladorTablaPrecios,controladorFactura);
            controladorJfSitio.iniciar();
        }
        if(e.getSource() == frm.jmenInicioSesion){
            jfLogin frmaux=new jfLogin();
            ControladorJfLogin controladorJfLogin = new ControladorJfLogin(this, controladorUsuario, frmaux);
            controladorJfLogin.iniciar();
        }
        if(e.getSource() == frm.jmenCerrarSesion){
            bloquearMenus();
            usuario=null;
        }
        if(e.getSource() == frm.jmenPrecios){
            TablaPrecios tabla = new TablaPrecios();
            jfTablaPrecios frmaux = new jfTablaPrecios();
            ControladorJfTablaValores controladorJfTablaPrecios = new ControladorJfTablaValores(tabla, frmaux,controladorTablaPrecios);
            controladorJfTablaPrecios.iniciar();
        }
        if(e.getSource()== frm.jmenSalir)
            {
            GuardarEnArchivo();
            System.exit(0);
        }
        if(e.getSource()==frm.jmenRepFactura)
        {
            jfReportes frmaux = new jfReportes();
            ControladorJfReporte controladorJfReporte = new ControladorJfReporte("REPORTE DE FACTURAS", controladorFactura.ListarFacturaRegistro(), frmaux);
            controladorJfReporte.iniciar();
        }
        if(e.getSource()==frm.jmenRepReserva)
        {
            jfReportes frmaux = new jfReportes();
            ControladorJfReporte controladorJfReporte = new ControladorJfReporte("REPORTE DE RESERVAS", controladorReserva.Listar(), frmaux);
            controladorJfReporte.iniciar();
        }
        if(e.getSource()==frm.jmenRepIngresos)
        {
            jfReportes frmaux = new jfReportes();
            ControladorJfReporte controladorJfReporte = new ControladorJfReporte("REPORTE DE RESERVAS", controladorRegistro.Listar(), frmaux);
            controladorJfReporte.iniciar();
        }
        if(e.getSource()==frm.jmenRepSitios)
        {
            jfReportes frmaux = new jfReportes();
            ControladorJfReporte controladorJfReporte = new ControladorJfReporte("REPORTE DE RESERVAS", controladorSitio.Listar(), frmaux);
            controladorJfReporte.iniciar();
        }
        if(e.getSource() == frm.jmenGenerarSitios){
            String nsitios = JOptionPane.showInputDialog("Ingresa cantidad de sitios a generar");
            ValidarFabrica validarFabrica = new ValidarFabrica();
            IValidar ivalidar = validarFabrica.getTipo("entero");
            if (ivalidar.validar(nsitios))
                GenerarSitios(Integer.parseInt(nsitios));
        }
            
    }
    private void GenerarSitios(int numeroSitios)
    {
        
        for (int i = 0; i < numeroSitios; i++) {
            Sitio objSitio = new Sitio(i,"aleatorio",1.5f,1.2f,"libre");
            controladorSitio.crearSitio(objSitio);
        }
        JOptionPane.showMessageDialog(null, "Se crearon correctamente "+numeroSitios+" sitios");
        frm.jmenGenerarSitios.setVisible(false);
    }
    private void GenerarTablaPrecios()
    {
        TablaPrecios tabla1 = new TablaPrecios(0, "aprobado",1f,1);
        TablaPrecios tabla2 = new TablaPrecios(0, "aprobado",1.5f,2);
        TablaPrecios tabla3 = new TablaPrecios(0, "aprobado",1.75f,3);
        TablaPrecios tabla4 = new TablaPrecios(0, "aprobado",2f,4);
        TablaPrecios tabla5 = new TablaPrecios(0, "aprobado",2.25f,5);
        TablaPrecios tabla6 = new TablaPrecios(0, "aprobado",2.50f,6);
        TablaPrecios tabla7 = new TablaPrecios(0, "aprobado",2.75f,7);
        TablaPrecios tabla8 = new TablaPrecios(0, "aprobado",3f,8);
        controladorTablaPrecios.crearTablaPrecio(tabla1);
        controladorTablaPrecios.crearTablaPrecio(tabla2);
        controladorTablaPrecios.crearTablaPrecio(tabla3);
        controladorTablaPrecios.crearTablaPrecio(tabla4);
        controladorTablaPrecios.crearTablaPrecio(tabla5);
        controladorTablaPrecios.crearTablaPrecio(tabla6);
        controladorTablaPrecios.crearTablaPrecio(tabla7);
        controladorTablaPrecios.crearTablaPrecio(tabla8);
    }
    private void generarUsuarioPrueba(){
        Usuario usuario1 = new Usuario("user", "user", 0, "0301633285", "user","user", "user@user.com","072156325");
        usuario1.setPerfilUsuario("usuario");
        Usuario usuario2 = new Usuario("admin", "admin", 0, "0301804910", "admin","admin", "admin@admin.com","072156325");
        usuario2.setPerfilUsuario("administrador");
        controladorUsuario.crearUsuario(usuario1);
        controladorUsuario.crearUsuario(usuario2);
    }
    
    public void bloquearMenus(){
        frm.jmenCerrarSesion.setEnabled(false);
        frm.jmenClientes.setEnabled(false);
        frm.jmenPrecios.setEnabled(false);
        frm.jmenRepFactura.setEnabled(false);
        frm.jmenRepIngresos.setEnabled(false);
        frm.jmenRepReserva.setEnabled(false);
        frm.jmenRepSitios.setEnabled(false);
        frm.jmenSitios.setEnabled(false);
        frm.jmenUsuarios.setEnabled(false);
        frm.jmenVehiculos.setEnabled(false);
        frm.jmenVerSitios.setEnabled(false);
        frm.jmenGenerarSitios.setEnabled(false);
        
    }
    public void ActivarTodoMenu(){
        frm.jmenCerrarSesion.setEnabled(true);
        frm.jmenClientes.setEnabled(true);
        frm.jmenPrecios.setEnabled(true);
        frm.jmenRepFactura.setEnabled(true);
        frm.jmenRepIngresos.setEnabled(true);
        frm.jmenRepReserva.setEnabled(true);
        frm.jmenRepSitios.setEnabled(true);
        frm.jmenSitios.setEnabled(true);
        frm.jmenUsuarios.setEnabled(true);
        frm.jmenVehiculos.setEnabled(true);
        frm.jmenVerSitios.setEnabled(true);
        frm.jmenGenerarSitios.setEnabled(true);
    }
    public void ActivarMenuUsuario(){
        frm.jmenCerrarSesion.setEnabled(true);
        frm.jmenClientes.setEnabled(false);
        frm.jmenPrecios.setEnabled(false);
        frm.jmenSitios.setEnabled(false);
        frm.jmenUsuarios.setEnabled(false);
        frm.jmenVehiculos.setEnabled(false);
        frm.jmenVerSitios.setEnabled(true);
        frm.jmenGenerarSitios.setEnabled(false);
        
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    private void GuardarEnArchivo()
    {
        ManejadorArchivos<Cliente> archClientes = ManejadorArchivos.getInstancia("clientes.obj");
        archClientes.setListado(controladorCliente.findAll());
        archClientes.Escribir();
         ManejadorArchivos<Usuario> archUsuarios = ManejadorArchivos.getInstancia("usuarios.obj");
        archUsuarios.setListado(controladorUsuario.findAll());
        archUsuarios.Escribir();   
                 ManejadorArchivos<Sitio> archSitios = ManejadorArchivos.getInstancia("sitios.obj");
        archSitios.setListado(controladorSitio.findAll());
        archSitios.Escribir(); 
                 ManejadorArchivos<Registro> archRegistros = ManejadorArchivos.getInstancia("registros.obj");
        archRegistros.setListado(controladorRegistro.findAll());
        archRegistros.Escribir(); 
                 ManejadorArchivos<Reserva> archReserva = ManejadorArchivos.getInstancia("reservas.obj");
        archReserva.setListado(controladorReserva.findAll());
        archReserva.Escribir(); 
                 ManejadorArchivos<Vehiculo> archVehiculo = ManejadorArchivos.getInstancia("vehiculos.obj");
        archVehiculo.setListado(controladorVehiculo.findAll());
        archVehiculo.Escribir(); 
         ManejadorArchivos<TablaPrecios> archTablaPrecios = ManejadorArchivos.getInstancia("tablaprecios.obj");
        archTablaPrecios.setListado(controladorTablaPrecios.findAll());
        archTablaPrecios.Escribir(); 
        JOptionPane.showMessageDialog(null, "Archivos guardados correctamente");
    }
    
            }
