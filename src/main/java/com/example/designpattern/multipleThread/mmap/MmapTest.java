package com.example.designpattern.multipleThread.mmap;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MmapTest {

    //Java中的讀寫方式大概可以被分成三種：
    //普通IO，FileChannel（檔案通道），mmap（記憶體映射）。 FileChannel 存在於java.nio 套件中。也是Java 最常用的文件操作類別
    //今天的主角mmap，由FileChannel 呼叫映射方法衍生出來的一種特殊讀寫文件的方式，被稱為內存映射。


    public static void main(String[] args) throws IOException {
        FileChannel fileChannel = null;
        try {
            fileChannel = new RandomAccessFile(new File("db.data"), "rw").getChannel();

            //MappedByteBuffer 就是 Java 中的 mmap 操作類別。
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileChannel.size());

            //write
            byte[] bytes = new byte[8];
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (bytes.length - i);
            }
            int pointer = 8;
            //由目前指向位置寫入資料 8 byte 的資料
            mappedByteBuffer.put(bytes);

            //由目前指向位置讀取資料 8 byte 的資料
            mappedByteBuffer.get(bytes);

            //由 pointer 指向並寫入資料
            //方法一, pointer 指向第一個 byte 被寫入的位置
            MappedByteBuffer subBuffer = mappedByteBuffer.slice();
            subBuffer.put(pointer, bytes);



            //以 mmap 讀取 1G 檔案為例， fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, _GB);
            //進行mmap是一個消耗極少的操作，此時並不表示1G的檔案被讀進了pageCache。
            FileChannel fileChannel2 = new RandomAccessFile(new File("db2.data"), "rw").getChannel();
            long _GB = fileChannel2.size();
            long _4kb = 4;
            long temp = 0;
            MappedByteBuffer map = fileChannel2.map(FileChannel.MapMode.READ_WRITE, 0, _GB);
            for (int i = 0; i < _GB; i += _4kb) {
                temp += map.get(i);
            }
//            System.out.println(temp);


        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            fileChannel.close();
        }

    }
}
