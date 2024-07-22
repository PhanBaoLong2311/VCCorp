package xabd;

public class ElectricCar extends Car {
    private String engineType;

    public ElectricCar(String model, String color, int year, String engineType) {
        super(model, color, year);
        this.engineType = engineType;
        display();

    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    @Override
    public void startEngine() {
        System.out.println("Động cơ điện khởi động");
    }

}
