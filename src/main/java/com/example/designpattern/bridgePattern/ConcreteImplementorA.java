package com.example.designpattern.bridgePattern;


/**
 * 具體實現化角色
 */
public class ConcreteImplementorA implements Implementor {
    @Override
    public void operationImpl() {
        System.out.println("ConcreteImplementorA operationImpl");
    }
}
