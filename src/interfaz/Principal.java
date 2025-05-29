package interfaz;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private JPanel contentPane;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		
		
		//Ventana a lo maximo de la pantalla 
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 896, 483);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("");
		menuBar.add(menu);
		
		JMenu mnArchivo = new JMenu("Archivo     ");
		mnArchivo.setFont(new Font("Serif", Font.BOLD, 22));
		menuBar.add(mnArchivo);
		
		JMenuItem mntmCerrarSesin = new JMenuItem("Cerrar Sesi\u00F3n");
		mntmCerrarSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				login log = new login();
				log.setVisible(true);
			}
		});
		mntmCerrarSesin.setFont(new Font("Serif", Font.PLAIN, 21));
		mnArchivo.add(mntmCerrarSesin);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		mntmSalir.setFont(new Font("Serif", Font.PLAIN, 21));
		mnArchivo.add(mntmSalir);
		
		JMenu mnGestion = new JMenu("Gesti\u00F3n      ");
		mnGestion.setFont(new Font("Serif", Font.BOLD, 22));
		menuBar.add(mnGestion);
		
		JMenuItem mntmClientes = new JMenuItem("Clientes");
		mntmClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListadoClientes listadoC = new ListadoClientes();
				listadoC.setVisible(true);
			}
		});
		mntmClientes.setFont(new Font("Serif", Font.PLAIN, 21));
		mnGestion.add(mntmClientes);
		
		JMenu mnServicios = new JMenu("Servicios");
		mnServicios.setFont(new Font("Serif", Font.PLAIN, 21));
		mnGestion.add(mnServicios);
		
		JMenuItem menuItem = new JMenuItem("Lista");
		menuItem.setFont(new Font("Serif", Font.PLAIN, 21));
		mnServicios.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Crear");
		menuItem_1.setFont(new Font("Serif", Font.PLAIN, 21));
		mnServicios.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("Modificar");
		menuItem_2.setFont(new Font("Serif", Font.PLAIN, 21));
		mnServicios.add(menuItem_2);
		
		JMenuItem menuItem_3 = new JMenuItem("Eliminar");
		menuItem_3.setFont(new Font("Serif", Font.PLAIN, 21));
		mnServicios.add(menuItem_3);
		
		JMenu mnRepresentantes = new JMenu("Representantes");
		mnRepresentantes.setFont(new Font("Serif", Font.PLAIN, 21));
		mnGestion.add(mnRepresentantes);
		
		JMenuItem mntmListar = new JMenuItem("Lista");
		mntmListar.setFont(new Font("Serif", Font.PLAIN, 21));
		mnRepresentantes.add(mntmListar);
		
		JMenuItem mntmCrear = new JMenuItem("Crear");
		mntmCrear.setFont(new Font("Serif", Font.PLAIN, 21));
		mnRepresentantes.add(mntmCrear);
		
		JMenuItem mntmModificar = new JMenuItem("Modificar");
		mntmModificar.setFont(new Font("Serif", Font.PLAIN, 21));
		mnRepresentantes.add(mntmModificar);
		
		JMenuItem mntmEliminar = new JMenuItem("Eliminar");
		mntmEliminar.setFont(new Font("Serif", Font.PLAIN, 21));
		mnRepresentantes.add(mntmEliminar);
		
		JMenu mnReportes = new JMenu("Reportes        ");
		mnReportes.setFont(new Font("Serif", Font.BOLD, 22));
		menuBar.add(mnReportes);
		
		JMenu mnServicios_1 = new JMenu("Servicios");
		mnServicios_1.setFont(new Font("Serif", Font.PLAIN, 21));
		mnReportes.add(mnServicios_1);
		
		JMenuItem mntmMesesConMayor = new JMenuItem("1. Meses con mayor gasto en kb de Cuentas Nautas");
		mntmMesesConMayor.setFont(new Font("Serif", Font.PLAIN, 21));
		mnServicios_1.add(mntmMesesConMayor);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("2. Provincias con la menor cantidad de Cuentas Nautas de Personas Naturales\r\n");
		mntmNewMenuItem.setFont(new Font("Serif", Font.PLAIN, 21));
		mnServicios_1.add(mntmNewMenuItem);
		
		JMenuItem mntmTelefonosMovil = new JMenuItem("3. Telefonos movil que tienen al menos una llamada que supera los 100 min de duracion ");
		mntmTelefonosMovil.setFont(new Font("Serif", Font.PLAIN, 21));
		mnServicios_1.add(mntmTelefonosMovil);
		
		JMenu mnClientes_1 = new JMenu("Clientes");
		mnClientes_1.setFont(new Font("Serif", Font.PLAIN, 21));
		mnReportes.add(mnClientes_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("1. Clientes Premium de Cuenta Nauta");
		mntmNewMenuItem_1.setFont(new Font("Serif", Font.PLAIN, 21));
		mnClientes_1.add(mntmNewMenuItem_1);
		
		JMenuItem mntmClientesCon = new JMenuItem("2. Clientes con mayor consumo en Llamadas de Larga Distancia");
		mntmClientesCon.setFont(new Font("Serif", Font.PLAIN, 21));
		mnClientes_1.add(mntmClientesCon);
		
		JMenu mnAyuda = new JMenu("Ayuda       ");
		mnAyuda.setFont(new Font("Serif", Font.BOLD, 22));
		menuBar.add(mnAyuda);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de");
		mntmAcercaDe.setFont(new Font("Serif", Font.PLAIN, 21));
		mnAyuda.add(mntmAcercaDe);
			contentPane = new JPanel(){
				public void paintComponent(Graphics g){
					Image img = Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/images/d.png"));
					g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(),this);
				}
			};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
	}
}
