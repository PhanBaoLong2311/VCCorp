import org.json.JSONObject;

public class Json {
    public static void main(String[] args) {

        String jsonString = "{ \"Name\": \"Long\", \"Age\": 20, \"Mssv\": 20215417 }";

        JSONObject jsonObject = new JSONObject(jsonString);

        // Lấy các giá trị từ đối tượng JSONObject
        String name = jsonObject.getString("name");
        int age = jsonObject.getInt("age");
        int mssv = jsonObject.getInt("mssv");

        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Mssv: " + mssv);

        // Chuyển đối tượng JSONObject thành chuỗi JSON
        String jsonStringConverted = jsonObject.toString();

        System.out.println("Converted JSON String: " + jsonStringConverted);
    }
}
