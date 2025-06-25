package auxiliares;



import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import logica.Cliente;

public class ClientesLlamadasLargaDistancia extends DefaultTableModel {
    
    private static final long serialVersionUID = 1L; 
    
    public ClientesLlamadasLargaDistancia(ArrayList<Cliente> lista) { 
        String[] columnNames = {"Indice", "Nombre", "Direccion", "Monto Total (CUP)"}; 
        this.setColumnIdentifiers(columnNames); 
     
        for (int i = 0; i < lista.size(); i++) {
            Cliente cliente = lista.get(i);
            Object[] newRow = new Object[]{
                i + 1, 
                cliente.getNombre(), 
                cliente.getDireccion(),
                cliente.getMontoLlamadasLargaDistancia(),
            };
            addRow(newRow);
        }
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // Hace que todas las celdas no sean editables
    }
}