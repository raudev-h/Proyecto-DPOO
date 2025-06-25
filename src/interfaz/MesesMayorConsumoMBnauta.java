package interfaz;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import auxiliares.MayorGastoKbTableModel;
import logica.CuentaNauta;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;

public class MesesMayorConsumoMBnauta extends JDialog {
    private static final long serialVersionUID = 1L;
    private ChartPanel chartPanel;
    private JTable table;
    private MayorGastoKbTableModel tableModel;
    private JPanel panel;
    
    public MesesMayorConsumoMBnauta() {
        setBounds(100, 100, 1126, 662);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        panel = new JPanel();
        panel.setBounds(15, 16, 1074, 496);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
        scrollPane.setBounds(15, 31, 1044, 337);
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
        
        JLabel lblTitulo = new JLabel("Meses de Mayor Consumo KB Nauta");
        lblTitulo.setFont(new Font("Serif", Font.BOLD, 21));
        lblTitulo.setBounds(15, 0, 350, 20);
        panel.add(lblTitulo);
        
        JButton btnCargarGrafico = new JButton("Cargar Gráfico");
        btnCargarGrafico.setBounds(433, 437, 189, 29);
        panel.add(btnCargarGrafico);
        btnCargarGrafico.setForeground(Color.WHITE);
        btnCargarGrafico.setBackground(new Color(0, 0, 153));
        btnCargarGrafico.setFont(new Font("Serif", Font.BOLD, 21));
        
        btnCargarGrafico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarGrafico();
            }
        });
        
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }
    
    // Método para cargar los datos usando arreglo de consumos (12 meses)
    public void cargarDatos(long[] consumosTotalesPorMes) {
        tableModel.cargarConsumos(consumosTotalesPorMes);
    }
    
    private void cargarGrafico() {
        // Extraer los datos del modelo para graficar
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Los meses los tienes definidos en el TableModel, pero aquí los definimos igual:
        String[] meses = {
            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        };

        for (int i = 0; i < meses.length; i++) {
            // Obtener el consumo de la tabla (modelo)
            Number valor = (Number) tableModel.getValueAt(i, 1);
            long consumo = valor != null ? valor.longValue() : 0;
            dataset.addValue(consumo, "Consumo", meses[i]);
        }

        JFreeChart chart = ChartFactory.createBarChart(
            "Consumo por Mes", // título del gráfico
            "Mes",             // etiqueta del eje X
            "KB",              // etiqueta del eje Y
            dataset            // conjunto de datos
        );

        if (chartPanel != null) {
            panel.remove(chartPanel);
        }

        chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(15, 320, 1044, 170);
        panel.add(chartPanel);
        panel.repaint();
        panel.revalidate();
    }

}