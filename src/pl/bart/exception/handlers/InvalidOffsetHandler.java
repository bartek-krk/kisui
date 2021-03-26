package pl.bart.exception.handlers;

import javax.swing.*;
import java.awt.*;

public class InvalidOffsetHandler implements Runnable{
    @Override
    public void run() {
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(null,
                "Offset musi być liczbą dodatnią lub równą zero!",
                "Błąd",
                JOptionPane.WARNING_MESSAGE);
    }
}
