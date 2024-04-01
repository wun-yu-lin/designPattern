package com.example.designpattern.adapterMode.instanceAdapter;

public class Adapter implements Targetable{
    private Source source;

    public Adapter(Source source){
        this.source = source;
    }


    @Override
    public void method1() {
        this.source.method1();
    }

    @Override
    public void method2() {
        System.out.println("This is the targetable method!");

    }


    public static void main(String[] args) {
        Adapter adapter = new Adapter( new Source());
        adapter.method1();
        adapter.method2();
    }
}
