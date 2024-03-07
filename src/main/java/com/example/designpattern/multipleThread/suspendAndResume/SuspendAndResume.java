package com.example.designpattern.multipleThread.suspendAndResume;

public class SuspendAndResume {

    //將導致死鎖，此方式有死鎖問題！！
//    public static void main(String[] args) {
//        Thread mt = new Thread(()-> {
//           while (true){
//               //println 是被聲明為同步方法，導致取得單例屬性加上同步鎖
//               System.out.println("Thread running....");
//           }
//        });
//        // 因為同步機制，mt不會解放鎖
//        mt.start();
//
//
//        try{
//            Thread.currentThread().sleep(100);
//        }catch (InterruptedException e){
//
//        }
//        mt.suspend();
//        System.out.println("Can you go here?");
//        mt.resume();
//
//    }


    //使用 wait & notify 組成，解決死鎖問題
    // 使用此作法在一定程度上解決死鎖問題
    /*
    需要注意：
    1. object.wait() 在任意對象都可以調用，導致賭塞
        wait() 會釋放該對象的鎖
        notify 可以解開賭塞，但需要“重新獲得鎖配置”
    2. wait() / notify() 需要在 synchronized 區塊或方法中調用，並且需要同對象的方法調用！！才能完成1. 的規局
    3. notify 可以釋放對象，並讓其獲得鎖。 notifyAll 是釋放所有賭塞的線程，並讓他們去競爭鎖，獲得鎖的線程才能接續往下執行。
     */
    public static void main(String[] args) throws InterruptedException {
        MyThread mt = new MyThread();
        mt.start();
        Thread.sleep(100);
        mt.suspendThread();
        System.out.println("Can you get here?");
        Thread.sleep(3000);
        mt.resumeThread();


    }


    static class MyThread extends Thread {
        public boolean isSuspend  = false;

        public void run(){
            while (true){
                synchronized (this){
                    System.out.println("Running...");
                    if (isSuspend)
                        try{
                            wait();
                        }catch (InterruptedException e){

                        }
                }
            }
        }
        public void suspendThread(){
            this.isSuspend = true;
        }

        public void resumeThread(){
            synchronized (this){
                this.isSuspend = false;
                notify();
            }
        }
    }





}
