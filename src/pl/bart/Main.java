package pl.bart;

import pl.bart.gui.MainScreen;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Kryptografia i Systemy Utajniania Informacji");
        frame.setContentPane(new MainScreen().mainScreenPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}
