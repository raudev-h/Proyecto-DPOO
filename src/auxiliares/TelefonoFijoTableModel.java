package auxiliares;
import logica.*;

import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.List;

public class TelefonoFijoTableModel extends DefaultTableModel {
    private ArrayList<TelefonoFijo> telefonos = new ArrayList<>(); // Inicializado aquí

    public TelefonoFijoTableModel() {
        String[] columnNames = {"Número", "Cliente", "Cant. Llamadas", "Facturas", "Llam. Larga Distancia"};
        setColumnIdentifiers(columnNames);
    }
    
    public void cargarDatos(ArrayList<TelefonoFijo> telefonos) {
        this.telefonos = new ArrayList<>(telefonos); // Guarda la referencia
        setRowCount(0);
        for (TelefonoFijo tf : telefonos) {
            String nombreCliente = tf.getTitular() != null ? tf.getTitular().getNombre() : "Sin titular";
            addRow(new Object[]{
                tf.getNumero(),
                nombreCliente,
                tf.getLlamadas().size(),
                tf.getFacturas().size(),
                tf.getLlamadasLargas().size()
            });
        }
    }
    
    public TelefonoFijo getServicioAt(int row) {
        return (row >= 0 && row < telefonos.size()) ? telefonos.get(row) : null; // Validación de rango
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}