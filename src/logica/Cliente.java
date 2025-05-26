package logica;
import java.util.ArrayList;

public abstract class Cliente {
    // Atributos
    protected String direccion;
    protected ArrayList<Servicio> servicios;
        
    // Constructor
    public Cliente(String direccion) {
        setDireccion(direccion);
        servicios = new ArrayList<Servicio>();
    }

    // Getters y setters 
    // Direccion
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    // Servicios
    public ArrayList<Servicio> getServicios() {
        return servicios;
    }
    public void setServicios(ArrayList<Servicio> servicios) {
        this.servicios = servicios;
    }
    
    

    
}
