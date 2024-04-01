package com.example.designpattern.adapterMode.classAdapter;

public class Adapter extends Source implements Targetable {
    @Override
    public void method2() {
        System.out.println("This is the targetable method!");
    }

    public static void main(String[] args) {

        //因此 Targetable 介面的實作類別就具有Source類別的功能。
        Targetable target = new Adapter();
        target.method1();
        target.method2();
    }
}
