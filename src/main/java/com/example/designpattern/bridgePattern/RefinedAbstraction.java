package com.example.designpattern.bridgePattern;

public class RefinedAbstraction extends Abstraction {
    protected RefinedAbstraction(Implementor implementor) {
        super(implementor);
    }

    @Override
    public void operation() {
        System.out.println("RefinedAbstraction operation");
        implementor.operationImpl();
    }

}
