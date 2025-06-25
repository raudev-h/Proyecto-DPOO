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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ListadoRepresentante extends JDialog {
    private JTable table;
    private RepresentanteTableModel tableModel;
    private static ListadoRepresentante instance;

    public static void main(String[] args) {
        try {
            ListadoRepresentante dialog = new ListadoRepresentante();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ListadoRepresentante() {
        setBounds(100, 100, 1126, 662);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(15, 16, 1074, 496);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
        scrollPane.setBounds(15, 36, 1044, 452);
        panel.add(scrollPane);
        
        // Inicializa el modelo y la tabla
        tableModel = new RepresentanteTableModel();
        table = new JTable(tableModel);
        
        // Configuración de estilo idéntica a ListadoClientes
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFont(new Font("Serif", Font.PLAIN, 18));
        scrollPane.setViewportView(table);
        
        // Estilo del header
        JTableHeader header = table.getTableHeader();
        Font headerFont = new Font("Serif", Font.PLAIN, 20);
        header.setFont(headerFont);
        table.setRowHeight(25);
        
        // Título
        JLabel lblListadoDeRepresentante = new JLabel("Listado de Representantes");
        lblListadoDeRepresentante.setFont(new Font("Serif", Font.BOLD, 21));
        lblListadoDeRepresentante.setBounds(15, 0, 250, 20);
        panel.add(lblListadoDeRepresentante);
        
        // Botón Cargar Datos (mismo estilo)
        JButton btnCargar = new JButton("Cargar Datos");
        btnCargar.setForeground(Color.WHITE);
        btnCargar.setBackground(new Color(0, 0, 153));
        btnCargar.setFont(new Font("Serif", Font.BOLD, 21));
        btnCargar.setBounds(434, 528, 189, 29);
        getContentPane().add(btnCargar);
        
        
        
        btnCargar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarDatos();
            }
        });
        
        configurarMenuContextual();
    }

    private void cargarDatos() {
        ArrayList<Representante> representantes = EmpresaTelecomunicaciones.getInstancia().getRepresentantes();
        if(representantes == null) {
            representantes = new ArrayList<>(); // Lista vacía si es nulo
        }
        tableModel.cargarDatos(representantes);
    }

    private void configurarMenuContextual() {
        final JPopupMenu popupMenu = new JPopupMenu();
        
        JMenuItem menuEditar = new JMenuItem("Editar");
        JMenuItem menuReasignar = new JMenuItem("Reasignar Cliente");
        JMenuItem menuEliminar = new JMenuItem("Eliminar");
        
        // Personalización de los menús
        menuEditar.setFont(new Font("Serif", Font.PLAIN, 20));
        menuReasignar.setFont(new Font("Serif", Font.PLAIN, 20));
        menuEliminar.setFont(new Font("Serif", Font.PLAIN, 20));
        
        // Acción para eliminar representante
        menuEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                
                if (selectedRow >= 0) {
                    // Configurar estilo del diálogo de confirmación
                    UIManager.put("OptionPane.messageFont", new Font("Serif", Font.BOLD, 20));
                    UIManager.put("OptionPane.buttonFont", new Font("Serif", Font.BOLD, 18));
                    
                    int confirm = JOptionPane.showConfirmDialog(null,
                        "¿Está seguro que desea eliminar este representante?", 
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                    
                    if (confirm == JOptionPane.YES_OPTION) {
                        String idRepresentante = (String) tableModel.getValueAt(selectedRow, 1);
                        System.out.println(idRepresentante);
                        boolean eliminado = EmpresaTelecomunicaciones.getInstancia().eliminarRepresentante(idRepresentante);
                        
                        if (eliminado) {
                            cargarDatos();
                            JOptionPane.showMessageDialog(
                                null, 
                                "Representante eliminado correctamente", 
                                "Éxito", 
                                JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(
                                null, 
                                "No se pudo eliminar el representante", 
                                "Error", 
                                JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(
                        null, 
                        "Por favor seleccione un representante para eliminar", 
                        "Advertencia", 
                        JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        popupMenu.add(menuEditar);
        popupMenu.add(menuReasignar);
        popupMenu.add(menuEliminar);
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                showPopupIfTriggered(e);
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                showPopupIfTriggered(e);
            }
            
            private void showPopupIfTriggered(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {  
                    int row = table.rowAtPoint(e.getPoint());
                    if (row >= 0) {
                        table.setRowSelectionInterval(row, row);
                        popupMenu.show(table, e.getX(), e.getY());
                    }
                }
            }
        });
    }

    // Patrón Singleton como en ListadoClientes
    public static void abrirListadoRepresentante() {
        if (instance == null) {
            instance = new ListadoRepresentante();
            instance.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            instance.setVisible(true);
        } else {
            if (!instance.isVisible()) {
                instance.setVisible(true);
            } else {
                // Configurar estilo del mensaje
                UIManager.put("OptionPane.messageFont", new Font("Serif", Font.BOLD, 20));
                UIManager.put("OptionPane.buttonFont", new Font("Serif", Font.BOLD, 18));
                UIManager.put("OptionPane.background", new Color(240, 240, 240));
                UIManager.put("Panel.background", new Color(240, 240, 240));
                UIManager.put("OptionPane.title", new Font("Serif", Font.PLAIN, 20));
                
                JOptionPane.showMessageDialog(null, 
                    "La ventana del listado de representantes ya se encuentra abierta",
                    null,
                    JOptionPane.INFORMATION_MESSAGE);
                instance.toFront();
            }
        }
    }

    @Override
    public void dispose() {
        instance = null;
        super.dispose();
    }
    
    public static Representante getRepresentanteSeleccionado(){
    	
    	return EmpresaTelecomunicaciones.getInstancia().getRepresentantes().get(0);
    }
    
    
}