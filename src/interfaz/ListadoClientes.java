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
    
    // Campos de edici�n
    private JPanel panelEdicion;
    private JTextField txtNombre;
    private JTextField txtDireccion;
    private JComboBox<String> cbMunicipio;
    private JComboBox<String> cbProvincia;
    private JTextField txtNumId;
    private JPanel panelPersonaNatural;
    private JTextField txtOrganismo;
    private JPanel panelPersonaJuridica;
    private JPanel panelEntidadNoEstatal;
    private JButton btnAceptar;
    private JButton btnCancelar;
    private Cliente clienteSeleccionado;
    private String nombreClienteSeleccionado;
    
    // Etiquetas para validaci�n
    private JLabel lblNombre;
    private JLabel lblDireccion;
    private JLabel lblMunicipio;
    private JLabel lblProvincia;
    private JLabel lblNumId;
    private JLabel lblOrganismo;

    // Mapa de provincias y municipios de Cuba
    private static final Map<String, String[]> PROVINCIAS_MUNICIPIOS = new LinkedHashMap<String, String[]>() {{
        put("Pinar del R�o", new String[]{"Consolaci�n del Sur", "Guane", "La Palma", "Los Palacios", 
            "Mantua", "Minas de Matahambre", "Pinar del R�o", "San Juan y Mart�nez", 
            "San Luis", "Sandino", "Vi�ales"});
        put("Artemisa", new String[]{"Alqu�zar", "Artemisa", "Bah�a Honda", "Bauta", 
            "Caimito", "Candelaria", "Guanajay", "G�ira de Melena", "Mariel", 
            "San Antonio de los Ba�os", "San Crist�bal"});
        put("La Habana", new String[]{"Arroyo Naranjo", "Boyeros", "Centro Habana", "Cerro", 
            "Cotorro", "Diez de Octubre", "Guanabacoa", "La Habana del Este", 
            "La Habana Vieja", "La Lisa", "Marianao", "Playa", "Plaza de la Revoluci�n", 
            "Regla", "San Miguel del Padr�n"});
        put("Mayabeque", new String[]{"Bataban�", "Bejucal", "G�ines", "Jaruco", 
            "Madruga", "Melena del Sur", "Nueva Paz", "Quivic�n", 
            "San Jos� de las Lajas", "San Nicol�s", "Santa Cruz del Norte"});
        put("Matanzas", new String[]{"Calimete", "C�rdenas", "Ciudad de Matanzas", "Col�n", 
            "Jag�ey Grande", "Jovellanos", "Limonar", "Los Arabos", 
            "Mart�", "Pedro Betancourt", "Perico", "Uni�n de Reyes", "Varadero"});
        put("Cienfuegos", new String[]{"Abreus", "Aguada de Pasajeros", "Cienfuegos", "Cruces", 
            "Cumanayagua", "Lajas", "Palmira", "Rodas"});
        put("Villa Clara", new String[]{"Caibari�n", "Camajuan�", "Cifuentes", "Corralillo", 
            "Encrucijada", "Manicaragua", "Placetas", "Quemado de G�ines", 
            "Ranchuelo", "Remedios", "Sagua la Grande", "Santa Clara", "Santo Domingo"});
        put("Sancti Sp�ritus", new String[]{"Cabaigu�n", "Fomento", "Jatibonico", "La Sierpe", 
            "Sancti Sp�ritus", "Taguasco", "Trinidad", "Yaguajay"});
        put("Ciego de �vila", new String[]{"Baragu�", "Bolivia", "Chambas", "Ciego de �vila", 
            "Ciro Redondo", "Florencia", "Majagua", "Mor�n", "Primero de Enero", "Venezuela"});
        put("Camag�ey", new String[]{"Camag�ey", "Carlos M. de C�spedes", "Esmeralda", "Florida", 
            "Gu�imaro", "Jimaguay�", "Minas", "Najasa", "Nuevitas", "Santa Cruz del Sur", 
            "Sibanic�", "Sierra de Cubitas", "Vertientes"});
        put("Las Tunas", new String[]{"Amancio", "Colombia", "Jes�s Men�ndez", "Jobabo", 
            "Las Tunas", "Majibacoa", "Manat�", "Puerto Padre"});
        put("Holgu�n", new String[]{"Antilla", "B�guanos", "Banes", "Cacocum", 
            "Calixto Garc�a", "Cueto", "Frank Pa�s", "Gibara", 
            "Holgu�n", "Mayar�", "Moa", "Rafael Freyre", "Sagua de T�namo", "Urbano Noris"});
        put("Granma", new String[]{"Bartolom� Mas�", "Bayamo", "Buey Arriba", "Campechuela", 
            "Cauto Cristo", "Guisa", "Jiguan�", "Manzanillo", 
            "Media Luna", "Niquero", "Pil�n", "R�o Cauto", "Yara"});
        put("Santiago de Cuba", new String[]{"Contramaestre", "Guam�", "Mella", "Palma Soriano", 
            "San Luis", "Santiago de Cuba", "Segundo Frente", "Songo-La Maya", "Tercer Frente"});
        put("Guant�namo", new String[]{"Baracoa", "Caimanera", "El Salvador", "Guant�namo", 
            "Im�as", "Mais�", "Manuel Tames", "Niceto P�rez", "San Antonio del Sur", "Yateras"});
        put("Isla de la Juventud", new String[]{"Isla de la Juventud"});
    }};

    // Constructor privado para Singleton
    private ListadoClientes() {
        setBounds(100, 100, 1087, 684);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        setTitle("Listado de Clientes");
        
        initComponents();
        configurarMenuContextual();
    }

    // M�todo Singleton para obtener la instancia
    public static synchronized ListadoClientes getInstance() {
        if (instance == null) {
            instance = new ListadoClientes();
        }
        return instance;
    }

    // Inicializaci�n de componentes principales
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
        
        initPanelEdicion();
    }

    // Inicializaci�n del panel de edici�n
    private void initPanelEdicion() {
        panelEdicion = new JPanel();
        panelEdicion.setBorder(new LineBorder(new Color(0, 0, 0)));
        panelEdicion.setBounds(1074, 55, 350, 550);
        panelEdicion.setVisible(false);
        panelEdicion.setLayout(null);
        getContentPane().add(panelEdicion);
        
        // Componentes comunes
        JLabel lblEdicionDeCliente = new JLabel("Edici�n de Cliente");
        lblEdicionDeCliente.setFont(new Font("Serif", Font.BOLD, 21));
        lblEdicionDeCliente.setBounds(32, 12, 280, 28);
        panelEdicion.add(lblEdicionDeCliente);
        
        // Campos nombre y direcci�n
        lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Serif", Font.PLAIN, 19));
        lblNombre.setBounds(35, 70, 280, 20);
        panelEdicion.add(lblNombre);
        
        txtNombre = new JTextField();
        txtNombre.setBounds(35, 100, 280, 26);
        panelEdicion.add(txtNombre);
        txtNombre.setColumns(10);
        
        lblDireccion = new JLabel("Direcci�n");
        lblDireccion.setFont(new Font("Serif", Font.PLAIN, 19));
        lblDireccion.setBounds(35, 140, 280, 20);
        panelEdicion.add(lblDireccion);
        
        txtDireccion = new JTextField();
        txtDireccion.setBounds(35, 170, 280, 26);
        panelEdicion.add(txtDireccion);
        txtDireccion.setColumns(10);
        
        // Campos para ubicaci�n (solo para PersonaNatural y PersonaJuridica)
        lblMunicipio = new JLabel("Municipio");
        lblMunicipio.setFont(new Font("Serif", Font.PLAIN, 19));
        lblMunicipio.setBounds(35, 210, 280, 20);
        panelEdicion.add(lblMunicipio);
        
        cbMunicipio = new JComboBox<String>();
        cbMunicipio.setBounds(35, 240, 280, 26);
        panelEdicion.add(cbMunicipio);
        
        lblProvincia = new JLabel("Provincia");
        lblProvincia.setFont(new Font("Serif", Font.PLAIN, 19));
        lblProvincia.setBounds(35, 280, 280, 20);
        panelEdicion.add(lblProvincia);
        
        cbProvincia = new JComboBox<String>();
        cbProvincia.setBounds(35, 310, 280, 26);
        // Cargar provincias
        for (String provincia : PROVINCIAS_MUNICIPIOS.keySet()) {
            cbProvincia.addItem(provincia);
        }
        // Establecer La Habana como selecci�n por defecto
        cbProvincia.setSelectedItem("La Habana");
        // Cargar municipios de la provincia seleccionada
        cargarMunicipios((String) cbProvincia.getSelectedItem());
        
        // Listener para cuando cambie la provincia
        cbProvincia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String provinciaSeleccionada = (String) cbProvincia.getSelectedItem();
                cargarMunicipios(provinciaSeleccionada);
            }
        });
        
        panelEdicion.add(cbProvincia);
        
        // Panel para PersonaNatural
        panelPersonaNatural = new JPanel();
        panelPersonaNatural.setBounds(0, 350, 350, 80);
        panelPersonaNatural.setLayout(null);
        panelPersonaNatural.setVisible(false);
        panelEdicion.add(panelPersonaNatural);
        
        lblNumId = new JLabel("N�mero de Identificaci�n");
        lblNumId.setFont(new Font("Serif", Font.PLAIN, 19));
        lblNumId.setBounds(35, 0, 280, 20);
        panelPersonaNatural.add(lblNumId);
        
        txtNumId = new JTextField();
        txtNumId.setBounds(35, 30, 280, 26);
        panelPersonaNatural.add(txtNumId);
        txtNumId.setColumns(10);
        
        // Panel para PersonaJuridica
        panelPersonaJuridica = new JPanel();
        panelPersonaJuridica.setBounds(0, 350, 350, 80);
        panelPersonaJuridica.setLayout(null);
        panelPersonaJuridica.setVisible(false);
        panelEdicion.add(panelPersonaJuridica);
        
        lblOrganismo = new JLabel("Organismo");
        lblOrganismo.setFont(new Font("Serif", Font.PLAIN, 19));
        lblOrganismo.setBounds(35, 0, 280, 20);
        panelPersonaJuridica.add(lblOrganismo);
        
        txtOrganismo = new JTextField();
        txtOrganismo.setBounds(35, 30, 280, 26);
        panelPersonaJuridica.add(txtOrganismo);
        txtOrganismo.setColumns(10);
        
        // Panel para EntidadNoEstatal (sin campos adicionales)
        panelEntidadNoEstatal = new JPanel();
        panelEntidadNoEstatal.setBounds(0, 350, 350, 50);
        panelEntidadNoEstatal.setLayout(null);
        panelEntidadNoEstatal.setVisible(false);
        panelEdicion.add(panelEntidadNoEstatal);
        
        // Botones
        btnAceptar = new JButton("Aceptar");
        btnAceptar.setForeground(new Color(255, 255, 255));
        btnAceptar.setBackground(new Color(0, 0, 153));
        btnAceptar.setFont(new Font("Serif", Font.PLAIN, 19));
        btnAceptar.setBounds(35, 450, 120, 30);
        panelEdicion.add(btnAceptar);
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(255, 255, 255));
        btnCancelar.setForeground(new Color(0, 0, 153));
        btnCancelar.setFont(new Font("Serif", Font.PLAIN, 19));
        btnCancelar.setBounds(195, 450, 120, 30);
        panelEdicion.add(btnCancelar);
        
        // Acci�n para Cancelar
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cerrarPanelEdicion();
            }
        });
        
        // Acci�n para Aceptar
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarCliente();
            }
        });
    }

    // M�todo para cargar municipios seg�n la provincia seleccionada
    private void cargarMunicipios(String provincia) {
        cbMunicipio.removeAllItems();
        String[] municipios = PROVINCIAS_MUNICIPIOS.get(provincia);
        if (municipios != null) {
            for (String municipio : municipios) {
                cbMunicipio.addItem(municipio);
            }
        }
    }

    // M�todo para cerrar el panel de edici�n
    private void cerrarPanelEdicion() {
        panelEdicion.setVisible(false);
        setSize(1090, 683);
        resetearValidaciones();
        clienteSeleccionado = null;
        nombreClienteSeleccionado = null;
    }

    // M�todo para mostrar la ventana
    public void mostrarVentana() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
        tableModel.cargarClientes();
    }

    @Override
    public void dispose() {
        instance = null;
        super.dispose();
    }

    // Muestra campos seg�n el tipo de cliente
    private void mostrarCamposSegunTipo(Cliente cliente) {
        resetearValidaciones();
        
        panelPersonaNatural.setVisible(false);
        panelPersonaJuridica.setVisible(false);
        panelEntidadNoEstatal.setVisible(false);
        
        txtNombre.setText(cliente.getNombre());
        txtDireccion.setText(cliente.getDireccion());
        
        if (cliente instanceof PersonaNatural) {
            PersonaNatural pn = (PersonaNatural) cliente;
            cbProvincia.setSelectedItem(pn.getProvincia());
            cbMunicipio.setSelectedItem(pn.getMunicipio());
            txtNumId.setText(pn.getNumId());
            panelPersonaNatural.setVisible(true);
            
            // Mostrar campos de ubicaci�n
            cbMunicipio.setVisible(true);
            cbProvincia.setVisible(true);
            lblMunicipio.setVisible(true);
            lblProvincia.setVisible(true);
        } 
        else if (cliente instanceof PersonaJuridica) {
            PersonaJuridica pj = (PersonaJuridica) cliente;
            cbProvincia.setSelectedItem(pj.getProvincia());
            cbMunicipio.setSelectedItem(pj.getMunicipio());
            txtOrganismo.setText(pj.getOrganismo());
            panelPersonaJuridica.setVisible(true);
            
            // Mostrar campos de ubicaci�n
            cbMunicipio.setVisible(true);
            cbProvincia.setVisible(true);
            lblMunicipio.setVisible(true);
            lblProvincia.setVisible(true);
        } 
        else if (cliente instanceof EntidadNoEstatal) {
            panelEntidadNoEstatal.setVisible(true);
            
            // Ocultar campos de ubicaci�n
            cbMunicipio.setVisible(false);
            cbProvincia.setVisible(false);
            lblMunicipio.setVisible(false);
            lblProvincia.setVisible(false);
        }
    }

    // Actualiza los datos del cliente
    private void actualizarCliente() {
        if (clienteSeleccionado == null) return;
        
        try {
            // Verificar si el cliente sigue existente
            Cliente clienteActual = EmpresaTelecomunicaciones.getInstancia().buscarCliente(nombreClienteSeleccionado);
            if (clienteActual == null) {
                JOptionPane.showMessageDialog(this, 
                    "El cliente ha sido eliminado y no puede ser editado", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                cerrarPanelEdicion();
                return;
            }
            
            // Validar campos comunes
            boolean valido = true;
            
            if (!validarNombre(txtNombre.getText())) valido = false;
            if (!validarDireccion(txtDireccion.getText())) valido = false;
            
            if (clienteSeleccionado instanceof PersonaNatural) {
                if (!validarNumId(txtNumId.getText())) valido = false;
            } 
            else if (clienteSeleccionado instanceof PersonaJuridica) {
                if (!validarOrganismo(txtOrganismo.getText())) valido = false;
            }
            
            if (!valido) {
                throw new Exception("Por favor complete todos los campos obligatorios");
            }
            
            // Si todo est� validado, proceder con la actualizaci�n
            String nombre = txtNombre.getText();
            String direccion = txtDireccion.getText();
            String provincia = (String) cbProvincia.getSelectedItem();
            String municipio = (String) cbMunicipio.getSelectedItem();
            
            if (clienteSeleccionado instanceof PersonaNatural) {
                PersonaNatural pn = (PersonaNatural) clienteSeleccionado;
                pn.setNombre(nombre);
                pn.setDireccion(direccion);
                pn.setMunicipio(municipio);
                pn.setProvincia(provincia);
                pn.setNumId(txtNumId.getText());
            } 
            else if (clienteSeleccionado instanceof PersonaJuridica) {
                PersonaJuridica pj = (PersonaJuridica) clienteSeleccionado;
                pj.setNombre(nombre);
                pj.setDireccion(direccion);
                pj.setMunicipio(municipio);
                pj.setProvincia(provincia);
                pj.setOrganismo(txtOrganismo.getText());
            } 
            else if (clienteSeleccionado instanceof EntidadNoEstatal) {
                EntidadNoEstatal ene = (EntidadNoEstatal) clienteSeleccionado;
                ene.setNombre(nombre);
                ene.setDireccion(direccion);
            }
            
            // Actualizar la tabla
            tableModel.cargarClientes();
            cerrarPanelEdicion();
            
            JOptionPane.showMessageDialog(this, "Cliente actualizado correctamente", 
                "�xito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // M�todos de validaci�n
    public boolean validarNombre(String nombre) {
        boolean valido = !nombre.isEmpty();
        lblNombre.setForeground(valido ? Color.BLACK : Color.RED);
        return valido;
    }
    
    public boolean validarDireccion(String direccion) {
        boolean valido = !direccion.isEmpty();
        lblDireccion.setForeground(valido ? Color.BLACK : Color.RED);
        return valido;
    }
    
    public boolean validarNumId(String numId) {
        boolean valido = !numId.isEmpty();
        lblNumId.setForeground(valido ? Color.BLACK : Color.RED);
        return valido;
    }
    
    public boolean validarOrganismo(String organismo) {
        boolean valido = !organismo.isEmpty();
        lblOrganismo.setForeground(valido ? Color.BLACK : Color.RED);
        return valido;
    }
    
    private void resetearValidaciones() {
        lblNombre.setForeground(Color.BLACK);
        lblDireccion.setForeground(Color.BLACK);
        if (lblNumId != null) lblNumId.setForeground(Color.BLACK);
        if (lblOrganismo != null) lblOrganismo.setForeground(Color.BLACK);
    }

    // M�todo est�tico para abrir la ventana
    public static void abrirListadoClientes() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
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

    // Configuraci�n del men� contextual
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
                        panelEdicion.setVisible(true);
                        setSize(1478, 683);
                        mostrarCamposSegunTipo(clienteSeleccionado);
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
                        "�Est� seguro que desea eliminar este cliente?", 
                        "Confirmar eliminaci�n", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    
                    if (confirm == JOptionPane.YES_OPTION) {
                        boolean eliminado = EmpresaTelecomunicaciones.getInstancia()
                            .eliminarCliente(nombreCliente);
                        
                        if (eliminado) {
                            // Si el cliente eliminado es el mismo que se est� editando, cerrar el panel
                            if (nombreClienteSeleccionado != null && 
                                nombreClienteSeleccionado.equals(nombreCliente)) {
                                cerrarPanelEdicion();
                            }
                            
                            tableModel.cargarClientes();
                            JOptionPane.showMessageDialog(null, 
                                "Cliente eliminado correctamente", 
                                "�xito", JOptionPane.INFORMATION_MESSAGE);
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
}