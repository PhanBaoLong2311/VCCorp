import java.util.concurrent.*;

public class Task implements Runnable{
    private int id; // Định danh các tasks

    public Task(int id){
        this.id = id;
    }

    @Override
    public void run(){
        try{
            System.out.println("Đang thực hiện Task" + id);
            Thread.sleep(5000);
        } catch(InterruptedException e){
            System.out.println("Task gặp lỗi trong quá trình thực thi");
            e.printStackTrace();
        }
        System.out.println("Chay xong " + id);
    }
}

