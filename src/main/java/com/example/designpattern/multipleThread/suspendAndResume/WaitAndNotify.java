package com.example.designpattern.multipleThread.suspendAndResume;

public class WaitAndNotify {
    public class Test1 {
        /*
        1. 使用 Sync 與 wait / notify 機制, wait '堵塞' 線程運行, 釋放對象的鎖, notify調用釋放被堵塞的線程，給其他線程使用, 鎖的部分會由sync 運行完才釋放，非notify調用的當下直接釋放鎖
              wait:會堵塞線程運行，該線程釋放鎖，並將線程放到 queue 當中
              notify:換醒一個在 queue 當中等待的線程，一次緩醒一個線程。鎖不會因為調用的當下釋放，而是sync 程式碼執行完後釋放！！
              notifyAll: 喚醒該lock object 在線程池當中的所有線程
        2. 建立 Object 物件的實例 lock, 調用 wait / notify
        3. enum 判斷目前狀態該給哪個線程處理，非目前開始用線程就堵塞該線程
        4. 調用 notify 釋放堵塞的線程


         */
        private static Object lock = new Object();

        private static TStatus status = TStatus.T1;

        static enum TStatus {
            T1,
            T2
        }

        static class T1 extends Thread {

            public void run() {
                while (true) {
                    synchronized (lock) {
                        //work
                        if (status == TStatus.T1) {
                            for (int i = 0; i < 10; i++) {
                                System.out.println("T1 working...");
                            }
                            System.out.println("T1 sleeping 100ms...");
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);

                            }
                            status = TStatus.T2;
                            lock.notify();

                        } else {
                            try {
                                System.out.println("堵塞T1");
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            }

            static class T2 extends Thread {

                public void run() {
                    while (true) {
                        synchronized (lock) {
                            //work
                            if (status == TStatus.T2) {
                                lock.notify();
                                for (int i = 0; i < 10; i++) {
                                    System.out.println("T2 working...");
                                }
                                System.out.println("T2 sleeping 100ms...");
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);

                                }
                                status = TStatus.T1;
                                lock.notify();

                            } else {
                                try {
                                    System.out.println("堵塞T2");
                                    lock.wait();
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    }
                }

                public static void main(String[] args) {
                    Thread t1 = new T1();
                    Thread t2 = new T2();
                    t1.start();
                    t2.start();
                }
            }

        }
    }
}
