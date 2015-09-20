package view;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;

import model.Observer;

public class TextFileGUI extends JFrame {
    private static final long serialVersionUID = -5957038959556058788L;
    private static final String TITLE = "Grant's Text File Formatter";
    private static final Toolkit TOOLKIT = Toolkit.getDefaultToolkit();
    private static final Dimension MINIMUM_SIZE = new Dimension(400, 400);

    public TextFileGUI(final Observer observer) {
        setup(observer);
    }

    private Rectangle centerRectangle(final Rectangle guiBounds, final Dimension screenSize) {
        final Rectangle newGuiBounds = new Rectangle(guiBounds);
        final int x = (int) ((screenSize.getWidth() - guiBounds.width) / 2);
        final int y = (int) ((screenSize.getHeight() - guiBounds.height) / 2);
        newGuiBounds.setLocation(x, y);
        return newGuiBounds;
    }

    private void setup(final Observer observer) {
        setTitle(TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(new MainPanel(observer));
        setMinimumSize(MINIMUM_SIZE);
        pack();
        setBounds(centerRectangle(getBounds(), TOOLKIT.getScreenSize()));
        setVisible(true);
    }

}
