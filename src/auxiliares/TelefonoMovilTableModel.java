package auxiliares;
import logica.*;

import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.List;

public class TelefonoMovilTableModel extends DefaultTableModel {
    private ArrayList<TelefonoMovil> telefonos = new ArrayList<>(); // Inicializado aquí

    public TelefonoMovilTableModel() {
        String[] columnNames = {"Número", "Cliente", "Cant. Llamadas", "Monto a Pagar"};
        setColumnIdentifiers(columnNames);
    }
    
    public void cargarDatos(List<TelefonoMovil> telefonos) {
        this.telefonos = new ArrayList<>(telefonos); // Guarda la referencia
        setRowCount(0);
        for (TelefonoMovil tm : telefonos) {
            String nombreCliente = tm.getTitular() != null ? tm.getTitular().getNombre() : "Sin titular";
            addRow(new Object[]{
                tm.getNumero(),
                nombreCliente,
                tm.getLlamadas().size(),
                tm.getMontoApagar() // Corregido: Mostrar monto en lugar de cantidad de llamadas
            });
        }
    }
    
    public TelefonoMovil getServicioAt(int row) {
        return (row >= 0 && row < telefonos.size()) ? telefonos.get(row) : null; // Validación de rango
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}

