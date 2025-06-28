package auxiliares;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

import logica.CuentaNauta;

public class MayorGastoKbTableModel extends DefaultTableModel {
    private static final long serialVersionUID = 1L;

    // Arreglo con los nombres de los meses
    private static final String[] MESES = {
        "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
        "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
    };

    // Constructor que define las columnas
    public MayorGastoKbTableModel() {
        super(new String[]{"Mes", "Consumo (KB)"}, 0);
    }
    

    // Método para agregar una fila (mes y consumo)
    public void adicionar(String mes, long consumoKB) {
        Object[] newRow = new Object[]{mes, consumoKB};
        addRow(newRow);
    }

    // Método para cargar los datos de consumo mensual 
    public void cargarConsumos(HashMap<String,Double> calcularKbGastadosMeses) {
        // Limpiar datos existentes
        this.setRowCount(0);
    }
        	public void cargarConsumos(long[] calcularKbGastadosMeses) {
        	    this.setRowCount(0);

        	    int index = 0;
        	    for (String mes : MESES) {
        	        long consumo = 0;
        	        if (index < calcularKbGastadosMeses.length) {
        	            consumo = calcularKbGastadosMeses[index];
        	        }
        	        this.adicionar(mes, consumo);
        	        index++;
        	    }
        	}

    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // Las celdas no se pueden editar
    }
}