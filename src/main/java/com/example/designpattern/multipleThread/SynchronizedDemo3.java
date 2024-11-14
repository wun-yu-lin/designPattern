package com.example.designpattern.multipleThread;

public class SynchronizedDemo3 {
    public synchronized void method(String name) {
        System.out.println(name + " method1 get the lock.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(name +  " release lock after 1s.");
    }
    public static synchronized void method2(String name) {
        System.out.println(name + " method2 get the lock.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(name +  " release lock after 1s.");
    }

    public static void main(String[] args) {
        //一個使用 static 一個使用 non static method 作用的對象不相同，不互斥
        SynchronizedDemo3 synchronizedDemo3 = new SynchronizedDemo3();
        Thread t1 = new Thread(() -> {
            while (true) {
                synchronizedDemo3.method("thread1");
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        });

        Thread t2 = new Thread(() -> {
            while (true) {
                SynchronizedDemo3.method2("thread2");
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t1.start();
        t2.start();
    }
}
