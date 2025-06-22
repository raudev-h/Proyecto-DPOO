package logica;

import java.util.ArrayList;




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
    
}
