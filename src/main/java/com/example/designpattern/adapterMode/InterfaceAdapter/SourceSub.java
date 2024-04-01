package com.example.designpattern.adapterMode.InterfaceAdapter;

public class SourceSub extends Wrapper{
    @Override
    public void method1() {
        System.out.println("the sourceable interface's first Sub1!");
    }

    @Override
    public void method2() {
        System.out.println("the sourceable interface's second Sub2!");
    }


    public static void main(String[] args) {
        SourceSub sourceSub = new SourceSub();
        sourceSub.method1();
        sourceSub.method2();
    }
}
