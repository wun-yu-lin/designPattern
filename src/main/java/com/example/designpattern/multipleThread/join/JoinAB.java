package com.example.designpattern.multipleThread.join;

public class JoinAB {
    public static void main(String[] args) throws InterruptedException {
        Thread t2 = new Thread(() -> {
            System.out.println("t2 is running");
            try {
                Thread.sleep(1000);;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("t2 is finished");
        });
        t2.start();

        t2.join();
        System.out.println("got t2's result, t1 is running");

    }
}
