package interfaz;

import auxiliares.*;
import logica.EmpresaTelecomunicaciones;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

public class CuentasNautasPorProvincia extends JDialog {
    private JTable table;
    private static CuentasNautasPorProvincia instance;
    
    public static void main(String[] args) {
        try {
            CuentasNautasPorProvincia dialog = new CuentasNautasPorProvincia();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CuentasNautasPorProvincia() {
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
        JLabel lblTitulo = new JLabel("Cuentas Nautas por Provincia");
        lblTitulo.setFont(new Font("Serif", Font.BOLD, 21));
        lblTitulo.setBounds(15, 0, 300, 20);
        panel.add(lblTitulo);
        
              
        llenarTabla();
    }

    private void llenarTabla() {
        ArrayList<Map.Entry<String, Integer>> datos = EmpresaTelecomunicaciones.getInstancia().menorCantCuentasNauta();
        CantidadCuentasNautasProvincias modelo = new CantidadCuentasNautasProvincias(datos);
        table.setModel(modelo);
        table.repaint();
    }
    
    // Patrón Singleton como en las otras ventanas
    public static void abrirCuentasNautasPorProvincia() {
        if (instance == null) {
            instance = new CuentasNautasPorProvincia();
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
                    "La ventana de Cuentas Nautas por Provincia ya está abierta",
                    "Información",
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