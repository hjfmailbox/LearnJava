package testClass;

public class Demo01 {

    {
        System.out.println("代码块");
    }

    static {
        System.out.println("静态代码块");
    }

    public Demo01() {
        System.out.println("构造函数");
    }

    public static void main(String[] args) {
        Demo01 demo01 = new Demo01();
    }
}
