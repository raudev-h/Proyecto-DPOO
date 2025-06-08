package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
<<<<<<< HEAD
=======
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
>>>>>>> d4356db030773f4e8df4e2e87ede1d32e00f39e0

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
<<<<<<< HEAD
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
=======

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
>>>>>>> d4356db030773f4e8df4e2e87ede1d32e00f39e0

public class login extends JFrame {

	private JPanel contentPane;
<<<<<<< HEAD
	private JTextField textField;
	private JPasswordField passwordField;
=======
	private JTextField textFieldUsuario;
	private JPasswordField textFieldPassword;
>>>>>>> d4356db030773f4e8df4e2e87ede1d32e00f39e0

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public login() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 775, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
<<<<<<< HEAD
		setLocationRelativeTo(null);
		
		JButton btnNewButton = new JButton("Iniciar Sesi\u00F3n");
		btnNewButton.setFont(new Font("Serif", Font.PLAIN, 21));
		btnNewButton.setBounds(61, 264, 148, 29);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Principal principal = new Principal();
				principal.setVisible(true);
			}
		});
		contentPane.setLayout(null);
		btnNewButton.setBackground(new Color(0, 0, 153));
		btnNewButton.setForeground(Color.WHITE);
		contentPane.add(btnNewButton);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Serif", Font.PLAIN, 22));
		lblUsuario.setBounds(61, 87, 128, 29);
=======
		//MENSAJE DE ERROR
		final JLabel mensajeError = new JLabel("Usuario o contrase\u00F1a incorractos");
		mensajeError.setForeground(Color.RED);
		mensajeError.setBackground(Color.BLACK);
		mensajeError.setFont(new Font("Serif", Font.PLAIN, 20));
		mensajeError.setBounds(57, 219, 287, 20);
		contentPane.add(mensajeError);
		mensajeError.setVisible(false);
		
		setLocationRelativeTo(null);
		
		
		JButton iniciarSesion = new JButton("Iniciar Sesi\u00F3n");
		iniciarSesion.setFont(new Font("Serif", Font.PLAIN, 21));
		iniciarSesion.setBounds(61, 264, 148, 29);
		iniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Obtener los datos de los campos de user y password
				String usuario = textFieldUsuario.getText();
				String password = textFieldPassword.getText();
				
				if(validarUsuario(usuario) && validarPassword(password)){
					dispose();
					Principal principal = new Principal();
					principal.setVisible(true);
				}
				else{
					mensajeError.setVisible(true);
					
				}
				
			}
		});
		contentPane.setLayout(null);
		iniciarSesion.setBackground(new Color(0, 0, 153));
		iniciarSesion.setForeground(Color.WHITE);
		contentPane.add(iniciarSesion);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Serif", Font.PLAIN, 22));
		lblUsuario.setBounds(57, 79, 128, 29);
>>>>>>> d4356db030773f4e8df4e2e87ede1d32e00f39e0
		contentPane.add(lblUsuario);
		
		JLabel lblNewLabel = new JLabel("Contrase\u00F1a");
		lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 22));
<<<<<<< HEAD
		lblNewLabel.setBounds(61, 174, 115, 20);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setBounds(61, 132, 255, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordField.setBounds(61, 210, 255, 26);
		contentPane.add(passwordField);
=======
		lblNewLabel.setBounds(61, 151, 115, 20);
		contentPane.add(lblNewLabel);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldUsuario.setBounds(57, 113, 255, 26);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		textFieldPassword = new JPasswordField();
		textFieldPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldPassword.setBounds(57, 177, 255, 26);
		contentPane.add(textFieldPassword);
>>>>>>> d4356db030773f4e8df4e2e87ede1d32e00f39e0
		
		JLabel lblBienvenidoDeVuelta = new JLabel("Bienvenido ");
		lblBienvenidoDeVuelta.setFont(new Font("Serif", Font.BOLD, 32));
		lblBienvenidoDeVuelta.setBounds(47, 28, 269, 35);
		contentPane.add(lblBienvenidoDeVuelta);
		
<<<<<<< HEAD
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Serif", Font.PLAIN, 21));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(new Color(0, 0, 153));
		btnSalir.setBounds(224, 264, 92, 29);
		contentPane.add(btnSalir);
=======
		JButton salir = new JButton("Salir");
		salir.setFont(new Font("Serif", Font.PLAIN, 21));
		salir.setForeground(Color.WHITE);
		salir.setBackground(new Color(0, 0, 153));
		salir.setBounds(224, 264, 92, 29);
		contentPane.add(salir);
>>>>>>> d4356db030773f4e8df4e2e87ede1d32e00f39e0
		
		JLabel lblDeVuelta = new JLabel("de vuelta!");
		lblDeVuelta.setFont(new Font("Serif", Font.BOLD, 32));
		lblDeVuelta.setBounds(210, 22, 279, 47);
		contentPane.add(lblDeVuelta);
<<<<<<< HEAD
=======
		
		
		
		
	}
	
	//Validar nombre de usuario este correcto
	public boolean validarUsuario(String usuario){
		
		return !usuario.trim().isEmpty() && usuario.equals("Admin");
	}
	//Validar que la contraseña este correcta y no vacia 
	public boolean validarPassword(String password){
		
		return !password.trim().isEmpty() && password.equals("etecsa");
>>>>>>> d4356db030773f4e8df4e2e87ede1d32e00f39e0
	}
}
