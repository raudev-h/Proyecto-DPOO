package logica;
import java.util.ArrayList;

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

     
}
