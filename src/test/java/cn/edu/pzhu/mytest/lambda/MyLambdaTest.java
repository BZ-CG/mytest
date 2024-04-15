package cn.edu.pzhu.mytest.lambda;

import java.util.function.Consumer;

/**
 * lambda测试
 *
 * @author zhangcz
 * @since 20240125
 */
public class MyLambdaTest {

    public static void main(String[] args) {
        System.setProperty("jdk.internal.lambda.dumpProxyClasses", ".");
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("CG");
    }
}
