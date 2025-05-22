package logica;

public class Representante {
    //Atributos
    private String nombreCompleto;
    private String numId;
    
    // Constructor
    public Representante(String nombreCompleto, String numId) {
        this.nombreCompleto = nombreCompleto;
        this.numId = numId;
    }

    // Getters y Setters
    // NOmbre del Representante
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public void setNombre(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    // Numero de id del Representante
    public String getNumId() {
        return numId;
    }
    public void setNumId(String numId) {
        this.numId = numId;
    } 
}
