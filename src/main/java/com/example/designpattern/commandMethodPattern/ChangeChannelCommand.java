package com.example.designpattern.commandMethodPattern;

public class ChangeChannelCommand implements Command {

    private final TV tv;

    public ChangeChannelCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.changeChannel();
    }
}
