package runner;
import java.beans.PropertyVetoException;

import excepciones.*;
import logica.*;

//import java.util.ArrayList;

public class Inicializadora {
	

	// Metodo que inicializa servicios, clientes y representante
	public static void Inicializar() throws NombreInvalidoException, UbicacionInvalidaException, 
									ProvinciaInvalidaException, CarnetIdentidadInvalidoException, DuplicadosException{

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

		empresa.agregarRepresentante("Yanet Gonzalez Rojas", "95061478952");
		Representante r14 = empresa.getRepresentantes().get(13);

		empresa.agregarRepresentante("Rafael Alvarez Quintana", "89081245236");
		Representante r15 = empresa.getRepresentantes().get(14);

		empresa.agregarRepresentante("Lianet Castillo Perez", "91012987451");
		Representante r16 = empresa.getRepresentantes().get(15);

		empresa.agregarRepresentante("Jose Manuel Estrada", "94021036574");
		Representante r17 = empresa.getRepresentantes().get(16);

		empresa.agregarRepresentante("Camila Rodriguez Soto", "96083014523");
		Representante r18 = empresa.getRepresentantes().get(17);

		empresa.agregarRepresentante("Leonardo Vazquez Diaz", "88041596324");
		Representante r19 = empresa.getRepresentantes().get(18);

		empresa.agregarRepresentante("Patricia Montes Perez", "92072315874");
		Representante r20 = empresa.getRepresentantes().get(19);




		//  Crear clientes
		// Personas Naturales

		empresa.agregarPersonaNatural("Carlos Martinez", "Calle 10 #45", "Playa", "La Habana", "85102578945");
		Cliente pn1 = empresa.getClientes().get(0);

		empresa.agregarPersonaNatural("Laura Diaz", "Avenida 5ta #1208", "Centro Habana", "La Habana", "90031545678");
		Cliente pn2 = empresa.getClientes().get(1);

		empresa.agregarPersonaNatural("Mario Perez", "Calle 12 #304", "Vedado", "La Habana", "87042178912");
		Cliente pn3 = empresa.getClientes().get(2);

		empresa.agregarPersonaNatural("Ana Torres", "Calle 19 #89", "Miramar", "La Habana", "91061234567");
		Cliente pn4 = empresa.getClientes().get(3);

		empresa.agregarPersonaNatural("Luis Gomez", "Avenida 26 #150", "Nuevo Vedado", "La Habana", "88010145678");
		Cliente pn5 = empresa.getClientes().get(4);

		empresa.agregarPersonaNatural("Yasmin Rodriguez", "Calle Linea #502", "Vedado", "La Habana", "95072498765");
		Cliente pn6 = empresa.getClientes().get(5);

		empresa.agregarPersonaNatural("Ernesto Lopez", "Calle 100 #456", "Boyeros", "La Habana", "92031532145");
		Cliente pn7 = empresa.getClientes().get(6);

		empresa.agregarPersonaNatural("Claudia Herrera", "Avenida Rancho Boyeros #789", "Boyeros", "La Habana", "89091465432");
		Cliente pn8 = empresa.getClientes().get(7);

		empresa.agregarPersonaNatural("Jorge Suarez", "Calle San Miguel #23", "Centro Habana", "La Habana", "87082478910");
		Cliente pn9 = empresa.getClientes().get(8);

		empresa.agregarPersonaNatural("Daniela Rivas", "Avenida Salvador Allende #78", "Cerro", "La Habana", "93031578941");
		Cliente pn10 = empresa.getClientes().get(9);

		// Personas Juridicas
		empresa.agregarPersonaJuridica("Empresa XYZ", "Calle 42 #506", "Plaza", "La Habana", "CITMA", r1);
		Cliente pj1 = empresa.getClientes().get(10);
		r1.setClienteRepresentado(pj1);

		empresa.agregarPersonaJuridica("Hotel Caribe", "Avenida 1ra #1402", "Varadero", "Matanzas", "MINTUR", r2);
		Cliente pj2 = empresa.getClientes().get(11);
		r2.setClienteRepresentado(pj2);

		empresa.agregarPersonaJuridica("CubanaTech", "Calle 23 #101", "Vedado", "La Habana", "MIC", r3);
		Cliente pj3 = empresa.getClientes().get(12);
		r3.setClienteRepresentado(pj3);

		empresa.agregarPersonaJuridica("AgroCuba SA", "Carretera Central #300", "Boyeros", "La Habana", "MINAG", r4);
		Cliente pj4 = empresa.getClientes().get(13);
		r4.setClienteRepresentado(pj4);

		empresa.agregarPersonaJuridica("BioFarmaceutica Habana", "Avenida Independencia #456", "Cerro", "La Habana", "BIOCUBAFARMA", r5);
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

		empresa.agregarPersonaJuridica("HabanaTur SA", "Avenida del Puerto #10", "Habana Vieja", "La Habana", "MINTUR", r9);
		Cliente pj9 = empresa.getClientes().get(18);
		r9.setClienteRepresentado(pj9);

		empresa.agregarPersonaJuridica("Telecomunicaciones Caribe", "Calle Zanja #123", "Centro Habana", "La Habana", "MINCOM", r10);
		Cliente pj10 = empresa.getClientes().get(19);
		r10.setClienteRepresentado(pj10);

		// Entidades No Estatales

		empresa.agregarEntidadNoEstatal("MIPYME TechSolutions", "Calle L #456", r11);
		Cliente ene1 = empresa.getClientes().get(20);
		r11.setClienteRepresentado(ene1);

		empresa.agregarEntidadNoEstatal("Panaderia El Buen Pan", "Calle M #12", r12);
		Cliente ene2 = empresa.getClientes().get(21);
		r12.setClienteRepresentado(ene2);

		empresa.agregarEntidadNoEstatal("MIPYME HabanaCode", "Avenida 31 #789", r13);
		Cliente ene3 = empresa.getClientes().get(22);
		r13.setClienteRepresentado(ene3);

		empresa.agregarEntidadNoEstatal("Cafe Aromas", "Calle 8 #43", r14);
		Cliente ene4 = empresa.getClientes().get(23);
		r14.setClienteRepresentado(ene4);

		empresa.agregarEntidadNoEstatal("Inventos Creativos", "Calle F #55", r15);
		Cliente ene5 = empresa.getClientes().get(24);
		r15.setClienteRepresentado(ene5);

		empresa.agregarEntidadNoEstatal("Soluciones Modicas", "Avenida 41 #900", r16);
		Cliente ene6 = empresa.getClientes().get(25);
		r16.setClienteRepresentado(ene6);

		empresa.agregarEntidadNoEstatal("EcoTrans", "Calle 11 #501", r17);
		Cliente ene7 = empresa.getClientes().get(26);
		r17.setClienteRepresentado(ene7);

		empresa.agregarEntidadNoEstatal("Arte en Casa", "Calle D #29", r18);
		Cliente ene8 = empresa.getClientes().get(27);
		r18.setClienteRepresentado(ene8);

		empresa.agregarEntidadNoEstatal("Consultores Legales SA", "Calle 25 #301", r19);
		Cliente ene9 = empresa.getClientes().get(28);
		r19.setClienteRepresentado(ene9);

		empresa.agregarEntidadNoEstatal("Cooperativa La Esperanza", "Calle 23 #104", r20);
		Cliente ene10 = empresa.getClientes().get(29);
		r20.setClienteRepresentado(ene10);

		// Agregar un Representante a una entidad no estatal( esto lo hice debajo de cada ENE)
		


		// Servicios para Personas Naturales
		empresa.agregarTelefonoFijo(pn1, "78781234");
		empresa.agregarTelefonoMovil(pn1, "52345678", 350.50);
		empresa.crearCuentaNauta(pn1, "carlos_mtz");

		empresa.agregarTelefonoMovil(pn2, "58901234", 420.75);
		empresa.crearCuentaNauta(pn2, "laura_dz");

		empresa.agregarTelefonoFijo(pn3, "78451233");
		empresa.agregarTelefonoMovil(pn3, "53456789", 380.00);
		empresa.crearCuentaNauta(pn3, "mario_perez");

		empresa.agregarTelefonoMovil(pn4, "58907654", 450.25);
		empresa.crearCuentaNauta(pn4, "ana_torres");

		empresa.agregarTelefonoFijo(pn5, "78123456");
		empresa.agregarTelefonoMovil(pn5, "52349876", 370.75);
		empresa.crearCuentaNauta(pn5, "luis_gomez");

		empresa.agregarTelefonoMovil(pn6, "58906543", 400.00);
		empresa.crearCuentaNauta(pn6, "yasmin_rodriguez");

		empresa.agregarTelefonoFijo(pn7, "78234567");
		empresa.agregarTelefonoMovil(pn7, "53456701", 360.00);
		empresa.crearCuentaNauta(pn7, "ernesto_lopez");

		empresa.agregarTelefonoMovil(pn8, "58903456", 410.50);
		empresa.crearCuentaNauta(pn8, "claudia_herrera");

		empresa.agregarTelefonoFijo(pn9, "78567890");
		empresa.agregarTelefonoMovil(pn9, "52345670", 390.00);
		empresa.crearCuentaNauta(pn9, "jorge_suarez");

		empresa.agregarTelefonoMovil(pn10, "58909999", 420.00);
		empresa.crearCuentaNauta(pn10, "daniela_rivas");

		// Servicios para Personas Jur�dicas
		empresa.agregarTelefonoFijo(pj1, "78889999");
		empresa.crearCuentaNauta(pj1, "empresa_xyz");

		empresa.agregarTelefonoMovil(pj2, "58432109", 600.00);
		empresa.agregarTelefonoFijo(pj2, "45789012");
		
		empresa.agregarTelefonoFijo(pj3, "78345678");
		empresa.agregarTelefonoMovil(pj3, "57901234", 550.50);
		empresa.crearCuentaNauta(pj3, "cubana_tech");

		
		empresa.agregarTelefonoFijo(pj4, "45234567");
		empresa.agregarTelefonoMovil(pj4, "58903456", 610.00);
		empresa.crearCuentaNauta(pj4, "agrocuba_sa");

		empresa.agregarTelefonoFijo(pj5, "78890012");
		empresa.agregarTelefonoMovil(pj5, "58907654", 580.75);
		empresa.crearCuentaNauta(pj5, "biofarm_habana");

		empresa.agregarTelefonoFijo(pj6, "45781234");
		empresa.agregarTelefonoMovil(pj6, "58451234", 590.25);
		empresa.crearCuentaNauta(pj6, "construc_caribe");

		empresa.agregarTelefonoFijo(pj7, "78901234");
		empresa.agregarTelefonoMovil(pj7, "57906543", 600.50);
		empresa.crearCuentaNauta(pj7, "transhabana");
	
		empresa.agregarTelefonoFijo(pj8, "45783456");
		empresa.agregarTelefonoMovil(pj8, "58456789", 615.00);
		empresa.crearCuentaNauta(pj8, "alimentos_sol");

		empresa.agregarTelefonoFijo(pj9, "78892345");
		empresa.agregarTelefonoMovil(pj9, "58904567", 620.00);
		empresa.crearCuentaNauta(pj9, "habana_tur");

		empresa.agregarTelefonoFijo(pj10, "45789999");
		empresa.agregarTelefonoMovil(pj10, "58453456", 630.00);
		empresa.crearCuentaNauta(pj10, "telecom_caribe");

		// Servicios para Entidades No Estatales
		empresa.crearCuentaNauta(ene1, "mipyme_techsolutions");
		
		empresa.agregarTelefonoMovil(ene2, "56781234", 300.25);
		empresa.crearCuentaNauta(ene2, "panaderia_buenpan");

		empresa.agregarTelefonoFijo(ene3, "56341234");
		empresa.agregarTelefonoMovil(ene3, "56432109", 320.50);
		empresa.crearCuentaNauta(ene3, "mipyme_habanacode");

		empresa.agregarTelefonoMovil(ene4, "56782345", 310.00);
		empresa.crearCuentaNauta(ene4, "cafe_aromas");

		empresa.agregarTelefonoFijo(ene5, "56234567");
		empresa.agregarTelefonoMovil(ene5, "56430987", 305.75);
		empresa.crearCuentaNauta(ene5, "disenos_creativos");

		empresa.agregarTelefonoMovil(ene6, "56785432", 315.50);
		empresa.crearCuentaNauta(ene6, "soluciones_medicas");

		empresa.agregarTelefonoFijo(ene7, "56123456");
		empresa.agregarTelefonoMovil(ene7, "56437890", 325.25);
		empresa.crearCuentaNauta(ene7, "ecotrans");

		empresa.agregarTelefonoMovil(ene8, "56789012", 330.00);
		empresa.crearCuentaNauta(ene8, "arte_en_casa");

		empresa.agregarTelefonoFijo(ene9, "56239876");
		empresa.agregarTelefonoMovil(ene9, "56431234", 335.75);
		empresa.crearCuentaNauta(ene9, "consultores_legales");

		empresa.agregarTelefonoMovil(ene10, "56783456", 340.25);
		empresa.crearCuentaNauta(ene10, "coop_esperanza");

	

		
		// Ejemplo de asignaci�n para pn1 (cliente 0), que tiene 3 servicios (�ndices 0,1,2):
		empresa.getClientes().get(0).addServicio(empresa.getServicios().get(0));
		empresa.getClientes().get(0).addServicio(empresa.getServicios().get(1));
		empresa.getClientes().get(0).addServicio(empresa.getServicios().get(2));

		empresa.getClientes().get(1).addServicio(empresa.getServicios().get(3));
		empresa.getClientes().get(1).addServicio(empresa.getServicios().get(4));

		empresa.getClientes().get(2).addServicio(empresa.getServicios().get(5));
		empresa.getClientes().get(2).addServicio(empresa.getServicios().get(6));

		empresa.getClientes().get(3).addServicio(empresa.getServicios().get(7));
		empresa.getClientes().get(3).addServicio(empresa.getServicios().get(8));

		empresa.getClientes().get(4).addServicio(empresa.getServicios().get(9));
		empresa.getClientes().get(4).addServicio(empresa.getServicios().get(10));
		empresa.getClientes().get(4).addServicio(empresa.getServicios().get(11));

		empresa.getClientes().get(5).addServicio(empresa.getServicios().get(12));
		empresa.getClientes().get(5).addServicio(empresa.getServicios().get(13));

		empresa.getClientes().get(6).addServicio(empresa.getServicios().get(14));
		empresa.getClientes().get(6).addServicio(empresa.getServicios().get(15));

		empresa.getClientes().get(7).addServicio(empresa.getServicios().get(16));
		empresa.getClientes().get(7).addServicio(empresa.getServicios().get(17));

		empresa.getClientes().get(8).addServicio(empresa.getServicios().get(18));
		empresa.getClientes().get(8).addServicio(empresa.getServicios().get(19));

		empresa.getClientes().get(9).addServicio(empresa.getServicios().get(20));
		empresa.getClientes().get(9).addServicio(empresa.getServicios().get(21));

		// Asignar servicios a Clientes Personas Jur�dicas 
		empresa.getClientes().get(10).addServicio(empresa.getServicios().get(22));
		empresa.getClientes().get(10).addServicio(empresa.getServicios().get(23));

		empresa.getClientes().get(11).addServicio(empresa.getServicios().get(24));
		empresa.getClientes().get(11).addServicio(empresa.getServicios().get(25));
		empresa.getClientes().get(11).addServicio(empresa.getServicios().get(26));

		empresa.getClientes().get(12).addServicio(empresa.getServicios().get(27));
		empresa.getClientes().get(12).addServicio(empresa.getServicios().get(28));

		empresa.getClientes().get(13).addServicio(empresa.getServicios().get(29));
		empresa.getClientes().get(13).addServicio(empresa.getServicios().get(30));
		empresa.getClientes().get(13).addServicio(empresa.getServicios().get(31));

		empresa.getClientes().get(14).addServicio(empresa.getServicios().get(32));
		empresa.getClientes().get(14).addServicio(empresa.getServicios().get(33));

		empresa.getClientes().get(15).addServicio(empresa.getServicios().get(34));
		empresa.getClientes().get(15).addServicio(empresa.getServicios().get(35));

		empresa.getClientes().get(16).addServicio(empresa.getServicios().get(36));
		empresa.getClientes().get(16).addServicio(empresa.getServicios().get(37));
		empresa.getClientes().get(16).addServicio(empresa.getServicios().get(38));

		empresa.getClientes().get(17).addServicio(empresa.getServicios().get(39));
		empresa.getClientes().get(17).addServicio(empresa.getServicios().get(40));

		empresa.getClientes().get(18).addServicio(empresa.getServicios().get(41));
		empresa.getClientes().get(18).addServicio(empresa.getServicios().get(42));
		empresa.getClientes().get(18).addServicio(empresa.getServicios().get(43));

		empresa.getClientes().get(19).addServicio(empresa.getServicios().get(44));
		empresa.getClientes().get(19).addServicio(empresa.getServicios().get(45));

		// Asignar servicios a Entidades No Estatales
		empresa.getClientes().get(20).addServicio(empresa.getServicios().get(46));
		empresa.getClientes().get(20).addServicio(empresa.getServicios().get(47));

		empresa.getClientes().get(21).addServicio(empresa.getServicios().get(48));
		empresa.getClientes().get(21).addServicio(empresa.getServicios().get(49));

		empresa.getClientes().get(22).addServicio(empresa.getServicios().get(50));
		empresa.getClientes().get(22).addServicio(empresa.getServicios().get(51));

		empresa.getClientes().get(23).addServicio(empresa.getServicios().get(52));
		empresa.getClientes().get(23).addServicio(empresa.getServicios().get(53));

		empresa.getClientes().get(24).addServicio(empresa.getServicios().get(54));
		empresa.getClientes().get(24).addServicio(empresa.getServicios().get(55));

		empresa.getClientes().get(25).addServicio(empresa.getServicios().get(56));
		empresa.getClientes().get(25).addServicio(empresa.getServicios().get(57));

		empresa.getClientes().get(26).addServicio(empresa.getServicios().get(58));
		empresa.getClientes().get(26).addServicio(empresa.getServicios().get(59));

		empresa.getClientes().get(27).addServicio(empresa.getServicios().get(60));
		empresa.getClientes().get(27).addServicio(empresa.getServicios().get(61));

		empresa.getClientes().get(28).addServicio(empresa.getServicios().get(62));
		empresa.getClientes().get(28).addServicio(empresa.getServicios().get(63));

		empresa.getClientes().get(29).addServicio(empresa.getServicios().get(64));
		empresa.getClientes().get(29).addServicio(empresa.getServicios().get(65));


		// Agregar un Representante a una entidad no estatal( esto lo hice debajo de cada ENE)
		


		// Servicios para Personas Naturales
		empresa.agregarTelefonoFijo(pn1, "78781234");
		empresa.agregarTelefonoMovil(pn1, "52345678", 350.50);
		empresa.crearCuentaNauta(pn1, "carlos_mtz");

		empresa.agregarTelefonoMovil(pn2, "58901234", 420.75);
		empresa.crearCuentaNauta(pn2, "laura_dz");

		empresa.agregarTelefonoFijo(pn3, "78451233");
		empresa.agregarTelefonoMovil(pn3, "53456789", 380.00);
		empresa.crearCuentaNauta(pn3, "mario_perez");

		empresa.agregarTelefonoMovil(pn4, "58907654", 450.25);
		empresa.crearCuentaNauta(pn4, "ana_torres");

		empresa.agregarTelefonoFijo(pn5, "78123456");
		empresa.agregarTelefonoMovil(pn5, "52349876", 370.75);
		empresa.crearCuentaNauta(pn5, "luis_gomez");

		empresa.agregarTelefonoMovil(pn6, "58906543", 400.00);
		empresa.crearCuentaNauta(pn6, "yasmin_rodriguez");

		empresa.agregarTelefonoFijo(pn7, "78234567");
		empresa.agregarTelefonoMovil(pn7, "53456701", 360.00);
		empresa.crearCuentaNauta(pn7, "ernesto_lopez");

		empresa.agregarTelefonoMovil(pn8, "58903456", 410.50);
		empresa.crearCuentaNauta(pn8, "claudia_herrera");

		empresa.agregarTelefonoFijo(pn9, "78567890");
		empresa.agregarTelefonoMovil(pn9, "52345670", 390.00);
		empresa.crearCuentaNauta(pn9, "jorge_suarez");

		empresa.agregarTelefonoMovil(pn10, "58909999", 420.00);
		empresa.crearCuentaNauta(pn10, "daniela_rivas");

		// Servicios para Personas Jurídicas
		empresa.agregarTelefonoFijo(pj1, "78889999");
		empresa.crearCuentaNauta(pj1, "empresa_xyz");

		empresa.agregarTelefonoMovil(pj2, "58432109", 600.00);
		empresa.agregarTelefonoFijo(pj2, "45789012");
		
		empresa.agregarTelefonoFijo(pj3, "78345678");
		empresa.agregarTelefonoMovil(pj3, "57901234", 550.50);
		empresa.crearCuentaNauta(pj3, "cubana_tech");

		
		empresa.agregarTelefonoFijo(pj4, "45234567");
		empresa.agregarTelefonoMovil(pj4, "58903456", 610.00);
		empresa.crearCuentaNauta(pj4, "agrocuba_sa");

		empresa.agregarTelefonoFijo(pj5, "78890012");
		empresa.agregarTelefonoMovil(pj5, "58907654", 580.75);
		empresa.crearCuentaNauta(pj5, "biofarm_habana");

		empresa.agregarTelefonoFijo(pj6, "45781234");
		empresa.agregarTelefonoMovil(pj6, "58451234", 590.25);
		empresa.crearCuentaNauta(pj6, "construc_caribe");

		empresa.agregarTelefonoFijo(pj7, "78901234");
		empresa.agregarTelefonoMovil(pj7, "57906543", 600.50);
		empresa.crearCuentaNauta(pj7, "transhabana");
	
		empresa.agregarTelefonoFijo(pj8, "45783456");
		empresa.agregarTelefonoMovil(pj8, "58456789", 615.00);
		empresa.crearCuentaNauta(pj8, "alimentos_sol");

		empresa.agregarTelefonoFijo(pj9, "78892345");
		empresa.agregarTelefonoMovil(pj9, "58904567", 620.00);
		empresa.crearCuentaNauta(pj9, "habana_tur");

		empresa.agregarTelefonoFijo(pj10, "45789999");
		empresa.agregarTelefonoMovil(pj10, "58453456", 630.00);
		empresa.crearCuentaNauta(pj10, "telecom_caribe");

		// Servicios para Entidades No Estatales
		empresa.crearCuentaNauta(ene1, "mipyme_techsolutions");
		
		empresa.agregarTelefonoMovil(ene2, "56781234", 300.25);
		empresa.crearCuentaNauta(ene2, "panaderia_buenpan");

		empresa.agregarTelefonoFijo(ene3, "56341234");
		empresa.agregarTelefonoMovil(ene3, "56432109", 320.50);
		empresa.crearCuentaNauta(ene3, "mipyme_habanacode");

		empresa.agregarTelefonoMovil(ene4, "56782345", 310.00);
		empresa.crearCuentaNauta(ene4, "cafe_aromas");

		empresa.agregarTelefonoFijo(ene5, "56234567");
		empresa.agregarTelefonoMovil(ene5, "56430987", 305.75);
		empresa.crearCuentaNauta(ene5, "disenos_creativos");

		empresa.agregarTelefonoMovil(ene6, "56785432", 315.50);
		empresa.crearCuentaNauta(ene6, "soluciones_medicas");

		empresa.agregarTelefonoFijo(ene7, "56123456");
		empresa.agregarTelefonoMovil(ene7, "56437890", 325.25);
		empresa.crearCuentaNauta(ene7, "ecotrans");

		empresa.agregarTelefonoMovil(ene8, "56789012", 330.00);
		empresa.crearCuentaNauta(ene8, "arte_en_casa");

		empresa.agregarTelefonoFijo(ene9, "56239876");
		empresa.agregarTelefonoMovil(ene9, "56431234", 335.75);
		empresa.crearCuentaNauta(ene9, "consultores_legales");

		empresa.agregarTelefonoMovil(ene10, "56783456", 340.25);
		empresa.crearCuentaNauta(ene10, "coop_esperanza");

	

		
		// Ejemplo de asignación para pn1 (cliente 0), que tiene 3 servicios (índices 0,1,2):
		empresa.getClientes().get(0).addServicio(empresa.getServicios().get(0));
		empresa.getClientes().get(0).addServicio(empresa.getServicios().get(1));
		empresa.getClientes().get(0).addServicio(empresa.getServicios().get(2));

		empresa.getClientes().get(1).addServicio(empresa.getServicios().get(3));
		empresa.getClientes().get(1).addServicio(empresa.getServicios().get(4));

		empresa.getClientes().get(2).addServicio(empresa.getServicios().get(5));
		empresa.getClientes().get(2).addServicio(empresa.getServicios().get(6));

		empresa.getClientes().get(3).addServicio(empresa.getServicios().get(7));
		empresa.getClientes().get(3).addServicio(empresa.getServicios().get(8));

		empresa.getClientes().get(4).addServicio(empresa.getServicios().get(9));
		empresa.getClientes().get(4).addServicio(empresa.getServicios().get(10));
		empresa.getClientes().get(4).addServicio(empresa.getServicios().get(11));

		empresa.getClientes().get(5).addServicio(empresa.getServicios().get(12));
		empresa.getClientes().get(5).addServicio(empresa.getServicios().get(13));

		empresa.getClientes().get(6).addServicio(empresa.getServicios().get(14));
		empresa.getClientes().get(6).addServicio(empresa.getServicios().get(15));

		empresa.getClientes().get(7).addServicio(empresa.getServicios().get(16));
		empresa.getClientes().get(7).addServicio(empresa.getServicios().get(17));

		empresa.getClientes().get(8).addServicio(empresa.getServicios().get(18));
		empresa.getClientes().get(8).addServicio(empresa.getServicios().get(19));

		empresa.getClientes().get(9).addServicio(empresa.getServicios().get(20));
		empresa.getClientes().get(9).addServicio(empresa.getServicios().get(21));

		// Asignar servicios a Clientes Personas Jurídicas 
		empresa.getClientes().get(10).addServicio(empresa.getServicios().get(22));
		empresa.getClientes().get(10).addServicio(empresa.getServicios().get(23));

		empresa.getClientes().get(11).addServicio(empresa.getServicios().get(24));
		empresa.getClientes().get(11).addServicio(empresa.getServicios().get(25));
		empresa.getClientes().get(11).addServicio(empresa.getServicios().get(26));

		empresa.getClientes().get(12).addServicio(empresa.getServicios().get(27));
		empresa.getClientes().get(12).addServicio(empresa.getServicios().get(28));

		empresa.getClientes().get(13).addServicio(empresa.getServicios().get(29));
		empresa.getClientes().get(13).addServicio(empresa.getServicios().get(30));
		empresa.getClientes().get(13).addServicio(empresa.getServicios().get(31));

		empresa.getClientes().get(14).addServicio(empresa.getServicios().get(32));
		empresa.getClientes().get(14).addServicio(empresa.getServicios().get(33));

		empresa.getClientes().get(15).addServicio(empresa.getServicios().get(34));
		empresa.getClientes().get(15).addServicio(empresa.getServicios().get(35));

		empresa.getClientes().get(16).addServicio(empresa.getServicios().get(36));
		empresa.getClientes().get(16).addServicio(empresa.getServicios().get(37));
		empresa.getClientes().get(16).addServicio(empresa.getServicios().get(38));

		empresa.getClientes().get(17).addServicio(empresa.getServicios().get(39));
		empresa.getClientes().get(17).addServicio(empresa.getServicios().get(40));

		empresa.getClientes().get(18).addServicio(empresa.getServicios().get(41));
		empresa.getClientes().get(18).addServicio(empresa.getServicios().get(42));
		empresa.getClientes().get(18).addServicio(empresa.getServicios().get(43));

		empresa.getClientes().get(19).addServicio(empresa.getServicios().get(44));
		empresa.getClientes().get(19).addServicio(empresa.getServicios().get(45));

		// Asignar servicios a Entidades No Estatales
		empresa.getClientes().get(20).addServicio(empresa.getServicios().get(46));
		empresa.getClientes().get(20).addServicio(empresa.getServicios().get(47));

		empresa.getClientes().get(21).addServicio(empresa.getServicios().get(48));
		empresa.getClientes().get(21).addServicio(empresa.getServicios().get(49));

		empresa.getClientes().get(22).addServicio(empresa.getServicios().get(50));
		empresa.getClientes().get(22).addServicio(empresa.getServicios().get(51));

		empresa.getClientes().get(23).addServicio(empresa.getServicios().get(52));
		empresa.getClientes().get(23).addServicio(empresa.getServicios().get(53));

		empresa.getClientes().get(24).addServicio(empresa.getServicios().get(54));
		empresa.getClientes().get(24).addServicio(empresa.getServicios().get(55));

		empresa.getClientes().get(25).addServicio(empresa.getServicios().get(56));
		empresa.getClientes().get(25).addServicio(empresa.getServicios().get(57));

		empresa.getClientes().get(26).addServicio(empresa.getServicios().get(58));
		empresa.getClientes().get(26).addServicio(empresa.getServicios().get(59));

		empresa.getClientes().get(27).addServicio(empresa.getServicios().get(60));
		empresa.getClientes().get(27).addServicio(empresa.getServicios().get(61));

		empresa.getClientes().get(28).addServicio(empresa.getServicios().get(62));
		empresa.getClientes().get(28).addServicio(empresa.getServicios().get(63));

		empresa.getClientes().get(29).addServicio(empresa.getServicios().get(64));
		empresa.getClientes().get(29).addServicio(empresa.getServicios().get(65));
		
		// Obtener los dos primeros telefonos fijos

        TelefonoMovil movil1 = empresa.getTelefonosMoviles().get(0);
        TelefonoMovil movil2 = empresa.getTelefonosMoviles().get(1);
        TelefonoMovil movil3 = empresa.getTelefonosMoviles().get(2);
		
//		Agregarle 10 llamadas a los telefonos Moviles obtenidos
		// Llamadas para el teléfono móvil 1 (52345678)

     // ==============================================
     // AGREGAR LLAMADAS MANUALMENTE A CADA TELÉFONO
     // ==============================================
        //TODO Arreglar los numeros de los telefonos de los destinatarios 

     // Llamadas para el teléfono móvil 1 (52345678)
        movil1.agregarLlamada(movil1.hacerLlamada("5355551111", 1500));    //  25 min = 180 min
        movil1.agregarLlamada(movil1.hacerLlamada("5355552222", 10800));   // 180 min = 10800 seg
        movil1.agregarLlamada(movil1.hacerLlamada("5355553333", 2700));    // 45 min = 2700 seg
        movil1.agregarLlamada(movil1.hacerLlamada("5355554444", 7200));     // 120 min = 7200 seg
        movil1.agregarLlamada(movil1.hacerLlamada("5355555555", 1800));     // 30 min = 1800 seg
        movil1.agregarLlamada(movil1.hacerLlamada("5355556666", 9000));     // 150 min = 9000 seg
        movil1.agregarLlamada(movil1.hacerLlamada("5355557777", 3600));     // 60 min = 3600 seg
        movil1.agregarLlamada(movil1.hacerLlamada("5355558888", 5400));     // 90 min = 5400 seg
        movil1.agregarLlamada(movil1.hacerLlamada("5355559999", 12000));    // 200 min = 12000 seg
        movil1.agregarLlamada(movil1.hacerLlamada("5355550000", 900));      // 15 min = 900 seg

        // Llamadas para el teléfono móvil 2 (58901234)
        movil2.agregarLlamada(movil2.hacerLlamada("5377771111", 1800));     // 30 min = 1800 seg
        movil2.agregarLlamada(movil2.hacerLlamada("5377772222", 2700));     // 45 min = 2700 seg
        movil2.agregarLlamada(movil2.hacerLlamada("5377773333", 6600));     // 110 min = 6600 seg
        movil2.agregarLlamada(movil2.hacerLlamada("5377774444", 1500));     // 25 min = 1500 seg
        movil2.agregarLlamada(movil2.hacerLlamada("5377775555", 10800));    // 180 min = 10800 seg
        movil2.agregarLlamada(movil2.hacerLlamada("5377776666", 3000));     // 50 min = 3000 seg
        movil2.agregarLlamada(movil2.hacerLlamada("5377777777", 7800));     // 130 min = 7800 seg
        movil2.agregarLlamada(movil2.hacerLlamada("5377778888", 1200));     // 20 min = 1200 seg
        movil2.agregarLlamada(movil2.hacerLlamada("5377779999", 4200));     // 70 min = 4200 seg
        movil2.agregarLlamada(movil2.hacerLlamada("5377770000", 9600));     // 160 min = 9600 seg

        // Llamadas para el teléfono móvil 3 (58432109)
        movil3.agregarLlamada(movil3.hacerLlamada("5388881111", 2700));     // 45 min = 2700 seg
        movil3.agregarLlamada(movil3.hacerLlamada("5388882222", 1800));     // 30 min = 1800 seg
        movil3.agregarLlamada(movil3.hacerLlamada("5388883333", 8400));     // 140 min = 8400 seg
        movil3.agregarLlamada(movil3.hacerLlamada("5388884444", 1500));     // 25 min = 1500 seg
        movil3.agregarLlamada(movil3.hacerLlamada("5388885555", 11400));    // 190 min = 11400 seg
        movil3.agregarLlamada(movil3.hacerLlamada("5388886666", 3300));     // 55 min = 3300 seg
        movil3.agregarLlamada(movil3.hacerLlamada("5388887777", 7200));     // 120 min = 7200 seg
        movil3.agregarLlamada(movil3.hacerLlamada("5388888888", 2400));     // 40 min = 2400 seg
        movil3.agregarLlamada(movil3.hacerLlamada("5388889999", 10200));    // 170 min = 10200 seg
        movil3.agregarLlamada(movil3.hacerLlamada("5388880000", 2100));     // 35 min = 2100 seg
	}
}