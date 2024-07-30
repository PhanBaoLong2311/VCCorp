### Lock
- Lock là một cơ chế đồng bộ thread, đảm bảo rằng chỉ một luồng truy cập tài nguyên chung tại một thời điểm, ngăn chặn lỗi và bảo vệ tính nhất quán của dữ liệu.
### Lock Reentrance
Lock reentrance là khả năng một luồng có thể lấy lại một khóa mà nó đã nắm giữ mà không bị khóa lại, cho phép cùng một luồng có thể vào lại các khối mã đồng bộ nhiều lần.
```java
public class ReentrantLockExample {
    private ReentrantLock lock = new ReentrantLock();

    public void DoSomeThing() {
        lock.lock(); // Thu được khóa lần đầu
        try {
            System.out.println("Doing something");
            DoSomeThingElse(); // Gọi một phương thức khác cũng thu được cùng khóa
        } finally {
            lock.unlock(); // Giải phóng khóa lần thứ nhất
        }
    }

    public void DoSomeThingElse() {
        lock.lock(); // Thu được khóa lần thứ hai
        try {
            System.out.println("Doing something else");
        } finally {
            lock.unlock(); // Giải phóng khóa lần thứ hai
        }
    }

    public static void main(String[] args) {
        ReentrantLockExample example = new ReentrantLockExample();
        example.DoSomeThing();
    }
}
```

### Race condition
Race conditions (Tình huống tương tranh) là khi có hai hay nhiều Thread cùng chia sẻ dữ liệu, hay đơn giản là cùng đọc và ghi vào một vùng dữ liệu, kết quả của việc thực thi multiple threads có thể thay đổi phụ thuộc vào thứ tự thực thi các thread.  
- Cách khắc phục:
  Để tránh xung đột tài nguyên khi chạy multiple-thread, Java đưa ra một cách giải quyết là dùng từ khoá synchronized cho phương thức hay đoạn code có chứa critical section.
```java
public class TwoSums {
    private int sum1 = 0;
    private int sum2 = 0;

    public void add(int val1, int val2) {
        synchronized(this) {
            this.sum1 += val1;
        }
        synchronized(this) {
            this.sum2 += val2;
        }
    }
}
```
### Atomic Integer
- Đây là một lớp Java cung cấp các phép toán nguyên tử (atomic) trên một giá trị số nguyên (integer). Điều này có nghĩa là các phép toán trên giá trị của AtomicInteger sẽ được thực hiện một cách an toàn trong môi trường đa luồng mà không cần sử dụng các cơ chế đồng bộ hóa phức tạp như synchronized.  
```java
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleAtomicExample {
    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {
        // Tạo 1 luồng để tăng giá trị của count
        new Thread(() -> {
            count.incrementAndGet(); // Tăng count lên 1
        }).start();

        // Đợi 1s để luồng hoàn thành
        try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Count: " + count.get()); // Sẽ in ra 1
    }
}
```

### ConcurrentHashMap
- ConcurrentHashMap là một cấu trúc dữ liệu cho phép nhiều luồng truy cập và cập nhật bản đồ đồng thời mà không cần phải sử dụng đồng bộ hóa phức tạp.
```java
public class ConcurrentHashMapExample {
    public static void main(String[] args) {
        // Tạo Concurrent HashMap
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        // Thêm giá trị sẵn vào bản đồ
        map.put("key1", 1);
        map.put("key2", 2);

        // Tạo luồng để thêm giá trị vào bản đồ
        Thread thread1 = new Thread(() -> {
            map.put("key3", 3);
            System.out.println("Thread1 added key3");
        });

        // Tạo một luồng khác để thêm giá trị vào bản đồ
        Thread thread2 = new Thread(() -> {
            map.put("key4", 4);
            System.out.println("Thread2 added key4");
        });

        // Bắt đầu các luồng
        thread1.start();
        thread2.start();

        // Đợi 1s để luồng hoàn thành
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Map: " + map);
    }
}
```

### Virtual Threads
- Virtual thread (luồng ảo) là thread được tạo và quản lý bởi một platform thay vì được tạo và quản lý bởi hệ điều hành như thread thông thường, tốn ít tài nguyên, dễ quản lý hơn so với Threads thông thường
```java
public class VirtualThreadExample {
    public static void main(String[] args) {
        // Tạo executor với virtual threads
        var executor = Executors.newVirtualThreadExecutor();
        
        // Gửi 5 task vào executor
        for (int i = 0; i < 5; i++) {
            int id = i;
            executor.submit(() -> {
                System.out.println("Task " + id + " is running on " + Thread.currentThread().getName());
            });
        }
        // Đóng executor
        executor.shutdown();
    }
}
```