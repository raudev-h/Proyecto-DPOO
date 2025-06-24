package prueba;

import logica.*;
import excepciones.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClienteRobustezTest {

    @Test
    public void testAgregarServicioNulo() throws Exception {
        Cliente c = new PersonaNatural("Nulo", "Calle", "Playa", "La Habana", "90101010101");
        try {
            c.addServicio(null);
            assertEquals(1, c.getServicios().size()); // Esto revela si permite nulos
            assertNull(c.getServicios().get(0));
        } catch (Exception e) {
            // Si tu código lanza excepción, también es válido
            assertTrue(e instanceof NullPointerException || e instanceof IllegalArgumentException);
        }
    }

    @Test
    public void testAgregarMuchosServicios() throws Exception {
        Cliente c = new PersonaNatural("Muchos", "Calle", "Playa", "La Habana", "90102020202");
        for (int i = 0; i < 1000; i++) {
            TelefonoFijo tf = new TelefonoFijo("1000" + i);
            c.addServicio(tf);
        }
        assertEquals(1000, c.getServicios().size());
    }

    @Test
    public void testDuplicarServicioReferenciaDistinta() throws Exception {
        Cliente c = new PersonaNatural("Refs", "Calle", "Playa", "La Habana", "90103030303");
        TelefonoFijo tf1 = new TelefonoFijo("55555555");
        TelefonoFijo tf2 = new TelefonoFijo("55555555");
        c.addServicio(tf1);
        c.addServicio(tf2); // ¿Permite dos objetos distintos pero con mismo número?
        assertEquals(2, c.getServicios().size());
    }

    @Test
    public void testServiciosCompartidosEntreClientes() throws Exception {
        TelefonoFijo tf = new TelefonoFijo("99999999");
        Cliente c1 = new PersonaNatural("A", "Calle", "Playa", "La Habana", "90104040404");
        Cliente c2 = new PersonaNatural("B", "Calle", "Playa", "La Habana", "90105050505");
        c1.addServicio(tf);
        c2.addServicio(tf);
        assertTrue(c1.getServicios().contains(tf));
        assertTrue(c2.getServicios().contains(tf));
        assertEquals(c1.getServicios().get(0), c2.getServicios().get(0));
    }

    @Test
    public void testDatosLimiteNombreDireccion() {
        // Espacios, caracteres raros, nombre largo, dirección larga
        try {
            new PersonaNatural("     ", "Calle", "Playa", "La Habana", "90106060606");
            fail("Debería lanzar excepción por nombre vacío");
        } catch (Exception e) {
            assertTrue(e instanceof NombreInvalidoException);
        }

        try {
            new PersonaNatural("NombreConCaracteres$$$", "Calle", "Playa", "La Habana", "90107070707");
            fail("Debería lanzar excepción por caracteres inválidos");
        } catch (Exception e) {
            assertTrue(e instanceof NombreInvalidoException);
        }

        try {
            StringBuilder largo = new StringBuilder();
            for (int i = 0; i < 500; i++) largo.append('a');
            Cliente c = new PersonaNatural(largo.toString(), "Calle", "Playa", "La Habana", "90108080808");
            assertTrue(c.getNombre().length() > 100);
        } catch (Exception e) {
            // Si tu sistema limita el largo, aquí también es válido
        }
    }
    
    @Test
    public void testServiciosIndependientesEntreClientes() throws Exception {
        TelefonoFijo tf1 = new TelefonoFijo("99999999");
        TelefonoFijo tf2 = new TelefonoFijo("99999999");
        Cliente c1 = new PersonaNatural("A", "Calle", "Playa", "La Habana", "90104040404");
        Cliente c2 = new PersonaNatural("B", "Calle", "Playa", "La Habana", "90105050505");
        c1.addServicio(tf1);
        c2.addServicio(tf2);
        assertTrue(c1.getServicios().contains(tf1));
        assertTrue(c2.getServicios().contains(tf2));
        assertEquals(tf1.getNumero(), tf2.getNumero());
        assertNotSame(tf1, tf2); // Son instancias diferentes
    }
    
    @Test
    public void testServiciosNoCompartidosEntreClientes() throws Exception {
        TelefonoFijo tf = new TelefonoFijo("99999999");
        Cliente c1 = new PersonaNatural("A", "Calle", "Playa", "La Habana", "90104040404");
        Cliente c2 = new PersonaNatural("B", "Calle", "Playa", "La Habana", "90105050505");

        c1.addServicio(tf);

        boolean excepcionLanzada = false;
        try {
            c2.addServicio(tf);
        } catch (Exception e) {
            excepcionLanzada = true;
        }
        assertTrue("No se permite compartir un TelefonoFijo entre clientes, debe lanzar excepción.", excepcionLanzada);
        assertTrue(c1.getServicios().contains(tf));
        assertFalse(c2.getServicios().contains(tf));
    }
}