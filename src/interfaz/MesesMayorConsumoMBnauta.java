package interfaz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

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

public class MesesMayorConsumoMBnauta extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JTable tablaMeses;
	private MayorGastoKbTableModel modeloMeses;
	private ChartPanel chartPanel;

	public MesesMayorConsumoMBnauta() {
		setTitle("Consumo mensual de Cuentas Nauta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Botones
		JButton btnCargarDatos = new JButton("Cargar Datos");
		btnCargarDatos.setBounds(45, 384, 145, 47);
		contentPane.add(btnCargarDatos);

		JButton btnCargarGrafico = new JButton("Cargar Gráfico");
		btnCargarGrafico.setBounds(427, 384, 145, 47);
		contentPane.add(btnCargarGrafico);

		// Panel principal
		panel = new JPanel();
		panel.setBounds(10, 11, 614, 370);
		panel.setLayout(null);
		contentPane.add(panel);

		// Tabla
		modeloMeses = new MayorGastoKbTableModel();
		tablaMeses = new JTable(modeloMeses);
		JScrollPane scrollPane = new JScrollPane(tablaMeses);
		scrollPane.setBounds(10, 10, 580, 349);
		panel.add(scrollPane);

		// Listeners
		btnCargarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarDatosTabla();
			}
		});

		btnCargarGrafico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarGrafico();
			}
		});
	}

	private void cargarDatosTabla() {
		HashMap<String, Double> consumos = CuentaNauta.calcularKbGastadosMeses();
		modeloMeses.cargarConsumos(consumos);
	}

	private void cargarGrafico() {
		HashMap<String, Double> consumos = CuentaNauta.calcularKbGastadosMeses();
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (Map.Entry<String, Double> entry : consumos.entrySet()) {
			dataset.addValue(entry.getValue(), "Consumo", entry.getKey());
		}

		JFreeChart chart = ChartFactory.createBarChart(
				"Consumo por Mes", "Mes", "KB", dataset);

		if (chartPanel != null) {
			panel.remove(chartPanel);
		}

		chartPanel = new ChartPanel(chart);
		chartPanel.setBounds(10, 170, 580, 180);
		panel.add(chartPanel);
		panel.repaint();
		panel.revalidate();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MesesMayorConsumoMBnauta frame = new MesesMayorConsumoMBnauta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}