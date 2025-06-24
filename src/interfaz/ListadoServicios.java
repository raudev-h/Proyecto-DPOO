package interfaz;

import auxiliares.*;
import logica.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ListadoServicios extends JDialog {
    private EmpresaTelecomunicaciones empresa;
    private JTabbedPane tabbedPane;
    private TelefonoFijoTableModel modelFijos;
    private TelefonoMovilTableModel modelMoviles;
    private CuentaNautaTableModel modelNauta;
    private JPanel panelFormulario;
    private JTable tablaBloqueada;
    private static ListadoServicios instance;
    private static Principal ventanaPrincipal;
    
    // Campos para creación de servicios
    private JPanel panelCreacion;
    private JLabel lblTipoServicio;
    private JComboBox<String> cbTipoServicio;
    private JButton btnAceptarCreate;
    private JButton btnCancelarCreate;
    private JButton btnCrearServicio;
    
    // Campos para Teléfono Fijo
    private JPanel panelTelefonoFijo;
    private JTextField txtNumeroFijo;
    private JLabel lblNumeroFijo;
    
    // Campos para Teléfono Móvil
    private JPanel panelTelefonoMovil;
    private JTextField txtNumeroMovil;
    private JTextField txtMontoMovil;
    private JLabel lblNumeroMovil;
    private JLabel lblMontoMovil;

    private ListadoServicios(Principal principal) {
        empresa = EmpresaTelecomunicaciones.getInstancia();
        setModal(true);
        setTitle("Listado de Servicios");
        setBounds(100, 100, 1015, 800);
        setLocationRelativeTo(null);
        ventanaPrincipal = principal; // Guardar referencia
        
        // Cambiar imagen al abrir
        ventanaPrincipal.cambiarImagenFondo("/imagenes/e.png");
        
        initComponents();
        configurarMenuContextual();
    }

    private void initComponents() {
        // Pestañas con tablas
        tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(27, 0, 931, 662);
        tabbedPane.setFont(new Font("Serif", Font.PLAIN, 18));
        modelFijos = new TelefonoFijoTableModel();
        modelMoviles = new TelefonoMovilTableModel();
        modelNauta = new CuentaNautaTableModel();

        JScrollPane scrollFijos = crearTabla(modelFijos);
        tabbedPane.addTab("Teléfonos Fijos", scrollFijos);
        
        JScrollPane scrollMoviles = crearTabla(modelMoviles);
        tabbedPane.addTab("Teléfonos Móviles", scrollMoviles);
        
        JScrollPane scrollNauta = crearTabla(modelNauta);
        tabbedPane.addTab("Cuentas Nauta", scrollNauta);

        // Panel formulario
        panelFormulario = new JPanel();
        panelFormulario.setBounds(973, 33, 370, 629);
        panelFormulario.setPreferredSize(new Dimension(300, getHeight()));
        Border titledBorder = BorderFactory.createTitledBorder(
        	    BorderFactory.createLineBorder(Color.BLACK),
        	    "Formulario de Asignación",  
        	    TitledBorder.LEFT,  
        	    TitledBorder.TOP,
        	    new Font("Serif", Font.BOLD, 21),
        	    Color.black
        	);
        panelFormulario.setLayout(new GridBagLayout());
        panelFormulario.setVisible(false);
        panelFormulario.setBorder(titledBorder);

        // Botón Asignar Servicio
        JButton btnAsignarServicio = new JButton("Asignar Servicio");
        btnAsignarServicio.setBounds(500, 678, 214, 40);
        btnAsignarServicio.setFont(new Font("Serif", Font.BOLD, 18));
        btnAsignarServicio.setForeground(new Color(0, 0, 153));
        btnAsignarServicio.setBackground(Color.WHITE);
        btnAsignarServicio.setPreferredSize(new Dimension(1, 40));
        btnAsignarServicio.setMargin(new Insets(5, 10, 5, 10));
                
        btnAsignarServicio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
           	
                setSize(1380, 800);
                setLocationRelativeTo(null);
                panelCreacion.setVisible(false);
                mostrarFormulario();
            }
        });
        getContentPane().setLayout(null);

        getContentPane().add(tabbedPane);
        getContentPane().add(panelFormulario);
        getContentPane().add(btnAsignarServicio);
        
        // Botón Crear Servicio
        btnCrearServicio = new JButton("Crear Servicio");
        btnCrearServicio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// Cerrar formulario de asignación si está abierto
                if (panelFormulario.isVisible()) {
                    panelFormulario.setVisible(false);
                    // Desbloquear tabla (por si acaso)
                    final JScrollPane scrollPane = (JScrollPane) tabbedPane.getSelectedComponent();
                    final JTable tablaActual = (JTable) scrollPane.getViewport().getView();
                    tablaActual.setEnabled(true);
                    tablaActual.setFocusable(true);
                    tabbedPane.setEnabled(true);
                    tabbedPane.setFocusable(true);
                }
                abrirPanelCreacion();
            }
        });
        btnCrearServicio.setPreferredSize(new Dimension(1, 40));
        btnCrearServicio.setMargin(new Insets(5, 10, 5, 10));
        btnCrearServicio.setForeground(new Color(0, 0, 153));
        btnCrearServicio.setBackground(Color.WHITE);
        btnCrearServicio.setFont(new Font("Serif", Font.BOLD, 18));
        btnCrearServicio.setBounds(237, 678, 208, 40);
        getContentPane().add(btnCrearServicio);

        // Inicializar panel de creación
        initPanelCreacion();
        
        cargarDatos();
    }

    private void abrirPanelCreacion() {
        // Bloquear tabla y pestañas
        final JScrollPane scrollPane = (JScrollPane) tabbedPane.getSelectedComponent();
        final JTable tablaActual = (JTable) scrollPane.getViewport().getView();
        tablaActual.setEnabled(false);
        tablaActual.setFocusable(false);
        tabbedPane.setEnabled(false);
        tabbedPane.setFocusable(false);
        
        resetearCamposCreacion();
        panelFormulario.setVisible(false);
        panelCreacion.setVisible(true);
        setSize(1380, 800);
    }
    
    private void resetearCamposCreacion() {
        txtNumeroFijo.setText("");
        txtNumeroMovil.setText("");
        txtMontoMovil.setText("");
        cbTipoServicio.setSelectedItem("Teléfono Fijo");
        mostrarCamposSegunTipo("Teléfono Fijo");
    }

    private void initPanelCreacion() {
        panelCreacion = new JPanel();
        panelCreacion.setBorder(new LineBorder(new Color(0, 0, 0)));
        panelCreacion.setBounds(973, 33, 350, 629);
        panelCreacion.setVisible(false);
        panelCreacion.setLayout(null);
        getContentPane().add(panelCreacion);
        
        JLabel lblCreacionServicio = new JLabel("Creación de Servicio");
        lblCreacionServicio.setFont(new Font("Serif", Font.BOLD, 21));
        lblCreacionServicio.setBounds(32, 12, 280, 28);
        panelCreacion.add(lblCreacionServicio);
        
        // Combo box para selección de tipo de servicio
        lblTipoServicio = new JLabel("Tipo de Servicio");
        lblTipoServicio.setFont(new Font("Serif", Font.PLAIN, 19));
        lblTipoServicio.setBounds(35, 50, 280, 20);
        panelCreacion.add(lblTipoServicio);
        
        cbTipoServicio = new JComboBox<String>();
        cbTipoServicio.setFont(new Font("Serif", Font.PLAIN, 18));
        cbTipoServicio.setBounds(35, 80, 280, 30);
        cbTipoServicio.addItem("Teléfono Fijo");
        cbTipoServicio.addItem("Teléfono Móvil");
        cbTipoServicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipoSeleccionado = (String) cbTipoServicio.getSelectedItem();
                mostrarCamposSegunTipo(tipoSeleccionado);
            }
        });
        panelCreacion.add(cbTipoServicio);
        
        // Panel para Teléfono Fijo
        panelTelefonoFijo = new JPanel();
        panelTelefonoFijo.setBounds(0, 120, 300, 100);
        panelTelefonoFijo.setLayout(null);
        panelTelefonoFijo.setVisible(false);
        panelCreacion.add(panelTelefonoFijo);
        
        lblNumeroFijo = new JLabel("Número de Teléfono");
        lblNumeroFijo.setFont(new Font("Serif", Font.PLAIN, 19));
        lblNumeroFijo.setBounds(35, 0, 280, 20);
        panelTelefonoFijo.add(lblNumeroFijo);
        
        txtNumeroFijo = new JTextField();
        txtNumeroFijo.setBounds(35, 30, 280, 26);
        panelTelefonoFijo.add(txtNumeroFijo);
        txtNumeroFijo.setColumns(10);
        
        // Panel para Teléfono Móvil
        panelTelefonoMovil = new JPanel();
        panelTelefonoMovil.setBounds(0, 120, 300, 150);
        panelTelefonoMovil.setLayout(null);
        panelTelefonoMovil.setVisible(false);
        panelCreacion.add(panelTelefonoMovil);
        
        lblNumeroMovil = new JLabel("Número de Teléfono");
        lblNumeroMovil.setFont(new Font("Serif", Font.PLAIN, 19));
        lblNumeroMovil.setBounds(35, 0, 280, 20);
        panelTelefonoMovil.add(lblNumeroMovil);
        
        txtNumeroMovil = new JTextField();
        txtNumeroMovil.setBounds(35, 30, 280, 26);
        panelTelefonoMovil.add(txtNumeroMovil);
        txtNumeroMovil.setColumns(10);
        
        lblMontoMovil = new JLabel("Monto a Pagar");
        lblMontoMovil.setFont(new Font("Serif", Font.PLAIN, 19));
        lblMontoMovil.setBounds(35, 60, 280, 20);
        panelTelefonoMovil.add(lblMontoMovil);
        
        txtMontoMovil = new JTextField();
        txtMontoMovil.setBounds(35, 90, 280, 26);
        panelTelefonoMovil.add(txtMontoMovil);
        txtMontoMovil.setColumns(10);
        
        // Botones
        btnAceptarCreate = new JButton("Aceptar");
        btnAceptarCreate.setForeground(new Color(255, 255, 255));
        btnAceptarCreate.setBackground(new Color(0, 0, 153));
        btnAceptarCreate.setFont(new Font("Serif", Font.PLAIN, 19));
        btnAceptarCreate.setBounds(35, 580, 120, 30);
        btnAceptarCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearServicio();
            }
        });
        panelCreacion.add(btnAceptarCreate);
        
        btnCancelarCreate = new JButton("Cancelar");
        btnCancelarCreate.setBackground(new Color(255, 255, 255));
        btnCancelarCreate.setForeground(new Color(0, 0, 153));
        btnCancelarCreate.setFont(new Font("Serif", Font.PLAIN, 19));
        btnCancelarCreate.setBounds(165, 580, 120, 30);
        btnCancelarCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cerrarPanelCreacion();
            }
        });
        panelCreacion.add(btnCancelarCreate);
    }
    
    private void mostrarCamposSegunTipo(String tipoServicio) {
        panelTelefonoFijo.setVisible(false);
        panelTelefonoMovil.setVisible(false);
        
        if (tipoServicio.equals("Teléfono Fijo")) {
            panelTelefonoFijo.setVisible(true);
        } 
        else if (tipoServicio.equals("Teléfono Móvil")) {
            panelTelefonoMovil.setVisible(true);
        }
    }
    
    //Crear Servicio (Movil o Fijo)
    private void crearServicio() {
        String tipoServicio = (String) cbTipoServicio.getSelectedItem();
        StringBuilder errores = new StringBuilder();
        String numero2 = "";
        
        try {
            if (tipoServicio.equals("Teléfono Fijo")) {
            	try{
	                String numero = txtNumeroFijo.getText();
	                TelefonoFijo.validarTelefonoFijo(numero);
	                //Comprobar repetidos
	                for(Servicio s: EmpresaTelecomunicaciones.getInstancia().getServicios()){
	                	if(s instanceof TelefonoFijo){
	                		if(((TelefonoFijo)s).getNumero().equals(numero)){
	                			throw new IllegalArgumentException("Ese número de telefono ya se encuentra registrado en el sistema");
	                		}
	                		
	                		
	                	}
	                }
	              //Comprobar repetidos
	                for(Servicio s: EmpresaTelecomunicaciones.getInstancia().getServiciosDisponibles()){
	                	if(s instanceof TelefonoFijo){
	                		if(((TelefonoFijo)s).getNumero().equals(numero)){
	                			throw new IllegalArgumentException("Ese número de telefono ya se encuentra registrado en el sistema");
	                		}
	                		
	                		
	                	}
	                }
	                
	                empresa.agregarTelefonoFijo(numero);
            	    lblNumeroFijo.setForeground(Color.BLACK);
                }catch(Exception e){
                	errores.append("- ").append(e.getMessage()).append("\n");
            	    lblNumeroFijo.setForeground(Color.RED); 
                	
                }
            } 
            else if (tipoServicio.equals("Teléfono Móvil")) {
                boolean movilValido = true;
            	
                try{
	                
                	numero2 = TelefonoMovil.validarTelefonoMovil(txtNumeroMovil.getText());
                	System.out.println(numero2);
	                
	              //Comprobar repetidos en los telefonos Activos
	                for(Servicio s: EmpresaTelecomunicaciones.getInstancia().getServicios()){
	                	if(s instanceof TelefonoMovil){
	                		if(((TelefonoMovil)s).getNumero().equals(numero2)){
	                			throw new IllegalArgumentException("Ese número de telefono ya se encuentra registrado en el sistema");
	                		}
	                		
	                		
	                	}
	                }
	              //Comprobar repetidos en los telefonos Diponibles
	                for(Servicio s: EmpresaTelecomunicaciones.getInstancia().getServiciosDisponibles()){
	                	if(s instanceof TelefonoMovil){
	                		if(((TelefonoMovil)s).getNumero().equals(numero2)){
	                			throw new IllegalArgumentException("Ese número de telefono ya se encuentra registrado en el sistema");
	                		}
	                		
	                		
	                	}
	                }
	                
            	    lblNumeroMovil.setForeground(Color.BLACK);
                }catch(Exception e){
                	movilValido = false;
                	errores.append("- ").append(e.getMessage()).append("\n");
            	    lblNumeroMovil.setForeground(Color.RED);                	
                }
                
                try{
                    String montoStr = txtMontoMovil.getText();
                    TelefonoMovil.validarMonto(montoStr);
                    lblMontoMovil.setForeground(Color.BLACK);
              	
                }catch(Exception e){
                	movilValido = false;
                	errores.append("- ").append(e.getMessage()).append("\n");
                	lblMontoMovil.setForeground(Color.RED); 
                }
                if(movilValido){
                     	
                	empresa.agregarTelefonoMovil(numero2, Double.parseDouble(txtMontoMovil.getText().replace(",", ".")));
                }
                
            }
            if(errores.length() > 0){
            	UIManager.put("OptionPane.messageFont", new Font("Serif", Font.PLAIN, 18));
                UIManager.put("OptionPane.buttonFont", new Font("Serif", Font.PLAIN, 16));
            	JOptionPane.showMessageDialog(
	    			    null, 
	    			    "ERRORES EN LOS DATOS:\n" + 
	    			    "----------------------------\n" + 
	    			    errores.toString() + 
	    			    "\n----------------------------\n" + 
	    			    "Por favor, corrija los campos resaltados en rojo.", 
	    			    "Error ", 
	    			    JOptionPane.ERROR_MESSAGE
	    			);
            }else{
                cargarDatos();
                resetearCamposCreacion();
                cerrarPanelCreacion();
                
                UIManager.put("OptionPane.messageFont", new Font("Serif", Font.PLAIN, 18));
                UIManager.put("OptionPane.buttonFont", new Font("Serif", Font.PLAIN, 16));
                JOptionPane.showMessageDialog(this, "Servicio creado exitosamente", 
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            UIManager.put("OptionPane.messageFont", new Font("Serif", Font.PLAIN, 18));
            JOptionPane.showMessageDialog(this, 
                "Error al crear servicio: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cerrarPanelCreacion() {
        // Desbloquear tabla y pestañas
        final JScrollPane scrollPane = (JScrollPane) tabbedPane.getSelectedComponent();
        final JTable tablaActual = (JTable) scrollPane.getViewport().getView();
        tablaActual.setEnabled(true);
        tablaActual.setFocusable(true);
        tabbedPane.setEnabled(true);
        tabbedPane.setFocusable(true);
        
        panelCreacion.setVisible(false);
        setSize(1015, 800);
    }

    private JScrollPane crearTabla(DefaultTableModel model) {
        tablaBloqueada = new JTable(model);
        tablaBloqueada.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaBloqueada.setFont(new Font("Serif", Font.PLAIN, 18));
        tablaBloqueada.setRowHeight(25);

        JTableHeader header = tablaBloqueada.getTableHeader();
        header.setFont(new Font("Serif", Font.PLAIN, 20));

        JScrollPane scrollPane = new JScrollPane(tablaBloqueada);
        scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
        return scrollPane;
    }

    private void configurarMenuContextual() {
        for(int i = 0; i < tabbedPane.getTabCount(); i++) {
            JScrollPane scrollPane = (JScrollPane) tabbedPane.getComponentAt(i);
            final JTable tabla = (JTable) scrollPane.getViewport().getView();
            
            final JPopupMenu popupMenu = new JPopupMenu();
            
            JMenuItem menuEditar = new JMenuItem("Editar");
            JMenuItem menuEliminar = new JMenuItem("Eliminar");
            
            menuEditar.setFont(new Font("Serif", Font.PLAIN, 20));
            menuEliminar.setFont(new Font("Serif", Font.PLAIN, 20));
            
            menuEditar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = tabla.getSelectedRow();
                    if (selectedRow >= 0) {
                        editarServicio(tabla, selectedRow);
                    } else {
                        JOptionPane.showMessageDialog(ListadoServicios.this,
                            "Por favor seleccione un servicio para editar",
                            "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
            
            menuEliminar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = tabla.getSelectedRow();
                    
                    if (selectedRow >= 0) {
                        UIManager.put("OptionPane.messageFont", new Font("Serif", Font.BOLD, 20));
                        UIManager.put("OptionPane.buttonFont", new Font("Serif", Font.BOLD, 18));
                        
                        Servicio servicio = obtenerServicioSeleccionado(tabla, selectedRow);
                        String mensaje = "¿Está seguro que desea eliminar este servicio?";
                        
                        int confirm = JOptionPane.showConfirmDialog(
                            ListadoServicios.this,
                            mensaje, 
                            "Confirmar eliminación", 
                            JOptionPane.YES_NO_OPTION, 
                            JOptionPane.WARNING_MESSAGE);
                        
                        if (confirm == JOptionPane.YES_OPTION) {
                            boolean eliminado = eliminarServicio(servicio);
                            
                            if (eliminado) {
                                cargarDatos();
                                JOptionPane.showMessageDialog(
                                    ListadoServicios.this,
                                    "Servicio eliminado correctamente",
                                    "Éxito", 
                                    JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(
                                    ListadoServicios.this,
                                    "No se pudo eliminar el servicio",
                                    "Error", 
                                    JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(
                            ListadoServicios.this,
                            "Por favor seleccione un servicio para eliminar",
                            "Advertencia", 
                            JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
            
            popupMenu.add(menuEditar);
            popupMenu.add(menuEliminar);
            
            tabla.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        int row = tabla.rowAtPoint(e.getPoint());
                        if (row >= 0) {
                            tabla.setRowSelectionInterval(row, row);
                            popupMenu.show(tabla, e.getX(), e.getY());
                        }
                    }
                }
                
                @Override
                public void mouseReleased(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        int row = tabla.rowAtPoint(e.getPoint());
                        if (row >= 0) {
                            tabla.setRowSelectionInterval(row, row);
                            popupMenu.show(tabla, e.getX(), e.getY());
                        }
                    }
                }
            });
        }
    }

    private Servicio obtenerServicioSeleccionado(JTable tabla, int row) {
        int tabIndex = tabbedPane.indexOfComponent(tabla.getParent().getParent());
        
        Servicio servicio = null;
        
        if (tabIndex == 0) {
            servicio = modelFijos.getServicioAt(row);
        } else if (tabIndex == 1) {
            servicio = modelMoviles.getServicioAt(row);
        } else if (tabIndex == 2) {
            servicio = modelNauta.getServicioAt(row);
        }
        
        return servicio;
    }

    private void editarServicio(final JTable tabla, final int row) {
        Servicio servicio = obtenerServicioSeleccionado(tabla, row);
        
        if (servicio != null) {
            if (servicio instanceof TelefonoFijo) {
                editarTelefonoFijo((TelefonoFijo) servicio);
            } else if (servicio instanceof TelefonoMovil) {
                editarTelefonoMovil((TelefonoMovil) servicio);
            } else if (servicio instanceof CuentaNauta) {
                editarCuentaNauta((CuentaNauta) servicio);
            }
        }
    }

    private void editarTelefonoFijo(TelefonoFijo telefono) {
        // Implementación para editar teléfono fijo
        String nuevoNumero = JOptionPane.showInputDialog(
            this, 
            "Ingrese el nuevo número:", 
            telefono.getNumero());
        
        if (nuevoNumero != null && !nuevoNumero.isEmpty()) {
            telefono.setNumero(nuevoNumero);
            cargarDatos();
        }
    }

    private void editarTelefonoMovil(TelefonoMovil telefono) {
        // Implementación para editar teléfono móvil
        JPanel panel = new JPanel(new GridLayout(2, 2));
        JTextField txtNumero = new JTextField(telefono.getNumero());
        JTextField txtMonto = new JTextField(String.valueOf(telefono.getMontoApagar()));
        
        panel.add(new JLabel("Número:"));
        panel.add(txtNumero);
        panel.add(new JLabel("Monto:"));
        panel.add(txtMonto);
        
        int result = JOptionPane.showConfirmDialog(
            this, 
            panel, 
            "Editar Teléfono Móvil", 
            JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION) {
            telefono.setNumero(txtNumero.getText());
            try {
                double monto = Double.parseDouble(txtMonto.getText());
                telefono.setMontoApagar(monto);
                cargarDatos();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                    this, 
                    "El monto debe ser un número válido", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editarCuentaNauta(CuentaNauta cuenta) {
        // Implementación para editar cuenta nauta
        String nuevoNick = JOptionPane.showInputDialog(
            this, 
            "Ingrese el nuevo nick:", 
            cuenta.getNick());
        
        if (nuevoNick != null && !nuevoNick.isEmpty()) {
            cuenta.setNick(nuevoNick);
            cargarDatos();
        }
    }

    private boolean eliminarServicio(Servicio servicio) {
        boolean eliminado = false;
        
        if (servicio instanceof TelefonoFijo) {
            eliminado = empresa.eliminarTelefonoFIjo(((TelefonoFijo)servicio).getNumero());
        } else if (servicio instanceof TelefonoMovil) {
            eliminado = empresa.eliminarTelefonoMovil(((TelefonoMovil)servicio).getNumero());
        } else if (servicio instanceof CuentaNauta) {
            eliminado = empresa.eliminarCuentaNauta(((CuentaNauta)servicio).getNick());
        }
        
        return eliminado;
    }

    private void cargarDatos() {
        modelFijos.cargarDatos(empresa.getTelefonosFijos());
        modelMoviles.cargarDatos(empresa.getTelefonosMoviles());
        modelNauta.cargarDatos(empresa.getCuentasNautas());
    }

    private void mostrarFormulario() {
        // Cerrar panel de creación si está abierto
        if (panelCreacion.isVisible()) {
            cerrarPanelCreacion();
        }
        
        // Configurar fuente general para el formulario
        final Font fontLabels = new Font("Serif", Font.PLAIN, 19);
        Font fontTextFields = new Font("Serif", Font.PLAIN, 18);
        
        // Implementación existente del formulario para asignar servicios
        panelFormulario.removeAll();
        panelFormulario.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        int row = 0;

        final JScrollPane scrollPane = (JScrollPane) tabbedPane.getSelectedComponent();
        final JTable tablaActual = (JTable) scrollPane.getViewport().getView();

        // 1) Buscador de titular
        JLabel lblBuscar = new JLabel("Buscar Titular:");
        lblBuscar.setFont(fontLabels);
        
        final JTextField txtBuscar = new JTextField(15);
        txtBuscar.setFont(fontTextFields);
        
        final DefaultListModel<Cliente> modeloLista = new DefaultListModel<Cliente>();
        final JList<Cliente> listaClientes = new JList<Cliente>(modeloLista);
        listaClientes.setFont(fontTextFields);
        listaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        for (Cliente c : empresa.getClientes()) {
            modeloLista.addElement(c);
        }

        txtBuscar.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                filtrar();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                filtrar();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                filtrar();
            }

            private void filtrar() {
                String texto = txtBuscar.getText().toLowerCase();
                modeloLista.clear();
                for (Cliente c : empresa.getClientes()) {
                    if (c.getNombre().toLowerCase().contains(texto)) {
                        modeloLista.addElement(c);
                    }
                }
            }
        });

        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
        panelFormulario.add(lblBuscar, gbc);
        row++;
        gbc.gridy = row;
        panelFormulario.add(txtBuscar, gbc);
        row++;
        gbc.gridy = row;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1; gbc.weighty = 1;
        panelFormulario.add(new JScrollPane(listaClientes), gbc);
        row++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0; gbc.weighty = 0;
        gbc.gridwidth = 1;

        // Declarar los componentes que se usarán según la pestaña
        final JComboBox<String> comboFijos = new JComboBox<String>();
        comboFijos.setFont(fontTextFields);
        
        final JComboBox<TelefonoMovil> comboMoviles = new JComboBox<TelefonoMovil>();
        comboMoviles.setFont(fontTextFields);
        
        final JLabel lblMonto = new JLabel("Monto: -");
        lblMonto.setFont(fontLabels);
        
        final JTextField txtNick = new JTextField(15);
        txtNick.setFont(fontTextFields);

        // Determinar qué pestaña está seleccionada
        final int pestañaSeleccionada = tabbedPane.getSelectedIndex();

        // Campos específicos según la pestaña
        if (pestañaSeleccionada == 0) { // Teléfonos Fijos
            JLabel lblFijo = new JLabel("Seleccionar Teléfono Fijo:");
            lblFijo.setFont(fontLabels);
            
            for (Servicio s : empresa.getServiciosDisponibles()) {
                if (s instanceof TelefonoFijo) {
                    comboFijos.addItem(((TelefonoFijo) s).getNumero());
                }
            }

            gbc.gridx = 0; gbc.gridy = row; panelFormulario.add(lblFijo, gbc);
            row++;
            gbc.gridy = row; gbc.gridwidth = 2;
            panelFormulario.add(comboFijos, gbc);
            row++;
            gbc.gridwidth = 1;
        } 
        else if (pestañaSeleccionada == 1) { // Teléfonos Móviles
            JLabel lblMovil = new JLabel("Seleccionar Teléfono Móvil:");
            lblMovil.setFont(fontLabels);
            
            for (Servicio s : empresa.getServiciosDisponibles()) {
                if (s instanceof TelefonoMovil) {
                    TelefonoMovil tm = (TelefonoMovil) s;
                    if (tm.getTitular() == null) {
                        comboMoviles.addItem(tm);
                    }
                }
            }

            comboMoviles.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TelefonoMovil seleccionado = (TelefonoMovil) comboMoviles.getSelectedItem();
                    lblMonto.setText(seleccionado != null ? 
                        "Monto: $" + seleccionado.getMontoApagar() : "Monto: -");
                }
            });

            if (comboMoviles.getItemCount() > 0) {
                lblMonto.setText("Monto: $" + 
                    ((TelefonoMovil)comboMoviles.getItemAt(0)).getMontoApagar());
            }

            gbc.gridx = 0; gbc.gridy = row; panelFormulario.add(lblMovil, gbc);
            gbc.gridx = 1; panelFormulario.add(lblMonto, gbc);
            row++;
            gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
            panelFormulario.add(comboMoviles, gbc);
            row++;
            gbc.gridwidth = 1;
        }
        else if (pestañaSeleccionada == 2) { // Cuentas Nauta
            JLabel lblNick = new JLabel("Nick de la cuenta:");
            lblNick.setFont(fontLabels);
            
            gbc.gridx = 0; gbc.gridy = row; panelFormulario.add(lblNick, gbc);
            row++;
            gbc.gridy = row; gbc.gridwidth = 2;
            panelFormulario.add(txtNick, gbc);
            row++;
            gbc.gridwidth = 1;
        }

     // Botones Guardar y Cancelar
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        // Configurar fuente y colores
        Font fontButtons1 = new Font("Serif", Font.PLAIN, 20); 
        btnGuardar.setFont(fontButtons1);
        btnCancelar.setFont(fontButtons1);

        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBackground(new Color(0, 0, 153));
        btnCancelar.setForeground(new Color(0, 0, 153));
        btnCancelar.setBackground(Color.WHITE);

        // Establecer tamaño fijo más pequeño
        Dimension buttonSize = new Dimension(110, 30);
        btnGuardar.setPreferredSize(buttonSize);
        btnGuardar.setMinimumSize(buttonSize);
        btnGuardar.setMaximumSize(buttonSize);
        btnCancelar.setPreferredSize(buttonSize);
        btnCancelar.setMinimumSize(buttonSize);
        btnCancelar.setMaximumSize(buttonSize);

        // Crear un panel contenedor para los botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(btnGuardar);
        buttonPanel.add(btnCancelar);

        // Configurar GridBagConstraints para el panel de botones
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.anchor = GridBagConstraints.CENTER;
        panelFormulario.add(buttonPanel, gbc);

        // BLOQUEAR TABLA Y PESTAÑAS
        tablaActual.setEnabled(false);
        tablaActual.setFocusable(false);
        tabbedPane.setEnabled(false);
        tabbedPane.setFocusable(false);

        // Acción Guardar
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente titular = listaClientes.getSelectedValue();
               
                if (titular == null) {
                    UIManager.put("OptionPane.messageFont", fontLabels);
                    JOptionPane.showMessageDialog(ListadoServicios.this,
                        "Debe seleccionar un titular.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                try {
                    if (pestañaSeleccionada == 0) { // Teléfono Fijo
                        if (comboFijos.getSelectedIndex() == -1) {
                            throw new IllegalArgumentException("Debe seleccionar un teléfono fijo disponible");
                        }
                        empresa.asignarTelefonoFijo(titular);
                    } 
                    else if (pestañaSeleccionada == 1) { // Teléfono Móvil
                        if (comboMoviles.getSelectedIndex() == -1) {
                            throw new IllegalArgumentException("Debe seleccionar un teléfono móvil disponible");
                        }
                        empresa.asignarTelefonoMovil(titular);
                    } 
                    else if (pestañaSeleccionada == 2) { // Cuenta Nauta
                        String nick = txtNick.getText();
                        if (nick.isEmpty()) {
                            throw new IllegalArgumentException("Debe ingresar un nick para la cuenta");
                        }
                        empresa.crearCuentaNauta(titular, nick);
                    }
                    
                    cargarDatos();
                    panelFormulario.setVisible(false);
                    tablaActual.setEnabled(true);
                    tablaActual.setFocusable(true);
                    tabbedPane.setEnabled(true);
                    tabbedPane.setFocusable(true);
                    setSize(1015, 800);
                    
                } catch (Exception ex) {
                    UIManager.put("OptionPane.messageFont", fontLabels);
                    JOptionPane.showMessageDialog(ListadoServicios.this,
                        "Error al asignar servicio: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción Cancelar
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelFormulario.setVisible(false);
                tablaActual.setEnabled(true);
                tablaActual.setFocusable(true);
                tabbedPane.setEnabled(true);
                tabbedPane.setFocusable(true);
                setSize(1015, 800);
            }
        });

        // FINAL: Mostrar
        panelFormulario.setVisible(true);
        panelFormulario.revalidate();
        panelFormulario.repaint();
    }
    
    @Override
    public void dispose() {
        // Restaurar imagen al cerrar
        if (ventanaPrincipal != null) {
            ventanaPrincipal.cambiarImagenFondo("/imagenes/d.png");
        }
        instance = null;
        super.dispose();
    }

    public static void abrirListadoServicio(Principal principal) {
        if (instance == null) {
            instance = new ListadoServicios(principal);
            instance.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            instance.setVisible(true);
        } else {
            // Actualizar referencia si ya existe
            instance.ventanaPrincipal = principal;
            if (!instance.isVisible()) instance.setVisible(true);
            else instance.toFront();
        }
    }
}