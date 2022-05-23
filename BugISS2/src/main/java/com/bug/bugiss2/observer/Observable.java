package com.bug.bugiss2.observer;



public interface Observable {
    void addObserver(Observer e);
    void removeObserver(Observer e);
    void notifyObservers();
}
