import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ThreadTest implements Runnable{


    @Override
    public void run(){

        try {
                System.out.println("Luồng đang chạy.");
                Thread.sleep(1000);
        } catch (InterruptedException e){
            System.out.println("Luồng gặp lỗi.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        ThreadTest a = new ThreadTest(); // Tạo một đối tượng ThreadTest
        Thread ThreadTask = new Thread(a); // Tạo một đối tượng Thread, truyền đối tượng ThreadTest vào
        ThreadTask.start(); // Bắt đầu luồng mới, thực thi phương thức run của đối tượng ThreadTest
        System.out.println("Đang chạy luồng.");
    }
}