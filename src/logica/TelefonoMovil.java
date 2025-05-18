package logica;

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
    @Override
    public Llamada hacerLlamada(String numeroDestino,double duracion, String ignored){
        LlamadaMovil llamada = new LlamadaMovil(duracion,numeroDestino);

        return llamada;
    }
    
}
