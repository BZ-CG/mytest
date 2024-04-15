package cn.edu.pzhu.mytest.bean;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Test {

    @org.junit.jupiter.api.Test
    void contextLoads() {
        Son son = new Son("son1");
        System.out.println(son);

        Person person = new Person("tom");
        person.testMethod();
    }

}
