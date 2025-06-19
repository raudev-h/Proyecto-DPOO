package auxiliares;

import java.util.ArrayList;
import logica.*;
import javax.swing.table.DefaultTableModel;

public class RepresentanteTableModel extends DefaultTableModel {
    public RepresentanteTableModel() {
        String[] columnNames = {
            "Nombre Representante", 
            "ID", 
            "Cliente Asociado"
        };
        setColumnIdentifiers(columnNames);
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // Esto hace que todas las celdas no sean editables
    }
    
    public void cargarDatos(ArrayList<Representante> representantes) {
        setRowCount(0); // Limpiar la tabla
        
        if (representantes != null) { // Verificar que la lista no sea null
            for (Representante r : representantes) {
                if (r != null) { // Verificar que el representante no sea null
                    String nombreCliente = "Ninguno"; // Valor por defecto
                    
                    // Verificar si tiene cliente asociado
                    if (r.getClienteRepresentado() != null) {
                        nombreCliente = r.getClienteRepresentado().getNombre();
                    }
                    
                    // Agregar la fila con los datos
                    addRow(new Object[]{
                        r.getNombreCompleto(), 
                        r.getNumId(), 
                        nombreCliente
                    });
                }
            }
        }
    }
}