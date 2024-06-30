package com.example.designpattern.observerPattern;


import java.util.ArrayList;
import java.util.List;

public class MessageStation implements Subject{

    private List<Observer> observers = new ArrayList<>();
    private String message = "";

    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        observers.forEach(observer -> observer.update(this.message));
    }

    public void setMessage(String message){
        this.message = message;
        notifyObserver();
    }
}
