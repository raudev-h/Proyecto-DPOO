package interfaz;

import logica.*;
import auxiliares.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ClientesMayorConsumoLlamadasLargaDistancia extends JDialog {
    private JTable table;
    private static ClientesMayorConsumoLlamadasLargaDistancia instance;
    private static Principal ventanaPrincipal;

    
 

    public ClientesMayorConsumoLlamadasLargaDistancia(Principal principal) {
    	setModal(true);
        setBounds(100, 100, 1126, 662);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(15, 16, 1074, 496);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        ventanaPrincipal = principal;
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
        scrollPane.setBounds(15, 36, 1044, 452);
        panel.add(scrollPane);
        
        table = new JTable();
        
        // Configuración de estilo consistente
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
        JLabel lblTitulo = new JLabel("Clientes con Mayor Consumo en Llamadas de Larga Distancia");
        lblTitulo.setFont(new Font("Serif", Font.BOLD, 21));
        lblTitulo.setBounds(15, 0, 600, 20);
        panel.add(lblTitulo);
        
     // Cambiar imagen solo si tenemos referencia válida
        if (ventanaPrincipal != null) {
            ventanaPrincipal.cambiarImagenFondo("/imagenes/e.png");
        }
        
        // Asegurarnos de manejar el cierre correctamente
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                restoreBackground();
            }
        });
        
        llenarTabla();
    }
    
    private void restoreBackground() {
        if (ventanaPrincipal != null) {
            ventanaPrincipal.cambiarImagenFondo("/imagenes/d.png");
        }
    }
    

    private void llenarTabla() {
        ArrayList<Cliente> clientes = EmpresaTelecomunicaciones.getInstancia().clientesAltoConsumoLlamadaFijo();
        ClientesLlamadasLargaDistancia modelo = new ClientesLlamadasLargaDistancia(clientes);
        table.setModel(modelo);
        table.repaint();
    }
    
    // Patrón Singleton
    public static void abrirReporteLlamadasLargaDistancia(Principal principal) {
        if (instance == null) {
            instance = new ClientesMayorConsumoLlamadasLargaDistancia(principal);
            instance.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            instance.setVisible(true);
        } else {
            if (!instance.isVisible()) {
                instance.setVisible(true);
            } else {
                UIManager.put("OptionPane.messageFont", new Font("Serif", Font.BOLD, 20));
                UIManager.put("OptionPane.buttonFont", new Font("Serif", Font.BOLD, 18));
                UIManager.put("OptionPane.background", new Color(240, 240, 240));
                UIManager.put("Panel.background", new Color(240, 240, 240));
                
                JOptionPane.showMessageDialog(null, 
                    "Este reporte ya se encuentra abierto",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE);
                instance.toFront();
            }
        }
    }

    @Override
    public void dispose() {
        restoreBackground();
        instance = null;
        super.dispose();
    }
}