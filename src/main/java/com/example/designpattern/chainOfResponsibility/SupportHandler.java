package com.example.designpattern.chainOfResponsibility;

public interface SupportHandler {
    void handRequest(Request request);
    void setNextHandler(SupportHandler nextHandler);
}
