package com.example.designpattern.commandMethodPattern;


// Invoker
public class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressesButton(){
        command.execute();
    }
}
