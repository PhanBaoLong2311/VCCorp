# Json 
- Json là là viết tắt của JavaScript Object Notation, là một kiểu định dạng dữ liệu tuân theo một quy luật nhất định mà hầu hết các ngôn ngữ lập trình hiện nay đều có thể đọc được.
- Định dạng JSON sử dụng các cặp key – value để dữ liệu sử dụng. Nó hỗ trợ các cấu trúc dữ liệu như đối tượng và mảng.
- Ví dụ:
```json
{
  "name": "Long",
  "age": 20,
  "mssv": 20215417
}
```

### Parsing Json
- Là quá trình chuyển đổi dữ liệu JSON từ định dạng chuỗi thành các đối tượng Java để có thể làm việc với dữ liệu đó trong chương trình của mình.
- Quá trình parsing json:
  + Nhận Dữ Liệu JSON: Dữ liệu JSON thường được nhận dưới dạng chuỗi (string)
  + Chuyển Đổi Thành Đối Tượng Java: Dữ liệu JSON được phân tích và chuyển đổi thành các đối tượng Java.
- Ví dụ:
```java
import org.json.JSONObject;

public class JsonExample {
    public static void main(String[] args) {
        // Tạo JSONObject
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Long");
        jsonObject.put("age", 20);
        jsonObject.put("mssv", 20215417);

        // Lấy giá trị 
        String name = jsonObject.getString("name");
        int age = jsonObject.getInt("age");
        int mssv = jsonObject.getInt("20215417");

        // In giá trị 
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Mssv: " + mssv);

        // Chuyển đổi JSONObject thành String
        String jsonString = jsonObject.toString();
        System.out.println("JSON String: " + jsonString);
    }
}
```