package logica;

public class PersonaJuridica extends ClientesConUbicacion {
    // Atributos
    //private String nombreEmpresa;
    private String organismo;
    private Representante representantePersonaJuridica;
    
    // Constructor
    public PersonaJuridica(String nombre,String direccion, String municipio, String provincia,
             String organismo, Representante representantePersonaJuridica) {
        super(nombre,direccion, municipio, provincia);

        setOrganismo(organismo);
        setRepresentantePersonaJuridica(representantePersonaJuridica);        
    }

    // Getters y Setters
    // Nombre de la empresa

    // Organismo al que pertenece
    public String getOrganismo() {
        return organismo;
    }
    public void setOrganismo(String organismo) {
        this.organismo = organismo;
    }

    public Representante getRepresentante(){
    	return representantePersonaJuridica;
    }
    // Representante
    public Representante getRepresentantePersonaJuridica() {
        return representantePersonaJuridica;
    }
    public void setRepresentantePersonaJuridica(Representante representante) {
        this.representantePersonaJuridica = representante;
    }

    

    
}
