package logica;

import java.util.ArrayList;

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
    //GETTERS Y SETTES (Pendiente getter y setters de los nombres y numId de los representantes)
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

    //
    
}
