/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import ec.edu.ups.excepcion.FloatExcepcion;
import ec.edu.ups.excepcion.RequeridoExcepcion;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import ec.edu.ups.modelo.Sitio;
import ec.edu.ups.vista.jfSitio;

/**
 * controlador que maneja el formulario para hacer mantenimiento de la tabla
 * usuario implementa actionlistener para atrapar eventos como acción del mouse
 */
public class ControladorJfSitio implements ActionListener {

    private Sitio mod,modaux;
    private final jfSitio  frm;
    public boolean nuevo;
    private final ControladorSitio controladorSitio;


    //constructor
    public ControladorJfSitio(Sitio mod, jfSitio frm, ControladorSitio controladorSitio) {
        //asignar valor a las variables 
        this.mod = mod;
        this.frm = frm;
        this.controladorSitio=controladorSitio;
        //agregar controlador de eventos a elementos del formulario
        this.frm.jbutEliminar.addActionListener(this);
        this.frm.jbutGuardar.addActionListener(this);
        this.frm.jbutLimpiar.addActionListener(this);
       
        //asignar controlador de evento mouse jtable
        frm.jtDatos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                try {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    modaux = new Sitio();
                    //asignar valores de jtable a entidad modelo usuario
                    modaux.setCodigo(Integer.parseInt(String.valueOf(target.getValueAt(row, 0))));
                    modaux.setDescripcion(String.valueOf(target.getValueAt(row, 1)));
                    modaux.setCostoNormal(String.valueOf(target.getValueAt(row, 2)));
                    modaux.setCostoReserva(String.valueOf(target.getValueAt(row, 3)));
                    modaux.setEstado(String.valueOf(target.getValueAt(row, 4)));                                   
                    //llamada a método local
                    llenarFormulario();
                } catch (NumberFormatException | RequeridoExcepcion | FloatExcepcion  ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }

    //método local para mostrar el formulario
    public void iniciar() {
        nuevo = true;
        //asignar valor a propiedades del formulario
        frm.jtxtId.setEditable(false);
        frm.jtxtEstado.setEditable(false);
        frm.setVisible(true);
        frm.setLocationRelativeTo(null);
        llenartabla();
    }
//controlador de eventos del formulario

    @Override
    public void actionPerformed(ActionEvent e) {
        //evento clic de botón guardar
        if (e.getSource() == frm.jbutGuardar) {
            try {
               
                    //asignar valores de elementos del formulario a propiedades del objeto
                    mod = new Sitio();
                    
                    mod.setDescripcion(frm.jtxtDescripcion.getText());
                    mod.setCostoNormal(frm.jtxtCostoNormal.getText());
                    mod.setCostoReserva(frm.jtxtCostoReserva.getText());
                    mod.setEstado(frm.jtxtEstado.getText());                    

                    if (nuevo) {
                        if (controladorSitio.crearSitio(mod)) {//registrar nuevo usuario
                            JOptionPane.showMessageDialog(null, "Registro Guardado");
                            limpiar();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al Guardar");
                        }
                    } else {
                        mod.setCodigo(Integer.valueOf(frm.jtxtId.getText()));
                        if (controladorSitio.modificarSitio(modaux, mod)) {//modificar datos de usuario
                            JOptionPane.showMessageDialog(null, "Registro Guardado");
                            limpiar();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al Guardar");
                        }
                    }
                    llenartabla();
                

            } catch (HeadlessException ex) {
                System.out.println(ex.getMessage());
            } catch (RequeridoExcepcion | FloatExcepcion ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        //evento clic de botón eliminar
        if (e.getSource() == frm.jbutEliminar) {
            try {
                if (controladorSitio.eliminarSitio(modaux)) {
                    JOptionPane.showMessageDialog(null, "Registro Eliminado");
                    limpiar();
                } 
                llenartabla();
            } catch (HeadlessException ex) {
                System.out.println(ex.getMessage());
            }
        }
        //evento clic botón limpiar
        if (e.getSource() == frm.jbutLimpiar) {
            limpiar();
            nuevo = true;
        }
    }
//método local para encerar elementos del formulario

    public void limpiar() {
        frm.jtxtId.setText(null);
        frm.jtxtDescripcion.setText(null);
        frm.jtxtCostoNormal.setText(null);
        frm.jtxtCostoReserva.setText(null);
        frm.jtxtEstado.setText(null);
        nuevo = true;
    }
//método local para llenar datos en jtable

    public void llenartabla() {
        try {
            frm.jtDatos.setModel(controladorSitio.Listar());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //método local para asignar valores del objeto modelo a los elementos del formulario
    void llenarFormulario() {
        frm.jtxtId.setText(String.valueOf(modaux.getCodigo()));
        frm.jtxtDescripcion.setText(modaux.getDescripcion());
        frm.jtxtCostoNormal.setText(String.valueOf(modaux.getCostoNormal()));
        frm.jtxtCostoReserva.setText(String.valueOf(modaux.getCostoReserva()));
        frm.jtxtEstado.setText(modaux.getEstado());
        nuevo = false;
    }
}
