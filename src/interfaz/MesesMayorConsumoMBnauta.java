package interfaz;

import auxiliares.MayorGastoKbTableModel;
import logica.CuentaNauta;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;
import javax.swing.ListSelectionModel;

public class MesesMayorConsumoMBnauta extends JDialog {
    private JTable table;
    private MayorGastoKbTableModel tableModel;
    private ChartPanel chartPanel;
    private static MesesMayorConsumoMBnauta instance;
    
    public static void main(String[] args) {
        try {
            MesesMayorConsumoMBnauta dialog = new MesesMayorConsumoMBnauta();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    MesesMayorConsumoMBnauta() {
        setBounds(100, 100, 1126, 662);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(15, 16, 1074, 496);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
        scrollPane.setBounds(15, 36, 1044, 280);
        panel.add(scrollPane);
        
        tableModel = new MayorGastoKbTableModel();
        table = new JTable(tableModel);
        
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFont(new Font("Serif", Font.PLAIN, 20));
        table.setRowHeight(25);
        scrollPane.setViewportView(table);
        
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Serif", Font.PLAIN, 20));
        
        JLabel lblTitulo = new JLabel("Meses de Mayor Consumo MB Nauta");
        lblTitulo.setFont(new Font("Serif", Font.BOLD, 21));
        lblTitulo.setBounds(15, 0, 350, 20);
        panel.add(lblTitulo);
        
        JButton btnCargarDatos = new JButton("Cargar Datos");
        btnCargarDatos.setForeground(Color.WHITE);
        btnCargarDatos.setBackground(new Color(0, 0, 153));
        btnCargarDatos.setFont(new Font("Serif", Font.BOLD, 21));
        btnCargarDatos.setBounds(300, 528, 189, 29);
        getContentPane().add(btnCargarDatos);
        
        JButton btnCargarGrafico = new JButton("Cargar Gráfico");
        btnCargarGrafico.setForeground(Color.WHITE);
        btnCargarGrafico.setBackground(new Color(0, 0, 153));
        btnCargarGrafico.setFont(new Font("Serif", Font.BOLD, 21));
        btnCargarGrafico.setBounds(550, 528, 189, 29);
        getContentPane().add(btnCargarGrafico);
        
        btnCargarDatos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HashMap<String, Double> consumos = CuentaNauta.calcularKbGastadosMeses();
                tableModel.cargarConsumos(consumos);
            }
        });
        
        btnCargarGrafico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HashMap<String, Double> consumos = CuentaNauta.calcularKbGastadosMeses();
                DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                for (Map.Entry<String, Double> entry : consumos.entrySet()) {
                    dataset.addValue(entry.getValue(), "Consumo", entry.getKey());
                }
                JFreeChart chart = ChartFactory.createBarChart("Consumo por Mes", "Mes", "KB", dataset);
                
                if (chartPanel != null) {
                    table.remove(chartPanel);
                }
                chartPanel = new ChartPanel(chart);
                chartPanel.setBounds(15, 320, 1044, 170);
                table.add(chartPanel);
                table.repaint();
                table.revalidate();
            }
        });
    }
    
    public static void abrirConsumoMBNauta() {
        if (instance == null) {
            instance = new MesesMayorConsumoMBnauta();
            instance.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            instance.setVisible(true);
        } else {
            if (!instance.isVisible()) {
                instance.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "La ventana de consumo MB Nauta ya se encuentra abierta");
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