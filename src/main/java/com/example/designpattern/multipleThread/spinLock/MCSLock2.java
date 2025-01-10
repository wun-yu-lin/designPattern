package com.example.designpattern.multipleThread.spinLock;

import jdk.jshell.spi.ExecutionControl;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MCSLock2 implements Lock {

    private static final Logger logger = LoggerFactory.getLogger(MCSLock2.class.getName());

    private AtomicReference<MCSNode> tail = new AtomicReference<>();

    private ThreadLocal<MCSNode> predecessor = ThreadLocal.withInitial(() -> null);

    private ThreadLocal<MCSNode> currentNode = ThreadLocal.withInitial(MCSNode::new);

    private static class MCSNode {
        MCSNode next;
        volatile boolean spin = true;
    }


    @Override
    public void lock() {
        predecessor.remove();
        logger.info("{} is trying to acquire the lock...", Thread.currentThread().getName());
        while (true) {
            predecessor.set(tail.get());
            if (tail.compareAndSet(predecessor.get(), currentNode.get())) {
                break;
            }
        }
        if (predecessor.get() != null) {
            predecessor.get().next = currentNode.get();
            while (currentNode.get().spin) {
                //thread 自己自旋
            }
        }
    }

    @Override
    public void unlock() {
        if (tail.get() != currentNode.get()) {
            if (currentNode.get().next != null) {
                currentNode.get().next.spin = false;  // 停止下一個 node 自旋，使取得鎖
            }
        }
        currentNode.remove();
    }

    @SneakyThrows
    @Override
    public void lockInterruptibly() throws InterruptedException {
        throw new ExecutionControl.NotImplementedException(MCSLock2.class.getName());
    }

    @SneakyThrows
    @Override
    public boolean tryLock() {
        throw new ExecutionControl.NotImplementedException(MCSLock2.class.getName());
    }

    @SneakyThrows
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        throw new ExecutionControl.NotImplementedException(MCSLock2.class.getName());
    }

    @SneakyThrows
    @Override
    public Condition newCondition() {
        throw new ExecutionControl.NotImplementedException(MCSLock2.class.getName());
    }

    public static void main(String[] args) {
        MCSLock2 lock = new MCSLock2();
        Runnable task = () -> {
            try {
                lock.lock();
                logger.info("{} acquired the lock", Thread.currentThread().getName());
                Thread.sleep(100); // 模擬執行緒執行的工作
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                logger.info("{} released the lock", Thread.currentThread().getName());
                lock.unlock();

            }
        };
        int threadNo = 0;
        for (int i = 0; i < 100; i++) {
            String threadName = "Thread-" + threadNo;
            Thread thread = new Thread(task, threadName);
            thread.start();
            threadNo++;
        }

    }
}
