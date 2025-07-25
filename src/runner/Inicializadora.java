package runner;
import java.beans.PropertyVetoException;


import java.util.ArrayList;
import java.util.Random;

import excepciones.*;
import logica.*;
import logica.*
;//import java.util.ArrayList;

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

		// ==============================
		// 1) CREAR TEL�FONOS FIJOS DISPONIBLES (sin titular)
		// ==============================
		empresa.agregarTelefonoFijo("78781234");
		

		empresa.agregarTelefonoFijo("78451233");

		empresa.agregarTelefonoFijo("76378134");

		empresa.agregarTelefonoFijo("76410037");

		empresa.agregarTelefonoFijo("78123456");
		empresa.agregarTelefonoFijo("78234567");
		empresa.agregarTelefonoFijo("76413700");
		empresa.agregarTelefonoFijo("78567890");
		empresa.agregarTelefonoFijo("78889999");
		empresa.agregarTelefonoFijo("45789012");
		empresa.agregarTelefonoFijo("78345678");
		empresa.agregarTelefonoFijo("45234567");
		empresa.agregarTelefonoFijo("78890012");
		empresa.agregarTelefonoFijo("45781234");
		empresa.agregarTelefonoFijo("78901234");
		empresa.agregarTelefonoFijo("45783456");
		empresa.agregarTelefonoFijo("78892345");
		empresa.agregarTelefonoFijo("45789999");
		empresa.agregarTelefonoFijo("78810023");
		empresa.agregarTelefonoFijo("78832040");
		empresa.agregarTelefonoFijo("56341234");
		empresa.agregarTelefonoFijo("56234567");
		empresa.agregarTelefonoFijo("56123456");
		empresa.agregarTelefonoFijo("78817052");
		empresa.agregarTelefonoFijo("56239876");
		empresa.agregarTelefonoFijo("78654321");
		empresa.agregarTelefonoFijo("78765432");
		empresa.agregarTelefonoFijo("78876543");
		empresa.agregarTelefonoFijo("78987654");
		empresa.agregarTelefonoFijo("78234567");
		empresa.agregarTelefonoFijo("78345678");
		empresa.agregarTelefonoFijo("78456789");
		empresa.agregarTelefonoFijo("78567890");
		empresa.agregarTelefonoFijo("78678901");
		empresa.agregarTelefonoFijo("78789012");
		empresa.agregarTelefonoFijo("78890123");
		empresa.agregarTelefonoFijo("78901234");
		empresa.agregarTelefonoFijo("78012345");
		empresa.agregarTelefonoFijo("78123456");

		// ==============================
		// 2) CREAR TEL�FONOS M�VILES DISPONIBLES (sin titular)
		// ==============================
		empresa.agregarTelefonoMovil("52345678", 350.50);
		empresa.agregarTelefonoMovil("58901234", 420.75);
		empresa.agregarTelefonoMovil("53456789", 380.00);
		empresa.agregarTelefonoMovil("58907654", 450.25);
		empresa.agregarTelefonoMovil("52349876", 370.75);
		empresa.agregarTelefonoMovil("58906543", 400.00);
		empresa.agregarTelefonoMovil("53456701", 360.00);
		empresa.agregarTelefonoMovil("58903456", 410.50);
		empresa.agregarTelefonoMovil("52345670", 390.00);
		empresa.agregarTelefonoMovil("58909999", 420.00);

		empresa.agregarTelefonoMovil("58432109", 600.00);
		empresa.agregarTelefonoMovil("57901234", 550.50);
		empresa.agregarTelefonoMovil("58903456", 610.00);
		empresa.agregarTelefonoMovil("58907654", 580.75);
		empresa.agregarTelefonoMovil("58451234", 590.25);
		empresa.agregarTelefonoMovil("57906543", 600.50);
		empresa.agregarTelefonoMovil("58456789", 615.00);
		empresa.agregarTelefonoMovil("58904567", 620.00);
		empresa.agregarTelefonoMovil("58453456", 630.00);

		empresa.agregarTelefonoMovil("56781234", 300.25);
		empresa.agregarTelefonoMovil("56432109", 320.50);
		empresa.agregarTelefonoMovil("56782345", 310.00);
		empresa.agregarTelefonoMovil("56430987", 305.75);
		empresa.agregarTelefonoMovil("56785432", 315.50);
		empresa.agregarTelefonoMovil("56437890", 325.25);
		empresa.agregarTelefonoMovil("56789012", 330.00);
		empresa.agregarTelefonoMovil("56431234", 335.75);
		empresa.agregarTelefonoMovil("56783456", 340.25);

		empresa.agregarTelefonoMovil("51234567", 245.50);
		empresa.agregarTelefonoMovil("58901234", 189.75);
		empresa.agregarTelefonoMovil("54567890", 320.00);
		empresa.agregarTelefonoMovil("52876543", 415.25);
		empresa.agregarTelefonoMovil("52652109", 150.80);
		empresa.agregarTelefonoMovil("53469012", 275.30);
		empresa.agregarTelefonoMovil("52347890", 495.60);
		empresa.agregarTelefonoMovil("53125678", 225.90);
		empresa.agregarTelefonoMovil("54096543", 380.45);
		empresa.agregarTelefonoMovil("54236789", 110.20);
		empresa.agregarTelefonoMovil("55876541", 290.75);
		empresa.agregarTelefonoMovil("55901236", 175.30);
		empresa.agregarTelefonoMovil("56789014", 430.50);
		empresa.agregarTelefonoMovil("56987652", 265.80);

		// ==============================
		// 3) ASIGNAR TEL�FONOS FIJOS A CLIENTES � usando n�mero expl�cito
		// ==============================

		// Personas Naturales
		empresa.asignarTelefonoFijo(pn1, "78781234");
		empresa.asignarTelefonoFijo(pn2, "78451233");
		empresa.asignarTelefonoFijo(pn3, "76378134");
		empresa.asignarTelefonoFijo(pn4, "76410037");
		empresa.asignarTelefonoFijo(pn5, "78123456");
		empresa.asignarTelefonoFijo(pn6, "78234567");
		empresa.asignarTelefonoFijo(pn7, "76413700");
		empresa.asignarTelefonoFijo(pn8, "78567890");
		empresa.asignarTelefonoFijo(pn9, "78889999");

		// Personas Jur�dicas
		empresa.asignarTelefonoFijo(pj1, "45789012");
		empresa.asignarTelefonoFijo(pj2, "78345678");
		empresa.asignarTelefonoFijo(pj3, "45234567");
		empresa.asignarTelefonoFijo(pj4, "78890012");
		empresa.asignarTelefonoFijo(pj5, "45781234");
		empresa.asignarTelefonoFijo(pj6, "78901234");
		empresa.asignarTelefonoFijo(pj7, "45783456");
		empresa.asignarTelefonoFijo(pj8, "78892345");
		empresa.asignarTelefonoFijo(pj9, "45789999");
		empresa.asignarTelefonoFijo(pj10, "78810023");

		// Entidades No Estatales
		empresa.asignarTelefonoFijo(ene1, "78832040");
		empresa.asignarTelefonoFijo(ene2, "56341234");
		empresa.asignarTelefonoFijo(ene3, "56234567");
		empresa.asignarTelefonoFijo(ene4, "56123456");
		empresa.asignarTelefonoFijo(ene5, "78817052");
		empresa.asignarTelefonoFijo(ene6, "56239876");
		empresa.asignarTelefonoFijo(ene7, "78654321");
		empresa.asignarTelefonoFijo(ene8, "78765432");
		empresa.asignarTelefonoFijo(ene9, "78876543");

		// ==============================
		// 4) ASIGNAR TEL�FONOS M�VILES A CLIENTES
		// ==============================
		// Personas Naturales
		empresa.asignarTelefonoMovil(pn1);
		empresa.crearCuentaNauta(pn1, "carlos_mtz");

		empresa.asignarTelefonoMovil(pn2);
		empresa.crearCuentaNauta(pn2, "laura_dz");

		empresa.asignarTelefonoMovil(pn3);
		empresa.crearCuentaNauta(pn3, "mario_perez");

		empresa.asignarTelefonoMovil(pn4);
		empresa.crearCuentaNauta(pn4, "ana_torres");

		empresa.asignarTelefonoMovil(pn5);
		empresa.crearCuentaNauta(pn5, "luis_gomez");

		empresa.asignarTelefonoMovil(pn6);

		empresa.asignarTelefonoMovil(pn7);
		empresa.crearCuentaNauta(pn7, "ernesto_lopez");

		empresa.asignarTelefonoMovil(pn8);
		empresa.crearCuentaNauta(pn8, "claudia_herrera");

		empresa.asignarTelefonoMovil(pn9);
		empresa.crearCuentaNauta(pn9, "jorge_suarez");

		empresa.asignarTelefonoMovil(pn10);

		// Personas Jur�dicas
		empresa.asignarTelefonoMovil(pj2);
		empresa.asignarTelefonoMovil(pj3);
		empresa.asignarTelefonoMovil(pj4);
		empresa.asignarTelefonoMovil(pj5);
		empresa.asignarTelefonoMovil(pj6);
		empresa.asignarTelefonoMovil(pj7);
		empresa.asignarTelefonoMovil(pj8);
		empresa.asignarTelefonoMovil(pj9);
		empresa.asignarTelefonoMovil(pj10);

		// Entidades No Estatales
		empresa.crearCuentaNauta(ene1, "mipyme_techsolutions");

		empresa.asignarTelefonoMovil(ene2);
		empresa.crearCuentaNauta(ene2, "panaderia_buenpan");

		empresa.asignarTelefonoMovil(ene3);
		empresa.crearCuentaNauta(ene3, "mipyme_habanacode");

		empresa.asignarTelefonoMovil(ene4);

		empresa.asignarTelefonoMovil(ene5);
		empresa.crearCuentaNauta(ene5, "disenos_creativos");

		empresa.asignarTelefonoMovil(ene6);

		empresa.asignarTelefonoMovil(ene7);
		empresa.crearCuentaNauta(ene7, "ecotrans");

		empresa.asignarTelefonoMovil(ene8);
		empresa.crearCuentaNauta(ene8, "arte_en_casa");

		empresa.asignarTelefonoMovil(ene9);
		empresa.crearCuentaNauta(ene9, "consultores_legales");

		empresa.asignarTelefonoMovil(ene10);


		// Obtener los dos primeros telefonos fijos

		TelefonoMovil movil1 = empresa.getTelefonosMoviles().get(0);
		TelefonoMovil movil2 = empresa.getTelefonosMoviles().get(1);
		TelefonoMovil movil3 = empresa.getTelefonosMoviles().get(2);

		//		Agregarle 10 llamadas a los telefonos Moviles obtenidos
		// Llamadas para el telefono movil 1 (52345678)

		// ==============================================
		// AGREGAR LLAMADAS MANUALMENTE A CADA TEL�FONO
		// ==============================================
		//TODO Arreglar los numeros de los telefonos de los destinatarios 

		// Llamadas para el telefono movil 1 (52345678)
		// Llamadas para el telefono movil 1 (52345678)
		movil1.agregarLlamada(movil1.hacerLlamada("52678901", 1500));    //  25 min = 1500 seg (m�vil)
		movil1.agregarLlamada(movil1.hacerLlamada("78345678", 10800));   // 180 min = 10800 seg (fijo)
		movil1.agregarLlamada(movil1.hacerLlamada("58901234", 2700));    // 45 min = 2700 seg (m�vil)
		movil1.agregarLlamada(movil1.hacerLlamada("76413700", 7200));    // 120 min = 7200 seg (fijo)
		movil1.agregarLlamada(movil1.hacerLlamada("58432109", 1800));    // 30 min = 1800 seg (m�vil)
		movil1.agregarLlamada(movil1.hacerLlamada("78234567", 9000));    // 150 min = 9000 seg (fijo)
		movil1.agregarLlamada(movil1.hacerLlamada("58907777", 3600));    // 60 min = 3600 seg (m�vil)
		movil1.agregarLlamada(movil1.hacerLlamada("45789012", 5400));    // 90 min = 5400 seg (fijo)
		movil1.agregarLlamada(movil1.hacerLlamada("58906543", 12000));   // 200 min = 12000 seg (m�vil)
		movil1.agregarLlamada(movil1.hacerLlamada("78654321", 900));     // 15 min = 900 seg (fijo)

		// Llamadas para el telefono movil 2 (58901234)
		movil2.agregarLlamada(movil2.hacerLlamada("58909999", 1800));    // 30 min = 1800 seg (m�vil)
		movil2.agregarLlamada(movil2.hacerLlamada("56123456", 2700));    // 45 min = 2700 seg (fijo)
		movil2.agregarLlamada(movil2.hacerLlamada("56432109", 6600));    // 110 min = 6600 seg (fijo)
		movil2.agregarLlamada(movil2.hacerLlamada("78832040", 1500));    // 25 min = 1500 seg (fijo)
		movil2.agregarLlamada(movil2.hacerLlamada("58901234", 10800));   // 180 min = 10800 seg (m�vil)
		movil2.agregarLlamada(movil2.hacerLlamada("78456789", 3000));    // 50 min = 3000 seg (fijo)
		movil2.agregarLlamada(movil2.hacerLlamada("52347890", 7800));    // 130 min = 7800 seg (m�vil)
		movil2.agregarLlamada(movil2.hacerLlamada("78234567", 1200));    // 20 min = 1200 seg (fijo)
		movil2.agregarLlamada(movil2.hacerLlamada("58907777", 4200));    // 70 min = 4200 seg (m�vil)
		movil2.agregarLlamada(movil2.hacerLlamada("45789012", 9600));    // 160 min = 9600 seg (fijo)

		// Llamadas para el telefono movil 3 (58432109)
		movil3.agregarLlamada(movil3.hacerLlamada("52345678", 2700));    // 45 min = 2700 seg (m�vil)
		movil3.agregarLlamada(movil3.hacerLlamada("78817052", 1800));    // 30 min = 1800 seg (fijo)
		movil3.agregarLlamada(movil3.hacerLlamada("58909999", 8400));    // 140 min = 8400 seg (m�vil)
		movil3.agregarLlamada(movil3.hacerLlamada("56123456", 1500));    // 25 min = 1500 seg (fijo)
		movil3.agregarLlamada(movil3.hacerLlamada("56432109", 11400));   // 190 min = 11400 seg (fijo)
		movil3.agregarLlamada(movil3.hacerLlamada("52345678", 3300));    // 55 min = 3300 seg (m�vil)
		movil3.agregarLlamada(movil3.hacerLlamada("58901234", 7200));    // 120 min = 7200 seg (m�vil)
		movil3.agregarLlamada(movil3.hacerLlamada("78456789", 2400));    // 40 min = 2400 seg (fijo)
		movil3.agregarLlamada(movil3.hacerLlamada("52347890", 10200));   // 170 min = 10200 seg (m�vil)
		movil3.agregarLlamada(movil3.hacerLlamada("78654321", 2100));    // 35 min = 2100 seg (fijo)
		
		//=================================================
		//AGREGAR LLAMADAS A TELEFONOS FIJOS
		//===================================================

		//=================================================
		// AGREGAR LLAMADAS A TODOS LOS TEL�FONOS FIJOS DE LA EMPRESA
		// Provincias y municipios v�lidos de la base de datos
		//=================================================

		// Ejemplo de n�meros v�lidos cubanos (m�viles y fijos)

		String[] telefonosValidos = {
			    "52678901", "58901234", "53456789", "54567890", "58432109", "56789012",
			    "78345678", "76413700", "78234567", "78654321", "78901234", "76410037",
			    "78123456", "78451233", "76378134", "78889999", "45789012", "45234567",
			    "52678901", "58901234", "53456789", "54567890", "58432109", "56789012",
			    "78345678", "76413700", "78234567", "78654321", "78901234", "76410037",
			    "78123456", "78451233", "76378134", "78889999", "45789012", "45234567"
			};

		// Usa la base de datos de provincias y municipios
		// import tu.paquete.MunicipiosPorProvincia;

		Random rand = new Random();

		Object[] provincias = MunicipiosPorProvincia.MUNICIPIOS_PROVINCIA.keySet().toArray();
		int totalTelefonos = empresa.getTelefonosFijos().size();

		for (int idx = 0; idx < totalTelefonos; idx++) {
		    TelefonoFijo tf = empresa.getTelefonosFijos().get(idx);

		    int cantidadLlamadas = 10 + rand.nextInt(11); // Entre 10 y 20 llamadas por tel�fono

		    for (int i = 0; i < cantidadLlamadas; i++) {
		        // Selecciona un n�mero de destino v�lido, diferente al propio
		        String numeroDestino = telefonosValidos[rand.nextInt(telefonosValidos.length)];
		        while (numeroDestino.equals(tf.getNumero())) {
		            numeroDestino = telefonosValidos[rand.nextInt(telefonosValidos.length)];
		        }

		        // Provincia v�lida de la base de datos
		        String provincia = (String) provincias[rand.nextInt(provincias.length)];

		        // Municipio v�lido para la provincia
		        String[] municipiosProvincia = MunicipiosPorProvincia.MUNICIPIOS_PROVINCIA.get(provincia);
		        String municipio = "";
		        if (municipiosProvincia != null && municipiosProvincia.length > 0) {
		            municipio = municipiosProvincia[rand.nextInt(municipiosProvincia.length)];
		        }

		        // Duraci�n aleatoria entre 60 y 3600 segundos (1 a 60 min)
		        double duracion = 60 + rand.nextInt(3541);

		        // Crear llamada usando el m�todo de la interfaz Llamador
		        Llamada llamada = tf.hacerLlamada(numeroDestino, duracion, provincia);

		        // Agregar la llamada al tel�fono fijo
		        tf.agregarLlamada(llamada);
		    }
		}
		
	//Creacion de al menos 20 representantes
		// Representantes adicionales libres (sin cliente asignado)
		empresa.agregarRepresentante("Zurdokar Plasencia", "84121501001");
		empresa.agregarRepresentante("Yaxiry Solunare", "90070102002");
		empresa.agregarRepresentante("Quiruvio Noreste", "78030603003");
		empresa.agregarRepresentante("Xilena Marab", "85082204004");
		empresa.agregarRepresentante("Ozmart Yerbabuena", "91103005005");
		empresa.agregarRepresentante("Ufrano Calipso", "79041406006");
		empresa.agregarRepresentante("Ixchel Toronja", "88052507007");
		empresa.agregarRepresentante("Yovankor del Monte", "83070808008");
		empresa.agregarRepresentante("Zuleyka Omb", "92090909009");
		empresa.agregarRepresentante("Quirino Almbar", "87121210010");
		empresa.agregarRepresentante("Xarand Csmico", "89111111011");
		empresa.agregarRepresentante("Odalisca Mercurio", "94020212012");
		empresa.agregarRepresentante("Yerut Pandora", "80030313013");
		empresa.agregarRepresentante("Zacarias Neptuno", "91040414014");
		empresa.agregarRepresentante("Ulpiano Cerezo", "85050515015");

		//================================================================
		//AGREGAR LLAMADAS LARGA DISTANCIA A TEL�FONOS FIJOS
		//========================================================
	
		Object[] provincias1 = MunicipiosPorProvincia.MUNICIPIOS_PROVINCIA.keySet().toArray();

		for (int idx = 0; idx < totalTelefonos; idx++) {
		    TelefonoFijo tf = empresa.getTelefonosFijos().get(idx);

		    int cantidadLlamadas = 1 + rand.nextInt(30); // Entre 1 y 30 llamadas

		    for (int i = 0; i < cantidadLlamadas; i++) {
		        // Selecciona un n�mero de destino v�lido, diferente al propio
		        String numeroDestino = telefonosValidos[rand.nextInt(telefonosValidos.length)];
		        while (numeroDestino.equals(tf.getNumero())) {
		            numeroDestino = telefonosValidos[rand.nextInt(telefonosValidos.length)];
		        }

		        // Duraci�n aleatoria entre 30 segundos y 2 horas (7200 segundos)
		        double duracion = 30 + rand.nextInt(7171);

		        // Provincia y municipio v�lidos usando la base de datos
		        String provincia = (String) provincias1[rand.nextInt(provincias1.length)];
		        String[] municipiosProvincia = MunicipiosPorProvincia.MUNICIPIOS_PROVINCIA.get(provincia);
		        String municipio = municipiosProvincia[rand.nextInt(municipiosProvincia.length)];

		        double totalFacturar = duracion * 0.75;

		        // Agrega la llamada usando el m�todo adecuado
		        tf.agregarLlamadaLargaDistancia(
		            duracion,
		            numeroDestino,
		            provincia,
		            municipio,
		            totalFacturar
		        );
		    }
		}
		
		// TODO datos de prueba para cuentas nauta
		// Busca las instancias de las cuentas en los servicios de cada cliente
		CuentaNauta cn1 = null, cn2 = null, cn3 = null, cn4 = null, cn5 = null;
		for (Servicio s : pn1.getServicios()) if (s instanceof CuentaNauta) cn1 = (CuentaNauta) s;
		for (Servicio s : pn2.getServicios()) if (s instanceof CuentaNauta) cn2 = (CuentaNauta) s;
		for (Servicio s : pn3.getServicios()) if (s instanceof CuentaNauta) cn3 = (CuentaNauta) s;
		for (Servicio s : pn4.getServicios()) if (s instanceof CuentaNauta) cn4 = (CuentaNauta) s;
		for (Servicio s : pn5.getServicios()) if (s instanceof CuentaNauta) cn5 = (CuentaNauta) s;

		// Ahora inicializa datos de consumo en varios meses
		if (cn1 != null) {
		    cn1.agregarMesDatos(
		        "2025-01",
		        110,    // kbEnviadosInternacional
		        1200,   // kbEnviadosNacional
		        90,     // kbRecibidosInternacional
		        1150,   // kbRecibidosNacional
		        500,    // kbNavegacion
		        25,     // montoEnvioInternacional
		        35,     // montoEnvioNacional
		        12,     // montoNavegacion
		        10,     // montoRecepcionInternacional
		        30,     // montoRecepcionNacional
		        112     // montoTotal
		    );
		    cn1.agregarMesDatos(
		        "2025-02",
		        130, 1300, 100, 1210, 520, 30, 37, 14, 13, 32, 1260
		    );
		    cn1.agregarMesDatos("2025-03",
			        130, 1300, 100, 1210, 520, 30, 37, 14, 13, 32, 1260);
		    
		    cn1.agregarMesDatos("2025-04",
		        130, 1300, 100, 1210, 520, 30, 37, 14, 13, 32, 1890);
		    
		    cn1.agregarMesDatos("2025-05",
		        130, 1300, 100, 1210, 520, 30, 37, 14, 13, 32, 1673);
		    
		    cn1.agregarMesDatos("2025-06",
			        130, 1300, 100, 1210, 520, 30, 37, 14, 13, 32, 1446);
		}

		if (cn2 != null) {
		    cn2.agregarMesDatos("2025-02", 215, 1500, 180, 1420, 610, 45, 55, 16, 18, 36, 1360);
		    cn2.agregarMesDatos("2025-03", 220, 1510, 185, 1430, 615, 42, 54, 18, 19, 37, 1385);
		    cn2.agregarMesDatos("2025-04", 230, 1520, 190, 1440, 620, 44, 56, 19, 20, 39, 1400);
		    cn2.agregarMesDatos("2025-05", 225, 1550, 195, 1455, 625, 43, 57, 20, 21, 41, 1425);
		}

		if (cn3 != null) {
		    cn3.agregarMesDatos("2025-02", 140, 1200, 120, 1100, 530, 34, 38, 15, 15, 33, 1120);
		    cn3.agregarMesDatos("2025-03", 150, 1250, 130, 1150, 540, 36, 40, 15, 16, 35, 1155);
		    cn3.agregarMesDatos("2025-04", 155, 1280, 135, 1170, 545, 35, 41, 17, 17, 36, 1170);
		    cn3.agregarMesDatos("2025-05", 160, 1300, 140, 1190, 550, 36, 43, 18, 18, 38, 1195);
		}

		if (cn4 != null) {
		    cn4.agregarMesDatos("2025-02", 200, 1400, 160, 1320, 600, 40, 50, 20, 22, 48, 1200);
		    cn4.agregarMesDatos("2025-03", 210, 1410, 170, 1330, 610, 41, 51, 21, 23, 49, 1235);
		    cn4.agregarMesDatos("2025-04", 215, 1420, 175, 1340, 620, 42, 52, 22, 24, 50, 1270);
		    cn4.agregarMesDatos("2025-05", 220, 1430, 180, 1350, 630, 43, 53, 23, 25, 51, 1305);
		}

		if (cn5 != null) {
		    cn5.agregarMesDatos("2025-02", 300, 2000, 280, 1900, 800, 70, 85, 32, 40, 60, 2200);
		    cn5.agregarMesDatos("2025-03", 310, 2100, 290, 2000, 820, 71, 86, 33, 41, 61, 2250);
		    cn5.agregarMesDatos("2025-04", 320, 2200, 300, 2100, 830, 72, 87, 34, 42, 62, 2300);
		    cn5.agregarMesDatos("2025-05", 330, 2300, 310, 2200, 840, 73, 88, 35, 43, 63, 2350);
		}
		// TODO aqu� cierra el bloque
		
		
	}
}


