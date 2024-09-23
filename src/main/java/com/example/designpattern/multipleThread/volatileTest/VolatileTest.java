package com.example.designpattern.multipleThread.volatileTest;

public class VolatileTest {
    private volatile static Integer count = 0;

    public synchronized static void increase() {
        count++; //必須是synchronized 操作，因為count++ 非原子性操作
    }

    private static class VolatileThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++) {
                increase();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];  // 建立一個線程數組

        for (int i = 0; i < 10; i++) {
            threads[i] = new VolatileThread();
            threads[i].start();
        }

        // 等待所有線程完成
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Final count: " + count);  // 最後輸出正確結果
    }

}
