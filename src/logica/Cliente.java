package logica;

import java.util.ArrayList;

public abstract class Cliente {
    // Atributos
    protected String nombre;
    protected String direccion;
    protected ArrayList<Servicio> servicios;

    // Constructor
    public Cliente(String nombre, String direccion) {
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

    // MÉTODOS
    // Agregar Servicio
    public void addServicio(Servicio s) {
        servicios.add(s);
    }
    
  // Obtener todos los telefonos existentes
    public ArrayList<Telefono> obtenerTelefonos(){
    	
    	ArrayList<Telefono> telefonos = new ArrayList<Telefono>();
    	
    	for(Servicio s: servicios){
    		
    		if(s instanceof Telefono){
    			telefonos.add((Telefono)s);
    		}
    	}	
    		
    	return telefonos;
    }
  
    // Obtener el monto de las llamadas de los clientes
    public double getMontoLlamadasLargaDistancia() {
        double monto = 0;

        for (Servicio s : servicios) {
            if (s instanceof TelefonoFijo) {
                TelefonoFijo tf = (TelefonoFijo) s;
                for (int i = 0; i < tf.getLlamadasLargas().size(); i++)
                    monto += tf.getLlamadasLargas().get(i).getTotalFacturar();
            }
        }
        return monto;
    }
}
