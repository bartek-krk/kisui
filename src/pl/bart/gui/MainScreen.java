package pl.bart.gui;

import pl.bart.gui.listener.ComponentResizeListener;

import javax.swing.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainScreen {
    public JPanel mainScreenPanel;
    private JLabel header;
    private JPanel choicePanel;
    private JButton rotNButton;
    private JButton randomPermutationButton;
    private JButton polybiusSquareButton;
    private JButton playfairButton;
    private JLabel signature;

    public MainScreen(JFrame frame) {

        List<JComponent> bigLettersResizeableComponents = Arrays.asList(
                this.header,
                this.randomPermutationButton,
                this.rotNButton,
                this.polybiusSquareButton,
                this.playfairButton
        );

        List<JComponent> smallLettersComponents = Collections.singletonList(
                this.signature
        );

        mainScreenPanel.addComponentListener(
                new ComponentResizeListener(
                        this.mainScreenPanel,
                        bigLettersResizeableComponents,
                        smallLettersComponents)
        );

        randomPermutationButton.addActionListener(e -> {
            frame.setContentPane(new RandomPermutationScreen(frame).randomPermutationScreenPanel);
            frame.revalidate();
        });
        rotNButton.addActionListener(e -> {
            frame.setContentPane(new RotNScreen(frame).rotNScreenPanel);
            frame.revalidate();
        });
        polybiusSquareButton.addActionListener(e -> {
            frame.setContentPane(new PolybiusSquareScreen(frame).polybiusSquareScreenPanel);
            frame.revalidate();
        });
        playfairButton.addActionListener(e -> {
            frame.setContentPane(new PlayfairScreen(frame).playfairScreenPanel);
            frame.revalidate();
        });
    }
}
