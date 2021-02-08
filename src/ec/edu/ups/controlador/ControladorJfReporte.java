/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.TableModel;
import ec.edu.ups.vista.jfReportes;
import ec.edu.ups.vista.jfTicket;

/**
 * controlador que maneja el formulario para hacer mantenimiento de la tabla
 * usuario implementa actionlistener para atrapar eventos como acción del mouse
 */
public class ControladorJfReporte implements ActionListener {

    private final jfReportes  frm;
    private final TableModel contenido;
    private final String titulo;

    //constructor
    public ControladorJfReporte(String titulo, TableModel contenido, jfReportes frm) {
        //asignar valor a las variables 
        this.frm = frm;
        this.frm.jbutCerrar.addActionListener(this);
        this.contenido=contenido;
        this.titulo=titulo;
    }
        
      
    //método local para mostrar el formulario
    public void iniciar() {
        frm.setTitle(titulo);
        frm.jtDatos.setModel(contenido);
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

