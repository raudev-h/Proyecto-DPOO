package logica;

import excepciones.NombreInvalidoException;

public class Representante {
    //Atributos
    private String nombreCompleto;
    private String numId;
    private Cliente clienteRepresentado;
  
    // Constructor
    public Representante(String nombreCompleto, String numId) throws NombreInvalidoException {
        setNombreCompleto(nombreCompleto);
        setNumId(numId);
    }

    // Getters y Setters
    // NOmbre del Representante
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public void setNombreCompleto(String nombreCompleto) throws NombreInvalidoException {
        if(nombre == null || nombre.trim().isEmpty()){
            throw new NombreInvalidoException("El nombre no puede estar vacío");
        }
        else if(!(nombre.trim().matches(("[a-zA-Z������������]+( [a-zA-Z������������]+)*")))){
            throw new NombreInvalidoException("El nombre solo puede contener letras");
        }
        else 
            this.nombre = nombre.trim();
    }
    // Numero de id del Representante
    public String getNumId() {
        return numId;
    }
    public void setNumId(String numId) {
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
