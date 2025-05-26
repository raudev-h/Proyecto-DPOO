package logica;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class EmpresaTelecomunicaciones {
    // Atributos
    private ArrayList<Cliente> clientes;
    private ArrayList<Servicio> servicios;
    private ArrayList<Representante> representantes;
    
    // Constructor
    public EmpresaTelecomunicaciones() {
        clientes = new ArrayList<Cliente>();
        servicios = new ArrayList<Servicio>();
        representantes = new ArrayList<Representante>();
    }

    // Getters y setters
    // Clientes
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }
    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    // Servicios
    public ArrayList<Servicio> getServicios() {
        return servicios;
    }
    public void setServicios(ArrayList<Servicio> servicios) {
        this.servicios = servicios;
    }

    // Representante
    public ArrayList<Representante> getRepresentantes() {
        return representantes;
    }
    public void setRepresentantes(ArrayList<Representante> representantes) {
        this.representantes = representantes;
    }   

    // Provincias con la menor cantidad de cuentas nauta de Personas Naturales
    public ArrayList<Map.Entry<String, Integer>> menorCantCuentasNauta(){
        ArrayList<PersonaNatural> personasNaturales = new ArrayList<PersonaNatural>();
        String [] provincias = {"Pinar del Rio", "Artemisa", "La Habana", "Mayabeque", "Matanzas", "Cienfuegos",
                            "Villa Clara", "Sancti Spiritus", "Ciego de Avila", "Camagüey", "Las Tunas", "Holguín",
                            "Granma", "Santiago de Cuba", "Guantánamo", "Isla de la Juventud"};
        Map<String, Integer> provinciasConCuenta = new HashMap <String, Integer>();

        // Guarda en una Lista las Personas Naturales que tienen contratado el servicio NautaHogar
        for(Servicio s :servicios){
            if(s instanceof CuentaNauta){
                CuentaNauta cuentaNauta = (CuentaNauta)s;
                if(cuentaNauta.getTitular() instanceof PersonaNatural)
                    personasNaturales.add((PersonaNatural)cuentaNauta.getTitular());      
            }
        }

        // Inicializar el HashMap con las provincias
        for(int i = 0; i < 16; i++){
            provinciasConCuenta.put(provincias[i], 0);
        }

        // Aumentar los valores de las provincias
        for(PersonaNatural p : personasNaturales){
            String provinciaCliente = p.getProvincia();
            // Aumentar el valor de la provincia
            if(provinciasConCuenta.containsKey(provinciaCliente)){
                provinciasConCuenta.put(provinciaCliente, provinciasConCuenta.get(provinciaCliente) + 1);
            }    
        }    
        
        // Traspasar lo valores del HashMap al ArrayList para ordenarlo
        ArrayList<Map.Entry<String,Integer>> provinciasOrdenadas = new ArrayList<>(provinciasConCuenta.entrySet());
        provinciasOrdenadas.sort(Map.Entry.comparingByValue());

        return provinciasOrdenadas;
    }
}
