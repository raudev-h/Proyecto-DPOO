package logica;

public class EntidadNoEstatal extends Cliente {
    // Atributos
    private String nombreEntidad;
    private Representante representanteEntidad;
    
    // Constructor
      public EntidadNoEstatal(String direccion,String nombreEntidad ,Representante representanteEntidad) {
        super(direccion);
        setNombreEntidad(nombreEntidad);
        setRepresentanteEntidad(representanteEntidad);        
    }

    // Getters y Setters
    // Nombre de la Entidad
    public String getNombreEntidad() {
        return nombreEntidad;
    }
    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }

    // Representante
    public Representante getRepresentanteEntidad() {
        return representanteEntidad;
    }
    public void setRepresentanteEntidad(Representante representanteEntidad) {
        this.representanteEntidad = representanteEntidad;
    }
    
}
