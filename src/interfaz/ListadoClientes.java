package interfaz;
import auxiliares.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
        setBounds(100, 100, 840, 662);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(15, 16, 788, 496);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
        scrollPane.setBounds(15, 36, 758, 452);
        panel.add(scrollPane);
        
        // Inicializa el modelo y la tabla
        tableModel = new ClienteTableModel();
        table = new JTable(tableModel);
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
        btnNewButton.setBounds(302, 519, 189, 29);
        getContentPane().add(btnNewButton);
        
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
    
}