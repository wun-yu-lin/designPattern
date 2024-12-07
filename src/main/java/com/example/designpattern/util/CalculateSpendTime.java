package com.example.designpattern.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CalculateSpendTime {

    public static void run (Runnable runnable) {
        Date before = new Date();
        runnable.run();
        Date after = new Date();
        // 計算花費時間
        long diff = after.getTime() - before.getTime();

        // 輸出花費時間
        System.out.println(runnable.toString() +  " spend " + diff + " ms....");


    }
}
