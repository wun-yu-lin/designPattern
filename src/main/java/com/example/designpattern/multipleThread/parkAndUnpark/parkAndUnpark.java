package com.example.designpattern.multipleThread.parkAndUnpark;

import java.util.concurrent.locks.LockSupport;

public class parkAndUnpark {
    private static String message;

    public static void main(String[] args) {

        //創立一個新的執行緒t1，並且讓t1進入等待狀態, 直到t2執行unpark(t1)後，t1才會被喚醒
        Thread  t1 = new Thread(() -> {
            LockSupport.park();
            System.out.println(message);
        });

        //創立一個新的執行緒t2，先賦予message數值，並且讓t2執行unpark(t1)，喚醒t1
        Thread t2 = new Thread(()->{
            message= "T2 working...";
            LockSupport.unpark(t1);
        });
        t1.start();
        t2.start();
    }
}
