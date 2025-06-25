package auxiliares;

import java.util.*;
import javax.swing.table.DefaultTableModel;

public class CantidadCuentasNautasProvincias extends DefaultTableModel {

    private static final long serialVersionUID = 1L; 
    
    public CantidadCuentasNautasProvincias(ArrayList<Map.Entry<String, Integer>> lista) { 
        String[] columnNames = {"Indice", "Provincias", "Cantidad de Cuentas"}; 
        this.setColumnIdentifiers(columnNames); 
     
        for (int i = 0; i < lista.size(); i++) {
            Object[] newRow = new Object[]{i + 1, lista.get(i).getKey(), lista.get(i).getValue()};
            addRow(newRow);
        }
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // Hace que todas las celdas no sean editables
    }
}