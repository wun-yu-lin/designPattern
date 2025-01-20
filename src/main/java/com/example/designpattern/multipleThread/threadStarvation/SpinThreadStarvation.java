package com.example.designpattern.multipleThread.threadStarvation;

import com.example.designpattern.multipleThread.spinLock.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SpinThreadStarvation {
    private static OriginSpinLock spinLock = new OriginSpinLock();
    private static List<Thread> threads = new ArrayList<>();

    public static void main(String[] args) {
        JFrame jFrame = new JFrame(SpinThreadStarvation.class.getName());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(new FlowLayout(FlowLayout.LEFT));
        jFrame.setSize(new Dimension(350, 200));
        for (int i = 0; i < 10; i++) {
            JProgressBar progressBar = new JProgressBar();
            progressBar.setStringPainted(true);
            progressBar.setMinimum(0);
            progressBar.setMaximum(1000);
            jFrame.add(progressBar);
            Thread t = new Thread(() -> {
                Thread currThread = Thread.currentThread();
                progressBar.setString(currThread.getName());
                int c = 0;
                while (true) {
                    System.out.printf(String.valueOf(c));
                    if (c >= 1000)
                        break;

                    spinLock.lock(Thread.currentThread().getName());
                    progressBar.setValue(++c);
                    int a = 0;
                    for (int j = 0; j < 10; j++) {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        a++;
                    }
                    spinLock.unlock();
                }
            });
            threads.add(t);
        }
        jFrame.setVisible(true);
        threads.forEach(Thread::start);
    }
}
