package runner;
import logica.*;
//import java.util.ArrayList;

public class Inicializadora {
    
    // Metodo que inicializa servicios, clientes y representante
    public static void Inicializar(){
        
        // REPRESENTANTES
        EmpresaTelecomunicaciones.getInstancia().agregarRepresentante("María Fernanda Pérez", "05021768480");
        EmpresaTelecomunicaciones.getInstancia().agregarRepresentante("Luis Alberto Gómez", "05030567702");

        //Representante r1 = EmpresaTelecomunicaciones.getInstancia().getRepresentantes().get(0);
        //Representante r2 = EmpresaTelecomunicaciones.getInstancia().getRepresentantes().get(1);

        // ENTIDAD NO ESTATAL   
        //EmpresaTelecomunicaciones.getInstancia().agregarEntidadNoEstatal("Calle 23 #104, Vedado", "MIPYME",r1);
        
        // PERSONA JURIDICA
        // EmpresaTelecomunicaciones.getInstancia().agregarPersonaJuridica("Calle 45 #67", "Playa", "La Habana","Gran Caribe","MINTUR",r2);

        // PERSONA NATURAL
        EmpresaTelecomunicaciones.getInstancia().agregarPersonaNatural("Calle Línea #456", "Vedado", "La Habana", "Raul Hechavarria", "97072468480");
        Cliente c1 = EmpresaTelecomunicaciones.getInstancia().getClientes().get(0);
        
        // TELEFONO FIJO
        EmpresaTelecomunicaciones.getInstancia().agregarTelefonoFijo(c1, "76378134");

        // CUENTA NAUTA
        EmpresaTelecomunicaciones.getInstancia().crearCuentaNauta(c1, "angell64"); 

        // TELEFONO MOVIL
        EmpresaTelecomunicaciones.getInstancia().agregarTelefonoMovil(c1, "52387754", 250);
    }
}



