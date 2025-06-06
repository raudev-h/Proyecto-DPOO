package interfaz;
import auxiliares.*;

import java.awt.BorderLayout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import logica.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

public class ListadoClientes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoClientes dialog = new ListadoClientes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoClientes() {
		setBounds(100, 100, 725, 462);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(667, 363, -650, -342);
		scrollPane.setViewportView(table);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.setBounds(15, 20, 528, 337);
		contentPanel.add(table);
		setLocationRelativeTo(null);
		
		cargarTablaUsuarios();
		
		JButton btnNewButton = new JButton("Salir");
		btnNewButton.setFont(new Font("Serif", Font.BOLD, 20));
		btnNewButton.setBackground(new Color(0, 0, 153));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	
		btnNewButton.setBounds(556, 319, 115, 29);
		contentPanel.add(btnNewButton);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(null);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBounds(602, 5, 79, 29);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	// Funcion para guardar en un arreglo lo que vamos a mostrar
	public static void cargarTablaUsuarios(){
		
		System.out.println("TABLA");
		ArrayList<Cliente> usuarios = EmpresaTelecomunicaciones.getInstancia().getClientes();
		Cliente[] tabla = new Cliente[usuarios.size()];
		
		for(int i = 0; i < tabla.length; i++){
			
			tabla[i] = usuarios.get(i);
			
		}
		
		UsuarioTableModel modelo = new UsuarioTableModel(tabla);
		table.setModel(modelo);
	}
}
