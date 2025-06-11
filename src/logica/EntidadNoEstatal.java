package logica;

import excepciones.NombreInvalidoException;

public class EntidadNoEstatal extends Cliente {
    // Atributos
   // private String nombreEntidad;
    private Representante representanteEntidad;
    
    // Constructor
      public EntidadNoEstatal(String nombre,String direccion ,Representante representanteEntidad) throws NombreInvalidoException {
        super(nombre,direccion);
        //setNombreEntidad(nombreEntidad);
        setRepresentanteEntidad(representanteEntidad);        
    }

    // Getters y Setters
    // Nombre de la Entidad
   

    // Representante
    public Representante getRepresentanteEntidad() {
        return representanteEntidad;
    }
    public void setRepresentanteEntidad(Representante representanteEntidad) {
        this.representanteEntidad = representanteEntidad;
    }
    
}
