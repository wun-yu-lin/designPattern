package com.example.designpattern.observerPattern;

public class PhoneMessageDisplay implements Observer {
    private String message = null;
    @Override
    public void update(String message) {
        this.message = message;
        display();
    }

    @Override
    public void display() {
        System.out.println("Phone display updated: " + this.message);
    }
}
