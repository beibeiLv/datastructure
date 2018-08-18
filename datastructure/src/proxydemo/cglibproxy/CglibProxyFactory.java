package proxydemo.cglibproxy;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxyFactory implements MethodInterceptor {
    private  Object target;

    public CglibProxyFactory(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        System.out.println("begin transaction");
        Object returnObject = method.invoke(target,args);
        System.out.println("end transaction");
        return  returnObject;

    }

    // create an proxy object for the target object

    public Object getProxyInstance() {
        //Util class
        Enhancer en = new Enhancer();
        //Set super class
        en.setSuperclass(target.getClass());
        //Set call back
        en.setCallback(this);
        //create sub class
        return en.create();
    }
}
