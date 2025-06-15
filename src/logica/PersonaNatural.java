package logica;

import java.time.DateTimeException;
import java.time.LocalDate;

import excepciones.*;

public class PersonaNatural extends ClientesConUbicacion {
    // Atributos
    //private String nombre;
    private String numId;
    

    // Constructor
    public PersonaNatural(String nombre,String direccion, String municipio, String provincia, String numId)
                         throws NombreInvalidoException, UbicacionInvalidaException, ProvinciaInvalidaException, CarnetIdentidadInvalidoException{
        super(nombre,direccion, municipio, provincia);
        setNumId(numId);
    }

    // Getters y Setters

    // numero de identificacion
    public String getNumId() {
        return numId;
    }
    public void setNumId(String numId) throws CarnetIdentidadInvalidoException{
        if(numId == null || numId.trim().isEmpty())
        	throw new CarnetIdentidadInvalidoException("El carnet no puede estar vacío");
        
        else if (!numId.matches("\\d{11}")) {
            throw new CarnetIdentidadInvalidoException("El carnet debe tener exactamente 11 dígitos numéricos");
        }
        
        else{
        	char [] cadena = numId.toCharArray();
        	
        	int anio = (cadena [0] - '0') * 10 + (cadena[1] - '0'); 
        	int mes = (cadena[2] - '0') * 10 + (cadena[3] - '0');
        	int dia = (cadena[4] - '0') * 10 + (cadena[5] - '0');
        	
        	// Solo se aceptan mayores de edad (18 años o más)
        	if(anio >= 0 && anio <= 7){
        		anio += 2000;
        	}
        	else
        		anio += 1900;
         try{
        	LocalDate fechaNacimiento = LocalDate.of(anio, mes, dia);
        	LocalDate hoy = LocalDate.now();
        		
        	if(fechaNacimiento.isAfter(hoy) || fechaNacimiento.plusYears(18).isAfter(hoy)){
        		throw new CarnetIdentidadInvalidoException("El carnet de identidad indica que es menor de 18 años o la fecha no existe");
        	}
        	
         }catch(DateTimeException e){
        	throw new CarnetIdentidadInvalidoException("El carnet de identidad es incorrecto");
         }
         	this.numId = numId; // luego de que se comprobó todo correctamente asignamos el valor final para el CI	
        }      		
    }

    

    
}
