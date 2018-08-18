package proxydemo.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.lang.reflect.Method;
public class ProxyFactory {
    //maintain an target object
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    //Generate proxy object for the target object
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(), new InvocationHandler(){
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
                System.out.println("Begin transaction");
                Object returnObject = method.invoke(target,args);
                System.out.println("End transaction");
                return returnObject;
            }

        });
    }
}
