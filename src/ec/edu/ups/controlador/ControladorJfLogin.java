/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import ec.edu.ups.excepcion.RequeridoExcepcion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.vista.jfLogin;

/**
 * controlador que maneja el formulario para hacer mantenimiento de la tabla
 * usuario implementa actionlistener para atrapar eventos como acción del mouse
 */
public class ControladorJfLogin implements ActionListener {

    private final jfLogin  frm;
    private final ControladorUsuario controladorUsuario;
    private final ControladorPrincipal controladorPrincipal;


    //constructor
    public ControladorJfLogin(ControladorPrincipal controladorPrincipal, ControladorUsuario controladorUsuario, jfLogin frm) {
        //asignar valor a las variables 
        this.controladorPrincipal=controladorPrincipal;
        this.controladorUsuario=controladorUsuario;
        this.frm=frm;
        //agregar controlador de eventos a elementos del formulario
        this.frm.jbutIngresar.addActionListener(this);
        this.frm.jbutcancelar.addActionListener(this);
    }

    //método local para mostrar el formulario
    public void iniciar() {
        frm.setVisible(true);
        frm.setLocationRelativeTo(null);
    }
//controlador de eventos del formulario

    @Override
    public void actionPerformed(ActionEvent e) {
        //evento clic de botón guardar
        if (e.getSource() == frm.jbutIngresar) {
            try {
                Usuario UsuarioIngresando = new Usuario();
                UsuarioIngresando.setNombreUsuario(frm.jtxtNombreUsuario.getText());
                UsuarioIngresando.setContraseña(frm.jtxtPassword.getText());
                UsuarioIngresando = controladorUsuario.Login(UsuarioIngresando);
                if (UsuarioIngresando != null)
                {
                    controladorPrincipal.setUsuario(UsuarioIngresando);
                    if(UsuarioIngresando.getPerfilUsuario().equals("administrador"))
                        controladorPrincipal.ActivarTodoMenu();
                    else
                        controladorPrincipal.ActivarMenuUsuario();
                    JOptionPane.showMessageDialog(null, "Bienvenido usuario: "+UsuarioIngresando.getNombreUsuario());
                    frm.dispose();
                }
                else
                {
                    controladorPrincipal.bloquearMenus();
                    JOptionPane.showMessageDialog(null, "Datos no encontrados, vuelva a intentar");
                }
                    
            } catch (RequeridoExcepcion ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            
    }
        if(e.getSource() == frm.jbutcancelar){
            frm.dispose();
        }
}
}
