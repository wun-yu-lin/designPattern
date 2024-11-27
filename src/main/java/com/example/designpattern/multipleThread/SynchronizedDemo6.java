package com.example.designpattern.multipleThread;

import static java.lang.Thread.sleep;

public class SynchronizedDemo6 {
    public static void method(String name) throws InterruptedException {
        synchronized (SynchronizedDemo6.class) {
            System.out.println(name + " get the lock.");
            sleep(3000);
            System.out.println(name + " release the lock  after 3s. ");
        }
    }


    public static synchronized void method2(String name) throws InterruptedException {
        System.out.println(name + " get the lock.");
        sleep(3000);
        System.out.println(name + " release the lock  after 3s. ");
    }

    public static void main(String[] args) {
        Thread t1 =  new Thread(()->{
            try {
                method("thread1");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 =  new Thread(()->{
            try {
                method("thread2");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t2.start();
    }

}
