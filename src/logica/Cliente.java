package logica;
import java.util.ArrayList;

public abstract class Cliente {
    // Atributos
    protected String nombre;
    protected String direccion;
    protected ArrayList<Servicio> servicios;
        
    // Constructor
    public Cliente(String nombre,String direccion) {
        setDireccion(direccion);
        setNombre(nombre);
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

    // Nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    //Agregar Servicio 
    public void addServicio(Servicio s){
    	servicios.add(s);
    	
    }
    //METODOS
    public ArrayList<Telefono> obtenerTelefonos(){
    	
    	ArrayList<Telefono> telefonos = new ArrayList<Telefono>();
    	
    	for(Servicio s: servicios){
    		
    		if(s instanceof Telefono){
    			telefonos.add((Telefono)s);
    		}
    	}	
    	
    	
    	return telefonos;
    	
    }
        
}
