package view;

import java.awt.FlowLayout;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import model.AbstractFormatTask;
import model.AllTasks;
import model.FormatTask;
import model.Observer;
import model.Subject;

public class OptionPanel extends JPanel implements Subject, Observer {
    private static final int COMPONENT_GAP = 5;
    private static final long serialVersionUID = 4714933485302002099L;
    private final List<AbstractFormatTask> checkboxes;
    private final List<Observer> observers;
    
    public OptionPanel(final Observer observer) {
        observers = new LinkedList<>();
        observers.add(observer);
        checkboxes = new LinkedList<>();
        setup();
    }
    
    @Override
    public void addObserver(final Observer observer) {
        observers.add(observer);
    }
    
    private void addTask(final FormatTask task) {
        add((JCheckBox) task);
        checkboxes.add((AbstractFormatTask) task);
    }

    private List<FormatTask> getTasks() {
        return checkboxes.stream()
                .filter(AbstractFormatTask::isSelected)
                .collect(Collectors.toList());
    }

    @Override
    public void notifyObservers(final Object arg) {
        observers.stream().forEach(observer -> observer.update(this, arg));
    }
    
    private void setup() {
        setLayout(new FlowLayout(FlowLayout.LEADING, COMPONENT_GAP, COMPONENT_GAP));
        AllTasks.getTasks().stream().forEach(task -> addTask(task));
    }
    
    @Override
    public void update(final Subject subject, final Object arg) {
        if (subject instanceof ButtonPanel) {
            notifyObservers(getTasks());
        }
    }
}
