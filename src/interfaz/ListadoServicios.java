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
    private JTabbedPane tabbedPane;
    private TelefonoFijoTableModel modelFijos;
    private TelefonoMovilTableModel modelMoviles;
    private CuentaNautaTableModel modelNauta;
    private JPanel panelFormulario; // Panel lateral para formulario
    private static ListadoServicios instance;

    private ListadoServicios() {
        setTitle("Listado de Servicios");
        setBounds(100, 100, 1126, 662);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

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
        panelFormulario.setLayout(new BoxLayout(panelFormulario, BoxLayout.Y_AXIS));
        panelFormulario.setVisible(false); // Oculto al inicio

        // ===========================
        // BOTÓN CREAR SERVICIO
        // ===========================
        JButton btnCrearServicio = new JButton("Crear Servicio");
        btnCrearServicio.setFont(new Font("Serif", Font.BOLD, 18));
        btnCrearServicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFormulario();
            }
        });
        // Ajustar fuente (más pequeña)
        btnCrearServicio.setFont(new Font("Serif", Font.PLAIN, 20));

        // Ajustar tamaño preferido (ancho x alto)
        btnCrearServicio.setPreferredSize(new Dimension(1, 40));

        // Reducir margen interno (padding)
        btnCrearServicio.setMargin(new Insets(5, 10, 5, 10));

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
        EmpresaTelecomunicaciones empresa = EmpresaTelecomunicaciones.getInstancia();
        modelFijos.cargarDatos(empresa.getTelefonosFijos());
        modelMoviles.cargarDatos(empresa.getTelefonosMoviles());
        modelNauta.cargarDatos(empresa.getCuentasNautas());
    }

    private void mostrarFormulario() {
        // Aquí limpias y muestras el panel lateral
        panelFormulario.removeAll();
        panelFormulario.add(new JLabel("Aquí irá el formulario para crear servicio."));
        panelFormulario.revalidate();
        panelFormulario.repaint();
        panelFormulario.setVisible(true);
    }

    // ==============
    // SINGLETON: asegurar que solo haya 1 ventana abierta
    // ==============
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
