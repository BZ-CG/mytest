package cn.edu.pzhu.mytest.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 运动handler
 *
 * @author zhangcz
 * @since 20201028
 */
@Component
public class SportInvocationHandler implements InvocationHandler {

    @Autowired
    private SportService sportService;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("playGames")) {
            throw new RuntimeException("不能玩游戏哦");
        }

        return method.invoke(sportService, args);
    }
}
