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
    public Llamada hacerLlamada(String numeroDestino,double duracion, String ignored){
        LlamadaMovil llamada = new LlamadaMovil(duracion,numeroDestino);

        return llamada;
    }
    
    //Metodo para buscar todas las llamadas que superaron X min de duracion
    public ArrayList<Llamada> llamadasMasMin(int minutos){

        ArrayList<Llamada> llamadasMayores = new ArrayList<Llamada>();

        for(Llamada ll: llamadas){

            if(ll instanceof LlamadaMovil){

            //Se convierten la llamadas de segundos a minutos y se verifiaca si es mayor que 100 min
                if((ll.getDuracion()/ 60) > minutos){
                    llamadasMayores.add(ll);
                }
            }
        }

        return llamadasMayores;
    }
    
    //Metodo para buscar la duracion Maxima de llamadas
    public double duracionMaxima (ArrayList<Llamada> llamadas ){
    	
    	double duracionMax = -1;
    	
    	for(Llamada ll: llamadas){
    		if(ll.getDuracion() > duracionMax)
    			duracionMax = ll.getDuracion();
    		
    	}   	
    		
    	return duracionMax;
    }
    
}
