package logica;
import java.util.HashMap;
import java.util.Map;

public class CuentaNauta extends Servicio{
    // TODO
    // Es de tipo Mapa para que despues podamos cambiarlo en caso de que necesitemos algo especifico de un mapa 
    // con caracteristicas propias, ver en la documentacion que Map es un tipo de Interfaz y HashMap una implementacion
    private Map<String, MesDatos> mesDatos; 
    private String nick;


    // Constructor
    public CuentaNauta(Cliente titularCliente,String nick) {
        super(titularCliente);
        setNick(nick);
        mesDatos = new HashMap<String,MesDatos>();
    }

    // Getters y setters
    // Nick de la cuenta
    public String getNick() {
        return nick;
    }
    public void setNick(String nick) {
        this.nick = nick;
    }
    // HashMap
     public Map<String, MesDatos> getMesDatos() {
        return mesDatos;
    }
    public void setMesDatos(Map<String, MesDatos> mesDatos) {
        this.mesDatos = mesDatos;
    }
    
    // Método para agregar los datos de un mes dado
    // Si hay una mejor manera de hacer esto, por favor comunicar
    public void agregarMesDatos(String mes, double kbEnviadosInternacional, double kbEnviadosNacional, double kbRecibidosInternacional,
     double kbRecibidosNacional, double kbNavegacion, 
    double montoEnvioInternacional, double montoEnvioNacional, double montoNavegacion,
     double montoRecepcionInternacional, double montoRecepcionNacional, double montoTotal){
        
        MesDatos m1 = new MesDatos(kbRecibidosNacional, kbEnviadosNacional, kbRecibidosInternacional, kbEnviadosInternacional, kbNavegacion,
        montoEnvioNacional, montoRecepcionNacional, montoEnvioInternacional, montoRecepcionInternacional, montoNavegacion, montoTotal);
        
        // Convertimos el mes a mayuscula para que no haya problema a la hora de buscar en el Mapa
        String mesValido = mes.toUpperCase();
        mesDatos.put(mesValido,m1);
    }

    // Método para obtener los datos de un mes específico
    public MesDatos obtenerDatosMes(String mes){
        return mesDatos.get(mes.toUpperCase()); // convertimos el mes que quieras buscar en mayusculas
    }
    
}
