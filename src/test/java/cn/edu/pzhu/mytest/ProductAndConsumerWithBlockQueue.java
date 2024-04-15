package cn.edu.pzhu.mytest;

import lombok.SneakyThrows;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 生产者消费者demo
 *
 * @author zhangcz
 * @since 20240125
 */
public class ProductAndConsumerWithBlockQueue {

    public static void main(String[] args) {
        Container container = new Container();
        new Thread(new Product(container), "生产者1号").start();
//        new Thread(new Product(container), "生产者2号").start();
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

        private LinkedBlockingQueue<Integer> list = new LinkedBlockingQueue<>();

        private Integer MAX_SIZE = 10;

        public Container() {
        }


        @SneakyThrows
        public void product(int value) {
            list.put(value);
            System.out.println(String.format("【%s】生产：%s,开始唤醒消费者", Thread.currentThread().getName(), value));

        }

        @SneakyThrows
        public int consumer() {
            Integer value = list.take();
            System.out.println(String.format("【%s】消费:%s, 开始唤醒生产者", Thread.currentThread().getName(), value));
            return value;
        }
    }
}

