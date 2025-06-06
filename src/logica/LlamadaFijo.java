package logica;

public class LlamadaFijo extends Llamada{

    //ATRIBUTOS
    protected String provincia;
    //CONSTRUCTOR
    public LlamadaFijo(double duracion, String numeroDestino, String provincia){

        super(duracion, numeroDestino);
        setProvincia(provincia);

    }
    //GETTER Y SETTER 
    //Provincia
    public void setProvincia(String provincia){
        this.provincia = provincia;
    }

    public String getProvincia(){
        return provincia;
    }
    //METODOS

    
}
 