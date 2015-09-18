package model;

public class RemoveEmptyLinesTask implements FormatTask {
    
    private static final int PRIORITY = 1;
    
    @Override
    public String format(String line) {
        if (!line.matches("^\\s*$")) {
            line += '\n';
        } else {
            line = "";
        }
        return line;
    }
    
    @Override
    public int getPriority() {
        return PRIORITY;
    }
    
}
