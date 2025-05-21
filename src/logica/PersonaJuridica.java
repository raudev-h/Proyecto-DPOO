package logica;

public class PersonaJuridica extends ClientesConUbicacion {
    // Atributos
    private String nombreEmpresa;
    private String organismo;
    private Representante representantePersonaJuridica;
    
    // Constructor
    public PersonaJuridica(String direccion, String municipio, String provincia, String nombreEmpresa,
             String organismo, Representante representantePersonaJuridica) {
        super(direccion, municipio, provincia);
        setNombreEmpresa(nombreEmpresa);
        setOrganismo(organismo);
        setRepresentantePersonaJuridica(representantePersonaJuridica);        
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
    public Representante getRepresentantePersonaJuridica() {
        return representantePersonaJuridica;
    }
    public void setRepresentantePersonaJuridica(Representante representante) {
        this.representantePersonaJuridica = representante;
    }

    

    
}
