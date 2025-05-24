package logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class EmpresaTelecomunicaciones {

    
    //ATRIBUTO
    private ArrayList<Servicio> servicios;
    private ArrayList<Cliente> clientes;
    private Representante representanteEntidad;
    private Representante representantePersona;
    //CONSTRUCTOR
    public EmpresaTelecomunicaciones(String nombreRepresentanteE, String numIdRepresentanteE,
                                String nombreRepresentanteP, String numIdRepresentanteP ){

        servicios = new ArrayList<Servicio>();
        clientes = new ArrayList<Cliente>();
        representanteEntidad = new Representante(nombreRepresentanteP, numIdRepresentanteP);
        representantePersona = new Representante(nombreRepresentanteE, numIdRepresentanteE);

    }
    //GETTERS Y SETTES 
    // TODO: (Pendiente getter y setters de los nombres y numId de los representantes)
    //METODOS
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

        
    }

  

