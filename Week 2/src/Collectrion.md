1.	HashMap: Là một cấu trúc dữ liệu dùng để lưu trữ các cặp key-value (khóa-giá trị), gồm một số đặc điểm chính:
-	Lưu trữ dữ liệu dưới dạng các cặp key-value. Mỗi key là duy nhất và ánh xạ đến một giá trị cụ thể.
-	Chuyển đổi key thành một chỉ mục trong bảng hash (hash table), giúp việc truy cập dữ liệu nhanh chóng.
-	Các phần tử không duy trì thứ tự chèn vào. Thứ tự của các phần tử có thể thay đổi sau khi chèn hoặc xóa các phần tử khác.
-	Chèn và xóa phần tử rất nhanh, thường là O(1) trong hầu hết các trường hợp.
-	Cho phép một key null và nhiều giá trị null.
-	Các phương thức chính:  
+put(K key, V value): Thêm một cặp key-value vào HashMap.  
+get(Object key): Trả về giá trị tương ứng với key.  
+remove(Object key): Xóa cặp key-value tương ứng với key.  
+containsKey(Object key): Kiểm tra xem HashMap có chứa key hay không.  
+size(): Trả về số lượng cặp key-value trong HashMap.

2.	HashSet: Là một cấu trúc dữ liệu được sử dụng để lưu trữ các phần tử duy nhất, không cho phép phần tử trùng lặp.
-	Không cho phép các phần tử trùng lặp. Nếu thêm một phần tử đã tồn tại trong HashSet, nó sẽ không thay đổi và không có phần tử trùng lặp nào được thêm vào.
-	Các phần tử không duy trì thứ tự chèn vào. Thứ tự của các phần tử có thể thay đổi sau khi thêm hoặc xóa các phần tử khác.
-	Thêm và xóa phần tử rất nhanh, thường là O(1) trong hầu hết các trường hợp.
-	Cho phép một giá trị null duy nhất.
-	Các phương thức chính:  
+add(E e): Thêm một phần tử vào HashSet.  
+remove(Object o): Xóa một phần tử khỏi HashSet.  
+contains(Object o): Kiểm tra xem HashSet có chứa phần tử hay không.  
+size(): Trả về số lượng phần tử trong HashSet.

3.	ArrayList: Là một cấu trúc dữ liệu động, dùng để lưu trữ các phần tử theo kiểu danh sách.
-	ArrayList lưu trữ các phần tử theo thứ tự chèn vào.
-	Kích thước có thể thay đổi linh hoạt bằng cách thêm và xóa các phần tử.
-	Hiệu suất truy cập phần tử tốt, thường là O(1), nhưng việc thêm và xóa phần tử có thể chậm hơn nếu cần di chuyển nhiều phần tử (O(n) trong trường hợp xấu nhất).
-	Cho phép các giá trị null.
-	Các phương thức chính:  
+add(E e): Thêm một phần tử vào cuối ArrayList.  
+get(int index): Trả về phần tử tại vị trí chỉ định.  
+remove(int index): Xóa phần tử tại vị trí chỉ định.  
+size(): Trả về số lượng phần tử trong ArrayList.  
+contains(Object o): Kiểm tra xem ArrayList có chứa phần tử hay không.

4.	So sánh sự khác biệt:

| Main Collection Classes | D   | O   | E   | TS  |
|-------------------------|-----|-----|-----|-----|
| HashMap                 | No  | No  | No  | No  |
| HashSet                 | No  | No  | No  | No  |
| ArrayList               | Yes | Yes | No  | No  |


- D là Duplicates (Trùng lặp)
- O là Ordering (Thứ tự)
- E là Efficient Index-Based Access (truy cập dựa trên chỉ mục hiệu quả)
- TS là Thread Safe (An toàn luồng)

5. Giải thích:
- Efficient Index-Based Access là phương pháp truy xuất dữ liệu từ một cấu trúc dữ liệu sử dụng chỉ mục để tìm kiếm và truy cập thông tin nhanh chóng hơn. Tuy nhiên HashMap, HashSet và ArrayList với cấu trúc dữ liệu của chúng đã cho phép tìm kiếm và truy cập thông tin với độ phức tạp O(1) nên không cần có truy cập dựa trên chỉ mục hiệu quả.  
- Cả 3 cấu trúc dữ liệu trên không có an toàn luồng do chúng được thiết kế để tối ưu hóa hiệu suất trong các trường hợp đơn luồng. Việc nhiều luồng truy cập có thể dẫn tới những vấn đề như luồng đồng thời có thể thay đổi trạng thái của cấu trúc dữ liệu, race conditions. Có thể sử dụng từ khóa synchronized để bảo vệ các thao tác trên cấu trúc dữ liệu.

6. Khái niệm Equals và HashCode:
-	Equals và HashCode là 2 phương thức so sánh đối tượng và quản lý chúng trong các cấu trúc dữ liệu như HashMap và HashSet.
-	Equals là phương thức xem 2 đối tượng có bằng nhau hay không. Nếu chúng bằng nhau theo equals thì được coi là tương đương (2 đối tượng tương đương không có nghĩa là chúng giống hệt nhau hoặc là một)
-	HashCode trả về một số nguyên đại diện cho đối tượng, được sử dụng trong cấu trúc dữ liệu Hash Table để xác định vị trí của đối tượng.
-	Nếu hai đối tượng được so sánh bằng phương thức equals và trả về true thì chúng phải có cùng giá trị HashCode.
