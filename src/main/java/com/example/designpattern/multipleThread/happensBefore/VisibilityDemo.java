package com.example.designpattern.multipleThread.happensBefore;

public class VisibilityDemo {
    private static int x = 0;
    private static int y = 1;

    /**
     * 這邊其實有可見性問題，由於經過 JVM，無法判讀經過 compile 後的實際邏輯
     *  thread 1 根本不會判讀到 thread 2 把變量改變了，因為可見性的問題
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
