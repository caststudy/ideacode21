public class StaticProxy implements Foo {
    Foo foo;

    public StaticProxy(Foo foo) {
        this.foo = foo;
    }

    @Override
    public void test() {
        System.out.println("调用[ test() ]前");
        foo.test();
        System.out.println("调用[ test() ]后");
    }

    @Override
    public String test(int i, String aa) {
        System.out.println("调用[ test(i,aa) ]前,参数:" + i + aa);
        String r = foo.test(i, aa);
        System.out.println("调用[ test(i,aa) ]后,返回结果:" + r);
        return r;
    }
}


class tests {
    public static void main(String[] args) {

        Foo f = new FooImpl();
        f.test();
        f.test(1, "aa");
        System.out.println("=========================");
        StaticProxy f1 = new StaticProxy(f);

        f1.test();
        System.out.println("-------------------------------");
        f1.test(1, "aa");
    }
}
