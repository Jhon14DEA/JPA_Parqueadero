/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ec.edu.ups.vista.jfTablaPrecios;
import ec.edu.ups.vista.jfTicket;
import ec.edu.ups.vista.jfVerTablaPrecios;

/**
 * controlador que maneja el formulario para hacer mantenimiento de la tabla
 * usuario implementa actionlistener para atrapar eventos como acción del mouse
 */
public class ControladorJfVerTablaPrecios implements ActionListener {

    private final jfVerTablaPrecios  frm;
    private final ControladorTablaPrecios controladorTablaPrecios;

    //constructor
    public ControladorJfVerTablaPrecios(jfVerTablaPrecios frm, ControladorTablaPrecios controladorTablaPrecios) {
        //asignar valor a las variables 
        this.frm = frm;
        this.controladorTablaPrecios = controladorTablaPrecios;
        this.frm.jbutCerrar.addActionListener(this);
    }
        
      
    //método local para mostrar el formulario
    public void iniciar() {
        frm.setTitle("LISTA DE PRECIOS");
        frm.jtDatos.setModel(controladorTablaPrecios.Listar());
        frm.jtDatos.setEnabled(false);
        frm.setVisible(true);
        frm.setLocationRelativeTo(null);
    }
//controlador de eventos del formulario

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==frm.jbutCerrar)
            frm.dispose();

}
}

