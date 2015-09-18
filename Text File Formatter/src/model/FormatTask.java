package model;

public interface FormatTask {

    /**
     * Formats the line according to the implementing class.
     *
     * <br>
     * <b>The line must end with a newline if it is preserved. Returning a blank
     * line will remove the line.</b>
     *
     * @param line
     *            the line to format
     * @return the formatted line
     *
     */
    public String format(String line);

    /**
     * Gets the priority of the task. A smaller integer means that task will be
     * executed before another with a higher integer.
     * 
     * @return the tasks priority
     */
    public int getPriority();
}
