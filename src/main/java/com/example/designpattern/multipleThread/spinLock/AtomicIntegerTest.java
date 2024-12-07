package com.example.designpattern.multipleThread.spinLock;

import com.example.designpattern.util.CalculateSpendTime;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {


    private static AtomicInteger value = new AtomicInteger(0);

    private void add (String threadName) {
        int updateValue =  value.addAndGet(1);
//        System.out.println(threadName + " current value " + updateValue);
    }

    public static void main(String[] args) {
        CalculateSpendTime.run(() -> {
            int count = 10000000;
            AtomicIntegerTest atomicIntegerTest = new AtomicIntegerTest();
            Thread t1 = new Thread(() -> {
                for (int i = 0; i < count; i++) {
                    atomicIntegerTest.add("thread1");
                }
            });
            Thread t2 = new Thread(() -> {
                for (int i = 0; i < count; i++) {
                    atomicIntegerTest.add("thread2");
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
