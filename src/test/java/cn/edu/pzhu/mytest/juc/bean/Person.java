package cn.edu.pzhu.mytest.juc.bean;

/**
 * person
 *
 * @author zhangcz
 * @since 20220228
 */
public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "name:" + name;
    }
}
