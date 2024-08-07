## 10 Query MySQL chưa tốt và cách khắc phục
1. Truy vấn không có chỉ mục trên cột tìm kiếm
- Truy vấn không hiệu quả:
``` 
SELECT * FROM hoc_sinh WHERE lop = 'IT1';
```
Nếu cột lop không có chỉ mục, MySQL phải thực hiện quét toàn bộ bảng (table scan) để tìm các hàng phù hợp. Điều này rất chậm với các bảng lớn.

- Tối ưu:
```java
CREATE INDEX idx_lop ON hoc_sinh(lop);
SELECT * FROM hoc_sinh WHERE lop = 'IT1';
```
Cải thiện: Tạo chỉ mục trên cột lop giúp MySQL tìm kiếm nhanh hơn mà không cần quét toàn bộ bảng.  
2. Sử dụng SELECT *
- Query không tối ưu:
```java
SELECT * FROM hoc_sinh WHERE tuoi > 16;
```
Vấn đề: Sử dụng SELECT * lấy tất cả các cột dù không cần thiết, gây lãng phí tài nguyên và băng thông mạng.

- Câu lệnh tối ưu hơn:
```java
SELECT ho, ten, tuoi FROM hoc_sinh WHERE tuoi > 16;
```
Cải thiện: Chỉ lấy các cột cần thiết giúp giảm thiểu dữ liệu truyền tải và cải thiện hiệu suất.  
3. Sử dụng OR thay vì UNION
- Câu lệnh không hiệu quả:
```java
CREATE INDEX idx_lop ON hoc_sinh(lop);
SELECT * FROM hoc_sinh WHERE lop = 'IT1' OR lop = 'IT2';
```

Vấn đề: OR có thể khiến MySQL không sử dụng chỉ mục hiệu quả, dẫn đến quét bảng không cần thiết.

- Câu lệnh tối ưu:
```java
CREATE INDEX idx_lop ON hoc_sinh(lop);
SELECT * FROM hoc_sinh WHERE lop = 'IT1'
UNION
SELECT * FROM hoc_sinh WHERE lop = 'IT2';
```
Cải thiện: Sử dụng UNION có thể giúp MySQL sử dụng chỉ mục tốt hơn, đặc biệt khi có chỉ mục trên cột lop.

4. Truy vấn với LIKE không tối ưu
- Câu lệnh ban đầu:
```java
CREATE INDEX idx_lop ON hoc_sinh(ten);
SELECT * FROM hoc_sinh WHERE ten LIKE '%Long%';
```

Vấn đề: Sử dụng ký tự % ở đầu khiến MySQL không thể sử dụng chỉ mục, phải quét toàn bộ bảng.

- Câu lệnh tối ưu:
```java
CREATE INDEX idx_lop ON hoc_sinh(ten);
ALTER TABLE hoc_sinh ADD FULLTEXT(ten);
SELECT * FROM hoc_sinh WHERE MATCH(ten) AGAINST('Long');
```
Cải thiện: Chỉ mục FULLTEXT giúp tìm kiếm văn bản hiệu quả hơn nhiều so với LIKE.  
5. Sử dụng hàm trong WHERE
- Câu lệnh chưa tốt:
```java
CREATE INDEX idx_lop ON hoc_sinh(ngay_sinh);
SELECT * FROM hoc_sinh WHERE YEAR(ngay_sinh) = 2003;
```
Vấn đề: Sử dụng hàm trên cột làm mất khả năng sử dụng chỉ mục, dẫn đến quét bảng.

- Câu lệnh tối ưu:
```java
CREATE INDEX idx_lop ON hoc_sinh(ngay_sinh);
SELECT * FROM hoc_sinh WHERE ngay_sinh BETWEEN '2005-01-01' AND '2005-12-31';
```
Cải thiện: Sử dụng khoảng giá trị giúp MySQL sử dụng chỉ mục tốt hơn.  
6. Sử dụng DISTINCT không cần thiết
- Câu lệnh chưa tối ưu:
```java
SELECT DISTINCT ho, ten FROM hoc_sinh;
```
Vấn đề: DISTINCT tốn tài nguyên để loại bỏ các bản ghi trùng lặp. Nếu không có bản ghi trùng lặp, việc này không cần thiết.

- Câu lện tối ưu:
```java
SELECT ho, ten FROM hoc_sinh;
```
Cải thiện: Loại bỏ DISTINCT nếu chắc chắn không có bản ghi trùng lặp.  

7. Sử dụng IN thay vì EXISTS
- Query chưa tối ưu:
```java
SELECT * FROM hoc_sinh WHERE id IN (SELECT id FROM hoc_sinh WHERE tuoi > 16);
```

Vấn đề: IN có thể kém hiệu quả hơn EXISTS khi làm việc với các tập con lớn.

- Query tối ưu:
```java
SELECT * FROM hoc_sinh WHERE EXISTS (SELECT 1 FROM hoc_sinh WHERE tuoi > 16);
```
Cải thiện: EXISTS có thể nhanh hơn vì nó ngừng tìm kiếm khi tìm thấy kết quả khớp đầu tiên.  

8. Sử dụng UNION thay vì UNION ALL
- Query chưa tối ưu:
```java
SELECT ho, ten FROM hoc_sinh WHERE lop = 'IT1'
UNION
SELECT ho, ten FROM hoc_sinh WHERE lop = 'IT2';
```
Vấn đề: UNION loại bỏ các bản ghi trùng lặp, tốn tài nguyên. Nếu biết chắc không có bản ghi trùng lặp, việc này không cần thiết.

- Query cải thiện:
```java
SELECT ho, ten FROM hoc_sinh WHERE lop = 'IT1'
UNION ALL
SELECT ho, ten FROM hoc_sinh WHERE lop = 'IT2';
```
Cải thiện: UNION ALL không loại bỏ các bản ghi trùng lặp, tiết kiệm tài nguyên.  

9. Sử dụng ORDER BY RAND()
- Câu lệnh chưa tốt:
```java
SELECT * FROM hoc_sinh ORDER BY RAND() LIMIT 10;
```
Vấn đề: ORDER BY RAND() tính toán lại giá trị ngẫu nhiên cho mỗi hàng, tốn nhiều tài nguyên.

- Câu lệnh tốt hơn:
```java
SELECT * FROM hoc_sinh WHERE id >= (SELECT FLOOR(RAND() * (SELECT MAX(id) FROM hoc_sinh))) LIMIT 10;
```
Cải thiện: Sử dụng cách khác để chọn ngẫu nhiên các hàng, giảm tải cho máy chủ.  

10. Sử dụng JOIN không tối ưu
- Câu lệnh ban đầu:
```java
SELECT hs.ho, hs.ten, d.diem_trung_binh
FROM hoc_sinh hs
JOIN diem d ON hs.id = d.hoc_sinh_id;
```
Vấn đề: Nếu không có chỉ mục trên các cột được JOIN, việc này có thể dẫn đến quét bảng.

- Câu lệnh cải thện:
```java
CREATE INDEX idx_hoc_sinh_id ON diem(hoc_sinh_id);
SELECT hs.ho, hs.ten, d.diem_trung_binh
FROM hoc_sinh hs
JOIN diem d ON hs.id = d.hoc_sinh_id;
```
Cải thiện: Tạo chỉ mục trên các cột được JOIN giúp MySQL tìm kiếm nhanh hơn.