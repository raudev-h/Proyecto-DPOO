package logica;

public class PersonaNatural extends ClientesConUbicacion {
    // Atributos
    private String nombre;
    private String numId;
    
    // Constructor
    public PersonaNatural(String direccion, String municipio, String provincia, String nombre, String numId) {
        super(direccion, municipio, provincia);
        setNombre(nombre);
        setNumId(numId);
    }

    // Getters y Setters
    // Nombre
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // numero de identificacion
    public String getNumId() {
        return numId;
    }
    public void setNumId(String numId) {
        this.numId = numId;
    }

    

    
}
