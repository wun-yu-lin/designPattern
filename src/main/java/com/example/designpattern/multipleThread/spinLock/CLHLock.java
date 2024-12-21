package com.example.designpattern.multipleThread.spinLock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class CLHLock {
    private static Unsafe unsafe = null;
    private static final long valueOffset;

    private volatile CLHNode tail;

    public class CLHNode {
        private volatile boolean isLocked = true;
    }


    public void lock(CLHNode currentThreadNode) {
        CLHNode preNode = null;

        for (;;) {
            preNode = tail;
            if (unsafe.compareAndSwapObject(this, valueOffset, preNode, currentThreadNode)) {
                break;
            }
        }
        if (preNode != null) {
            while (preNode.isLocked){}
        }
    }

    public void unlock(CLHNode currentThreadNode) {
        if (!unsafe.compareAndSwapObject(this, valueOffset, currentThreadNode, null)) {
            currentThreadNode.isLocked = false;
        }
    }

    static {
        try {
            unsafe = getUnsafeInstance();
            valueOffset = unsafe.objectFieldOffset(CLHLock.class.getDeclaredField("tail"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Unsafe getUnsafeInstance() throws NoSuchFieldException, IllegalAccessException {
        Field unsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
        unsafeInstance.setAccessible(true);
        return (Unsafe) unsafeInstance.get(Unsafe.class);
    }

    public static void main(String[] args) throws InterruptedException {
        CLHLock lock = new CLHLock();

        Runnable task = () -> {
            CLHLock.CLHNode node = lock.new CLHNode();
            try {
                System.out.println(Thread.currentThread().getName() + " is trying to acquire the lock...");
                lock.lock(node);
                System.out.println(Thread.currentThread().getName() + " has acquired the lock!");

                // 模擬臨界區
                Thread.sleep(1000);

                System.out.println(Thread.currentThread().getName() + " is releasing the lock...");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock(node);
            }
        };

        // 啟動多個執行緒進行測試
        Thread thread1 = new Thread(task, "Thread-1");
        Thread thread2 = new Thread(task, "Thread-2");
        Thread thread3 = new Thread(task, "Thread-3");

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("All threads have finished execution.");
    }
}
