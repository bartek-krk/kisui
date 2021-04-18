package pl.bart.gui;

import pl.bart.algorithm.RotN;
import pl.bart.exception.InvalidInputException;
import pl.bart.exception.InvalidOffsetException;
import pl.bart.exception.handlers.InvalidInputHandler;
import pl.bart.exception.handlers.InvalidOffsetHandler;
import pl.bart.gui.listener.ComponentResizeListener;
import pl.bart.validator.OffsetValidator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class RotNScreen {
    public JPanel rotNScreenPanel;
    private JLabel signature;
    private JPanel panel;
    private JTextArea in;
    private JTextArea out;
    private JLabel inLabel;
    private JLabel outLabel;
    private JButton runButton;
    private JLabel keyLabel;
    private JTable key;
    private JScrollPane outScrollPane;
    private JScrollPane inScrollPane;
    private JScrollPane keyScrollPane;
    private JButton returnButton;
    private JLabel header;
    private JTextField offset;
    private JLabel offsetLabel;
    private JPanel headerPanel;

    public RotNScreen(JFrame frame) {

        List<JComponent> bigLettersResizeableComponents = Collections.singletonList(
                this.header
        );

        List<JComponent> smallLettersComponents = Arrays.asList(
                this.signature,
                this.inLabel,
                this.outLabel,
                this.keyLabel,
                this.runButton,
                this.returnButton,
                this.offsetLabel
        );

        rotNScreenPanel.addComponentListener(
                new ComponentResizeListener(
                        this.rotNScreenPanel,
                        bigLettersResizeableComponents,
                        smallLettersComponents)
        );

        runButton.addActionListener(e -> {
            int offsetValue = 0;

            try {
                String in = offset.getText();
                if (OffsetValidator.validate(in)) offsetValue = Integer.parseInt(in);
            } catch (InvalidOffsetException invalidOffsetException){
                new InvalidOffsetHandler().run();
            }

            try (RotN encoder = new RotN(offsetValue)){
                out.setText(encoder.cipher(in.getText()));
                Character[][] data = new Character[encoder.getCharacterMap().size()][2];
                int i = 0;
                for(Map.Entry<Character,Character> entry : encoder.getCharacterMap().entrySet()) {
                    data[i][0] = entry.getKey();
                    data[i][1] = entry.getValue();
                    i++;
                }

                DefaultTableModel model = (DefaultTableModel) key.getModel();
                model.setDataVector(data, new String[]{"Przed zakodowaniem", "Po zakodowaniu"});
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
