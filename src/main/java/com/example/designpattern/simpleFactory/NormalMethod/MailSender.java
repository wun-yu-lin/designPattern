package com.example.designpattern.simpleFactory.NormalMethod;


//Impl. class
public class MailSender implements Sender{
    @Override
    public void send() {
        System.out.println("Send Mail!");
    }
}
