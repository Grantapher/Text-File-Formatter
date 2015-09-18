package model;

public class TrimWhitespaceTask implements FormatTask {
    
    private static final int PRIORITY = 0;

    @Override
    public String format(final String line) {
        return line.trim() + '\n';
    }
    
    @Override
    public int getPriority() {
        return PRIORITY;
    }
    
}
