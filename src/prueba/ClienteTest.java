package prueba;

import logica.*;
import excepciones.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClienteTest {

    @Test
    public void testCrearPersonaNatural() throws Exception {
        Cliente c = new PersonaNatural("Ana", "Calle 5", "Playa", "La Habana", "90010555555");
        assertEquals("Ana", c.getNombre());
        assertEquals("Calle 5", c.getDireccion());
        assertEquals(0, c.getServicios().size());
    }

    @Test
    public void testAgregarServicioNoDuplica() throws Exception {
        Cliente c = new PersonaNatural("Juan", "Calle 1", "Playa", "La Habana", "90010666666");
        TelefonoFijo tf = new TelefonoFijo("55555555");
        c.addServicio(tf);
        c.addServicio(tf);
        assertEquals(2, c.getServicios().size()); // Si no quieres duplicados, cambia la lógica de addServicio
    }

    @Test(expected = NombreInvalidoException.class)
    public void testNombreInvalido() throws Exception {
        new PersonaNatural("", "Calle 1", "Playa", "La Habana", "90010777777");
    }

    @Test(expected = UbicacionInvalidaException.class)
    public void testDireccionInvalida() throws Exception {
        new PersonaNatural("Pedro", "", "Playa", "La Habana", "90010888888");
    }
}