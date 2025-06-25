package auxiliares;

import logica.*;
import javax.swing.table.DefaultTableModel;

public class TlfLlamadasMas100min extends DefaultTableModel {
    private static final long serialVersionUID = 1L;
    
    public TlfLlamadasMas100min() {
        String[] columnNames = {"Indice", "Numero Telefono", "Titular", 
                               "Cant. Llamadas > limite", "Duracion Maxima (min)"};
        this.setColumnIdentifiers(columnNames);
    }
    
    public void adicionar(int indice, String numTelefono, String nombreTitular, 
                         int llamadasMayores, 
                         double duracionMayor) {
        Object[] newRow = new Object[]{
            indice, 
            numTelefono, 
            nombreTitular,
            llamadasMayores, 
            duracionMayor,
            
        };
        addRow(newRow);
    }
    
    public void cargarDatosTelefonos(int limite) {
        int indice = 1;
        for(TelefonoMovil tlf: EmpresaTelecomunicaciones.getInstancia().telefonosMovilLLamadasMasMin(limite)) {
            String numTelefono = tlf.getNumero();
            String nombreTitular = tlf.getTitular().getNombre();
            int llamadasMayores = tlf.llamadasMasMin(limite).size();
            double duracionMayor = tlf.duracionMaxima(tlf.llamadasMasMin(limite));
            
            this.adicionar(indice, numTelefono, nombreTitular, 
                          llamadasMayores, duracionMayor);
            indice++;
        }
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // Hace que todas las celdas no sean editables
    }
}