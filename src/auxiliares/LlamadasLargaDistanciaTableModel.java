package auxiliares;
import logica.*;

import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.List;

public class LlamadasLargaDistanciaTableModel extends DefaultTableModel{
	
	private static final long serialVersionUID = 1L;
	
	// Constructor de la Tabla de llamadas larga Distancia con sus respectivos nombre de columnas
	public LlamadasLargaDistanciaTableModel(){
		String[] columnNames = {"Número Destino", "Duración","Provincia destino","Municipio","Costo"};
		this.setColumnIdentifiers(columnNames);
	}
	
	//Funcion para adicionar los datos a la tabla de cada llamada 
			public void adicionar(String numeroDestino, String duracion,String provincia,String municipio ,double costo){
				Object[] newRow = new Object[]{numeroDestino, duracion, provincia,municipio, costo};
				addRow(newRow);
			}
	
	//Cargar la informacion de cada llamada de larga Distancia desde la logica
	public void cargarLlamadas(TelefonoFijo telefono){
		
		//Limpiar los datos de las llamadas existentes
		this.setRowCount(0);
		
		//Obtener la informacion de todas las llamadas de larga distancia del telefono Fijo
		for(Llamada ll : telefono.getLlamadasLargas() ){
			String numeroDestino = ll.getNumeroDestino();
			String duracion = convertirDuracion(ll.getDuracion());
			double costo = Math.round(ll.getDuracion() * 0.75 * 100.0) / 100.0;	
			String provincia = ((LlamadaFijo)ll).getProvincia();
			String municipio = ((LlamadaLargaDistancia)ll).getMunicipio();
			
			this.adicionar(numeroDestino, duracion, provincia,municipio, costo);			
			
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

