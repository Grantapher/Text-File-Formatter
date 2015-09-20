package model;

import java.util.Comparator;

import javax.swing.JCheckBox;

public abstract class AbstractFormatTask extends JCheckBox implements FormatTask {
    
    private static final long serialVersionUID = -7504140047027997457L;

    public static Comparator<? super AbstractFormatTask> sortByPriority() {
        return (t1, t2) -> Integer.compare(t1.priority, t2.priority);
    }

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
