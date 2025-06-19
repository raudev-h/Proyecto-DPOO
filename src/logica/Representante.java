package logica;

import java.time.DateTimeException;
import java.time.LocalDate;

import excepciones.CarnetIdentidadInvalidoException;
import excepciones.NombreInvalidoException;

public class Representante {
    //Atributos
    private String nombreCompleto;
    private String numId;
    private Cliente clienteRepresentado;
  
    // Constructor
    public Representante(String nombreCompleto, String numId) throws NombreInvalidoException, CarnetIdentidadInvalidoException {
        setNombreCompleto(nombreCompleto);
        setNumId(numId);
    }

    // Getters y Setters
    // NOmbre del Representante
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public void setNombreCompleto(String nombreCompleto) throws NombreInvalidoException {
        if(nombreCompleto == null || nombreCompleto.trim().isEmpty())
            throw new NombreInvalidoException("El nombre no puede estar vacío");
        
        else if(!(nombreCompleto.trim().matches(("[a-zA-Z������������]+( [a-zA-Z������������]+)*"))))
            throw new NombreInvalidoException("El nombre solo puede contener letras");
        
        else 
            this.nombreCompleto = nombreCompleto.trim();
    }
    
    public void setNombre(String nombreCompleto){
    	this.nombreCompleto = nombreCompleto;
    }
    // Numero de id del Representante
    public String getNumId() {
        return numId;
    }
    public void setNumId(String numId) throws CarnetIdentidadInvalidoException{
        
    	if(numId == null || numId.trim().isEmpty())
        	throw new CarnetIdentidadInvalidoException("El carnet no puede estar vac�o");
        
        else if (!numId.matches("\\d{11}")) {
            throw new CarnetIdentidadInvalidoException("El carnet debe tener exactamente 11 d�gitos num�ricos");
        }
        
        else{
        	char [] cadena = numId.toCharArray();
        	
        	int anio = (cadena [0] - '0') * 10 + (cadena[1] - '0'); 
        	int mes = (cadena[2] - '0') * 10 + (cadena[3] - '0');
        	int dia = (cadena[4] - '0') * 10 + (cadena[5] - '0');
        	
        	// Solo se aceptan mayores de edad (18 a�os o m�s)
        	if(anio >= 0 && anio <= 7){
        		anio += 2000;
        	}
        	else
        		anio += 1900;
         try{
        	LocalDate fechaNacimiento = LocalDate.of(anio, mes, dia);
        	LocalDate hoy = LocalDate.now();
        		
        	if(fechaNacimiento.isAfter(hoy) || fechaNacimiento.plusYears(18).isAfter(hoy)){
        		throw new CarnetIdentidadInvalidoException("El carnet de identidad indica que es menor de 18 a�os o la fecha no existe");
        	}
        	
         }catch(DateTimeException e){
        	throw new CarnetIdentidadInvalidoException("El carnet de identidad es incorrecto");
         }
         	this.numId = numId; // luego de que se comprob� todo correctamente asignamos el valor final para el CI	
        }
    }
    
    // Cliente representadoS
    public synchronized void setClienteRepresentado(Cliente clienteRepresentado){
    	this.clienteRepresentado = clienteRepresentado;
    	
    }
    public Cliente getClienteRepresentado(){
    	return clienteRepresentado;
    } 
}
