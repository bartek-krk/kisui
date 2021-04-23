package pl.bart.gui;

import pl.bart.algorithm.PolybiusSquare;
import pl.bart.exception.InvalidInputException;
import pl.bart.exception.handlers.InvalidInputHandler;
import pl.bart.gui.listener.ComponentResizeListener;
import pl.bart.pojo.Coordinates;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PolybiusSquareScreen {
    public JPanel polybiusSquareScreenPanel;
    private JPanel headerPanel;
    private JPanel panel;
    private JLabel signature;
    private JButton returnButton;
    private JLabel header;
    private JPanel translationPanel;
    private JPanel tablePanel;
    private JLabel inLabel;
    private JTextArea in;
    private JScrollPane inScrollPane;
    private JTextArea out;
    private JScrollPane outScrollPane;
    private JLabel outLabel;
    private JButton runButton;
    private JTable table;
    private JLabel tableLabel;
    private JScrollPane tableScrollPane;

    public PolybiusSquareScreen(JFrame frame) {

        List<JComponent> bigLettersResizeableComponents = Collections.singletonList(
                this.header
        );

        List<JComponent> smallLettersComponents = Arrays.asList(
                this.inLabel,
                this.outLabel,
                this.tableLabel,
                this.runButton,
                this.signature,
                this.returnButton
        );

        polybiusSquareScreenPanel.addComponentListener(
                new ComponentResizeListener(
                        this.polybiusSquareScreenPanel,
                        bigLettersResizeableComponents,
                        smallLettersComponents)
        );

        runButton.addActionListener(e -> {
            try(PolybiusSquare encoder = new PolybiusSquare()) {
                out.setText(encoder.cipher(in.getText()));

                //TODO: filling this.table with encoder.getAlphabet()

                encoder.getAlphabet().entrySet().forEach(entry -> {
                    Coordinates coordinates = entry.getValue();
                    System.out.printf("%s => %d%d\n", entry.getKey(), coordinates.getX(), coordinates.getY());
                });

            } catch (InvalidInputException invalidInputException) {
                new InvalidInputHandler(invalidInputException).run();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        returnButton.addActionListener(e -> {
            frame.setContentPane(new MainScreen(frame).mainScreenPanel);
            frame.revalidate();
        });

    }
}
