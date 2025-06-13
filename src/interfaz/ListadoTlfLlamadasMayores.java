package interfaz;

import auxiliares.*;
import logica.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
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
    private JButton btnAplicar;
    
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
        setBounds(100, 100, 1126, 700); // Aumenté ligeramente la altura
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(15, 16, 1074, 550); // Ajusté altura del panel
        getContentPane().add(panel);
        panel.setLayout(null);
        
        // Título de la ventana
        JLabel lblTitulo = new JLabel("Teléfonos con Llamadas Largas");
        lblTitulo.setFont(new Font("Serif", Font.BOLD, 24));
        lblTitulo.setBounds(0, 0, 500, 30);
        panel.add(lblTitulo);
        
        // Panel de controles
        JPanel panelControles = new JPanel();
        panelControles.setBounds(10, 32, 1049, 84);
        panelControles.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelControles.setBorder(BorderFactory.createTitledBorder("Seleccione el límite de minutos"));
        panel.add(panelControles);
        
        // Slider para selección de minutos
        slider = new JSlider(JSlider.HORIZONTAL, 1, 300, 100);
        slider.setMajorTickSpacing(50);
        slider.setMinorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setFont(new Font("Serif", Font.PLAIN, 14));
        slider.setPreferredSize(new Dimension(500, 50));
        
        // Campo de texto para minutos
        txtMinutos = new JTextField();
        txtMinutos.setHorizontalAlignment(JTextField.CENTER);
        txtMinutos.setFont(new Font("Serif", Font.PLAIN, 16));
        txtMinutos.setPreferredSize(new Dimension(60, 30));
        txtMinutos.setText("100");
        
        // Botón aplicar
        btnAplicar = new JButton("Aplicar Filtro");
        btnAplicar.setFont(new Font("Serif", Font.BOLD, 16));
        btnAplicar.setBackground(new Color(0, 0, 153));
        btnAplicar.setForeground(Color.WHITE);
        
        panelControles.add(new JLabel("Mínimo:"));
        panelControles.add(slider);
        panelControles.add(new JLabel("Minutos:"));
        panelControles.add(txtMinutos);
        panelControles.add(btnAplicar);
        
        // Tabla
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
        scrollPane.setBounds(15, 120, 1044, 400); // Ajusté posición y altura
        panel.add(scrollPane);
        
        table = new JTable();
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFont(new Font("Serif", Font.PLAIN, 16));
        scrollPane.setViewportView(table);
        
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Serif", Font.PLAIN, 18));
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
                    // No hacer nada si no es número
                }
            }
        });
        
        btnAplicar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int minutos = Integer.parseInt(txtMinutos.getText());
                    if (minutos >= 1 && minutos <= 300) {
                        cargarDatos(minutos);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un número válido", "Error", JOptionPane.ERROR_MESSAGE);
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
    
    // Resto del código (Singleton, dispose) permanece igual
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
                    "El listado de teléfonos con llamadas largas ya está abierto",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE);
                instance.toFront();
            }
        }
    }

    @Override
    public void dispose() {
        instance = null;
        super.dispose();
    }
}