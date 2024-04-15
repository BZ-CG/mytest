package cn.edu.pzhu.mytest.bean;

import com.google.common.eventbus.Subscribe;

/**
 * QQ监听器
 *
 * @author zhangcz
 * @since 20201028
 */
public class QQChatListener {


    @Subscribe
    public void execute(String message) {
        System.out.println("++++");
        System.out.println("QQ接收到消息:" + message);
        int a = 1 / 0;
        System.out.println("++++");
    }

}
