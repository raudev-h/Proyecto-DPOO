package interfaz;
import imagenes.*;

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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import auxiliares.ClientesServiciosTableModel;
import auxiliares.CuentaNautaTableModel;
import runner.Inicializadora;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import logica.Cliente;
import logica.EmpresaTelecomunicaciones;

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicializadora.Inicializar();
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
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1800, 900);
			contentPane = new JPanel(){
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public void paintComponent(Graphics g){
					Image img = Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/imagenes/d.png"));
					g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(),this);
				}
			};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1830, 39);
		contentPane.add(menuBar);
		
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
				ListadoClientes.abrirListadoClientes();
			}
		});
		mntmClientes.setFont(new Font("Serif", Font.PLAIN, 21));
		mnGestion.add(mntmClientes);
		
		JMenuItem mntmServicios = new JMenuItem("Servicios");
		mntmServicios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				 ListadoServicios.abrirListadoServicio();
			}
		});
		mntmServicios.setFont(new Font("Serif", Font.PLAIN, 20));
		mnGestion.add(mntmServicios);
		
		JMenuItem mntmRepresentantes = new JMenuItem("Representantes");
		mntmRepresentantes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 ListadoRepresentante.abrirListadoRepresentante();;
				
				
			}
		});
		mntmRepresentantes.setFont(new Font("Serif", Font.PLAIN, 20));
		mnGestion.add(mntmRepresentantes);
		
		JMenu mnReportes = new JMenu("Reportes        ");
		mnReportes.setFont(new Font("Serif", Font.BOLD, 22));
		menuBar.add(mnReportes);
		
		JMenu mnServicios_1 = new JMenu("Servicios");
		mnServicios_1.setFont(new Font("Serif", Font.PLAIN, 21));
		mnReportes.add(mnServicios_1);
		
		JMenuItem mntmMesesConMayor = new JMenuItem("1. Meses con mayor gasto en kb de Cuentas Nautas");
		mntmMesesConMayor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			MesesMayorConsumoMBnauta frame = new MesesMayorConsumoMBnauta();
		     frame.setVisible(true);
		  }
	    });
			
		
	
		mntmMesesConMayor.setFont(new Font("Serif", Font.PLAIN, 21));
		mnServicios_1.add(mntmMesesConMayor);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("2. Provincias con la menor cantidad de Cuentas Nautas de Personas Naturales\r\n");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CuentasNautasPorProvincia cuentasNautas = new CuentasNautasPorProvincia();
				cuentasNautas.setVisible(true);
			}
		});
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
		mntmClientesCon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClientesMayorConsumoLlamadasLargaDistancia llamadasLargaDistancia = new ClientesMayorConsumoLlamadasLargaDistancia();
				llamadasLargaDistancia.setVisible(true);
				
			}
		});
		mntmClientesCon.setFont(new Font("Serif", Font.PLAIN, 21));
		mnClientes_1.add(mntmClientesCon);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("3. Clientes con todos los servicios contratados");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		        try {
		        // Verificar que la instancia no sea null
		           EmpresaTelecomunicaciones empresa = EmpresaTelecomunicaciones.getInstancia();
		           if (empresa == null) {
		             JOptionPane.showMessageDialog(null, "Error: La empresa no está inicializada", 
		                                            "Error", JOptionPane.ERROR_MESSAGE);
		               return;
		            }
		            
		            // Obtener los clientes
		            ArrayList<Cliente> clientesConTodos = empresa.clientesConTodosLosTiposServicio();
		            if (clientesConTodos == null) {
		                JOptionPane.showMessageDialog(null, "Error: No se pudieron obtener los clientes", 
		                                            "Error", JOptionPane.ERROR_MESSAGE);
		                return;
		            }
		            
		            // Crear la ventana
		            ClientesConTodosLosServiciosContratados clientesFull = new ClientesConTodosLosServiciosContratados();
		            
		            // Cargar datos y mostrar
		            clientesFull.cargarDatos(clientesConTodos);
		            clientesFull.setVisible(true);
		            
		        } catch (Exception e) {
		            JOptionPane.showMessageDialog(null, 
		                "Error al abrir la ventana: " + e.getMessage(), 
		                "Error", JOptionPane.ERROR_MESSAGE);
		            e.printStackTrace();
		        }
		    }
		});
		    

		mntmNewMenuItem_2.setFont(new Font("Serif", Font.PLAIN, 21));
		mnClientes_1.add(mntmNewMenuItem_2);
		
		JMenu mnAyuda = new JMenu("Ayuda       ");
		mnAyuda.setFont(new Font("Serif", Font.BOLD, 22));
		menuBar.add(mnAyuda);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JOptionPane.showMessageDialog(null,
					    "*Servicios Telefonicos de ETECSA\n*Versiï¿½n: 1.0\n*Lanzada en junio de 2025\n*Desarrolladores:\n-Raul Hechavarria\n-Aniel Varela\n-Ruben Anazco ",
					    "Acerca de",
					    JOptionPane.INFORMATION_MESSAGE);
				
				
				
			}
		});
		mntmAcercaDe.setFont(new Font("Serif", Font.PLAIN, 21));
		mnAyuda.add(mntmAcercaDe);
	
		
		
		
	}
}
