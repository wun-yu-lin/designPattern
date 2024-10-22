package com.example.designpattern.multipleThread.happensBefore;

public class JoinPrinciple {
    private static int x = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()-> {
            x  = x + 2;
        });
        t1.start();
        t1.join(); // 等待t1 執行完後再接續下去
        System.out.println("main-thread x = " + x );
    }
}
