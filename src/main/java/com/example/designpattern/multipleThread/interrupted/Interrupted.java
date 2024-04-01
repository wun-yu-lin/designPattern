package com.example.designpattern.multipleThread.interrupted;

public class Interrupted extends Thread{
    @Override
    public void run() {
        while(!interrupted()) {
            System.out.println("Thread is running");
        }
        System.out.println("Thread is interrupted");
    }

    public static void main(String[] args) {
        Interrupted thread = new Interrupted();
        thread.start();
        try {
            Thread.sleep(1000);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
