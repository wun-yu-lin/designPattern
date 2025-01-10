package com.example.designpattern.multipleThread.spinLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicReference;
//使用 AtomicReference 的版本
public class CLHLock2 {
    static Logger logger = LoggerFactory.getLogger(CLHLock2.class.getName());

    private AtomicReference<CLHNode> tail = new AtomicReference<>();

    public static class CLHNode {
        private volatile boolean isLocked = true;
    }

    public CLHNode getNode() {
        return new CLHNode();
    }

    public void lock(CLHNode currThreadNode) {
        CLHNode preNode = null;
        while (true) {
            //多個線程競爭寫入 FIFO queue
            preNode = tail.get();
            if (tail.compareAndSet(preNode, currThreadNode)) {
                break;
            }
        }
        if (preNode != null) {
            while (preNode.isLocked) {
                //當前驅node release lock, 下個線程解除自旋
                //下個node 解除自旋後，就獲得鎖，接續工作
            }
        }
        preNode = null;
    }

    public void unLock(CLHNode currThreadNode) {
        //如果當前node不是 tail, CAS 操作會失敗, 而放掉當前的鎖
        if (!tail.compareAndSet(currThreadNode, null)) {
            currThreadNode.isLocked = false;
        }
    }

    public static void main(String[] args) {
        CLHLock2 lock2 = new CLHLock2();

        Runnable task = () -> {
            CLHNode node = lock2.getNode();
            try {
                logger.info(Thread.currentThread().getName() + " is trying to acquire the lock...");
                lock2.lock(node);
                logger.info(Thread.currentThread().getName() + " has acquired the lock!");

                // 模擬臨界區
                Thread.sleep(1000);

                logger.info(Thread.currentThread().getName() + " is releasing the lock...");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock2.unLock(node);
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
