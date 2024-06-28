package cn.edu.pzhu.mytest.thread;

import lombok.SneakyThrows;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 交替打印
 *
 * @author zhangcz
 * @since 20240627
 */
public class SequentialPrinterWithLock {

    private static final int NUMBER = 100;

    private static int COUNT = 1;

    private static final Lock LOCK = new ReentrantLock();

    private static Condition oddCondition = LOCK.newCondition();

    private static Condition evenCondition = LOCK.newCondition();

    @SneakyThrows
    public static void main(String[] args) {
        new Thread(new OddNumberThread(), "奇数线程").start();
        new Thread(new EvenNumberThread(), "偶数线程").start();

        Thread.sleep(500);
    }

    static class OddNumberThread extends Thread {

        @SneakyThrows
        @Override
        public void run() {
            while (COUNT <= NUMBER) {
                LOCK.lock();
                try {
                    if (COUNT % 2 == 0) {
                        oddCondition.await();
                    } else {
                        System.out.printf("thread:%s, value:%s\n", Thread.currentThread().getName(), COUNT);
                        COUNT++;
                        evenCondition.signal();
                    }
                } finally {
                    LOCK.unlock();
                }
            }
        }
    }

    static class EvenNumberThread extends Thread {

        @SneakyThrows
        @Override
        public void run() {
            while (COUNT <= NUMBER) {
                LOCK.lock();
                try {
                    if (COUNT % 2 != 0) {
                        evenCondition.await();
                    } else {
                        System.out.printf("thread:%s, value:%s\n", Thread.currentThread().getName(), COUNT);
                        COUNT++;
                        oddCondition.signal();
                    }
                } finally {
                    LOCK.unlock();
                }
            }
        }
    }
}
