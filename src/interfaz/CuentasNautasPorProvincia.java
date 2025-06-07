package interfaz;

import auxiliares.*;
import runner.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import logica.EmpresaTelecomunicaciones;

public class CuentasNautasPorProvincia extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CuentasNautasPorProvincia dialog = new CuentasNautasPorProvincia();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CuentasNautasPorProvincia() {
		setBounds(100, 100, 986, 528);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(83, 62, 747, 326);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
		llenarTabla();
	}
	public static void llenarTabla(){
		
		CantidadCuentasNautasProvincias tablaCuentas = new CantidadCuentasNautasProvincias(EmpresaTelecomunicaciones.getInstancia().menorCantCuentasNauta());
		
		table.setModel(tablaCuentas);
		table.repaint();
		
	}
}
