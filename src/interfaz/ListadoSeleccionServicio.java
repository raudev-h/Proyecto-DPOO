package interfaz;

import auxiliares.ServicioSeleccionTableModel;
import logica.EmpresaTelecomunicaciones;
import logica.Servicio;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListadoSeleccionServicio extends JDialog {
    private JTable table;
    private ServicioSeleccionTableModel tableModel;
    private Servicio servicioSeleccionado;
    private JButton btnSeleccionar;

    public ListadoSeleccionServicio() {
        setBounds(100, 100, 1126, 700);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        setModal(true);
        setTitle("Seleccionar Servicio");
        
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
        
        tableModel = new ServicioSeleccionTableModel();
        table = new JTable(tableModel);
        
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFont(new Font("Serif", Font.PLAIN, 18));
        scrollPane.setViewportView(table);
        
        JTableHeader header = table.getTableHeader();
        Font headerFont = new Font("Serif", Font.PLAIN, 20);
        header.setFont(headerFont);
        table.setRowHeight(25);
        
        JLabel lblListado = new JLabel("Seleccionar Servicio");
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
                seleccionarServicio();
            }
        });
        
        cargarDatos();
    }

    private void cargarDatos() {
        // Obtener servicios disponibles
        ArrayList<Servicio> serviciosDisponibles = 
            EmpresaTelecomunicaciones.getInstancia().getServiciosDisponibles();
        
        if (serviciosDisponibles == null || serviciosDisponibles.isEmpty()) {
            UIManager.put("OptionPane.messageFont", new Font("Serif", Font.PLAIN, 18));
            JOptionPane.showMessageDialog(
                this, 
                "No hay servicios disponibles", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE
            );
            dispose();
        } else {
            tableModel.cargarDatos(serviciosDisponibles);
        }
    }

    private void seleccionarServicio() {
    	
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String numeroServicio = (String) tableModel.getValueAt(selectedRow, 1);
            // Buscar el servicio por su número
            servicioSeleccionado = EmpresaTelecomunicaciones.getInstancia().buscarTelefono(numeroServicio);
            
            dispose();
        } else {
            UIManager.put("OptionPane.messageFont", new Font("Serif", Font.PLAIN, 18));
            JOptionPane.showMessageDialog(
                this, 
                "Por favor seleccione un servicio de la lista", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE
            );
        }
    }

    public static Servicio abrirYSeleccionar() {
        ListadoSeleccionServicio dialog = new ListadoSeleccionServicio();
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
        return dialog.servicioSeleccionado;
    }

    public Servicio getServicioSeleccionado() {
        return this.servicioSeleccionado;
    }
}