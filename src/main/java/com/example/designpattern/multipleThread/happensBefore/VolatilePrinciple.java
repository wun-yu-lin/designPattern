package com.example.designpattern.multipleThread.happensBefore;

public class VolatilePrinciple {
    //volatile 讓變數具有可見性
    private static volatile int x = 1;

    private static int y = 0;


    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            y = 2;
            x = x + 3;
        });

        Thread t2 = new Thread(() -> {
            System.out.println("thread2 x = " + x + ", y = " + y);
        });


        t1.start();
        Thread.sleep(1000);
        t2.start();

    }

}
