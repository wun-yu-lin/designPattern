package com.example.designpattern.multipleThread.threadStarvation;

import javax.swing.*;
import java.awt.*;

public class ThreadStarvationDemo {
    private static Object lock = new Object();

    @FunctionalInterface
    interface ThreadRunnable<T, E> {
        void run(T arg1, E arg2);
    }

    public static void main(String[] args) {

        ThreadRunnable<JProgressBar, Object> threadRunnable = (bar, lock) -> {
            bar.setString(Thread.currentThread().getName());
            int c = 0;
            while (true) {
                synchronized (lock) {
                    if (c >= 1000) {
                        break;
                    }
                    bar.setValue(++c);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        String title = "Thread Starvation--synchronized";
        createFrameAndProcess(threadRunnable,title,10);

    }

    public static void createFrameAndProcess(ThreadRunnable<JProgressBar, Object> runnable, String title,
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
            new Thread(() -> runnable.run(progressBar, lock)).start();
        }
        jFrame.setVisible(true);
    }
}
