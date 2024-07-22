package xabd;

public class Car {
    private String model;
    protected String color;
    int year;

    static void display(){
        System.out.println("abc");
    }
    public Car(String model, String color, int year) {
        this.model = model;
        this.color = color;
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void startEngine() {
        System.out.println("Khởi động");
    }

    public void stopEngine() {
        System.out.println("Tắt động cơ");
    }


}


