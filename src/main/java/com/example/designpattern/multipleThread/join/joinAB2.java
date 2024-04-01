package com.example.designpattern.multipleThread.join;

public class joinAB2 {
    public static void main(String[] args) {

        Thread t2 = new Thread(() -> {
            System.out.println("t2 is running");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("t2 is finished");
        });

        Thread t3 = new Thread(() -> {
            System.out.println("t3 is running");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("t3 is finished");
        });

        Thread t1 = new Thread(() -> {
            System.out.println("t1 is running");
            try {
                t2.join();
                t3.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("got t2 and t3's result, t1 is finished");
        });

        t3.start();
        t2.start();
        t1.start();

    }
}
