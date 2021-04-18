package pl.bart.exception.handlers;

import pl.bart.exception.InvalidInputException;

import javax.swing.*;
import java.awt.*;

public class InvalidInputHandler implements Runnable {

    InvalidInputException causingException;

    public InvalidInputHandler(InvalidInputException causingException) {
        this.causingException = causingException;
    }

    @Override
    public void run() {
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(null,
                causingException.getReason().message,
                "Błąd",
                JOptionPane.WARNING_MESSAGE);
    }
}
