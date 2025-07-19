package logica;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class TelefonoFijo extends Telefono {

    // ATRIBUTOS
    protected ArrayList<Factura> facturas;
    protected ArrayList<LlamadaLargaDistancia> llamadasLargas;

    // CONSTRUCTOR
    public TelefonoFijo(Cliente titular, String numero) {

        super(titular, numero);

        facturas = new ArrayList<Factura>();
        llamadasLargas = new ArrayList<LlamadaLargaDistancia>();

    }
    
    // TELEFONO SIN TITULAR
    public TelefonoFijo(String numero) {
        super(null, numero);
        
        facturas = new ArrayList<Factura>();
        llamadasLargas = new ArrayList<LlamadaLargaDistancia>();
    }

    // GETTER Y SETTER
    // Facturas
    public ArrayList<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(ArrayList<Factura> facturas) {
        this.facturas = facturas;
    }

    // Llamadas de larga distancia
    public ArrayList<LlamadaLargaDistancia> getLlamadasLargas() {
        return llamadasLargas;
    }

    public void setLlamadasLargas(ArrayList<LlamadaLargaDistancia> llamadasLargas) {
        this.llamadasLargas = llamadasLargas;
    }

    // METODOS
    // Implementacion de prueba del metodo hacer llamada
    // @Override
    public Llamada hacerLlamada(String numeroDestino, double duracion, String provincia) {
        LlamadaFijo llamada = new LlamadaFijo(duracion, numeroDestino, provincia);

        return llamada;
    }
   
    //Ignored 
   public Llamada hacerLlamada(String numero, double duracion){
	   
	   return null;
   }
    

    // Agregar llamada larga distancia
    public void agregarLlamadaLargaDistancia(double duracion, String numeroDestino, String provincia,
            String municipio, double totalFacturar) {
        LlamadaLargaDistancia llamada = new LlamadaLargaDistancia(duracion, numeroDestino, provincia, municipio,
                totalFacturar);
        llamadasLargas.add(llamada);
    } 

    

    //Valida un número de teléfono fijo cubano.

    public static void validarTelefonoFijo(String telefono) throws IllegalArgumentException {
    	
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de teléfono no puede estar vacío.");
        }
       
     // Regex para teléfonos fijos cubanos (soporta +53 opcional y formatos con/sin espacios)
        String REGEX_FIJO = "^(\\+53[\\s-]?)?[7]\\d{7}$|^(\\+53[\\s-]?)?[2-4][1-9]\\d{6}$";
        // Elimina espacios y guiones para simplificar la validación
        String numeroLimpio = telefono.replaceAll("[\\s-]", "");
        // Verifica el formato con regex
        if (!Pattern.matches(REGEX_FIJO, numeroLimpio)) {          
            throw new IllegalArgumentException("Número de teléfono fijo inválido");
        }
    }
    
    public double calcularCostoTotalLlamadas(){
        double costo = 0;
        for(Llamada ll: llamadas){
        	 
            costo += ll.getDuracion() * 0.048;
            System.out.println("costo: " + costo);
        }
        System.out.println("Costo Total"+    Math.round(costo * 100.0) / 100.0);
        return Math.round(costo * 100.0) / 100.0;
    }
    
    public double calcularCostoTotalLlamadasLargas(){
    	double costo = 0;
        for(Llamada ll: llamadasLargas){
            costo += ll.getDuracion() * 0.75;
        }
        return Math.round(costo * 100.0) / 100.0;
    }

}
