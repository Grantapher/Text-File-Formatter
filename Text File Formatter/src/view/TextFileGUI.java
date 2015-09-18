package view;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class TextFileGUI extends JFrame {
    private static final long serialVersionUID = -5957038959556058788L;
    private static final String TITLE = "Grant's Text File Formatter";
    private static final Toolkit TOOLKIT = Toolkit.getDefaultToolkit();

    public TextFileGUI() {
        setTitle(TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(getCenteredRectangleWithScale(.5f, TOOLKIT.getScreenSize()));
        setVisible(true);
    }

    private Rectangle getCenteredRectangleWithScale(final float scale, final Dimension dimension) {
        final Rectangle centeredRectangle = new Rectangle();
        final int width = (int) dimension.getWidth();
        final int height = (int) dimension.getHeight();
        final Point center = new Point(width / 2, height / 2);
        final int cornerX = (int) (center.getX() + width / 2 * scale);
        final int cornerY = (int) (center.getY() + height / 2 * scale);
        final Point corner = new Point(cornerX, cornerY);
        centeredRectangle.setFrameFromCenter(center, corner);
        return centeredRectangle;
    }

}
