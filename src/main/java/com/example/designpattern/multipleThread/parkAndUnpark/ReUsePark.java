package com.example.designpattern.multipleThread.parkAndUnpark;

import java.util.concurrent.locks.LockSupport;

public class ReUsePark {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            LockSupport.park();
            System.out.println("t1 is running");
            LockSupport.park();
            System.out.println("t1 is running again");
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            LockSupport.unpark(t1);
            System.out.println("t2 is running");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            LockSupport.unpark(t1);
        });

        t1.start();
        t2.start();
    }
}
