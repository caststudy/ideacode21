import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.AopConfigException;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.annotation.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface MyAnno {
}

class FooImplS {
    public void test() {
        System.out.println("执行 FooSImplS.test() ....");
    }

    @MyAnno
    public String test(int a, String s) {
        System.out.println("执行 FooSImplS.test(a,b)");
        return a + "----" + s;
    }
}

public class SpringProxy implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        MyAnno annotation = getAnnotation(method);
        if (annotation == null) {
            return invocation.proceed();
        }
        System.out.println("调用[" + method + "]前,函数参数：" + invocation.getArguments());
        Object proceed = invocation.proceed();
        System.out.println("调用[" + method + "]后,函数返回结果:" + proceed);
        return proceed;
    }

    private MyAnno getAnnotation(Method method) {
        if (method.isAnnotationPresent(MyAnno.class)) {
            return method.getAnnotation(MyAnno.class);
        }
        return null;
    }

}

class test3 {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, AopConfigException {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new FooImplS());
        proxyFactory.addAdvice(new SpringProxy());
        FooImplS f = (FooImplS) proxyFactory.getProxy();
        f.test();
        f.test(1, "aaa");
    }
}
