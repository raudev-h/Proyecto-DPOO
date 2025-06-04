package logica;

import java.util.*;

public class EmpresaTelecomunicaciones {
	// Atributos
	private static EmpresaTelecomunicaciones empresa;
	private ArrayList<Cliente> clientes;
	private ArrayList<Servicio> servicios;
	private ArrayList<Representante> representantes;

	// Constructor
	private EmpresaTelecomunicaciones() {
		clientes = new ArrayList<Cliente>();
		servicios = new ArrayList<Servicio>();
		representantes = new ArrayList<Representante>();
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
					
			for(Servicio s: cliente.getServicios()){
				servicios.remove(s);				
			}
			clientes.remove(cliente);
			eliminado = true;
			
		}
		
		return eliminado;
		
	}
	
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

	// Representantes
	public ArrayList<Representante> getRepresentantes() {
		return representantes;
	}

	public void setRepresentantes(ArrayList<Representante> representantes) {
		this.representantes = representantes;
	}

	// METODOS
	// Agregar Representante
	public void agregarRepresentante(String nombreCompleto, String numId) {
		Representante r1 = new Representante(nombreCompleto, numId);
		representantes.add(r1);
	}

	// Agregar Entidad no estatal
	public void agregarEntidadNoEstatal(String direccion, String nombreEntidad, Representante representante) {
		Cliente c1 = new EntidadNoEstatal(direccion, nombreEntidad, representante);
		clientes.add(c1);
	}

	// Agregar Persona Natural
	public void agregarPersonaNatural(String direccion, String municipio, String provincia, String nombre,
			String numId) {
		Cliente c1 = new PersonaNatural(direccion, municipio, provincia, nombre, numId);
		clientes.add(c1);
	}

	// Agregar Persona Juridica
	public void agregarPersonaJuridica(String direccion, String municipio, String provincia, String nombreEmpresaString,
			String organismo, Representante representante) {
		Cliente c1 = new PersonaJuridica(direccion, municipio, provincia, nombreEmpresaString, organismo,
				representante);
		clientes.add(c1);
	}

	// Agregar Cuenta Nauta
	public void crearCuentaNauta(Cliente titular, String nick) {
		Servicio s1 = new CuentaNauta(titular, nick);
		servicios.add(s1);
	}

	// Agregar Telefono Fijo
	public void agregarTelefonoFijo(Cliente titular, String numero) {
		Servicio s1 = new TelefonoFijo(titular, numero);
		servicios.add(s1);
	}

	// Agregar Telefono Movil
	public void agregarTelefonoMovil(Cliente titular, String numero, double montoPagar) {
		Servicio s1 = new TelefonoMovil(titular, numero, montoPagar);
		servicios.add(s1);
	}
	
	//Buscar a un cliente por su nombre TODO: Testear metodo ya que se uso un while...algo poco convencional en estos tiempos 
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
		
		
		
		
	

	/*
	 * // LLamadas de Movil que duraron mas de 100 minutos
	 * public ArrayList<TelefonoMovil> telefonosMovilLLamadasMasMin(int minutos){
	 * 
	 * ArrayList<TelefonoMovil> telefonosMovilMasMin = new
	 * ArrayList<TelefonoMovil>();
	 * if(!servicios.isEmpty()){ //Se comprueba que hay al menos un servicio
	 * 
	 * for(Servicio s: servicios){
	 * if(s instanceof Telefono){
	 * 
	 * if(s instanceof TelefonoMovil){
	 * 
	 * //Buscamos la cantidad de llamadas que superan los 100 min del telefono
	 * int llamadasMasMin = ((TelefonoMovil)s).llamadasMasMin(minutos).size();
	 * if(llamadasMasMin > 0){
	 * telefonosMovilMasMin.add((TelefonoMovil)s);
	 * }
	 * }
	 * }
	 * }
	 * }
	 * 
	 * return telefonosMovilMasMin;
	 * }
	 */
	// Buscar los clientes que tengan al menoos 30% (4 ) meses de mas de 1000 cup de
	// montoTotal en sus Cuentas Nautas
	public ArrayList<Cliente> clientesMasMilMontoNauta() {

		ArrayList<Cliente> mejoresClientes = new ArrayList<Cliente>();

		if (!servicios.isEmpty()) {

			for (Servicio s : servicios) {

				if (s instanceof CuentaNauta) {

					if (((CuentaNauta) s).cantMesesMasMilGasto() >= 4) {
						mejoresClientes.add(s.getTitular());
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

	public ArrayList<Map.Entry<String, Integer>> menorCantCuentasNauta() {
		ArrayList<PersonaNatural> personasNaturales = new ArrayList<PersonaNatural>();
		String[] provincias = { "Pinar del Rio", "Artemisa", "La Habana", "Mayabeque", "Matanzas", "Cienfuegos",
				"Villa Clara", "Sancti Spiritus", "Ciego de Avila", "Camag�ey", "Las Tunas", "Holgu�n",
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

//		BubbleSort.sort(provinciasOrdenadas);

		return provinciasOrdenadas;
	}
}
