package com.example.designpattern.multipleThread.spinLock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class OriginSpinLock {

    private static Unsafe unsafe = null;

    private static final long valueOffset;

    private volatile int value = 0;

    static {
        try {
            unsafe = getUnsafeInstance();
            valueOffset = unsafe.objectFieldOffset(OriginSpinLock.class.getDeclaredField("value"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Unsafe getUnsafeInstance() throws NoSuchFieldException, IllegalAccessException {
        Field unsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
        unsafeInstance.setAccessible(true);
        return (Unsafe) unsafeInstance.get(Unsafe.class);
    }

    public void lock(String name) {
        for (; ; ) {
            int newV = value + 1;
            System.out.println(name + " waiting lock....");
            if (newV == 1) {
                //CAS 操作
                //如果成功把 newV, 修改成 1 代表取得 lock
                if (unsafe.compareAndSwapInt(this, valueOffset, 0, newV)) {
                    //獲取鎖即可return
                    return;
                }
            }
        }
    }

    public void unlock() {
        //CAS 操作
        //將鎖放掉
        unsafe.compareAndSwapInt(this, valueOffset, 1, 0);
    }


    public static void main(String[] args) {
        OriginSpinLock originSpinLock = new OriginSpinLock();

        Thread t1 = new Thread(() -> {
            originSpinLock.lock("thread1");
            System.out.println("thread 1 get lock...");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            originSpinLock.unlock();
            System.out.println("thread 1 release lock...");

        });

        Thread t2 = new Thread(() -> {
            originSpinLock.lock("thread2");
            System.out.println("thread 2 get lock...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            originSpinLock.unlock();
            System.out.println("thread 2 release lock...");

        });

        t1.start();
        t2.start();




    }


}
