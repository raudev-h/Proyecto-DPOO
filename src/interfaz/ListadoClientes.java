package interfaz;
import auxiliares.*;
import logica.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class ListadoClientes extends JDialog {
    // Componentes de la interfaz
    private JTable table;
    private ClienteTableModel tableModel;
    private static ListadoClientes instance;
    
    // Campos de edición
    private JPanel panelEdicion;
    private JTextField txtNombreEdit;
    private JTextField txtDireccionEdit;
    private JComboBox<String> cbMunicipioEdit;
    private JComboBox<String> cbProvinciaEdit;
    private JTextField txtNumIdEdit;
    private JPanel panelPersonaNaturalEdit;
    private JTextField txtOrganismoEdit;
    private JPanel panelPersonaJuridicaEdit;
    private JPanel panelEntidadNoEstatalEdit;
    private JButton btnAceptarEdit;
    private JButton btnCancelarEdit;
    
    // Campos de creación
    private JPanel panelCreacion;
    private JTextField txtNombreCreate;
    private JTextField txtDireccionCreate;
    private JComboBox<String> cbMunicipioCreate;
    private JComboBox<String> cbProvinciaCreate;
    private JTextField txtNumIdCreate;
    private JPanel panelPersonaNaturalCreate;
    private JTextField txtOrganismoCreate;
    private JPanel panelPersonaJuridicaCreate;
    private JPanel panelEntidadNoEstatalCreate;
    private JButton btnAceptarCreate;
    private JButton btnCancelarCreate;
    private JButton btnCrearCliente;
    private JComboBox<String> cbTipoCliente;
    
    private Cliente clienteSeleccionado;
    private String nombreClienteSeleccionado;
    private boolean modoEdicion;
    
    // Etiquetas para validación
    private JLabel lblNombreEdit, lblDireccionEdit, lblMunicipioEdit, lblProvinciaEdit, lblNumIdEdit, lblOrganismoEdit;
    private JLabel lblNombreCreate, lblDireccionCreate, lblMunicipioCreate, lblProvinciaCreate, lblNumIdCreate, lblOrganismoCreate;
    private JLabel lblTipoCliente;
    private JLabel lblTituloEdicion;


    // Mapa de provincias y municipios de Cuba
    private static final Map<String, String[]> PROVINCIAS_MUNICIPIOS = new LinkedHashMap<String, String[]>() {{
        put("Pinar del Río", new String[]{"Consolación del Sur", "Guane", "La Palma", "Los Palacios", 
            "Mantua", "Minas de Matahambre", "Pinar del Río", "San Juan y Martínez", 
            "San Luis", "Sandino", "Viñales"});
        put("Artemisa", new String[]{"Alquízar", "Artemisa", "Bahía Honda", "Bauta", 
            "Caimito", "Candelaria", "Guanajay", "Güira de Melena", "Mariel", 
            "San Antonio de los Baños", "San Cristóbal"});
        put("La Habana", new String[]{"Arroyo Naranjo", "Boyeros", "Centro Habana", "Cerro", 
            "Cotorro", "Diez de Octubre", "Guanabacoa", "La Habana del Este", 
            "La Habana Vieja", "La Lisa", "Marianao", "Playa", "Plaza de la Revolución", 
            "Regla", "San Miguel del Padrón"});
        put("Mayabeque", new String[]{"Batabanó", "Bejucal", "Güines", "Jaruco", 
            "Madruga", "Melena del Sur", "Nueva Paz", "Quivicán", 
            "San José de las Lajas", "San Nicolás", "Santa Cruz del Norte"});
        put("Matanzas", new String[]{"Calimete", "Cárdenas", "Ciudad de Matanzas", "Colón", 
            "Jagüey Grande", "Jovellanos", "Limonar", "Los Arabos", 
            "Martí", "Pedro Betancourt", "Perico", "Unión de Reyes", "Varadero"});
        put("Cienfuegos", new String[]{"Abreus", "Aguada de Pasajeros", "Cienfuegos", "Cruces", 
            "Cumanayagua", "Lajas", "Palmira", "Rodas"});
        put("Villa Clara", new String[]{"Caibarién", "Camajuaní", "Cifuentes", "Corralillo", 
            "Encrucijada", "Manicaragua", "Placetas", "Quemado de Güines", 
            "Ranchuelo", "Remedios", "Sagua la Grande", "Santa Clara", "Santo Domingo"});
        put("Sancti Spíritus", new String[]{"Cabaiguán", "Fomento", "Jatibonico", "La Sierpe", 
            "Sancti Spíritus", "Taguasco", "Trinidad", "Yaguajay"});
        put("Ciego de Ávila", new String[]{"Baraguá", "Bolivia", "Chambas", "Ciego de Ávila", 
            "Ciro Redondo", "Florencia", "Majagua", "Morón", "Primero de Enero", "Venezuela"});
        put("Camagüey", new String[]{"Camagüey", "Carlos M. de Céspedes", "Esmeralda", "Florida", 
            "Guáimaro", "Jimaguayú", "Minas", "Najasa", "Nuevitas", "Santa Cruz del Sur", 
            "Sibanicú", "Sierra de Cubitas", "Vertientes"});
        put("Las Tunas", new String[]{"Amancio", "Colombia", "Jesús Menéndez", "Jobabo", 
            "Las Tunas", "Majibacoa", "Manatí", "Puerto Padre"});
        put("Holguín", new String[]{"Antilla", "Báguanos", "Banes", "Cacocum", 
            "Calixto García", "Cueto", "Frank País", "Gibara", 
            "Holguín", "Mayarí", "Moa", "Rafael Freyre", "Sagua de Tánamo", "Urbano Noris"});
        put("Granma", new String[]{"Bartolomé Masó", "Bayamo", "Buey Arriba", "Campechuela", 
            "Cauto Cristo", "Guisa", "Jiguaní", "Manzanillo", 
            "Media Luna", "Niquero", "Pilón", "Río Cauto", "Yara"});
        put("Santiago de Cuba", new String[]{"Contramaestre", "Guamá", "Mella", "Palma Soriano", 
            "San Luis", "Santiago de Cuba", "Segundo Frente", "Songo-La Maya", "Tercer Frente"});
        put("Guantánamo", new String[]{"Baracoa", "Caimanera", "El Salvador", "Guantánamo", 
            "Imías", "Maisí", "Manuel Tames", "Niceto Pérez", "San Antonio del Sur", "Yateras"});
        put("Isla de la Juventud", new String[]{"Isla de la Juventud"});
    }};

    // Constructor privado para Singleton
    private ListadoClientes() {
        setBounds(100, 100, 1087, 684);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        setTitle("Listado de Clientes");
        setModal(true); // Hacer la ventana modal
        
        initComponents();
        configurarMenuContextual();
    }

    // Método Singleton para obtener la instancia
    public static synchronized ListadoClientes getInstance() {
        if (instance == null) {
            instance = new ListadoClientes();
        }
        return instance;
    }

    // Método estático para abrir la ventana
    public static void abrirListadoClientes() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Verificar si ya existe una instancia visible
                if (instance != null && instance.isVisible()) {
                    JOptionPane.showMessageDialog(null, 
                        "El listado de clientes ya está abierto", 
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
                    instance.toFront(); // Traer al frente la ventana existente
                    return;
                }
                
                ListadoClientes dialog = ListadoClientes.getInstance();
                dialog.mostrarVentana();
                
                if (dialog.isVisible()) {
                    UIManager.put("OptionPane.messageFont", new Font("Serif", Font.BOLD, 20));
                    UIManager.put("OptionPane.buttonFont", new Font("Serif", Font.BOLD, 18));
                    UIManager.put("OptionPane.background", new Color(240, 240, 240));
                    UIManager.put("Panel.background", new Color(240, 240, 240));
                    UIManager.put("OptionPane.title", new Font("Serif",Font.PLAIN,20));
                    
                    dialog.toFront();
                }
            }
        });
    }

    @Override
    public void dispose() {
        instance = null;
        super.dispose();
    }


    

    private void initComponents() {
        tableModel = new ClienteTableModel();
        tableModel.cargarClientes();
        
        JPanel panel = new JPanel();
        panel.setBounds(15, 16, 1044, 600);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
        scrollPane.setBounds(15, 36, 1014, 552);
        panel.add(scrollPane);
        
        table = new JTable(tableModel);
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFont(new Font("Serif", Font.PLAIN, 18));
        scrollPane.setViewportView(table);
        
        JTableHeader header = table.getTableHeader();
        Font headerFont = new Font("Serif",Font.PLAIN, 20);
        header.setFont(headerFont);
        
        table.setFont(new Font("Serif", Font.PLAIN, 20));
        table.setRowHeight(25);
        
        JLabel lblListadoDeClientes = new JLabel("Listado de Clientes");
        lblListadoDeClientes.setFont(new Font("Serif", Font.BOLD, 21));
        lblListadoDeClientes.setBounds(15, 0, 195, 20);
        panel.add(lblListadoDeClientes);
        
        // Botón Crear Cliente
        btnCrearCliente = new JButton("Crear Cliente");
        btnCrearCliente.setForeground(new Color(0, 0, 153));
        btnCrearCliente.setBackground(Color.WHITE);
        btnCrearCliente.setFont(new Font("Serif", Font.PLAIN, 18));
        btnCrearCliente.setBounds(850, 0, 180, 30);
        btnCrearCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirPanelCreacion();
            }
        });
        panel.add(btnCrearCliente);
        
        initPanelEdicion();
        initPanelCreacion();
    }

    private void abrirPanelCreacion() {
        modoEdicion = false;
        resetearCamposCreacion();
        panelEdicion.setVisible(false);
        panelCreacion.setVisible(true);
        setSize(1478, 683);
    }
    
    private void resetearCamposCreacion() {
        txtNombreCreate.setText("");
        txtDireccionCreate.setText("");
        txtNumIdCreate.setText("");
        txtOrganismoCreate.setText("");
        cbProvinciaCreate.setSelectedItem("La Habana");
        cbTipoCliente.setSelectedItem("Persona Natural");
        mostrarCamposSegunTipoCreacion("Persona Natural");
    }

    // ============ PANEL DE CREACIÓN ============
    private void initPanelCreacion() {
        panelCreacion = new JPanel();
        panelCreacion.setBorder(new LineBorder(new Color(0, 0, 0)));
        panelCreacion.setBounds(1074, 55, 350, 550);
        panelCreacion.setVisible(false);
        panelCreacion.setLayout(null);
        getContentPane().add(panelCreacion);
        
        // Componentes comunes
        JLabel lblCreacionDeCliente = new JLabel("Creación de Cliente");
        lblCreacionDeCliente.setFont(new Font("Serif", Font.BOLD, 21));
        lblCreacionDeCliente.setBounds(32, 12, 280, 28);
        panelCreacion.add(lblCreacionDeCliente);
        
        // Combo box para selección de tipo de cliente
        lblTipoCliente = new JLabel("Tipo de Cliente");
        lblTipoCliente.setFont(new Font("Serif", Font.PLAIN, 19));
        lblTipoCliente.setBounds(35, 50, 280, 20);
        panelCreacion.add(lblTipoCliente);
        
        cbTipoCliente = new JComboBox<String>();
        cbTipoCliente.setFont(new Font("Serif", Font.PLAIN, 18));
        cbTipoCliente.setBounds(35, 80, 280, 30);
        cbTipoCliente.addItem("Persona Natural");
        cbTipoCliente.addItem("Persona Jurídica");
        cbTipoCliente.addItem("Entidad No Estatal");
        cbTipoCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipoSeleccionado = (String) cbTipoCliente.getSelectedItem();
                mostrarCamposSegunTipoCreacion(tipoSeleccionado);
            }
        });
        panelCreacion.add(cbTipoCliente);
        
        // Campos nombre y dirección
        lblNombreCreate = new JLabel("Nombre");
        lblNombreCreate.setFont(new Font("Serif", Font.PLAIN, 19));
        lblNombreCreate.setBounds(35, 120, 280, 20);
        panelCreacion.add(lblNombreCreate);
        
        txtNombreCreate = new JTextField();
        txtNombreCreate.setBounds(35, 150, 280, 26);
        panelCreacion.add(txtNombreCreate);
        txtNombreCreate.setColumns(10);
        
        lblDireccionCreate = new JLabel("Dirección");
        lblDireccionCreate.setFont(new Font("Serif", Font.PLAIN, 19));
        lblDireccionCreate.setBounds(35, 190, 280, 20);
        panelCreacion.add(lblDireccionCreate);
        
        txtDireccionCreate = new JTextField();
        txtDireccionCreate.setBounds(35, 220, 280, 26);
        panelCreacion.add(txtDireccionCreate);
        txtDireccionCreate.setColumns(10);
        
        // Campos para ubicación
        lblMunicipioCreate = new JLabel("Municipio");
        lblMunicipioCreate.setFont(new Font("Serif", Font.PLAIN, 19));
        lblMunicipioCreate.setBounds(35, 260, 280, 20);
        panelCreacion.add(lblMunicipioCreate);
        
        cbMunicipioCreate = new JComboBox<String>();
        cbMunicipioCreate.setFont(new Font("Serif", Font.PLAIN, 18));
        cbMunicipioCreate.setBounds(35, 290, 280, 30);
        panelCreacion.add(cbMunicipioCreate);
        
        lblProvinciaCreate = new JLabel("Provincia");
        lblProvinciaCreate.setFont(new Font("Serif", Font.PLAIN, 19));
        lblProvinciaCreate.setBounds(35, 330, 280, 20);
        panelCreacion.add(lblProvinciaCreate);
        
        cbProvinciaCreate = new JComboBox<String>();
        cbProvinciaCreate.setFont(new Font("Serif", Font.PLAIN, 18));
        cbProvinciaCreate.setBounds(35, 360, 280, 30);
        for (String provincia : PROVINCIAS_MUNICIPIOS.keySet()) {
            cbProvinciaCreate.addItem(provincia);
        }
        cbProvinciaCreate.setSelectedItem("La Habana");
        cargarMunicipiosCreacion((String) cbProvinciaCreate.getSelectedItem());
        
        cbProvinciaCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String provinciaSeleccionada = (String) cbProvinciaCreate.getSelectedItem();
                cargarMunicipiosCreacion(provinciaSeleccionada);
            }
        });
        panelCreacion.add(cbProvinciaCreate);
        
        // Panel para PersonaNatural
        panelPersonaNaturalCreate = new JPanel();
        panelPersonaNaturalCreate.setBounds(0, 400, 350, 80);
        panelPersonaNaturalCreate.setLayout(null);
        panelPersonaNaturalCreate.setVisible(false);
        panelCreacion.add(panelPersonaNaturalCreate);
        
        lblNumIdCreate = new JLabel("Número de Identificación");
        lblNumIdCreate.setFont(new Font("Serif", Font.PLAIN, 19));
        lblNumIdCreate.setBounds(35, 0, 280, 20);
        panelPersonaNaturalCreate.add(lblNumIdCreate);
        
        txtNumIdCreate = new JTextField();
        txtNumIdCreate.setBounds(35, 30, 280, 26);
        panelPersonaNaturalCreate.add(txtNumIdCreate);
        txtNumIdCreate.setColumns(10);
        
        // Panel para PersonaJuridica
        panelPersonaJuridicaCreate = new JPanel();
        panelPersonaJuridicaCreate.setBounds(0, 400, 350, 80);
        panelPersonaJuridicaCreate.setLayout(null);
        panelPersonaJuridicaCreate.setVisible(false);
        panelCreacion.add(panelPersonaJuridicaCreate);
        
        lblOrganismoCreate = new JLabel("Organismo");
        lblOrganismoCreate.setFont(new Font("Serif", Font.PLAIN, 19));
        lblOrganismoCreate.setBounds(35, 0, 280, 20);
        panelPersonaJuridicaCreate.add(lblOrganismoCreate);
        
        txtOrganismoCreate = new JTextField();
        txtOrganismoCreate.setBounds(35, 30, 280, 26);
        panelPersonaJuridicaCreate.add(txtOrganismoCreate);
        txtOrganismoCreate.setColumns(10);
        
        // Panel para EntidadNoEstatal
        panelEntidadNoEstatalCreate = new JPanel();
        panelEntidadNoEstatalCreate.setBounds(0, 400, 350, 50);
        panelEntidadNoEstatalCreate.setLayout(null);
        panelEntidadNoEstatalCreate.setVisible(false);
        panelCreacion.add(panelEntidadNoEstatalCreate);
        
        // Botones
        btnAceptarCreate = new JButton("Aceptar");
        btnAceptarCreate.setForeground(new Color(255, 255, 255));
        btnAceptarCreate.setBackground(new Color(0, 0, 153));
        btnAceptarCreate.setFont(new Font("Serif", Font.PLAIN, 19));
        btnAceptarCreate.setBounds(35, 500, 120, 30);
        btnAceptarCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              crearCliente();
            }
        });
        panelCreacion.add(btnAceptarCreate);
        
        btnCancelarCreate = new JButton("Cancelar");
        btnCancelarCreate.setBackground(new Color(255, 255, 255));
        btnCancelarCreate.setForeground(new Color(0, 0, 153));
        btnCancelarCreate.setFont(new Font("Serif", Font.PLAIN, 19));
        btnCancelarCreate.setBounds(195, 500, 120, 30);
        btnCancelarCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cerrarPanelEdicion();
            }
        });
        panelCreacion.add(btnCancelarCreate);
    }

    private void cargarMunicipiosCreacion(String provincia) {
        cbMunicipioCreate.removeAllItems();
        String[] municipios = PROVINCIAS_MUNICIPIOS.get(provincia);
        if (municipios != null) {
            for (String municipio : municipios) {
                cbMunicipioCreate.addItem(municipio);
            }
        }
    }

    private void mostrarCamposSegunTipoCreacion(String tipoCliente) {
        resetearValidacionesCreacion();
        
        panelPersonaNaturalCreate.setVisible(false);
        panelPersonaJuridicaCreate.setVisible(false);
        panelEntidadNoEstatalCreate.setVisible(false);
        
        if (tipoCliente.equals("Persona Natural")) {
            panelPersonaNaturalCreate.setVisible(true);
            cbMunicipioCreate.setVisible(true);
            cbProvinciaCreate.setVisible(true);
            lblMunicipioCreate.setVisible(true);
            lblProvinciaCreate.setVisible(true);
        } 
        else if (tipoCliente.equals("Persona Jurídica")) {
            panelPersonaJuridicaCreate.setVisible(true);
            cbMunicipioCreate.setVisible(true);
            cbProvinciaCreate.setVisible(true);
            lblMunicipioCreate.setVisible(true);
            lblProvinciaCreate.setVisible(true);
        } 
        else if (tipoCliente.equals("Entidad No Estatal")) {
            panelEntidadNoEstatalCreate.setVisible(true);
            cbMunicipioCreate.setVisible(false);
            cbProvinciaCreate.setVisible(false);
            lblMunicipioCreate.setVisible(false);
            lblProvinciaCreate.setVisible(false);
        }
    }

    private void resetearValidacionesCreacion() {
        lblNombreCreate.setForeground(Color.BLACK);
        lblDireccionCreate.setForeground(Color.BLACK);
        lblNumIdCreate.setForeground(Color.BLACK);
        lblOrganismoCreate.setForeground(Color.BLACK);
    }
    private void crearCliente() {
        boolean agregado = false;
        
        try {
            // Validar campos comunes
            boolean nombreValido = validarNombreCreacion(txtNombreCreate.getText());
            boolean direccionValida = validarDireccionCreacion(txtDireccionCreate.getText());
            boolean valido = nombreValido && direccionValida;
            
            String tipoCliente = (String) cbTipoCliente.getSelectedItem();
            Cliente nuevoCliente = null;
            
            // Validar campos específicos según el tipo de cliente
            if (tipoCliente.equals("Persona Natural")) {
                boolean numIdValido = validarNumIdCreacion(txtNumIdCreate.getText());
                valido = valido && numIdValido;
                
                if (valido) {
                    nuevoCliente = new PersonaNatural(
                        txtNombreCreate.getText(),
                        txtDireccionCreate.getText(),
                        (String) cbMunicipioCreate.getSelectedItem(),
                        (String) cbProvinciaCreate.getSelectedItem(),
                        txtNumIdCreate.getText()
                    );
                    EmpresaTelecomunicaciones.getInstancia().agregarPersonaNatural(
                        txtDireccionCreate.getText(),
                        (String) cbMunicipioCreate.getSelectedItem(),
                        (String) cbProvinciaCreate.getSelectedItem(),
                        txtNombreCreate.getText(), 
                        txtNumIdCreate.getText()
                    );
                    agregado = true;
                }
            } 
            else if (tipoCliente.equals("Persona Jurídica")) {
                boolean organismoValido = validarOrganismoCreacion(txtOrganismoCreate.getText());
                valido = valido && organismoValido;
                
                if (valido) {
                    nuevoCliente = new PersonaJuridica(
                        txtNombreCreate.getText(), 
                        txtDireccionCreate.getText(),
                        (String) cbMunicipioCreate.getSelectedItem(),
                        (String) cbProvinciaCreate.getSelectedItem(),
                        txtOrganismoCreate.getText(),
                        null
                    );
                    EmpresaTelecomunicaciones.getInstancia().agregarPersonaJuridica(
                        txtDireccionCreate.getText(), 
                        (String) cbMunicipioCreate.getSelectedItem(), 
                        (String) cbProvinciaCreate.getSelectedItem(), 
                        txtNombreCreate.getText(), 
                        txtOrganismoCreate.getText(), 
                        null
                    );
                    agregado = true;
                }
            } 
            else if (tipoCliente.equals("Entidad No Estatal")) {
                if (valido) {
                    nuevoCliente = new EntidadNoEstatal(
                        txtNombreCreate.getText(),
                        txtDireccionCreate.getText(),
                        null
                    );
                    EmpresaTelecomunicaciones.getInstancia().agregarEntidadNoEstatal(
                        txtDireccionCreate.getText(), 
                        txtNombreCreate.getText(), 
                        null
                    );
                    agregado = true;
                }
            }
            
            if (!valido) {
                // Configurar fuente para los mensajes de error
                UIManager.put("OptionPane.messageFont", new Font("Serif", Font.PLAIN, 18));
                UIManager.put("OptionPane.buttonFont", new Font("Serif", Font.PLAIN, 16));
                
                throw new Exception("Complete todos los campos obligatorios marcados en rojo");
            }
            
            // Verificar si el cliente ya existe
            if (EmpresaTelecomunicaciones.getInstancia().buscarCliente(nuevoCliente.getNombre()) != null) {
                throw new Exception("Ya existe un cliente con ese nombre");
            }
            
            if (agregado) {
                tableModel.cargarClientes();
                cerrarPanelEdicion();
                
                // Configurar fuente para el mensaje de éxito
                UIManager.put("OptionPane.messageFont", new Font("Serif", Font.PLAIN, 18));
                UIManager.put("OptionPane.buttonFont", new Font("Serif", Font.PLAIN, 16));
                
                JOptionPane.showMessageDialog(this, "Cliente creado exitosamente", 
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                throw new Exception("No se pudo crear el cliente");
            }
        } catch (Exception e) {
            // Configurar fuente para los mensajes de error
            UIManager.put("OptionPane.messageFont", new Font("Serif", Font.PLAIN, 18));
            UIManager.put("OptionPane.buttonFont", new Font("Serif", Font.PLAIN, 16));
            
            JOptionPane.showMessageDialog(this, e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

 // ============ PANEL DE EDICIÓN ============
    private void initPanelEdicion() {
    	panelEdicion = new JPanel();
        panelEdicion.setBorder(new LineBorder(new Color(0, 0, 0)));
        panelEdicion.setBounds(1074, 55, 350, 550);
        panelEdicion.setVisible(false);
        panelEdicion.setLayout(null);
        getContentPane().add(panelEdicion);
        
        lblTituloEdicion = new JLabel("Edición de Cliente");
        lblTituloEdicion.setFont(new Font("Serif", Font.BOLD, 21));
        lblTituloEdicion.setBounds(32, 12, 280, 28);
        panelEdicion.add(lblTituloEdicion);
        
        // Campos nombre y dirección
        lblNombreEdit = new JLabel("Nombre");
        lblNombreEdit.setFont(new Font("Serif", Font.PLAIN, 19));
        lblNombreEdit.setBounds(35, 50, 280, 20);
        panelEdicion.add(lblNombreEdit);
        
        txtNombreEdit = new JTextField();
        txtNombreEdit.setBounds(35, 80, 280, 26);
        panelEdicion.add(txtNombreEdit);
        txtNombreEdit.setColumns(10);
        
        lblDireccionEdit = new JLabel("Dirección");
        lblDireccionEdit.setFont(new Font("Serif", Font.PLAIN, 19));
        lblDireccionEdit.setBounds(35, 120, 280, 20);
        panelEdicion.add(lblDireccionEdit);
        
        txtDireccionEdit = new JTextField();
        txtDireccionEdit.setBounds(35, 150, 280, 26);
        panelEdicion.add(txtDireccionEdit);
        txtDireccionEdit.setColumns(10);
        
        // Campos para ubicación
        lblMunicipioEdit = new JLabel("Municipio");
        lblMunicipioEdit.setFont(new Font("Serif", Font.PLAIN, 19));
        lblMunicipioEdit.setBounds(35, 190, 280, 20);
        panelEdicion.add(lblMunicipioEdit);
        
        cbMunicipioEdit = new JComboBox<String>();
        cbMunicipioEdit.setFont(new Font("Serif", Font.PLAIN, 18));
        cbMunicipioEdit.setBounds(35, 220, 280, 30);
        panelEdicion.add(cbMunicipioEdit);
        
        lblProvinciaEdit = new JLabel("Provincia");
        lblProvinciaEdit.setFont(new Font("Serif", Font.PLAIN, 19));
        lblProvinciaEdit.setBounds(35, 260, 280, 20);
        panelEdicion.add(lblProvinciaEdit);
        
        cbProvinciaEdit = new JComboBox<String>();
        cbProvinciaEdit.setFont(new Font("Serif", Font.PLAIN, 18));
        cbProvinciaEdit.setBounds(35, 290, 280, 30);
        for (String provincia : PROVINCIAS_MUNICIPIOS.keySet()) {
            cbProvinciaEdit.addItem(provincia);
        }
        cbProvinciaEdit.setSelectedItem("La Habana");
        cargarMunicipiosEdicion((String) cbProvinciaEdit.getSelectedItem());
        
        cbProvinciaEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String provinciaSeleccionada = (String) cbProvinciaEdit.getSelectedItem();
                cargarMunicipiosEdicion(provinciaSeleccionada);
            }
        });
        panelEdicion.add(cbProvinciaEdit);
        
        // Panel para PersonaNatural
        panelPersonaNaturalEdit = new JPanel();
        panelPersonaNaturalEdit.setBounds(0, 330, 350, 80);
        panelPersonaNaturalEdit.setLayout(null);
        panelPersonaNaturalEdit.setVisible(false);
        panelEdicion.add(panelPersonaNaturalEdit);
        
        lblNumIdEdit = new JLabel("Número de Identificación");
        lblNumIdEdit.setFont(new Font("Serif", Font.PLAIN, 19));
        lblNumIdEdit.setBounds(35, 0, 280, 20);
        panelPersonaNaturalEdit.add(lblNumIdEdit);
        
        txtNumIdEdit = new JTextField();
        txtNumIdEdit.setBounds(35, 30, 280, 26);
        panelPersonaNaturalEdit.add(txtNumIdEdit);
        txtNumIdEdit.setColumns(10);
        
        // Panel para PersonaJuridica
        panelPersonaJuridicaEdit = new JPanel();
        panelPersonaJuridicaEdit.setBounds(0, 330, 350, 80);
        panelPersonaJuridicaEdit.setLayout(null);
        panelPersonaJuridicaEdit.setVisible(false);
        panelEdicion.add(panelPersonaJuridicaEdit);
        
        lblOrganismoEdit = new JLabel("Organismo");
        lblOrganismoEdit.setFont(new Font("Serif", Font.PLAIN, 19));
        lblOrganismoEdit.setBounds(35, 0, 280, 20);
        panelPersonaJuridicaEdit.add(lblOrganismoEdit);
        
        txtOrganismoEdit = new JTextField();
        txtOrganismoEdit.setBounds(35, 30, 280, 26);
        panelPersonaJuridicaEdit.add(txtOrganismoEdit);
        txtOrganismoEdit.setColumns(10);
        
        // Panel para EntidadNoEstatal
        panelEntidadNoEstatalEdit = new JPanel();
        panelEntidadNoEstatalEdit.setBounds(0, 330, 350, 50);
        panelEntidadNoEstatalEdit.setLayout(null);
        panelEntidadNoEstatalEdit.setVisible(false);
        panelEdicion.add(panelEntidadNoEstatalEdit);
        
        // Botones
        btnAceptarEdit = new JButton("Aceptar");
        btnAceptarEdit.setForeground(new Color(255, 255, 255));
        btnAceptarEdit.setBackground(new Color(0, 0, 153));
        btnAceptarEdit.setFont(new Font("Serif", Font.PLAIN, 19));
        btnAceptarEdit.setBounds(35, 430, 120, 30);
        btnAceptarEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarCliente();
            }
        });
        panelEdicion.add(btnAceptarEdit);
        
        btnCancelarEdit = new JButton("Cancelar");
        btnCancelarEdit.setBackground(new Color(255, 255, 255));
        btnCancelarEdit.setForeground(new Color(0, 0, 153));
        btnCancelarEdit.setFont(new Font("Serif", Font.PLAIN, 19));
        btnCancelarEdit.setBounds(195, 430, 120, 30);
        btnCancelarEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cerrarPanelEdicion();
            }
        });
        panelEdicion.add(btnCancelarEdit);
    }

    private void cargarMunicipiosEdicion(String provincia) {
        cbMunicipioEdit.removeAllItems();
        String[] municipios = PROVINCIAS_MUNICIPIOS.get(provincia);
        if (municipios != null) {
            for (String municipio : municipios) {
                cbMunicipioEdit.addItem(municipio);
            }
        }
    }

    private void mostrarCamposSegunTipoEdicion(Cliente cliente) {
        resetearValidacionesEdicion();
        
        panelPersonaNaturalEdit.setVisible(false);
        panelPersonaJuridicaEdit.setVisible(false);
        panelEntidadNoEstatalEdit.setVisible(false);
        
        txtNombreEdit.setText(cliente.getNombre());
        txtDireccionEdit.setText(cliente.getDireccion());
        
        if (cliente instanceof PersonaNatural) {
            PersonaNatural pn = (PersonaNatural) cliente;
            cbProvinciaEdit.setSelectedItem(pn.getProvincia());
            cbMunicipioEdit.setSelectedItem(pn.getMunicipio());
            txtNumIdEdit.setText(pn.getNumId());
            panelPersonaNaturalEdit.setVisible(true);
            
            cbMunicipioEdit.setVisible(true);
            cbProvinciaEdit.setVisible(true);
            lblMunicipioEdit.setVisible(true);
            lblProvinciaEdit.setVisible(true);
        } 
        else if (cliente instanceof PersonaJuridica) {
            PersonaJuridica pj = (PersonaJuridica) cliente;
            cbProvinciaEdit.setSelectedItem(pj.getProvincia());
            cbMunicipioEdit.setSelectedItem(pj.getMunicipio());
            txtOrganismoEdit.setText(pj.getOrganismo());
            panelPersonaJuridicaEdit.setVisible(true);
            
            cbMunicipioEdit.setVisible(true);
            cbProvinciaEdit.setVisible(true);
            lblMunicipioEdit.setVisible(true);
            lblProvinciaEdit.setVisible(true);
        } 
        else if (cliente instanceof EntidadNoEstatal) {
            panelEntidadNoEstatalEdit.setVisible(true);
            
            cbMunicipioEdit.setVisible(false);
            cbProvinciaEdit.setVisible(false);
            lblMunicipioEdit.setVisible(false);
            lblProvinciaEdit.setVisible(false);
        }
    }

    private void resetearValidacionesEdicion() {
        lblNombreEdit.setForeground(Color.BLACK);
        lblDireccionEdit.setForeground(Color.BLACK);
        lblNumIdEdit.setForeground(Color.BLACK);
        lblOrganismoEdit.setForeground(Color.BLACK);
    }

    private void actualizarCliente() {
        if (clienteSeleccionado == null) return;
        
        try {
            Cliente clienteActual = EmpresaTelecomunicaciones.getInstancia()
                              .buscarCliente(nombreClienteSeleccionado);
            if (clienteActual == null) {
                // Configurar fuente para los mensajes de error
                UIManager.put("OptionPane.messageFont", new Font("Serif", Font.PLAIN, 18));
                UIManager.put("OptionPane.buttonFont", new Font("Serif", Font.PLAIN, 16));
                
                JOptionPane.showMessageDialog(this, 
                    "El cliente ha sido eliminado", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                cerrarPanelEdicion();
                return;
            }
            
            boolean nombreValido = validarNombreEdicion(txtNombreEdit.getText());
            boolean direccionValida = validarDireccionEdicion(txtDireccionEdit.getText());
            boolean valido = nombreValido && direccionValida;
            
            if (clienteSeleccionado instanceof PersonaNatural) {
                boolean numIdValido = validarNumIdEdicion(txtNumIdEdit.getText());
                valido = valido && numIdValido;
            } 
            else if (clienteSeleccionado instanceof PersonaJuridica) {
                boolean organismoValido = validarOrganismoEdicion(txtOrganismoEdit.getText());
                valido = valido && organismoValido;
            }
            
            if (!valido) {
                // Configurar fuente para los mensajes de error
                UIManager.put("OptionPane.messageFont", new Font("Serif", Font.PLAIN, 18));
                UIManager.put("OptionPane.buttonFont", new Font("Serif", Font.PLAIN, 16));
                
                throw new Exception("Complete todos los campos obligatorios marcados en rojo");
            }
            
            String nombre = txtNombreEdit.getText();
            String direccion = txtDireccionEdit.getText();
            
            if (clienteSeleccionado instanceof PersonaNatural) {
                PersonaNatural pn = (PersonaNatural) clienteSeleccionado;
                pn.setNombre(nombre);
                pn.setDireccion(direccion);
                pn.setMunicipio((String) cbMunicipioEdit.getSelectedItem());
                pn.setProvincia((String) cbProvinciaEdit.getSelectedItem());
                pn.setNumId(txtNumIdEdit.getText());
            } 
            else if (clienteSeleccionado instanceof PersonaJuridica) {
                PersonaJuridica pj = (PersonaJuridica) clienteSeleccionado;
                pj.setNombre(nombre);
                pj.setDireccion(direccion);
                pj.setMunicipio((String) cbMunicipioEdit.getSelectedItem());
                pj.setProvincia((String) cbProvinciaEdit.getSelectedItem());
                pj.setOrganismo(txtOrganismoEdit.getText());
            } 
            else if (clienteSeleccionado instanceof EntidadNoEstatal) {
                EntidadNoEstatal ene = (EntidadNoEstatal) clienteSeleccionado;
                ene.setNombre(nombre);
                ene.setDireccion(direccion);
            }
            
            tableModel.cargarClientes();
            cerrarPanelEdicion();
            
            // Configurar fuente para el mensaje de éxito
            UIManager.put("OptionPane.messageFont", new Font("Serif", Font.PLAIN, 18));
            UIManager.put("OptionPane.buttonFont", new Font("Serif", Font.PLAIN, 16));
            
            JOptionPane.showMessageDialog(this, "Cliente actualizado", 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            // Configurar fuente para los mensajes de error
            UIManager.put("OptionPane.messageFont", new Font("Serif", Font.PLAIN, 18));
            UIManager.put("OptionPane.buttonFont", new Font("Serif", Font.PLAIN, 16));
            
            JOptionPane.showMessageDialog(this, e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
    
    // Métodos de validación para creación
    private boolean validarNombreCreacion(String nombre) {
        boolean valido = !nombre.trim().isEmpty();
        lblNombreCreate.setForeground(valido ? Color.BLACK : Color.RED);
        return valido;
    }
    
    private boolean validarDireccionCreacion(String direccion) {
        boolean valido = !direccion.trim().isEmpty();
        lblDireccionCreate.setForeground(valido ? Color.BLACK : Color.RED);
        return valido;
    }
    
    private boolean validarNumIdCreacion(String numId) {
        boolean valido = !numId.trim().isEmpty();
        lblNumIdCreate.setForeground(valido ? Color.BLACK : Color.RED);
        return valido;
    }
    
    private boolean validarOrganismoCreacion(String organismo) {
        boolean valido = !organismo.trim().isEmpty();
        lblOrganismoCreate.setForeground(valido ? Color.BLACK : Color.RED);
        return valido;
    }

    // Métodos de validación para edición
    private boolean validarNombreEdicion(String nombre) {
        boolean valido = !nombre.trim().isEmpty();
        lblNombreEdit.setForeground(valido ? Color.BLACK : Color.RED);
        return valido;
    }
    
    private boolean validarDireccionEdicion(String direccion) {
        boolean valido = !direccion.trim().isEmpty();
        lblDireccionEdit.setForeground(valido ? Color.BLACK : Color.RED);
        return valido;
    }
    
    private boolean validarNumIdEdicion(String numId) {
        boolean valido = !numId.trim().isEmpty();
        lblNumIdEdit.setForeground(valido ? Color.BLACK : Color.RED);
        return valido;
    }
    
    private boolean validarOrganismoEdicion(String organismo) {
        boolean valido = !organismo.trim().isEmpty();
        lblOrganismoEdit.setForeground(valido ? Color.BLACK : Color.RED);
        return valido;
    }
    // Configuración del menú contextual
    private void configurarMenuContextual() {
        final JPopupMenu popupMenu = new JPopupMenu();
        
        JMenuItem menuEditar = new JMenuItem("Editar");
        JMenuItem menuEliminar = new JMenuItem("Eliminar");
        
        menuEditar.setFont(new Font("Serif", Font.PLAIN, 20));
        menuEliminar.setFont(new Font("Serif", Font.PLAIN, 20));
        
        menuEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    nombreClienteSeleccionado = (String) tableModel.getValueAt(selectedRow, 0);
                    clienteSeleccionado = EmpresaTelecomunicaciones.getInstancia()
                        .buscarCliente(nombreClienteSeleccionado);
                    
                    if (clienteSeleccionado != null) {
                        modoEdicion = true;
                        panelCreacion.setVisible(false);
                        panelEdicion.setVisible(true);
                        setSize(1478, 683);
                        mostrarCamposSegunTipoEdicion(clienteSeleccionado);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, 
                        "Por favor seleccione un cliente para editar", 
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        menuEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                
                if (selectedRow >= 0) {
                    UIManager.put("OptionPane.messageFont", new Font("Serif", Font.BOLD, 20));
                    UIManager.put("OptionPane.buttonFont", new Font("Serif", Font.BOLD, 18));
                    
                    String nombreCliente = (String) tableModel.getValueAt(selectedRow, 0);
                    
                    int confirm = JOptionPane.showConfirmDialog(null,
                        "¿Está seguro que desea eliminar este cliente?", 
                        "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    
                    if (confirm == JOptionPane.YES_OPTION) {
                        boolean eliminado = EmpresaTelecomunicaciones.getInstancia()
                            .eliminarCliente(nombreCliente);
                        
                        if (eliminado) {
                            if (nombreClienteSeleccionado != null && 
                                nombreClienteSeleccionado.equals(nombreCliente)) {
                                cerrarPanelEdicion();
                            }
                            
                            tableModel.cargarClientes();
                            JOptionPane.showMessageDialog(null, 
                                "Cliente eliminado correctamente", 
                                "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, 
                                "No se pudo eliminar el cliente", 
                                "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, 
                        "Por favor seleccione un cliente para eliminar", 
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        popupMenu.add(menuEditar);
        popupMenu.add(menuEliminar);
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                showPopupIfTriggered(e);
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                showPopupIfTriggered(e);
            }
            
            private void showPopupIfTriggered(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {  
                    int row = table.rowAtPoint(e.getPoint());
                    if (row >= 0) {
                        table.setRowSelectionInterval(row, row);
                        popupMenu.show(table, e.getX(), e.getY());
                    }
                }
            }
        });
    }

    // Método para mostrar la ventana
    public void mostrarVentana() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
        tableModel.cargarClientes();
    }

    // Método para cerrar el panel de edición/creación
    private void cerrarPanelEdicion() {
        panelEdicion.setVisible(false);
        panelCreacion.setVisible(false);
        setSize(1090, 683);
        resetearValidacionesCreacion();
        resetearValidacionesEdicion();
        clienteSeleccionado = null;
        nombreClienteSeleccionado = null;
    }
}