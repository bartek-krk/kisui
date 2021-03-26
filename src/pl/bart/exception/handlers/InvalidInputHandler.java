package pl.bart.exception.handlers;

import javax.swing.*;
import java.awt.*;

public class InvalidInputHandler implements Runnable{
    @Override
    public void run() {
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(null,
                "Dozwolone są tylko znaki alfabetu angielskiego i liczby!",
                "Błąd",
                JOptionPane.WARNING_MESSAGE);
    }
}
