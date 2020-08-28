/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Umg.Controlador;
import Umg.Modelo.Persona;
import Umg.Modelo.PersonaDAO;
import Umg.Vista.JFVistaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
/**
 * 
 * @author @lscas98
 */
public class CtrInicio implements ActionListener{
    
    /*Instanciando*/
    PersonaDAO dao = new PersonaDAO();
    Persona p = new Persona();
    JFVistaPrincipal vista = new JFVistaPrincipal();
    DefaultTableModel modelo = new DefaultTableModel();

    public CtrInicio(JFVistaPrincipal v) {
        this.vista = v;
        this.vista.btnListar.addActionListener(this);
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnDelete.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnNuevo.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //boton mostrar listado
    if (e.getSource() == vista.btnListar) {
        LimpiarTabla();
        MostrarLista(vista.tabla);
        limpiarCampos();
        }
        //boton agregar usario
        if (e.getSource() == vista.btnAgregar) {
            if(vista.txtNom.getText().equals("")||vista.txtCorreo.getText().equals("")||vista.txtTel.getText().equals("")){
                JOptionPane.showMessageDialog(vista, "LLENE TODOS LOS CAMPOS.","AGREGAR",JOptionPane.WARNING_MESSAGE);
                }else{
                    Agregar();
                    MostrarLista(vista.tabla);
                    //limpiarCampos();
                }
            }
            //boton editar persona
            if (e.getSource() == vista.btnEditar) {
                int fila = vista.tabla.getSelectedRow();
                if (fila == -1) {
                    JOptionPane.showMessageDialog(vista, "DEBE SELECCIONAR LA FILA DEL USUARIO","ACTUALIZAR",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        int id = Integer.parseInt((String) vista.tabla.getValueAt(fila, 0).toString());
                        String nom = (String) vista.tabla.getValueAt(fila, 1);
                        String correo = (String) vista.tabla.getValueAt(fila, 2);
                        String tel = (String) vista.tabla.getValueAt(fila, 3);
                        vista.txtId.setText("" + id);
                        vista.txtNom.setText(nom);
                        vista.txtCorreo.setText(correo);
                        vista.txtTel.setText(tel);
                    }
                }
                //boton actualizar persona
                if (e.getSource() == vista.btnActualizar) {
                    Actualizar();
                    MostrarLista(vista.tabla);
                   // limpiarCampos();

                }
                    //boton eliminar persona
                    if (e.getSource() == vista.btnDelete) {
                        /*YA SE VERIFICA DENTRO DE LA FUNCION ELIMINAR*/
                            Eliminar();
                            MostrarLista(vista.tabla);
                            limpiarCampos();
                            
                    }
                        //boton limpiar campos
                        if (e.getSource() == vista.btnNuevo) {
                            limpiarCampos();
                        }

    }
    //limpiar campos
    void limpiarCampos() {
        vista.txtId.setText("");
        vista.txtNom.setText("");
        vista.txtTel.setText("");
        vista.txtCorreo.setText("");
        vista.txtNom.requestFocus();
    }
    //eliminar
    public void Eliminar() {
        int fila = vista.tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista,"DEBE SELECCIONAR LA FILA DEL USUARIO","ELIMINAR",JOptionPane.INFORMATION_MESSAGE);
        } else {
            int msj=JOptionPane.showConfirmDialog(vista, "DESEA ELIMINAR EL REGISTRO?", "CONFIRMAR",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
            if(msj==JOptionPane.YES_OPTION){
                int id = Integer.parseInt((String) vista.tabla.getValueAt(fila, 0).toString());
                dao.Delete(id);
                JOptionPane.showMessageDialog(vista,"USUARIO ELIMINADO","ELIMINAR",JOptionPane.INFORMATION_MESSAGE);
            }
        }                    
        LimpiarTabla();
    }
    //agregar
    public void Agregar() {
            if(vista.txtId.getText().equals("")){
                int msj=JOptionPane.showConfirmDialog(vista, "ESTA SEGURO DE LOS DATOS INGRESADOS?", "CONFIRMAR",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                if(msj==JOptionPane.YES_OPTION){
                String nom = vista.txtNom.getText();
                String correo = vista.txtCorreo.getText();
                String tel = vista.txtTel.getText();
                p.setNombre(nom);
                p.setCorreo(correo);
                p.setTelefono(tel);
                int r = dao.Add(p);
                if (r == 1) {
                    JOptionPane.showMessageDialog(vista, "USUARIO AGREGADO.","AGREGAR",JOptionPane.INFORMATION_MESSAGE);
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(vista, "ERROR AL AGREGAR","AGREGAR",JOptionPane.ERROR);
                }
                LimpiarTabla();
                }
                else{LimpiarTabla();}
            }
            
            else{
                 JOptionPane.showMessageDialog(vista, "ESTE USUARIO YA ESTA REGISTRADO","AGREGAR",JOptionPane.INFORMATION_MESSAGE);
                 LimpiarTabla();
            }
    }
    
//actualizar
    public void Actualizar() {
        if (vista.txtId.getText().equals("")) {
            JOptionPane.showMessageDialog(vista, "1.SELECCIONE EL USUARIO EN EL REGISTRO\n2.PRESIONE EL BOTON EDITAR PERSONA\n3.MODIFIQUE LOS DATOS NECESARIOS","ACTUALIZAR",JOptionPane.WARNING_MESSAGE);
        } else {
            int msj=JOptionPane.showConfirmDialog(vista, "ESTA SEGURO DE LOS DATOS INGRESADOS?", "CONFIRMAR",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
            if(msj==JOptionPane.YES_OPTION){
                int id = Integer.parseInt(vista.txtId.getText());
                String nom = vista.txtNom.getText();
                String correo = vista.txtCorreo.getText();
                String tel = vista.txtTel.getText();
                p.setId(id);
                p.setNombre(nom);
                p.setCorreo(correo);
                p.setTelefono(tel);
                int r = dao.Update(p);
                    if (r == 1) {
                        JOptionPane.showMessageDialog(vista, "EL USUARIO SE HA ACTUALIZADO.","ACTUALIZAR",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(vista, "ERROR AL ACTUALIZAR","ACTUALIZAR",JOptionPane.ERROR);
                    }
                    limpiarCampos();
        }
    }    
        LimpiarTabla();
    }
    
    //mostrar listas
    public void MostrarLista(JTable tabla) {
        CentrarCeldas(tabla);
        modelo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modelo);
        List<Persona> lista = dao.ToList();
        Object[] objeto = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getNombre();
            objeto[2] = lista.get(i).getCorreo();
            objeto[3] = lista.get(i).getTelefono();
            modelo.addRow(objeto);
        }
        tabla.setRowHeight(35);
        tabla.setRowMargin(10);

    }
    //centrar celdas
    void CentrarCeldas(JTable tabla) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < vista.tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }
    //limpiar tablas
    void LimpiarTabla() {
        for (int i = 0; i < vista.tabla.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }
}
