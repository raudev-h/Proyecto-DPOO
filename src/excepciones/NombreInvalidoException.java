package excepciones;

public class NombreInvalidoException extends Exception {
    public NombreInvalidoException(String mensaje){
        super(mensaje);
    }
}
