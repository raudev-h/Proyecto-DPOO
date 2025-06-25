package logica;                                             

public abstract class ClientesConUbicacion extends Cliente {
    // Atributos
    protected String municipio;
    protected String provincia;
    
    // Constructor
    public ClientesConUbicacion(String nombre,String direccion, String municipio, String provincia) {
        super(nombre,direccion);
       setMunicipio(municipio);
       setProvincia(provincia);
    }

    // Getters y setters
    // Municipio
    public String getMunicipio() {
        return municipio;
    }
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    // Provincia
    public String getProvincia() {
        return provincia;
    }
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    
}
