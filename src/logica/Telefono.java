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
    
    
    
}
