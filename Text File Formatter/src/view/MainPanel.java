package view;

import java.awt.BorderLayout;

import javax.swing.JLabel;
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
        
        final JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BorderLayout(GAP_BETWEEN_COMPONENTS, GAP_BETWEEN_COMPONENTS));
        middlePanel.add(optionPanel, BorderLayout.NORTH);
        
        final JPanel previewPanel = new JPanel();
        middlePanel.add(previewPanel, BorderLayout.CENTER);
        previewPanel.setLayout(new BorderLayout(0, 0));

        final JLabel previewLabel = new JLabel("Preview:");
        previewPanel.add(previewLabel, BorderLayout.NORTH);
        
        add(new FileChooserPanel(observer, textArea), BorderLayout.NORTH);

        final JScrollPane scrollPane = new JScrollPane(textArea);
        previewPanel.add(scrollPane);

        add(middlePanel, BorderLayout.CENTER);
        add(new ButtonPanel(observer), BorderLayout.SOUTH);
    }

}
