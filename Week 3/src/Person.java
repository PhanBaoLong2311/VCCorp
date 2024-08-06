/*import com.google.gson.Gson;

class Person {
    private String name;
    private int age;
    private int mssv;

    // Getters Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public int getMssv() {
        return mssv;
    }
    public void setMssv(int mssv) {
        this.mssv = mssv;
    }

    @Override
    public String toString() {
        return "Person{Name='" + name + "', Age=" + age + ", Mssv=" + mssv + "}";
    }
}

public class GsonExample {
    public static void main(String[] args) {
        // Chuỗi JSON
        String jsonString = "{\"Name\":\"Long\", \"Age\":20, \"Mssv\":20215417}";

        // Tạo đối tượng Gson
        Gson gson = new Gson();

        // Parse chuỗi JSON thành đối tượng Person
        Person person = gson.fromJson(jsonString, Person.class);

        // In đối tượng ra màn hình
        System.out.println(person);
    }
}
*/