package logica;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

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
    //Metodo para obtener la cantidad de meses que superaron los 1000 cup mensual
    public int cantMesesMasMilGasto(){

        int cantMeses = 0;
        //Iterar por el HashMap de Meses y MesDatos y contar los meses en que superen los 1000 de montoTotal
        for(MesDatos datos: mesDatos.values() ){
            if(datos.getMontoTotal() > 1000){
                cantMeses++;
            }


        }
        return cantMeses;

    }
    //Buscar los KB gastados tanto internacionales como nacionales en todos los meses
    public HashMap<String, Double > calcularKbGastadosMeses(){

        HashMap <String, Double> mesesKb = new HashMap<String, Double>();

        //Agregamos los valores de gasto en KB de todos los meses
        for(String mes: mesDatos.keySet()){
           mesesKb.put(mes, calcularGastoKbMes(mes));        
        }

        return mesesKb;
    }

    //Calcular el gasto en kb Internacionales y nacionales de un mes en especifico en el mesDatos
    public double calcularGastoKbMes(String mes){

        double total = 0;
        if(mesDatos.keySet().contains(mes)){
            MesDatos mesActual = mesDatos.get(mes); //Obtienes los datos reales del mes en cuestion
            total += mesActual.getKbEnviadosInternacional() + mesActual.getKbRecibidosInternacional() +
                    mesActual.getKbEnviadosNacional() + mesActual.getKbRecibidosNacional();
        }

        return total;
    }

    //Buscar los meses Con mayor valor de gasto en KB
    public HashMap<String, Double> buscarMesesMayores(HashMap <String, Double> meses){

        HashMap<String, Double> mesesMayoresKb = new HashMap<String, Double>();
        double mayor = -1;
        //Buscar los meses con el mayor valor y guardarlos en el hashmap  de <Mes, valor> de los mayores meses
        if(meses != null && !(meses.isEmpty())){ //Verificamos que el HashMap no este vacio

            for(String m: meses.keySet()){ 
                if(meses.get(m) > mayor){ 
                    //Se crea un nuevo piso
                    mayor = meses.get(m); 
                    mesesMayoresKb.clear() ;
                    mesesMayoresKb.put(m,meses.get(m)); 
                }
                
                else if(meses.get(m) == mayor ){ 
                    mesesMayoresKb.put(m,meses.get(m)); 
                }
            }
    }
        

        return mesesMayoresKb;
    }
}
  
   
