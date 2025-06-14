package logica;

import java.nio.file.DirectoryIteratorException;

import excepciones.*;

public class EntidadNoEstatal extends Cliente {
    // Atributos
   // private String nombreEntidad;
    private Representante representanteEntidad;
    
    // Constructor
      public EntidadNoEstatal(String nombre,String direccion ,Representante representanteEntidad)
                             throws NombreInvalidoException, DireccionInvalidaException {
        super(nombre,direccion);
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
