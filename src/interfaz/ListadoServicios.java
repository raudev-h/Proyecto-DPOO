package interfaz;
import auxiliares.*;
import logica.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListadoServicios extends JDialog {
    private JTabbedPane tabbedPane;
    private TelefonoFijoTableModel modelFijos;
    private TelefonoMovilTableModel modelMoviles;
    private CuentaNautaTableModel modelNauta;
    private static ListadoServicios instance;

    public static void main(String[] args) {
        try {
            ListadoServicios dialog = new ListadoServicios();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ListadoServicios() {
        setBounds(100, 100, 1126, 662);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(15, 16, 1074, 496);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        // Configuración del JTabbedPane
        tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(15, 36, 1044, 452);
        panel.add(tabbedPane);
        
        // Configurar fuente general para todas las pestañas
        tabbedPane.setFont(new Font("Serif", Font.PLAIN, 18));
        
        panel.add(tabbedPane);
        
        // Crear modelos
        modelFijos = new TelefonoFijoTableModel();
        modelMoviles = new TelefonoMovilTableModel();
        modelNauta = new CuentaNautaTableModel();
        
        // Añadir pestañas con estilo
        tabbedPane.addTab("Teléfonos Fijos", crearTabla(modelFijos));
        tabbedPane.addTab("Teléfonos Móviles", crearTabla(modelMoviles));
        tabbedPane.addTab("Cuentas Nauta", crearTabla(modelNauta));
        
        // Título
        JLabel lblListadoDeServicios = new JLabel("Listado de Servicios");
        lblListadoDeServicios.setFont(new Font("Serif", Font.BOLD, 21));
        lblListadoDeServicios.setBounds(15, 0, 195, 20);
        panel.add(lblListadoDeServicios);
        
        // Botón Cargar Datos 
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
        
        

    }

    private JScrollPane crearTabla(DefaultTableModel model) {
        JTable table = new JTable(model);
        
        // Estilo idéntico al ListadoClientes
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFont(new Font("Serif", Font.PLAIN, 18));
        table.setRowHeight(25);
        
        // Estilo del header
        JTableHeader header = table.getTableHeader();
        Font headerFont = new Font("Serif", Font.PLAIN, 20);
        header.setFont(headerFont);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
        return scrollPane;
    }

    private void cargarDatos() {
        modelFijos.cargarDatos(EmpresaTelecomunicaciones.getInstancia().getTelefonosFijos());
        modelMoviles.cargarDatos(EmpresaTelecomunicaciones.getInstancia().getTelefonosMoviles());
        modelNauta.cargarDatos(EmpresaTelecomunicaciones.getInstancia().getCuentasNautas());
        
    }
    
    // Método estático para abrir la ventana, y asegurar que sea solo 1
    public static void abrirListadoServicio() {
        if(instance == null) {
            instance = new ListadoServicios();
            instance.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            instance.setVisible(true);        
        } else {
            if(!instance.isVisible()) {
                instance.setVisible(true);
            } else {
                UIManager.put("OptionPane.messageFont", new Font("Serif", Font.BOLD, 20));
                UIManager.put("OptionPane.buttonFont", new Font("Serif", Font.BOLD, 18));
                UIManager.put("OptionPane.background", new Color(240, 240, 240));
                UIManager.put("Panel.background", new Color(240, 240, 240));
                
                JOptionPane.showMessageDialog(null, 
                    "La ventana del listado de servicios ya se encuentra abierta",
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
}


