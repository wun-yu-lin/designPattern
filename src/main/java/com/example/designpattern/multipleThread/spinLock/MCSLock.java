package com.example.designpattern.multipleThread.spinLock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class MCSLock {
    private static Unsafe unsafe = null;
    private volatile MCSNode tail;
    private static final long valueOffset;

    public static class MCSNode {
        MCSNode next;
        volatile boolean spin = true; //spin 變量
    }

    static {
        try {
            unsafe = getUnsafeInstance();
            valueOffset = unsafe.objectFieldOffset(MCSLock.class.getDeclaredField("tail"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Unsafe getUnsafeInstance() throws NoSuchFieldException, IllegalAccessException {
        Field unsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
        unsafeInstance.setAccessible(true);
        return (Unsafe) unsafeInstance.get(Unsafe.class);
    }

    public void lock(MCSNode currentThreadMcsNode) {
        MCSNode predecessor = null;
        for (; ; ) {
            predecessor = tail;
            if (unsafe.compareAndSwapObject(this, valueOffset, tail, currentThreadMcsNode)) {
                break;
            }
        }
        if (predecessor != null) {
            predecessor.next = currentThreadMcsNode;
            while (currentThreadMcsNode.spin) {
                //自旋
            }
        }
    }

    public void unlock(MCSNode currentThreadMcsNode) {
        if (tail != currentThreadMcsNode) {
            if (currentThreadMcsNode.next == null) {
                if (unsafe.compareAndSwapObject(this, valueOffset, currentThreadMcsNode, null)) {
                    return;
                } else {
                    while (currentThreadMcsNode.next == null) {
                        //自旋
                    }
                }
            }
            currentThreadMcsNode.next.spin = false; // 停止下一個 node 自旋，使取得鎖
        }
    }


    private static final MCSLock lock = new MCSLock();
    // 每個執行緒都需要有自己的 MCSNode 節點
    private static final ThreadLocal<MCSLock.MCSNode> threadNode = ThreadLocal.withInitial(MCSLock.MCSNode::new);

    public static void main(String[] args) {
        Runnable task = () -> {
            MCSLock.MCSNode node = threadNode.get(); // 獲取每個執行緒的 MCSNode 節點
            lock.lock(node);
            try {
                System.out.println(Thread.currentThread().getName() + " acquired the lock");
                Thread.sleep(1000); // 模擬執行緒執行的工作
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock(node);
                System.out.println(Thread.currentThread().getName() + " released the lock");
            }
        };

        // 創建多個執行緒進行測試
        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");
        Thread t3 = new Thread(task, "Thread-3");

        t1.start();
        t2.start();
        t3.start();
    }


}
