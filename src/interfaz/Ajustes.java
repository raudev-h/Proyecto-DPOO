package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import auxiliares.ToggleSwitch;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

public class Ajustes extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private static Principal ventanaPrincipal; // Referencia a Principal
    private static Ajustes instance; // Singleton

    // Método para mostrar Ajustes en modo Singleton, y cambiar imagen
    public static void mostrarAjustes(Principal principal) {
        if (instance == null) {
            instance = new Ajustes(principal);
            instance.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            instance.setModal(true); // Bloquea la aplicación hasta cerrar
            instance.setLocationRelativeTo(null); // Centrar
            // Cambiar imagen al abrir
            ventanaPrincipal = principal;
            if (ventanaPrincipal != null) {
                ventanaPrincipal.cambiarImagenFondo("/imagenes/e.png");
            }
            instance.setVisible(true);
        } else {
            instance.toFront();
            ventanaPrincipal = principal;
        }
    }

    // Constructor privado para Singleton
    private Ajustes(Principal principal) {
        setTitle("Ajustes");
        setBounds(100, 100, 400, 250);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        ventanaPrincipal = principal;

        JLabel lblSwitch = new JLabel("Modo Oscuro:");
        lblSwitch.setFont(new java.awt.Font("Serif", java.awt.Font.BOLD, 18));
        contentPanel.add(lblSwitch);

        // Usa tu switch personalizado
        final ToggleSwitch toggleSwitch = new ToggleSwitch();
        toggleSwitch.setToolTipText("Activa/desactiva modo oscuro");

        // --- ARREGLO: Estado inicial sincronizado con el modo actual ---
        boolean darkMode = UIManager.getLookAndFeel() instanceof FlatDarkLaf;
        toggleSwitch.setSelected(darkMode);

        toggleSwitch.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                boolean dark = toggleSwitch.isSelected();
                try {
                    if (dark) {
                        UIManager.setLookAndFeel(new FlatDarkLaf());
                    } else {
                        UIManager.setLookAndFeel(new FlatLightLaf());
                    }
                    // Forzar líneas en tablas tras el cambio
                    UIManager.put("Table.showVerticalLines", Boolean.TRUE);
                    UIManager.put("Table.showHorizontalLines", Boolean.TRUE);
                    // Actualizar todas las ventanas y limpiar personalizaciones de color
                    Window[] windows = Window.getWindows();
                    for (int i = 0; i < windows.length; i++) {
                        resetColorsAndBorders(windows[i]);
                        SwingUtilities.updateComponentTreeUI(windows[i]);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        contentPanel.add(toggleSwitch);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                dispose();
            }
        });
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);
    }

    /**
     * Restablece los colores (foreground/background) y bordes personalizados
     * de todos los componentes dentro del contenedor dado (recursivo).
     */
    private static void resetColorsAndBorders(java.awt.Container container) {
        java.awt.Component[] comps = container.getComponents();
        for (int i = 0; i < comps.length; i++) {
            comps[i].setForeground(null);
            comps[i].setBackground(null);
            if (comps[i] instanceof javax.swing.JComponent) {
                ((javax.swing.JComponent) comps[i]).setBorder(null);
            }
            if (comps[i] instanceof java.awt.Container) {
                resetColorsAndBorders((java.awt.Container) comps[i]);
            }
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

}