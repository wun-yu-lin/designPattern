package com.example.designpattern.multipleThread.deadlock;

public class DeadLockDemo1 {

    private static final Object lockA = new Object();
    private static final Object lockB = new Object();


    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            synchronized (lockA) {
                System.out.println(Thread.currentThread().getName() +  " get lock A");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lockB){
                    System.out.println(Thread.currentThread().getName() + " get lock B");
                }

            }
        });

        Thread t2 = new Thread(()->{
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() +  " get lock B");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lockA){
                    System.out.println(Thread.currentThread().getName() + " get lock A");
                }

            }
        });


        t1.start();
        t2.start();
    }

}
