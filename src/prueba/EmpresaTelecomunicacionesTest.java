package prueba;

import logica.*;
import excepciones.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class EmpresaTelecomunicacionesTest {

    @org.junit.Before
    public void setUp() {
        EmpresaTelecomunicaciones empresa = EmpresaTelecomunicaciones.getInstancia();
        empresa.getClientes().clear();
        empresa.getServicios().clear();
        empresa.getServiciosDisponibles().clear();
        empresa.getRepresentantes().clear();
    }

    @Test
    public void testCrearPersonaNaturalYAgregarServicio() throws Exception {
        EmpresaTelecomunicaciones empresa = EmpresaTelecomunicaciones.getInstancia();
        empresa.agregarTelefonoFijo("12345678");
        empresa.agregarPersonaNatural("Juan Perez", "Calle 1", "Playa", "La Habana", "90010112345");

        Cliente cliente = empresa.buscarCliente("Juan Perez");
        assertNotNull(cliente);

        empresa.asignarTelefonoFijo(cliente, "12345678");
        assertEquals(1, cliente.getServicios().size());
        assertTrue(cliente.getServicios().get(0) instanceof TelefonoFijo);
        assertEquals("12345678", ((TelefonoFijo)cliente.getServicios().get(0)).getNumero());
    }

    @Test
    public void testNoDuplicarServicios() throws Exception {
        EmpresaTelecomunicaciones empresa = EmpresaTelecomunicaciones.getInstancia();
        empresa.agregarTelefonoFijo("22223333");
        empresa.agregarPersonaNatural("Maria Lopez", "Calle 2", "Playa", "La Habana", "90010254321");

        Cliente cliente = empresa.buscarCliente("Maria Lopez");
        empresa.asignarTelefonoFijo(cliente, "22223333");
        Exception ex = null;
        try {
            empresa.asignarTelefonoFijo(cliente, "22223333");
        } catch (IllegalArgumentException e) {
            ex = e;
        }
        assertNotNull(ex);
        assertTrue(ex.getMessage().toLowerCase().contains("no hay teléfono fijo disponible"));
    }

    @Test
    public void testNoAgregarPersonaNaturalRepetida() throws Exception {
        EmpresaTelecomunicaciones empresa = EmpresaTelecomunicaciones.getInstancia();
        empresa.agregarPersonaNatural("Luis Perez", "Calle 3", "Playa", "La Habana", "90010333333");
        Exception ex = null;
        try {
            empresa.agregarPersonaNatural("Luis Perez", "Calle 3", "Playa", "La Habana", "90010333333");
        } catch (DuplicadosException e) {
            ex = e;
        }
        assertNotNull(ex);
        assertTrue(ex.getMessage().toLowerCase().contains("ya existe"));
    }

    @Test
    public void testEliminarClienteYServicios() throws Exception {
        EmpresaTelecomunicaciones empresa = EmpresaTelecomunicaciones.getInstancia();
        empresa.agregarTelefonoFijo("88887777");
        empresa.agregarPersonaNatural("Pedro Soto", "Calle 4", "Playa", "La Habana", "90010444444");

        Cliente cliente = empresa.buscarCliente("Pedro Soto");
        empresa.asignarTelefonoFijo(cliente, "88887777");

        assertEquals(1, cliente.getServicios().size());
        empresa.eliminarCliente(cliente);
        assertNull(empresa.buscarCliente("Pedro Soto"));
        boolean existeTelefono = false;
        for (Servicio s : empresa.getServicios()) {
            if (s instanceof TelefonoFijo && ((TelefonoFijo) s).getNumero().equals("88887777")) {
                existeTelefono = true;
            }
        }
        assertFalse(existeTelefono);
    }
}