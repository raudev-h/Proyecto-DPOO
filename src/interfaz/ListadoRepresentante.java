package interfaz;

import auxiliares.RepresentanteTableModel;
import logica.Cliente;
import logica.EmpresaTelecomunicaciones;
import logica.PersonaNatural;
import logica.Representante;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;

import excepciones.CarnetIdentidadInvalidoException;
import excepciones.NombreInvalidoException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ListadoRepresentante extends JDialog {
    private JTable table;
    private RepresentanteTableModel tableModel;
    private static ListadoRepresentante instance;
    private TableRowSorter<RepresentanteTableModel> sorter;
    private static Principal ventanaPrincipal; 

    
    // Campos para edición/creación
    private JPanel panelEdicion;
    private JTextField txtNombre;
    private JTextField txtNumId;
    private JButton btnAceptar;
    private JButton btnCancelar;
    private JLabel lblNombre;
    private JLabel lblNumId;
    private JLabel lblTituloEdicion;
    private boolean modoEdicion;
    private Representante representanteSeleccionado;
    private String numIdRepresentanteSeleccionado;
    
    // Campo para búsqueda
    private JTextField txtBusqueda;

    

    private ListadoRepresentante(Principal principal) {
        setModal(true);
        setBounds(100, 100, 1087, 790);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(15, 16, 1035, 702);
        getContentPane().add(panel);
        panel.setLayout(null);
        ventanaPrincipal = principal; // Guardar referencia
        
        // Cambiar imagen al abrir
        ventanaPrincipal.cambiarImagenFondo("/imagenes/e.png");
        
        // Panel de búsqueda simplificado
        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setBounds(15, 30, 588, 40);
        panelBusqueda.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panel.add(panelBusqueda);
        
        // Componente de búsqueda único
        JLabel lblBuscar = new JLabel("Buscar:");
        lblBuscar.setFont(new Font("Serif", Font.PLAIN, 18));
        panelBusqueda.add(lblBuscar);
        
        txtBusqueda = new JTextField();
        txtBusqueda.setFont(new Font("Serif", Font.PLAIN, 18));
        txtBusqueda.setColumns(30);
        txtBusqueda.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filtrarTabla();
            }
        });
        panelBusqueda.add(txtBusqueda);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(15, 80, 1005, 606);
        scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
        panel.add(scrollPane);
        
        // Inicializa el modelo y la tabla
        tableModel = new RepresentanteTableModel();
        table = new JTable(tableModel);
        sorter = new TableRowSorter<RepresentanteTableModel>(tableModel);
        table.setRowSorter(sorter);
        
        // Configuración de estilo idéntica a ListadoClientes
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFont(new Font("Serif", Font.PLAIN, 18));
        scrollPane.setViewportView(table);
        
        // Estilo del header
        JTableHeader header = table.getTableHeader();
        Font headerFont = new Font("Serif", Font.PLAIN, 20);
        header.setFont(headerFont);
        table.setRowHeight(25);
        
        // Título
        JLabel lblListadoDeRepresentante = new JLabel("Listado de Representantes");
        lblListadoDeRepresentante.setBounds(15, 0, 250, 20);
        lblListadoDeRepresentante.setFont(new Font("Serif", Font.BOLD, 21));
        panel.add(lblListadoDeRepresentante);
        
        JButton btnCrearRepresentante = new JButton("Crear Representante");
        btnCrearRepresentante.setFont(new Font("Serif", Font.PLAIN, 20));
        btnCrearRepresentante.setBackground(new Color(255, 255, 255));
        btnCrearRepresentante.setForeground(new Color(0, 0, 153));
        btnCrearRepresentante.setBounds(794, 30, 203, 30);
        btnCrearRepresentante.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirPanelCreacion();
            }
        });
        panel.add(btnCrearRepresentante);
        
        configurarMenuContextual();
        cargarDatos();
        initPanelEdicion();
    }

    // Método para filtrar la tabla mientras se escribe
    private void filtrarTabla() {
        final String textoBusqueda = txtBusqueda.getText().trim().toLowerCase();
        
        if (textoBusqueda.isEmpty()) {
            sorter.setRowFilter(null);
            return;
        }
        
        try {
            // Filtro que busca en ambas columnas (nombre y número de ID)
            RowFilter<RepresentanteTableModel, Object> rf = new RowFilter<RepresentanteTableModel, Object>() {
                public boolean include(Entry<? extends RepresentanteTableModel, ? extends Object> entry) {
                    String nombre = entry.getStringValue(0).toLowerCase();
                    String numId = entry.getStringValue(1).toLowerCase();
                    return nombre.contains(textoBusqueda) || numId.contains(textoBusqueda);
                }
            };
            sorter.setRowFilter(rf);
        } catch (Exception e) {
            manejarError(e, "Error al aplicar el filtro de búsqueda");
        }
    }   
     //Inicializa el panel de edición/creación con los componentes necesarios
     
    private void initPanelEdicion() {
        panelEdicion = new JPanel();
        panelEdicion.setBorder(new LineBorder(new Color(0, 0, 0)));
        panelEdicion.setBounds(1074, 55, 350, 650);
        panelEdicion.setVisible(false);
        panelEdicion.setLayout(null);
        getContentPane().add(panelEdicion);
        
        lblTituloEdicion = new JLabel("Creación de Representante");
        lblTituloEdicion.setFont(new Font("Serif", Font.BOLD, 21));
        lblTituloEdicion.setBounds(32, 12, 280, 28);
        panelEdicion.add(lblTituloEdicion);
        
        // Campo nombre
        lblNombre = new JLabel("Nombre Completo");
        lblNombre.setFont(new Font("Serif", Font.PLAIN, 19));
        lblNombre.setBounds(35, 50, 280, 20);
        panelEdicion.add(lblNombre);
        
        txtNombre = new JTextField();
        txtNombre.setBounds(35, 80, 280, 26);
        panelEdicion.add(txtNombre);
        txtNombre.setColumns(10);
        
        // Campo número de identidad
        lblNumId = new JLabel("Número de Identidad");
        lblNumId.setFont(new Font("Serif", Font.PLAIN, 19));
        lblNumId.setBounds(35, 120, 280, 20);
        panelEdicion.add(lblNumId);
        
        txtNumId = new JTextField();
        txtNumId.setBounds(35, 150, 280, 26);
        panelEdicion.add(txtNumId);
        txtNumId.setColumns(10);
        
        // Botón Aceptar
        btnAceptar = new JButton("Aceptar");
        btnAceptar.setForeground(new Color(255, 255, 255));
        btnAceptar.setBackground(new Color(0, 0, 153));
        btnAceptar.setFont(new Font("Serif", Font.PLAIN, 19));
        btnAceptar.setBounds(35, 580, 120, 30);
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (modoEdicion) {
                    actualizarRepresentante();
                } else {
                    crearRepresentante();
                }
            }
        });
        panelEdicion.add(btnAceptar);
        
        // Botón Cancelar
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(255, 255, 255));
        btnCancelar.setForeground(new Color(0, 0, 153));
        btnCancelar.setFont(new Font("Serif", Font.PLAIN, 19));
        btnCancelar.setBounds(195, 580, 120, 30);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cerrarPanelEdicion();
            }
        });
        panelEdicion.add(btnCancelar);
    }

    
     //Abre el panel de creación de representante
     
    private void abrirPanelCreacion() {
        modoEdicion = false;
        resetearCampos();
        lblTituloEdicion.setText("Creación de Representante");
        panelEdicion.setVisible(true);
        setSize(1478, 790);
    }

    
     //Abre el panel de edición con los datos del representante seleccionado
     
    private void abrirPanelEdicion(Representante representante) {
        modoEdicion = true;
        representanteSeleccionado = representante;
        lblTituloEdicion.setText("Edición de Representante");
        
        // Llenar campos con datos del representante
        txtNombre.setText(representante.getNombreCompleto());
        txtNumId.setText(representante.getNumId());
        
        panelEdicion.setVisible(true);
        setSize(1478, 790);
    }

    
     // CREAR REPRESENTANTE
     
    private void crearRepresentante() {
        String nombre = "";
        String numId = "";
        StringBuilder errores =  new StringBuilder();
        
        try {
        	try{
        		Cliente.validarNombre(txtNombre.getText());
        		nombre = txtNombre.getText();
        		lblNombre.setForeground(Color.black);
        		
        	}catch(Exception e){
        		errores.append("- ").append(e.getMessage()).append("\n");
	    	    lblNombre.setForeground(Color.RED); 
      		
        	}
        	try{
        		PersonaNatural.validarNumId(txtNumId.getText());
        		numId = txtNumId.getText();
        		//Comprobar que no este repetido
        		for(Representante r: EmpresaTelecomunicaciones.getInstancia().getRepresentantes()){
        			if(r.getNumId().equals(numId)){
        				throw new IllegalArgumentException("Ya existe una representante con este número de identidad");
        			}
        		}
        		        		
        		numId = txtNumId.getText();
        		lblNumId.setForeground(Color.black);
        		
        	}catch(Exception e){
        		errores.append("- ").append(e.getMessage()).append("\n");
        		lblNumId.setForeground(Color.RED); 
        	}
        	
        	if(errores.length() > 0){

                UIManager.put("OptionPane.messageFont", new Font("Serif", Font.PLAIN, 18));
                UIManager.put("OptionPane.buttonFont", new Font("Serif", Font.PLAIN, 16));
        		JOptionPane.showMessageDialog(
	    			    null, 
	    			    "ERRORES EN LOS DATOS:\n" + 
	    			    "----------------------------\n" + 
	    			    errores.toString() + 
	    			    "\n----------------------------\n" + 
	    			    "Por favor, corrija los campos resaltados en rojo.", 
	    			    "Error ", 
	    			    JOptionPane.ERROR_MESSAGE
	    			);	    	
        		
        	}else{
        		EmpresaTelecomunicaciones.getInstancia().agregarRepresentante(nombre, numId);
                cargarDatos();
                cerrarPanelEdicion();
             // Mostrar mensaje de éxito
                UIManager.put("OptionPane.messageFont", new Font("Serif", Font.PLAIN, 18));
                UIManager.put("OptionPane.buttonFont", new Font("Serif", Font.PLAIN, 16));
                JOptionPane.showMessageDialog(this, "Representante creado exitosamente", 
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
        		
        		
        	}
           
        } catch (Exception e) {
            manejarError(e, "Error al crear representante: " + e.getMessage());
        }
    }

    
     //EDITAR REPRESENTANTE
     
    private void actualizarRepresentante() {
    	
    	if (representanteSeleccionado == null) {
            manejarError(new Exception("No se ha seleccionado ningún representante"), "Error al actualizar");
            return;
        }
       
  
    	StringBuilder errores = new StringBuilder();

        // Resetear colores de validación
        lblNombre.setForeground(Color.BLACK);
        lblNumId.setForeground(Color.BLACK);
        
        try {
            // Validar nombre
            try {

                Representante.validarNombre(txtNombre.getText());
                lblNombre.setForeground(Color.BLACK);
            } catch (NombreInvalidoException e) {
                errores.append("- ").append(e.getMessage()).append("\n");
                lblNombre.setForeground(Color.RED);
            }
            
            // Validar número de identidad
            
            try {

                Representante.validarNumId(txtNumId.getText());
                
                // Validar duplicados (excepto el actual)
                for (Representante r : EmpresaTelecomunicaciones.getInstancia().getRepresentantes()) {
                    if (r != representanteSeleccionado && r.getNumId().equals(txtNumId.getText())) {

                        throw new CarnetIdentidadInvalidoException("Ya existe un representante con este número de identidad");
                    }
                }
                
                lblNumId.setForeground(Color.BLACK);
            } catch (CarnetIdentidadInvalidoException e) {
                errores.append("- ").append(e.getMessage()).append("\n");
                lblNumId.setForeground(Color.RED);
            }

            // Mostrar errores si hay alguno
            if (errores.length() > 0) {
                UIManager.put("OptionPane.messageFont", new Font("Serif", Font.PLAIN, 18));
                UIManager.put("OptionPane.buttonFont", new Font("Serif", Font.PLAIN, 16));
                JOptionPane.showMessageDialog(
                    null, 
                    "ERRORES EN LOS DATOS:\n" + 
                    "----------------------------\n" + 
                    errores.toString() + 
                    "\n----------------------------\n" + 
                    "Por favor, corrija los campos resaltados en rojo.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE
                );
            } else {

                // Actualizar datos del representante (usando los métodos con validación)
            	try{
	                representanteSeleccionado.setNombreCompleto(txtNombre.getText());
	                representanteSeleccionado.setNumId(txtNumId.getText());
            	}catch(Exception e){
            		 manejarError(e, "Error al actualizar representante, revise los datos " );
            	}
                
                cargarDatos();
                cerrarPanelEdicion();
                
                // Mostrar mensaje de éxito
                UIManager.put("OptionPane.messageFont", new Font("Serif", Font.PLAIN, 18));
                UIManager.put("OptionPane.buttonFont", new Font("Serif", Font.PLAIN, 16));
                JOptionPane.showMessageDialog(
                    this, 
                    "Representante actualizado correctamente", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        } catch (Exception e) {
            manejarError(e, "Error al actualizar representante: " + e.getMessage());
        }
    }

    
    // Cierra el panel de edición/creación y restaura el tamaño original
     
    private void cerrarPanelEdicion() {
        panelEdicion.setVisible(false);
        setSize(1087, 790);
        resetearCampos();
        representanteSeleccionado = null;
    }

    
     // Resetea los campos del formulario
     
    private void resetearCampos() {
        txtNombre.setText("");
        txtNumId.setText("");
        lblNombre.setForeground(Color.BLACK);
        lblNumId.setForeground(Color.BLACK);
    }


     //Maneja los errores mostrando un mensaje al usuario
    private void manejarError(Exception e, String mensaje) {
        UIManager.put("OptionPane.messageFont", new Font("Serif", Font.PLAIN, 18));
        UIManager.put("OptionPane.buttonFont", new Font("Serif", Font.PLAIN, 16));
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void cargarDatos() {
        ArrayList<Representante> representantes = EmpresaTelecomunicaciones.getInstancia().getRepresentantes();
        if(representantes == null) {
            representantes = new ArrayList<Representante>(); // Lista vacía si es nulo
        }
        tableModel.cargarDatos(representantes);
    }

    
     // Configura el menú contextual para la tabla
    
    private void configurarMenuContextual() {
        final JPopupMenu popupMenu = new JPopupMenu();
        
        JMenuItem menuEditar = new JMenuItem("Editar");
        JMenuItem menuEliminar = new JMenuItem("Eliminar");
        
        // Personalización de los menús
        menuEditar.setFont(new Font("Serif", Font.PLAIN, 20));
        menuEliminar.setFont(new Font("Serif", Font.PLAIN, 20));
        
        // Acción para editar representante
        menuEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                
                if (selectedRow >= 0) {
                    String idRepresentante = (String) tableModel.getValueAt(selectedRow, 1);

                    Representante rep = EmpresaTelecomunicaciones.getInstancia().buscarRepresentante(idRepresentante);

                    
                    if (rep != null) {
                        abrirPanelEdicion(rep);
                    }
                } else {
                    JOptionPane.showMessageDialog(
                        null, 
                        "Por favor seleccione un representante para editar", 
                        "Advertencia", 
                        JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        // Acción para eliminar representante
        menuEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                
                if (selectedRow >= 0) {
                    // Configurar estilo del diálogo de confirmación
                    UIManager.put("OptionPane.messageFont", new Font("Serif", Font.BOLD, 20));
                    UIManager.put("OptionPane.buttonFont", new Font("Serif", Font.BOLD, 18));
                    
                    String idRepresentante = (String) tableModel.getValueAt(selectedRow, 1);
                    
                    int confirm = JOptionPane.showConfirmDialog(null,
                        "¿Está seguro que desea eliminar este representante?", 
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                    
                    if (confirm == JOptionPane.YES_OPTION) {
                        boolean eliminado = EmpresaTelecomunicaciones.getInstancia().eliminarRepresentante(idRepresentante);
                        
                        if (eliminado) {
                            cargarDatos();
                            JOptionPane.showMessageDialog(
                                null, 
                                "Representante eliminado correctamente", 
                                "Éxito", 
                                JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(
                                null, 
                                "No se pudo eliminar el representante", 
                                "Error", 
                                JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(
                        null, 
                        "Por favor seleccione un representante para eliminar", 
                        "Advertencia", 
                        JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        popupMenu.add(menuEditar);
        popupMenu.add(menuEliminar);
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                showPopupIfTriggered(e);
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                showPopupIfTriggered(e);
            }
            
            private void showPopupIfTriggered(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {  
                    int row = table.rowAtPoint(e.getPoint());
                    if (row >= 0) {
                        table.setRowSelectionInterval(row, row);
                        popupMenu.show(table, e.getX(), e.getY());
                    }
                }
            }
        });
    }

 // Método Singleton 
    public static void abrirListadoRepresentante(Principal principal) {
        if (instance == null) {
            instance = new ListadoRepresentante(principal);
            instance.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            instance.setVisible(true);
        } else {
            instance.toFront();
            // Actualizar referencia si ya existe
            instance.ventanaPrincipal = principal;
        }
    }
    @Override
    public void dispose() {
        // Restaurar imagen al cerrar
        if (ventanaPrincipal != null) {
            ventanaPrincipal.cambiarImagenFondo("/imagenes/d.png");
        }
        instance = null;
        super.dispose();
    }
    
    public static Representante getRepresentanteSeleccionado(){
        return EmpresaTelecomunicaciones.getInstancia().getRepresentantes().get(0);
    }
}