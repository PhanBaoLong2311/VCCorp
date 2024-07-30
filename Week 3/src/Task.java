import java.util.concurrent.*;

public class Task implements Runnable{
    private int id;

    public Task(int id){
        this.id = id;
    }

    @Override
    public void run(){
        try{
            System.out.println("Đang thực hiện Task");
            Thread.sleep(1000);
        } catch(InterruptedException e){
            System.out.println("Task gặp lỗi trong quá trình thực thi");
            e.printStackTrace();
        }
    }
}

