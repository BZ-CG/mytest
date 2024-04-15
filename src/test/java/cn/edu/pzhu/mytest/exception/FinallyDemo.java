package cn.edu.pzhu.mytest.exception;

/**
 * finally demo
 *
 * @author zhangcz
 * @since 20240125
 */
public class FinallyDemo {

    public static void main(String[] args) {
        System.out.println(getInt());
    }

    public static int getInt() {
        int i = 0;
        try {
            i = 1;
            return i;
        } finally {
            i = 3;
            return i;
        }
    }
}
