package cn.edu.pzhu.mytest;

import lombok.SneakyThrows;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者demo
 *
 * @author zhangcz
 * @since 20240125
 */
public class ProductAndConsumerWithLock {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Container container = new Container(lock);
        new Thread(new Product(container), "生产者1号").start();
        new Thread(new Product(container), "生产者2号").start();
        new Thread(new Consumer(container), "消费者1号").start();
    }

    static class Product implements Runnable {

        private Container container;

        public Product(Container container) {
            this.container = container;
        }


        @SneakyThrows
        @Override
        public void run() {
            for (; ; ) {
                ThreadLocalRandom random = ThreadLocalRandom.current();
                int value = random.nextInt(10);
                container.product(value);
//                Thread.sleep(1000);
            }
        }
    }


    static class Consumer implements Runnable {

        private Container container;

        public Consumer(Container container) {
            this.container = container;
        }

        @Override
        @SneakyThrows
        public void run() {
            for (; ; ) {
                container.consumer();
            }
        }
    }


    static class Container {

        private LinkedList<Integer> list = new LinkedList<>();

        private Lock lock;

        private Condition notFull;

        private Condition notEmpty;

        private Integer MAX_SIZE = 10;

        public Container(Lock lock) {
            this.lock = lock;
            this.notFull = lock.newCondition();
            this.notEmpty = lock.newCondition();
        }


        @SneakyThrows
        public void product(int value) {
            try {
                lock.lockInterruptibly();
                while (list.size() == MAX_SIZE) {
                    System.out.println("vector 已满暂停消生产");
                    notFull.await();
                }

                list.add(value);
                System.out.println(String.format("【%s】生产：%s,开始唤醒消费者", Thread.currentThread().getName(), value));
                notEmpty.signal();
            } finally {
                lock.unlock();
            }
        }

        @SneakyThrows
        public int consumer() {
            try {
                lock.lockInterruptibly();
                while (list.isEmpty()) {
                    System.out.println("vector 为空暂停消费");
                    notEmpty.await();
                }

                Integer value = list.removeFirst();
                System.out.println(String.format("【%s】消费:%s, 开始唤醒生产者", Thread.currentThread().getName(), value));
                notFull.signal();
                Thread.sleep(1000);
                return value;
            } finally {
                try {
                    lock.unlock();
                } catch (Exception e) {
//                    throw new RuntimeException(e);
                    System.out.println(" unlock Exception ");
                }
            }
        }
    }
}

