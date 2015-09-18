package view;

import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import model.FormatTask;
import model.Observer;
import model.RemoveEmptyLinesTask;
import model.Subject;
import model.TrimWhitespaceTask;

public class OptionPanel extends JPanel implements Subject, Observer {
    private static final String TRIM_WHITESPACE_TEXT = "Trim Whitespace Around Lines";
    private static final String EMPTY_LINE_TEXT = "Remove Empty Lines";
    private static final int COMPONENT_GAP = 5;
    private static final long serialVersionUID = 4714933485302002099L;
    private final Map<JCheckBox, FormatTask> taskMap;
    private final List<Observer> observers;
    
    public OptionPanel(final Observer observer) {
        observers = new LinkedList<>();
        observers.add(observer);
        taskMap = new HashMap<>();
        setup();
    }
    
    @Override
    public void addObserver(final Observer observer) {
        observers.add(observer);
    }
    
    private List<FormatTask> getTasks() {
        return taskMap.entrySet().stream()
                .filter(entry -> entry.getKey().isSelected())
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    @Override
    public void notifyObservers(final Object arg) {
        observers.stream().forEach(observer -> observer.update(this, arg));
    }

    private void setup() {
        setLayout(new FlowLayout(FlowLayout.LEADING, COMPONENT_GAP, COMPONENT_GAP));
        
        final JCheckBox emptyLineBox = new JCheckBox(EMPTY_LINE_TEXT);
        add(emptyLineBox);
        taskMap.put(emptyLineBox, new RemoveEmptyLinesTask());
        
        final JCheckBox trimBox = new JCheckBox(TRIM_WHITESPACE_TEXT);
        add(trimBox);
        taskMap.put(trimBox, new TrimWhitespaceTask());
    }

    @Override
    public void update(final Subject subject, final Object arg) {
        if (subject instanceof ButtonPanel) {
            notifyObservers(getTasks());
        }
    }
    
}
