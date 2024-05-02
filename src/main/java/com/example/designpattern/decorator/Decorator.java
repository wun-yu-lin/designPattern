package com.example.designpattern.decorator;

public class Decorator implements Component{
    private Component component;

    public Decorator(Component component) {
        System.out.println("創建裝飾者");
        this.component = component;
    }

    public void operation() {
        component.operation();
    }

}
