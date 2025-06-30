package auxiliares;
import logica.*;

import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.List;

public class LlamadaFijoTableModel extends DefaultTableModel{
	
	private static final long serialVersionUID = 1L;
	
	// Constructor de la Tabla de llamadas con sus respectivos nombre de columnas
	public LlamadaFijoTableModel(){
		String[] columnNames = {"Número Destino", "Duración","Provincia destino","Costo"};
		this.setColumnIdentifiers(columnNames);
	}
	
	//Funcion para adicionar los datos a la tabla de cada llamada 
			public void adicionar(String numeroDestino, String duracion,String provincia, double costo){
				Object[] newRow = new Object[]{numeroDestino, duracion, provincia, costo};
				addRow(newRow);
			}
	
	//Cargar la informacion de cada llamada desde la logica
	public void cargarLlamadas(TelefonoFijo telefono){
		
		//Limpiar los datos de las llamadas existentes
		this.setRowCount(0);

		//Obtener la informacion de todas las llamadas del telefono Fijo
		for(Llamada ll : telefono.getLlamadas() ){
				if(!(ll instanceof LlamadaLargaDistancia)){
				String numeroDestino = ll.getNumeroDestino();
				String duracion = convertirDuracion(ll.getDuracion());
				double costo = Math.round(ll.getDuracion() * 0.048 * 100.0) / 100.0;
				String provincia = ((LlamadaFijo)ll).getProvincia();

				this.adicionar(numeroDestino, duracion, provincia,costo);
			}
			
		}
	}
	
	//Convertir la duracion de una llamada a formato 00:00/000:00 min
	private String convertirDuracion(double duracion) {
	    int minutos = (int)duracion / 60;
	    int segundos = (int)Math.round(duracion % 60);

	    // Asegurarse de que los segundos siempre tengan dos dígitos
	    String segundosStr = (segundos < 10) ? "0" + segundos : String.valueOf(segundos);

	    return minutos + ":" + segundosStr;
	}
	
	@Override
	public boolean isCellEditable(int row, int column){
					
		return false;
	}
	
	
	
	
	
}

