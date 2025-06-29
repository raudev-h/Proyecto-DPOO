package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;



import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ayuda extends JDialog {
    private final JPanel contentPanel = new JPanel();
    private static Principal ventanaPrincipal; // Referencia a Principal
    private static Ayuda instance; // Para patrón Singleton


    public static void mostrarAyuda() {
        try {
            Ayuda dialog = new Ayuda();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setLocationRelativeTo(null); // Centrar en pantalla

            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Ayuda() {
        setTitle("Ayuda del Sistema");
        setModal(true);
        setSize(800, 600); // TamaÃ±o consistente

        // Centrar la ventana
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);

        getContentPane().setLayout(new BorderLayout());

        // Panel de contenido mejorado
        contentPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        contentPanel.setLayout(new BorderLayout(0, 0));

        // === Usamos JTextPane para formateo de color ===
        JTextPane txtAyuda = new JTextPane();
        txtAyuda.setEditable(false);
        txtAyuda.setFont(new Font("Serif", Font.PLAIN, 20));
        txtAyuda.setText(obtenerTextoAyuda());

        // Aplicar color oscuro a los correos
        aplicarEstiloCorreos(txtAyuda);

        // Asegurarse de que empiece desde arriba
        txtAyuda.setCaretPosition(0);

        JScrollPane scrollPane = new JScrollPane(txtAyuda);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        // Panel de botones
        JPanel buttonPane = new JPanel();
        buttonPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        buttonPane.setLayout(new BorderLayout(0, 0));

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnCerrar.setFont(new Font("Serif", Font.BOLD, 18)); // Fuente consistente
        buttonPane.add(btnCerrar, BorderLayout.EAST);
        getRootPane().setDefaultButton(btnCerrar);
    }

    private String obtenerTextoAyuda() {
        StringBuilder sb = new StringBuilder();
        sb.append("SISTEMA DE GESTIÓN DE TELECOMUNICACIONES\n\n");
        sb.append("Manual de Usuario\n");
        sb.append("----------------\n\n");

        sb.append("1. Gestión de Clientes\n");
        sb.append("   - Agregar nuevos clientes (Personas Naturales, Jurídicas o Entidades No Estatales)\n");
        sb.append("   - Editar información de clientes existentes\n");
        sb.append("   - Asignar servicios a clientes\n\n");

        sb.append("2. Gestion de Servicios\n");
        sb.append("   - Teléfonos Fijos: Asignar a clientes, editar números\n");
        sb.append("   - Teléfonos Móviles: Asignar con sus montos correspondientes\n");
        sb.append("   - Cuentas Nauta: Asignar con nick único (Personas Naturales solo 1 cuenta)\n\n");

        sb.append("3. Gestión de Representantes\n");
        sb.append("   - Asignar representantes a Entidades No Estatales o Personas Jurídicas\n\n");

        sb.append("Para soporte técnico contacte a:\n\n");
        sb.append("raulalbertohechavarria@gmail.com\n");
        sb.append("anielvarela64@gmail.com\n");
        sb.append("rubenanazco0@gmail.com\n\n\n");

        sb.append("Versión del Sistema: 1.0.0");

        return sb.toString();
    }

    /**
     * Resalta los correos en negro más oscuro usando StyledDocument
     */
    private void aplicarEstiloCorreos(JTextPane txtPane) {
        StyledDocument doc = txtPane.getStyledDocument();
        String texto = txtPane.getText();

        // Atributo: color negro fuerte
        SimpleAttributeSet attrs = new SimpleAttributeSet();
        new Color(20, 20, 20);
		StyleConstants.setForeground(attrs, UIManager.getColor("Label.foreground")); // Negro oscuro

        // Buscar y aplicar a cada correo
        String[] correos = {
            "raulalbertohechavarria@gmail.com",
            "anielvarela64@gmail.com"
        };

        for (String correo : correos) {
            int pos = texto.indexOf(correo);
            if (pos >= 0) {
                doc.setCharacterAttributes(pos, correo.length(), attrs, false);
            }
        }
    }
}

