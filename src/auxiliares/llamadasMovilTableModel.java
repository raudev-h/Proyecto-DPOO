package auxiliares;
import java.util.ArrayList;

import logica.*;

import javax.swing.table.DefaultTableModel;

public class llamadasMovilTableModel  extends DefaultTableModel  {
		private static final long serialVersionUID = 1L;
		
		// Constructor de la Tabla de llamadas con sus respectivos nombre de columnas
		public llamadasMovilTableModel(){
			String[] columnNames = {"Número Destino", "Duración","Costo"};
			this.setColumnIdentifiers(columnNames);
		}
		
		//Funcion para adicionar los datos a la tabla de cada llamada
				public void adicionar(String numeroDestino, String duracion, double costo){
					Object[] newRow = new Object[]{numeroDestino, duracion, costo};
					addRow(newRow);
				}
		
		//Cargar la informacion de cada llamada desde la logica
		public void cargarLlamadas(Telefono telefono){
			
			//Limpiar los datos de las llamadas existentes
			this.setRowCount(0);
			
			//Obtener la informacion de todos las llamadas del telefono
			for(Llamada ll : telefono.getLlamadas() ){
				String numeroDestino = ll.getNumeroDestino();
				String duracion = convertirDuracion(ll.getDuracion());
				double costo = Math.round(ll.getDuracion() * 0.14 * 100.0) / 100.0;				
				
				this.adicionar(numeroDestino, duracion, costo);			
				
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
