package com.example.designpattern.singleton;

public class SingletonSync {
    private static SingletonSync instance = null;

    private static synchronized void syncInit(){
        if (instance==null){
           instance = new SingletonSync();
        }
    }

    public static SingletonSync getInstance(){
        if (instance==null){
            syncInit();
        }
        return instance;
    }
}
