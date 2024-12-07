package com.example.designpattern.multipleThread.spinLock;


import com.example.designpattern.util.CalculateSpendTime;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class TicketSpinLock {

    private static Unsafe unsafe = null;
    private static final long ticketNumOffset;
    private static final long processingNumOffset;

    private volatile int ticketNum = 0;

    private volatile int processingNum = 0;

    //測試自旋鎖加數值用，非ticketSpinLock必要欄位
    private volatile int testValue = 0;

    static {
        try {
            unsafe = getUnsafeInstance();
            ticketNumOffset = unsafe.objectFieldOffset(TicketSpinLock.class.getDeclaredField("ticketNum"));
            processingNumOffset = unsafe.objectFieldOffset(TicketSpinLock.class.getDeclaredField("processingNum"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Unsafe getUnsafeInstance() throws NoSuchFieldException, IllegalAccessException {
        Field unsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
        unsafeInstance.setAccessible(true);
        return (Unsafe) unsafeInstance.get(Unsafe.class);
    }

    public int lock() {
        int nowNum;
        for (; ; ) {
            nowNum = ticketNum;
            if (unsafe.compareAndSwapInt(this, ticketNumOffset, ticketNum, ticketNum + 1)) {
                break;
            }
        }

        while (processingNum != nowNum) {
        }
        return nowNum;
    }

    public void unlock(int ticket) {
        int next = ticket + 1;
        unsafe.compareAndSwapInt(this, processingNumOffset, ticket, next);
    }

    public void processing(String threadName) {
        int ticket = lock();
        System.out.println(threadName + " get lock...");
        System.out.println(threadName + "current value " + testValue);
        testValue++;
        System.out.println(threadName + " current value " + testValue);
        unlock(ticket);
        System.out.println(threadName + " release lock...");
    }


    public static void main(String[] args) {
        CalculateSpendTime.run(()-> {
            int count = 10000;
            TicketSpinLock spinLock = new TicketSpinLock();
            Thread t1 = new Thread(() -> {
                for (int i = 0; i < count; i++) {
                    spinLock.processing("thread1");
                }
            });

            Thread t2 = new Thread(() -> {
                for (int i = 0; i < count; i++) {
                    spinLock.processing("thread2");
                }
            });
            t1.start();
            t2.start();
            try {
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
