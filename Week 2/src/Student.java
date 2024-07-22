import java.util.HashMap;
import java.util.Map;

public class Student{

    public static void main(String[] args) {
        Map<String, Integer> sv = new HashMap<>();

        sv.put("Long", 20215417);
        sv.put("Tuoi", 20218472);
        sv.put("Ngoc", 20221313);

        System.out.println("Mã sinh viên của Long là " + sv.get("Long"));

        if (sv.containsKey("Tuoi")) {
            System.out.println("Mã sinh viên của Tươi là " + sv.get("Tuoi"));
        }

        System.out.println("Mã sinh viên từng người là ");
        for (Map.Entry<String, Integer> entry : sv.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        sv.remove("Long");
        System.out.println("Đã xóa mã sinh viên của Long");

        System.out.println("Số mã sinh viên hiện tại là " + sv.size());
    }
}