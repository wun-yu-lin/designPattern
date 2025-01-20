package com.example.designpattern.multipleThread.threadStarvation;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo1 {
    //透過 ReentrantLock 加上公平性，來解決 thread starvation 問題
    private static boolean isFair = true;
    private static ReentrantLock lock = new ReentrantLock(isFair);

    static List<Thread> threads = new ArrayList<>();

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("ReentrantLock 加上公平性解決線程飢餓問題");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(new FlowLayout(FlowLayout.LEFT));
        jFrame.setSize(new Dimension(350,200));
        for (int i = 0; i < 10; i++) {
            JProgressBar progressBar = new JProgressBar();
            progressBar.setStringPainted(true);
            progressBar.setMinimum(0);
            progressBar.setMaximum(1000);
            jFrame.add(progressBar);
            Thread t =  new Thread(() -> {
               progressBar.setString(Thread.currentThread().getName());
               int c = 0;
               while (true) {
                   if (c >= 1000) {
                       break;
                   }
                   lock.lock();
                   progressBar.setValue(++c);
                   try {
                       Thread.sleep(1);
                   } catch (InterruptedException e) {
                       throw new RuntimeException(e);
                   }
                   lock.unlock();
               }
            }, ReentrantLockDemo1.class.getName() + "_" + i);
            threads.add(t);
        }
        jFrame.setVisible(true);
        threads.forEach(Thread::start);
    }

}
