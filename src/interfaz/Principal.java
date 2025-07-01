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
import javax.swing.Box;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import runner.Inicializadora;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Principal extends JFrame {

    private JPanel contentPane;
    private String imagenFondoActual = "/imagenes/d.png"; // Imagen por defecto
    
    private static Principal instance = null;

    /**
     * Create the frame.
     */
    private Principal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, screenSize.width , screenSize.height);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        setLocationRelativeTo(null);

        //======= Content Panel =========
        
        //Crear panel de fondo personalizado
        contentPane = new FondoPanel();
        ((FondoPanel) contentPane).setImagenFondo(imagenFondoActual); // Carga inicial
//        contentPane = new JPanel();
        
        //=================

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu menu = new JMenu("");
        menuBar.add(menu);
        
        JMenu mnArchivo = new JMenu("Sesión     ");
        mnArchivo.setIcon(new ImageIcon(getClass().getResource("/imagenes/LogOut.png")));
        mnArchivo.setFont(new Font("Serif", Font.BOLD, 24));
        menuBar.add(mnArchivo);
        
        JMenuItem mntmCerrarSesin = new JMenuItem("Cerrar Sesi\u00F3n");
        mntmCerrarSesin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();
                login.getInstance().setVisible(true);
                login.getInstance().resetearCampos();

            }
        });
        mntmCerrarSesin.setFont(new Font("Serif", Font.PLAIN, 22));
        mnArchivo.add(mntmCerrarSesin);
        
        JMenuItem mntmSalir = new JMenuItem("Salir");
        mntmSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();
            }
        });
        mntmSalir.setFont(new Font("Serif", Font.PLAIN, 22));
        mnArchivo.add(mntmSalir);
        
        JMenu mnGestion = new JMenu("Gesti\u00F3n      ");
        mnGestion.setIcon(new ImageIcon(getClass().getResource("/imagenes/IconoGestion.png")));
        mnGestion.setFont(new Font("Serif", Font.BOLD, 24));
        menuBar.add(mnGestion);
        
        JMenuItem mntmClientes = new JMenuItem("Clientes");
        mntmClientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ListadoClientes.abrirListadoClientes(Principal.this);
                
            }
        });
        mntmClientes.setFont(new Font("Serif", Font.PLAIN, 22));
        mnGestion.add(mntmClientes);
        
        JMenuItem mntmServicios = new JMenuItem("Servicios");
        mntmServicios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ListadoServicios.abrirListadoServicio(Principal.this);
            }
        });
        mntmServicios.setFont(new Font("Serif", Font.PLAIN, 22));
        mnGestion.add(mntmServicios);
        
        JMenuItem mntmRepresentantes = new JMenuItem("Representantes");
        mntmRepresentantes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ListadoRepresentante.abrirListadoRepresentante(Principal.this);
            }
        });
        mntmRepresentantes.setFont(new Font("Serif", Font.PLAIN, 22));
        mnGestion.add(mntmRepresentantes);
        
        JMenu mnReportes = new JMenu("Reportes        ");
        mnReportes.setIcon(new ImageIcon(getClass().getResource("/imagenes/IconoReporte.png")));
        mnReportes.setFont(new Font("Serif", Font.BOLD, 24));
        menuBar.add(mnReportes);
        
        JMenu mnServicios_1 = new JMenu("Servicios");
        mnServicios_1.setFont(new Font("Serif", Font.PLAIN, 22));
        mnReportes.add(mnServicios_1);
        
        JMenuItem mntmMesesConMayor = new JMenuItem("1. Meses con mayor gasto en kb de Cuentas Nautas");
        mntmMesesConMayor.setFont(new Font("Serif", Font.PLAIN, 22));
        mnServicios_1.add(mntmMesesConMayor);
        mntmMesesConMayor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	MesesMayorConsumoMBnauta m = new MesesMayorConsumoMBnauta();
            	m.setVisible(true);
            }
        });
        
        JMenuItem mntmNewMenuItem = new JMenuItem("2. Provincias con la menor cantidad de Cuentas Nautas de Personas Naturales\r\n");
        mntmNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                CuentasNautasPorProvincia cuentasNautas = new CuentasNautasPorProvincia(Principal.this);
                cuentasNautas.setVisible(true);
            }
        });
        mntmNewMenuItem.setFont(new Font("Serif", Font.PLAIN, 22));
        mnServicios_1.add(mntmNewMenuItem);
        
        JMenuItem mntmTelefonosMovil = new JMenuItem("3. Teléfonos movil que tienen al menos una llamada que supera la duracion ");
        mntmTelefonosMovil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ListadoTlfLlamadasMayores.abrirListadoLlamadasMayores(Principal.this);	
            }
        });
        mntmTelefonosMovil.setFont(new Font("Serif", Font.PLAIN, 22));
        mnServicios_1.add(mntmTelefonosMovil);
        
        JMenu mnClientes_1 = new JMenu("Clientes");
        mnClientes_1.setFont(new Font("Serif", Font.PLAIN, 22));
        mnReportes.add(mnClientes_1);
        
        JMenuItem mntmNewMenuItem_1 = new JMenuItem("1. Clientes Premium de Cuenta Nauta");
        mntmNewMenuItem_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ListadoClientesPremiumNauta.abrirListadoClientesPremium(Principal.this);
            }
        });
        mntmNewMenuItem_1.setFont(new Font("Serif", Font.PLAIN, 22));
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
        mntmClientesCon_1.setFont(new Font("Serif", Font.PLAIN, 22));
        mnClientes_1.add(mntmClientesCon_1);
        mntmClientesCon_1.addActionListener(new ActionListener(){
        	 public void actionPerformed(ActionEvent arg0) {
        		 ClientesConTodosLosServiciosContratados ct = new ClientesConTodosLosServiciosContratados();
        		 ct.setVisible(true);
                
             }
         });
        
        
        JMenu mnAyuda = new JMenu("Ayuda       ");
        mnAyuda.setIcon(new ImageIcon(getClass().getResource("/imagenes/IconoAyuda.png")));
		mnAyuda.setFont(new Font("Serif", Font.BOLD, 24));
		menuBar.add(mnAyuda);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ayuda.mostrarAyuda(Principal.this);;
				
			}
		});
		mntmAcercaDe.setFont(new Font("Serif", Font.PLAIN, 22));
		mnAyuda.add(mntmAcercaDe);
		
		
		menuBar.add(Box.createHorizontalGlue());
		
		JButton btnAjustes = new JButton("Ajustes");
		btnAjustes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ajustes.mostrarAjustes(Principal.this);
			}
		});
		btnAjustes.setIcon(new ImageIcon(getClass().getResource("/imagenes/IconoAjustes.png")));
		btnAjustes.setBackground(Color.WHITE);
		btnAjustes.setFont(new Font("Serif", Font.BOLD, 22));
		menuBar.add(btnAjustes);
    }
    
    // M�todo para cambiar la imagen de fondo
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
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (img != null) {
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
    public static Principal getInstance() {
        if (instance == null) {
            instance = new Principal();
        }       
        return instance;
    }
}