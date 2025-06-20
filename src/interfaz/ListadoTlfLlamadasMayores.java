package interfaz;

import auxiliares.*;
import logica.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ListadoTlfLlamadasMayores extends JDialog {
    private JTable table;
    private static ListadoTlfLlamadasMayores instance;
    private JSlider slider;
    private JTextField txtMinutos;
    
    public static void main(String[] args) {
        try {
            ListadoTlfLlamadasMayores dialog = new ListadoTlfLlamadasMayores();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ListadoTlfLlamadasMayores() {
        setBounds(100, 100, 1126, 700); 
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(15, 16, 1074, 550); 
        getContentPane().add(panel);
        panel.setLayout(null);
        
        // T�tulo de la ventana - Estilo igual a otras ventanas
        JLabel lblTitulo = new JLabel("Tel�fonos con Llamadas Largas");
        lblTitulo.setFont(new Font("Serif", Font.BOLD, 24));
        lblTitulo.setBounds(0, 0, 500, 30);
        panel.add(lblTitulo);
        
        // Panel de controles
        JPanel panelControles = new JPanel();
        panelControles.setBounds(10, 32, 1049, 100);
        panelControles.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.BLACK),
            "Seleccione el limite de minutos",
            TitledBorder.DEFAULT_JUSTIFICATION,
            TitledBorder.DEFAULT_POSITION,
            new Font("Serif", Font.BOLD, 18), // Fuente del t�tulo del borde
            Color.BLACK
        ));
        panel.add(panelControles);
        
        // Slider para selecci�n de minutos - Estilo mejorado
        slider = new JSlider(JSlider.HORIZONTAL, 1, 300, 100);
        slider.setBounds(168, 23, 600, 60);
        slider.setMajorTickSpacing(50);
        slider.setMinorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setFont(new Font("Serif", Font.PLAIN, 18)); // Fuente m�s grande
        slider.setPreferredSize(new Dimension(600, 60)); 
        panelControles.setLayout(null);
        
        // Etiquetas para el slider - Estilo consistente
        JLabel lblMinimo = new JLabel("Minimo:");
        lblMinimo.setBounds(93, 45, 60, 39);
        lblMinimo.setFont(new Font("Serif", Font.PLAIN, 18));
        panelControles.add(lblMinimo);
        
        panelControles.add(slider);
        
        JLabel lblMinutos = new JLabel("Minutos:");
        lblMinutos.setBounds(793, 59, 63, 24);
        lblMinutos.setFont(new Font("Serif", Font.PLAIN, 18));
        panelControles.add(lblMinutos);
        
        // Campo de texto para minutos - Estilo consistente
        txtMinutos = new JTextField();
        txtMinutos.setBounds(876, 56, 80, 30);
        txtMinutos.setHorizontalAlignment(JTextField.CENTER);
        txtMinutos.setFont(new Font("Serif", Font.PLAIN, 18)); // Fuente m�s grande
        txtMinutos.setPreferredSize(new Dimension(80, 30));
        txtMinutos.setText("100");
        panelControles.add(txtMinutos);
        
        // Tabla - Estilo id�ntico a otras ventanas
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
        scrollPane.setBounds(15, 120, 1044, 400); 
        panel.add(scrollPane);
        
        table = new JTable();
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFont(new Font("Serif", Font.PLAIN, 18)); // Fuente m�s grande
        scrollPane.setViewportView(table);
        
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Serif", Font.PLAIN, 20)); // Igual que otras ventanas
        table.setRowHeight(25);
        
        // Listeners
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (!slider.getValueIsAdjusting()) {
                    int minutos = slider.getValue();
                    txtMinutos.setText(String.valueOf(minutos));
                    cargarDatos(minutos);
                }
            }
        });
        
        txtMinutos.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                try {
                    int minutos = Integer.parseInt(txtMinutos.getText());
                    if (minutos >= 1 && minutos <= 300) {
                        slider.setValue(minutos);
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            cargarDatos(minutos);
                        }
                    }
                } catch (NumberFormatException ex) {
                    // No hacer nada si no es n�mero
                }
            }
        });
        
        // Carga inicial
        cargarDatos(100);
    }

    private void cargarDatos(int limiteMinutos) {
        TlfLlamadasMas100min modelo = new TlfLlamadasMas100min();
        modelo.cargarDatosTelefonos(limiteMinutos);
        table.setModel(modelo);
        table.repaint();
    }
    
    // M�todo para el patr�n singleton
    public static void abrirListadoLlamadasMayores() {
        if (instance == null) {
            instance = new ListadoTlfLlamadasMayores();
            instance.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            instance.setVisible(true);
        } else {
            if (!instance.isVisible()) {
                instance.setVisible(true);
            } else {
                UIManager.put("OptionPane.messageFont", new Font("Serif", Font.BOLD, 20));
                UIManager.put("OptionPane.buttonFont", new Font("Serif", Font.BOLD, 18));
                UIManager.put("OptionPane.background", new Color(240, 240, 240));
                UIManager.put("Panel.background", new Color(240, 240, 240));
                
                JOptionPane.showMessageDialog(null, 
                    "El listado de tel�fonos con llamadas largas ya est� abierto",
                    "Informaci�n",
                    JOptionPane.INFORMATION_MESSAGE);
                instance.toFront();
            }
        }
    }
    
    // Liberar la instancia al cerrar la pantalla
    @Override
    public void dispose() {
        instance = null;
        super.dispose();
    }
}