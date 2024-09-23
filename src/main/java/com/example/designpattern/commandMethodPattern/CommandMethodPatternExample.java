package com.example.designpattern.commandMethodPattern;

public class CommandMethodPatternExample {
    public static void main(String[] args) {
        //device
        TV tv = new TV();
        Stereo stereo = new Stereo();
        Command turnOnCommand = new TurnOnCommand(tv);
        Command turnOffCommand = new TurnOffCommand(tv);
        Command changeChannelCommand = new ChangeChannelCommand(tv);
        Command adjustVolumeCommand = new AdjustVolumeCommand(stereo);


        RemoteControl remoteControl = new RemoteControl();

        remoteControl.setCommand(turnOnCommand);
        remoteControl.pressesButton();

        remoteControl.setCommand(turnOffCommand);
        remoteControl.pressesButton();

        remoteControl.setCommand(changeChannelCommand);
        remoteControl.pressesButton();

        remoteControl.setCommand(adjustVolumeCommand);
        remoteControl.pressesButton();
    }
}
