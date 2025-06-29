package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;

public class login extends JFrame {

    // 1. Variable estática para la instancia única
    private static login instance = null;
    
    private JPanel contentPane;
    private JTextField textFieldUsuario;
    private JPasswordField textFieldPassword;
    private JLabel mensajeError;
    private JLabel mensajeIntentos;
    private JLabel lblTemporizador;
    
    // Control de intentos
    private int intentosFallidos = 0;
    private static final int MAX_INTENTOS = 3;
    private static final int TIEMPO_BLOQUEO = 60 * 1000; // 1 min de espera
    private Timer bloqueoTimer;
    private int segundosRestantes;

    private boolean autenticado = false;

    public boolean isAutenticado() {
        return autenticado;
    }
    
    // 2. Constructor privado para evitar instanciación externa
    private login() {
        configurarVentana();
        inicializarComponentes();
    }
    
    // 3. Método estático para obtener la instancia única
    public static login getInstance() {
        if (instance == null) {
            instance = new login();
        }       
        return instance;
    }

    private void configurarVentana() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 775, 443);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        // Elementos de diseño (manteniendo tu estilo original)
        JLabel lblBienvenido = new JLabel("Bienvenido ");
        lblBienvenido.setFont(new Font("Serif", Font.BOLD, 32));
        lblBienvenido.setBounds(47, 28, 269, 35);
        contentPane.add(lblBienvenido);

        JLabel lblDeVuelta = new JLabel("de vuelta!");
        lblDeVuelta.setFont(new Font("Serif", Font.BOLD, 32));
        lblDeVuelta.setBounds(210, 22, 279, 47);
        contentPane.add(lblDeVuelta);

        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setFont(new Font("Serif", Font.PLAIN, 22));
        lblUsuario.setBounds(57, 79, 128, 29);
        contentPane.add(lblUsuario);

        JLabel lblContrasena = new JLabel("Contraseña");
        lblContrasena.setFont(new Font("Serif", Font.PLAIN, 22));
        lblContrasena.setBounds(61, 151, 115, 20);
        contentPane.add(lblContrasena);

        // Campos de texto
        textFieldUsuario = new JTextField();
        textFieldUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldUsuario.setBounds(57, 113, 259, 26);
        contentPane.add(textFieldUsuario);
        textFieldUsuario.setColumns(10);

        textFieldPassword = new JPasswordField();
        textFieldPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        textFieldPassword.setBounds(57, 177, 259, 26);
        contentPane.add(textFieldPassword);

        // Mensaje de error
        mensajeError = new JLabel("Usuario o contraseña incorrectos");
        mensajeError.setForeground(Color.RED);
        mensajeError.setFont(new Font("Serif", Font.PLAIN, 18));
        mensajeError.setBounds(57, 206, 346, 20);
        mensajeError.setVisible(false);
        contentPane.add(mensajeError);
        
        mensajeIntentos = new JLabel("");
        mensajeIntentos.setForeground(Color.RED);
        mensajeIntentos.setFont(new Font("Serif", Font.PLAIN, 18));
        mensajeIntentos.setBounds(57, 231, 346, 20);
        mensajeIntentos.setVisible(false);
        contentPane.add(mensajeIntentos);

        // Botón Iniciar Sesión
        JButton iniciarSesion = new JButton("Iniciar Sesión");
        iniciarSesion.setFont(new Font("Serif", Font.PLAIN, 21));
        iniciarSesion.setBounds(47, 292, 162, 29);
        iniciarSesion.setBackground(new Color(0, 0, 153));
        iniciarSesion.setForeground(Color.WHITE);
        iniciarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
               verificarCredenciales();
            }
        });
        contentPane.add(iniciarSesion);

        // Temporizador
        lblTemporizador = new JLabel("");
        lblTemporizador.setForeground(Color.RED);
        lblTemporizador.setFont(new Font("Serif", Font.PLAIN, 18));
        lblTemporizador.setBounds(57, 256, 244, 20);
        lblTemporizador.setVisible(false);
        contentPane.add(lblTemporizador);
        
        // Botón Salir
        JButton salir = new JButton("Salir");
        salir.setFont(new Font("Serif", Font.PLAIN, 21));
        salir.setForeground(Color.WHITE);
        salir.setBackground(new Color(0, 0, 153));
        salir.setBounds(224, 292, 92, 29);
        salir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        contentPane.add(salir);
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/imagenes/DB.png")));
        lblNewLabel.setBounds(401, 28, 298, 307);
        contentPane.add(lblNewLabel);

    }

    private void verificarCredenciales() {
        if (intentosFallidos >= MAX_INTENTOS) {
            UIManager.put("OptionPane.messageFont", new Font("Serif", Font.PLAIN, 20));
            UIManager.put("OptionPane.buttonFont", new Font("Serif", Font.PLAIN, 18));
            JOptionPane.showMessageDialog(this, 
                "Demasiados intentos. Espere 1 minuto.", 
                "Cuenta Bloqueada", 
                JOptionPane.ERROR_MESSAGE);
        } else {
            String usuario = textFieldUsuario.getText();
            String password = new String(textFieldPassword.getPassword());

            if (validarUsuario(usuario) && validarPassword(password)) {
                iniciarSesion();
            } else {
                manejarIntentoFallido();
            }
        }
    }

    private boolean validarUsuario(String usuario) {
        return !usuario.trim().isEmpty() && usuario.equals("Admin");
    }

    private boolean validarPassword(String password) {
        return !password.trim().isEmpty() && password.equals("etecsa");
    }

    private void iniciarSesion() {
        autenticado = true;
        dispose();
        Principal.getInstance().setVisible(true);
    }

    private void manejarIntentoFallido() {
        intentosFallidos++;
        mensajeError.setText("Usuario o Contraseña incorrectos");
        mensajeIntentos.setText("Intentos: "+ intentosFallidos + "/" + MAX_INTENTOS);
        mensajeError.setVisible(true);
        mensajeIntentos.setVisible(true);
        
        if (intentosFallidos >= MAX_INTENTOS) {
            bloquearSistema();
        }
    }

    private void bloquearSistema() {
        textFieldUsuario.setEnabled(false);
        textFieldPassword.setEnabled(false);
        textFieldUsuario.setText("");
        textFieldPassword.setText("");
        segundosRestantes = TIEMPO_BLOQUEO / 1000; // Convertir a segundos
        lblTemporizador.setText("Tiempo restante: " + segundosRestantes + "s");
        lblTemporizador.setVisible(true);
        
        // Timer que se ejecuta cada segundo (1000 ms)
        bloqueoTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                segundosRestantes--;
                lblTemporizador.setText("Tiempo restante: " + segundosRestantes + "s");
                
                if (segundosRestantes <= 0) {
                    desbloquearSistema();
                    ((Timer)e.getSource()).stop(); // Detener el timer
                }
            }
        });
        bloqueoTimer.start();
    }

    private void desbloquearSistema() {
        bloqueoTimer.stop();
        intentosFallidos = 0;
        textFieldUsuario.setEnabled(true);
        textFieldPassword.setEnabled(true);
        mensajeError.setVisible(false);
        lblTemporizador.setVisible(false);
    }
    
    public void  resetearCampos(){
    	textFieldUsuario.setText("");
    	textFieldPassword.setText("");
        intentosFallidos = 0;
        mensajeError.setVisible(false);
        mensajeIntentos.setVisible(false);
        lblTemporizador.setVisible(false);
   	
    }
    
    
}