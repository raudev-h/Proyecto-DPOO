package auxiliares;
import logica.*;

import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.List;

public class TelefonoFijoTableModel extends DefaultTableModel {
	

    public TelefonoFijoTableModel() {
        String[] columnNames = {"Número", "Cliente", "Llamadas", "Facturas", "Llam. Larga Distancia"};
        setColumnIdentifiers(columnNames);
    }
    
    public void cargarDatos(ArrayList<TelefonoFijo> telefonos) {
        setRowCount(0); // Limpiar datos anteriores 
        for (TelefonoFijo tf : telefonos) {
            addRow(new Object[]{ tf.getNumero(),tf.getTitular().getNombre(), tf.getLlamadas().size(), tf.getFacturas().size(),
                tf.getLlamadasLargas().size()
            });
        }
    }
    

    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // Hacer la Tabla no editable
    }

}