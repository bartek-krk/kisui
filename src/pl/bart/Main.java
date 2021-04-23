package pl.bart;

import pl.bart.algorithm.PolybiusSquare;
import pl.bart.exception.InvalidInputException;
import pl.bart.gui.MainScreen;
import pl.bart.pojo.Coordinates;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        JFrame frame = new JFrame("Kryptografia i Systemy Utajniania Informacji");
        frame.setContentPane(new MainScreen(frame).mainScreenPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(screenSize.width/4*3,screenSize.height/4*3);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
