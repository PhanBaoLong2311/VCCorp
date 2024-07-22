import java.util.ArrayList;
import java.util.List;

public class Fruit {

    public static void main(String[] args) {
        List<String> fru = new ArrayList<>();

        fru.add("Apple");
        fru.add("Banana");
        fru.add("Orange");
        fru.add("Grpae");

        System.out.println("Danh sách quả gồm: " + fru);

        System.out.println("Quả đầu tiên trong danh sách là: " + fru.get(0));

        fru.add(3, "Dragon Fruit");
        System.out.println("Sau khi thêm danh sách là: " + fru);

        fru.remove(2);
        System.out.println("Sau khi xóa danh sách là: " + fru);

        System.out.println("Kích thước danh sách là: " + fru.size());
    }
}