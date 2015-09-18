package model;

public class TrimWhitespaceTask extends AbstractFormatTask {
    private static final long serialVersionUID = -7400356570363380815L;
    private static final String LABEL_TEXT = "Trim Whitespace Around Lines";
    private static final int PRIORITY = 0;
    
    public TrimWhitespaceTask() {
        super(LABEL_TEXT, PRIORITY);
    }
    
    @Override
    public String format(final String line) {
        return line.trim() + '\n';
    }

}
