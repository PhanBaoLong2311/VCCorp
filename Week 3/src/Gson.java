import com.google.gson.Gson;

class Person {
    private String name;
    private int age;
    private int mssv;

    // Getters and Setters
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
        return "Person{name='" + name + "', age=" + age + ", mssv='" + mssv + "'}";
    }
}

public class Gson {
    public static void main(String[] args) {
        // Chuỗi JSON
        String jsonString = "{\"name\":\"Long\", \"age\":20, \"mssv\":20215417}";

        // Tạo đối tượng Gson
        Gson gson = new Gson();

        // Parse chuỗi JSON thành đối tượng Person
        Person person = gson.fromJson(jsonString, Person.class);

        // In đối tượng ra màn hình
        System.out.println(person);
    }
}
