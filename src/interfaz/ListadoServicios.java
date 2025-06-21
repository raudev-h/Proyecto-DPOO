package interfaz;

import auxiliares.*;
import logica.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListadoServicios extends JDialog {
    private EmpresaTelecomunicaciones empresa;
    private JTabbedPane tabbedPane;
    private TelefonoFijoTableModel modelFijos;
    private TelefonoMovilTableModel modelMoviles;
    private CuentaNautaTableModel modelNauta;
    private JPanel panelFormulario; // Panel lateral para formulario
    private static ListadoServicios instance;

    private ListadoServicios() {
        empresa = EmpresaTelecomunicaciones.getInstancia();

        setTitle("Listado de Servicios");
        setBounds(100, 100, 1126, 662);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        setModal(true);

        // ===========================
        // PESTAÑAS CON TABLAS
        // ===========================
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Serif", Font.PLAIN, 18));

        // Crear modelos
        modelFijos = new TelefonoFijoTableModel();
        modelMoviles = new TelefonoMovilTableModel();
        modelNauta = new CuentaNautaTableModel();

        // Añadir cada pestaña con su tabla (dentro de JScrollPane)
        tabbedPane.addTab("Teléfonos Fijos", crearTabla(modelFijos));
        tabbedPane.addTab("Teléfonos Móviles", crearTabla(modelMoviles));
        tabbedPane.addTab("Cuentas Nauta", crearTabla(modelNauta));

        // ===========================
        // PANEL LATERAL PARA FORMULARIO
        // ===========================
        panelFormulario = new JPanel();
        panelFormulario.setPreferredSize(new Dimension(300, getHeight()));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Formulario"));
        panelFormulario.setVisible(false); // Oculto al inicio

        // ===========================
        // BOTÓN CREAR SERVICIO
        // ===========================
        JButton btnCrearServicio = new JButton("Crear Servicio");
        btnCrearServicio.setFont(new Font("Serif", Font.PLAIN, 18));
        btnCrearServicio.setPreferredSize(new Dimension(1, 40));
        btnCrearServicio.setMargin(new Insets(5, 10, 5, 10));
        btnCrearServicio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarFormulario();
            }
        });

        // ===========================
        // AGREGAR TODO AL DIALOG
        // ===========================
        add(tabbedPane, BorderLayout.CENTER);
        add(panelFormulario, BorderLayout.EAST);
        add(btnCrearServicio, BorderLayout.SOUTH);

        // Cargar datos iniciales
        cargarDatos();
    }

    private JScrollPane crearTabla(DefaultTableModel model) {
        JTable table = new JTable(model);
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

        // === Buscador ===
        JLabel lblBuscar = new JLabel("Buscar Titular:");
        final JTextField txtBuscar = new JTextField(15);
        final DefaultListModel<String> modeloLista = new DefaultListModel<String>();
        final JList<String> listaClientes = new JList<String>(modeloLista);
        listaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        for (Cliente c : empresa.getClientes()) {
            modeloLista.addElement(c.getNombre());
        }

        txtBuscar.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }

            public void filtrar() {
                String texto = txtBuscar.getText().toLowerCase();
                modeloLista.clear();
                for (Cliente c : empresa.getClientes()) {
                    if (c.getNombre().toLowerCase().contains(texto)) {
                        modeloLista.addElement(c.getNombre());
                    }
                }
            }
        });

        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
        panelFormulario.add(lblBuscar, gbc); row++;
        gbc.gridy = row;
        panelFormulario.add(txtBuscar, gbc); row++;
        gbc.gridy = row;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        panelFormulario.add(new JScrollPane(listaClientes), gbc); row++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0; gbc.weighty = 0;
        gbc.gridwidth = 1;

        // === Campos específicos ===
        final int index = tabbedPane.getSelectedIndex();
        final JTextField campoNumero = new JTextField(15);
        final JTextField campoMonto = new JTextField(15);
        final JTextField campoNick = new JTextField(15);

        if (index == 0) {
            panelFormulario.add(new JLabel("Número:"), gbc); row++;
            gbc.gridy = row; panelFormulario.add(campoNumero, gbc); row++;
        } else if (index == 1) {
            panelFormulario.add(new JLabel("Número:"), gbc); row++;
            gbc.gridy = row; panelFormulario.add(campoNumero, gbc); row++;
            panelFormulario.add(new JLabel("Monto a Pagar:"), gbc); row++;
            gbc.gridy = row; panelFormulario.add(campoMonto, gbc); row++;
        } else if (index == 2) {
            panelFormulario.add(new JLabel("Nick:"), gbc); row++;
            gbc.gridy = row; panelFormulario.add(campoNick, gbc); row++;
        }

        // === Botones ===
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");
        gbc.gridy = row; panelFormulario.add(btnGuardar, gbc);
        gbc.gridx = 1; panelFormulario.add(btnCancelar, gbc);

        // === Acción Botón Guardar ===
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombreSeleccionado = listaClientes.getSelectedValue();
                Cliente titular = null;
                for (Cliente c : empresa.getClientes()) {
                    if (c.getNombre().equals(nombreSeleccionado)) {
                        titular = c;
                        break;
                    }
                }
                if (titular == null) {
                    JOptionPane.showMessageDialog(ListadoServicios.this,
                        "Debe seleccionar un titular.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    if (index == 0) {
                        String numero = campoNumero.getText();
                        empresa.agregarTelefonoFijo(titular, numero);
                    } else if (index == 1) {
                        String numero = campoNumero.getText();
                        double monto = Double.parseDouble(campoMonto.getText());
                        empresa.agregarTelefonoMovil(titular, numero, monto);
                    } else if (index == 2) {
                        String nick = campoNick.getText();
                        empresa.crearCuentaNauta(titular, nick);
                    }
                    cargarDatos();
                    panelFormulario.setVisible(false);
                    setTablaSeleccionHabilitada(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ListadoServicios.this,
                        "Error al crear servicio: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // === Acción Botón Cancelar ===
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelFormulario.setVisible(false);
                setTablaSeleccionHabilitada(true);
            }
        });

        panelFormulario.setVisible(true);
        panelFormulario.revalidate();
        panelFormulario.repaint();

        // === Deshabilitar tabla activa ===
        setTablaSeleccionHabilitada(false);
    }


    	
    	// Método para que no puedas selecionar nada dentro del formulario
    	private void setTablaSeleccionHabilitada(boolean habilitada) {
    	    int index = tabbedPane.getSelectedIndex();
    	    JScrollPane scroll = (JScrollPane) tabbedPane.getComponentAt(index);
    	    JTable table = (JTable) scroll.getViewport().getView();
    	    table.setEnabled(habilitada);
    	}

    public static void abrirListadoServicio() {
        if (instance == null) {
            instance = new ListadoServicios();
            instance.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            instance.setVisible(true);
        } else {
            if (!instance.isVisible()) {
                instance.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null,
                        "La ventana del listado de servicios ya está abierta",
                        "Información",
                        JOptionPane.INFORMATION_MESSAGE);
                instance.toFront();
            }
        }
    }

    @Override
    public void dispose() {
        instance = null;
        super.dispose();
    }

    
}
