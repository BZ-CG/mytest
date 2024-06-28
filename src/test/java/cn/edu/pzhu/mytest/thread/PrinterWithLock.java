package cn.edu.pzhu.mytest.thread;

import lombok.SneakyThrows;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 打印adc
 *
 * @author zhangcz
 * @since 20240627
 */
public class PrinterWithLock {

    private static final char[] CHAR_ARR = new char[] { '1', 'A', '2', 'B', '3', 'C' };

    private static int STEP = 0;

    private static final Lock LOCK = new ReentrantLock();

    private static final Condition numberCondition = LOCK.newCondition();

    private static final Condition charCondition = LOCK.newCondition();

    @SneakyThrows
    public static void main(String[] args) {
        new Thread(new NumberPrinter(), "数字线程").start();
        new Thread(new CharPrinter(), "字母线程").start();

        Thread.sleep(500);
    }

    static class NumberPrinter implements Runnable {

        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                LOCK.lock();
                try {
                    int index = STEP % CHAR_ARR.length;
                    if (index % 2 != 0) {
                        numberCondition.await();
                    } else {
                        System.out.print(CHAR_ARR[index]);
                        STEP++;
                        charCondition.signal();
                    }
                } finally {
                    LOCK.unlock();
                }
            }
        }
    }

    static class CharPrinter implements Runnable {

        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                LOCK.lock();
                try {
                    int index = STEP % CHAR_ARR.length;
                    if (index % 2 == 0) {
                        charCondition.await();
                    } else {
                        System.out.print(CHAR_ARR[index]);
                        if (index == CHAR_ARR.length - 1) {
                            System.out.println();
                        }

                        STEP++;
                        numberCondition.signal();
                    }
                } finally {
                    LOCK.unlock();
                }
            }
        }
    }

}
