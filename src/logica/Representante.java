package logica;

public class Representante {
    //Atributos
    private String nombreCompleto;
    private String numId;
<<<<<<< HEAD
    
=======
    private Cliente clienteRepresentado;
  
>>>>>>> d4356db030773f4e8df4e2e87ede1d32e00f39e0
    // Constructor
    public Representante(String nombreCompleto, String numId) {
        setNombreCompleto(nombreCompleto);
        setNumId(numId);
    }

    // Getters y Setters
    // NOmbre del Representante
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    // Numero de id del Representante
    public String getNumId() {
        return numId;
    }
    public void setNumId(String numId) {
        this.numId = numId;
    } 
<<<<<<< HEAD
=======
    
    // Cliente representadoS
    public void setClienteRepresentado(Cliente clienteRepresentado){
    	this.clienteRepresentado = clienteRepresentado;
    	
    }
    public Cliente getClienteRepresentado(){
    	return clienteRepresentado;
    } 
>>>>>>> d4356db030773f4e8df4e2e87ede1d32e00f39e0
}
