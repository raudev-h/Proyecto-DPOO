package logica;

import excepciones.*;

public abstract class ClientesConUbicacion extends Cliente {
    // Atributos
    protected String municipio;
    protected String provincia;
    
    // Constructor
    public ClientesConUbicacion(String nombre,String direccion, String municipio, 
                                String provincia) throws NombreInvalidoException, DireccionInvalidaException,
                                ProvinciaInvalidaException {
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
    // Esta validacion pienso que debemos mejorarla ya que no es dificil
    // comprobar con las provincias reales de Cuba a pesar de no tener BD
    public void setProvincia(String provincia) throws ProvinciaInvalidaException {
        if(provincia == null || provincia.trim().isEmpty()){
            throw new ProvinciaInvalidaException("La provincia no puede estar vacia");
        }
        else
            this.provincia = provincia;
    }
}
