package logica;

public abstract class Llamada {

     //ATRIBUTOS
    protected double duracion;
    protected String numeroDestino;

    //CONSTRUCTOR
    public Llamada(double duracion, String numeroDestino){

        setDuracion(duracion);
        setNumeroDestino(numeroDestino);
    }
    
    //GETTER Y SETTER 
    //Duracion
    public void setDuracion(double duracion){
        this. duracion = duracion;
    }

    public double getDuracion(){
        return duracion;
    }
    //Numero de Destino
    public void setNumeroDestino(String numeroDestino){
        this.numeroDestino = numeroDestino;
    }
    public String getNumeroDestino(){
        return numeroDestino;
    }
    //METODOS
    
}
