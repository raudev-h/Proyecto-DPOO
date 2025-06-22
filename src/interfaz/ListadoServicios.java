package interfaz;

import auxiliares.*;
import logica.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
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

    private ListadoServicios() {
        empresa = EmpresaTelecomunicaciones.getInstancia();
        setModal(true);
        setTitle("Listado de Servicios");
        setBounds(100, 100, 1126, 662);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Pestañas con tablas
        tabbedPane = new JTabbedPane();
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
        panelFormulario.setPreferredSize(new Dimension(300, getHeight()));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Formulario"));
        panelFormulario.setLayout(new GridBagLayout());
        panelFormulario.setVisible(false);

        // Botón Crear Servicio
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
        configurarMenuContextual();
    }

    private JScrollPane crearTabla(DefaultTableModel model) {
        tablaBloqueada = new JTable(model); // Asigna a la variable de instancia
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
                        eliminarServicio(tabla, selectedRow);
                    } else {
                        JOptionPane.showMessageDialog(ListadoServicios.this,
                            "Por favor seleccione un servicio para eliminar",
                            "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
            
            popupMenu.add(menuEditar);
            popupMenu.add(menuEliminar);
            
            tabla.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    mostrarMenuSiEsClickDerecho(e);
                }
                
                @Override
                public void mouseReleased(MouseEvent e) {
                    mostrarMenuSiEsClickDerecho(e);
                }
                
                private void mostrarMenuSiEsClickDerecho(MouseEvent e) {
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
        
        switch(tabIndex) {
            case 0: return modelFijos.getServicioAt(row);
            case 1: return modelMoviles.getServicioAt(row);
            case 2: return modelNauta.getServicioAt(row);
            default: return null;
        }
    }

    private void editarServicio(final JTable tabla, final int row) {
        Servicio servicio = obtenerServicioSeleccionado(tabla, row);
        if (servicio != null) {
            // Implementar lógica específica de edición
            JOptionPane.showMessageDialog(this,
                "Editar servicio: " + servicio.getClass().getSimpleName(),
                "Edición", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void eliminarServicio(final JTable tabla, final int row) {
        final Servicio servicio = obtenerServicioSeleccionado(tabla, row);
        if (servicio != null) {
            UIManager.put("OptionPane.messageFont", new Font("Serif", Font.BOLD, 20));
            UIManager.put("OptionPane.buttonFont", new Font("Serif", Font.BOLD, 18));
            
            int confirm = JOptionPane.showConfirmDialog(ListadoServicios.this,
                "¿Está seguro que desea eliminar este servicio?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            
            if (confirm == JOptionPane.YES_OPTION) {
                boolean eliminado = false;
                
                if (servicio instanceof TelefonoFijo) {
                    eliminado = empresa.eliminarTelefonoFIjo(((TelefonoFijo)servicio).getNumero());
                } else if (servicio instanceof TelefonoMovil) {
                    eliminado = empresa.eliminarTelefonoMovil(((TelefonoMovil)servicio).getNumero());
                } else if (servicio instanceof CuentaNauta) {
                    eliminado = empresa.eliminarCuentaNauta(((CuentaNauta)servicio).getNick());
                }
                
                if (eliminado) {
                    cargarDatos();
                    JOptionPane.showMessageDialog(ListadoServicios.this,
                        "Servicio eliminado correctamente",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(ListadoServicios.this,
                        "No se pudo eliminar el servicio",
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void cargarDatos() {
        modelFijos.cargarDatos(empresa.getTelefonosFijos());
        modelMoviles.cargarDatos(empresa.getTelefonosMoviles());
        modelNauta.cargarDatos(empresa.getCuentasNautas());
    }

// TODO
    
    private void mostrarFormulario() {
        panelFormulario.removeAll();
        panelFormulario.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        int row = 0;

        // Obtener la tabla actual según la pestaña seleccionada
        final JScrollPane scrollPane = (JScrollPane) tabbedPane.getSelectedComponent();
        final JTable tablaActual = (JTable) scrollPane.getViewport().getView();

        // ========================
        // 1) Buscador de titular
        // ========================
        JLabel lblBuscar = new JLabel("Buscar Titular:");
        final JTextField txtBuscar = new JTextField(15);
        final DefaultListModel<Cliente> modeloLista = new DefaultListModel<Cliente>();
        final JList<Cliente> listaClientes = new JList<Cliente>(modeloLista);
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
        final JComboBox<TelefonoMovil> comboMoviles = new JComboBox<TelefonoMovil>();
        final JLabel lblMonto = new JLabel("Monto: -");
        final JTextField txtNick = new JTextField(15);

        // Determinar qué pestaña está seleccionada
        final int pestañaSeleccionada = tabbedPane.getSelectedIndex();

        // ========================
        // Campos específicos según la pestaña
        // ========================
        if (pestañaSeleccionada == 0) { // Teléfonos Fijos
            JLabel lblFijo = new JLabel("Seleccionar Teléfono Fijo:");
            
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
        tablaActual.setEnabled(false);
        tablaActual.setFocusable(false);
        tabbedPane.setEnabled(false);
        tabbedPane.setFocusable(false);

        // ========================
        // Acción Guardar
        // ========================
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente titular = listaClientes.getSelectedValue();
                if (titular == null) {
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
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ListadoServicios.this,
                        "Error al asignar servicio: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // ========================
        // Acción Cancelar
        // ========================
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelFormulario.setVisible(false);
                tablaActual.setEnabled(true);
                tablaActual.setFocusable(true);
                tabbedPane.setEnabled(true);
                tabbedPane.setFocusable(true);
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

}