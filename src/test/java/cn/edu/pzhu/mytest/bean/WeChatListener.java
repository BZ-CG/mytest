package cn.edu.pzhu.mytest.bean;

import com.google.common.eventbus.Subscribe;

/**
 * 微信监听器
 *
 * @author zhangcz
 * @since 20201028
 */
public class WeChatListener {


    @Subscribe
    public void execute(String message) throws InterruptedException {
        Thread.sleep(5000);

        System.out.println("----");
        System.out.println("微信接收到消息:" + message);
        System.out.println("----");


    }

}
