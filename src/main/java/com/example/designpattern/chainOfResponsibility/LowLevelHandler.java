package com.example.designpattern.chainOfResponsibility;

public class LowLevelHandler implements SupportHandler{
    private  SupportHandler nextHandler;
    @Override
    public void handRequest(Request request) {
        if (request.getPriority() == Request.Priority.Low){
            System.out.println("Low Level: " + request.getResponsibility());
        }else if (nextHandler != null){
            nextHandler.handRequest(request);
        }
    }

    @Override
    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
