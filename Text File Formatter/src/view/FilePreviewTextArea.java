package view;

import java.io.File;

import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import model.AllTasks;
import model.Formatter;
import model.Observer;
import model.Subject;

public class FilePreviewTextArea extends JTextArea implements Observer {
    private static final long serialVersionUID = 7373491621280502735L;
    private static final int NUMBER_LINES_PREVIEW = 100;
    private File file;

    public FilePreviewTextArea() {
        setup();
    }
    
    private void setup() {
        final DefaultCaret caret = (DefaultCaret) getCaret();
        caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
        setEditable(false);
    }

    @Override
    public void update(final Subject subject, final Object arg) {
        if (subject instanceof FileChooserPanel) {
            file = (File) arg;
        }
        if (file != null) {
            final String previewText = Formatter.format(file, AllTasks.getSelectedTasks(), NUMBER_LINES_PREVIEW);
            setText(previewText);
        }
    }

}
