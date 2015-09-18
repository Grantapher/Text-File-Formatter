package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

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

        final OptionPanel optionPanel = new OptionPanel(observer);

        add(new FileChooserPanel(observer), BorderLayout.NORTH);
        add(optionPanel, BorderLayout.CENTER);
        add(new ButtonPanel(optionPanel), BorderLayout.SOUTH);
    }
    
}
