package logica;

public abstract class Cliente {
    // Atributos
    protected String direccion;

        // Constructor
    public Cliente(String direccion) {
        setDireccion(direccion);
    }

    // Getters y setters 
    // Direccion
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    

    
}
