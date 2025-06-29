package auxiliares;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Un botón deslizante personalizado estilo "iPhone switch" para Swing.
 * Es un JToggleButton que dibuja un fondo y un círculo móvil según el estado.
 */
public class ToggleSwitch extends JToggleButton {

    public ToggleSwitch() {
        setModel(new DefaultButtonModel());
        setPreferredSize(new Dimension(60, 30));
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);

        // Cambia el estado al hacer clic
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setSelected(!isSelected());
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        // Antialiasing para suavidad
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Color de fondo según estado
        Color bg = isSelected() ? new Color(40, 150, 255) : new Color(180, 180, 180);
        Color knob = Color.WHITE;

        // Dibuja el fondo redondeado
        g2.setColor(bg);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());

        // Dibuja el círculo móvil
        int margin = 4;
        int diameter = getHeight() - 2 * margin;
        int x = isSelected() ? getWidth() - diameter - margin : margin;

        g2.setColor(knob);
        g2.fillOval(x, margin, diameter, diameter);

        g2.dispose();
    }
}