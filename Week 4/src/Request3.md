## Cơ sở dữ liệu quan hệ 
- Đây là cơ sở dữ liệu lưu trữ dữ liệu ở định dạng có bảng có hàng và cột.

### MySQL
- MySQL là một hệ quản trị cơ sở dữ liệu mà nguồn mở phổ biến. Nó được sử dụng rộng rãi trong các ứng dụng web, bao gồm các nền tảng lớn như Facebook, Twitter, và YouTube.
- Với các đặc điểm:
  + Hỗ trợ đa người dùng.
  + Tuân thủ chuẩn SQL.
  + Có khả năng mở rộng cao.
  + Hỗ trợ nhiều công cụ lưu trữ khác nhau.

### PostgreSQL
- PostgreSQL là một hệ quản trị cơ sở dữ liệu quan hệ mã nguồn mở, nổi tiếng với tính toàn vẹn dữ liệu và khả năng mở rộng.
- Các đặc điểm nổi bật:
  + Hỗ trợ các kiểu dữ liệu phức tạp và các phép toán phức tạp. 
  + Tuân thủ chuẩn SQL. 
  + Có hệ thống bảo mật mạnh mẽ. 
  + Hỗ trợ ACID (Atomicity, Consistency, Isolation, Durability).

### SQLite
- SQLite là một hệ quản trị cơ sở dữ liệu SQL nhẹ, không yêu cầu server và thường được sử dụng trong các ứng dụng di động, máy tính để bàn và các hệ thống nhúng.
- Đặc điểm nổi bật:
  + Kích thước nhỏ gọn và dễ dàng triển khai.
  + Không cần cấu hình server.
  + Hiệu suất cao cho các ứng dụng nhỏ.
  + Dữ liệu được lưu trữ trong một tệp duy nhất.

## Cơ sở dữ liệu NoSQL
- Là cơ sở dữ liệu sử dụng nhiều mô hình dữ liệu khác nhau để truy cập và quản lý dữ liệu.
- Dữ liệu được lưu trữ mà không có các ràng buộc theo yêu cầu của cơ sở dữ liệu quan hệ.

### MongoDB
- MongoDB là một database hướng tài liệu (document) và sử dụng lưu trữ dữ liệu dưới dạng Document JSON.
- Đặc điểm:
  + Lưu trữ dữ liệu dưới dạng BSON (Binary JSON).
  + Khả năng mở rộng ngang tốt.
  + Hỗ trợ các truy vấn phức tạp và linh hoạt.
  + Thích hợp cho các ứng dụng đòi hỏi tính linh hoạt trong cấu trúc dữ liệu.

### Cassandra
- Cassandra là một hệ quản trị cơ sở dữ liệu được thiết kế để xử lý lượng lớn dữ liệu được phân tán trên nhiều máy chủ.
- Đặc điểm:
  + Khả năng mở rộng ngang tốt.
  + Khả năng chịu lỗi cao.
  + Hỗ trợ các mô hình dữ liệu linh hoạt.
  + Hiệu suất cao cho các ứng dụng ghi và đọc lớn.

### Redis
- Redis (REmote DIctionary Server) là một mã nguồn mở được dùng để lưu trữ dữ liệu có cấu trúc dạng key-value,  có thể sử dụng như một database, bộ nhớ cache hay một message broker.
- Đặc điểm:
  + Hiệu suất cao với thời gian truy cập dữ liệu rất nhanh.
  + Hỗ trợ các cấu trúc dữ liệu phức tạp như strings, hashes, lists, sets và sorted sets.
  + Khả năng mở rộng và chịu lỗi tốt.
  
## CAP
- CAP là viết tắt của ba tính chất chính trong hệ thống phân tán: Consistency (Nhất quán), Availability (Sẵn sàng) và Partition Tolerance (Chịu lỗi phân vùng).
1. Consistency (Nhất quán):
- Tất cả các nút trong hệ thống phân tán nhìn thấy cùng một dữ liệu cùng một thời điểm. Khi dữ liệu được ghi vào một nút, tất cả các nút khác ngay lập tức biết về thay đổi đó.
2. Avaibility (Sẵn sàng):
- Mỗi yêu cầu nhận được phản hồi (thành công hoặc thất bại), nhưng hệ thống luôn sẵn sàng phục vụ các yêu cầu.
3. Partition Tolerance (Chịu lỗi phân vùng):
- Hệ thống tiếp tục hoạt động ngay cả khi một số nút không thể liên lạc với nhau.

## ACID
- ACID là một tập hợp các thuộc tính đảm bảo rằng các giao dịch cơ sở dữ liệu được thực hiện một cách đáng tin cậy. ACID là viết tắt của:
1. Atomicity (Tính nguyên tử):
- Tính nguyên tử đảm bảo rằng mỗi giao dịch được xem như một đơn vị không thể chia nhỏ. Giao dịch hoặc là được thực hiện hoàn toàn, hoặc là không được thực hiện gì cả. Nếu có bất kỳ phần nào của giao dịch gặp lỗi, toàn bộ giao dịch sẽ bị hủy bỏ và cơ sở dữ liệu sẽ trở lại trạng thái trước khi giao dịch bắt đầu.
- Ví dụ: Trong một giao dịch chuyển tiền từ tài khoản A sang tài khoản B, cả hai hành động (trừ tiền từ A và cộng tiền vào B) phải được thực hiện hoàn toàn. Nếu một hành động thất bại, cả hai hành động sẽ bị hủy bỏ.
2. Consistency (Nhất quán):
- Tính nhất quán đảm bảo rằng một giao dịch đưa cơ sở dữ liệu từ một trạng thái hợp lệ này sang một trạng thái hợp lệ khác, và mọi quy tắc toàn vẹn dữ liệu đều được duy trì. Sau khi giao dịch hoàn tất, tất cả các ràng buộc dữ liệu sẽ vẫn được thỏa mãn.
- Ví dụ: Nếu có một quy tắc rằng số dư tài khoản không được âm, thì sau mỗi giao dịch, quy tắc này vẫn phải được duy trì.
3. Isolation (Cách ly):
- Tính cách ly đảm bảo rằng các giao dịch đang chạy đồng thời không ảnh hưởng lẫn nhau. Kết quả của các giao dịch đồng thời sẽ giống như khi các giao dịch được thực hiện tuần tự.
- Ví dụ: Nếu hai người cùng lúc chuyển tiền vào cùng một tài khoản, tính cách ly đảm bảo rằng số tiền cuối cùng trong tài khoản sẽ là tổng của cả hai giao dịch, bất kể thứ tự thực hiện.
4. Durability (Bền vững):
- Tính bền vững đảm bảo rằng sau khi một giao dịch đã được cam kết, các thay đổi dữ liệu sẽ được lưu trữ vĩnh viễn, ngay cả khi hệ thống gặp sự cố.
- Ví dụ: Sau khi một giao dịch rút tiền từ máy ATM được cam kết, số tiền rút sẽ được ghi nhận và không bị mất ngay cả khi hệ thống gặp sự cố ngay sau đó.

## BASE
-  BASE trong cơ sở dữ liệu là một cách tiếp cận khác với mô hình truyền thống ACID, thường được sử dụng trong các hệ thống NoSQL để đạt được khả năng mở rộng và hiệu suất cao hơn. BASE là viết tắt của các nguyên tắc sau:
1. Basically Available (Sẵn sàng cơ bản):
- Sẵn sàng cơ bản đảm bảo rằng hệ thống sẽ luôn phản hồi các yêu cầu từ người dùng. Điều này có nghĩa là hệ thống sẽ luôn có thể truy cập và cung cấp dịch vụ, ngay cả khi có một số lỗi hoặc sự cố xảy ra.
2. Soft State (Trạng thái mềm):
- Trạng thái mềm có nghĩa là trạng thái của hệ thống có thể thay đổi theo thời gian, ngay cả khi không có bất kỳ dữ liệu mới nào được ghi vào hệ thống. Điều này xảy ra do các cập nhật liên tục và việc đồng bộ hóa dữ liệu giữa các nút trong hệ thống phân tán.
- Hệ thống không đòi hỏi rằng các trạng thái của nó phải nhất quán ngay lập tức. Thay vào đó, trạng thái có thể chuyển đổi và thay đổi dần dần theo thời gian.
3. Eventual Consistency (Nhất quán cuối cùng):
- Nhất quán cuối cùng có nghĩa là hệ thống đảm bảo rằng, cuối cùng, tất cả các bản sao của dữ liệu sẽ trở nên nhất quán, miễn là không có cập nhật mới nào được thực hiện.

## 3 dạng chuẩn của cơ sở dữ liệu
- Quá trình chuẩn hóa (normalization) giúp thiết kế cơ sở dữ liệu sao cho giảm thiểu sự dư thừa dữ liệu và tránh các vấn đề về cập nhật, chèn, và xóa dữ liệu.
1. Dạng chuẩn 1 (First Normal Form - 1NF)
- Mỗi ô của bảng chỉ chứa một giá trị duy nhất.
- Mỗi hàng của bảng là duy nhất.

| StudentID | Name | Subjects          |
|-----------|------|-------------------|
| 1         | Long | Math, Science     |
| 2         | Hien | Math, History     |
| 3         | Tuan | Science, History  |

Bảng này chưa ở dạng 1NF, để đưa về đúng dạng 1NF thì phải tách các môn học ra các hàng riêng biệt.  

| StudentID | Name | Subject   |
|-----------|------|-----------|
| 1         | Long | Math      |
| 1         | Long | Science   |
| 2         | Hien | Math      |
| 2         | Hien | History   |
| 3         | Tuan | Science   |
| 3         | Tuan | History   |


2. Dạng chuẩn 2 (Second Normal Form - 2NF)
- Ở dạng chuẩn 1 (1NF).
- Mọi cột không thuộc khóa chính đều phụ thuộc hoàn toàn vào toàn bộ khóa chính, chứ không chỉ một phần của khóa chính.

| StudentID | Subject | Teacher   |
|-----------|---------|-----------|
| 1         | Math    | Mr. Smith |
| 1         | Science | Mrs. Doe  |
| 2         | Math    | Mr. Smith |
| 2         | History | Mr. Lee   |
| 3         | Science | Mrs. Doe  |
| 3         | History | Mr. Lee   |

Trong bảng này, Teacher phụ thuộc vào Subject chứ không phải toàn bộ khóa chính (StudentID, Subject). Để đưa bảng này về 2NF, chúng ta cần tách thành hai bảng StudentSubjects và Subjects.

| StudentID | Subject   |             
|-----------|-----------|
| 1         | Math      |
| 1         | Science   |
| 2         | Math      |
| 2         | History   |
| 3         | Science   |
| 3         | History   |

| Subject   | Teacher   |
|-----------|-----------|
| Math      | Mr. Smith |
| Science   | Mrs. Doe  |
| History   | Mr. Lee   |

3. Dạng chuẩn 3
- Quan hệ thỏa mãn chuẩn 2 (2NF).
- Không có thuộc tính không phải khóa phụ thuộc vào một thuộc tính không phải khóa khác.

| Subject   | Teacher   | Classroom |
|-----------|-----------|-----------|
| Math      | Mr. Smith | Room 101  |
| Science   | Mrs. Doe  | Room 102  |
| History   | Mr. Lee   | Room 103  |

Trong bảng này, Classroom phụ thuộc vào Subject qua Teacher. Để đưa bảng này về 3NF, cần tách thành 2 bảng Subjects và Teachers.

| Subject   | TeacherID |
|-----------|-----------|
| Math      | 1         |
| Science   | 2         |
| History   | 3         |

| TeacherID | Teacher   | Classroom |
|-----------|-----------|-----------|
| 1         | Mr. Smith | Room 101  |
| 2         | Mrs. Doe  | Room 102  |
| 3         | Mr. Lee   | Room 103  |
