## Spark

- Spark là một nền tảng phổ biến cho việc xử lý dữ liệu, đã dần thay thế khung MapReduce truyền thống.
- Ưu điểm của Spark so với MapReduce:
  + Công việc lặp đi lặp lại (Iterative jobs): Spark lưu trữ dữ liệu trung gian trong bộ nhớ thay vì ghi ra đĩa như MapReduce, giúp giảm thiểu thời gian I/O khi thực hiện các job lặp đi lặp lại.
  + Hỗ trợ phân tích tương tác (Analysis): Bằng cách cache dữ liệu trong bộ nhớ, Spark giảm thiểu thời gian phản hồi cho các truy vấn lặp lại trên cùng một tập dữ liệu, giúp việc phân tích dữ liệu trở nên linh hoạt và nhanh chóng hơn so với MapReduce.
  + Cung cấp nhiều API: Spark cung cấp một loạt các API phong phú và dễ sử dụng cho nhiều ngôn ngữ lập trình như Scala, Java, Python, và R.

### Architecture (Kiến trúc)
- Spark sử dụng kiến trúc master - slave, trong đó master điều phối và phân phối công việc cho các quá trình slave. Hai quá trình này trong Spark là:
  + Driver
  + Executor

1. Driver
- Driver (master) chịu trách nhiệm duy trì trạng thái tổng thể của ứng dụng Spark, phản hồi chương trình hoặc đầu vào của người dùng, và phân tích, phân phối, cũng như lên lịch công việc cho các quá trình executor.
- Driver là trung tâm của ứng dụng Spark và duy trì tất cả các thông tin liên quan đến ứng dụng trong suốt vòng đời của ứng dụng.

2. Executor
- Executor là quá trình thực thi các mã do Driver giao phó. 
- Báo cáo trạng thái của quá trình tính toán trên executor đó trở lại cho driver. 
- Executors cũng là nơi thực hiện các tác vụ tính toán thực tế và lưu trữ dữ liệu tạm thời.

### Cluster Manager
- Driver của Spark thương lượng tài nguyên với cluster manager để khởi động các quá trình executor. 

### Deploy Mode: 
Xác định vị trí mà Driver Program sẽ chạy
- Client Mode:
  + Driver chạy trên máy local của người dùng, trong khi các executor chạy trên các node trong cụm.
- Cluster Mode:
  + Driver chạy trên một node trong cụm.

### Master Mode: 
Xác định loại cluster manager sẽ được sử dụng để quản lý tài nguyên và điều hành các ứng dụng Spark.

- Standalone: Driver
- Local: Toàn bộ ứng dụng chạy trên một máy duy nhất
- YARN
- Mesos
- Kubernetes

### Vòng đời ứng dụng của Spark
1. Nộp ứng dụng Spark:
  - Người dùng nộp ứng dụng Spark tới cụm (cluster) bằng cách chạy lệnh spark-submit trong cửa sổ terminal.
  - Lệnh này khởi động một tiến trình nói chuyện với Cluster Manager. Nếu sử dụng YARN, tiến trình này kết nối với daemon Resource Manager (RM).
  - Nếu công việc được chấp nhận, RM sẽ tạo ra tiến trình driver Spark trên một trong các máy trong cụm.
  2. Khởi chạy tiến trình driver:
  - Khi tiến trình driver bắt đầu chạy, nó sẽ thực thi mã của người dùng. Mã này cần thiết lập một SparkSession, giúp cấu hình Spark cluster.
  - Tiến trình driver và các tiến trình executor được gọi chung là Spark cluster.
  - SparkSession : điểm truy cập duy nhất để tương tác với chức năng Spark cơ bản.
  - SparkSession sẽ nói chuyện với daemon Cluster Manager (trong trường hợp này là RM) để khởi chạy các tiến trình Spark executor trên các nút công nhân (worker nodes).
  3. Khởi chạy các Executor:
  - Cluster manager sẽ chạy các executer trên các node khác nhau, gửi lại vị trí từng node cho driver để driver điều khiển các executer.
  4. Thực thi công việc Spark:
  - Khi Spark cluster sẵn sàng, driver sẽ phân công các tasks cho các tiến trình executor và bắt đầu thực thi công việc.

### Spark API
- Spark cung cấp các API và trừu tượng dữ liệu (data abstractions) giúp nâng cao trải nghiệm của nhà phát triển.
- Trừu tượng hóa cấp thấp được gọi là RDD (Resilient Distributed Datasets)

### RDD
- RDD (Resilient Distributed Dataset) là một tập hợp các đối tượng bản ghi chỉ đọc (không thể thay đổi), được phân vùng trên toàn cluster và có thể được vận hành song song.
- Không khuyến khích làm việc trực tiếp với chúng trừ khi mục đích là để kiểm soát chặt chẽ.
- Khi sử dụng RDD, phải đánh đổi các chức năng tối ưu hóa và chức năng được xây dựng sẵn đi kèm với API có cấu trúc như DataFrames và Datasets.
- Các thuộc tính:  

a. Resilient (khả năng chịu lỗi): 
- RDD có khả năng chịu lỗi nhờ sử dụng RDD lineage graph 

b. Distributed (phân tán):

- Dữ liệu tạo nên RDD được trải trên một cluster máy.

c. Datasets (tập dữ liệu):

- Dữ liệu bên ngoài có thể được nạp từ nhiều nguồn khác nhau như file JSON, file CSV, văn bản, database.

### Tạo RDD
1. Tạo RDD từ các bộ sưu tập cục bộ:
- Sử dụng phương thức parallelize() của SparkContext
```java
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.List;

public class SparkExample {
    public static void main(String[] args) {
        // Tạo SparkContext
        JavaSparkContext sc = new JavaSparkContext("local", "SparkExample");

        // Tạo danh sách cục bộ
        List<String> brands = Arrays.asList("Tesla", "Ford", "GM");

        // Tạo RDD từ danh sách cục bộ
        JavaRDD<String> brandsRDD = sc.parallelize(brands);
    }
}
```

2. Tạo RDD từ các nguồn dữ liệu:
-  Ta có thể tạo ra RDD từ các file văn bản
```java
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.SparkConf;

public class SimpleTextFileRDDExample {
    public static void main(String[] args) {
        // Cấu hình SparkConf
        SparkConf conf = new SparkConf().setAppName("SimpleTextFileRDDExample").setMaster("local");

        // Tạo JavaSparkContext từ SparkConf
        JavaSparkContext sc = new JavaSparkContext(conf);

        // Đường dẫn tới tệp văn bản
        String filePath = "path_to_your_text_file.txt";

        // Tạo RDD từ tệp văn bản
        JavaRDD<String> textFileRDD = sc.textFile(filePath);

        // Đóng JavaSparkContext
        sc.close();
    }
}
```

3. Tạo RDD từ data frame và data set:
- Tạo RDD từ data frame
```java
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.api.java.JavaRDD;

public class SparkExample {
    public static void main(String[] args) {
        // Tạo SparkSession
        SparkSession spark = SparkSession.builder()
            .appName("SparkExample")
            .master("local")
            .getOrCreate();

        // Tạo DataFrame
        Dataset<Row> dataFrame = spark.range(100).toDF();

        // Tạo RDD từ DataFrame
        JavaRDD<Row> rdd = dataFrame.javaRDD();

        // Kiểm tra số lượng record trong RDD
        long count = rdd.count();
        System.out.println("Number of records in RDD: " + count);

        // Đóng SparkSession
        spark.stop();
    }
}
```

- Tính bất biến của RDD:
  + RDDs là bất biến, có nghĩa là chúng không thể bị thay đổi sau khi được tạo ra.
  + Thực hiện các phép biến đổi áp dụng lên RDD để tạo ra RDD mới.
- Lazy Evaluation: các phép biến đổi không được thực thi cho đến khi một hành động được thực hiện.
- Thuộc tính của RDD:
  + Danh sách các phân vùng (partitions).
  + Một hàm để tính toán mỗi phần (split).
  + Danh sách các phụ thuộc vào các RDD khác.
  + Một trình phân vùng tùy chọn cho các RDD key-value.
  + Danh sách các vị trí ưa thích (preferred locations) để tính toán phần split.
  
- RDD giữ lại dòng dõi (lineage) của nó, tức là theo dõi tất cả các RDD cha của một RDD cụ thể.
