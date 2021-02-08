/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import ec.edu.ups.excepcion.CedulaExcepcion;
import ec.edu.ups.excepcion.EmailExcepcion;
import ec.edu.ups.excepcion.RequeridoExcepcion;
import ec.edu.ups.excepcion.TelefonoExcepcion;
import ec.edu.ups.excepcion.TextoExcepcion;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import ec.edu.ups.modelo.Cliente;
import ec.edu.ups.vista.jfCliente;

/**
 * controlador que maneja el formulario para hacer mantenimiento de la tabla
 * usuario implementa actionlistener para atrapar eventos como acción del mouse
 */
public class ControladorJfCliente implements ActionListener {

    private Cliente mod,modaux;
    private final jfCliente  frm;
    public boolean nuevo;
    private final ControladorCliente controladorCliente;

    //constructor
    public ControladorJfCliente(Cliente mod, jfCliente frm, ControladorCliente controladorCliente) {
        //asignar valor a las variables 
        this.mod = mod;
        this.frm = frm;
        this.controladorCliente=controladorCliente;
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
                    modaux = new Cliente();
                    //asignar valores de jtable a entidad modelo usuario
                    modaux.setCodigo(Integer.parseInt(String.valueOf(target.getValueAt(row, 0))));
                    modaux.setCodigoIdentificacion(String.valueOf(target.getValueAt(row, 1)));
                    modaux.setNombre(String.valueOf(target.getValueAt(row, 2)));
                    modaux.setApellido(String.valueOf(target.getValueAt(row, 3)));
                    modaux.setNumeroTelefono(String.valueOf(target.getValueAt(row, 4)));
                    modaux.setCorreo(String.valueOf(target.getValueAt(row, 5)));
                    modaux.setTipoCliente(String.valueOf(target.getValueAt(row, 6)));
                                   
                    //llamada a método local
                    llenarFormulario();
                } catch (NumberFormatException | CedulaExcepcion | RequeridoExcepcion | TextoExcepcion | TelefonoExcepcion | EmailExcepcion ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }

    //método local para mostrar el formulario
    public void iniciar() {
        nuevo = true;
        //asignar valor a propiedades del formulario
        llenartabla();
        frm.jtxtId.setEditable(false);
        frm.setVisible(true);
        frm.setLocationRelativeTo(null);
    }
//controlador de eventos del formulario

    @Override
    public void actionPerformed(ActionEvent e) {
        //evento clic de botón guardar
        if (e.getSource() == frm.jbutGuardar) {
            try {
               
                    //asignar valores de elementos del formulario a propiedades del objeto
                    mod = new Cliente();
                    
                    mod.setCodigoIdentificacion(frm.jtxtCedula.getText());
                    mod.setNombre(frm.jtxtNombre.getText());
                    mod.setApellido(frm.jtxtApellido.getText());
                    mod.setNumeroTelefono(frm.jtxtNumeroTelefono.getText());
                    mod.setCorreo(frm.jtxtCorreo.getText());
                    mod.setTipoCliente(frm.jcmbTipoCliente.getSelectedItem().toString());

                    if (nuevo) {
                        if (controladorCliente.crearCliente(mod)) {//registrar nuevo usuario
                            JOptionPane.showMessageDialog(null, "Registro Guardado");
                            limpiar();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al Guardar");
                        }
                    } else {
                        mod.setCodigo(Integer.valueOf(frm.jtxtId.getText()));
                        if (controladorCliente.modificarCliente(modaux, mod)) {//modificar datos de usuario
                            JOptionPane.showMessageDialog(null, "Registro Guardado");
                            limpiar();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al Guardar");
                        }
                    }
                    llenartabla();
                

            } catch (HeadlessException ex) {
                System.out.println(ex.getMessage());
            } catch (CedulaExcepcion | TextoExcepcion | RequeridoExcepcion | TelefonoExcepcion | EmailExcepcion ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        //evento clic de botón eliminar
        if (e.getSource() == frm.jbutEliminar) {
            try {
                if (controladorCliente.eliminarCliente(modaux)) {
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
        frm.jtxtApellido.setText(null);
        frm.jtxtCedula.setText(null);
        frm.jtxtCorreo.setText(null);
        frm.jtxtId.setText(null);
        frm.jtxtNombre.setText(null);
        frm.jtxtNumeroTelefono.setText(null);
        nuevo = true;
    }
//método local para llenar datos en jtable

    public void llenartabla() {
        try {
            frm.jtDatos.setModel(controladorCliente.Listar());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //método local para asignar valores del objeto modelo a los elementos del formulario
    void llenarFormulario() {
        frm.jtxtApellido.setText(modaux.getApellido());
        frm.jtxtCedula.setText(modaux.getCodigoIdentificacion());
        frm.jtxtCorreo.setText(modaux.getCorreo());
        frm.jtxtId.setText(String.valueOf(modaux.getCodigo()));
        frm.jtxtNombre.setText(modaux.getNombre());
        frm.jtxtNumeroTelefono.setText(modaux.getNumeroTelefono());
        frm.jcmbTipoCliente.setSelectedItem(modaux.getTipoCliente());
        nuevo = false;
    }
}
