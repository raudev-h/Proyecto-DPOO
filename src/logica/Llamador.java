package logica;

public interface Llamador {

    Llamada hacerLlamada(String numero, double duracion, String provincia);
    Llamada hacerLlamada(String numero, double duracion);
    
}
