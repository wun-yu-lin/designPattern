package com.example.designpattern.singleton;

import java.util.Objects;

public class Singleton {


    //private constructor, 避免被實體化
    private Singleton(){}


    //內部類別保護實例
    private static class SingletonFactory{
        private static Singleton instance = new Singleton();


        //調用實例的接口
        public static Singleton getInstance(){
            return SingletonFactory.instance;
        }


        //對象如果被序列化，可保證對象在序列化前後保持一致
        public Object readResolve(){
            return getInstance();
        }

    }


}
