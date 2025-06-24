package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Ayuda extends JDialog {
    private final JPanel contentPanel = new JPanel();
    private static Principal ventanaPrincipal; // Referencia a Principal
    private static Ayuda instance; // Para patrón Singleton

    public static void main(String[] args) {
        try {
            Ayuda dialog = new Ayuda(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Constructor modificado para recibir Principal
    public Ayuda(Principal principal) {
        setModal(true);
        setBounds(100, 100, 450, 300);
        ventanaPrincipal = principal; // Guardar referencia
        
        // Cambiar imagen al abrir si tenemos referencia
        if (ventanaPrincipal != null) {
            ventanaPrincipal.cambiarImagenFondo("/imagenes/e.png");
        }
        
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        
        JButton okButton = new JButton("OK");
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);
    }

    @Override
    public void dispose() {
        // Restaurar imagen al cerrar si tenemos referencia
        if (ventanaPrincipal != null) {
            ventanaPrincipal.cambiarImagenFondo("/imagenes/d.png");
        }
        super.dispose();
    }
    
    // Método estático para abrir la ayuda
    public static void abrirAyuda(Principal principal) {
        if (instance == null) {
            instance = new Ayuda(principal);
            instance.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            instance.setVisible(true);
        } else {
            // Actualizar referencia si ya existe
            instance.ventanaPrincipal = principal;
            instance.toFront();
        }
    }
}