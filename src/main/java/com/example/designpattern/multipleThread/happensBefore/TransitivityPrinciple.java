package com.example.designpattern.multipleThread.happensBefore;

public class TransitivityPrinciple {

    static int x = 0;

    public static void main(String[] args) {
        x = 3;
        Thread t1 = new Thread(() -> {
            x = x + 2;
            System.out.println("thread 1,  x = " + x);

            Thread t2 = new Thread(() -> {
                x = x + 2;
                System.out.println("thread 2,  x = " + x);
            });
            t2.start();
        });

        t1.start();
    }


}
