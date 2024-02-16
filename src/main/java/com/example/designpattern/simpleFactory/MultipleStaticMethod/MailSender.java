package com.example.designpattern.simpleFactory.MultipleStaticMethod;


//Impl. class
public class MailSender implements Sender {
    @Override
    public void send() {
        System.out.println("Send Mail!");
    }
}
