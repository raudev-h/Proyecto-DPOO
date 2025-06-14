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
        if(nombreCompleto == null || nombreCompleto.trim().isEmpty()){
            throw new NombreInvalidoException("El nombre no puede estar vac√≠o");
        }
        else if(!(nombreCompleto.trim().matches(("[a-zA-Z·ÈÌÛ˙¡…Õ”⁄Ò—]+( [a-zA-Z·ÈÌÛ˙¡…Õ”⁄Ò—]+)*")))){
            throw new NombreInvalidoException("El nombre solo puede contener letras");
        }
        else 
            this.nombreCompleto = nombreCompleto.trim();
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
