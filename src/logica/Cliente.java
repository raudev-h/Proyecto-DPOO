package logica;

import java.util.ArrayList;
import excepciones.*;

public abstract class Cliente {
    // Atributos
    protected String nombre;
    protected String direccion;
    protected ArrayList<Servicio> servicios;

    // Constructor
    public Cliente(String nombre, String direccion) throws NombreInvalidoException, UbicacionInvalidaException {
        setDireccion(direccion);
        setNombre(nombre);
        servicios = new ArrayList<Servicio>();
    }

    // GETTERS Y SETTERS
    // Direccion
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) throws UbicacionInvalidaException {
        if(direccion == null || direccion.trim().isEmpty()){
           throw new UbicacionInvalidaException("La direccion no puede estar vac√≠a");
        }
        else 
            this.direccion = direccion.trim();
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

    public void setNombre(String nombre) throws NombreInvalidoException {
    
    	if(nombre == null || nombre.trim().isEmpty()){
            throw new NombreInvalidoException("El nombre no puede estar vac√≠o");
        }
        else if(!(nombre.trim().matches(("[a-zA-Z·ÈÌÛ˙¡…Õ”⁄Ò—]+( [a-zA-Z·ÈÌÛ˙¡…Õ”⁄Ò—]+)*")))){
            throw new NombreInvalidoException("El nombre solo puede contener letras");
        }
        else 
            this.nombre = nombre.trim();
    }
    
    //======================= Aniel  metodos estaticos para validar 
    public static void validarNombre(String nombre) throws NombreInvalidoException {
    
    	if(nombre == null || nombre.trim().isEmpty()){
            throw new NombreInvalidoException("El nombre no puede estar vacÌo");
        }
        else if(!(nombre.trim().matches(("[a-zA-Z·ÈÌÛ˙¡…Õ”⁄Ò—]+( [a-zA-Z·ÈÌÛ˙¡…Õ”⁄Ò—]+)*")))){
            throw new NombreInvalidoException("El nombre solo puede contener letras");
        }

    }
    public static void validarDireccion(String direccion) throws UbicacionInvalidaException {
        if(direccion == null || direccion.trim().isEmpty()){
            throw new UbicacionInvalidaException("La direccion no puede estar vacÌa");
         }

     }
    
    
    //========================

    // M…TODOS
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
        
	//Buscar el primer telefono (Devuelve el numero solamente, No disponible en caso de no tener)
	public String buscarPrimerTelefono(){
		
		String numeroTelefono = "No diponible";
		
				if(!this.obtenerTelefonos().isEmpty()){
					
					numeroTelefono = this.obtenerTelefonos().get(0).getNumero();													
				}
		
			
		return numeroTelefono;
	}
	
	//Buscar el total de meses que superaron los 1000 cup de todas sus Cuentas Nautas
	public int cantMesesMasMilCuentasNautas(){
		
		int cantidad = 0;
		
		for(Servicio s: servicios ){
			
			if(s instanceof CuentaNauta){
				
				cantidad += ((CuentaNauta)s).cantMesesMasMilGasto();
			}
			
		}
		
		return cantidad;
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
    
    // Obtener el nombre en la interfaz
    @Override
    public String toString() {
        return nombre; 
    }

}
