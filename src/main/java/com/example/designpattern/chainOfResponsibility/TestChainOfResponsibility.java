package com.example.designpattern.chainOfResponsibility;

public class TestChainOfResponsibility {
    public static void main(String[] args) {
        HighLevelHandler highLevelHandler = new HighLevelHandler();
        MediumLevelHandler mediumLevelHandler = new MediumLevelHandler();
        LowLevelHandler lowLevelHandler = new LowLevelHandler();
        lowLevelHandler.setNextHandler(mediumLevelHandler);
        mediumLevelHandler.setNextHandler(highLevelHandler);

        Request request1 = new Request(Request.Priority.High, "High");
        Request request2 = new Request(Request.Priority.Medium, "Medium");
        Request request3 = new Request(Request.Priority.Low, "Low");
        lowLevelHandler.handRequest(request1);
        lowLevelHandler.handRequest(request2);
        lowLevelHandler.handRequest(request3);

    }
}
