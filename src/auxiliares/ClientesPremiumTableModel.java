package auxiliares;

import logica.*;
import javax.swing.table.DefaultTableModel;

public class ClientesPremiumTableModel extends DefaultTableModel {
    private static final long serialVersionUID = 1L;
    
    public ClientesPremiumTableModel() {
       
        String[] columnNames = {"Indice","Nombre", "Dirección", "Teléfono", "Meses >1000 CUP"};
        this.setColumnIdentifiers(columnNames);
    }
    
 
    public void adicionar(int indice,String nombre, String direccion, String telefono, int mesesAltoConsumo) {
        Object[] newRow = new Object[]{
        	indice,
            nombre, 
            direccion, 
            telefono,
            mesesAltoConsumo
        };
        addRow(newRow);
    }
    
    public void cargarDatosClientesPremium() {
        
        int indice = 1; 
        for (Cliente cliente : EmpresaTelecomunicaciones.getInstancia().clientesMasMilMontoNauta()) {
        	
            String nombre = cliente.getNombre();
            String direccion = cliente.getDireccion();
            String telefono = cliente.buscarPrimerTelefono();
            int cantMesesAltoConsumo = cliente.cantMesesMasMilCuentasNautas();
            
            this.adicionar(indice,nombre, direccion, telefono, cantMesesAltoConsumo);
            indice++;
        }
        
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return false; 
    }
}