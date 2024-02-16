package com.example.designpattern.simpleFactory.MultipleMethod;


//實例透過 Sender factory 的不同方法來建立，方法是靜態的，直接調用即可
public class SenderFactory {
    public static Sender produceMail() {
        return new MailSender();
    }

    public static Sender produceSms() {
        return new SmsSender();
    }

    public static void main(String[] args) {
        Sender sender = produceSms();
        sender.send();
        sender = produceMail();
        sender.send();

    }
}
