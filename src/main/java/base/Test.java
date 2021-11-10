package base;

public class Test {
    public static void main(String[] args) {
        testFloat();
    }

    public static void testFloat(){
        float f1 = 123456789f;
        float f2 = f1+1;
        System.out.println(f1==f2);
    }

    public static void testStringEqual(){
        String s1 = new String("Hello world");
    }

    /**
     * @author hjf
     * @version
     */
    public static void testOperator(){
        long a = 123456789L;
        int b = 123456789;
        short c = 10;
        byte d = 8;

        System.out.println(a+b+c+d);
        System.out.println(b+c+d);
        System.out.println(c+d);
    }
}
