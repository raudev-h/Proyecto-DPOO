package auxiliares;

import javax.swing.table.DefaultTableModel;

import java.util.List;

import logica.Cliente;
import logica.EntidadNoEstatal;
import logica.PersonaNatural;

public class ClientesServiciosTableModel extends DefaultTableModel {

    public ClientesServiciosTableModel() {
        String[] columnas = {"Nombre", "Dirección", "Tipo de Cliente"};
        setColumnIdentifiers(columnas);
    }

    public static void cargarDatos(ClientesServiciosTableModel modelo, List<Cliente> clientes) {
        modelo.setRowCount(0); // Limpiar filas anteriores

        for (Cliente c : clientes) {
            String nombre = obtenerNombre(c);
            String direccion = c.getDireccion();
            String tipo = obtenerTipo(c);

            modelo.addRow(new Object[]{nombre, direccion, tipo});
        }
    }

    // Como cargarDatos es estático, estos métodos deben ser estáticos también
    private static String obtenerNombre(Cliente cliente) {
        if (cliente instanceof PersonaNatural) {
            return ((PersonaNatural) cliente).getNombre();
        } else if (cliente instanceof EntidadNoEstatal) {
            return ((EntidadNoEstatal) cliente).getNombre();
        } else {
            return "Desconocido";
        }
    }

    private static String obtenerTipo(Cliente cliente) {
        if (cliente instanceof PersonaNatural) {
            return "Persona Natural";
        } else if (cliente instanceof EntidadNoEstatal) {
            return "Entidad No Estatal";
        } else {
            return "Otro";
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}