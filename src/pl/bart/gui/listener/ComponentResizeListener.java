package pl.bart.gui.listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.List;

public class ComponentResizeListener implements ComponentListener {

    public static final int BIG_LETTERS_RESIZE_FACTOR = 25;
    public static final int SMALL_LETTERS_RESIZE_FACTOR = 65;
    public static final int MIN_FONT_SIZE = 12;

    private JPanel panel;
    private List<JComponent> bigLettersComponents;
    private List<JComponent> smallLettersComponents;

    public ComponentResizeListener(JPanel panel, List<JComponent> bigLettersComponents, List<JComponent> smallLettersComponents) {
        this.panel = panel;
        this.bigLettersComponents = bigLettersComponents;
        this.smallLettersComponents = smallLettersComponents;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        int width = this.panel.getWidth();
        Font font = bigLettersComponents.get(0).getFont();
        int bigSize = width/BIG_LETTERS_RESIZE_FACTOR > MIN_FONT_SIZE ? width/BIG_LETTERS_RESIZE_FACTOR : MIN_FONT_SIZE;
        Font bigFont = new Font(font.getName(),font.getStyle(),bigSize);
        int smallSize = width/SMALL_LETTERS_RESIZE_FACTOR > MIN_FONT_SIZE ? width/SMALL_LETTERS_RESIZE_FACTOR : MIN_FONT_SIZE;
        Font smallFont = new Font(font.getName(), font.getStyle(),smallSize);
        bigLettersComponents.forEach(component -> component.setFont(bigFont));
        smallLettersComponents.forEach(component -> component.setFont(smallFont));
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        //pass
    }

    @Override
    public void componentShown(ComponentEvent e) {
        //pass
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        //pass
    }
}
