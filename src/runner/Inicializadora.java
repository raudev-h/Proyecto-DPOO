package runner;
import logica.*;
//import java.util.ArrayList;

public class Inicializadora {

	// Metodo que inicializa servicios, clientes y representante
	public static void Inicializar(){

		EmpresaTelecomunicaciones empresa = EmpresaTelecomunicaciones.getInstancia();

		// Inicializar representantes, agrego 20 porque son 10 para personas juridicas y 10 para ent no estatales

		empresa.agregarRepresentante("Maria Fernanda Perez", "05021768480");
		Representante r1 = empresa.getRepresentantes().get(0);

		empresa.agregarRepresentante("Luis Alberto Gomez", "05030567702");
		Representante r2 = empresa.getRepresentantes().get(1);

		empresa.agregarRepresentante("Ana Lopez Garcia", "91040312345");
		Representante r3 = empresa.getRepresentantes().get(2);

		empresa.agregarRepresentante("Maykel Fernandez Cruz", "77011869902");
		Representante r4 = empresa.getRepresentantes().get(3);

		empresa.agregarRepresentante("Ruben Perez Perez", "99010269993");
		Representante r5 = empresa.getRepresentantes().get(4);

		empresa.agregarRepresentante("Lazaro Lopez Cruz", "91050569365");
		Representante r6 = empresa.getRepresentantes().get(5);

		empresa.agregarRepresentante("Fernando Espinosa Rodriguez", "94020869552");
		Representante r7 = empresa.getRepresentantes().get(6);

		empresa.agregarRepresentante("Anthony Mendez Gomez", "88070122531");
		Representante r8 = empresa.getRepresentantes().get(7);

		empresa.agregarRepresentante("Ana Laura Echevarria Fonseca", "77111125423");
		Representante r9 = empresa.getRepresentantes().get(8);

		empresa.agregarRepresentante("Anabel Lopez Lopez", "92050136669");
		Representante r10 = empresa.getRepresentantes().get(9);

		empresa.agregarRepresentante("Carlos Daniel Romero", "90010254789");
		Representante r11 = empresa.getRepresentantes().get(10);

		empresa.agregarRepresentante("Jorge Luis Herrera", "87021548963");
		Representante r12 = empresa.getRepresentantes().get(11);

		empresa.agregarRepresentante("Dayana Martinez Suarez", "93071825641");
		Representante r13 = empresa.getRepresentantes().get(12);

		empresa.agregarRepresentante("Yanet González Rojas", "95061478952");
		Representante r14 = empresa.getRepresentantes().get(13);

		empresa.agregarRepresentante("Rafael Alvarez Quintana", "89081245236");
		Representante r15 = empresa.getRepresentantes().get(14);

		empresa.agregarRepresentante("Lianet Castillo Pérez", "91012987451");
		Representante r16 = empresa.getRepresentantes().get(15);

		empresa.agregarRepresentante("Jose Manuel Estrada", "94021036574");
		Representante r17 = empresa.getRepresentantes().get(16);

		empresa.agregarRepresentante("Camila Rodriguez Soto", "96083014523");
		Representante r18 = empresa.getRepresentantes().get(17);

		empresa.agregarRepresentante("Leonardo Vázquez Díaz", "88041596324");
		Representante r19 = empresa.getRepresentantes().get(18);

		empresa.agregarRepresentante("Patricia Montes Pérez", "92072315874");
		Representante r20 = empresa.getRepresentantes().get(19);





		//  Crear clientes
		// Personas Naturales

		empresa.agregarPersonaNatural("Carlos Martínez", "Calle 10 #45", "Playa", "La Habana", "85102578945");
		Cliente pn1 = empresa.getClientes().get(0);

		empresa.agregarPersonaNatural("Laura Díaz", "Avenida 5ta #1208", "Centro Habana", "La Habana", "90031545678");
		Cliente pn2 = empresa.getClientes().get(1);

		empresa.agregarPersonaNatural("Mario Pérez", "Calle 12 #304", "Vedado", "La Habana", "87042178912");
		Cliente pn3 = empresa.getClientes().get(2);

		empresa.agregarPersonaNatural("Ana Torres", "Calle 19 #89", "Miramar", "La Habana", "91061234567");
		Cliente pn4 = empresa.getClientes().get(3);

		empresa.agregarPersonaNatural("Luis Gómez", "Avenida 26 #150", "Nuevo Vedado", "La Habana", "88010145678");
		Cliente pn5 = empresa.getClientes().get(4);

		empresa.agregarPersonaNatural("Yasmin Rodríguez", "Calle Línea #502", "Vedado", "La Habana", "95072498765");
		Cliente pn6 = empresa.getClientes().get(5);

		empresa.agregarPersonaNatural("Ernesto López", "Calle 100 #456", "Boyeros", "La Habana", "92031532145");
		Cliente pn7 = empresa.getClientes().get(6);

		empresa.agregarPersonaNatural("Claudia Herrera", "Avenida Rancho Boyeros #789", "Boyeros", "La Habana", "89091465432");
		Cliente pn8 = empresa.getClientes().get(7);

		empresa.agregarPersonaNatural("Jorge Suárez", "Calle San Miguel #23", "Centro Habana", "La Habana", "87082478910");
		Cliente pn9 = empresa.getClientes().get(8);

		empresa.agregarPersonaNatural("Daniela Rivas", "Avenida Salvador Allende #78", "Cerro", "La Habana", "93031578941");
		Cliente pn10 = empresa.getClientes().get(9);

		// Personas JurÃ­dicas
		empresa.agregarPersonaJuridica("Empresa XYZ", "Calle 42 #506", "Plaza", "La Habana", "CITMA", r1);
		Cliente pj1 = empresa.getClientes().get(10);
		r1.setClienteRepresentado(pj1);

		empresa.agregarPersonaJuridica("Hotel Caribe", "Avenida 1ra #1402", "Varadero", "Matanzas", "MINTUR", r2);
		Cliente pj2 = empresa.getClientes().get(11);
		r2.setClienteRepresentado(pj2);

		empresa.agregarPersonaJuridica("CubanaTech", "Calle 23 #101", "Vedado", "La Habana", "MIC", r3);
		Cliente pj3 = empresa.getClientes().get(12);
		r3.setClienteRepresentado(pj3);

		empresa.agregarPersonaJuridica("AgroCuba S.A.", "Carretera Central #300", "Boyeros", "La Habana", "MINAG", r4);
		Cliente pj4 = empresa.getClientes().get(13);
		r4.setClienteRepresentado(pj4);

		empresa.agregarPersonaJuridica("BioFarmacéutica Habana", "Avenida Independencia #456", "Cerro", "La Habana", "BIOCUBAFARMA", r5);
		Cliente pj5 = empresa.getClientes().get(14);
		r5.setClienteRepresentado(pj5);

		empresa.agregarPersonaJuridica("Construcciones Caribe", "Calle 70 #505", "Playa", "La Habana", "MICONS", r6);
		Cliente pj6 = empresa.getClientes().get(15);
		r6.setClienteRepresentado(pj6);

		empresa.agregarPersonaJuridica("TransHabana", "Avenida Rancho Boyeros #789", "Boyeros", "La Habana", "MITRANS", r7);
		Cliente pj7 = empresa.getClientes().get(16);
		r7.setClienteRepresentado(pj7);

		empresa.agregarPersonaJuridica("Alimentos del Sol", "Calle 32 #102", "Marianao", "La Habana", "MINAL", r8);
		Cliente pj8 = empresa.getClientes().get(17);
		r8.setClienteRepresentado(pj8);

		empresa.agregarPersonaJuridica("HabanaTur S.A.", "Avenida del Puerto #10", "Habana Vieja", "La Habana", "MINTUR", r9);
		Cliente pj9 = empresa.getClientes().get(18);
		r9.setClienteRepresentado(pj9);

		empresa.agregarPersonaJuridica("Telecomunicaciones Caribe", "Calle Zanja #123", "Centro Habana", "La Habana", "MINCOM", r10);
		Cliente pj10 = empresa.getClientes().get(19);
		r10.setClienteRepresentado(pj10);

		// Entidades No Estatales

		empresa.agregarEntidadNoEstatal("MIPYME TechSolutions", "Calle L #456", r11);
		Cliente ene1 = empresa.getClientes().get(20);
		r11.setClienteRepresentado(ene1);

		empresa.agregarEntidadNoEstatal("Panadería El Buen Pan", "Calle M #12", r12);
		Cliente ene2 = empresa.getClientes().get(21);
		r12.setClienteRepresentado(ene2);

		empresa.agregarEntidadNoEstatal("MIPYME HabanaCode", "Avenida 31 #789", r13);
		Cliente ene3 = empresa.getClientes().get(22);
		r13.setClienteRepresentado(ene3);

		empresa.agregarEntidadNoEstatal("Café Aromas", "Calle 8 #43", r14);
		Cliente ene4 = empresa.getClientes().get(23);
		r14.setClienteRepresentado(ene4);

		empresa.agregarEntidadNoEstatal("Diseños Creativos", "Calle F #55", r15);
		Cliente ene5 = empresa.getClientes().get(24);
		r15.setClienteRepresentado(ene5);

		empresa.agregarEntidadNoEstatal("Soluciones Médicas", "Avenida 41 #900", r16);
		Cliente ene6 = empresa.getClientes().get(25);
		r16.setClienteRepresentado(ene6);

		empresa.agregarEntidadNoEstatal("EcoTrans", "Calle 11 #501", r17);
		Cliente ene7 = empresa.getClientes().get(26);
		r17.setClienteRepresentado(ene7);

		empresa.agregarEntidadNoEstatal("Arte en Casa", "Calle D #29", r18);
		Cliente ene8 = empresa.getClientes().get(27);
		r18.setClienteRepresentado(ene8);

		empresa.agregarEntidadNoEstatal("Consultores Legales S.A.", "Calle 25 #301", r19);
		Cliente ene9 = empresa.getClientes().get(28);
		r19.setClienteRepresentado(ene9);

		empresa.agregarEntidadNoEstatal("Cooperativa La Esperanza", "Calle 23 #104", r20);
		Cliente ene10 = empresa.getClientes().get(29);
		r20.setClienteRepresentado(ene10);

		// Agregar un Representante a una entidad no estatal( esto lo hice debajo de cada ENE)
		


		// Crear servicios a partir de que se crearon los clientes
		// Servicios para Personas Naturales
		empresa.agregarTelefonoFijo(pn1, "78781234");
		empresa.agregarTelefonoMovil(pn1, "52345678", 350.50);
		empresa.crearCuentaNauta(pn1, "carlos_mtz");


		empresa.agregarTelefonoMovil(pn2, "58901234", 420.75);
		empresa.crearCuentaNauta(pn2, "laura_dz");

		// Servicios para Personas JurÃ¯Â¿Â½dicas
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

		// Hacer que los clientes llamen
		TelefonoFijo tf1 = (TelefonoFijo) empresa.getClientes().get(0).getServicios().get(0);
		tf1.agregarLlamadaLargaDistancia(20, "12345678", "La Habana", "Cerro", 550.00);


	}

}




