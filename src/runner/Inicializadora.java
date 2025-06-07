package runner;
import logica.*;
//import java.util.ArrayList;

public class Inicializadora {
    
    // Metodo que inicializa servicios, clientes y representante
    public static void Inicializar(){
        
    	EmpresaTelecomunicaciones empresa = EmpresaTelecomunicaciones.getInstancia();
    	
    	// Inicializar representantes
    	empresa.agregarRepresentante("María Fernanda Pérez", "05021768480");
        Representante r1 = empresa.getRepresentantes().get(0);
        
        empresa.agregarRepresentante("Luis Alberto Gómez", "05030567702");
        Representante r2 = empresa.getRepresentantes().get(1);
        
        empresa.agregarRepresentante("Ana López García", "91040312345");
        Representante r3 = empresa.getRepresentantes().get(2);
      
        //  Crear clientes
        // Personas Naturales
        empresa.agregarPersonaNatural("Carlos Martínez", "Calle 10 #45", "Playa", "La Habana", "85102578945");
        Cliente pn1 = empresa.getClientes().get(0);
        
        empresa.agregarPersonaNatural("Laura Díaz", "Avenida 5ta #1208", "Centro Habana", "La Habana", "90031545678");
        Cliente pn2 = empresa.getClientes().get(1);
             
        // Personas Jurídicas
        empresa.agregarPersonaJuridica("Empresa XYZ", "Calle 42 #506", "Plaza", "La Habana", "CITMA", r1);
        Cliente pj1 = empresa.getClientes().get(2);
        
        empresa.agregarPersonaJuridica("Hotel Caribe", "Avenida 1ra #1402", "Varadero", "Matanzas", "MINTUR", r2);
        Cliente pj2 = empresa.getClientes().get(3);
       
        // Entidades No Estatales
        empresa.agregarEntidadNoEstatal("Cooperativa La Esperanza", "Calle 23 #104", r3);
        Cliente ene1 = empresa.getClientes().get(4);
        
        empresa.agregarEntidadNoEstatal("MIPYME TechSolutions", "Calle L #456", r1);
        Cliente ene2 = empresa.getClientes().get(5);


        // Crear servicios a partir de que se crearon los clientes
        // Servicios para Personas Naturales
        empresa.agregarTelefonoFijo(pn1, "78781234");
        empresa.agregarTelefonoMovil(pn1, "52345678", 350.50);
        empresa.crearCuentaNauta(pn1, "carlos_mtz");
        
        empresa.agregarTelefonoMovil(pn2, "58901234", 420.75);
        empresa.crearCuentaNauta(pn2, "laura_dz");

        // Servicios para Personas Jurídicas
        empresa.agregarTelefonoFijo(pj1, "78889999");
        empresa.crearCuentaNauta(pj1, "empresa_xyz");
        
        empresa.agregarTelefonoMovil(pj2, "58432109", 600.00);
        empresa.agregarTelefonoFijo(pj2, "45789012");

        // Servicios para Entidades No Estatales
        empresa.crearCuentaNauta(ene1, "coop_esperanza");
        empresa.agregarTelefonoMovil(ene2, "56781234", 300.25);
        
        //Agregar los servicios a los clientes
        empresa.getClientes().get(0).addServicio(empresa.getServicios().get(0));
        empresa.getClientes().get(0).addServicio(empresa.getServicios().get(1));
        empresa.getClientes().get(0).addServicio(empresa.getServicios().get(2));
        
        empresa.getClientes().get(1).addServicio(empresa.getServicios().get(3));
        empresa.getClientes().get(1).addServicio(empresa.getServicios().get(4));
        
        empresa.getClientes().get(2).addServicio(empresa.getServicios().get(5));
        empresa.getClientes().get(2).addServicio(empresa.getServicios().get(6));
        
        empresa.getClientes().get(3).addServicio(empresa.getServicios().get(7));
        empresa.getClientes().get(3).addServicio(empresa.getServicios().get(8));
        
        
        
        
        
        
    }
        
    }



