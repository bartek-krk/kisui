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

    public MainScreen() {
        randomPermutationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Losowe Permutacje");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setContentPane(new RandomPermutationScreen().randomPermutationScreenPanel);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
