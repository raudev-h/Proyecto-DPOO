package logica;

import java.util.ArrayList;

public class TelefonoFijo  extends Telefono{

     //ATRIBUTOS
     protected ArrayList<Factura> facturas;
     protected ArrayList<LlamadaLargaDistancia> llamadasLargas;
    //CONSTRUCTOR
    public TelefonoFijo(Cliente titular,String numero){

        super(titular,numero);

        facturas = new ArrayList<Factura>();
        llamadasLargas = new ArrayList<LlamadaLargaDistancia>();

    }
    //GETTER Y SETTER 
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

    //METODOS
    //Implementacion de prueba del metodo hacer llamada 
    @Override
    public Llamada hacerLlamada(String numeroDestino,double duracion, String provincia){
        LlamadaFijo llamada = new LlamadaFijo(duracion,numeroDestino, provincia);

        return llamada;
    }

    
}
