package com.example.designpattern.multipleThread.spinLock;

import com.example.designpattern.util.CalculateSpendTime;

public class MutexLockTest {


    private volatile int value = 0;

    public void add (String threadName){
        synchronized (MutexLockTest.class) {
//            System.out.println(threadName + " get lock...");
            value++;
        }
    }

    public static void main(String[] args) {
        CalculateSpendTime.run(() -> {
            int count = 10000000;
            MutexLockTest mutexLockTest = new MutexLockTest();
            Thread t1 = new Thread(() -> {
                for (int i = 0; i < count; i++) {
                    mutexLockTest.add("thread1");
                }
            });
            Thread t2 = new Thread(() -> {
                for (int i = 0; i < count; i++) {
                    mutexLockTest.add("thread2");
                }
            });

            t1.start();
            t2.start();
            try {
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
