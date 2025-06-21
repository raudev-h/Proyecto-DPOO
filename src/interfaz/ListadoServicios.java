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

        // ===========================
        // PESTA�AS CON TABLAS
        // ===========================
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Serif", Font.PLAIN, 18));

        // Crear modelos
        modelFijos = new TelefonoFijoTableModel();
        modelMoviles = new TelefonoMovilTableModel();
        modelNauta = new CuentaNautaTableModel();

        // A�adir cada pesta�a con su tabla (dentro de JScrollPane)
        tabbedPane.addTab("Tel�fonos Fijos", crearTabla(modelFijos));
        tabbedPane.addTab("Tel�fonos M�viles", crearTabla(modelMoviles));
        tabbedPane.addTab("Cuentas Nauta", crearTabla(modelNauta));

        // ===========================
        // PANEL LATERAL PARA FORMULARIO
        // ===========================
        panelFormulario = new JPanel();
        panelFormulario.setPreferredSize(new Dimension(300, getHeight()));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Formulario"));
        panelFormulario.setVisible(false); // Oculto al inicio

        // ===========================
        // BOT�N CREAR SERVICIO
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
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;

        final int index = tabbedPane.getSelectedIndex();
        int row = 0;

        final JTextField txtNumero = new JTextField(15);
        final JTextField txtMonto = new JTextField(10);
        final JTextField txtNick = new JTextField(15);

        if (index == 0) {
            // Tel�fono Fijo: solo n�mero
            gbc.gridx = 0;
            gbc.gridy = row;
            panelFormulario.add(new JLabel("N�mero:"), gbc);
            gbc.gridx = 1;
            panelFormulario.add(txtNumero, gbc);

        } else if (index == 1) {
            // Tel�fono M�vil: n�mero + monto
            gbc.gridx = 0;
            gbc.gridy = row;
            panelFormulario.add(new JLabel("N�mero:"), gbc);
            gbc.gridx = 1;
            panelFormulario.add(txtNumero, gbc);

            row++;
            gbc.gridx = 0;
            gbc.gridy = row;
            panelFormulario.add(new JLabel("Monto a Pagar:"), gbc);
            gbc.gridx = 1;
            panelFormulario.add(txtMonto, gbc);

        } else if (index == 2) {
            // Cuenta Nauta: solo nick
            gbc.gridx = 0;
            gbc.gridy = row;
            panelFormulario.add(new JLabel("Nick:"), gbc);
            gbc.gridx = 1;
            panelFormulario.add(txtNick, gbc);
        }

        // Bot�n Guardar dentro del panel lateral
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        JButton btnGuardar = new JButton("Guardar");
        panelFormulario.add(btnGuardar, gbc);

        // Acci�n Guardar
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (index == 0) {
                        String numero = txtNumero.getText();
                        // TODO: Aqu� crea el Telefono Fijo
                        // empresa.agregarTelefonoFijo(...);
                    } else if (index == 1) {
                        String numero = txtNumero.getText();
                        double monto = Double.parseDouble(txtMonto.getText());
                        // TODO: Aqu� crea el Telefono Movil
                        // empresa.agregarTelefonoMovil(...);
                    } else if (index == 2) {
                        String nick = txtNick.getText();
                        // TODO: Aqu� crea la Cuenta Nauta
                        // empresa.crearCuentaNauta(...);
                    }
                    cargarDatos();
                    JOptionPane.showMessageDialog(ListadoServicios.this, "Servicio creado correctamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ListadoServicios.this, "Error: " + ex.getMessage());
                }
            }
        });

        // Mostrar
        panelFormulario.setVisible(true);
        panelFormulario.revalidate();
        panelFormulario.repaint();
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
                        "La ventana del listado de servicios ya est� abierta",
                        "Informaci�n",
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
