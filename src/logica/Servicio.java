package logica;

public abstract class Servicio{

    //ATRIBUTOS
    protected Cliente titular;

    //CONSTRUCTOR
    public Servicio(Cliente titular){
        
        setTitular(titular);
    }
    //GETTERS Y SETTERS
    //titular
    public void setTitular(Cliente titular){
        this.titular = titular;
    }

    public Cliente getTitular(){
        return titular;
    }
    //METODOS
}