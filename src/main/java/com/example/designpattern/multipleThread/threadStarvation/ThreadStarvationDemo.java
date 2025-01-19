package com.example.designpattern.multipleThread.threadStarvation;

import javax.swing.*;
import java.awt.*;

public class ThreadStarvationDemo {
    private static Object lock = new Object();

    @FunctionalInterface
    interface ThreadRunnable<T> {
        void run(T arg1);
    }

    public static void main(String[] args) {

        ThreadRunnable<JProgressBar> threadRunnable = (bar) -> {
            bar.setString(Thread.currentThread().getName());
            int c = 0;
            while (true) {
                synchronized (lock) {
                    if (c >= 1000) {
                        break;
                    }
                    bar.setValue(++c);
                    int temp = 0;
                    for (long i = 0; i <10000000 ; i++) {
                        temp++;
                    }
                }
            }
        };
        String title = "Thread Starvation--synchronized";
        createFrameAndProcess(threadRunnable,title,10);

    }

    public static void createFrameAndProcess(ThreadRunnable<JProgressBar> runnable, String title,
                                             int threadCount) {
        JFrame jFrame = new JFrame(title);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(new FlowLayout(FlowLayout.LEFT));
        jFrame.setSize(new Dimension(350, 200));
        for (int i = 0; i < threadCount; i++) {
            JProgressBar progressBar = new JProgressBar();
            progressBar.setStringPainted(true);
            progressBar.setMinimum(0);
            progressBar.setMaximum(1000);
            jFrame.add(progressBar);
            new Thread(() -> runnable.run(progressBar), ThreadStarvationDemo.class.getName() + "_" + i).start();
        }
        jFrame.setVisible(true);
    }
}
