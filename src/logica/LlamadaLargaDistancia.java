package logica;

public class LlamadaLargaDistancia extends LlamadaFijo {

    //ATRIBUTOS
    private String municipio;
    private double totalFacturar;
    //CONSTRUCTOR
    public LlamadaLargaDistancia(double duracion, String numeroDestino, String provincia,String municipio,
            double totalFacturar){
            
                super(duracion, numeroDestino, provincia);
                setMunicipio(municipio);
                setTotalFacturar(totalFacturar);

            }
    //GETTER Y SETTER 
    //Provincia
    public void setMunicipio(String municipio){

        this.municipio = municipio;
    }
    public String getMunicipio(){

        return municipio;
    }

    //Total a facturar
    public void setTotalFacturar(double totalFacturar){

        this.totalFacturar = totalFacturar;
    }
    public double getTotalFacturar(){
        return totalFacturar;
    }
    //METODO    


    
}
