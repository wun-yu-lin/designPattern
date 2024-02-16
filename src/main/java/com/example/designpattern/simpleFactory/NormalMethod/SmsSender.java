package com.example.designpattern.simpleFactory.NormalMethod;


//Impl. class
public class SmsSender implements Sender{
    @Override
    public void send() {
        System.out.println("send sms");
    }
}
