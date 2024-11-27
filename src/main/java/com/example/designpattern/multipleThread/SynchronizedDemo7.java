package com.example.designpattern.multipleThread;

public class SynchronizedDemo7 {

    private static int a = 0;

    public void add() {
        synchronized (this) {
            a++;
        }
    }

    public synchronized void add2() {
        a++;
    }
}
