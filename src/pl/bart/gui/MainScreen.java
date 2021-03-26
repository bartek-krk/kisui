package pl.bart.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen {
    public JPanel mainScreenPanel;
    private JLabel header;
    private JPanel choicePanel;
    private JButton rotNButton;
    private JButton randomPermutationButton;
    private JButton button3;
    private JButton button4;
    private JLabel signature;

    public MainScreen(JFrame frame) {

        randomPermutationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new RandomPermutationScreen(frame).randomPermutationScreenPanel);
                frame.revalidate();
            }
        });
        rotNButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new RotNScreen(frame).rotNScreenPanel);
                frame.revalidate();
            }
        });
    }
}
