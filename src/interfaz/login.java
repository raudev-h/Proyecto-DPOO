package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

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
		contentPane.add(lblUsuario);
		
		JLabel lblNewLabel = new JLabel("Contrase\u00F1a");
		lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 22));
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
		
		JLabel lblBienvenidoDeVuelta = new JLabel("Bienvenido ");
		lblBienvenidoDeVuelta.setFont(new Font("Serif", Font.BOLD, 32));
		lblBienvenidoDeVuelta.setBounds(47, 28, 269, 35);
		contentPane.add(lblBienvenidoDeVuelta);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Serif", Font.PLAIN, 21));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(new Color(0, 0, 153));
		btnSalir.setBounds(224, 264, 92, 29);
		contentPane.add(btnSalir);
		
		JLabel lblDeVuelta = new JLabel("de vuelta!");
		lblDeVuelta.setFont(new Font("Serif", Font.BOLD, 32));
		lblDeVuelta.setBounds(210, 22, 279, 47);
		contentPane.add(lblDeVuelta);
	}
}
