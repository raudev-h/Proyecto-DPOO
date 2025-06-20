package logica;

import java.util.*;


import excepciones.*;

public class EmpresaTelecomunicaciones {
	// Atributos
	private static EmpresaTelecomunicaciones empresa;
	private ArrayList<Cliente> clientes;
	private ArrayList<Servicio> servicios;
	private ArrayList<Representante> representantes;
	private ArrayList<Servicio> serviciosDisponibles;

	// Constructor
	private EmpresaTelecomunicaciones() {
		clientes = new ArrayList<Cliente>();
		servicios = new ArrayList<Servicio>();
		representantes = new ArrayList<Representante>();
		serviciosDisponibles = new ArrayList<Servicio> ();
	}

	// Getters y setters
	// Obtener la unica instancia de la clase Empresa Telecomunicaciones
	public static EmpresaTelecomunicaciones getInstancia() {
		if (empresa == null) {
			empresa = new EmpresaTelecomunicaciones();
		}
		return empresa;
	}

	// Clientes
	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}

	//Eliminacion de un cliente pasandole al cliente seleccionado
	public boolean eliminarCliente(Cliente cliente){

		boolean eliminado = false;
		//Eliminar un cliente y todos sus servicios asociados 
		if(clientes.contains(cliente)){

			//Eliminacion de todos los servicios contratados de ese cliente
			for(Servicio s: cliente.getServicios()){
				servicios.remove(s);				
			}
			clientes.remove(cliente);
			eliminado = true;

		}

		return eliminado;

	}

	//Eliminacion de un cliente pasandole el nombre del cliente solamente
	public boolean eliminarCliente(String nombreCliente){

		boolean eliminado = false;
		//Eliminar un cliente y todos sus servicios asociados 
		Cliente cliente = this.buscarCliente(nombreCliente);
		if(clientes.contains(cliente)){

			for(Servicio s: cliente.getServicios()){
				servicios.remove(s);				
			}
			clientes.remove(cliente);
			eliminado = true;

		}

		return eliminado;

	}
	// Servicios
	public ArrayList<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(ArrayList<Servicio> servicios) {
		this.servicios = servicios;
	}
	// Servicios Disponibles
	public ArrayList<Servicio> getServicioDisponible(){
		return serviciosDisponibles;
	}
	public void setServiciosDisponibles(ArrayList<Servicio> serviciosDisponibles){
		this.serviciosDisponibles = serviciosDisponibles;
	}

	// Representantes
	public ArrayList<Representante> getRepresentantes() {
		return representantes;
	}

	public void setRepresentantes(ArrayList<Representante> representantes) {
		this.representantes = representantes;
	}

	//Eliminar un representante pasandole solamente el ID
	public boolean eliminarRepresentante(String id){

		boolean eliminado = false;
		int i = 0;

		while(i<representantes.size() && !eliminado){

			if(representantes.get(i).getNumId().equals(id)){
				representantes.remove(representantes.get(i));
				eliminado = true;
			}
			i++;
		}

		return eliminado;
	}

	// METODOS


	//Buscar un cliente con su nombre y actualizar sus datos de nombre y direccion
	public boolean actualizarCliente(String nombreOriginal,String nuevoNombre, String nuevaDireccion) throws UbicacionInvalidaException,
	NombreInvalidoException{

		boolean actualizado = false;

		for(Cliente c: clientes){
			if(c.getNombre().equals(nombreOriginal)){
				c.setNombre(nuevoNombre);
				c.setDireccion(nuevaDireccion);
				actualizado = true;
			}

		}

		return actualizado;
	}


	// Agregar Representante
	public void agregarRepresentante(String nombreCompleto, String numId) throws NombreInvalidoException, CarnetIdentidadInvalidoException,
	DuplicadosException{

		for(Representante r : representantes){
			if(r.getNumId().equals(numId))
				throw new DuplicadosException("Ese representante ya existe");
		}

		Representante r1 = new Representante(nombreCompleto, numId);
		representantes.add(r1);
	}

	// Agregar Entidad no estatal
	public void agregarEntidadNoEstatal(String direccion, String nombreEntidad, Representante representante) 
			throws NombreInvalidoException, UbicacionInvalidaException, DuplicadosException {

		for(Cliente c : clientes){
			if(c instanceof EntidadNoEstatal){
				EntidadNoEstatal ene = (EntidadNoEstatal)c;
				if(ene.getDireccion().equalsIgnoreCase(direccion))
					throw new DuplicadosException("Esa persona ya existe en nuestro sistema");
			}
		}

		Cliente c1 = new EntidadNoEstatal(direccion, nombreEntidad, representante);
		clientes.add(c1);
	}

	// Agregar Persona Natural
	public void agregarPersonaNatural(String direccion, String municipio, String provincia, String nombre,
			String numId) throws NombreInvalidoException, UbicacionInvalidaException, ProvinciaInvalidaException,
			CarnetIdentidadInvalidoException, DuplicadosException {

		for(Cliente c : clientes){
			if(c instanceof PersonaNatural){
				PersonaNatural pn = (PersonaNatural)c;
				if(pn.getNumId().equals(numId))
					throw new DuplicadosException("Esa persona ya existe en nuestro sistema");
			}
		}

		Cliente c1 = new PersonaNatural(direccion, municipio, provincia, nombre, numId);
		clientes.add(c1);
	}

	// Agregar Persona Juridica
	public void agregarPersonaJuridica(String direccion, String municipio, String provincia, String nombreEmpresaString,
			String organismo, Representante representante) throws NombreInvalidoException, UbicacionInvalidaException,
			ProvinciaInvalidaException, DuplicadosException{

		for(Cliente c : clientes){
			if(c instanceof PersonaJuridica){
				PersonaJuridica pj = (PersonaJuridica)c;
				if(pj.getDireccion().equalsIgnoreCase(direccion))
					throw new DuplicadosException("Esa persona jur�dica ya existe en nuestro sistema");
			}
		}

		Cliente c1 = new PersonaJuridica(direccion, municipio, provincia, nombreEmpresaString, organismo,
				representante);
		clientes.add(c1);
	}

	// Agregar Cuenta Nauta
	public void crearCuentaNauta(Cliente titular, String nick) {
		
		boolean tieneTelefono = false;
		
		for(int i = 0; i < titular.getServicios().size() && !tieneTelefono ; i++){
			if(titular.getServicios().get(i) instanceof TelefonoFijo){
				tieneTelefono = true;
			}
		}
		if(!tieneTelefono)
			throw new IllegalArgumentException("Para contratar el nauta debe tener tel�fono fijo");	
		
		Servicio s1 = new CuentaNauta(titular, nick);
		servicios.add(s1);
	}


	// Agregar Telefono Fijo
	public void agregarTelefonoFijo(Cliente titular, String numero) {

		if(titular == null){
			Servicio s1 = new TelefonoFijo(null, numero);
			serviciosDisponibles.add(s1);
		}
		else{
			Servicio s1 = new TelefonoFijo(titular, numero);
			servicios.add(s1);
		}
	}

	// Asignar un telefono Fijo ya existente
	public void asignarTelefonoFijo(Cliente titular){
		Servicio disponible = null;
		
		for(int i = 0; i < serviciosDisponibles.size() && disponible == null; i++){
			if(serviciosDisponibles.get(i) instanceof TelefonoFijo){
				disponible = serviciosDisponibles.get(i);
			}
		}
		if(disponible == null)
			throw new IllegalArgumentException("No hay telefono Fijo disponible");
		
		disponible.setTitular(titular);
		servicios.add(disponible);
		serviciosDisponibles.remove(disponible);
		
	}

	// Agregar Telefono Movil
	public void agregarTelefonoMovil(Cliente titular, String numero, double montoPagar) {

		if(titular == null){
			Servicio s1 = new TelefonoMovil(null, numero, montoPagar);
			serviciosDisponibles.add(s1);
		}
		else{
			Servicio s1 = new TelefonoMovil(titular, numero, montoPagar);
			servicios.add(s1);
		}
	}

	// Asignar un telefono Movil ya existente a un cliente
	public void asignarTelefonoMovil(Cliente titular){
		TelefonoMovil disponible = null;

		for (int i = 0; i < serviciosDisponibles.size() && disponible == null; i++) {
			if (serviciosDisponibles.get(i) instanceof TelefonoMovil) {
				disponible = (TelefonoMovil) serviciosDisponibles.get(i);
			}
		}
		// m�s adelante le cambio la excepcion	
		if (disponible == null) 
			throw new IllegalArgumentException("No hay tel�fono m�vil disponible");

		disponible.setTitular(titular);
		servicios.add(disponible);
		serviciosDisponibles.remove(disponible);

	}



	//Obtener los TelefonosFijos
	public ArrayList<TelefonoFijo> getTelefonosFijos(){

		ArrayList<TelefonoFijo> telefonosFijos = new ArrayList<TelefonoFijo>();

		for(Servicio s: servicios){
			if(s instanceof TelefonoFijo){
				telefonosFijos.add((TelefonoFijo)s);
			}	
		}

		return telefonosFijos;
	}

	//Obtener los Telefonos Moviles
	public ArrayList<TelefonoMovil> getTelefonosMoviles(){

		ArrayList<TelefonoMovil> telefonosMoviles = new ArrayList<TelefonoMovil>();

		for(Servicio s: servicios ){
			if(s instanceof TelefonoMovil){
				telefonosMoviles.add((TelefonoMovil)s);
			}
		}


		return telefonosMoviles;
	}

	//Obtener las Cuentas Nautas
	public ArrayList<CuentaNauta> getCuentasNautas(){

		ArrayList<CuentaNauta> cuentasNautas = new ArrayList<CuentaNauta>();

		for(Servicio s: servicios ){
			if(s instanceof CuentaNauta){
				cuentasNautas.add((CuentaNauta)s);
			}
		}

		return cuentasNautas;
	}

	//METODOS

	//Buscar representantes sin clientes a representar(representantes libres)

	//Buscar representante a partir del ID
	public Representante buscarRepresentante(String numId){

		Representante representanteEncontrado = null;
		int i = 0;

		while(i<representantes.size() && representanteEncontrado == null){

			if(representantes.get(i).getNumId().equals(numId)){
				representanteEncontrado = representantes.get(i);		
			}

			i++;
		}		
		return representanteEncontrado;
	}



	//Buscar a un cliente por su nombre 
	public Cliente buscarCliente(String nombreCliente){

		Cliente clienteEncontrado = null;
		boolean encontrado = false;
		int i = 0; 
		if(!(nombreCliente.trim().isEmpty()) && nombreCliente != null){

			while(i<clientes.size() && encontrado == false){
				if(clientes.get(i).getNombre().equals(nombreCliente)){

					clienteEncontrado = clientes.get(i);
				}
				i++;
			}		

		}
		return clienteEncontrado;

	}


	// LLamadas de Movil que duraron mas de X minutos
	public ArrayList<TelefonoMovil> telefonosMovilLLamadasMasMin(int minutos){

		ArrayList<TelefonoMovil> telefonosMovilMasMin = new ArrayList<TelefonoMovil>();

		if(!servicios.isEmpty()){ //Se comprueba que hay al menos un servicio
			for(Servicio s: servicios){
				if(s instanceof TelefonoMovil){

					TelefonoMovil tm = (TelefonoMovil)s;

					//Buscamos la cantidad de llamadas que superan los X min del telefono							
					if(!tm.llamadasMasMin(minutos).isEmpty()) {
						telefonosMovilMasMin.add(tm);
					}
				}
			}
		}

		return telefonosMovilMasMin;
	}

	// Buscar los clientes que tengan al menoos 30% (4 ) meses de mas de 1000 cup de montoTotal en sus Cuentas Nautas

	public ArrayList<Cliente> clientesMasMilMontoNauta() {

		ArrayList<Cliente> mejoresClientes = new ArrayList<Cliente>();

		if (!servicios.isEmpty()) {

			for (Servicio s : servicios) {

				if (s instanceof CuentaNauta) {

					if (((CuentaNauta) s).cantMesesMasMilGasto() >= 4) {
						if(!mejoresClientes.contains(s.getTitular())){
							mejoresClientes.add(s.getTitular());
						}

					}
				}
			}

		}

		return mejoresClientes;
	}




	// Buscar los meses de mayor gasto en kb de todas las Cuentas Nautas
	public ArrayList<String> mesesMaskbGastadosCuentas() {
		// Inicializamos el hashmap
		ArrayList<String> mesesMayorGastoCuentas = new ArrayList<String>();
		double mayor = -1;

		if (servicios != null) {

			for (Servicio s : servicios) {
				if (s instanceof CuentaNauta) {
					CuentaNauta cuentaActual = (CuentaNauta) s;

					HashMap<String, Double> mesesGasto = cuentaActual.calcularKbGastadosMeses();
					HashMap<String, Double> mesesMayores = cuentaActual.buscarMesesMayores(mesesGasto);

					double gastoKbMesesMayores = Collections.max(mesesMayores.values()); // Obtener el primer
					// valor(Todos iguales)

					if (gastoKbMesesMayores > mayor) {
						// Se crea un nuevo techo
						mayor = gastoKbMesesMayores;
						mesesMayorGastoCuentas.clear();

						// Agrego el nombre de los meses
						for (String m : mesesMayores.keySet()) {
							mesesMayorGastoCuentas.add(m);
						}
					}

					else if (gastoKbMesesMayores == mayor) {

						for (String m : mesesMayores.keySet()) {
							if (!(mesesMayorGastoCuentas.contains(m))) /// Evitamos que se guarden meses repetidos
								mesesMayorGastoCuentas.add(m);

						}
					}

				}

			}
		}
		return mesesMayorGastoCuentas;
	}

	// Buscar las provincias con la menor cant de Cuentas Nautas
	public ArrayList<Map.Entry<String, Integer>> menorCantCuentasNauta() {
		ArrayList<PersonaNatural> personasNaturales = new ArrayList<PersonaNatural>();
		String[] provincias = { "Pinar del Rio", "Artemisa", "La Habana", "Mayabeque", "Matanzas", "Cienfuegos",
				"Villa Clara", "Sancti Spiritus", "Ciego de Avila", "Camaguey", "Las Tunas", "Holguin",
				"Granma", "Santiago de Cuba", "Guantanamo", "Isla de la Juventud" };
		Map<String, Integer> provinciasConCuenta = new HashMap<String, Integer>();

		// Guardar Personas Naturales con Cuenta Nauta


		if (servicios != null) {
			for (Servicio s : servicios) {
				if (s instanceof CuentaNauta) {
					CuentaNauta cuentaNauta = (CuentaNauta) s;
					if (cuentaNauta.getTitular() != null && cuentaNauta.getTitular() instanceof PersonaNatural)
						personasNaturales.add((PersonaNatural) cuentaNauta.getTitular());
				}
			}
		}

		// Inicializar provincias
		for (int i = 0; i < provincias.length; i++) {
			provinciasConCuenta.put(provincias[i], 0);
		}

		// Contar por provincia

		for (PersonaNatural p : personasNaturales) {
			String provinciaCliente = p.getProvincia();
			if (provinciasConCuenta.containsKey(provinciaCliente)) {
				provinciasConCuenta.put(provinciaCliente, provinciasConCuenta.get(provinciaCliente) + 1);
			}
		}

		// Ordenar provincias por cantidad de cuentas
		ArrayList<Map.Entry<String, Integer>> provinciasOrdenadas = new ArrayList<Map.Entry<String, Integer>>(
				provinciasConCuenta.entrySet());


		BubbleSort.sort(provinciasOrdenadas);

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

	//Buscar los representantes que no tienen clientes a representar (representantes libres)

	public synchronized ArrayList<Representante> buscarRepresentantesLibres(){

		ArrayList<Representante> representantesLibres = new ArrayList<Representante>();

		for(Representante r: representantes ){

			if(r.getClienteRepresentado() == null){
				representantesLibres.add(r);
			}		
		}

		return representantesLibres;
	}

	// M�todo para asignar representante a un cliente (Persona Jur�dica o Entidad No Estatal)
	public void asignarRepresentanteACliente(Cliente cliente, Representante representante) {

		if (cliente != null && representante != null){


			// Si el cliente ya ten�a un representante, lo liberamos
			if (cliente instanceof PersonaJuridica) {
				PersonaJuridica pj = (PersonaJuridica) cliente;
				if (pj.getRepresentantePersonaJuridica() != null) {
					pj.getRepresentantePersonaJuridica().setClienteRepresentado(null);
				}
				pj.setRepresentantePersonaJuridica(representante);
			} 
			else if (cliente instanceof EntidadNoEstatal) {
				EntidadNoEstatal ene = (EntidadNoEstatal) cliente;
				if (ene.getRepresentanteEntidad() != null) {
					ene.getRepresentanteEntidad().setClienteRepresentado(null);
				}
				ene.setRepresentanteEntidad(representante);
			}

			// Asignamos el cliente al representante
			representante.setClienteRepresentado(cliente);
		}
	}

	// M�todo para desasignar representante de un cliente
	public synchronized void desasignarRepresentanteDeCliente(Cliente cliente) {
		if (cliente != null){

			if (cliente instanceof PersonaJuridica) {
				PersonaJuridica pj = (PersonaJuridica) cliente;
				if (pj.getRepresentantePersonaJuridica() != null) {
					pj.getRepresentantePersonaJuridica().setClienteRepresentado(null);
					System.out.print("Se ha deshasignado un representante");
					pj.setRepresentantePersonaJuridica(null);
				}
			} 
			else if (cliente instanceof EntidadNoEstatal) {
				EntidadNoEstatal ene = (EntidadNoEstatal) cliente;
				if (ene.getRepresentanteEntidad() != null) {
					System.out.print("Se ha deshasignado un representante");
					ene.getRepresentanteEntidad().setClienteRepresentado(null);
					ene.setRepresentanteEntidad(null);
				}
			}
		}
	}








}
