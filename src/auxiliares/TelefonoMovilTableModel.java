package auxiliares;
import logica.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TelefonoMovilTableModel extends DefaultTableModel {
	
	
    public TelefonoMovilTableModel() {
        String[] columnNames = {"Número", "Cliente", "Llamadas", "Monto a Pagar"};
        setColumnIdentifiers(columnNames);

    }
    public void cargarDatos(List<TelefonoMovil> telefonos) {
        setRowCount(0);
        for (TelefonoMovil tm : telefonos) {
        	addRow(new Object[]{ tm.getNumero(), tm.getTitular().getNombre(),tm.getMontoApagar(), tm.getLlamadas().size()
            });
        }
    }
}

