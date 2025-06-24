package logica;

import excepciones.*;

public class PersonaJuridica extends ClientesConUbicacion {
    // Atributos
    //private String nombreEmpresa;
    private String organismo;
    private Representante representantePersonaJuridica;
    
    // Constructor
    public PersonaJuridica(String nombre,String direccion, String municipio, String provincia,
             String organismo, Representante representantePersonaJuridica)
              throws NombreInvalidoException, UbicacionInvalidaException, ProvinciaInvalidaException{
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
    public void setOrganismo(String organismo) throws NombreInvalidoException {
        if(organismo == null || organismo.trim().isEmpty())
            throw new NombreInvalidoException("El organismo al que pertenece no puede estar vacio");
        
        else if(!(nombre.trim().matches(("[a-zA-ZáéíóúÁÉÍÓÚñÑ]+( [a-zA-ZáéíóúÁÉÍÓÚñÑ]+)*"))))
            throw new NombreInvalidoException("El organismo al que pertenece solo puede contener letras");
            
        else    
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

   //METODOS 
    //Validar Organismo 
    public static void validarOrganismo(String organismo) throws NombreInvalidoException {
        if(organismo == null || organismo.trim().isEmpty())
            throw new NombreInvalidoException("El organismo al que pertenece no puede estar vacío");
        
        else if(!(organismo.trim().matches(("[a-zA-ZáéíóúÁÉÍÓÚñÑ]+( [a-zA-ZáéíóúÁÉÍÓÚñÑ]+)*"))))
            throw new NombreInvalidoException("El organismo al que pertenece solo puede contener letras");

    }

    
}
