package com.example.designpattern.decorator;

public class ConcreteDecorator extends Decorator {
    public ConcreteDecorator(Component component) {
        super(component);
    }

    public void operation() {
        super.operation();
        addFunction();

    }

    public void addFunction() {
        System.out.println("為具體角色增加的新功能，如：獲取新數據...");
    }
}
