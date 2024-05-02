package com.example.designpattern.decorator;

public class ConcreteComponent implements Component {
    @Override
    public void operation() {
        System.out.println("invoke 具體角色的方法...");
    }

    public ConcreteComponent() {
        System.out.println("創建具體角色");
    }
}
