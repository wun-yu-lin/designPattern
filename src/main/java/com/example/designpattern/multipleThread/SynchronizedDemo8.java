package com.example.designpattern.multipleThread;

import java.util.concurrent.atomic.AtomicInteger;

public class SynchronizedDemo8 {

    private static final Object lock = new Object();

    private static int count = 0;

    private static final AtomicInteger thread1Count = new AtomicInteger(0);
    private static final AtomicInteger thread2Count = new AtomicInteger(0);


    private static void add(String threadName) {
        synchronized (lock) {
            System.out.println(threadName +  " get the lock");
            count++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(threadName +  " release the lock");
        }
    }


    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                SynchronizedDemo8.add("thread 1");
                thread1Count.addAndGet(1);
                System.out.println("thread 1, and count" +  thread2Count);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                SynchronizedDemo8.add("thread 2");
                thread2Count.addAndGet(1);
                System.out.println("thread 2, and count" +  thread2Count);
            }
        });
        //阻塞行為測試，t1 & t2 會因為同步機制，導致線程阻塞
        t1.start();
        t2.start();
    }
}
