package com.example.designpattern.simpleFactory.MultipleStaticMethod;


//實例透過 Sender factory 的不同方法來建立
public class SenderFactory {
    public Sender produceMail() {
        return new MailSender();
    }

    public Sender produceSms() {
        return new SmsSender();
    }

    public static void main(String[] args) {
        SenderFactory senderFactory = new SenderFactory();
        Sender sender = senderFactory.produceMail();
        sender.send();

        sender = senderFactory.produceSms();
        sender.send();

    }
}
