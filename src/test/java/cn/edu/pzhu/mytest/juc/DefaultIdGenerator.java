package cn.edu.pzhu.mytest.juc;

import org.springframework.stereotype.Service;

/**
 * 默认的生成器
 *
 * @author zhangcz
 * @since 20220228
 */
@Service
public class DefaultIdGenerator implements IdGenerator {
    private int id = 1;

    @Override
    public Integer getId() {
        return id++;
    }
}
