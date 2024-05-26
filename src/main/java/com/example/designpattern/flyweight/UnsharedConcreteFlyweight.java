package com.example.designpattern.flyweight;


//此子類的目的是，不共享的 ConcreteFlyweight
public class UnsharedConcreteFlyweight implements Flyweight{
    @Override
    public void operation(ExtrinsicState extrinsicState) {
        //自行設計
        System.out.println("Invoke extrinsicState method...");
    }
}
