### Data Frame
1. Khái niệm:
- Data Frame là một API có cấu trúc phổ biến nhất trong Spark, đại diện cho một bảng với các hàng và cột được xác định rõ ràng.
- Schema: Mỗi cột trong DataFrame có một loại dữ liệu được xác định và được duy trì trong một schema. Schema xác định tên và kiểu dữ liệu của các cột.
- Partition:
  + DataFrame được chia thành các phần nhỏ hơn gọi là partition. Mỗi partition là một tập hợp các hàng từ DataFrame và nằm trên một máy vật lý trong cluster.
  + Số lượng partition cũng quyết định tính song song đạt được trong tác vụ Spark (càng nhiều thì có càng nhiều executor).

2. Cách hoạt động:
- Phân phối dữ liệu: DataFrame phân phối dữ liệu của nó trên các máy khác nhau trong một cụm, giúp xử lý dữ liệu lớn bằng cách chia nhỏ và xử lý song song.
- Lazy Evaluation: DataFrames và Datasets được đánh giá lười biếng, có nghĩa là chúng chỉ thực hiện các phép toán trên dữ liệu khi có một hành động thực hiện.

3. Spark type:
- Schema: Bạn có thể định nghĩa schema thủ công hoặc để Spark suy diễn schema từ dữ liệu. Schema có thể được kiểm tra bằng cách sử dụng phương thức schema.
- Spark Types: Spark sử dụng một engine gọi là Catalyst để duy trì thông tin kiểu dữ liệu và ánh xạ chúng tới các kiểu dữ liệu tương ứng trong các ngôn ngữ lập trình được hỗ trợ (Java, Python, v.v.).

### Data Set
1. Khái niệm:
- Dataset là một tập hợp các đối tượng được đánh kiểu mạnh (strong type), không thể thay đổi được ánh xạ tới một lược đồ quan hệ.
- DataSet không được hỗ trợ trong R và Python.
- Sau Spark 2.0, RDD đã được thay thế bằng Dataset, được định kiểu mạnh mẽ giống như RDD, nhưng có khả năng tối ưu hóa phong phú hơn.

2. Encoder:
- Encoder là tính năng giúp chuyển đổi các loại JVM sang biểu diễn nội bộ (dạng bảng) của Spark.
- Encoder ánh xạ loại miền cụ thể tới biểu diễn bên trong của Spark cho loại đó (Encoder giúp Spark chuyển đổi dữ liệu từ dạng đặc trưng của ứng dụng (domain-specific) sang dạng nội bộ tối ưu hóa mà Spark có thể xử lý hiệu quả hơn trong môi trường phân tán)

3. So sánh với Data Frame:
- Type Safety: Datasets kiểm tra kiểu dữ liệu tại thời gian biên dịch (compile-time), trong khi DataFrames kiểm tra tại thời gian chạy (runtime).
- DataFrame: Có thể xem là một Dataset của loại Row, đây là biểu diễn nội bộ của Spark để tính toán trong bộ nhớ.

4. Trường hợp sử dụng Data Set:
- Thực hiện toán tử mà bắt buộc phải dạng dataset.
- Type-safety check lúc compile.
- Tái sử dụng: Nếu bạn có các case classes hoặc bean classes, bạn có thể tái sử dụng chúng cho cả các tác vụ phân tán và cục bộ.

5. Tạo data set
- Để tạo Data Set yêu cầu biết schema (tên và kiểu các cột)
- Ví dụ dưới đây minh họa việc tạo một Dataset từ một tệp văn bản đại diện cho các đối tượng của lớp Car.
```java
import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;

import java.io.Serializable;

// Lớp Car đại diện cho một chiếc xe
public class Car implements Serializable {
    String make;
    String model;

    // Getters and Setters (có thể thêm nếu cần)
}

class Program {

    public static void main(String[] args) {

        // Cấu hình Spark
        SparkConf sparkConf = new SparkConf().setAppName("DatasetExample");

        // Tạo SparkSession
        SparkSession sparkSession = SparkSession.builder()
                .config(sparkConf)
                .getOrCreate();

        // Tạo Dataset từ tệp văn bản
        Dataset<Car> cars = sparkSession.read().text("/DataJek/cars.txt")
                .as(Encoders.bean(Car.class));
    }
}
```

- FlatMap là một phép biến đổi (transformation) trong Apache Spark, được sử dụng để ánh xạ (map) một RDD chứa các phần tử đầu vào thành nhiều phần tử đầu ra, sau đó "phẳng" (flatten) các kết quả thành một RDD duy nhất.

### Zookeeper

1. Khái niệm:
- Zookeeper là một dịch vụ phân tán được thiết kế để quản lý và đồng bộ hóa các ứng dụng phân tán. 
- Nó cung cấp một cơ chế để đảm bảo rằng các ứng dụng phân tán hoạt động đồng bộ và nhất quán.

2. Mục đích:
- Zookeeper quản lý cấu hình, đồng bộ hóa dịch vụ và cung cấp các tính năng phân tán như lựa chọn leader, nhóm node, và đồng bộ hóa trạng thái.
- Ứng dụng: Zookeeper được dùng trong các hệ thống phân tán để quản lý cấu hình dịch vụ, kiểm soát đồng bộ hóa và phân phối nhiệm vụ, đảm bảo tính nhất quán trong hệ thống có nhiều node.

3. Cấu trúc:
- Zookeeper Service: Một cluster ZooKeeper thường bao gồm một số node (thường là một số lẻ để đảm bảo bầu cử leader), và mỗi node đều chạy một bản sao của dịch vụ ZooKeeper.
- Znode: ZooKeeper lưu trữ dữ liệu trong cấu trúc cây phân cấp gọi là znode. Mỗi znode có thể lưu trữ dữ liệu và có thể có các znode con.
- Session: ZooKeeper duy trì các session để quản lý trạng thái kết nối giữa client và server.

4. ZAB (Zookeeper atomic broadcast):
- Là giao thức đồng bộ hóa để đảm bảo tính nhất quán và đồng bộ hóa dữ liệu giữa các node trong cluster.
- Atomic Broadcast: ZAB đảm bảo các thay đổi dữ liệu được gửi đến tất cả các node một cách đồng bộ và nhất quán.
- Hoạt động theo mô hình Leader - follower model:
  + Leader: 
    + Một node trong cluster được bầu cử làm leader, chịu trách nhiệm phát broadcast về các thay đổi dữ liệu.
    + Khi node leader gặp sự cố, quá trình khôi phục và bầu cử leader mới được thực hiện một cách đồng bộ, đảm bảo dữ liệu không bị mất và các thay đổi được áp dụng chính xác.
  + Follower:
    + Các node còn lại trong cluster là follower, nhận các thông báo từ leader và áp dụng các thay đổi theo thứ tự leader gửi.

5. Cách thức hoạt động của Zookeeper:
- ZooKeeper sử dụng giao thức ZAB (ZooKeeper Atomic Broadcast) để đảm bảo tính nhất quán và đồng bộ hóa dữ liệu giữa các node trong cluster.
- Ghi và đọc dữ liệu: Các client có thể đọc và ghi dữ liệu vào các znode. ZooKeeper đảm bảo rằng các thao tác ghi và đọc được đồng bộ hóa trên tất cả các node trong cluster.