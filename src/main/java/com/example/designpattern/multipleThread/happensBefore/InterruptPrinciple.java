package com.example.designpattern.multipleThread.happensBefore;

public class InterruptPrinciple {
    private static int x = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000000);
            } catch (InterruptedException e) {
                System.out.println("t1 thread interrupted!");
            }
            System.out.println("thread1 x = " + x);
        });

        t1.start();
        x = x + 1;
        Thread.sleep(3000);
        t1.interrupt();
    }
}
