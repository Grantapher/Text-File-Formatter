package model;

public interface Subject {
    public void addObserver(Observer observer);
    
    public void notifyObservers(Object arg);
}
