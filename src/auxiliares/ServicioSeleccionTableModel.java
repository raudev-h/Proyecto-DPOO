package auxiliares;
import logica.*;

import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.List;

public class ServicioSeleccionTableModel extends DefaultTableModel {
	

    public ServicioSeleccionTableModel() {
        String[] columnNames = {"Tipo de Servicio","Número"};
        setColumnIdentifiers(columnNames);
    }
    
    public void cargarDatos(ArrayList<Servicio> serviciosDisponibles) {
        setRowCount(0); // Limpiar datos anteriores 
        String tipoServicio;
        for (Servicio s : serviciosDisponibles) {
        	if(s instanceof TelefonoFijo || s instanceof TelefonoMovil){
        		
        		if(s instanceof TelefonoMovil){ tipoServicio = "Télefono Móvil";}
        		else{ tipoServicio =  "Télefono Fijo"; }
        			      		
        		
        	   addRow(new Object[]{tipoServicio, ((Telefono)s).getNumero()});
        	}
        }
    }
    
    

    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // Hacer la Tabla no editable
    }

}