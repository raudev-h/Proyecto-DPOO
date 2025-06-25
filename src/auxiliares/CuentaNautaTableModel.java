package auxiliares;
import logica.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CuentaNautaTableModel extends DefaultTableModel {
    public CuentaNautaTableModel() {
        String[] columnNames = {"Nick", "Cliente", "Meses", "MB Totales"};
        setColumnIdentifiers(columnNames);
    }
    
    public void cargarDatos(List<CuentaNauta> cuentas) {
        setRowCount(0);
        for (CuentaNauta cn : cuentas) {      	
       	
        		addRow(new Object[]{ cn.getNick(),cn.getTitular().getNombre(), cn.getMesDatos().size(),
        				cn.calcularMbTotalesGastados()
        		});
        }
    }
    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // Esto hace que todas las celdas no sean editables
    }
}
