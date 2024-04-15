package cn.edu.pzhu.mytest.proxy;

import org.springframework.stereotype.Service;

/**
 * 实现
 *
 * @author zhangcz
 * @since 20201028
 */
@Service
public class SportServiceImpl implements SportService {
    @Override
    public void playBasketball() {
        System.out.println("打球");
    }

    @Override
    public void playGames() {
        System.out.println("玩游戏");
    }

    @Override
    public void running() {
        System.out.println("跑步");
    }
}
