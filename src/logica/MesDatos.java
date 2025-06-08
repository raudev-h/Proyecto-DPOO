package logica;

public class MesDatos {
    private double kbRecibidosNacional;
    private double kbEnviadosNacional;
    private double kbRecibidosInternacional;
    private double kbEnviadosInternacional;   
    private double kbNavegacion;
    private double montoEnvioNacional;
    private double montoRecepcionNacional;
    private double montoEnvioInternacional;
    private double montoRecepcionInternacional;
    private double montoNavegacion;
    private double montoTotal;

    // Constructor
   public MesDatos(double kbRecibidosNacional, double kbEnviadosNacional, double kbRecibidosInternacional,
            double kbEnviadosInternacional, double kbNavegacion, double montoEnvioNacional,
            double montoRecepcionNacional, double montoEnvioInternacional, double montoRecepcionInternacional,
            double montoNavegacion, double montoTotal) {

               setKbRecibidosNacional(kbRecibidosNacional);
               setKbEnviadosNacional(kbEnviadosNacional);
               setKbRecibidosInternacional(kbRecibidosInternacional);
               setKbEnviadosInternacional(kbEnviadosInternacional);
               setKbNavegacion(kbNavegacion);
               setMontoEnvioNacional(montoEnvioNacional);
               setMontoRecepcionNacional(montoRecepcionNacional);
               setMontoEnvioInternacional(montoEnvioInternacional);
               setMontoRecepcionInternacional(montoRecepcionInternacional);
               setMontoNavegacion(montoNavegacion);
               setMontoTotal(montoTotal);
    }
    // Getters y setters
    public double getKbRecibidosNacional() {
        return kbRecibidosNacional;
    }
    public void setKbRecibidosNacional(double kbRecibidosNacional) {
        this.kbRecibidosNacional = kbRecibidosNacional;
    }

    public double getKbEnviadosNacional() {
        return kbEnviadosNacional;
    }
    public void setKbEnviadosNacional(double kbEnviadosNacional) {
        this.kbEnviadosNacional = kbEnviadosNacional;
    }

    public double getKbRecibidosInternacional() {
        return kbRecibidosInternacional;
    }
    public void setKbRecibidosInternacional(double kbRecibidosInternacional) {
        this.kbRecibidosInternacional = kbRecibidosInternacional;
    }

    public double getKbEnviadosInternacional() {
        return kbEnviadosInternacional;
    }
    public void setKbEnviadosInternacional(double kbEnviadosInternacional) {
        this.kbEnviadosInternacional = kbEnviadosInternacional;
    }

    public double getKbNavegacion() {
        return kbNavegacion;
    }
    public void setKbNavegacion(double kbNavegacion) {
        this.kbNavegacion = kbNavegacion;
    }

    public double getMontoEnvioNacional() {
        return montoEnvioNacional;
    }
    public void setMontoEnvioNacional(double montoEnvioNacional) {
        this.montoEnvioNacional = montoEnvioNacional;
    }

    public double getMontoRecepcionNacional() {
        return montoRecepcionNacional;
    }
    public void setMontoRecepcionNacional(double montoRecepcionNacional) {
        this.montoRecepcionNacional = montoRecepcionNacional;
    }

    public double getMontoEnvioInternacional() {
        return montoEnvioInternacional;
    }
    public void setMontoEnvioInternacional(double montoEnvioInternacional) {
        this.montoEnvioInternacional = montoEnvioInternacional;
    }

    public double getMontoRecepcionInternacional() {
        return montoRecepcionInternacional;
    }
    public void setMontoRecepcionInternacional(double montoRecepcionInternacional) {
        this.montoRecepcionInternacional = montoRecepcionInternacional;
    }

    public double getMontoNavegacion() {
        return montoNavegacion;
    }
    public void setMontoNavegacion(double montoNavegacion) {
        this.montoNavegacion = montoNavegacion;
    }

    public double getMontoTotal() {
        return montoTotal;
    }
    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }
<<<<<<< HEAD
=======
    
    //METODOS
    
    public double calcularTotalGastadoKb(){
    	
    	return  kbRecibidosNacional+ kbEnviadosNacional + kbRecibidosInternacional + kbEnviadosInternacional + kbNavegacion;
    }
>>>>>>> d4356db030773f4e8df4e2e87ede1d32e00f39e0

    
}

