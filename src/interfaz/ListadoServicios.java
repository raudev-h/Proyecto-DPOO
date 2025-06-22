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
        setModal(false); // Bloquea toda la ventana mientras está abierta
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

        // === Buscador ===
        JLabel lblBuscar = new JLabel("Buscar Titular:");
        final JTextField txtBuscar = new JTextField(15);
        final DefaultListModel<Cliente> modeloLista = new DefaultListModel<Cliente>();
        final JList<Cliente> listaClientes = new JList<Cliente>(modeloLista);
        listaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        for (Cliente c : empresa.getClientes()) modeloLista.addElement(c);

        txtBuscar.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }

            public void filtrar() {
                String texto = txtBuscar.getText().toLowerCase();
                modeloLista.clear();
                for (Cliente c : empresa.getClientes()) {
                    if (c.toString().toLowerCase().contains(texto)) modeloLista.addElement(c);
                }
            }
        });

        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
        panelFormulario.add(lblBuscar, gbc); row++;
        gbc.gridy = row; panelFormulario.add(txtBuscar, gbc); row++;
        gbc.gridy = row; gbc.fill = GridBagConstraints.BOTH; gbc.weightx = 1; gbc.weighty = 1;
        panelFormulario.add(new JScrollPane(listaClientes), gbc); row++;
        gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 0; gbc.weighty = 0; gbc.gridwidth = 1;

        // === Campos ===
        final int index = tabbedPane.getSelectedIndex();
        final JLabel lblNumero = new JLabel("Número:");
        final JTextField campoNumero = new JTextField(15);
        final JLabel lblMonto = new JLabel("Monto a Pagar:");
        final JTextField campoMonto = new JTextField(15);
        final JLabel lblNick = new JLabel("Nick:");
        final JTextField campoNick = new JTextField(15);

        // Filtros de entrada
        ((javax.swing.text.AbstractDocument) campoNumero.getDocument()).setDocumentFilter(new NumericDocumentFilter(8));
        ((javax.swing.text.AbstractDocument) campoMonto.getDocument()).setDocumentFilter(new NumericDocumentFilter(10));

        if (index == 0) {
            gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1;
            panelFormulario.add(lblNumero, gbc);
            gbc.gridx = 1; panelFormulario.add(campoNumero, gbc);
            row++;
        } else if (index == 1) {
            gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1;
            panelFormulario.add(lblNumero, gbc);
            gbc.gridx = 1; panelFormulario.add(campoNumero, gbc);
            row++;

            gbc.gridx = 0; gbc.gridy = row;
            panelFormulario.add(lblMonto, gbc);
            gbc.gridx = 1; panelFormulario.add(campoMonto, gbc);
            row++;
        } else if (index == 2) {
            gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1;
            panelFormulario.add(lblNick, gbc);
            gbc.gridx = 1; panelFormulario.add(campoNick, gbc);
            row++;
        }

        // === Botones ===
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        gbc.gridx = 0; gbc.gridy = row; panelFormulario.add(btnGuardar, gbc);
        gbc.gridx = 1; panelFormulario.add(btnCancelar, gbc);

        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Cliente titular = listaClientes.getSelectedValue();
                boolean valido = true;

                if (titular == null) {
                    JOptionPane.showMessageDialog(ListadoServicios.this, "Debe seleccionar un titular.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (index == 0 || index == 1) {
                    String num = campoNumero.getText().trim();
                    if (num.length() != 8) {
                        lblNumero.setForeground(Color.RED);
                        valido = false;
                    } else {
                        lblNumero.setForeground(Color.BLACK);
                    }
                }

                if (index == 1) {
                    String montoTxt = campoMonto.getText().trim();
                    try {
                        double m = Double.parseDouble(montoTxt);
                        if (m <= 0) {
                            lblMonto.setForeground(Color.RED);
                            valido = false;
                        } else {
                            lblMonto.setForeground(Color.BLACK);
                        }
                    } catch (NumberFormatException ex) {
                        lblMonto.setForeground(Color.RED);
                        valido = false;
                    }
                }

                if (index == 2) {
                    if (campoNick.getText().trim().isEmpty()) {
                        lblNick.setForeground(Color.RED);
                        valido = false;
                    } else {
                        lblNick.setForeground(Color.BLACK);
                    }
                }

                if (!valido) return;

                try {
                    if (index == 0) empresa.asignarTelefonoFijo(titular);
                    else if (index == 1) empresa.agregarTelefonoMovil(titular, campoNumero.getText(), Double.parseDouble(campoMonto.getText()));
                    else if (index == 2) empresa.crearCuentaNauta(titular, campoNick.getText());

                    cargarDatos();
                    tablaBloqueada.setEnabled(true);
                    panelFormulario.setVisible(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ListadoServicios.this, "Error: " + ex.getMessage());
                }
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tablaBloqueada.setEnabled(true);
                panelFormulario.setVisible(false);
            }
        });

        tablaBloqueada.setEnabled(false); // Bloquear tabla mientras se llena
        panelFormulario.setVisible(true);
        panelFormulario.revalidate();
        panelFormulario.repaint();
    }

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
