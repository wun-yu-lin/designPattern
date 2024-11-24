package com.example.designpattern.dynamicProxy.jdk;

import org.springframework.stereotype.Component;

@Component
public class Student implements Human{
    @Override
    public void display() {
        System.out.println("Student display");
    }
}
