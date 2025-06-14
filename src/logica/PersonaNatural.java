package logica;

import excepciones.DireccionInvalidaException;
import excepciones.NombreInvalidoException;

public class PersonaNatural extends ClientesConUbicacion {
    // Atributos
    //private String nombre;
    private String numId;
    
    // Constructor
    public PersonaNatural(String nombre,String direccion, String municipio, String provincia, String numId)
                         throws NombreInvalidoException, DireccionInvalidaException{
        super(nombre,direccion, municipio, provincia);
        setNumId(numId);
    }

    // Getters y Setters

    // numero de identificacion
    public String getNumId() {
        return numId;
    }
    public void setNumId(String numId) {
        this.numId = numId;
    }

    

    
}
