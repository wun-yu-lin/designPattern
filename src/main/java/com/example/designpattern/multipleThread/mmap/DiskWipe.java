package com.example.designpattern.multipleThread.mmap;

import javax.security.auth.callback.Callback;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.concurrent.Callable;

public class DiskWipe {
    // 定義 4KB 和 1GB 的大小
    private static final int _4KB = 4 * 1024;
    private static final long _1GB = 1024L * 1024L * 1024L;

    public static void main(String[] args) {
        File file = new File("yourfile.dat");
        // 方法一: 4KB 刷盘
        runWithTime(() -> diskWipe4KB(file), "diskWipe4KB()");

        // 方法二: 单字节刷盘
        runWithTime(() -> diskWipeSingleChr(file), "diskWipeSingleChr");
    }

    public static void diskWipeSingleChr(File file) {
        try (FileChannel fileChannel = new RandomAccessFile(file, "rw").getChannel()) {
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1);
            byteBuffer.put((byte) 0);
            for (long i = 0; i < _1GB; i++) {
                byteBuffer.position(0);
                byteBuffer.limit(1);
                fileChannel.write(byteBuffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void diskWipe4KB(File file) {
        try (FileChannel fileChannel = new RandomAccessFile(file, "rw").getChannel()) {
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(_4KB);
            for (int i = 0; i < _4KB; i++) {
                byteBuffer.put((byte) 0);
            }
            for (long i = 0; i < _1GB; i += _4KB) {
                byteBuffer.position(0);
                byteBuffer.limit(_4KB);
                fileChannel.write(byteBuffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void runWithTime(Runnable cb, String methodName) {
        Date start = new Date();
        cb.run();
        Date end = new Date();
        long elapsedTime = end.getTime() - start.getTime();  // 使用 getTime() 來獲取毫秒
        System.out.println("run" + methodName + ", Spend: " + elapsedTime + " ms");
    }
}
