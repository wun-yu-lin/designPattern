package com.example.designpattern.flyweight;


//用來實作抽象的 Flyweight 模式的 interface
//此類必續是共享的，保存的狀態都會歸到 intrinsicState
public class ConcreteFlyweight implements Flyweight{

    private IntrinsicState intrinsicState;

    @Override
    public void operation(ExtrinsicState extrinsicState) {
        //自行定義方式
        System.out.println("invoke extrinsicState method...");
    }
}
