package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Observer;
import model.Subject;

public class FileChooserPanel extends JPanel implements Subject {
    private static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    public static final String TEMP_FILEPATH = CURRENT_DIRECTORY + "preview.tmp";
    private static final String BUTTON_TEXT = "Browse";
    private static final String LABEL_TEXT = "File To Edit:";
    private static final long serialVersionUID = 509910247723427709L;
    private final JFileChooser fileChooser;
    private final List<Observer> observers;
    private File selectedFile;

    /**
     * Create the panel.
     *
     * @param observer
     */
    public FileChooserPanel(final Observer... observers) {
        this.observers = new LinkedList<>();
        Arrays.stream(observers).forEach(this.observers::add);

        selectedFile = new File(CURRENT_DIRECTORY);
        fileChooser = new JFileChooser(selectedFile);
        setup();
    }

    @Override
    public void addObserver(final Observer observer) {
        observers.add(observer);
    }
    
    @Override
    public void notifyObservers(final Object arg) {
        // observers.stream().forEach(observer -> observer.update(this, arg));
        for (final Observer observer : observers) {
            observer.update(this, arg);
        }
    }

    private void setup() {
        final GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0 };
        gridBagLayout.rowWeights = new double[] { 0.0 };
        setLayout(gridBagLayout);

        final JLabel label = new JLabel(LABEL_TEXT);
        final GridBagConstraints gbc_label = new GridBagConstraints();
        gbc_label.anchor = GridBagConstraints.WEST;
        gbc_label.gridx = 0;
        gbc_label.gridy = 0;
        add(label, gbc_label);

        final JTextField textField = new JTextField();
        textField.setEditable(false);
        final GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.anchor = GridBagConstraints.WEST;
        gbc_textField.insets = new Insets(0, 5, 0, 5);
        gbc_textField.gridx = 1;
        gbc_textField.gridy = 0;
        add(textField, gbc_textField);

        final JButton button = new JButton(BUTTON_TEXT);
        final GridBagConstraints gbc_button = new GridBagConstraints();
        gbc_button.anchor = GridBagConstraints.NORTHWEST;
        gbc_button.gridx = 2;
        gbc_button.gridy = 0;
        add(button, gbc_button);
        button.addActionListener(e -> {
            if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(this)) {
                selectedFile = fileChooser.getSelectedFile();
                textField.setText(selectedFile.getAbsolutePath());
                notifyObservers(selectedFile);
            }
        });
    }
}
