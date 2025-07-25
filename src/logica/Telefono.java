package logica;

import java.util.ArrayList;
public abstract class Telefono extends Servicio implements Llamador {

    //ATRIBUTOS
    protected String numero;
    protected ArrayList<Llamada> llamadas;

    //CONSTRUCTOR
    public Telefono(Cliente titular,String numero){

        super(titular);
        setNumero(numero);
        llamadas = new ArrayList<Llamada>();
    }

    //GETTER Y SETTER 
    //numero
    public void setNumero(String numero){
    	
        this.numero = numero;
    }

    public String getNumero(){
        return numero;
    }
    //Llamadas 
    public ArrayList<Llamada> getLlamadas(){
    	return llamadas;
    }
    //METODOS
  //Agregar una llamada a la lista de llamadas del telefono
    public void agregarLlamada(Llamada ll){
    	llamadas.add(ll);
    }
    public abstract double calcularCostoTotalLlamadas();
    
    
    
}
