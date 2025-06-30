package interfaz;

import auxiliares.llamadasMovilTableModel;
import logica.Telefono;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListadoLlamadasFijo extends JDialog {
    private JTable table;
    private llamadasMovilTableModel tableModel;
    private Telefono telefono;

    private static ListadoLlamadasFijo instance; // Singleton

    private JLabel lblTitulo;
    private JButton btnCerrar;

    public ListadoLlamadasFijo(Principal principal, Telefono telefono) {
        this.telefono = telefono;

        setModal(true);
        setTitle("Listado de Llamadas del Teléfono Fijo");
        setBounds(100, 100, 781, 713);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(10, 11, 734, 630);
        panel.setLayout(null);
        getContentPane().add(panel);

        // Título
        lblTitulo = new JLabel("Teléfono Fijo: " + telefono.getNumero());
        lblTitulo.setFont(new Font("Serif", Font.BOLD, 21));
        lblTitulo.setBounds(20, 45, 500, 25);
        panel.add(lblTitulo);
        
        lblTitulo = new JLabel("Cliente: " + telefono.getTitular().getNombre());
        lblTitulo.setFont(new Font("Serif", Font.BOLD, 20));
        lblTitulo.setBounds(20, 0, 500, 25);
        panel.add(lblTitulo);

        // ScrollPane para la tabla
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 77, 699, 501);
        scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
        panel.add(scrollPane);

        // Inicializar modelo y tabla
        tableModel = new llamadasMovilTableModel();
        table = new JTable(tableModel);
        table.setFont(new Font("Serif", Font.PLAIN, 17));
        table.setRowHeight(25);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);

        // Estilo header
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Serif", Font.BOLD, 18));

        scrollPane.setViewportView(table);

        JLabel lblTotalLlamadas = new JLabel("Total llamadas: " + telefono.getLlamadas().size());
        lblTotalLlamadas.setBounds(20, 594, 185, 20);
        lblTotalLlamadas.setFont(new Font("Serif", Font.BOLD, 18));

        panel.add(lblTotalLlamadas);
        

        JLabel lblCostoTotal = new JLabel("Costo Total: " + telefono.calcularCostoTotalLlamadas() );
        lblCostoTotal.setBounds(502, 594, 165, 20);
        lblCostoTotal.setFont(new Font("Serif", Font.BOLD, 18));
        panel.add(lblCostoTotal);

        cargarLlamadas();
    }

    // Método para cargar las llamadas del teléfono fijo en la tabla
    private void cargarLlamadas() {
        if (telefono != null) {
            tableModel.cargarLlamadas(telefono);
        }
    }

    // Método para cerrar la ventana
    private void cerrarVentana() {
        dispose();
    }

    // Método Singleton para abrir la ventana
    public static void abrirListadoLlamadasFijo(Principal principal, Telefono telefono) {
        if (instance == null || !instance.isDisplayable()) {
            instance = new ListadoLlamadasFijo(principal, telefono);
            instance.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            instance.setVisible(true);
        } else {
            instance.toFront();
        }
    }

    // Para Java 1.7, sobreescribir dispose para limpiar instancia
    @Override
    public void dispose() {
        instance = null;
        super.dispose();
    }
}