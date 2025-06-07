package auxiliares;
import logica.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TelefonoMovilTableModel extends DefaultTableModel {
	
	
    public TelefonoMovilTableModel() {
        String[] columnNames = {"Número", "Cliente", "Cant. Llamadas", "Monto a Pagar"};
        setColumnIdentifiers(columnNames);

    }
    public void cargarDatos(List<TelefonoMovil> telefonos) {
        setRowCount(0);
        for (TelefonoMovil tm : telefonos) {
        	addRow(new Object[]{ tm.getNumero(), tm.getTitular().getNombre(),tm.getLlamadas().size(), tm.getLlamadas().size()
            });
        }
    }
    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // Esto hace que todas las celdas no sean editables
    }
}

