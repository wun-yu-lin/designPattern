package com.example.designpattern.observerPattern;

public class PCMessageDisplay implements Observer {
    private String message = null;
    @Override
    public void update(String message) {
        this.message = message;
        display();
    }

    @Override
    public void display() {
        System.out.println("PC display updated: " + this.message);
    }
}
