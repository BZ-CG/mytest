package cn.edu.pzhu.mytest.thread;

import lombok.SneakyThrows;

/**
 * 交替打印
 *
 * @author zhangcz
 * @since 20240627
 */
public class SequentialPrinter {

    private static final int NUMBER = 100;

    private static int COUNT = 1;

    private static final Object LOCK = new Object();

    @SneakyThrows
    public static void main(String[] args) {
        new Thread(new OddNumberThread(LOCK), "奇数线程").start();
        new Thread(new EvenNumberThread(LOCK), "偶数线程").start();

        Thread.sleep(500);
    }

    static class OddNumberThread extends Thread {

        private Object lock;

        public OddNumberThread(Object o) {
            this.lock = o;
        }

        @SneakyThrows
        @Override
        public void run() {
            while (COUNT <= NUMBER) {
                synchronized (lock) {
                    if (COUNT % 2 == 0) {
                        lock.wait();
                    } else {
                        System.out.printf("thread:%s, value:%s\n", Thread.currentThread().getName(), COUNT);
                        COUNT++;
                        lock.notify();
                    }
                }
            }
        }
    }

    static class EvenNumberThread extends Thread {

        private Object lock;

        public EvenNumberThread(Object o) {
            this.lock = o;
        }

        @SneakyThrows
        @Override
        public void run() {
            while (COUNT <= NUMBER) {
                synchronized (lock) {
                    if (COUNT % 2 != 0) {
                        lock.wait();
                    } else {
                        System.out.printf("thread:%s, value:%s\n", Thread.currentThread().getName(), COUNT);
                        COUNT++;
                        lock.notify();
                    }
                }
            }
        }
    }

}
