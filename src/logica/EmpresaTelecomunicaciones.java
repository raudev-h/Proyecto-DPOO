package  logica;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class EmpresaTelecomunicaciones {
    // ATRIBUTOS
    private ArrayList<Cliente> clientes;
    private ArrayList<Servicio> servicios;
    private ArrayList<Representante> representantes;
    
    // CONSTRUCTOR
    public EmpresaTelecomunicaciones() {
        clientes = new ArrayList<Cliente>();
        servicios = new ArrayList<Servicio>();
        representantes = new ArrayList<Representante>();
    }

    // GETTERS Y SETTERS
    // Clientes
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }
    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    // Servicios
    public ArrayList<Servicio> getServicios() {
        return servicios;
    }
    public void setServicios(ArrayList<Servicio> servicios) {
        this.servicios = servicios;
    }

    // Representante
    public ArrayList<Representante> getRepresentantes() {
        return representantes;
    }
    public void setRepresentantes(ArrayList<Representante> representantes) {
        this.representantes = representantes;
    }   

    // METODOS
    //Buscar los clientes que tengan al menoos 30% (4 ) meses de mas de 1000 cup de montoTotal en sus Cuentas Nautas
    public ArrayList<Cliente> clientesMasMilMontoNauta(){

        ArrayList<Cliente> mejoresClientes = new ArrayList<Cliente>();

        if( !servicios.isEmpty()){

            for(Servicio s: servicios ){

            if(s instanceof CuentaNauta){
                
                if(((CuentaNauta)s).cantMesesMasMilGasto() >= 4){
                    mejoresClientes.add(s.getTitular());
                }
            }
        }

        }
        
        return mejoresClientes;
    }

    //Buscar los meses de mayor gasto en kb de todas las Cuentas Nautas
    public ArrayList<String>  mesesMaskbGastadosCuentas(){
        //Inicializamos el hashmap 
        ArrayList<String> mesesMayorGastoCuentas = new ArrayList<String>();
        double mayor = -1;

        if(servicios != null){

            for(Servicio s : servicios){
                if(s instanceof CuentaNauta){
                    CuentaNauta cuentaActual = (CuentaNauta)s;

                    HashMap<String, Double > mesesGasto =cuentaActual.calcularKbGastadosMeses();
                    HashMap<String, Double> mesesMayores = cuentaActual.buscarMesesMayores(mesesGasto);

                    double gastoKbMesesMayores = Collections.max(mesesMayores.values()); //Obtener el primer valor(Todos iguales)
                    
                    if(gastoKbMesesMayores > mayor){ 
                        //Se crea un nuevo techo
                        mayor = gastoKbMesesMayores;
                        mesesMayorGastoCuentas.clear();

                        //Agrego el nombre de los meses
                        for(String m: mesesMayores.keySet()){
                            mesesMayorGastoCuentas.add(m);
                        }
                    }

                    else if(gastoKbMesesMayores == mayor){

                        for(String m: mesesMayores.keySet()){
                            if(!(mesesMayorGastoCuentas.contains(m))){ //Evitamos que se guarden meses repetidos
                                mesesMayorGastoCuentas.add(m);

                            }
                        }
                    }
                
                }
            
            }
        }
    return mesesMayorGastoCuentas;          
    }

        public ArrayList<TelefonoMovil> telefonosMovilLLamadasMasMin(int minutos){ 

        ArrayList<TelefonoMovil> telefonosMovilMasMin = new ArrayList<TelefonoMovil>();
        if(!servicios.isEmpty()){ //Se comprueba que hay al menos un servicio

            for(Servicio s: servicios){
                if(s instanceof Telefono){

                    if(s  instanceof TelefonoMovil){

                        //Buscamos la cantidad de llamadas que superan los 100 min del telefono
                        int llamadasMasMin = ((TelefonoMovil)s).llamadasMasMin(minutos).size();
                        if(llamadasMasMin > 0){
                            telefonosMovilMasMin.add((TelefonoMovil)s);
                        }


                    }
                }
            }
        }

        return telefonosMovilMasMin;
    }

    // Provincias con la menor cantidad de cuentas nauta de Personas Naturales
    public ArrayList<Map.Entry<String, Integer>> menorCantCuentasNauta(){
        ArrayList<PersonaNatural> personasNaturales = new ArrayList<PersonaNatural>();
        String [] provincias = {"Pinar del Rio", "Artemisa", "La Habana", "Mayabeque", "Matanzas", "Cienfuegos",
                            "Villa Clara", "Sancti Spiritus", "Ciego de Avila", "Camagüey", "Las Tunas", "Holguín",
                            "Granma", "Santiago de Cuba", "Guantánamo", "Isla de la Juventud"};
        Map<String, Integer> provinciasConCuenta = new HashMap <String, Integer>();

        // Guarda en una Lista las Personas Naturales que tienen contratado el servicio NautaHogar
        for(Servicio s :servicios){
            if(s instanceof CuentaNauta){
                CuentaNauta cuentaNauta = (CuentaNauta)s;
                if(cuentaNauta.getTitular() instanceof PersonaNatural)
                    personasNaturales.add((PersonaNatural)cuentaNauta.getTitular());      
            }
        }

        // Inicializar el HashMap con las provincias
        for(int i = 0; i < 16; i++){
            provinciasConCuenta.put(provincias[i], 0);
        }

        // Aumentar los valores de las provincias
        for(PersonaNatural p : personasNaturales){
            String provinciaCliente = p.getProvincia();
            // Aumentar el valor de la provincia
            if(provinciasConCuenta.containsKey(provinciaCliente)){
                provinciasConCuenta.put(provinciaCliente, provinciasConCuenta.get(provinciaCliente) + 1);
            }    
        }    
        
        // Traspasar lo valores del HashMap al ArrayList para ordenarlo
        ArrayList<Map.Entry<String,Integer>> provinciasOrdenadas = new ArrayList<>(provinciasConCuenta.entrySet());
        provinciasOrdenadas.sort(Map.Entry.comparingByValue());

        return provinciasOrdenadas;
    }

    //Clientes con un Consumo mayor a 500 pesos en al menos 3 Llamadas de Larga Distancia
    //Se puede mejorar el metodo devolviendo un hashmap con los nombres y el monto 
    public ArrayList<Cliente> clientesAltoConsumoLlamadaFijo(){

        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        ArrayList<Cliente> clientesEvaluados = new ArrayList<Cliente>();
        int cantLlamadas = 0;

        for(Servicio s : servicios){
            if(s instanceof TelefonoFijo){
                TelefonoFijo tf = (TelefonoFijo)s;
                Cliente titular = tf.getTitular();

                // Analizamos a los clientes una sola vez
                if(!clientesEvaluados.contains(titular)){
                    clientesEvaluados.add(titular);
                
                // Buscamos nuevamente en todos los servicios si ese cliente tiene mas de 1 telefono fijo
                for(Servicio otro : servicios){
                    if(otro instanceof TelefonoFijo){
                        TelefonoFijo otroTf = (TelefonoFijo)otro;
                        if(otroTf.getTitular().equals(titular)){
                            for(LlamadaLargaDistancia llamada : otroTf.getLlamadasLargas()){
                                if(llamada.getTotalFacturar() >= 500)
                                    cantLlamadas++;
                            }
                        }
                        
                    }
                }
                if(cantLlamadas >= 3)
                    clientes.add(titular);
                }
            }
        }
        return clientes;
    }
}


