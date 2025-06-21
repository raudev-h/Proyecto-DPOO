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
    private JPanel panelFormulario; // Panel lateral real
    private JTextField campoNumero;
    private JTextField campoMonto;
    private JTextField campoNick;
    private JButton btnGuardar;

    private static ListadoServicios instance;

    private ListadoServicios() {
        empresa = EmpresaTelecomunicaciones.getInstancia();
        setTitle("Listado de Servicios");
        setBounds(100, 100, 1126, 662);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // ================================
        // PESTAÑAS CON TABLAS
        // ================================
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Serif", Font.PLAIN, 18));

        modelFijos = new TelefonoFijoTableModel();
        modelMoviles = new TelefonoMovilTableModel();
        modelNauta = new CuentaNautaTableModel();

        tabbedPane.addTab("Teléfonos Fijos", crearTabla(modelFijos));
        tabbedPane.addTab("Teléfonos Móviles", crearTabla(modelMoviles));
        tabbedPane.addTab("Cuentas Nauta", crearTabla(modelNauta));

        // ================================
        // PANEL LATERAL FORMULARIO
        // ================================
        panelFormulario = new JPanel();
        panelFormulario.setPreferredSize(new Dimension(300, getHeight()));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Formulario"));
        panelFormulario.setLayout(new BoxLayout(panelFormulario, BoxLayout.Y_AXIS));
        panelFormulario.setVisible(false); // Oculto al inicio

        // ================================
        // BOTÓN CREAR SERVICIO
        // ================================
        JButton btnCrearServicio = new JButton("Crear Servicio");
        btnCrearServicio.setFont(new Font("Serif", Font.PLAIN, 20));
        btnCrearServicio.setPreferredSize(new Dimension(200, 40));
        btnCrearServicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFormularioLateral();
            }
        });

        // ================================
        // AGREGAR COMPONENTES
        // ================================
        add(tabbedPane, BorderLayout.CENTER);
        add(panelFormulario, BorderLayout.EAST);
        add(btnCrearServicio, BorderLayout.SOUTH);

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

    private void mostrarFormularioLateral() {
        // Limpiar formulario
        panelFormulario.removeAll();

        int index = tabbedPane.getSelectedIndex();

        campoNumero = new JTextField();
        campoMonto = new JTextField();
        campoNick = new JTextField();
        btnGuardar = new JButton("Guardar");

        if (index == 0) { // Teléfono Fijo
            panelFormulario.add(new JLabel("Número:"));
            panelFormulario.add(campoNumero);
        } else if (index == 1) { // Teléfono Móvil
            panelFormulario.add(new JLabel("Número:"));
            panelFormulario.add(campoNumero);
            panelFormulario.add(new JLabel("Monto a pagar:"));
            panelFormulario.add(campoMonto);
        } else if (index == 2) { // Cuenta Nauta
            panelFormulario.add(new JLabel("Nick:"));
            panelFormulario.add(campoNick);
        }

        panelFormulario.add(Box.createVerticalStrut(10));
        panelFormulario.add(btnGuardar);

        // Listener del botón GUARDAR
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarServicio();
            }
        });

        panelFormulario.revalidate();
        panelFormulario.repaint();
        panelFormulario.setVisible(true);
    }

    private void guardarServicio() {
        int index = tabbedPane.getSelectedIndex();

        try {
            if (index == 0) {
                String numero = campoNumero.getText();
                // empresa.agregarTelefonoFijo(titular, numero);
            } else if (index == 1) {
                String numero = campoNumero.getText();
                double monto = Double.parseDouble(campoMonto.getText());
                // empresa.agregarTelefonoMovil(titular, numero, monto);
            } else if (index == 2) {
                String nick = campoNick.getText();
                // empresa.agregarCuentaNauta(titular, nick);
            }

            cargarDatos();
            panelFormulario.setVisible(false); // Ocultar después de guardar

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Monto inválido: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al crear servicio: " + ex.getMessage());
        }
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
                UIManager.put("OptionPane.messageFont", new Font("Serif", Font.BOLD, 20));
                UIManager.put("OptionPane.buttonFont", new Font("Serif", Font.BOLD, 18));
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

    public static void main(String[] args) {
        abrirListadoServicio();
    }
}
