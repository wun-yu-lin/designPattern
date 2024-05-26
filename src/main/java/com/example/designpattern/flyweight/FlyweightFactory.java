package com.example.designpattern.flyweight;


import java.util.Hashtable;

/**
 1. 此類被用來維護一個 Flyweight pool (存放內部狀態)
 2. 當 client 端請求時一個共享的 flyweight 時，會先搜尋 pool 是否有適合使用的，如果有則直接返回對象，否則就創立一個新的對象，再 return 出對像。
 */
public class FlyweightFactory {

    //Flyweight pool
    private Hashtable flyweights  = new Hashtable();


    //maintain pool
    public Flyweight getFlyweight(Object key){
        Flyweight flyweight = (Flyweight) flyweights.get(key);
        if (flyweight == null){
            //if not key exist, create new flyweight
            flyweight = new ConcreteFlyweight();
            flyweights.put(key, flyweight);
        }
        return  flyweight;
    }



}
