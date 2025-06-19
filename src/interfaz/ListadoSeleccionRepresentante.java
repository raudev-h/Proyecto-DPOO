package interfaz;

import auxiliares.RepresentanteTableModel;
import logica.EmpresaTelecomunicaciones;
import logica.Representante;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListadoSeleccionRepresentante extends JDialog {
    private JTable table;
    private RepresentanteTableModel tableModel;
    private Representante representanteSeleccionado;
    private JButton btnSeleccionar;

    public ListadoSeleccionRepresentante() {
        setBounds(100, 100, 1126, 700);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        setModal(true);
        setTitle("Seleccionar Representante");
        
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setBounds(15, 16, 1074, 600);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
        scrollPane.setBounds(15, 36, 1044, 500);
        panel.add(scrollPane);
        
        tableModel = new RepresentanteTableModel();
        table = new JTable(tableModel);
        
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFont(new Font("Serif", Font.PLAIN, 18));
        scrollPane.setViewportView(table);
        
        JTableHeader header = table.getTableHeader();
        Font headerFont = new Font("Serif", Font.PLAIN, 20);
        header.setFont(headerFont);
        table.setRowHeight(25);
        
        JLabel lblListado = new JLabel("Seleccionar Representante");
        lblListado.setFont(new Font("Serif", Font.BOLD, 21));
        lblListado.setBounds(15, 0, 300, 20);
        panel.add(lblListado);
        
        btnSeleccionar = new JButton("Seleccionar");
        btnSeleccionar.setBounds(437, 552, 200, 40);
        panel.add(btnSeleccionar);
        btnSeleccionar.setForeground(Color.WHITE);
        btnSeleccionar.setBackground(new Color(0, 0, 153));
        btnSeleccionar.setFont(new Font("Serif", Font.BOLD, 20));
        btnSeleccionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarRepresentante();
            }
        });
        
        cargarDatos();
    }

    private void cargarDatos() {
        // Limpiar y volver a cargar los datos frescos
        ArrayList<Representante> representantesLibres = 
            EmpresaTelecomunicaciones.getInstancia().buscarRepresentantesLibres();
        
        if (representantesLibres == null || representantesLibres.isEmpty()) {
            UIManager.put("OptionPane.messageFont", new Font("Serif", Font.PLAIN, 18));
            JOptionPane.showMessageDialog(
                this, 
                "No hay representantes disponibles sin clientes asignados", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE
            );
            dispose();
        } else {
            tableModel.cargarDatos(representantesLibres); // Refrescar la tabla
        }
    }

    private void seleccionarRepresentante() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String idRepresentante = (String) tableModel.getValueAt(selectedRow, 1);
            representanteSeleccionado = EmpresaTelecomunicaciones.getInstancia().buscarRepresentante(idRepresentante);
            dispose();
        } else {
            UIManager.put("OptionPane.messageFont", new Font("Serif", Font.PLAIN, 18));
            JOptionPane.showMessageDialog(
                this, 
                "Por favor seleccione un representante de la lista", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE
            );
        }
    }

    public static Representante abrirYSeleccionar() {
        ListadoSeleccionRepresentante dialog = new ListadoSeleccionRepresentante();
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
        return dialog.representanteSeleccionado;
    }

    public Representante getRepresentanteSeleccionado() {
        return this.representanteSeleccionado;
    }
}