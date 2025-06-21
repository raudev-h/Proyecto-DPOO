package auxiliares;

// CLASE PARA VALIDAR LOS CAMPOS DEL FORMULARIO DE SERVICIOS

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class NumericDocumentFilter extends DocumentFilter {
    private int maxLength;

    public NumericDocumentFilter(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) 
            throws BadLocationException {
        if (string == null) {
            return;
        }
        if (isNumeric(string) && (fb.getDocument().getLength() + string.length()) <= maxLength) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) 
            throws BadLocationException {
        if (text == null) {
            return;
        }
        int newLength = fb.getDocument().getLength() - length + text.length();
        if (isNumeric(text) && newLength <= maxLength) {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    private boolean isNumeric(String text) {
        return text.matches("\\d*");
    }
}
