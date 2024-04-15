package cn.edu.pzhu.mytest.bean;

import lombok.Data;

/**
 * person
 *
 * @author zhangcz
 * @since 20200917
 */
@Data
public class Person {
    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
        System.out.println("Person constructor.");
    }

    protected void testMethod() {

    }

    private void testPrivate() {
        System.out.println("Person private");
    }

    public void testPublic() {
        System.out.println("Person public");
    }

}
