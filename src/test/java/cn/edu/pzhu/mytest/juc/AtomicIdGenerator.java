package cn.edu.pzhu.mytest.juc;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * atomic生成器
 *
 * @author zhangcz
 * @since 20220228
 */
@Service
public class AtomicIdGenerator implements IdGenerator {
    private AtomicInteger atomicInteger = new AtomicInteger(1);

    @Override
    public Integer getId() {
        return atomicInteger.getAndIncrement();
    }
}
