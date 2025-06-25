package runner;

import excepciones.CarnetIdentidadInvalidoException;
import excepciones.DuplicadosException;
import excepciones.NombreInvalidoException;
import excepciones.ProvinciaInvalidaException;
import excepciones.UbicacionInvalidaException;
import interfaz.login;
import interfaz.Principal;

public class Runner {
    public static void main(String[] args) throws NombreInvalidoException, UbicacionInvalidaException, ProvinciaInvalidaException, CarnetIdentidadInvalidoException, DuplicadosException {
        Inicializadora.Inicializar();

        // Crear y mostrar el login EN el hilo EDT
        final login[] loginWindow = new login[1];
        try {
            javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    loginWindow[0] = login.getInstance();
                    loginWindow[0].setVisible(true);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Esperar a que el login se cierre (esto ya es en el hilo main, fuera del EDT)
        while (loginWindow[0].isVisible()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Si autenticó, abrir principal
        if (loginWindow[0].isAutenticado()) {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    Principal principal = new Principal();
                    principal.setVisible(true);
                }
            });
        }
        // Si no autenticó, el programa termina solo
    }
}