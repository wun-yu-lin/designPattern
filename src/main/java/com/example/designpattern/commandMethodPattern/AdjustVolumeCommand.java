package com.example.designpattern.commandMethodPattern;

public class AdjustVolumeCommand implements Command{
    private final Stereo stereo;

    public AdjustVolumeCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.adjustVolume();
    }
}
