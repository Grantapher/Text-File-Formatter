package model;

public class RemoveEmptyLinesTask extends AbstractFormatTask {
    private static final long serialVersionUID = 5797960890090182903L;
    private static String LABEL_TEXT = "Remove Empty Lines";
    private static final int PRIORITY = 1;

    public RemoveEmptyLinesTask() {
        super(LABEL_TEXT, PRIORITY);
    }
    
    @Override
    public String format(String line) {
        if (!line.matches("^\\s*$")) {
            line += '\n';
        } else {
            line = "";
        }
        return line;
    }
}
