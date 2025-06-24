package auxiliares;
import java.util.ArrayList;

import logica.*;

import javax.swing.table.DefaultTableModel;

public class ClienteTableModel  extends DefaultTableModel  {
		private static final long serialVersionUID = 1L;
		
		// Constructor de la Tabla de clientes con sus respectivos nombre de columnas
		public ClienteTableModel(){
			String[] columnNames = {"Nombre", "Direccion","Cant. Servicios","Tipo de Cliente"};
			this.setColumnIdentifiers(columnNames);
		}
		
		//Funcion para adicionar los datos a la tabla de cada usuario
				public void adicionar(String nombre, String direccion, int cantServicios, String tipoCliente){
					Object[] newRow = new Object[]{nombre, direccion, cantServicios, tipoCliente};
					addRow(newRow);
				}
		
		//Cargar la informacion de cada cliente desde la logica
		public void cargarClientes(){
			
			//Limpiar los datos de los clientes existentes
			this.setRowCount(0);
			
			//Obtener la informacion de todos los clientes de la empresa
			for(Cliente cliente: EmpresaTelecomunicaciones.getInstancia().getClientes()){
								
				String nombre = cliente.getNombre();
				String direccion = cliente.getDireccion();
				int cantServicios = cliente.getServicios().size();
				String tipoCliente = cliente.getClass().getSimpleName();
				
				
				this.adicionar(nombre, direccion, cantServicios, tipoCliente);			
				
			}
		}
		
		@Override
		public boolean isCellEditable(int row, int column){
						
			return false;
		}
		
		  //Eliminar un cliente de la tabla 
	    public void eliminarCliente(int indiceFila){
	    	
	    	Cliente cliente = EmpresaTelecomunicaciones.getInstancia().getClientes().get(indiceFila);
	    	
	    	EmpresaTelecomunicaciones.getInstancia().getClientes().remove(cliente);
	    	
	    	this.cargarClientes();
	    	
    	
	    	
	    }
	    public void actualizarClientes(ArrayList<Cliente> clientesFiltrados) {
	        // Limpiar los datos existentes
	        this.setRowCount(0);
	        
	        // Agregar los clientes filtrados
	        for(Cliente cliente : clientesFiltrados) {
	            String nombre = cliente.getNombre();
	            String direccion = cliente.getDireccion();
	            int cantServicios = cliente.getServicios().size();
	            String tipoCliente = cliente.getClass().getSimpleName();
	            this.adicionar(nombre, direccion, cantServicios, tipoCliente);
	        }
	    }
		
		
	}
