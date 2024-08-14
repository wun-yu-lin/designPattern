package com.example.designpattern.chainOfResponsibility;

public class MediumLevelHandler implements SupportHandler{
    private  SupportHandler nextHandler;
    @Override
    public void handRequest(Request request) {
        if (request.getPriority() == Request.Priority.Medium){
            System.out.println("Medium Level: " + request.getResponsibility());
        }else if (nextHandler != null){
            nextHandler.handRequest(request);
        }
    }

    @Override
    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
