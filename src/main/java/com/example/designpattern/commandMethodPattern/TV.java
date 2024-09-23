package com.example.designpattern.commandMethodPattern;

public class TV implements Device{
    @Override
    public void turnOn() {
        System.out.println("turn on TV....");
    }

    @Override
    public void turnOff() {
        System.out.println("turn off TV....");
    }


    public void changeChannel(){
        System.out.println("Channel changed!....");
    }
}
