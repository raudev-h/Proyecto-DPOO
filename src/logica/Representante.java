package logica;

public class Representante {
    //Atributos
    private String nombre;
    private String apellidos;
    private String numId;
    
    // Constructor
    public Representante(String nombre, String apellidos, String numId) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.numId = numId;
    }

    // Getters y Setters
    // NOmbre del Representante
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Apellidos del Representante
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    // Numero de id del Representante
    public String getNumId() {
        return numId;
    }
    public void setNumId(String numId) {
        this.numId = numId;
    } 
}
