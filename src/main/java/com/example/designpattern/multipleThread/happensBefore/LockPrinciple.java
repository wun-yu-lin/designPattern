package com.example.designpattern.multipleThread.happensBefore;

public class LockPrinciple {
    private static int x = 0;

    private static final Object lock = new Object();

    //lock 原則
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (lock) {
                    x = x + 3;
                    System.out.println("thread 1 x=  " + x);
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (lock) {
                    x = x + 1;
                    System.out.println("thread 2 x=  " + x);
                }
            }
        });

        t1.start();
        t2.start();
    }
}
