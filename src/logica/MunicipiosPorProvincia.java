package logica;
import java.util.HashMap;
import java.util.Map;

public class MunicipiosPorProvincia {
    public static final Map<String, String[]> MUNICIPIOS_PROVINCIA = new HashMap<String, String[]>();

    static {
        MUNICIPIOS_PROVINCIA.put("Pinar del Río", new String[]{"Consolación del Sur", "Guane", "La Palma", "Los Palacios", 
            "Mantua", "Minas de Mateliahambre", "Pinar del Río", "San Juan y Martínez", 
            "San Luis", "Sandino", "Viñales"});
        MUNICIPIOS_PROVINCIA.put("Artemisa", new String[]{"Alquízar", "Artemisa", "Bahía Honda", "Bauta", 
            "Caimito", "Candelaria", "Guanajay", "Güira de Melena", "Mariel", 
            "San Antonio de los Baños", "San Cristóbal"});
        MUNICIPIOS_PROVINCIA.put("La Habana", new String[]{"Arroyo Naranjo", "Boyeros", "Centro Habana", "Cerro", 
            "Cotorro", "Diez de Octubre", "Guanabacoa", "La Habana del Este", 
            "La Habana Vieja", "La Lisa", "Marianao", "Playa", "Plaza de la Revolución", 
            "Regla", "San Miguel del Padrón"});
        MUNICIPIOS_PROVINCIA.put("Mayabeque", new String[]{"Batabanó", "Bejucal", "Güines", "Jaruco", 
            "Madruga", "Melena del Sur", "Nueva Paz", "Quivicán", 
            "San José de las Lajas", "San Nicolás", "Santa Cruz del Norte"});
        MUNICIPIOS_PROVINCIA.put("Matanzas", new String[]{"Calimete", "Cárdenas", "Ciudad de Matanzas", "Colón", 
            "Jagüey Grande", "Jovellanos", "Limonar", "Los Arabos", 
            "Martí", "Pedro Betancourt", "Perico", "Unión de Reyes", "Varadero"});
        MUNICIPIOS_PROVINCIA.put("Cienfuegos", new String[]{"Abreus", "Aguada de Pasajeros", "Cienfuegos", "Cruces", 
            "Cumanayagua", "Lajas", "Palmira", "Rodas"});
        MUNICIPIOS_PROVINCIA.put("Villa Clara", new String[]{"Caibarién", "Camajuaní", "Cifuentes", "Corralillo", 
            "Encrucijada", "Manicaragua", "Placetas", "Quemado de Güines", 
            "Ranchuelo", "Remedios", "Sagua la Grande", "Santa Clara", "Santo Domingo"});
        MUNICIPIOS_PROVINCIA.put("Sancti Spíritus", new String[]{"Cabaiguán", "Fomento", "Jatibonico", "La Sierpe", 
            "Sancti Spíritus", "Taguasco", "Trinidad", "Yaguajay"});
        MUNICIPIOS_PROVINCIA.put("Ciego de Ávila", new String[]{"Baraguá", "Bolivia", "Chambas", "Ciego de Ávila", 
            "Ciro Redondo", "Florencia", "Majagua", "Morón", "Primero de Enero", "Venezuela"});
        MUNICIPIOS_PROVINCIA.put("Camagüey", new String[]{"Camagüey", "Carlos M. de Céspedes", "Esmeralda", "Florida", 
            "Guáimaro", "Jimaguayú", "Minas", "Najasa", "Nuevitas", "Santa Cruz del Sur", 
            "Sibanicú", "Sierra de Cubitas", "Vertientes"});
        MUNICIPIOS_PROVINCIA.put("Las Tunas", new String[]{"Amancio", "Colombia", "Jesús Menéndez", "Jobabo", 
            "Las Tunas", "Majibacoa", "Manatí", "Puerto Padre"});
        MUNICIPIOS_PROVINCIA.put("Holguín", new String[]{"Antilla", "Báguanos", "Banes", "Cacocum", 
            "Calixto García", "Cueto", "Frank País", "Gibara", 
            "Holguín", "Mayarí", "Moa", "Rafael Freyre", "Sagua de Tánamo", "Urbano Noris"});
        MUNICIPIOS_PROVINCIA.put("Granma", new String[]{"Bartolomé Masó", "Bayamo", "Buey Arriba", "Campechuela", 
            "Cauto Cristo", "Guisa", "Jiguaní", "Manzanillo", 
            "Media Luna", "Niquero", "Pilón", "Río Cauto", "Yara"});
        MUNICIPIOS_PROVINCIA.put("Santiago de Cuba", new String[]{"Contramaestre", "Guamá", "Mella", "Palma Soriano", 
            "San Luis", "Santiago de Cuba", "Segundo Frente", "Songo-La Maya", "Tercer Frente"});
        MUNICIPIOS_PROVINCIA.put("Guantánamo", new String[]{"Baracoa", "Caimanera", "El Salvador", "Guantánamo", 
            "Imías", "Maisí", "Manuel Tames", "Niceto Pérez", "San Antonio del Sur", "Yateras"});
        MUNICIPIOS_PROVINCIA.put("Isla de la Juventud", new String[]{"Isla de la Juventud"});
    }
}