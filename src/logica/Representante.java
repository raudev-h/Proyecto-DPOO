package logica;

import excepciones.CarnetIdentidadInvalidoException;
import excepciones.NombreInvalidoException;

public class Representante {
    //Atributos
    private String nombreCompleto;
    private String numId;
    private Cliente clienteRepresentado;
  
    // Constructor
    public Representante(String nombreCompleto, String numId) throws NombreInvalidoException, CarnetIdentidadInvalidoException {
        setNombreCompleto(nombreCompleto);
        setNumId(numId);
    }

    // Getters y Setters
    // NOmbre del Representante
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public void setNombreCompleto(String nombreCompleto) throws NombreInvalidoException {
        if(nombreCompleto == null || nombreCompleto.trim().isEmpty())
            throw new NombreInvalidoException("El nombre no puede estar vac√≠o");
        
        else if(!(nombreCompleto.trim().matches(("[a-zA-Z·ÈÌÛ˙¡…Õ”⁄Ò—]+( [a-zA-Z·ÈÌÛ˙¡…Õ”⁄Ò—]+)*"))))
            throw new NombreInvalidoException("El nombre solo puede contener letras");
        
        else 
            this.nombreCompleto = nombreCompleto.trim();
    }
    // Numero de id del Representante
    public String getNumId() {
        return numId;
    }
    public void setNumId(String numId) throws CarnetIdentidadInvalidoException{
        if(numId == null || numId.trim().isEmpty())
        	throw new CarnetIdentidadInvalidoException("El carnet no puede estar vacÌo");
        
        else if(!(numId.matches("\\d{11}"))){
        	throw new CarnetIdentidadInvalidoException("El carnet debe tener 11 dÌgitos numÈricos");
        }
        else
        	this.numId = numId;
        	
    }
    
    // Cliente representadoS
    public void setClienteRepresentado(Cliente clienteRepresentado){
    	this.clienteRepresentado = clienteRepresentado;
    	
    }
    public Cliente getClienteRepresentado(){
    	return clienteRepresentado;
    } 
}
