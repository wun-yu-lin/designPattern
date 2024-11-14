package com.example.designpattern.multipleThread;


//作用在對象方法裏面
public class SynchronizedDemo4 {
    private static final Object lock = new Object();
    public void method(String name) {
        //針對像本身上鎖
        synchronized (this) {
            System.out.println(name + " method1 get the lock.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(name +  " release lock after 1s.");
        }
    }

    public void method2(String name) {
        //針對像本身上鎖
        synchronized (this) {
            System.out.println(name + " method2 get the lock.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(name +  " release lock after 1s.");
        }
    }

    public static void main(String[] args) {
        //一個使用 static 一個使用 non static method 作用的對象不相同，不互斥
        SynchronizedDemo4 synchronizedDemo4 = new SynchronizedDemo4();
        Thread t1 = new Thread(() -> {
            while (true) {
                synchronizedDemo4.method("thread1");
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        });

        Thread t2 = new Thread(() -> {
            while (true) {
                synchronizedDemo4.method2("thread2");
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        });

        t1.start();
        t2.start();
    }
}
