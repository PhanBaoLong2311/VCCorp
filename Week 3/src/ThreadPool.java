import java.util.concurrent.*;

public class ThreadPool {
    public static void main(String[] args) {
        int corePoolSize = 2; // Số luồng cơ bản trong
        int maxPoolSize = 4; // Số luồng tối đa
        long keepAliveTime = 1; // Thời gian luồng dư thừa sẽ chờ trước khi bị kết thúc (10s)
        TimeUnit unit = TimeUnit.SECONDS;

        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(20); // Tạo hàng đợi gồm 20 tasks
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy(); // Chiến lược xử lý khi hàng đợi đã đầy, CallerRunPolicy sẽ chạy nhiệm vụ bằng luồng gọi nếu không có sẵn luôồng

        ThreadPoolExecutor no1 = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, unit, queue, handler);

        for (int i = 0; i < 20; ++i) {
            no1.execute(new Task(i));
            System.out.println("submit" + i);
        }
    }
}