package com.example.designpattern.multipleThread.happensBefore;

public class StartPrinciple {
    private static int x = 0;

    public static void main(String[] args) {
        x = 3;
        System.out.println("x = " + x);
        Thread t1 = new Thread(() -> {
            x = x * 2;
            System.out.println("thread x = " + x);
        });

        t1.start();
    }
}
