package cn.edu.pzhu.mytest.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * person代理
 *
 * @author zhangcz
 * @since 20201028
 */
public class PersonCgLibProxy implements MethodInterceptor {

    private Object target;

    public PersonCgLibProxy(Object object) {
        this.target = object;

    }

    public Object getInstance() {
        // 工具类
        Enhancer enhancer = new Enhancer();
        // 设置父类
        enhancer.setSuperclass(target.getClass());
        // 设置回调函数
        enhancer.setCallback(this);
        // 创建子类
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("方法执行前----");
        Object result = method.invoke(target, objects);
        System.out.println("方法执行后----");

        return result;
    }
}
