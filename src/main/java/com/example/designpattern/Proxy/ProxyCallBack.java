package com.example.designpattern.Proxy;


public class ProxyCallBack {
    public static class Proxy {
        public static void doSomething(Runnable cb) {
            System.out.println("doSomething, before proxy method");
            cb.run();
            System.out.println("doSomething, after proxy method");
        }

    }

    public static void main(String[] args) {
        Proxy.doSomething( () -> {
            System.out.println("doSomething, proxy method");
        });
    }


}