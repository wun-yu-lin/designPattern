package com.example.designpattern.multipleThread.spinLock;

import jdk.jshell.spi.ExecutionControl;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

// 封裝所有行為 對外只開放 Lock & unLock
public class CLHLock3 implements Lock {

    static Logger logger = LoggerFactory.getLogger(CLHLock3.class.getName());

    private AtomicReference<CLHNode> tail = new AtomicReference<>();
    ThreadLocal<CLHNode> currentThreadCLHNode = ThreadLocal.withInitial(CLHNode::new);
    ThreadLocal<CLHNode> previousThreadCLHNode = ThreadLocal.withInitial(() -> null);


    private static class CLHNode {
        private volatile boolean isLock = true;
    }

    private void initCurrentThreadNode(){
        this.currentThreadCLHNode = ThreadLocal.withInitial(CLHNode::new);
    }


    @Override
    public void lock() {
        previousThreadCLHNode.remove();
        initCurrentThreadNode();
        logger.info(Thread.currentThread().getName() + " is trying to acquire the lock...");
        while (true) {
            previousThreadCLHNode.set(tail.get());
            //多個線程競爭寫入 FIFO queue
            if (tail.compareAndSet(previousThreadCLHNode.get(), currentThreadCLHNode.get())) {
                break;
            }
        }
        if (previousThreadCLHNode.get() != null) {
            while (previousThreadCLHNode.get().isLock) {
                //當前驅node release lock, 下個線程解除自旋
                //下個node 解除自旋後，就獲得鎖，接續工作
            }
        }
        previousThreadCLHNode.remove();
    }

    @Override
    public void unlock() {
        //如果當前node不是 tail, CAS 操作會失敗, 而放掉當前的鎖
        if (!tail.compareAndSet(currentThreadCLHNode.get(), null)) {
            currentThreadCLHNode.get().isLock = false;
        }
    }



    @Override
    public void lockInterruptibly() throws InterruptedException {
        lock();
    }

    @Override
    public boolean tryLock() {
        lock();
        return true;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        lock();
        return true;
    }

    @SneakyThrows
    @Override
    public Condition newCondition() {
        throw new ExecutionControl.NotImplementedException("Not implementation newCondition in CLHLock");
    }


    public static void main(String[] args) {
        CLHLock3 lock = new CLHLock3();

        Runnable task = () -> {
            try {
                lock.lock();
                logger.info(Thread.currentThread().getName() + " has acquired the lock!");

                // 模擬臨界區
                Thread.sleep(1000);

                logger.info(Thread.currentThread().getName() + " is releasing the lock...");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }

        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");
        Thread t3 = new Thread(task, "Thread-3");
        Thread t4 = new Thread(task, "Thread-4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
