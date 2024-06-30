package com.example.designpattern.observerPattern;

public class MessageAppTest {
    public static void main(String[] args) {
        Observer pcObserver = new PCMessageDisplay();
        Observer phoneObserver = new PhoneMessageDisplay();
        MessageStation subject = new MessageStation();
        subject.setMessage("test no observer");
        subject.addObserver(pcObserver);
        subject.addObserver(phoneObserver);
        subject.setMessage("after add observer, get message!");

    }

}
