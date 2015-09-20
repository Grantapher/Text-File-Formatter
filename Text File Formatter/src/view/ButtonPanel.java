package view;

import java.awt.FlowLayout;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.Observer;
import model.Subject;

public class ButtonPanel extends JPanel implements Subject {
    private static final String FORMAT_TEXT = "Format!";
    private static final long serialVersionUID = -8688748618155465054L;
    private final List<Observer> observers;
    
    public ButtonPanel(final Observer... observers) {
        this.observers = new LinkedList<>();
        Arrays.stream(observers).forEach(this.observers::add);
        setup();
    }

    @Override
    public void addObserver(final Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(final Object arg) {
        observers.stream().forEach(observer -> observer.update(this, arg));
    }

    private void setup() {
        setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 0));

        final JButton formatButton = new JButton(FORMAT_TEXT);
        formatButton.addActionListener(e -> {
            notifyObservers(null);
        });
        add(formatButton);
    }
    
}
