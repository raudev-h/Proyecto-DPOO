package logica;

import java.util.*;


import excepciones.*;

public class EmpresaTelecomunicaciones {
	
	// ========== ATRIBUTOS ==========
	private static EmpresaTelecomunicaciones empresa;
	private ArrayList<Cliente> clientes;
	private ArrayList<Servicio> servicios;
	private ArrayList<Servicio> serviciosDisponibles;
	private ArrayList<Representante> representantes;


	// ========= CONSTRUCTOR ==========
	private EmpresaTelecomunicaciones() {
		clientes = new ArrayList<Cliente>();
		servicios = new ArrayList<Servicio>();
		serviciosDisponibles = new ArrayList<Servicio>();
		representantes = new ArrayList<Representante>();
	}

	//========== GETTERS Y SETTERS ==========
	

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

	// Servicios Diponibles
	public void setServiciosDisponibles(ArrayList<Servicio> serviciosDisponibles){
		this.serviciosDisponibles = serviciosDisponibles;
	}
	
	public ArrayList<Servicio> getServiciosDisponibles() {
			if (this.serviciosDisponibles == null) {
				this.serviciosDisponibles = new ArrayList<Servicio>();
			}
			return this.serviciosDisponibles;
		}


	// Representantes
	public ArrayList<Representante> getRepresentantes() {
		return representantes;
	}

	public void setRepresentantes(ArrayList<Representante> representantes) {
		this.representantes = representantes;
	}


	//========== METODOS ==========
	
	// Obtener la unica instancia de la clase Empresa Telecomunicaciones (Singleton)
		public static EmpresaTelecomunicaciones getInstancia() {
			if (empresa == null) {
				empresa = new EmpresaTelecomunicaciones();
			}
			return empresa;
		}


	// GESTION DE CLIENTES
		
		
		// Agregar Entidad no estatal
		public void agregarEntidadNoEstatal(String nombreEntidad, String direccion,  Representante representante) 
				throws NombreInvalidoException, UbicacionInvalidaException, DuplicadosException {

			for(Cliente c : clientes){
				if(c instanceof EntidadNoEstatal){
					EntidadNoEstatal ene = (EntidadNoEstatal)c;
					if(ene.getDireccion().equalsIgnoreCase(direccion))
						throw new DuplicadosException("Esa persona ya existe en nuestro sistema");
				}
			}

			Cliente c1 = new EntidadNoEstatal(nombreEntidad, direccion, representante);
			clientes.add(c1);
		}

		// Agregar Persona Natural
		public void agregarPersonaNatural(String nombre, String direccion, String municipio, String provincia, 
				String numId) throws NombreInvalidoException, UbicacionInvalidaException, ProvinciaInvalidaException,
				CarnetIdentidadInvalidoException, DuplicadosException {

			for(Cliente c : clientes){
				if(c instanceof PersonaNatural){
					PersonaNatural pn = (PersonaNatural)c;
					if(pn.getNumId().equals(numId))
						throw new DuplicadosException("Esa persona ya existe en nuestro sistema");
				}
			}

			Cliente c1 = new PersonaNatural(nombre, direccion, municipio, provincia,  numId);

			clientes.add(c1);
		}

		// Agregar Persona Juridica
		public void agregarPersonaJuridica(String nombreEmpresaString, String direccion, String municipio, String provincia, 
				String organismo, Representante representante) throws NombreInvalidoException, UbicacionInvalidaException,
				ProvinciaInvalidaException, DuplicadosException{

			for(Cliente c : clientes){
				if(c instanceof PersonaJuridica){
					PersonaJuridica pj = (PersonaJuridica)c;
					if(pj.getDireccion().equalsIgnoreCase(direccion))
						throw new DuplicadosException("Esa persona jurídica ya existe en nuestro sistema");
				}
			}

			Cliente c1 = new PersonaJuridica(nombreEmpresaString, direccion, municipio, provincia, organismo,representante);

			clientes.add(c1);
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
					if(s instanceof Telefono){
						s.setTitular(null);
						serviciosDisponibles.add(s);
						servicios.remove(s);
					}
					else if(s instanceof CuentaNauta){
						servicios.remove(s);
					}


				}
				clientes.remove(cliente);
				eliminado = true;

			}

			return eliminado;

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
		
		// Elminiar el cliente si sus servicios llegan a ser 0
		public void eliminarClienteServicio(Cliente titular){

			if(titular.getServicios().size() == 0)
				clientes.remove(titular);
		}
		
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
		
		// Metodo para validar si persona natural ya tiene cuenta nauta o si es persona juridica
		public boolean comprobarNauta(Cliente titular){

			boolean encontrado = false;

			if(titular instanceof PersonaNatural){
				PersonaNatural pn = (PersonaNatural) titular;
				for(int i = 0; i < pn.getServicios().size() && !encontrado; i++){
					if(pn.getServicios().get(i) instanceof CuentaNauta)
						encontrado = true;
				}
			}
			return encontrado;
		}
		
		// Comprobar si PERSONA NATURAL tiene mas de 1 telefono
		public boolean comprobarFijoPersonaNatural(Cliente titular){

			boolean encontrado = false;

			if(titular instanceof PersonaNatural){
				PersonaNatural pn = (PersonaNatural) titular;

				for(int i = 0; i < pn.getServicios().size() && !encontrado; i++){
					if(pn.getServicios().get(i) instanceof TelefonoFijo)
						encontrado = true;
				}
			}

			return encontrado;
		}
		
		// Comprobar si PERSONA NATURAL tiene mas de 2 telefonos moviles
		public boolean comprobarMovil(Cliente titular){

			int cantidad = 0;

			if(titular instanceof PersonaNatural){
				PersonaNatural pn = (PersonaNatural) titular;
				for (Servicio s : pn.getServicios()) {
					if (s instanceof TelefonoMovil)
						cantidad++;
				}
			}

			return cantidad >= 2;
		}
		
	//GESTION DE SERVICIOS
		
		
		// CRUD de Cuenta Nauta TODO
		// Agregar Cuenta Nauta
		public void crearCuentaNauta(Cliente titular, String nick) {

			boolean tieneTelefono = false;
			boolean tieneNauta = false;

			for(int i = 0; i < titular.getServicios().size() && !tieneTelefono ; i++){
				if(titular.getServicios().get(i) instanceof TelefonoFijo){
					tieneTelefono = true;
				}
			}
			if(!tieneTelefono)
				throw new IllegalArgumentException("Para contratar el nauta debe tener teléfono fijo");	

			if(titular instanceof PersonaJuridica)
				throw new IllegalArgumentException("Este tipo de cliente no puede tener CuentaNauta");

			if (comprobarNauta(titular))
				throw new IllegalArgumentException("El cliente ya tiene una CuentaNauta");

			Servicio s1 = new CuentaNauta(titular, nick);
			servicios.add(s1);

		}
		
		// Eliminar Cuenta Nauta 
		public boolean eliminarCuentaNauta(String nick) {
			boolean eliminado = false;
			CuentaNauta cuenta = null;

			// Buscar la cuenta a eliminar
			for(int i = 0; i < servicios.size() && !eliminado; i++) {
				if(servicios.get(i) instanceof CuentaNauta) {
					cuenta = (CuentaNauta)servicios.get(i);

					if(cuenta.getNick().equals(nick)) {
						// Eliminar de la lista principal de servicios
						servicios.remove(i);
						eliminado = true;

						// Eliminar del titular si existe
						if(cuenta.getTitular() != null) {
							cuenta.getTitular().getServicios().remove(cuenta);
							eliminarClienteServicio(cuenta.getTitular());
						}
					}
				}
			}

			return eliminado;
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

		// CRUD de Telefono Fijo TODO
		// Agregar Telefono Fijo
		public void agregarTelefonoFijo(String numero) {

			Servicio s1 = new TelefonoFijo(numero);
			serviciosDisponibles.add(s1);
		}

		// Asignar un telefono Fijo ya existente
		public void asignarTelefonoFijo(Cliente titular, String numero) {
			TelefonoFijo disponible = null;

			for (int i = 0; i < serviciosDisponibles.size() && disponible == null; i++) {
				Servicio s = serviciosDisponibles.get(i);
				if (s instanceof TelefonoFijo) {
					TelefonoFijo fijo = (TelefonoFijo) s;
					if (fijo.getNumero().equals(numero)) {
						disponible = fijo;
					}
				}
			}

			if (disponible == null)
				throw new IllegalArgumentException("No hay teléfono fijo disponible con el número especificado.");

			if (comprobarFijoPersonaNatural(titular))
				throw new IllegalArgumentException("Persona Natural no puede tener más de 1 teléfono fijo.");

			disponible.setTitular(titular);
			
			servicios.add(disponible);
			serviciosDisponibles.remove(disponible);
		}
		
		// Eliminar telefono fijo
		public boolean eliminarTelefonoFIjo(String numero) {
			boolean eliminado = false;
			TelefonoFijo fijoAEliminar = null;
			int indice = -1;

			// Buscar el teléfono fijo en servicios activos
					for (int i = 0; i < servicios.size() && !eliminado; i++) {
						if (servicios.get(i) instanceof TelefonoFijo) {
							TelefonoFijo fijo = (TelefonoFijo) servicios.get(i);
							if (fijo.getNumero().equals(numero)) {
								fijoAEliminar = fijo;
								indice = i;
								eliminado = true;
							}
						}
					}

			if (fijoAEliminar != null) {
				// Obtener el titular antes de modificar
				Cliente titular = fijoAEliminar.getTitular();

				// Eliminar de la lista principal
				servicios.remove(fijoAEliminar);

				//Eliminar el servicio del cliente
				ArrayList<Servicio> serviciosCliente = new ArrayList<>(titular.getServicios());
				for(Servicio c: serviciosCliente){
					if(c instanceof TelefonoFijo){
						if(((TelefonoFijo)c).getNumero().equals(fijoAEliminar.getNumero())){
							titular.getServicios().remove(c);
						}

					}
				}
				
				// Limpiar completamente el servicio

				fijoAEliminar.setTitular(null);

				// for de prueba
				for(Servicio s : titular.getServicios()){
					if(s instanceof TelefonoFijo){
						System.out.println(((TelefonoFijo)s).getNumero().equals(fijoAEliminar.getNumero()));
						System.out.println(((TelefonoFijo)s).getNumero());

					}
				}
				// Verificar que no exista ya en disponibles
				boolean existeEnDisponibles = false;
				for (Servicio s : serviciosDisponibles) {
					if (s instanceof TelefonoFijo && 
							((TelefonoFijo)s).getNumero().equals(numero)) {
						existeEnDisponibles = true;

					}
				}

				// Agregar a disponibles solo si no existe
				if (!existeEnDisponibles) {
					serviciosDisponibles.add(fijoAEliminar);
				}

				// Verificar si hay que eliminar al cliente
				eliminarClienteServicio(titular);
			}

			return eliminado;
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
		
		// CRUD de Telefono Movil TODO
		// Agregar Telefono Movil
		public void agregarTelefonoMovil(String numero, double montoPagar) {

			Servicio s1 = new TelefonoMovil(numero, montoPagar);
			serviciosDisponibles.add(s1);
		}


		// Asignar un telefono Movil ya existente a un cliente
		public void asignarTelefonoMovil(Cliente titular){
			TelefonoMovil disponible = null;

			for (int i = 0; i < serviciosDisponibles.size() && disponible == null; i++) {
				if (serviciosDisponibles.get(i) instanceof TelefonoMovil) {
					disponible = (TelefonoMovil) serviciosDisponibles.get(i);
				}
			}

			if (disponible == null) 
				throw new IllegalArgumentException("No hay teléfono móvil disponible");

			if(comprobarMovil(titular))
				throw new IllegalArgumentException("Persona Natural no puede tener más de 2 Teléfonos Móviles");

			disponible.setTitular(titular);

			servicios.add(disponible);
			serviciosDisponibles.remove(disponible);

		}
		

		// Eliminar Telefono Movil
		public boolean eliminarTelefonoMovil(String numero){

			boolean encontrado = false;
			TelefonoMovil movil = null;

			for(int i = 0; i < servicios.size() && !encontrado; i++){
				if(servicios.get(i) instanceof TelefonoMovil){
					movil = (TelefonoMovil)servicios.get(i);

					if(movil.getNumero().equals(numero))
						encontrado = true;

				}
			}

			if(movil != null && encontrado){
				servicios.remove(movil);
				movil.getTitular().getServicios().remove(movil);
				serviciosDisponibles.add(movil);
				eliminarClienteServicio(movil.getTitular());
			}
			return encontrado;
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
		
		//Buscar Telefono (movil o fijo) a partir de su numero de telefono
		public Telefono buscarTelefono(String numero){

			Telefono tlf = null;
			if(serviciosDisponibles != null){

				for(int i=0;i<serviciosDisponibles.size();i++){

					if(serviciosDisponibles.get(i) instanceof Telefono){



						if(((Telefono)serviciosDisponibles.get(i)).getNumero().equals(numero)){

							tlf = (Telefono)serviciosDisponibles.get(i);
						}
					}
				}
			}
			return tlf;
		}
		
		

	// GESTION DE REPRESENTANTES
	
	
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

		// Metodo para asignar representante a un cliente (Persona Jurï¿½dica o Entidad No Estatal)
		public void asignarRepresentanteACliente(Cliente cliente, Representante representante) {

			if (cliente != null && representante != null){


				// Si el cliente ya tenï¿½a un representante, lo liberamos
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
		
		// Metodo para desasignar representante de un cliente
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

	
	

	//========== REPORTES ==========

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
		
		for(Servicio s : servicios){
			if(s instanceof TelefonoFijo){
				TelefonoFijo tf = (TelefonoFijo)s;
				Cliente titular = tf.getTitular();

				// Analizamos a los clientes una sola vez
				if(!clientesEvaluados.contains(titular)){
					clientesEvaluados.add(titular);

					int cantLlamadas = 0;

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



	
	
	
	//Clientes con todos los servicios contratados(logica y escalabilidad mejorada) :)
    
    public ArrayList<Cliente> clientesConTodosLosTiposServicio() {
        ArrayList<Cliente> resultado = new ArrayList<>();

        for (Cliente cliente : clientes) {
            if (cliente.tieneTodosLosTiposServicio()) {
                resultado.add(cliente);
            }
        }

        return resultado;
    }
}