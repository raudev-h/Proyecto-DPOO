package interfaz;
import imagenes.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import runner.Inicializadora;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import logica.Cliente;
import logica.CuentaNauta;
import logica.EmpresaTelecomunicaciones;
import logica.Servicio;

public class Principal extends JFrame {

    private JPanel contentPane;
    private String imagenFondoActual = "/imagenes/d.png"; // Imagen por defecto
    private ClientesConTodosLosServiciosContratados ventanaClientes = null;
    private MesesMayorConsumoMBnauta ventanaMesesKb = null;
    
    
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
       // setBounds(0, 0, 1600, 900);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setLocationRelativeTo(null);


        // Crear panel de fondo personalizado
        contentPane = new FondoPanel();
        ((FondoPanel) contentPane).setImagenFondo(imagenFondoActual); // Carga inicial

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 3000, 39);
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
                // Pasar referencia de Principal al abrir el listado
                ListadoClientes.abrirListadoClientes(Principal.this);
            }
        });
        mntmClientes.setFont(new Font("Serif", Font.PLAIN, 21));
        mnGestion.add(mntmClientes);
        
        JMenuItem mntmServicios = new JMenuItem("Servicios");
        mntmServicios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ListadoServicios.abrirListadoServicio(Principal.this);
            }
        });
        mntmServicios.setFont(new Font("Serif", Font.PLAIN, 20));
        mnGestion.add(mntmServicios);
        
        JMenuItem mntmRepresentantes = new JMenuItem("Representantes");
        mntmRepresentantes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ListadoRepresentante.abrirListadoRepresentante(Principal.this);
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
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    EmpresaTelecomunicaciones empresa = EmpresaTelecomunicaciones.getInstancia();

                    if (empresa == null) {
                        JOptionPane.showMessageDialog(null, "La empresa no está inicializada.",
                                                      "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Inicializar arreglo de consumos totales por mes (12 meses)
                    long[] consumosTotalesPorMes = new long[12]; // todos 0 al inicio

                    // Sumar consumos de todas las cuentas nauta mes a mes
                    for (Servicio s : empresa.getServicios()) {
                        if (s instanceof CuentaNauta) {
                            CuentaNauta c = (CuentaNauta) s;
                            HashMap<String, Double> consumosCuenta = CuentaNauta.calcularKbGastadosMeses();
                            for (Map.Entry<String, Double> entry : consumosCuenta.entrySet()) {
                            	int mesIndex = mesAIndice(entry.getKey());
                                if (mesIndex >= 0 && mesIndex < 12) {
                                    consumosTotalesPorMes[mesIndex] += entry.getValue().longValue();
                                }
                            }
                        }
                    }

                    // Mostrar la ventana con el arreglo de consumos por mes
                    if (ventanaMesesKb == null || !ventanaMesesKb.isDisplayable()) {
                        ventanaMesesKb = new MesesMayorConsumoMBnauta();
                    }
                    ventanaMesesKb.cargarDatos(consumosTotalesPorMes);
                    ventanaMesesKb.setVisible(true);
                    ventanaMesesKb.toFront();
                    ventanaMesesKb.requestFocus();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                        "Error al mostrar los meses de mayor consumo: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
        mntmMesesConMayor.setFont(new Font("Serif", Font.PLAIN, 21));
        mnServicios_1.add(mntmMesesConMayor);
        
        JMenuItem mntmNewMenuItem = new JMenuItem("2. Provincias con la menor cantidad de Cuentas Nautas de Personas Naturales\r\n");
        mntmNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                CuentasNautasPorProvincia cuentasNautas = new CuentasNautasPorProvincia(Principal.this);
                cuentasNautas.setVisible(true);
            }
        });
        mntmNewMenuItem.setFont(new Font("Serif", Font.PLAIN, 21));
        mnServicios_1.add(mntmNewMenuItem);
        
        JMenuItem mntmTelefonosMovil = new JMenuItem("3. Telefonos movil que tienen al menos una llamada que supera la duracion ");
        mntmTelefonosMovil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ListadoTlfLlamadasMayores.abrirListadoLlamadasMayores(Principal.this);	
            }
        });
        mntmTelefonosMovil.setFont(new Font("Serif", Font.PLAIN, 21));
        mnServicios_1.add(mntmTelefonosMovil);
        
        JMenu mnClientes_1 = new JMenu("Clientes");
        mnClientes_1.setFont(new Font("Serif", Font.PLAIN, 21));
        mnReportes.add(mnClientes_1);
        
        JMenuItem mntmNewMenuItem_1 = new JMenuItem("1. Clientes Premium de Cuenta Nauta");
        mntmNewMenuItem_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ListadoClientesPremiumNauta.abrirListadoClientesPremium(Principal.this);
            }
        });
        mntmNewMenuItem_1.setFont(new Font("Serif", Font.PLAIN, 21));
        mnClientes_1.add(mntmNewMenuItem_1);
        
        JMenuItem mntmClientesCon = new JMenuItem("2. Clientes con mayor consumo en Llamadas de Larga Distancia");
        mntmClientesCon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ClientesMayorConsumoLlamadasLargaDistancia llamadasLargaDistancia = new ClientesMayorConsumoLlamadasLargaDistancia(Principal.this);
                llamadasLargaDistancia.setVisible(true);
            }
        });
        mntmClientesCon.setFont(new Font("Serif", Font.PLAIN, 21));
        mnClientes_1.add(mntmClientesCon);
        
        JMenuItem mntmClientesCon_1 = new JMenuItem("3. Clientes con todos los servicios contratados");
        mntmClientesCon_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    EmpresaTelecomunicaciones empresa = EmpresaTelecomunicaciones.getInstancia();
                    if (empresa == null) {
                        JOptionPane.showMessageDialog(null, "Error: La empresa no está inicializada",
                                                      "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    ArrayList<Cliente> clientesConTodos = empresa.clientesConTodosLosTiposServicio();
                    if (clientesConTodos == null) {
                        JOptionPane.showMessageDialog(null, "Error: No se pudieron obtener los clientes",
                                                      "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (ventanaClientes == null || !ventanaClientes.isDisplayable()) {
                        ventanaClientes = new ClientesConTodosLosServiciosContratados();
                        ventanaClientes.cargarDatos(clientesConTodos);
                        ventanaClientes.setVisible(true);
                    } else {
                        ventanaClientes.toFront();
                        ventanaClientes.requestFocus();
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,
                        "Error al abrir la ventana: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        });
        mntmClientesCon_1.setFont(new Font("Serif", Font.PLAIN, 21));
        mnClientes_1.add(mntmClientesCon_1);
        
        
        JMenu mnAyuda = new JMenu("Ayuda       ");
		mnAyuda.setFont(new Font("Serif", Font.BOLD, 22));
		menuBar.add(mnAyuda);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ayuda ayuda = new Ayuda();
				ayuda.setVisible(true);
			}
		});
		mntmAcercaDe.setFont(new Font("Serif", Font.PLAIN, 21));
		mnAyuda.add(mntmAcercaDe);
    }
    
    protected int mesAIndice(String key) {
		// TODO Auto-generated method stub
		return 0;
	}

	// MÃ©todo para cambiar la imagen de fondo
    public void cambiarImagenFondo(String nuevaImagen) {
        this.imagenFondoActual = nuevaImagen;
        ((FondoPanel) contentPane).setImagenFondo(nuevaImagen);
        contentPane.repaint();
    }
    
    // Clase interna para el panel con fondo personalizado
    class FondoPanel extends JPanel {
        private Image img;
        private String currentImagePath;
        
        public void setImagenFondo(String path) {
            if (!path.equals(currentImagePath)) {
                currentImagePath = path;
                img = Toolkit.getDefaultToolkit().getImage(Principal.class.getResource(path));
                // Forzar carga inmediata
                MediaTracker tracker = new MediaTracker(this);
                tracker.addImage(img, 0);
                try {
                    tracker.waitForID(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        public int mesAIndice(String mes) {//este metodo es para convertir meses en numeros
            switch (mes.toLowerCase()) {
                case "enero": return 1;
                case "febrero": return 2;
                case "marzo": return 3;
                case "abril": return 4;
                case "mayo": return 5;
                case "junio": return 6;
                case "julio": return 7;
                case "agosto": return 8;
                case "septiembre": return 9;
                case "octubre": return 10;
                case "noviembre": return 11;
                case "diciembre": return 12;
                default: return -1;
            }
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (img != null) {
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
