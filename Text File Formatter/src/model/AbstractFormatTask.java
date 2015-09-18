package model;

import javax.swing.JCheckBox;

public abstract class AbstractFormatTask extends JCheckBox implements FormatTask {
    private static final long serialVersionUID = -7504140047027997457L;
    protected final String labelText;
    protected final int priority;

    protected AbstractFormatTask(final String labelText, final int priority) {
        this.labelText = labelText;
        this.priority = priority;
        setText(labelText);
    }

    @Override
    public abstract String format(String line);

    @Override
    public int getPriority() {
        return priority;
    }

}
