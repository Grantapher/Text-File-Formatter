package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import view.FileChooserPanel;
import view.OptionPanel;

public class Formatter implements Observer {
    private static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    private static final String TEMP_FILENAME = "tmp";
    private File file;

    private void format(final JPanel subject, final List<FormatTask> tasks) {
        if (tasks.isEmpty()) {
            return;
        }

        tasks.sort((t1, t2) -> Integer.compare(t1.getPriority(), t2.getPriority()));
        final File tempFile = new File(CURRENT_DIRECTORY, TEMP_FILENAME);
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            try (PrintWriter writer = new PrintWriter(tempFile)) {
                String line;
                while (null != (line = reader.readLine())) {
                    for (final FormatTask task : tasks) {
                        line = trimNewLines(line);
                        line = task.format(line);
                    }
                    if (!line.isEmpty()) {
                        writer.print(line);
                    }
                }
            }
        } catch (final FileNotFoundException e) {
            JOptionPane.showMessageDialog(subject, "WTF: File not Found", "What a Terrible Failure",
                    JOptionPane.ERROR_MESSAGE);
        } catch (final IOException e) {
            JOptionPane.showMessageDialog(subject, "File Read Failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        file.delete();
        tempFile.renameTo(file);
    }

    private String trimNewLines(final String line) {
        return line.replaceAll("\n", "");
    }

    @Override
    public void update(final Subject subject, final Object arg) {
        if (subject instanceof FileChooserPanel) {
            file = (File) arg;
        } else if (subject instanceof OptionPanel && file != null && file.exists() && file.isFile()) {
            @SuppressWarnings("unchecked")
            final List<FormatTask> tasks = (List<FormatTask>) arg;
            format((JPanel) subject, tasks);
        }
    }

}
