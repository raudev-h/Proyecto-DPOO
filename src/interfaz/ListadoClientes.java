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

public class ListadoClientes extends JDialog {
    // Componentes de la interfaz
    private JTable table;
    private ClienteTableModel tableModel;
    private static ListadoClientes instance;
    
    // Campos de edición
    private JPanel panelEdicion;
    private JTextField txtNombre;
    private JTextField txtDireccion;
    private JTextField txtMunicipio;
    private JTextField txtProvincia;
    private JTextField txtNumId;
    private JPanel panelPersonaNatural;
    private JTextField txtOrganismo;
    private JPanel panelPersonaJuridica;
    private JPanel panelEntidadNoEstatal;
    private JButton btnAceptar;
    private JButton btnCancelar;
    private Cliente clienteSeleccionado;
    private String nombreClienteSeleccionado;
    
    // Etiquetas para validación
    private JLabel lblNombre;
    private JLabel lblDireccion;
    private JLabel lblMunicipio;
    private JLabel lblProvincia;
    private JLabel lblNumId;
    private JLabel lblOrganismo;

    // Constructor privado para Singleton
    private ListadoClientes() {
        setBounds(100, 100, 1087, 684);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        setTitle("Listado de Clientes");
        
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

    // Inicialización de componentes principales
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

    // Inicialización del panel de edición
    private void initPanelEdicion() {
        panelEdicion = new JPanel();
        panelEdicion.setBorder(new LineBorder(new Color(0, 0, 0)));
        panelEdicion.setBounds(1074, 55, 350, 550);
        panelEdicion.setVisible(false);
        panelEdicion.setLayout(null);
        getContentPane().add(panelEdicion);
        
        // Componentes comunes
        JLabel lblEdicionDeCliente = new JLabel("Edición de Cliente");
        lblEdicionDeCliente.setFont(new Font("Serif", Font.BOLD, 21));
        lblEdicionDeCliente.setBounds(32, 12, 280, 28);
        panelEdicion.add(lblEdicionDeCliente);
        
        // Campos nombre y dirección
        lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Serif", Font.PLAIN, 19));
        lblNombre.setBounds(35, 70, 280, 20);
        panelEdicion.add(lblNombre);
        
        txtNombre = new JTextField();
        txtNombre.setBounds(35, 100, 280, 26);
        panelEdicion.add(txtNombre);
        txtNombre.setColumns(10);
        
        lblDireccion = new JLabel("Dirección");
        lblDireccion.setFont(new Font("Serif", Font.PLAIN, 19));
        lblDireccion.setBounds(35, 140, 280, 20);
        panelEdicion.add(lblDireccion);
        
        txtDireccion = new JTextField();
        txtDireccion.setBounds(35, 170, 280, 26);
        panelEdicion.add(txtDireccion);
        txtDireccion.setColumns(10);
        
        // Campos para ubicación (solo para PersonaNatural y PersonaJuridica)
        lblMunicipio = new JLabel("Municipio");
        lblMunicipio.setFont(new Font("Serif", Font.PLAIN, 19));
        lblMunicipio.setBounds(35, 210, 280, 20);
        panelEdicion.add(lblMunicipio);
        
        txtMunicipio = new JTextField();
        txtMunicipio.setBounds(35, 240, 280, 26);
        panelEdicion.add(txtMunicipio);
        txtMunicipio.setColumns(10);
        
        lblProvincia = new JLabel("Provincia");
        lblProvincia.setFont(new Font("Serif", Font.PLAIN, 19));
        lblProvincia.setBounds(35, 280, 280, 20);
        panelEdicion.add(lblProvincia);
        
        txtProvincia = new JTextField();
        txtProvincia.setBounds(35, 310, 280, 26);
        panelEdicion.add(txtProvincia);
        txtProvincia.setColumns(10);
        
        // Panel para PersonaNatural
        panelPersonaNatural = new JPanel();
        panelPersonaNatural.setBounds(0, 350, 350, 80);
        panelPersonaNatural.setLayout(null);
        panelPersonaNatural.setVisible(false);
        panelEdicion.add(panelPersonaNatural);
        
        lblNumId = new JLabel("Número de Identificación");
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
        
        // Acción para Cancelar
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cerrarPanelEdicion();
            }
        });
        
        // Acción para Aceptar
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarCliente();
            }
        });
    }

    // Método para cerrar el panel de edición
    private void cerrarPanelEdicion() {
        panelEdicion.setVisible(false);
        setSize(1090, 683);
        resetearValidaciones();
        clienteSeleccionado = null;
        nombreClienteSeleccionado = null;
    }

    // Método para mostrar la ventana
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

    // Muestra campos según el tipo de cliente
    private void mostrarCamposSegunTipo(Cliente cliente) {
        resetearValidaciones();
        
        panelPersonaNatural.setVisible(false);
        panelPersonaJuridica.setVisible(false);
        panelEntidadNoEstatal.setVisible(false);
        
        txtNombre.setText(cliente.getNombre());
        txtDireccion.setText(cliente.getDireccion());
        
        if (cliente instanceof PersonaNatural) {
            PersonaNatural pn = (PersonaNatural) cliente;
            txtMunicipio.setText(pn.getMunicipio());
            txtProvincia.setText(pn.getProvincia());
            txtNumId.setText(pn.getNumId());
            panelPersonaNatural.setVisible(true);
            
            // Mostrar campos de ubicación
            txtMunicipio.setVisible(true);
            txtProvincia.setVisible(true);
            lblMunicipio.setVisible(true);
            lblProvincia.setVisible(true);
        } 
        else if (cliente instanceof PersonaJuridica) {
            PersonaJuridica pj = (PersonaJuridica) cliente;
            txtMunicipio.setText(pj.getMunicipio());
            txtProvincia.setText(pj.getProvincia());
            txtOrganismo.setText(pj.getOrganismo());
            panelPersonaJuridica.setVisible(true);
            
            // Mostrar campos de ubicación
            txtMunicipio.setVisible(true);
            txtProvincia.setVisible(true);
            lblMunicipio.setVisible(true);
            lblProvincia.setVisible(true);
        } 
        else if (cliente instanceof EntidadNoEstatal) {
            panelEntidadNoEstatal.setVisible(true);
            
            // Ocultar campos de ubicación
            txtMunicipio.setVisible(false);
            txtProvincia.setVisible(false);
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
                if (!validarMunicipio(txtMunicipio.getText())) valido = false;
                if (!validarProvincia(txtProvincia.getText())) valido = false;
                if (!validarNumId(txtNumId.getText())) valido = false;
            } 
            else if (clienteSeleccionado instanceof PersonaJuridica) {
                if (!validarMunicipio(txtMunicipio.getText())) valido = false;
                if (!validarProvincia(txtProvincia.getText())) valido = false;
                if (!validarOrganismo(txtOrganismo.getText())) valido = false;
            }
            
            if (!valido) {
                throw new Exception("Por favor complete todos los campos obligatorios");
            }
            
            // Si todo está validado, proceder con la actualización
            String nombre = txtNombre.getText();
            String direccion = txtDireccion.getText();
            
            if (clienteSeleccionado instanceof PersonaNatural) {
                PersonaNatural pn = (PersonaNatural) clienteSeleccionado;
                pn.setNombre(nombre);
                pn.setDireccion(direccion);
                pn.setMunicipio(txtMunicipio.getText());
                pn.setProvincia(txtProvincia.getText());
                pn.setNumId(txtNumId.getText());
            } 
            else if (clienteSeleccionado instanceof PersonaJuridica) {
                PersonaJuridica pj = (PersonaJuridica) clienteSeleccionado;
                pj.setNombre(nombre);
                pj.setDireccion(direccion);
                pj.setMunicipio(txtMunicipio.getText());
                pj.setProvincia(txtProvincia.getText());
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
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Métodos de validación
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
    
    public boolean validarMunicipio(String municipio) {
        boolean valido = !municipio.isEmpty();
        lblMunicipio.setForeground(valido ? Color.BLACK : Color.RED);
        return valido;
    }
    
    public boolean validarProvincia(String provincia) {
        boolean valido = !provincia.isEmpty();
        lblProvincia.setForeground(valido ? Color.BLACK : Color.RED);
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
        lblMunicipio.setForeground(Color.BLACK);
        lblProvincia.setForeground(Color.BLACK);
        if (lblNumId != null) lblNumId.setForeground(Color.BLACK);
        if (lblOrganismo != null) lblOrganismo.setForeground(Color.BLACK);
    }

    // Método estático para abrir la ventana
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
                        "¿Está seguro que desea eliminar este cliente?", 
                        "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    
                    if (confirm == JOptionPane.YES_OPTION) {
                        boolean eliminado = EmpresaTelecomunicaciones.getInstancia()
                            .eliminarCliente(nombreCliente);
                        
                        if (eliminado) {
                            // Si el cliente eliminado es el mismo que se está editando, cerrar el panel
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
}