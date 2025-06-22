package interfaz;

import auxiliares.*;
import logica.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListadoServicios extends JDialog {
    private EmpresaTelecomunicaciones empresa;
    private JTabbedPane tabbedPane;
    private TelefonoFijoTableModel modelFijos;
    private TelefonoMovilTableModel modelMoviles;
    private CuentaNautaTableModel modelNauta;
    private JPanel panelFormulario;
    private JTable tablaBloqueada; // Para bloquear selección
    private static ListadoServicios instance;

    private ListadoServicios() {
        empresa = EmpresaTelecomunicaciones.getInstancia();
        setModal(true); // Bloquea toda la ventana mientras está abierta
        setTitle("Listado de Servicios");
        setBounds(100, 100, 1126, 662);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // PESTAÑAS CON TABLAS
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Serif", Font.PLAIN, 18));
        modelFijos = new TelefonoFijoTableModel();
        modelMoviles = new TelefonoMovilTableModel();
        modelNauta = new CuentaNautaTableModel();

        tabbedPane.addTab("Teléfonos Fijos", crearTabla(modelFijos));
        tabbedPane.addTab("Teléfonos Móviles", crearTabla(modelMoviles));
        tabbedPane.addTab("Cuentas Nauta", crearTabla(modelNauta));

        // PANEL FORMULARIO
        panelFormulario = new JPanel();
        panelFormulario.setPreferredSize(new Dimension(300, getHeight()));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Formulario"));
        panelFormulario.setLayout(new GridBagLayout());
        panelFormulario.setVisible(false);

        // BOTÓN CREAR SERVICIO
        JButton btnCrearServicio = new JButton("Crear Servicio");
        btnCrearServicio.setFont(new Font("Serif", Font.BOLD, 18));
        btnCrearServicio.setPreferredSize(new Dimension(1, 40));
        btnCrearServicio.setMargin(new Insets(5, 10, 5, 10));
        btnCrearServicio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarFormulario();
            }
        });

        add(tabbedPane, BorderLayout.CENTER);
        add(panelFormulario, BorderLayout.EAST);
        add(btnCrearServicio, BorderLayout.SOUTH);

        cargarDatos();
    }

    private JScrollPane crearTabla(DefaultTableModel model) {
        JTable table = new JTable(model);
        tablaBloqueada = table; // Referencia para bloquear
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFont(new Font("Serif", Font.PLAIN, 18));
        table.setRowHeight(25);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Serif", Font.PLAIN, 20));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
        return scrollPane;
    }

    private void cargarDatos() {
        modelFijos.cargarDatos(empresa.getTelefonosFijos());
        modelMoviles.cargarDatos(empresa.getTelefonosMoviles());
        modelNauta.cargarDatos(empresa.getCuentasNautas());
    }
    
    private void mostrarFormulario() {
        panelFormulario.removeAll();
        panelFormulario.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        int row = 0;

        // ========================
        // 1) Buscador de titular
        // ========================
        JLabel lblBuscar = new JLabel("Buscar Titular:");
        final JTextField txtBuscar = new JTextField(15);
        final DefaultListModel<Cliente> modeloLista = new DefaultListModel<>();
        final JList<Cliente> listaClientes = new JList<>(modeloLista);
        listaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        for (Cliente c : empresa.getClientes()) {
            modeloLista.addElement(c);
        }

        txtBuscar.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }

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
        
        // Declarar los campos de texto como final para que puedan ser accedidos en el ActionListener
        final JTextField txtNumeroFijo = new JTextField(15);
        final JTextField txtNumeroMovil = new JTextField(15);
        final JTextField txtMonto = new JTextField(15);
        final JTextField txtNick = new JTextField(15);

        // Determinar qué pestaña está seleccionada
        final int pestañaSeleccionada = tabbedPane.getSelectedIndex();
        
        // Campos específicos según la pestaña
        final JComboBox<String> comboFijos = new JComboBox<>();
        final JComboBox<TelefonoMovil> comboMoviles = new JComboBox<>();
        final JLabel lblMonto = new JLabel("Monto: -");

        if (pestañaSeleccionada == 0) { // Teléfonos Fijos
            JLabel lblFijo = new JLabel("Seleccionar Teléfono Fijo:");
            
            // Llenar comboBox con teléfonos fijos disponibles
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
            
            // Llenar comboBox con teléfonos móviles disponibles
            for (Servicio s : empresa.getServiciosDisponibles()) {
                if (s instanceof TelefonoMovil) {
                    TelefonoMovil tm = (TelefonoMovil) s;
                    if (tm.getTitular() == null) { // solo disponibles
                        comboMoviles.addItem(tm);
                    }
                }
            }

            // Actualizar monto al seleccionar un teléfono móvil
            comboMoviles.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    TelefonoMovil seleccionado = (TelefonoMovil) comboMoviles.getSelectedItem();
                    if (seleccionado != null) {
                        lblMonto.setText("Monto: $" + seleccionado.getMontoApagar());
                    } else {
                        lblMonto.setText("Monto: -");
                    }
                }
            });


            
            // Mostrar monto del primer teléfono si existe
            if (comboMoviles.getItemCount() > 0) {
                TelefonoMovil primero = (TelefonoMovil) comboMoviles.getItemAt(0);
                lblMonto.setText("Monto: $" + primero.getMontoApagar());
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
            
            gbc.gridx = 0; gbc.gridy = row; panelFormulario.add(lblNick, gbc);
            row++;
            gbc.gridy = row; gbc.gridwidth = 2;
            panelFormulario.add(txtNick, gbc);
            row++;
            gbc.gridwidth = 1;
        }

        // ========================
        // Botones Guardar y Cancelar
        // ========================
        JButton btnGuardar = new JButton("Guardar");
        gbc.gridx = 0; gbc.gridy = row;
        panelFormulario.add(btnGuardar, gbc);

        JButton btnCancelar = new JButton("Cancelar");
        gbc.gridx = 1; gbc.gridy = row;
        panelFormulario.add(btnCancelar, gbc);

        // ========================
        // BLOQUEAR TABLA Y PESTAÑAS
        // ========================
        tablaBloqueada.setEnabled(false);
        tabbedPane.setEnabled(false);

        // ========================
        // Acción Guardar
        // ========================
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Cliente titular = listaClientes.getSelectedValue();
                if (titular == null) {
                    JOptionPane.showMessageDialog(ListadoServicios.this,
                            "Debe seleccionar un titular.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
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
                        if(nick.isEmpty()) {
                            throw new IllegalArgumentException("Debe ingresar un nick para la cuenta");
                        }
                        empresa.crearCuentaNauta(titular, nick);
                    }

                    cargarDatos();
                    panelFormulario.setVisible(false);
                    tablaBloqueada.setEnabled(true);
                    tabbedPane.setEnabled(true);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ListadoServicios.this,
                            "Error al asignar servicio: " + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // ========================
        // Acción Cancelar
        // ========================
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelFormulario.setVisible(false);
                tablaBloqueada.setEnabled(true);
                tabbedPane.setEnabled(true);
            }
        });

        // ========================
        // FINAL: Mostrar
        // ========================
        panelFormulario.setVisible(true);
        panelFormulario.revalidate();
        panelFormulario.repaint();
    }
    // TODO


    @Override
    public void dispose() {
        instance = null;
        super.dispose();
    }

    public static void abrirListadoServicio() {
        if (instance == null) {
            instance = new ListadoServicios();
            instance.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            instance.setVisible(true);
        } else {
            if (!instance.isVisible()) instance.setVisible(true);
            else JOptionPane.showMessageDialog(null, "La ventana ya está abierta");
            instance.toFront();
        }
    }

    public static void main(String[] args) {
        abrirListadoServicio();
    }
}
