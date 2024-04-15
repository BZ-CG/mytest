package cn.edu.pzhu.mytest.juc;

import cn.edu.pzhu.mytest.juc.bean.Person;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * juc test
 *
 * @author zhangcz
 * @since 20220228
 */
@SpringBootTest
public class AtomicTest {

    @Autowired
    private DefaultIdGenerator defaultIdGenerator;

    @Autowired
    private AtomicIdGenerator atomicIdGenerator;


    @Test
    @SneakyThrows
    public void testUnsafe() {
        Person person = new Person("zhangchao");
        Field name = person.getClass().getDeclaredField("name");
        name.setAccessible(true);
        name.set(person, "Autumn");

        System.out.println(person);

        Unsafe unsafe = getUnsafe();
        long fieldOffset = unsafe.objectFieldOffset(name);
        unsafe.putObject(person, fieldOffset, "zhuchengjian");

        System.out.println(person);
    }


    @SneakyThrows
    private Unsafe getUnsafe() {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        return (Unsafe) theUnsafe.get(null);
    }

    @Test
    public void testAtomicStampedReference() {
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference(1, 1);
        new Thread(() -> {
            int value = atomicStampedReference.getReference();
            int stamp = atomicStampedReference.getStamp();
            sleep(100);
            System.out.printf("currentStamp:%s,actualStamp:%s\n", stamp, atomicStampedReference.getStamp());
            System.out.printf("%s casResult:%s\n", Thread.currentThread().getName(), atomicStampedReference.compareAndSet(value, value + 1, stamp, stamp + 1));
        }, "thread1").start();


        new Thread(() -> {
            int value = atomicStampedReference.getReference();
            int stamp = atomicStampedReference.getStamp();
            System.out.printf("%s casResult:%s\n", Thread.currentThread().getName(), atomicStampedReference.compareAndSet(value, value + 1, stamp, stamp + 1));

            value = atomicStampedReference.getReference();
            stamp = atomicStampedReference.getStamp();
            System.out.printf("%s casResult:%s\n", Thread.currentThread().getName(), atomicStampedReference.compareAndSet(value, 1, stamp, stamp + 1));
        }, "thread2").start();

        sleep(100);
    }

    @Test
    public void testABA() {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        new Thread(() -> {
            int value = atomicInteger.get();
            sleep(100);
            System.out.printf("%s casResult:%s\n", Thread.currentThread().getName(), atomicInteger.compareAndSet(value, value + 1));
        }, "thread1").start();


        new Thread(() -> {
            int value = atomicInteger.get();
            System.out.printf("%s casResult:%s\n", Thread.currentThread().getName(), atomicInteger.compareAndSet(value, value + 1));
            System.out.printf("%s casResult:%s\n", Thread.currentThread().getName(), atomicInteger.compareAndSet(2, 1));
        }, "thread2").start();

        sleep(100);
    }

    @SneakyThrows
    private void sleep(long time) {
        Thread.sleep(time);
    }

    @Test
    public void testCAS() {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        System.out.println("casResult: " + atomicInteger.compareAndSet(1, 2));
        System.out.println("casResult: " + atomicInteger.compareAndSet(3, 1));
        System.out.println("casResult: " + atomicInteger.compareAndSet(2, 1));
        System.out.println(atomicInteger.get());
    }

    @Test
    public void testIdGenerator() {
        Set<Integer> defaultResult = getIdResultSet(defaultIdGenerator);
        printResult(defaultResult);

        Set<Integer> atomicResult = getIdResultSet(atomicIdGenerator);
        printResult(atomicResult);

    }

    public void printResult(Set<Integer> idSet) {
        System.out.printf("size:%s, sum:%s\n", idSet.size(), idSet.stream().reduce(Integer::sum));
    }

    @SneakyThrows
    public Set<Integer> getIdResultSet(IdGenerator idGenerator) {
        Set<Integer> idSet = new CopyOnWriteArraySet<>();
        ExecutorService executorService = Executors.newFixedThreadPool(200);
        List<Future<?>> futureList = new ArrayList<>();
        for (int i = 0; i < 2000; i++) {
            futureList.add(executorService.submit(() -> {
                idSet.add(idGenerator.getId());
            }));
        }

        for (Future<?> future : futureList) {
            future.get();
        }

        return idSet;
    }

    @Test
    public void testAtomicInteger() {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        System.out.println(atomicInteger.getAndIncrement());
        System.out.println(atomicInteger.getAndIncrement());

    }
}
