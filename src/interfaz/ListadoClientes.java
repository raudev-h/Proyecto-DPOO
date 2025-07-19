package interfaz;

import auxiliares.*;
import logica.*;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.table.*;

import excepciones.CarnetIdentidadInvalidoException;
import excepciones.DuplicadosException;
import excepciones.NombreInvalidoException;
import excepciones.UbicacionInvalidaException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class ListadoClientes extends JDialog {

    // Componentes de la interfaz
    private JTable table;
    private ClienteTableModel tableModel;
    private static ListadoClientes instance;
    private JTextField txtBusqueda;


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
    private JButton btnSeleccionarRepresentanteEdit;
    

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
    private JButton btnSeleccionarRepresentanteCreate;
    private JComboBox<String> cbTipoCliente;
    private JLabel lblRepresentanteJuridicaCreate;  // Para Persona Jurídica (creación)
    private JLabel lblRepresentanteEntidadCreate;   // Para Entidad No Estatal (creación)
    private JLabel lblRepresentanteSeleccionadoEdit;
    private JLabel lblRepresentanteSeleccionadoEdit1;
    
    private Cliente clienteSeleccionado;
    private String nombreClienteSeleccionado;
    private boolean modoEdicion;
    private Representante representanteSeleccionado;
    

    // Etiquetas para validación
    private JLabel lblNombreEdit, lblDireccionEdit, lblMunicipioEdit, lblProvinciaEdit, lblNumIdEdit, lblOrganismoEdit;
    private JLabel lblNombreCreate, lblDireccionCreate, lblMunicipioCreate, lblProvinciaCreate, lblNumIdCreate, lblOrganismoCreate;
    private JLabel lblTipoCliente;
    private JLabel lblTituloEdicion;
    
 // Componentes para seleccionar un servicio al crear un cliente
    private JButton btnSeleccionarServicio;
    private Servicio servicioSeleccionado;
    private static Principal ventanaPrincipal;


    // Mapa de provincias y municipios de Cuba
    private static final Map<String, String[]> PROVINCIAS_MUNICIPIOS = new LinkedHashMap<String, String[]>() {{
        put("Pinar del Río", new String[]{"Consolación del Sur", "Guane", "La Palma", "Los Palacios", 
            "Mantua", "Minas de Mateliahambre", "Pinar del Río", "San Juan y Martínez", 
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
    private ListadoClientes(Principal principal) {
        setBounds(100, 100, 1087, 790);
        ventanaPrincipal = principal;
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        setTitle("Listado de Clientes");
        setModal(true);
        setResizable(false); // ← Esto evita que la ventana se pueda redimensionar
        
        
        initComponents();
        configurarMenuContextual();
        
    }
    


    // Método Singleton para obtener la instancia

    public static synchronized ListadoClientes getInstance(Principal principal) {
        if (instance == null) {
            instance = new ListadoClientes(principal);
        } else {
            // Actualizar referencia si ya existe
            instance.ventanaPrincipal = principal;
        }
        return instance;
    }

    // Método estático para abrir la ventana

    public static void abrirListadoClientes(final Principal principal) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Pre-cambiar la imagen ANTES de crear el diálogo
                principal.cambiarImagenFondo("/imagenes/e.png");
                
                if (instance != null && instance.isVisible()) {
                    JOptionPane.showMessageDialog(null, 
                        "El listado de clientes ya está abierto", 
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
                    instance.toFront();
                    return;
                }
                
                ListadoClientes dialog = ListadoClientes.getInstance(principal);
                dialog.mostrarVentana();
                
                if (dialog.isVisible()) {
                    UIManager.put("OptionPane.messageFont", new Font("Serif", Font.BOLD, 20));
                    dialog.toFront();
                }
            }
        });
    }
    
    
    //Liberar la instancia al cerrar el listado

    @Override
    public void dispose() {
        // Restaurar imagen original SOLO cuando se cierra completamente
        if (ventanaPrincipal != null) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    ventanaPrincipal.cambiarImagenFondo("/imagenes/d.png");
                }
            });
        }
        instance = null;
        super.dispose();
    }
    private void initComponents() {
        tableModel = new ClienteTableModel();
        tableModel.cargarClientes();
        
        JPanel panel = new JPanel();
        panel.setBounds(15, 16, 1044, 650);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        // Panel de búsqueda
        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setBounds(15, 30, 588, 40);
        panelBusqueda.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panel.add(panelBusqueda);
        
        // Componente de búsqueda
        JLabel lblBuscar = new JLabel("Buscar:");
        lblBuscar.setFont(new Font("Serif", Font.PLAIN, 18));
        panelBusqueda.add(lblBuscar);
        
        txtBusqueda = new JTextField();
        txtBusqueda.setFont(new Font("Serif", Font.PLAIN, 18));
        txtBusqueda.setColumns(30);
        txtBusqueda.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filtrarTabla();
            }
        });
        panelBusqueda.add(txtBusqueda);
        
        JLabel lblListadoDeClientes = new JLabel("Listado de Clientes");
        lblListadoDeClientes.setFont(new Font("Serif", Font.BOLD, 21));
        lblListadoDeClientes.setBounds(15, 0, 195, 20);
        panel.add(lblListadoDeClientes);
        
        // Configuración única del JScrollPane y JTable
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
        scrollPane.setBounds(15, 80, 1014, 570);
        panel.add(scrollPane);
        
        table = new JTable(tableModel);
        table.getTableHeader().setReorderingAllowed(false);
        
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFont(new Font("Serif", Font.PLAIN, 18));
        table.setRowHeight(25);
        scrollPane.setViewportView(table);
        
        // Configurar anchos de columnas
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(200); // Nombre
        columnModel.getColumn(1).setPreferredWidth(200); // Dirección
        columnModel.getColumn(2).setPreferredWidth(150); // Tipo
        
        JTableHeader header = table.getTableHeader();
        Font headerFont = new Font("Serif", Font.PLAIN, 20);
        header.setFont(headerFont);
        
        // Configurar el TableRowSorter
        sorter = new TableRowSorter<ClienteTableModel>(tableModel);
        table.setRowSorter(sorter);
        
        // Botón Crear Cliente
        btnCrearCliente = new JButton("Crear Cliente");
        btnCrearCliente.setForeground(new Color(0, 0, 153));
        btnCrearCliente.setBackground(Color.WHITE);
        btnCrearCliente.setFont(new Font("Serif", Font.PLAIN, 20));
        btnCrearCliente.setBounds(850, 0, 180, 30);
        btnCrearCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirPanelCreacion();
            }
        });
        panel.add(btnCrearCliente);
        
        initPanelCreacion();
        initPanelEdicion();
    }
    
 // Añadir nuevo método para filtrar la tabla (similar al de ListadoRepresentante)
    private void filtrarTabla() {
        final String textoBusqueda = txtBusqueda.getText().trim().toLowerCase();
        
        if (textoBusqueda.isEmpty()) {
            tableModel.cargarClientes(); // Mostrar todos
            return;
        }
        
        try {
            ArrayList<Cliente> clientesFiltrados = new ArrayList<>();
            ArrayList<Cliente> todosClientes = EmpresaTelecomunicaciones.getInstancia().getClientes();
            
            for (Cliente cliente : todosClientes) {
                boolean coincideNombre = cliente.getNombre().toLowerCase().contains(textoBusqueda);
                boolean coincideCI = false;
                
                // Solo para PersonaNatural: buscar en el carnet
                if (cliente instanceof PersonaNatural) {
                    PersonaNatural pn = (PersonaNatural) cliente;
                    String numId = pn.getNumId().toLowerCase();
                    coincideCI = numId.contains(textoBusqueda);
                }
                
                if (coincideNombre || coincideCI) {
                    clientesFiltrados.add(cliente);
                }
            }
            
            tableModel.actualizarClientes(clientesFiltrados);
            
        } catch (Exception e) {
            manejarError(e, "Error al aplicar el filtro de búsqueda");
        }
    }

    // Añadir variable de clase para el sorter
    private TableRowSorter<ClienteTableModel> sorter;

    private void abrirPanelCreacion() {
        modoEdicion = false;
        resetearCamposCreacion();
        panelEdicion.setVisible(false);
        panelCreacion.setVisible(true);
        setSize(1478, 790);
    }
    
    private void resetearCamposCreacion() {
        txtNombreCreate.setText("");
        txtDireccionCreate.setText("");
        txtNumIdCreate.setText("");
        txtOrganismoCreate.setText("");
        cbProvinciaCreate.setSelectedItem("La Habana");
        cbTipoCliente.setSelectedItem("Persona Natural");

        
        // Resetear ambas etiquetas
        if (lblRepresentanteJuridicaCreate != null) {
            lblRepresentanteJuridicaCreate.setText("Representante: Ninguno");
        }
        if (lblRepresentanteEntidadCreate != null) {
            lblRepresentanteEntidadCreate.setText("Representante: Ninguno");
        }
        
        representanteSeleccionado = null;
        servicioSeleccionado = null;
        mostrarCamposSegunTipoCreacion("Persona Natural");
    }



    // ============ PANEL DE CREACIÓN ============ 

    private void initPanelCreacion() {
    	panelCreacion = new JPanel();
        panelCreacion.setBorder(new LineBorder(new Color(0, 0, 0)));
        panelCreacion.setBounds(1074, 55, 350, 650);
        panelCreacion.setVisible(false);
        panelCreacion.setLayout(null);
        getContentPane().add(panelCreacion);
        

        
        // COMPONENTES COMUNES

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
        panelPersonaJuridicaCreate.setBounds(0, 400, 350, 120);
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
        //Aqui va lo que tiene que ver con representantes
        
        btnSeleccionarRepresentanteCreate = new JButton("Seleccionar Representante");
        btnSeleccionarRepresentanteCreate.setForeground(new Color(0, 0, 153));
        btnSeleccionarRepresentanteCreate.setBackground(new Color(255, 255, 255));
        btnSeleccionarRepresentanteCreate.setFont(new Font("Serif", Font.PLAIN, 18));
        btnSeleccionarRepresentanteCreate.setBounds(35, 60, 280, 30);
        btnSeleccionarRepresentanteCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seleccionarRepresentanteCreacion();
            }
        });
        panelPersonaJuridicaCreate.add(btnSeleccionarRepresentanteCreate);
        
        lblRepresentanteJuridicaCreate = new JLabel("Representante: Ninguno");
        lblRepresentanteJuridicaCreate.setFont(new Font("Serif", Font.PLAIN, 16));
        lblRepresentanteJuridicaCreate.setBounds(35, 90, 280, 30);
        panelPersonaJuridicaCreate.add(lblRepresentanteJuridicaCreate);
         
        
        // Panel para EntidadNoEstatal
        panelEntidadNoEstatalCreate = new JPanel();
        panelEntidadNoEstatalCreate.setBounds(0, 400, 350, 120);
        panelEntidadNoEstatalCreate.setLayout(null);
        panelEntidadNoEstatalCreate.setVisible(false);
        panelCreacion.add(panelEntidadNoEstatalCreate);
        
     // Ahora puedes agregar componentes a panelEntidadNoEstatalCreate
        btnSeleccionarRepresentanteCreate = new JButton("Seleccionar Representante");
        btnSeleccionarRepresentanteCreate.setForeground(Color.WHITE);
        btnSeleccionarRepresentanteCreate.setBackground(new Color(0, 0, 153));
        btnSeleccionarRepresentanteCreate.setFont(new Font("Serif", Font.PLAIN, 16));
        btnSeleccionarRepresentanteCreate.setBounds(35, 0, 280, 30);
        btnSeleccionarRepresentanteCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seleccionarRepresentanteCreacion();
            }
        });
        lblRepresentanteEntidadCreate = new JLabel("Representante: Ninguno");
        lblRepresentanteEntidadCreate.setFont(new Font("Serif", Font.PLAIN, 16));
        lblRepresentanteEntidadCreate.setBounds(35, 40, 280, 30);
        panelEntidadNoEstatalCreate.add(lblRepresentanteEntidadCreate);
        
        // Botón para seleccionar representante
        btnSeleccionarRepresentanteCreate = new JButton("Seleccionar Representante");
        btnSeleccionarRepresentanteCreate.setForeground(new Color(0, 0, 153));
        btnSeleccionarRepresentanteCreate.setBackground(Color.WHITE);
        btnSeleccionarRepresentanteCreate.setFont(new Font("Serif", Font.PLAIN, 18));
        btnSeleccionarRepresentanteCreate.setBounds(35, 0, 280, 30);
        btnSeleccionarRepresentanteCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seleccionarRepresentanteCreacion();
            }
        });
        panelEntidadNoEstatalCreate.add(btnSeleccionarRepresentanteCreate);
        
        // Botones
        btnAceptarCreate = new JButton("Aceptar");
        btnAceptarCreate.setForeground(new Color(255, 255, 255));
        btnAceptarCreate.setBackground(new Color(0, 0, 153));
        btnAceptarCreate.setFont(new Font("Serif", Font.PLAIN, 19));
        btnAceptarCreate.setBounds(35, 580, 120, 30);
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
        btnCancelarCreate.setBounds(195, 580, 120, 30);
        btnCancelarCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cerrarPanelEdicion();
            }
        });
        panelCreacion.add(btnCancelarCreate);
        
        //Boton de Seleccionar un Servicio
        btnSeleccionarServicio = new JButton("Seleccionar Servicio");
        btnSeleccionarServicio.setForeground(new Color(0, 0, 153));
        btnSeleccionarServicio.setBackground(new Color(255, 255, 255));
        btnSeleccionarServicio.setFont(new Font("Serif", Font.PLAIN, 18));
        btnSeleccionarServicio.setBounds(35, 520, 280, 30);
        btnSeleccionarServicio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seleccionarServicio();
            }
        });
        panelCreacion.add(btnSeleccionarServicio);

    }

    //Metodo para le eleccion de un Representante en la tabla de servicio al crear a un cliente

    private void seleccionarRepresentanteCreacion() {
        if(EmpresaTelecomunicaciones.getInstancia().buscarRepresentantesLibres().size()>0){
            Representante rep = ListadoSeleccionRepresentante.abrirYSeleccionar();
            if (rep != null) {
                representanteSeleccionado = rep;
                
                // Actualizar ambas etiquetas de creación
                if (lblRepresentanteJuridicaCreate != null) {
                    lblRepresentanteJuridicaCreate.setText("Representante: " + rep.getNombreCompleto());
                }
                if (lblRepresentanteEntidadCreate != null) {
                    lblRepresentanteEntidadCreate.setText("Representante: " + rep.getNombreCompleto());
                }
            }
        }
        else{
            UIManager.put("OptionPane.messageFont", new Font("Serif", Font.PLAIN, 18));
            JOptionPane.showMessageDialog(this, 
                "No hay representantes disponibles", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
  //Metodo para le eleccion de un Servicio en la tabla de servicio al crear a un cliente
    private void seleccionarServicio() {
        ArrayList<Servicio> serviciosDisponibles = EmpresaTelecomunicaciones.getInstancia().getServiciosDisponibles();
        
        if (serviciosDisponibles != null && !serviciosDisponibles.isEmpty()) {
            Servicio servicio = ListadoSeleccionServicio.abrirYSeleccionar();
            if (servicio != null) {  // Verificar que el servicio no sea null
            	
                servicioSeleccionado = servicio;                
                UIManager.put("OptionPane.messageFont", new Font("Serif", Font.PLAIN, 18));
                JOptionPane.showMessageDialog(this, 
                    "Servicio asignado correctamente", 
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            UIManager.put("OptionPane.messageFont", new Font("Serif", Font.PLAIN, 18));
            JOptionPane.showMessageDialog(this, 
                "No hay servicios disponibles", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
   

    //Metodo para cargar los municipios en el ComboBox en dependencia de la provincia actual
    private void cargarMunicipiosCreacion(String provincia) {
        cbMunicipioCreate.removeAllItems();
        String[] municipios = PROVINCIAS_MUNICIPIOS.get(provincia);
        if (municipios != null) {
            for (String municipio : municipios) {
                cbMunicipioCreate.addItem(municipio);
            }
        }
    }
    
    //Metodo para mostrar solamente los campos necesarios para cada tipo de cliente
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
        lblNombreCreate.setForeground(UIManager.getColor("Label.foreground"));
        lblDireccionCreate.setForeground(UIManager.getColor("Label.foreground"));
        lblNumIdCreate.setForeground(UIManager.getColor("Label.foreground"));
        lblOrganismoCreate.setForeground(UIManager.getColor("Label.foreground"));
    }

    //Metodo para crear un nuevo cliente en dependencia del cliente que se este intentnando crear 
    private void crearCliente() {
        Cliente nuevoCliente = null;
        boolean creado = false;
        StringBuilder errores = new StringBuilder();
        String tipoCliente = (String) cbTipoCliente.getSelectedItem();

        try {
        	
        	// Validar que se haya seleccionado un servicio
        	if (servicioSeleccionado == null) {
        	    errores.append("un Servicio Telefónico");       	    
        	}
           
            // Validar representante para tipos que lo requieren
            if ((tipoCliente.equals("Persona Jurídica") || tipoCliente.equals("Entidad No Estatal")) && 
                representanteSeleccionado == null) {           	
            	errores.append("un Representante");
            }
            
            if(errores.length() > 0){
            	if(representanteSeleccionado == null && servicioSeleccionado == null && !(tipoCliente.equals("Persona Natural"))){
            		errores.insert(22, " y ");
            	}
            
                UIManager.put("OptionPane.messageFont", new Font("Serif", Font.PLAIN, 18));
                JOptionPane.showMessageDialog(this, 
                    "Error: Debe seleccionar "+ errores.toString(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
                
                
                
            
            // CREAR PERSONA NATURAL
            if (tipoCliente.equals("Persona Natural")) {
                
                creado = crearPersonaNatural(servicioSeleccionado);
                if(creado){
                	//Obtener nuevo cliente
                	int longitudListado = EmpresaTelecomunicaciones.getInstancia().getClientes().size();
                	nuevoCliente = EmpresaTelecomunicaciones.getInstancia().getClientes().get(longitudListado-1);

                }
                
             }
            
            
            //CREAR UNA PERSONA JURIDICA
            else if (tipoCliente.equals("Persona Jurídica")) {
            	creado = crearPersonaJuridica(servicioSeleccionado, representanteSeleccionado);
            	
                  if(creado){  
                  	
                	//Obtener nuevo cliente

                	int longitudListado = EmpresaTelecomunicaciones.getInstancia().getClientes().size();

                  	nuevoCliente = EmpresaTelecomunicaciones.getInstancia().getClientes().get(longitudListado-1);

                	  
                    // Asignación bidireccional del representante y cliente
                    if (representanteSeleccionado != null) {
                        EmpresaTelecomunicaciones.getInstancia()
                            .asignarRepresentanteACliente(nuevoCliente, representanteSeleccionado);
                    }
                  }
             }
            else if (tipoCliente.equals("Entidad No Estatal")) {
                	
            		creado = crearEntidadNoEstatal(servicioSeleccionado, representanteSeleccionado);
            		
            		if(creado){
            			int longitudListado = EmpresaTelecomunicaciones.getInstancia().getClientes().size();
                      	nuevoCliente = EmpresaTelecomunicaciones.getInstancia().getClientes().get(longitudListado-1);
                    
                    // Asignación bidireccional del representante
                    if (representanteSeleccionado != null) {
                        EmpresaTelecomunicaciones.getInstancia()
                            .asignarRepresentanteACliente(nuevoCliente, representanteSeleccionado);
                    }
                }
            }
         
            
            if (nuevoCliente != null) {
            	
            	
                // Asignar el servicio seleccionado al cliente
                servicioSeleccionado.setTitular(nuevoCliente);                
                   
                    // Actualizar las listas de servicios
                 EmpresaTelecomunicaciones.getInstancia().getServicios().add(servicioSeleccionado);
                 EmpresaTelecomunicaciones.getInstancia().getServiciosDisponibles().remove(servicioSeleccionado);
                 
  
                // Actualizar la tabla
                tableModel.cargarClientes();
                
                // Limpiar y liberar recursos
                resetearCamposCreacion();
                representanteSeleccionado = null;
                servicioSeleccionado = null;
                cerrarPanelEdicion();
                

                // Mostrar mensaje de éxito

                UIManager.put("OptionPane.messageFont", new Font("Serif", Font.PLAIN, 18));
                UIManager.put("OptionPane.buttonFont", new Font("Serif", Font.PLAIN, 16));
                JOptionPane.showMessageDialog(this, "Cliente creado exitosamente", 
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                throw new Exception("No se pudo crear el cliente");
            }
        } catch (Exception e) {
            // En caso de error, liberar cualquier recurso asignado en la BD de la empresa o en el servicio seleccionado
            if (nuevoCliente != null) {
                EmpresaTelecomunicaciones.getInstancia().getClientes().remove(nuevoCliente);
            }
            if (servicioSeleccionado != null && servicioSeleccionado.getTitular() != null) {
                servicioSeleccionado.setTitular(null);
            }
            
            
        }
    }


    // ============ PANEL DE EDICIÓN ============

    private void initPanelEdicion() {
        panelEdicion = new JPanel();
        panelEdicion.setBorder(new LineBorder(new Color(0, 0, 0)));
        panelEdicion.setBounds(1074, 55, 350, 650);
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
        panelPersonaJuridicaEdit.setBounds(0, 330, 350, 120);
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
        
        btnSeleccionarRepresentanteEdit = new JButton("Seleccionar Representante");
        btnSeleccionarRepresentanteEdit.setForeground(new Color(0, 0, 153));
        btnSeleccionarRepresentanteEdit.setBackground(Color.WHITE);
        btnSeleccionarRepresentanteEdit.setFont(new Font("Serif", Font.PLAIN, 18));
        btnSeleccionarRepresentanteEdit.setBounds(35, 60, 280, 30);
        btnSeleccionarRepresentanteEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seleccionarRepresentanteEdicion();
            }
        });
        panelPersonaJuridicaEdit.add(btnSeleccionarRepresentanteEdit);
        
        lblRepresentanteSeleccionadoEdit = new JLabel("Representante: Ninguno");
        lblRepresentanteSeleccionadoEdit.setFont(new Font("Serif", Font.PLAIN, 16));
        lblRepresentanteSeleccionadoEdit.setBounds(35, 90, 280, 30);
        panelPersonaJuridicaEdit.add(lblRepresentanteSeleccionadoEdit);
      
        // Panel para EntidadNoEstatal
        panelEntidadNoEstatalEdit = new JPanel();
        panelEntidadNoEstatalEdit.setBounds(0, 330, 350, 131);
        panelEntidadNoEstatalEdit.setLayout(null);
        panelEntidadNoEstatalEdit.setVisible(false);
        panelEdicion.add(panelEntidadNoEstatalEdit);
        
        
        
        // Botón para seleccionar representante
        JButton btnSeleccionarRepresentanteEdit1 = new JButton("Seleccionar Representante");
        btnSeleccionarRepresentanteEdit1.setForeground(new Color(0, 0, 153));
        btnSeleccionarRepresentanteEdit1.setBackground(new Color(255, 255, 255));
        btnSeleccionarRepresentanteEdit1.setFont(new Font("Serif", Font.PLAIN, 18));
        btnSeleccionarRepresentanteEdit1.setBounds(35, 60, 280, 30);
        btnSeleccionarRepresentanteEdit1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seleccionarRepresentanteEdicion();
            }
        });
        panelEntidadNoEstatalEdit.add(btnSeleccionarRepresentanteEdit1);
        
        lblRepresentanteSeleccionadoEdit1 = new JLabel("Representante: Ninguno");
        lblRepresentanteSeleccionadoEdit1.setFont(new Font("Serif", Font.PLAIN, 16));
        lblRepresentanteSeleccionadoEdit1.setBounds(35, 90, 280, 30);  // Ajusta posición según necesites
        panelEntidadNoEstatalEdit.add(lblRepresentanteSeleccionadoEdit1);
        
        
        // Botones
        btnAceptarEdit = new JButton("Aceptar");
        btnAceptarEdit.setForeground(new Color(255, 255, 255));
        btnAceptarEdit.setBackground(new Color(0, 0, 153));
        btnAceptarEdit.setFont(new Font("Serif", Font.PLAIN, 19));
        btnAceptarEdit.setBounds(35, 580, 120, 30);
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
        btnCancelarEdit.setBounds(195, 580, 120, 30);
        btnCancelarEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cerrarPanelEdicion();
            }
        });
        
        
        panelEdicion.add(btnCancelarEdit);
    }

    private void seleccionarRepresentanteEdicion() {
        if(EmpresaTelecomunicaciones.getInstancia().buscarRepresentantesLibres().size() > 0){
            Representante rep = ListadoSeleccionRepresentante.abrirYSeleccionar();
            if (rep != null) {
                representanteSeleccionado = rep;
                // Actualizar etiquetas solo si no son null
                if (lblRepresentanteSeleccionadoEdit != null) {
                    lblRepresentanteSeleccionadoEdit.setText("Representante: " + rep.getNombreCompleto());
                }
                if (lblRepresentanteSeleccionadoEdit1 != null) {
                    lblRepresentanteSeleccionadoEdit1.setText("Representante: " + rep.getNombreCompleto());
                }
            }
        }
        else {
            UIManager.put("OptionPane.messageFont", new Font("Serif", Font.PLAIN, 18));
            JOptionPane.showMessageDialog(this, 
                "No hay representantes disponibles", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
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
            
            if (pj.getRepresentante() != null) {
                lblRepresentanteSeleccionadoEdit.setText("Representante: " + pj.getRepresentante().getNombreCompleto());
                representanteSeleccionado = pj.getRepresentante();
            } else {
                lblRepresentanteSeleccionadoEdit.setText("Representante: Ninguno");
                representanteSeleccionado = null;
            }
        } 
        else if (cliente instanceof EntidadNoEstatal) {
            EntidadNoEstatal ene = (EntidadNoEstatal) cliente;
            panelEntidadNoEstatalEdit.setVisible(true);
            
            cbMunicipioEdit.setVisible(false);
            cbProvinciaEdit.setVisible(false);
            lblMunicipioEdit.setVisible(false);
            lblProvinciaEdit.setVisible(false);
            
            if (ene.getRepresentanteEntidad() != null) {
                lblRepresentanteSeleccionadoEdit1.setText("Representante: " + ene.getRepresentanteEntidad().getNombreCompleto());
                representanteSeleccionado = ene.getRepresentanteEntidad();
            } else {
                lblRepresentanteSeleccionadoEdit1.setText("Representante: Ninguno");
                representanteSeleccionado = null;
            }
        }
    }

    private void resetearValidacionesEdicion() {
        lblNombreEdit.setForeground(UIManager.getColor("Label.foreground"));
        lblDireccionEdit.setForeground(UIManager.getColor("Label.foreground"));
        lblNumIdEdit.setForeground(UIManager.getColor("Label.foreground"));
        lblOrganismoEdit.setForeground(UIManager.getColor("Label.foreground"));
    }

	    private void actualizarCliente() {
	        try {
            // Obtener el cliente actual desde la base de datos
            Cliente clienteActual = EmpresaTelecomunicaciones.getInstancia()
                                  .buscarCliente(nombreClienteSeleccionado);
            
            // Verificar si el cliente aún existe
            if (clienteActual == null) {
                UIManager.put("OptionPane.messageFont", new Font("Serif", Font.PLAIN, 18));
                JOptionPane.showMessageDialog(this, 
                    "El cliente ha sido eliminado", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                cerrarPanelEdicion();
                return;
            }
            
            StringBuilder errores = new StringBuilder();
            
            // Validar campos comunes
            //Validar Nombre
            try{
            	if(clienteActual instanceof PersonaNatural){
            		validarNombreEdicion(txtNombreEdit.getText());
            	}
            	if(clienteActual instanceof PersonaJuridica){
            		this.validarNombreEdicionRobustaJ(txtNombreEdit.getText(), clienteActual);
            	}
            	if(clienteActual instanceof EntidadNoEstatal){
            		this.validarNombreEdicionRobustaE(txtNombreEdit.getText(), clienteActual);
            		
            	}
        	    lblNombreEdit.setForeground(UIManager.getColor("Label.foreground")); 

            }catch(Exception e){
            	errores.append("- ").append(e.getMessage()).append("\n");
        	    lblNombreEdit.setForeground(Color.RED); 
            }
            
            // Validar Direccion
            try{
            	this.validarDireccionEdicion(txtDireccionEdit.getText());
        	    lblDireccionEdit.setForeground(UIManager.getColor("Label.foreground")); 

            }catch(Exception e){
            	errores.append("- ").append(e.getMessage()).append("\n");
        	    lblDireccionEdit.setForeground(Color.RED); 
            }
           
            if (clienteSeleccionado instanceof PersonaNatural) {
                
            	try{
                	this.validarNumIdEdicion(txtNumIdEdit.getText(), clienteActual); 
            	 // PRIMERO VERIFICAR SI EL NÚMERO YA EXISTE
    	            for (Cliente c : EmpresaTelecomunicaciones.getInstancia().getClientes()) {
    	                if (c instanceof PersonaNatural) {
    	                    PersonaNatural pn = (PersonaNatural)c;
    	                    if (pn != clienteActual && pn.getNumId().equals(txtNumIdEdit.getText() )) {

    	                        throw new DuplicadosException("Ya existe una persona con este número de identidad");
    	                    }
    	                }
    	            }
    	            for(Representante r: EmpresaTelecomunicaciones.getInstancia().getRepresentantes()){
    	            	if(txtNumIdEdit.getText().equals(r.getNumId())){
    	            		 throw new DuplicadosException("Ya existe una persona con este número de identidad");
    	            	}
    	            	
    	            	
    	            	
    	            }
            	    lblNumIdEdit.setForeground(UIManager.getColor("Label.foreground")); 

                }catch(Exception e){
                	errores.append("- ").append(e.getMessage()).append("\n");
                	lblNumIdEdit.setForeground(Color.RED); 
                }
               
            } 
            else if (clienteSeleccionado instanceof PersonaJuridica) {
            	
            	try{
                	this.validarOrganismoEdicion(txtOrganismoEdit.getText());
            	    lblNumIdEdit.setForeground(UIManager.getColor("Label.foreground")); 

                }catch(Exception e){
                	errores.append("- ").append(e.getMessage()).append("\n");
                	lblNumIdEdit.setForeground(Color.RED); 
                }         	
            }
            
            if (errores.length() > 0) {
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
            }
            else{
            // Guardar el representante anterior para limpieza posterior si es necesario
            Representante representanteAnterior = null;
            if (clienteSeleccionado instanceof PersonaJuridica) {
                representanteAnterior = ((PersonaJuridica)clienteSeleccionado).getRepresentantePersonaJuridica();
            } 
            else if (clienteSeleccionado instanceof EntidadNoEstatal) {
                representanteAnterior = ((EntidadNoEstatal)clienteSeleccionado).getRepresentanteEntidad();
            }
            
            // Actualizar datos básicos del cliente
            String nombre = txtNombreEdit.getText();
            String direccion = txtDireccionEdit.getText();
            
            clienteSeleccionado.setNombre(nombre);
            clienteSeleccionado.setDireccion(direccion);
            
            // Actualizar campos específicos según el tipo de cliente
            if (clienteSeleccionado instanceof PersonaNatural) {
                PersonaNatural pn = (PersonaNatural) clienteSeleccionado;
                pn.setMunicipio((String) cbMunicipioEdit.getSelectedItem());
                pn.setProvincia((String) cbProvinciaEdit.getSelectedItem());
                pn.setNumId(txtNumIdEdit.getText());
            } 
            else if (clienteSeleccionado instanceof PersonaJuridica) {
                PersonaJuridica pj = (PersonaJuridica) clienteSeleccionado;
                pj.setMunicipio((String) cbMunicipioEdit.getSelectedItem());
                pj.setProvincia((String) cbProvinciaEdit.getSelectedItem());
                pj.setOrganismo(txtOrganismoEdit.getText());
                
                // Manejo del representante
                if (representanteSeleccionado != null) {
                    // Desasignar primero el representante anterior si es diferente al nuevo
                    if (representanteAnterior != null && !representanteAnterior.equals(representanteSeleccionado)) {
                        EmpresaTelecomunicaciones.getInstancia()
                            .desasignarRepresentanteDeCliente(clienteSeleccionado);
                    }
                    // Asignar el nuevo representante
                    EmpresaTelecomunicaciones.getInstancia()
                        .asignarRepresentanteACliente(clienteSeleccionado, representanteSeleccionado);
                } else {
                    // Si no hay representante seleccionado, desasignar el existente
                    EmpresaTelecomunicaciones.getInstancia()
                        .desasignarRepresentanteDeCliente(clienteSeleccionado);
                }
            } 
            else if (clienteSeleccionado instanceof EntidadNoEstatal) {
                EntidadNoEstatal ene = (EntidadNoEstatal) clienteSeleccionado;
                
                // Manejo del representante
                if (representanteSeleccionado != null) {
                    // Desasignar primero el representante anterior si es diferente al nuevo
                    if (representanteAnterior != null && !representanteAnterior.equals(representanteSeleccionado)) {
                        EmpresaTelecomunicaciones.getInstancia()
                            .desasignarRepresentanteDeCliente(clienteSeleccionado);
                    }
                    // Asignar el nuevo representante
                    EmpresaTelecomunicaciones.getInstancia()
                        .asignarRepresentanteACliente(clienteSeleccionado, representanteSeleccionado);
                } else {
                    // Si no hay representante seleccionado, desasignar el existente
                    EmpresaTelecomunicaciones.getInstancia()
                        .desasignarRepresentanteDeCliente(clienteSeleccionado);
                }
            }
            
            // Actualizar la tabla de clientes
            tableModel.cargarClientes();
            
            // Cerrar el panel de edición y limpiar
            cerrarPanelEdicion();
            

            // Mostrar mensaje de éxito

            UIManager.put("OptionPane.messageFont", new Font("Serif", Font.PLAIN, 18));
            UIManager.put("OptionPane.buttonFont", new Font("Serif", Font.PLAIN, 16));
            JOptionPane.showMessageDialog(this, "Cliente actualizado correctamente", 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
  
            }

        } catch (Exception e) {
            
        }
	        
    }
    

    // Métodos de validación para edición

    private void  validarNombreEdicion(String nombre) throws NombreInvalidoException {
        
        Cliente.validarNombre(nombre);
        
        }

    private void validarNombreEdicionRobustaE(String nombre,Cliente clienteActual)throws NombreInvalidoException {
            
            Cliente.validarNombre(nombre);
            
            for(Cliente c: EmpresaTelecomunicaciones.getInstancia().getClientes()){
            	if(c instanceof EntidadNoEstatal){
	            	if(c.getNombre().equalsIgnoreCase(nombre) && c!= clienteActual){
	            		throw new NombreInvalidoException("Esa Entidad Estatal ya existe en nuestro sistema");
	            	}
            	}
            }
            }
    private void validarNombreEdicionRobustaJ(String nombre,Cliente clienteActual)throws NombreInvalidoException {
        
        Cliente.validarNombre(nombre);
        for(Cliente c: EmpresaTelecomunicaciones.getInstancia().getClientes()){
        	if(c instanceof PersonaJuridica){
            	if(c.getNombre().equalsIgnoreCase(nombre) && c!= clienteActual){
            		throw new NombreInvalidoException("Esa Persona Juridica ya existe en nuestro sistema");
            	}
        	}
        }
      }
       
    
    private void validarDireccionEdicion(String direccion) throws UbicacionInvalidaException {

    	Cliente.validarDireccion(direccion);
    }
    
    private void validarNumIdEdicion(String numId, Cliente cliente) throws CarnetIdentidadInvalidoException {
        		
        PersonaNatural.validarNumId(numId);
        
        for(Cliente c: EmpresaTelecomunicaciones.getInstancia().getClientes()){
        	if(c instanceof PersonaNatural){
        		if(((PersonaNatural)c).getNumId().equals(numId) && c != cliente){
        			throw new CarnetIdentidadInvalidoException("Ya existe una persona con este número de identidad");
        			
        		}
        	}
        }

    }
    
    private void validarOrganismoEdicion(String organismo) throws NombreInvalidoException {
       
    	PersonaJuridica.validarOrganismo(organismo);
    }


    // Configuración del menú contextual

    private void configurarMenuContextual() {
        final JPopupMenu popupMenu = new JPopupMenu();
        
        JMenuItem menuEditar = new JMenuItem("Editar");
        JMenuItem menuEliminar = new JMenuItem("Eliminar");
        
        menuEditar.setFont(new Font("Serif", Font.PLAIN, 20));
        menuEliminar.setFont(new Font("Serif", Font.PLAIN, 20));
        menuEliminar.setForeground(Color.RED);
        
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
                        setSize(1478, 790);
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
        setSize(1090, 790);
        resetearValidacionesCreacion();
        resetearValidacionesEdicion();
        clienteSeleccionado = null;
        nombreClienteSeleccionado = null;
        representanteSeleccionado = null;
    }
    
    //Metodos puros para crear cada tipo de Cliente 
  //Crear una PERSONA NATURAL validando todos sus datos y lanzando excepciones en cada caso
    public boolean crearPersonaNatural(Servicio servicioSeleccionado){
    	   	
    	boolean creado = false;
    	
    	StringBuilder errores = new StringBuilder();
 
   	
    	//Atributos necesarios para crear a una Persona Natural
    	String nombre = null;
    	String direccion = null;
    	String numId = null;
    	String provincia = null;
    	String municipio = null;

	    	//Validar Nombre
	    	try {
	    	    Cliente.validarNombre(txtNombreCreate.getText());
	    	    
	    	    nombre = txtNombreCreate.getText();
	    	    lblNombreCreate.setForeground(UIManager.getColor("Label.foreground")); 
	    	   
	    	} catch (NombreInvalidoException e) {
	    	    errores.append("- ").append(e.getMessage()).append("\n");
	    	    lblNombreCreate.setForeground(Color.RED); 
	    	}
	    	
	    	//Validar Direccion
	    	try{   		
	    		Cliente.validarDireccion(txtDireccionCreate.getText());
	    		
	    		direccion = txtDireccionCreate.getText();
	    		lblDireccionCreate.setForeground(UIManager.getColor("Label.foreground"));

	    	}catch(UbicacionInvalidaException e) {
	        	    errores.append("- ").append(e.getMessage()).append("\n");
	        	    lblDireccionCreate.setForeground(Color.RED); 
	    		}
	
	    	//Validar numero de Identidad
	    	try { 
	    		
	    		// PRIMERO VERIFICAR SI EL NÚMERO YA EXISTE
	            for (Cliente c : EmpresaTelecomunicaciones.getInstancia().getClientes()) {
	                if (c instanceof PersonaNatural) {
	                    PersonaNatural pn = (PersonaNatural)c;
	                    if (pn.getNumId().equals(txtNumIdCreate.getText())) {

	                        throw new DuplicadosException("Ya existe una persona con este número de identidad");
	                    }
	                }
	            }
	            for(Representante r: EmpresaTelecomunicaciones.getInstancia().getRepresentantes()){
	            	if(r.getNumId().equals(txtNumIdCreate.getText())){
	            		 throw new DuplicadosException("Ya existe una persona con este número de identidad");
	            	}
	            	
	            	
	            }
	            	    	    
	    	    numId = txtNumIdCreate.getText();
	    	    PersonaNatural.validarNumId(numId);
	    	    lblNumIdCreate.setForeground(UIManager.getColor("Label.foreground"));
	    	    
	    	}catch(DuplicadosException e){

	    		errores.append("- ").append(e.getMessage()).append("\n");
	    	    lblNumIdCreate.setForeground(Color.RED);
	    		
	    	}catch (Exception e) {

	    	    errores.append("- ").append(e.getMessage()).append("\n");
	    	    lblNumIdCreate.setForeground(Color.RED);	    	    
	    	}

	    	
	    	provincia = (String) cbProvinciaCreate.getSelectedItem();
	    	municipio = (String) cbMunicipioCreate.getSelectedItem(); 
	    	
	    	//Asignar provincia y los municipios
	    	if (provincia == null || municipio == null) {
	    	    errores.append("- Seleccione provincia y municipio válidos\n");
	    	}
	 
	    	
	    	
	    	//Mensaje de error de Validacion
	    	if (errores.length() > 0) {
	    		
	    		JOptionPane.showMessageDialog(
	    			    null, 
	    			    "ERRORES EN LOS DATOS:\n" + 
	    			    "----------------------------\n" + 
	    			    errores.toString() + 
	    			    "\n----------------------------\n" + 
	    			    "Por favor, corrija los campos resaltados en rojo.", 
	    			    "Error ", 
	    			    JOptionPane.ERROR_MESSAGE
	    			);	    	} 
	    	else {

	    		if(servicioSeleccionado != null){
		    		try{
		    			
		    		//Usar los valores ya valido
		    		EmpresaTelecomunicaciones.getInstancia().agregarPersonaNatural(nombre,direccion, municipio, provincia, numId);		    	    
		    		creado = true;
		    	    // Limpiar campos después de creación exitosa
		            limpiarCampos();
		    		}catch(Exception e){
		    			// Manejo de errores
		                manejarError(e,"Error:"+e.getMessage());

		    			
		    			}
		    		}
	    		}
    		
    	return creado;
    }
    
    //Metodo para crear una PERSONA JURIDICA validando todos sus datos 
    public boolean crearPersonaJuridica(Servicio servicioSeleccionado, Representante representanteSeleccionado){
	   	
    	boolean creado = false;
    	
    	StringBuilder errores = new StringBuilder();
   	
    	//Atributos necesarios para crear a una Persona Natural
    	String nombre = null;
    	String direccion = null;
    	String organismo = null;
    	String provincia = null;
    	String municipio = null;

	    	//Validar Nombre
	    	try {
	    		
	    	    Cliente.validarNombre(txtNombreCreate.getText());
	    	    //Buscar nombres duplicados
	    	    for(Cliente c: EmpresaTelecomunicaciones.getInstancia().getClientes()){
	    	    	if(c instanceof PersonaJuridica){
	    	    		if(c.getNombre().equals(txtNombreCreate.getText())){
	    	    			throw new Exception("Esa Persona Jurídica ya existe en nuestro sistema");
	    	    		}
	    	    	}
	    	    	
	    	    }
	    	    
	    	    	
	    	    nombre = txtNombreCreate.getText();
	    	   lblNombreCreate.setForeground(UIManager.getColor("Label.foreground")); 
	    	   
	    	} catch (Exception e) {
	    	    errores.append("- ").append(e.getMessage()).append("\n");
	    	    lblNombreCreate.setForeground(Color.RED); 
	    	}
	    	
	    	//Validar Direccion
	    	try{   		
	    		Cliente.validarDireccion(txtDireccionCreate.getText());
	    		
	    		direccion = txtDireccionCreate.getText();
	    		lblDireccionCreate.setForeground(UIManager.getColor("Label.foreground"));

	    	}catch(UbicacionInvalidaException e) {
	        	    errores.append("- ").append(e.getMessage()).append("\n");
	        	    lblDireccionCreate.setForeground(Color.RED); 
	    		}
	    	
	    	//Validar organismo
	    	try {
	    		PersonaJuridica.validarOrganismo(txtOrganismoCreate.getText());
	    	    organismo = txtOrganismoCreate.getText();
	    	    lblOrganismoCreate.setForeground(UIManager.getColor("Label.foreground")); 
	    	   
	    	} catch (Exception e) {

	    	    errores.append("- ").append(e.getMessage()).append("\n");
	    	    lblOrganismoCreate.setForeground(Color.red);
	    	}
	
	    	provincia = (String) cbProvinciaCreate.getSelectedItem();
	    	municipio = (String) cbMunicipioCreate.getSelectedItem(); 
	    	
	    	//Asignar provincia y los municipios
	    	if (provincia == null || municipio == null) {
	    	    errores.append("- Seleccione provincia y municipio válidos\n");
	    	}
	    	
	    	
	    	//Mensaje de error de Validacion
	    	if (errores.length() > 0) {
	    		
	    		JOptionPane.showMessageDialog(
	    			    null, 
	    			    "ERRORES EN LOS DATOS:\n" + 
	    			    "----------------------------\n" + 
	    			    errores.toString() + 
	    			    "\n----------------------------\n" + 
	    			    "Por favor, corrija los campos resaltados en rojo.", 
	    			    "Error ", 
	    			    JOptionPane.ERROR_MESSAGE
	    			);	    	} 
	    	else {

	    		if(servicioSeleccionado != null && representanteSeleccionado != null){
		    		try{
		    			
		    		//Usar los valores ya valido
		    		EmpresaTelecomunicaciones.getInstancia().agregarPersonaJuridica(nombre,direccion, municipio, provincia, organismo,representanteSeleccionado);		    	    
		    		creado = true;
		    	    // Limpiar campos después de creación exitosa
		            limpiarCampos();
		    		}catch(Exception e){
		    			// Manejo de errores
		                manejarError(e,"Error:"+e.getMessage());

		    			
		    			}
		    		}
	    		}
    		
    	return creado;
    }
    
    //Metodo para crear una PERSONA JURIDICA validando todos sus datos 
    public boolean crearEntidadNoEstatal(Servicio servicioSeleccionado, Representante representanteSeleccionado){
	   	
    	boolean creado = false;
    	
    	StringBuilder errores = new StringBuilder();
   	
    	//Atributos necesarios para crear a una Persona Natural
    	String nombre = null;
    	String direccion = null;

	    	//Validar Nombre
	    	try {
	    		
	    	    Cliente.validarNombre(txtNombreCreate.getText());
	    	    //Buscar nombres duplicados
	    	    for(Cliente c: EmpresaTelecomunicaciones.getInstancia().getClientes()){
	    	    	if(c instanceof EntidadNoEstatal){
	    	    		if(c.getNombre().equals(txtNombreCreate.getText())){
	    	    			throw new Exception("Esa Entidad Estatal ya existe en nuestro sistema");
	    	    		}
	    	    	}
	    	    	
	    	    }
	    	    
	    	    	//
	    	    nombre = txtNombreCreate.getText();
	    	    lblNombreCreate.setForeground(UIManager.getColor("Label.foreground")); 
	    	   
	    	} catch (Exception e) {
	    	    errores.append("- ").append(e.getMessage()).append("\n");
	    	    lblNombreCreate.setForeground(Color.RED); 
	    	}
	    	
	    	//Validar Direccion
	    	try{   		
	    		Cliente.validarDireccion(txtDireccionCreate.getText());
	    		
	    		direccion = txtDireccionCreate.getText();
	    		lblDireccionCreate.setForeground(UIManager.getColor("Label.foreground"));

	    	}catch(UbicacionInvalidaException e) {
	        	    errores.append("- ").append(e.getMessage()).append("\n");
	        	    lblDireccionCreate.setForeground(Color.RED); 
	    		}
	    	    	
	    	
	    	
	    	//Mensaje de error de Validacion
	    	if (errores.length() > 0) {
	    		
	    		JOptionPane.showMessageDialog(
	    			    null, 
	    			    "ERRORES EN LOS DATOS:\n" + 
	    			    "----------------------------\n" + 
	    			    errores.toString() + 
	    			    "\n----------------------------\n" + 
	    			    "Por favor, corrija los campos resaltados en rojo.", 
	    			    "Error ", 
	    			    JOptionPane.ERROR_MESSAGE
	    			);	    	} 
	    	else {

	    		if(servicioSeleccionado != null && representanteSeleccionado != null){
		    		try{
		    			
		    		//Usar los valores ya valido
		    		EmpresaTelecomunicaciones.getInstancia().agregarEntidadNoEstatal(nombre,direccion,representanteSeleccionado);		    	    
		    		creado = true;
		    	    // Limpiar campos después de creación exitosa
		            limpiarCampos();
		    		}catch(Exception e){
		    			// Manejo de errores
		                manejarError(e,"Error:"+e.getMessage());

		    			
		    			}
		    		}
	    		}
    		
    	return creado;
    }
    
    
    private void manejarError(Exception e, String mensaje) {
        UIManager.put("OptionPane.messageFont", new Font("Serif", Font.PLAIN, 18));
        UIManager.put("OptionPane.buttonFont", new Font("Serif", Font.PLAIN, 16));
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    private void limpiarCampos() {
        txtNombreCreate.setText("");
        txtDireccionCreate.setText("");
        txtNumIdCreate.setText("");
        cbProvinciaCreate.setSelectedIndex(-1);
        cbMunicipioCreate.setSelectedIndex(-1);
    }
    
}