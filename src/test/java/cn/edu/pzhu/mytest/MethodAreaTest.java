package cn.edu.pzhu.mytest;

/**
 * 测试
 *
 * @author zhangcz
 * @since 20240125
 */
public class MethodAreaTest {

    public static void main(String[] args) {
        Order order = new Order();

    }

}

class Order {
    public static int count = 1;

    public static void hello() {
        System.out.println("Order.hello");
    }
}
