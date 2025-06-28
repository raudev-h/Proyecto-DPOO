package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

public class Ajustes extends JDialog {

    private final JPanel contentPanel = new JPanel();

    public Ajustes() {
        setTitle("Ajustes");
        setBounds(100, 100, 400, 250);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);

        // Botón para cambiar a Modo Oscuro (FlatLaf Dark)
        JButton btnOscuro = new JButton("Modo Oscuro");
        btnOscuro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    UIManager.setLookAndFeel(new FlatDarkLaf());
                    // Cambia el Look & Feel de toda la aplicación
                    for (java.awt.Window window : java.awt.Window.getWindows()) {
                        SwingUtilities.updateComponentTreeUI(window);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        contentPanel.add(btnOscuro);

        // Botón para cambiar a Modo Normal (FlatLaf Light)
        JButton btnClaro = new JButton("Modo Claro");
        btnClaro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    UIManager.setLookAndFeel(new FlatLightLaf());
                    // Cambia el Look & Feel de toda la aplicación
                    for (java.awt.Window window : java.awt.Window.getWindows()) {
                        SwingUtilities.updateComponentTreeUI(window);
                    }
                    javax.swing.UIManager.put("Table.showVerticalLines", true);
                    javax.swing.UIManager.put("Table.showHorizontalLines", true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        contentPanel.add(btnClaro);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);
    }

    // Método main solo para pruebas individuales de este diálogo
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Ajustes dialog = new Ajustes();
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }
}