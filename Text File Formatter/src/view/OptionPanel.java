package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JPanel;

import model.AbstractFormatTask;
import model.AllTasks;
import model.Observer;
import model.Subject;

public class OptionPanel extends JPanel implements Subject {
    private class Action extends AbstractAction {
        private static final long serialVersionUID = 1993163230217634700L;

        @Override
        public void actionPerformed(final ActionEvent e) {
            notifyObservers(null);
        }

    }
    
    private static final long serialVersionUID = 4714933485302002099L;
    private final List<AbstractFormatTask> checkboxes;
    private final List<Observer> observers;

    public OptionPanel(final Observer... observers) {
        this.observers = new LinkedList<>();
        Arrays.stream(observers).forEach(this.observers::add);
        
        checkboxes = new LinkedList<>();
        setup();
    }

    @Override
    public void addObserver(final Observer observer) {
        observers.add(observer);
    }

    private void addTask(final AbstractFormatTask task) {
        final Action action = new Action();
        action.putValue(Action.NAME, task.getText());
        task.setAction(action);
        add(task);
        checkboxes.add(task);
    }

    @Override
    public void notifyObservers(final Object arg) {
        observers.stream().forEach(observer -> observer.update(this, arg));
    }

    private void setup() {
        setLayout(new WrapLayout(FlowLayout.LEADING, 0, 0));
        AllTasks.getTasks().stream().forEach(task -> addTask(task));
    }
    
}
