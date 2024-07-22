                                                                         Week 1

**1.Các tính chất của OOP:**
- Đóng gói (Encapsulation): Là khả năng che dấu các chi tiết bên trong của đối tượng và chỉ hiển thị những gì cần thiết để sử dụng bên ngoài. Điều này giúp bảo vệ dữ liệu của đối tượng và ngăn chặn sự can thiệp trái phép từ phía bên ngoài.
-	Kế thừa (Inheritance): Cho phép một lớp con kế thừa các thuộc tính và phương thức từ một lớp cha. Kế thừa cho phép tái sử dụng mã nguồn và xây dựng các lớp mới dựa trên các lớp hiện có.
-	Đa hình (Polymorphism): Là khả năng của một đối tượng để hiển thị hành vi khác nhau dựa trên ngữ cảnh. Đa hình giúp giảm sự phức tạp của mã và cải thiện tính linh hoạt của hệ thống.
-	Trừu tượng (Abstraction): Là khả năng tập trung vào các chi tiết quan trọng của một đối tượng và bỏ qua các chi tiết không cần thiết. Trừu tượng giúp tạo ra các lớp và phương thức để giảm sự phức tạp và tăng tính tổng quát của hệ thống.

**2.Các tính chất trong Solid:**  
*•	Nguyên lý Đơn trách nhiệm (Single Responsibility Principle - SRP):*
- Một lớp nên chỉ có một lý do để thay đổi.
-	Mỗi lớp trong hệ thống nên chỉ chịu trách nhiệm duy nhất cho một mục đích cụ thể, và nếu có sự thay đổi xảy ra, nó chỉ nên liên quan đến lý do duy nhất đó.

*•	Nguyên lý Mở rộng và Đóng gói (Open/Closed Principle - OCP):*
-	Phần mềm nên được thiết kế sao cho mở rộng (extension) có thể thực hiện được mà không cần sửa đổi (modification) các phần đã tồn tại.
-	Nên sử dụng kế thừa và giao diện để mở rộng tính năng của hệ thống mà không cần phải thay đổi mã nguồn hiện có.

*•	Nguyên lý Ngăn xếp LSP (Liskov Substitution Principle - LSP):*
-	Các đối tượng của lớp con có thể thay thế được các đối tượng của lớp cha mà không làm thay đổi tính đúng đắn của chương trình.
-	Tính thừa kế phải được áp dụng một cách chính xác, không làm thay đổi hành vi của các lớp cha mà lớp con kế thừa.

*•	Nguyên lý Tách rời Interface (Interface Segregation Principle - ISP):*
-	Nên ưu tiên sử dụng nhiều interface nhỏ (có nghĩa là cụ thể) hơn là một interface lớn.
-	Cần phân chia các giao diện thành các thành phần nhỏ hơn, để các lớp chỉ cần triển khai các giao diện mà chúng cần, giảm thiểu sự phụ thuộc vào những giao diện không cần thiết.

*•	Nguyên lý Phân chia trách nhiệm (Dependency Inversion Principle - DIP):*
-	Các module cao cấp không nên phụ thuộc vào các module thấp cấp. Cả hai nên phụ thuộc vào các trừu tượng.
-	Sử dụng các giao diện hoặc các lớp trừu tượng để giảm thiểu sự phụ thuộc trực tiếp giữa các lớp, nâng cao tính linh hoạt và tái sử dụng trong hệ thống.

**3.Khái niệm interface:**
-	Interface là một bản thiết kế định nghĩa các phương thức mà các lớp khác nhau có thể triển khai.
-	Interface cung cấp các hành vi chung mà các đối tượng có thể thực hiện.
-	Giúp tách rời khai báo và triển khai của các phương thức, nâng cao tính linh hoạt và tái sử dụng mã nguồn.

**4.Khái niệm static:**
-	Static là từ khoá dùng để chỉ các thành phần (biến và phương thức) thuộc về lớp, không phải thuộc về từng đối tượng cụ thể của lớp đó. Điều này cho phép truy cập các thành phần này mà không cần tạo đối tượng của lớp.
-	Static giúp chia sẻ thông tin chung giữa tất cả các đối tượng của lớp, tiết kiệm bộ nhớ khi chỉ cần một biến hay phương thức duy nhất trong bộ nhớ

**5.Trình bày về đã áp dụng OOP trong đoạn code đã xây dựng:**  
•Đóng gói:
-   Các lớp Car, ElectricCar, RegularEngine, EngineStart đều sử dụng đóng gói để che giấu các chi tiet bên trong.

•Kế thừa:
- Lớp ElectricCar kế thừa từ lớp Car: ElectricCar sử dụng các thuộc tính và phương thức của lớp Car và mở rộng tính năng riêng (engineType).

•Đa hình :
-   Đối tượng Car và ElectricCar đều có phương thức StartEngine() được triển khai khác nhau. Khi gọi startEgine() thông qua đối tượng của lớp Car và ElectricCar, chương trình sẽ gọi phương thức tương ứng của từng xe.

•Trừu tượng:
-   EngineStart sử dụng trừu tượng để khởi động các động cơ mà không quan tâm đến loại động cơ cụ thể nào, chỉ cần nó triển khai interface Engine.

![](../Untitled.png)






 
