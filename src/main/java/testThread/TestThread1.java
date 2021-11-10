package testThread;

public class TestThread1 {
    public static void main(String[] args) {
        MyThread  myThread = new MyThread();
        myThread.start();

        for (int i = 0; i < 100; i++) {
            System.out.println("主线程"+i);
        }
    }
}
