package com.example.designpattern.multipleThread.parkAndUnpark;

import java.util.concurrent.locks.LockSupport;

public class ReUsePark2 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            LockSupport.park();
            System.out.println("t1 is running");
        });

        Thread t2 = new Thread(() -> {
            LockSupport.unpark(t1);
            LockSupport.unpark(t1);

        });

        t1.start();
        t2.start();
    }
}
