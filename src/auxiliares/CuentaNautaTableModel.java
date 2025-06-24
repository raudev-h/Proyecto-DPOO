package auxiliares;
import logica.*;

import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.List;

public class CuentaNautaTableModel extends DefaultTableModel {
    private ArrayList<CuentaNauta> cuentas = new ArrayList<>(); // Inicializado aquí

    public CuentaNautaTableModel() {
        String[] columnNames = {"Nick", "Cliente", "Meses", "MB Totales"};
        setColumnIdentifiers(columnNames);
    }
    
    public void cargarDatos(List<CuentaNauta> cuentas) {
        this.cuentas = new ArrayList<>(cuentas); // Guarda la referencia
        setRowCount(0);
        for (CuentaNauta cn : cuentas) {
            String nombreCliente = cn.getTitular() != null ? cn.getTitular().getNombre() : "Sin titular";
            addRow(new Object[]{
                cn.getNick(),
                nombreCliente,
                cn.getMesDatos().size(),
                cn.calcularMbTotalesGastados()
            });
        }
    }
    
    public CuentaNauta getServicioAt(int row) {
        return (row >= 0 && row < cuentas.size()) ? cuentas.get(row) : null; // Validación de rango
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}