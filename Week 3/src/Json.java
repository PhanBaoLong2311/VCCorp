import org.json.JSONObject;

public class Json {
    public static void main(String[] args) {
        // Tạo JsonObject
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Long");
        jsonObject.put("age", 20);
        jsonObject.put("mssv", 20215417);

        // In giá trị
        System.out.println(jsonObject.toString());
    }
}
