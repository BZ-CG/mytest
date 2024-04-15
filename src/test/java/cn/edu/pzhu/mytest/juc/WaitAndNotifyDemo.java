package cn.edu.pzhu.mytest.juc;

import lombok.SneakyThrows;

/**
 * lock support
 *
 * @author zhangcz
 * @since 20220228
 */
public class WaitAndNotifyDemo {
    @SneakyThrows
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        synchronized (myThread) {
            myThread.start();
            System.out.println("before wait");
            // 阻塞主线程
            myThread.wait();
            System.out.println("after wait");
        }
    }

}

class MyThread extends Thread {
    @Override
    public void run() {
        synchronized (this) {
            System.out.println("before notify");
            notify();
            System.out.println("after notify");
        }
    }
}
