package auxiliares;

import logica.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MesDatosTableModel extends DefaultTableModel {
    private ArrayList<Map.Entry<String, MesDatos>> mesesDatos = new ArrayList<>();
    private CuentaNauta cuenta;

    public MesDatosTableModel() {
        String[] columnNames = {
            "Nick", 
            "Mes", 
            "KB Recibidos Nacional", 
            "KB Enviados Nacional", 
            "KB Recibidos Internacional", 
            "KB Enviados Internacional",
            "KB Navegación",
            "Monto Total"
        };
        setColumnIdentifiers(columnNames);
    }
    
    public void cargarDatos(CuentaNauta cuenta) {
        this.cuenta = cuenta;
        this.mesesDatos = new ArrayList<>(cuenta.getMesDatosOrdenados());
        setRowCount(0);
        
        for (Map.Entry<String, MesDatos> entry : mesesDatos) {
            MesDatos md = entry.getValue();
            addRow(new Object[]{
                cuenta.getNick(), // Nick de la cuenta
                entry.getKey(),   // Mes
                md.getKbRecibidosNacional(),
                md.getKbEnviadosNacional(),
                md.getKbRecibidosInternacional(),
                md.getKbEnviadosInternacional(),
                md.getKbNavegacion(),
                md.getMontoTotal()
            });
        }
    }
    
    public Map.Entry<String, MesDatos> getMesDatosAt(int row) {
        return (row >= 0 && row < mesesDatos.size()) ? mesesDatos.get(row) : null;
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}