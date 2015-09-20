package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.swing.JOptionPane;

import view.ButtonPanel;
import view.FileChooserPanel;

public class Formatter implements Observer {
    
    /**
     * Formats the file
     *
     * @param file the text file to format
     * @return a string containing the formatted text
     */
    public static String format(final File file, final List<AbstractFormatTask> tasks) {
        return format(file, tasks, -1);
    }
    
    /**
     * Formats the file for the given number of lines.
     *
     * @param file the text file to format
     * @param numLines the number of lines to format
     * @return a string containing the formatted text
     */
    public static String format(final File file, final List<AbstractFormatTask> tasks, final int numLines) {
        if (file == null || !file.exists() || file.isDirectory()) {
            return "";
        }
        
        final StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            for (int i = 0; null != (line = reader.readLine()) && (numLines == -1 || i < numLines); i++) {
                line += '\n';
                for (final FormatTask task : tasks) {
                    line = trimNewLines(line);
                    line = task.format(line);
                }
                if (!line.isEmpty()) {
                    sb.append(line);
                }
            }
        } catch (final FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "WTF: File not Found", "What a Terrible Failure",
                    JOptionPane.ERROR_MESSAGE);
        } catch (final IOException e) {
            JOptionPane.showMessageDialog(null, "File Read Failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return sb.toString();
    }
    
    private static String trimNewLines(final String line) {
        return line.replaceAll("\n", "");
    }
    
    private File file;
    
    @Override
    public void update(final Subject subject, final Object arg) {
        if (subject instanceof FileChooserPanel) {
            file = (File) arg;
        } else if (subject instanceof ButtonPanel) {
            final List<AbstractFormatTask> tasks = AllTasks.getSelectedTasks();
            if (tasks.isEmpty()) {
                return;
            }
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.print(format(file, tasks));
            } catch (final FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "WTF: File not Found", "What a Terrible Failure",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
