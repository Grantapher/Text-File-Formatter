package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import model.Observer;

public class MainPanel extends JPanel {
    private static final int GAP_BETWEEN_COMPONENTS = 5;
    private static final long serialVersionUID = -8668618688820184387L;

    /**
     * Create the panel.
     *
     * @param observer
     */
    public MainPanel(final Observer observer) {
        setup(observer);
    }

    private void setup(final Observer observer) {
        setLayout(new BorderLayout(GAP_BETWEEN_COMPONENTS, GAP_BETWEEN_COMPONENTS));
        setBorder(new EmptyBorder(GAP_BETWEEN_COMPONENTS, GAP_BETWEEN_COMPONENTS, GAP_BETWEEN_COMPONENTS,
                GAP_BETWEEN_COMPONENTS));

        final FilePreviewTextArea textArea = new FilePreviewTextArea();
        final OptionPanel optionPanel = new OptionPanel(observer, textArea);

        add(new FileChooserPanel(observer, textArea), BorderLayout.NORTH);

        final JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BorderLayout(GAP_BETWEEN_COMPONENTS, GAP_BETWEEN_COMPONENTS));
        middlePanel.add(optionPanel, BorderLayout.NORTH);

        final JScrollPane scrollPane = new JScrollPane(textArea);
        middlePanel.add(scrollPane, BorderLayout.CENTER);

        add(middlePanel, BorderLayout.CENTER);
        add(new ButtonPanel(observer), BorderLayout.SOUTH);
    }

}
