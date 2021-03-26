package pl.bart.gui;

import pl.bart.algorithm.RotN;
import pl.bart.exception.InvalidInputException;
import pl.bart.exception.InvalidOffsetException;
import pl.bart.exception.handlers.InvalidInputHandler;
import pl.bart.exception.handlers.InvalidOffsetHandler;
import pl.bart.validator.OffsetValidator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public RotNScreen(JFrame frame) {
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int offsetValue = 0;

                try {
                    String in = offset.getText();
                    if (OffsetValidator.validate(in)) offsetValue = Integer.parseInt(in);
                } catch (InvalidOffsetException invalidOffsetException){
                    invalidOffsetException.printStackTrace();
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
                    invalidInputException.printStackTrace();
                    new InvalidInputHandler().run();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new MainScreen(frame).mainScreenPanel);
                frame.revalidate();
            }
        });
    }
}
