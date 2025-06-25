package logica;

import java.util.ArrayList;

public class Factura { 
    
     //ATRIBUTOS
    private double tarifaFija;
    private double montoLlamadasLocales;
    private double montoLLamdasLargaDistancia;
    private double atraso;
    private double totalPagar;
    ArrayList<LlamadaLargaDistancia> llamadasLargaDistancia;

    //CONSTRUCTOR
    public Factura(double tarifaFija, double montoLlamadasLocales, double montoLLamdasLargaDistancia, double atraso,
            double totalPagar) {

        setTarifaFija(tarifaFija);
        setMontoLlamadasLocales(montoLlamadasLocales);
        setMontoLLamdasLargaDistancia(montoLLamdasLargaDistancia);
        setAtraso(atraso);
        setTotalPagar(totalPagar);

        llamadasLargaDistancia = new ArrayList<LlamadaLargaDistancia>();
    }
   
    
    //GETTER Y SETTER 
    //tarifa Fija
    public void setTarifaFija(double tarifaFija) {
        this.tarifaFija = tarifaFija;
    }

     public double getTarifaFija() {
        return tarifaFija;
    }

    //Monto por llamadas Locales
    public void setMontoLlamadasLocales(double montoLlamadasLocales) {
        this.montoLlamadasLocales = montoLlamadasLocales;
    }

    public double getMontoLlamadasLocales() {
        return montoLlamadasLocales;
    }
    //Monto por llamadas de Larga distancia
    public void setMontoLLamdasLargaDistancia(double montoLLamdasLargaDistancia) {
        this.montoLLamdasLargaDistancia = montoLLamdasLargaDistancia;
    }

    public double getMontoLLamdasLargaDistancia() {
        return montoLLamdasLargaDistancia;
    }

    //Atraso

    public void setAtraso(double atraso) {
        this.atraso = atraso;
    }

     public double getAtraso() {
        return atraso;
    }

    //Total a pagar
    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    //Faltan los getter y setters que tienen que ver con el ArrayList de llamdadas de Larga Distancia


    //METODOS
    
    
   
    
    
   
    
    
    
    
    
    
    
    
}
