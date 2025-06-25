package auxiliares;

import javax.swing.table.DefaultTableModel;

import logica.*;

public class UsuarioTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;

	public UsuarioTableModel(Cliente[] usuarios){

		String[] columnNames = {"Nombre","Direccion", "Cantidad de Servicios"};
		setColumnIdentifiers(columnNames);
//		this.setColumnIdentifiers(columnNames); 

		for (int i = 0; i < usuarios.length; i++) {
			Object[] newRow = new Object[]{usuarios[i].getNombre(),usuarios[i].getDireccion(), usuarios[i].getServicios().size()};
			addRow(newRow);
		}
	}
}