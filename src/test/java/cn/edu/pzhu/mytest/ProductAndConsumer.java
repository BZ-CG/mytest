package cn.edu.pzhu.mytest;

import lombok.SneakyThrows;

import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 生产者消费者demo
 *
 * @author zhangcz
 * @since 20240125
 */
public class ProductAndConsumer {


    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        new Thread(new Product(vector, 10)).start();
        new Thread(new Consumer(vector)).start();
    }


    static class Product implements Runnable {
        private Vector<Integer> vector;
        private int size;

        public Product(Vector<Integer> vector, int size) {
            this.vector = vector;
            this.size = size;
        }


        @SneakyThrows
        @Override
        public void run() {
            for (; ; ) {

                synchronized (vector) {
                    while (vector.size() == size) {
                        System.out.println("vector 已满暂停消生产");
                        vector.wait();
                    }
                }

                synchronized (vector) {
                    ThreadLocalRandom random = ThreadLocalRandom.current();
                    int value = random.nextInt(10);
                    vector.add(value);
                    System.out.println(String.format("生产：%s,开始唤醒消费者", value));
                    vector.notifyAll();
                    Thread.sleep(1000);
                }
            }
        }
    }


    static class Consumer implements Runnable {
        private Vector<Integer> vector;

        public Consumer(Vector<Integer> vector) {
            this.vector = vector;
        }


        @Override
        @SneakyThrows
        public void run() {
            for (; ; ) {
                synchronized (vector) {
                    while (vector.isEmpty()) {
                        System.out.println("vector 为空暂停消费");
                        vector.wait();
                    }
                }

                synchronized (vector) {
                    Integer value = vector.remove(0);
                    System.out.println(String.format("消费:%s, 开始唤醒生产者", value));
                    vector.notifyAll();
                    Thread.sleep(1000);
                }
            }
        }
    }

}
