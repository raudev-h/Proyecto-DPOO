package interfaz;

import logica.*;
import auxiliares.MayorGastoKbTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class MesesMayorConsumoMBnauta extends JFrame {

    private JPanel contentPane;
    private MayorGastoKbTableModel modeloMeses;
    private JTable tablaMeses;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MesesMayorConsumoMBnauta frame = new MesesMayorConsumoMBnauta();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MesesMayorConsumoMBnauta() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 350);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // Crear el modelo
        modeloMeses = new MayorGastoKbTableModel();

        // Crear la tabla con el modelo
        tablaMeses = new JTable(modeloMeses);

        // Agregar la tabla con scroll al panel
        JScrollPane scrollPane = new JScrollPane(tablaMeses);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Botón para cargar datos
        JButton btnCargarDatos = new JButton("Cargar Datos");
        btnCargarDatos.setFont(new Font("Serif", Font.BOLD, 16));
        contentPane.add(btnCargarDatos, BorderLayout.SOUTH);

        // Listener para el botón
        btnCargarDatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarDatos();
            }
        });
    }

    private void cargarDatos() {
        // Obtener los consumos desde EmpresaTelecomunicaciones
        // Aquí asumo que devuelve un ArrayList<String> (ajusta según tu implementación)
        HashMap<String, Double> consumosPorMes = CuentaNauta.calcularKbGastadosMeses();

        // Cargar los datos en el modelo
        modeloMeses.cargarConsumos(consumosPorMes);
    }
}
    