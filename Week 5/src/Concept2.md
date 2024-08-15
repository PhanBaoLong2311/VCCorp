### HDFS
- HDFS (Hadoop Distributed File System) là hệ thống quản lý tệp phân tán được thiết kế để lưu trữ và truy cập dữ liệu lớn trong một cluster.
- Gồm 2 thành phần:
    + NameNode (NN)
    + DataNode (DN)

### HDFS Block
- Trong HDFS (Hadoop Distributed File System), dữ liệu được chia thành các khối (blocks) để dễ quản lý và lưu trữ trên các DataNode trong cụm Hadoop.
- HDFS block là đơn vị nhỏ nhất mà name node có thể tham chiếu tới.
- Mỗi tệp trong HDFS được chia thành các khối có kích thước cố định (128MB).
- Đối với các khối có kích thước lớn, kết nối TCP có thể đạt thông lượng cao hơn so với các khối có kích thước nhỏ hơn, giảm áp lực lên namecode, tăng tốc độ mạng, tăng tốc độ map reduce.

### Block Replication
- Đây là một thuộc tính cho phép hệ thống tiếp tục hoạt động bình thường trong trường hợp một số thành phần của nó bị lỗi.
- Các block bản sao sẽ được đặt trên một giá khác với bản sao đầu tiên (không đặt quá nhiều bản sao trên một giá), còn bản sao đầu sẽ đặt trên một nút với máy khách (nếu máy khách chạy ngoài cluster thì nút được chọn ngẫu nhiên).

1. Name Node
- Duy trì cây hệ thống tệp và tất cả siêu dữ liệu cho tất cả các tệp và thư mục trong cây.
- MetaData (siêu dữ liệu) được lưu trữ trên local disk (đĩa cục bộ) bởi:
  + Namespace Image File (FS Image)
  + Edit Log

a. FS Image
- FS Image là một tệp tin lưu trữ trạng thái hoàn chỉnh của hệ thống tệp HDFS tại một thời điểm cụ thể. Nó chứa thông tin về toàn bộ cấu trúc thư mục và các tệp trong hệ thống.

b. Edit Log
- EditLog là nhật ký giao dịch hoặc nhật ký chứa các bản ghi về mọi thay đổi xảy ra với MetaData với lần gần nhất FS Image.
- Cả Edit Log và FS Image đều nằm trên hệ thống tệp cục bộ của NameNode.

c. Secondary Node
- Secondary Node định kì hợp nhất edit log vào fs image để edit log chỉnh sửa không vượt quá giới hạn hợp lý.
- Secondary Node không thể thay thế NameNode trong trường hợp bị lỗi và thường được chạy trên máy khác.
- Namenode Secondary không chạy khi HDFS được triển khai với tính khả dụng cao.

d. Single point of failure
- Nếu một thành phần cụ thể trong hệ thống phân tán được chỉ định là Single point of failure, thì lỗi của thành phần quan trọng này có thể khiến toàn bộ hệ thống sụp đổ.
- Không máy nào tương tác với NameNode có thể đọc, ghi hoặc liệt kê các tệp và thư mục trên hệ thống, cả MapReduce.
- Để xử lý, có 3 chiến lược:
  + Sử dụng backups
  + Sử dụng Secondary Node
  + Sử dụng NameNode dự phòng

2. Data Node
- Datanode lưu trữ dữ liệu thực tế của HDFS. 
- Nó lưu các khối dữ liệu trên hệ thống tệp cục bộ của mình và gửi báo cáo khối (block report) tới Namenode.

3. Checkpoint Node
- Đảm bảo tính nhất quán và khả năng phục hồi của hệ thống tệp phân tán.
- Định kỳ tìm FS image và edit log từ name node và hợp nhất chúng cục bộ. Các trạng thái kết quả được gọi là checkpoint, và tải fs image mới đã hợp nhất lên name node.
- Giảm tải công việc cho Namenode chính.  
- Sự khác biệt so với Secondary Node là checkpoint node tải fs image lên namenode, còn secondary node không có tính năng đó.

4. Ghi dữ liệu trong HDFS 
   1. Khách hàng (client) bắt đầu quá trình viết bằng cách lưu dữ liệu tạm thời trên đĩa cục bộ của nó. Dữ liệu sẽ được gom lại thành một khối đủ lớn trước khi gửi đến HDFS.
   2. Khách hàng liên hệ với Namenode để kiểm tra xem tệp có tồn tại và liệu khách hàng có quyền tạo tệp không. Nếu các kiểm tra này thành công, Namenode cung cấp cho khách hàng danh sách các Datanode để viết dữ liệu.
   3. Khách hàng bắt đầu viết dữ liệu vào Datanode đầu tiên trong danh sách. Datanode này nhận dữ liệu từ khách hàng, ghi dữ liệu vào kho lưu trữ cục bộ của nó, và sau đó chuyển dữ liệu đến Datanode thứ hai trong danh sách.
   4. Dữ liệu được truyền qua một chuỗi các Datanode, với mỗi Datanode nhận và truyền dữ liệu theo từng phần. Điều này tạo ra một đường dẫn dữ liệu từ khách hàng đến tất cả các Datanode tham gia.
   5. Tất cả các Datanode lưu trữ bản sao của khối dữ liệu và đảm bảo đồng bộ hóa dữ liệu.

5. Đọc dữ liệu trong HDFS
   1. Máy Khách gửi một yêu cầu RPC đến Namenode để lấy địa chỉ của các khối dữ liệu mà nó muốn đọc.
   2. Namenode phản hồi với danh sách các địa chỉ Datanode cho mỗi khối dữ liệu. Danh sách này được sắp xếp theo mức độ gần gũi với khách hàng, ưu tiên các Datanode gần nhất.
   3. Nếu máy khách đang chạy trên một Datanode chứa bản sao của khối dữ liệu, nó sẽ đọc dữ liệu trực tiếp từ Datanode đó. Nếu không có bản sao cục bộ, khách hàng sẽ kết nối với Datanode gần nhất để đọc dữ liệu. Dữ liệu được truyền từ Datanode đến khách hàng cho đến khi khối dữ liệu kết thúc.
   4. Máy khách tiếp tục kết nối với các Datanode gần nhất để đọc các khối dữ liệu tiếp theo, lần lượt như vậy cho đến khi toàn bộ tệp được đọc xong.
   5. Nếu có lỗi xảy ra trong quá trình giao tiếp với một Datanode, máy khách sẽ thử kết nối với Datanode gần nhất khác chứa bản sao của khối dữ liệu. Các khối dữ liệu nhận được sẽ được kiểm tra lỗi bằng cách tính toán checksum. Nếu có lỗi, một bản sao của khối dữ liệu sẽ được đọc từ Datanode khác, và Namenode sẽ được thông báo để thực hiện các biện pháp khắc phục.

6. High Availability (HA) trong HDFS
- High Availability (HA) là khả năng của hệ thống hoặc thành phần hệ thống để duy trì hoạt động liên tục trong một khoảng thời gian dài. 
- Trong cài đặt HA, một Namenode phục vụ các yêu cầu của khách hàng và được gọi là Active Namenode, trong khi những Namenode khác được gọi là Standby Namenode.
  1. Cách thức hoạt động:
  - Standby Namenode sử dụng các JournalNodes, nơi ghi lại tất cả các thay đổi mà Active Namenode thực hiện trên namespace của mình.
  - Vì JournalNodes có thể gặp lỗi, nên cần có một số lượng JournalNodes lẻ, ít nhất là ba. 
  2. Failover:
  - Khi Active Namenode gặp sự cố, Standby Namenode sẽ sao chép các thay đổi từ JournalNodes và áp dụng chúng vào trạng thái của mình.
  - Sau đó Standby NameNode sẽ tự xưng là Active NameNode mới.
- Tình Huống "Split Brain"
  + Nếu xảy ra sự cố về mạng trong cluster, mỗi Namenode có thể cho rằng Namenode kia đã chết và tự xưng là Active Namenode. Điều này có thể dẫn đến việc cả hai Namenodes đều cố gắng ghi vào JournalNodes, gây ra sự hỏng hóc của trạng thái được duy trì bởi JournalNodes.
- Fencing
  + Fencing là một cơ chế ngăn chặn một Namenode đã từng là Active nhưng giờ không còn hoạt động đúng cách nữa phục vụ dữ liệu cũ cho các khách hàng.
    
7. Vòng đời ứng dụng của Spark
   1. Nộp ứng dụng Spark:
   - Người dùng nộp ứng dụng Spark tới cụm (cluster) bằng cách chạy lệnh spark-submit trong cửa sổ terminal.
   - Lệnh này khởi động một tiến trình nói chuyện với Cluster Manager. Nếu sử dụng YARN, tiến trình này kết nối với daemon Resource Manager (RM).
   - Nếu công việc được chấp nhận, RM sẽ tạo ra tiến trình driver Spark trên một trong các máy trong cụm.
   2. Khởi chạy tiến trình driver:
   - Khi tiến trình driver bắt đầu chạy, nó sẽ thực thi mã của người dùng. Mã này cần thiết lập một SparkSession, giúp cấu hình Spark cluster.
   - Tiến trình driver và các tiến trình executor được gọi chung là Spark cluster.
   - SparkSession : điểm truy cập duy nhất thống nhất để tương tác với chức năng Spark cơ bản. 
   - SparkSession sẽ nói chuyện với daemon Cluster Manager (trong trường hợp này là RM) để khởi chạy các tiến trình Spark executor trên các nút công nhân (worker nodes).
   3. Khởi chạy các Executor:
   - Cluster manager sẽ chạy các executer trên các node khác nhau, gửi lại vị trí từng node cho driver để driver điều khiển các executer.
   4. Thực thi công việc Spark:
   - Khi Spark cluster sẵn sàng, driver sẽ phân công các tasks cho các tiến trình executor và bắt đầu thực thi công việc.

