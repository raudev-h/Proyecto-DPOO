package prueba;

import logica.*;
import excepciones.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EmpresaTelecomunicacionesRobustezTest {

    @Before
    public void setUp() {
        EmpresaTelecomunicaciones empresa = EmpresaTelecomunicaciones.getInstancia();
        empresa.getClientes().clear();
        empresa.getServicios().clear();
        empresa.getServiciosDisponibles().clear();
        empresa.getRepresentantes().clear();
    }

    @Test
    public void testAgregarYEliminarMasivo() throws Exception {
        EmpresaTelecomunicaciones empresa = EmpresaTelecomunicaciones.getInstancia();

        for (int i = 0; i < 500; i++) {
            empresa.agregarPersonaNatural("C" + i, "Dir" + i, "Playa", "La Habana", "91" + String.format("%09d", i));
        }
        assertEquals(500, empresa.getClientes().size());

        for (int i = 0; i < 500; i++) {
            Cliente c = empresa.buscarCliente("C" + i);
            assertNotNull(c);
            empresa.eliminarCliente(c);
        }
        assertEquals(0, empresa.getClientes().size());
    }

    @Test
    public void testEliminarClienteConServiciosActivos() throws Exception {
        EmpresaTelecomunicaciones empresa = EmpresaTelecomunicaciones.getInstancia();
        empresa.agregarTelefonoFijo("22221111");
        empresa.agregarPersonaNatural("Massimo", "Calle", "Playa", "La Habana", "90202020202");
        Cliente cliente = empresa.buscarCliente("Massimo");
        empresa.asignarTelefonoFijo(cliente, "22221111");
        assertEquals(1, cliente.getServicios().size());
        empresa.eliminarCliente(cliente);
        assertNull(empresa.buscarCliente("Massimo"));
    }

    @Test
    public void testAsignarServicioNoDisponible() throws Exception {
        EmpresaTelecomunicaciones empresa = EmpresaTelecomunicaciones.getInstancia();
        empresa.agregarPersonaNatural("Nodo", "Calle", "Playa", "La Habana", "90303030303");
        Cliente cliente = empresa.buscarCliente("Nodo");
        Exception ex = null;
        try {
            empresa.asignarTelefonoFijo(cliente, "00000000");
        } catch (IllegalArgumentException e) {
            ex = e;
        }
        assertNotNull(ex);
        assertTrue(ex.getMessage().toLowerCase().contains("no hay tel"));
    }

    @Test
    public void testServiciosDuplicadosEnEmpresa() throws Exception {
        EmpresaTelecomunicaciones empresa = EmpresaTelecomunicaciones.getInstancia();
        empresa.agregarTelefonoFijo("12341234");
        empresa.agregarPersonaNatural("Duplicado", "Calle", "Playa", "La Habana", "90404040404");
        Cliente cliente = empresa.buscarCliente("Duplicado");
        empresa.asignarTelefonoFijo(cliente, "12341234");
        // Vuelve a agregar el mismo teléfono como disponible (ilegal)
        empresa.agregarTelefonoFijo("12341234");
        boolean existeTelefono = false;
        for (Servicio s : empresa.getServiciosDisponibles()) {
            if (s instanceof TelefonoFijo && ((TelefonoFijo) s).getNumero().equals("12341234")) {
                existeTelefono = true;
            }
        }
        assertTrue(existeTelefono);
    }
}