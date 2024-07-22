import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Animal {
    private String name;
    private String type;

    public Animal(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object a) {
        if (this == a) return true;
        if (a == null || getClass() != a.getClass()) return false;
        Animal animal = (Animal) a;
        return Objects.equals(name, animal.name) && Objects.equals(type, animal.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }

    @Override
    public String toString() {
        return name + " (" + type + ")";
    }

    public static void main(String[] args) {
        Set<Animal> ani = new HashSet<>();

        ani.add(new Animal("Dog", "4 legs"));
        ani.add(new Animal("Cat", "legs"));
        ani.add(new Animal("Chicken", "2 legs"));
        ani.add(new Animal("Cat", "4 legs")); // Phần tử bị trùng, sẽ không được thêm vào

        System.out.println("HashSet gồm các con vật: " + ani);

        if (ani.contains(new Animal("Banana", "Fruit"))) {
            System.out.println("Banana là một con vật");
        } else {
            System.out.println("Banana không phải là vật");
        }

        ani.remove(new Animal("Dog", "Mammal"));
        System.out.println("Sau khi xóa, HashSet còn: " + ani);

        System.out.println("Kích thước của HashSet là: " + ani.size());
    }
}
