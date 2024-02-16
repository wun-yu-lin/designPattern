package com.example.designpattern.simpleFactory.NormalMethod;

import jdk.jshell.spi.ExecutionControl;

//建立 SenderFactory, 作為 instance 創建的接口
public class SenderFactory {
    public Sender produce(String type) throws ExecutionControl.NotImplementedException {
        if (type.equals("mail")){
            return new MailSender();
        } else if (type.equals("sms")) {
            return new SmsSender();
        }else{
            throw new ExecutionControl.NotImplementedException("此類別無法被 Sender Factory 所創建！");
        }
    }

    public static void main(String[] args) {
        SenderFactory senderFactory = new SenderFactory();
        try {
            Sender sender =  senderFactory.produce("mail");
            sender.send();

            sender =  senderFactory.produce("sms");
            sender.send();

            sender =  senderFactory.produce("phone");
            sender.send();

        } catch (ExecutionControl.NotImplementedException e) {
            System.out.println(e.getMessage());
        }

    }
}
