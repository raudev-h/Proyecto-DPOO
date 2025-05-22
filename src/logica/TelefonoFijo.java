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
    //METODOS
    //Implementacion de prueba del metodo hacer llamada 
    @Override
    public Llamada hacerLlamada(String numeroDestino,double duracion, String provincia){
        LlamadaFijo llamada = new LlamadaFijo(duracion,numeroDestino, provincia);

        return llamada;
    }
    

    
}
