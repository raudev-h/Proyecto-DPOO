package interfaz;
import logica.*;
import auxiliares.ClientesServiciosTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;
import logica.Cliente;

public class ClientesConTodosLosServiciosContratados extends JDialog {
    private JTable table;
    private ClientesServiciosTableModel tableModel;

    // ---------- SINGLETON -------------
    private static ClientesConTodosLosServiciosContratados instance;
    private static Principal ventanaPrincipal; // Referencia a Principal

    public static void mostrarClientesConTodosLosServiciosContratados(Principal principal) {
        if (instance == null) {
            instance = new ClientesConTodosLosServiciosContratados(principal);
            instance.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            instance.setModal(true); // Bloquea la aplicación hasta cerrar
            instance.setLocationRelativeTo(null);
            ventanaPrincipal = principal;
            if (ventanaPrincipal != null) {
                ventanaPrincipal.cambiarImagenFondo("/imagenes/e.png");
            }
            instance.setVisible(true);
        } else {
            instance.toFront();
            ventanaPrincipal = principal;
        }
    }

    // Constructor privado para Singleton
    private ClientesConTodosLosServiciosContratados(Principal principal) {
        setBounds(100, 100, 1126, 662);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(15, 16, 1074, 571);
        getContentPane().add(panel);
        panel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
        scrollPane.setBounds(15, 36, 1044, 522);
        panel.add(scrollPane);

        tableModel = new ClientesServiciosTableModel();
        table = new JTable(tableModel);

        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFont(new Font("Serif", Font.PLAIN, 20));
        table.setRowHeight(25);
        scrollPane.setViewportView(table);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Serif", Font.PLAIN, 20));

        JLabel lblTitulo = new JLabel("Clientes con Todos los Servicios Contratados");
        lblTitulo.setFont(new Font("Serif", Font.BOLD, 21));
        lblTitulo.setBounds(15, 0, 450, 20);
        panel.add(lblTitulo);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        ventanaPrincipal = principal; // Para imagen de fondo

        cargarDatos(EmpresaTelecomunicaciones.getInstancia().clientesConTodosLosTiposServicio());
    }

    public void cargarDatos(ArrayList<Cliente> clientes) {
        ClientesServiciosTableModel.cargarDatos(tableModel, clientes);
    }

    @Override
    public void dispose() {
        // Restaurar imagen al cerrar
        if (ventanaPrincipal != null) {
            ventanaPrincipal.cambiarImagenFondo("/imagenes/d.png");
        }
        instance = null;
        super.dispose();
    }
}