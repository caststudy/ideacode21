import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy implements InvocationHandler {
    Object target;

    public DynamicProxy(Object target) {
        this.target = target;

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        System.out.println("调用[" + method + "]前,函数参数：" + (args != null ? args.length : null));
        result = method.invoke(target, args);
        System.out.println("调用[" + method + "]后,函数返回结果:" + result);
        return result;
    }


}

class test {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Foo f = new FooImpl();
        f.test();
        f.test(1, "aa");
        System.out.println("=========================");
        DynamicProxy handler = new DynamicProxy(f);
        Foo f1 = (Foo) Proxy.newProxyInstance(f.getClass().getClassLoader(), f.getClass().getInterfaces(), handler);
        f1.test();
        System.out.println("-------------------------------");
        f1.test(1, "aa");
    }
}
