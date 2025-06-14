package logica;

import excepciones.*;

public class PersonaNatural extends ClientesConUbicacion {
    // Atributos
    //private String nombre;
    private String numId;
    
    // Constructor
    public PersonaNatural(String nombre,String direccion, String municipio, String provincia, String numId)
                         throws NombreInvalidoException, UbicacionInvalidaException, ProvinciaInvalidaException, CarnetIdentidadInvalidoException{
        super(nombre,direccion, municipio, provincia);
        setNumId(numId);
    }

    // Getters y Setters

    // numero de identificacion
    public String getNumId() {
        return numId;
    }
    public void setNumId(String numId) throws CarnetIdentidadInvalidoException{
        if(numId == null || numId.trim().isEmpty())
        	throw new CarnetIdentidadInvalidoException("El carnet no puede estar vacío");
        
        else if(!(numId.matches("\\d{11}"))){
        	throw new CarnetIdentidadInvalidoException("El carnet debe tener 11 dígitos numéricos");
        }
        else
        	this.numId = numId;
        	
    }

    

    
}
