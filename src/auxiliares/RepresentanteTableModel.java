package auxiliares;
import java.util.ArrayList;

import logica.*;

import javax.swing.table.DefaultTableModel;

import logica.Cliente;

public class RepresentanteTableModel extends DefaultTableModel {
    public RepresentanteTableModel() {
        String[] columnNames = {
            "Nombre Representante", 
            "ID", 
            "Cliente Asociado", 
            
        };
        setColumnIdentifiers(columnNames);
    }
    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // Esto hace que todas las celdas no sean editables
    }
    
    public void cargarDatos( ArrayList<Representante> representantes) {
        setRowCount(0);
        for (Representante r : representantes) {
        	
        	
            addRow(new Object[]{ r.getNombreCompleto(), r.getNumId(), r.getClienteRepresentado().getNombre()});
        }


    }
}
