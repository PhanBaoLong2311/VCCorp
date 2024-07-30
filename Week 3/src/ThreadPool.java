import java.util.concurrent.*;

public class ThreadPool{
    public static void main(String[] args){
        int corePoolSize = 2;
        int maxPoolSize = 4;
        long keepAliveTime = 10;
        TimeUnit unit = TimeUnit.SECONDS;

        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(20);
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();

        ThreadPoolExecutor no1 = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, unit, queue, handler);

        for (int i = 0; i < 10; ++i){
            no1.execute(new Task(i));
        }
    }
}
