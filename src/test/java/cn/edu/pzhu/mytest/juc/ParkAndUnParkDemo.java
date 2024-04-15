package cn.edu.pzhu.mytest.juc;

import lombok.SneakyThrows;

import java.util.concurrent.locks.LockSupport;

/**
 * park unPark
 *
 * @author zhangcz
 * @since 20220228
 */
public class ParkAndUnParkDemo {

    @SneakyThrows
    public static void main(String[] args) {
        MyThreadWithPark myThreadWithPark = new MyThreadWithPark(Thread.currentThread());
        myThreadWithPark.start();

//        Thread.sleep(1000);
        System.out.println("before park");
        LockSupport.park();
        System.out.println("after park");
    }
}

class MyThreadWithPark extends Thread {
    private Object object;

    public MyThreadWithPark(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        System.out.println("before unpark");
        LockSupport.unpark((Thread) object);
        System.out.println("after unpark");
    }
}
