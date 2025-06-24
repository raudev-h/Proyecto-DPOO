package interfaz;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ayuda extends JDialog {

    private final JPanel contentPanel = new JPanel();

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
        setSize(800, 600); // Tama�o consistente
        
        // Centrar la ventana
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);
        
        getContentPane().setLayout(new BorderLayout());
        
        // Panel de contenido mejorado
        contentPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        contentPanel.setLayout(new BorderLayout(0, 0));
        
        // �rea de texto con informaci�n de ayuda
        JTextArea txtAyuda = new JTextArea();
        txtAyuda.setEditable(false);
        txtAyuda.setFont(new Font("Serif", Font.PLAIN, 20)); // Fuente Serif tama�o 18
        txtAyuda.setLineWrap(true);
        txtAyuda.setWrapStyleWord(true);
        txtAyuda.setText(obtenerTextoAyuda());
        
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
        sb.append("SISTEMA DE GESTI�N DE TELECOMUNICACIONES\n\n");
        sb.append("Manual de Usuario\n");
        sb.append("----------------\n\n");
        
        sb.append("1. Gesti�n de Clientes\n");
        sb.append("   - Agregar nuevos clientes (Personas Naturales, Jur�dicas o Entidades No Estatales)\n");
        sb.append("   - Editar informaci�n de clientes existentes\n");
        sb.append("   - Asignar servicios a clientes\n\n");
        
        sb.append("2. Gesti�n de Servicios\n");
        sb.append("   - Tel�fonos Fijos: Asignar a clientes, editar n�meros\n");
        sb.append("   - Tel�fonos M�viles: Asignar con sus montos correspondientes\n");
        sb.append("   - Cuentas Nauta: Asignar con nick �nico (Personas Naturales solo 1 cuenta)\n\n");
        
        sb.append("3. Gesti�n de Representantes\n");
        sb.append("   - Asignar representantes a Entidades No Estatales o Personas Jur�dicas\n\n");
        
        sb.append("Para soporte t�cnico contacte a:\n\n"); // Doble salto de l�nea
        sb.append("raulalbertohechavarria@gmail.com\n");
        sb.append("anielvarela64@gmail.com\n\n\n"); // Triple salto de l�nea para separaci�n
        
        sb.append("Versi�n del Sistema: 1.0.0");
        
        return sb.toString();
    }
}