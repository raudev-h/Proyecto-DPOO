package logica;

public class PersonaJuridica extends Cliente {
    // Atributos
    private String nombreEmpresa;
    private String organismo;
    private Representante representantePersona;
    
    // Constructor
    public PersonaJuridica(String direccion, String nombreEmpresa, String organismo, Representante representante) {
        super(direccion);
        setNombreEmpresa(nombreEmpresa);
        setRepresentante(representante);
    }

    // Getters y Setters
    // Nombre de la empresa
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    // Organismo al que pertenece
    public String getOrganismo() {
        return organismo;
    }
    public void setOrganismo(String organismo) {
        this.organismo = organismo;
    }

    // Representante
    public Representante getRepresentante() {
        return representantePersona;
    }
    public void setRepresentante(Representante representantePersona) {
        this.representantePersona = representantePersona;
    }

    

    
}
