package interfaz;
import auxiliares.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JProgressBar;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
//import de funciones del Mouse 
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import logica.EmpresaTelecomunicaciones;

import logica.*;


public class ListadoClientes extends JDialog {
    private JTable table;
    private ClienteTableModel tableModel;
    private static ListadoClientes instance; //Variable para controlar la cant de instancias del Listado de Clientes
    

    public static void main(String[] args) {
        try {
            ListadoClientes dialog = new ListadoClientes();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ListadoClientes() {
        setBounds(100, 100, 1126, 662);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(15, 16, 1074, 496);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
        scrollPane.setBounds(15, 36, 1044, 452);
        panel.add(scrollPane);
        
        // Inicializa el modelo y la tabla
        tableModel = new ClienteTableModel();
        table = new JTable(tableModel);
        
        //Impedir el reordenamiento de las columnas de las tablas 
        table.getTableHeader().setReorderingAllowed(false);;
        
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFont(new Font("Serif", Font.PLAIN, 18));
        scrollPane.setViewportView(table);
        
        //Ajustar el diseño de la tabla(Header)
        JTableHeader header = table.getTableHeader();
        Font headerFont = new Font("Serif",Font.PLAIN, 20);
        header.setFont(headerFont);
        
        table.setFont(new Font("Serif", Font.PLAIN, 20));
        table.setRowHeight(25); 
        
        //Ajuste del Titulo 
        JLabel lblListadoDeClientes = new JLabel("Listado de Clientes");
        lblListadoDeClientes.setFont(new Font("Serif", Font.BOLD, 21));
        lblListadoDeClientes.setBounds(15, 0, 195, 20);
        panel.add(lblListadoDeClientes);
        
        JButton btnNewButton = new JButton("Cargar Datos");
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setBackground(new Color(0, 0, 153));
        btnNewButton.setFont(new Font("Serif", Font.BOLD, 21));
        btnNewButton.setBounds(434, 528, 189, 29);
        getContentPane().add(btnNewButton);
        
        configurarMenuContextual();
            
        
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                tableModel.cargarClientes();
            }
        });
    }
    public static void abrirListadoClientes(){
    	if(instance == null){
    		
    		instance = new ListadoClientes();
    		instance.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            instance.setVisible(true);        
    	}
    	else {
    		if(!instance.isVisible()){
    		instance.setVisible(true);
    		}
	    	else{
	    		
	    		// Cambiar fuente, color de texto y fondo
	    		UIManager.put("OptionPane.messageFont", new Font("Serif", Font.BOLD, 20));
	    		UIManager.put("OptionPane.buttonFont", new Font("Serif", Font.BOLD, 18));
	    		UIManager.put("OptionPane.background", new Color(240, 240, 240));
	    		UIManager.put("Panel.background", new Color(240, 240, 240));
	    		UIManager.put("OptionPane.title", new Font("Serif",Font.PLAIN,20));
	    		
	    		JOptionPane.showMessageDialog(null, "La ventana del listado de clientes ya se encuentra abierta",null,JOptionPane.INFORMATION_MESSAGE);
	    		instance.toFront();
	    	}
    	}
    		
    	
    }
    @Override
    public void dispose() {
        instance = null; // Libera la instancia al cerrarla
        super.dispose();
    }
    
    //Configuracion del menu emergente de accion a un Cliente
    private void configurarMenuContextual(){
    	
    	final JPopupMenu popupMenu = new JPopupMenu();
    	
    	JMenuItem menuEditar = new JMenuItem("Editar");
    	JMenuItem menuEliminar = new JMenuItem("Eliminar");
    	
    	//Personalizacion de los menu
    	menuEditar.setFont(new Font("Serif", Font.PLAIN, 20));
    	menuEliminar.setFont(new Font("Serif", Font.PLAIN, 20));
    	
    	// Accion de eliminar el menuItem Eliminar
    	menuEliminar.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        // Obtener la fila seleccionada
    	        int selectedRow = table.getSelectedRow();
    	        
    	        if (selectedRow >= 0) { //Comprobar que la fila seleccionada es correcta
    	        	
    	            // Confirmar la eliminación
    	            UIManager.put("OptionPane.messageFont", new Font("Serif", Font.BOLD, 20));
    	            UIManager.put("OptionPane.buttonFont", new Font("Serif", Font.BOLD, 18));
    	            
    	            int confirm = JOptionPane.showConfirmDialog(null,"¿Está seguro que desea eliminar este cliente?", 
    	                "Confirmar eliminación",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
    	            
    	            if (confirm == JOptionPane.YES_OPTION) {
    	                // Obtener el cliente de la fila correspondiente
    	                String nombreCliente = (String) tableModel.getValueAt(selectedRow, 0);
    	                
    	                // Eliminar el cliente de la empresa
    	                boolean eliminado = EmpresaTelecomunicaciones.getInstancia().eliminarCliente(nombreCliente);
    	                
    	                if (eliminado) {
    	                    // Actualizar la tabla
    	                    tableModel.cargarClientes();
    	                    JOptionPane.showMessageDialog(
    	                        null, 
    	                        "Cliente eliminado correctamente", 
    	                        "Éxito", 
    	                        JOptionPane.INFORMATION_MESSAGE);
    	                } else {
    	                    JOptionPane.showMessageDialog(
    	                        null, 
    	                        "No se pudo eliminar el cliente", 
    	                        "Error", 
    	                        JOptionPane.ERROR_MESSAGE);
    	                }
    	            }
    	        } else {
    	            JOptionPane.showMessageDialog(
    	                null, 
    	                "Por favor seleccione un cliente para eliminar", 
    	                "Advertencia", 
    	                JOptionPane.WARNING_MESSAGE);
    	        }
    	    }
    	});
    	
    	//Añadir los menuItem al popMenu
    	popupMenu.add(menuEditar);
    	popupMenu.add(menuEliminar);
    	
    	
    	table.addMouseListener(new MouseAdapter() {
    		
    		/*Algunos  Sistemas Operativos activan el menu con el mousePressed y otros con el mouseReleased
    		 * Implementando ambos nos aseguramos que siempre funcione, haciendo la app mas robusta
    		 * */
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
    
  
    
    
    
    
    
    
    
}