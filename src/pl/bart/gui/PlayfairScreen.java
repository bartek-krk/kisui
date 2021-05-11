package pl.bart.gui;

import pl.bart.algorithm.Encoder;
import pl.bart.algorithm.Playfair;
import pl.bart.algorithm.PolybiusSquare;
import pl.bart.exception.InvalidInputException;
import pl.bart.exception.handlers.InvalidInputHandler;
import pl.bart.gui.listener.ComponentResizeListener;
import pl.bart.pojo.Coordinates;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PlayfairScreen {
    public JPanel playfairScreenPanel;
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

    public PlayfairScreen(JFrame frame) {

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

        playfairScreenPanel.addComponentListener(
                new ComponentResizeListener(
                        this.playfairScreenPanel,
                        bigLettersResizeableComponents,
                        smallLettersComponents)
        );

        runButton.addActionListener(e -> {
            try(Encoder<Map<Character,Coordinates>> encoder = new Playfair()) {
                out.setText(encoder.cipher(in.getText()));

                Character[][] data = new Character[9][9];

                data[0][0] = ' ';
                for (int i=1; i<=8; i++) {
                    data[0][i] = Character.forDigit(i-1,10);
                    data[i][0] = Character.forDigit(i-1,10);
                }


                encoder.getCharacterMap().entrySet().forEach(entry -> {
                    int x = entry.getValue().getX()+1;
                    int y = entry.getValue().getY()+1;

                    data[x][y] = entry.getKey();
                });


                table.getTableHeader().setReorderingAllowed(false);
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setDataVector(data, new String[]{"","","","","","","","",""});

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
