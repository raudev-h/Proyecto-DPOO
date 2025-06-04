package auxiliares;
import javax.swing.table.DefaultTableModel;

public class ServicioTableModel extends DefaultTableModel {
	

	private static final long serialVersionUID = 1L;
	public ServicioTableModel(){
		String[] columnNames = {};
		this.setColumnIdentifiers(columnNames);
	}
	public void adicionar(String nombre, String direccion, int cantServicios){
		Object[] newRow = new Object[]{nombre, direccion, cantServicios};
		addRow(newRow);
	}


}
