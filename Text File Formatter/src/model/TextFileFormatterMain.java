package model;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import view.TextFileGUI;

/**
 * Driver class for the program.
 *
 * @author Grant
 */
public class TextFileFormatterMain {
    
    public static void main(final String[] args) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        final Observer observer = new Formatter();
        new TextFileGUI(observer);
    }
    
}
