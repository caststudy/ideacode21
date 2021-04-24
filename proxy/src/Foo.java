interface Foo {
    void test();

    String test(int i, String aa);
}

class FooImpl implements Foo {

    @Override
    public void test() {
        System.out.println("执行 FooSImpl.test() ....");
    }

    public String test(int a, String s) {
        System.out.println("执行 FooSImpl.test(a,b)");
        return a + "----" + s;
    }
}


