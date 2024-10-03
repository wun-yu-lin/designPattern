package com.example.designpattern.multipleThread.happensBefore;

public class VisibilityDemo2 {
    private static volatile int x = 0;
    private static volatile int y = 1;

    /**
     * 這邊解決了 可見性的問題
     * 透過加上 volatile 強制讓 thread 寫入變量到 main memory
     * 這樣其他線程即可讀取到變量 讚喔
     */
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (x == 2 && y == 3) {
                    System.out.println("Thread 1 可以看到變量改變");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            x = 2;
            y = 3;
        });

        t1.start();
        Thread.sleep(1000);
        t2.start();
    }

}
