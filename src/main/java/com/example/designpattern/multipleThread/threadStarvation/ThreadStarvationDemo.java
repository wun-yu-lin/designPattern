package com.example.designpattern.multipleThread.threadStarvation;

import javax.swing.*;
import java.awt.*;

public class ThreadStarvationDemo {
    private static Object lock = new Object();


    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Thread Starvation--synchronized");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(new FlowLayout(FlowLayout.LEFT));
        jFrame.setSize(new Dimension(350,200));
        for (int i = 0; i < 10; i++) {
            JProgressBar progressBar = new JProgressBar();
            progressBar.setStringPainted(true);
            progressBar.setMinimum(0);
            progressBar.setMaximum(1000);
            jFrame.add(progressBar);
            new Thread(()-> {
                progressBar.setString(Thread.currentThread().getName());
                int c = 0;
                while(true) {
                    synchronized (lock) {
                        if (c >= 1000) {
                            break;
                        }
                        progressBar.setValue(++c);
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }).start();
        }

        jFrame.setVisible(true);
    }
}
