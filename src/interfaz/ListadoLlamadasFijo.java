package interfaz;

import auxiliares.LlamadaFijoTableModel;
import auxiliares.LlamadasLargaDistanciaTableModel;
import logica.TelefonoFijo;
import logica.Telefono;
import logica.Llamada;
import logica.LlamadaLargaDistancia;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListadoLlamadasFijo extends JDialog {

    private JTabbedPane tabbedPane;
    private JTable tableLlamadas;
    private JTable tableLargaDistancia;
    private LlamadaFijoTableModel modelLlamadas;
    private LlamadasLargaDistanciaTableModel modelLargaDistancia;
    private TelefonoFijo telefonoFijo;

    private static ListadoLlamadasFijo instance; // Singleton

    private JLabel lblCliente;
    private JLabel lblTelefono;
    private JLabel lblTotalLlamadas;
    private JLabel lblCostoTotal;

    public ListadoLlamadasFijo(Principal principal, Telefono telefono) {
        this.telefonoFijo = (TelefonoFijo)telefono;

        setModal(true);
        setTitle("Listado de Llamadas del Teléfono Fijo");
        setBounds(100, 100, 820, 760);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(10, 11, 784, 677);
        panel.setLayout(null);
        getContentPane().add(panel);

        lblCliente = new JLabel("Cliente: " + telefonoFijo.getTitular().getNombre());
        lblCliente.setFont(new Font("Serif", Font.BOLD, 20));
        lblCliente.setBounds(20, 0, 500, 25);
        panel.add(lblCliente);

        lblTelefono = new JLabel("Teléfono Fijo: " + telefonoFijo.getNumero());
        lblTelefono.setFont(new Font("Serif", Font.BOLD, 21));
        lblTelefono.setBounds(20, 35, 500, 25);
        panel.add(lblTelefono);

        // Crear el tabbedPane
        tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(20, 70, 740, 520);
        tabbedPane.setFont(new Font("Serif", Font.PLAIN, 18));
        panel.add(tabbedPane);

        // Modelo y tabla de llamadas normales
        modelLlamadas = new LlamadaFijoTableModel();
        tableLlamadas = new JTable(modelLlamadas);
        tableLlamadas.setFont(new Font("Serif", Font.PLAIN, 17));
        tableLlamadas.setRowHeight(25);
        tableLlamadas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableLlamadas.getTableHeader().setReorderingAllowed(false);
        JTableHeader header1 = tableLlamadas.getTableHeader();
        header1.setFont(new Font("Serif", Font.BOLD, 18));
        JScrollPane scrollLlamadas = new JScrollPane(tableLlamadas);
        scrollLlamadas.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
        tabbedPane.addTab("Llamadas Locales", scrollLlamadas);

        // Modelo y tabla de llamadas larga distancia
        modelLargaDistancia = new LlamadasLargaDistanciaTableModel();
        tableLargaDistancia = new JTable(modelLargaDistancia);
        tableLargaDistancia.setFont(new Font("Serif", Font.PLAIN, 17));
        tableLargaDistancia.setRowHeight(25);
        tableLargaDistancia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableLargaDistancia.getTableHeader().setReorderingAllowed(false);
        JTableHeader header2 = tableLargaDistancia.getTableHeader();
        header2.setFont(new Font("Serif", Font.BOLD, 18));
        JScrollPane scrollLargaDistancia = new JScrollPane(tableLargaDistancia);
        scrollLargaDistancia.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
        tabbedPane.addTab("Llamadas Larga Distancia", scrollLargaDistancia);

        // Etiquetas para total llamadas y costo
        lblTotalLlamadas = new JLabel();
        lblTotalLlamadas.setBounds(20, 610, 300, 20);
        lblTotalLlamadas.setFont(new Font("Serif", Font.BOLD, 18));
        panel.add(lblTotalLlamadas);

        lblCostoTotal = new JLabel();
        lblCostoTotal.setBounds(520, 610, 230, 20);
        lblCostoTotal.setFont(new Font("Serif", Font.BOLD, 18));
        panel.add(lblCostoTotal);

        cargarTablas();
        actualizarTotales();

        tabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                actualizarTotales();
            }
        });
    }

    // Cargar los datos en ambas tablas
    private void cargarTablas() {
        if (telefonoFijo != null) {
            modelLlamadas.cargarLlamadas(telefonoFijo); // Carga todas las llamadas normales (no largas)
            modelLargaDistancia.cargarLlamadas(telefonoFijo); // Carga todas las largas distancia
        }
    }

    // Actualizar etiquetas de totales en función de la pestaña activa
    private void actualizarTotales() {
    	
        int selectedTab = tabbedPane.getSelectedIndex();
        if (selectedTab == 0) { // Llamadas Locales
            int total = telefonoFijo.getLlamadas().size();
            double costo = telefonoFijo.calcularCostoTotalLlamadas();
            lblTotalLlamadas.setText("Total llamadas locales: " + total);
            lblCostoTotal.setText("Costo Total: " + String.format("%.2f", costo));
        } else {
            int totalLargas = telefonoFijo.getLlamadasLargas().size();
            double costoLargas = telefonoFijo.calcularCostoTotalLlamadasLargas();
            lblTotalLlamadas.setText("Total llamadas larga distancia: " + totalLargas);
            lblCostoTotal.setText("Costo Total: " + String.format("%.2f", costoLargas));
        }
    }



    // Método Singleton para abrir la ventana
    public static void abrirListadoLlamadasFijo(Principal principal, Telefono telefono) {
        boolean crearNueva = false;
        if (instance == null) {
            crearNueva = true;
        } else if (!instance.isDisplayable()) {
            crearNueva = true;
        }
        if (crearNueva) {
            instance = new ListadoLlamadasFijo(principal, telefono);
            instance.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            instance.setVisible(true);
        } else {
            instance.toFront();
        }
    }

    @Override
    public void dispose() {
        instance = null;
        super.dispose();
    }
}