package logica;

import java.util.ArrayList;
import java.util.regex.Pattern;




public class TelefonoMovil extends Telefono {

     //ATRIBUTOS
     protected double montoApagar;
     
    //CONSTRUCTOR
    public TelefonoMovil(Cliente titular,String numero, double montoApagar){

        super(titular,numero);
        setMontoApagar(montoApagar);

    }
    
    // CONSTRUCTOR DE TELEFONO SIN TITULAR
    public TelefonoMovil(String numero, double montoApagar){

        super(null,numero);
        setMontoApagar(montoApagar);

    }
    
    //GETTER Y SETTER 
    //Monto a pagar
    public void setMontoApagar(double montoApagar){
        this.montoApagar = montoApagar;
    }

    public double getMontoApagar(){
        return montoApagar;
    }
    
    
    //METODOS
    //@Override
    //Metodo para hacer una llamada
    public Llamada hacerLlamada(String numeroDestino,double duracion){
        LlamadaMovil llamada = new LlamadaMovil(duracion,numeroDestino);

        return llamada;
    }
    
    //Ignored
    public Llamada hacerLlamada(String numeroDestino,double duracion,String provincia){

        return null;
    }
    
    //Metodo para buscar todas las llamadas que superaron X min de duracion
    public ArrayList<Llamada> llamadasMasMin(int minutos){

        ArrayList<Llamada> llamadasMayores = new ArrayList<Llamada>();

        for(Llamada ll: llamadas){

            if(ll instanceof LlamadaMovil){
            //Se convierten la llamadas de segundos a minutos y se verifiaca si es mayor que el limite
                if((ll.getDuracion()/ 60.0) > minutos){
                    llamadasMayores.add(ll);
                }
            }
        }

        return llamadasMayores;
    }
    
    //Metodo para buscar la duracion Maxima de llamadas
    public int duracionMaxima (ArrayList<Llamada> llamadas ){
    	
    	double duracionMax = 0;
    	
    	for(Llamada ll: llamadas){
    		if(ll.getDuracion() > duracionMax)
    			duracionMax = ll.getDuracion();
    		
    	}   	
    		
    	return (int) Math.ceil(duracionMax / 60.0);
    }
    
 // Obtener el nombre en la interfaz
    @Override
    public String toString() {
        return numero; 
    }

 
    public static String validarTelefonoMovil(String telefono) throws IllegalArgumentException {
        String regex_movil = "^(\\+53)?[56]\\d{7}$";
        String regex_espacios = ".*\\s+.*";
        
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de teléfono no puede estar vacío.");
        }

        // Verifica si contiene espacios (incluyendo intermedios)
        if (Pattern.matches(regex_espacios, telefono)) {
            throw new IllegalArgumentException("El número no puede contener espacios.");
        }

        // Verifica el formato general
        if (!Pattern.matches(regex_movil, telefono)) {
            throw new IllegalArgumentException("Número móvil inválido.");
        }

        // Eliminar el +53 si existe y devolver el número limpio
        return telefono.replaceAll("^\\+53", "");
    }
    
    public static void validarMonto(String montoStr) throws IllegalArgumentException {
        if (montoStr == null || montoStr.trim().isEmpty()) {
            throw new IllegalArgumentException("El monto no puede estar vacío.");
        }

        try {
            double monto = Double.parseDouble(montoStr.replace(",", ".")); // Acepta tanto "." como "," como separador decimal
            
            if (monto < 0) {
                throw new IllegalArgumentException("El monto no puede ser negativo.");
            }
            
            
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("El monto debe ser un número válido ");
        }
        System.out.print(montoStr + ": Paso por todas las validaciones");
    }

    
}
