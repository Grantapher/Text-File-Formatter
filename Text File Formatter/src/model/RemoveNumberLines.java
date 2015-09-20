package model;

public class RemoveNumberLines extends AbstractFormatTask {
    private static final long serialVersionUID = -5335667947229660931L;
    private static String LABEL_TEXT = "Remove Page Number Lines";
    private static final int PRIORITY = 0;

    public RemoveNumberLines() {
        super(LABEL_TEXT, PRIORITY);
    }

    @Override
    public String format(String line) {
        if (line.matches("^\\W*\\d+\\W*$")) {
            line = "";
        } else {
            line += '\n';
        }
        return line;
    }
}
