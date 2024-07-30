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
        ThreadTest a = new ThreadTest();
        Thread ThreadTask = new Thread(a);
        ThreadTask.start();
        System.out.println("Đang chạy luồng.");
    }
}