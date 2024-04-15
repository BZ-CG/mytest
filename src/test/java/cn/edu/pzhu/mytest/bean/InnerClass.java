package cn.edu.pzhu.mytest.bean;

/**
 * 内部类
 *
 * @author zhangcz
 * @since 20220228
 */
public class InnerClass {

    private static String NAME = "Father Class Name";

    public static String PUBLIC_NAME = "Father Class Public Name";

    public String NO_STATIC_NAME = "Father No Static Name";

    public void noneStaticMethod() {

    }

    public static void staticMethod() {
        System.out.println("staticMethod");

        class InnerInStaticMethod {
            public String name = NAME;

            //            public String name2 = NO_STATIC_NAME;
            public void display() {
//                noneStaticMethod();
                staticMethod();
            }
        }
    }

    public void display() {
        System.out.println("displayMethod");

        class InnerInNoStaticMethod {
            public String name = NAME;

            public void display() {
                staticMethod();
                noneStaticMethod();
            }
        }
    }

    class InnerTest {

        public String name = NO_STATIC_NAME;
    }

    static class StaticInnerClass {
        public String name = NAME;

    }

    public void test() {
        InnerClass innerClass = new InnerClass();
        InnerClass.InnerTest innerTest = new InnerClass.InnerTest();
        StaticInnerClass staticInnerClass = new InnerClass.StaticInnerClass();
    }
}
