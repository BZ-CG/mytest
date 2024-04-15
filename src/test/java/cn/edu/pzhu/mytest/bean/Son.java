package cn.edu.pzhu.mytest.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * son
 *
 * @author zhangcz
 * @since 20200917
 */
@Data
public class Son extends Person {

    private final Integer age;

    private BigDecimal bigDecimal;

    public Son(String name) {
        super(name);
        System.out.println("Son constructor.");
        testMethod();
        age = 1;
    }

//    private void testPrivate() {
//        System.out.println("Son private");
//    }

    @Override
    protected void testMethod() {
        super.testMethod();
    }

}
