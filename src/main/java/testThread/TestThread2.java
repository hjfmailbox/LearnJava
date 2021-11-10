package testThread;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class TestThread2 extends Thread {
    private String url;
    private String name;

    public TestThread2(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public static void main(String[] args) {
        TestThread2 t1 = new TestThread2("https://fluorine.cf/images/001.jpg", "1.jpg");
        TestThread2 t2 = new TestThread2("https://fluorine.cf/images/002.jpg", "2.jpg");
        TestThread2 t3 = new TestThread2("https://fluorine.cf/images/003.jpg", "3.jpg");

        t1.start();
        t2.start();
        t3.start();
    }

    @Override
    public void run() {
        Downloader downloader = new Downloader();
        downloader.Download(this.url, this.name);
        System.out.println(this.name + "下载完成");
    }
}

class Downloader {
    public void Download(String url, String name) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常，下载失败");
        }
    }
}
