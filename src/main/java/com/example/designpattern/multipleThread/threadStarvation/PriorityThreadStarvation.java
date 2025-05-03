package com.example.designpattern.multipleThread.threadStarvation;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public class PriorityThreadStarvation {

    private static List<Thread> threadList = new ArrayList<>();
    public static void main(String[] args) {

        JFrame jFrame = new JFrame(PriorityThreadStarvation.class.getName());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(new FlowLayout(FlowLayout.LEFT));
        jFrame.setSize(new Dimension(350, 400));
        Integer currentProcess = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < currentProcess * 2; i++) {
            JProgressBar progressBar = new JProgressBar();
            progressBar.setStringPainted(true);
            progressBar.setMinimum(0);
            progressBar.setMaximum(1000);
            jFrame.add(progressBar);
            int finalI = i;
            Thread t =  new Thread(() -> {
                Thread currThread = Thread.currentThread();
                progressBar.setString(currThread.getName());
                int c = 0;
                while(true) {
                    if (c >= 1000) break;
                    progressBar.setValue(++c);
                    int a = 0;
                    for (long j= 0; j < 10000000; j++) {
                        a++;
                    }
                    if (finalI == 0) {
                        currThread.setPriority(1);
                    } else {
                        currThread.setPriority(10);
                    }
                }
            });
            threadList.add(t);
        }
        jFrame.setVisible(true);
        for (Thread thread: threadList) {
            thread.start();
        }
    }
}
