public class Main {
    public static void main(String[] args) {
        Car car = new Car("Toyota", "Đỏ", 2020);
        ElectricCar electricCar = new ElectricCar("Tesla", "Xanh", 2021, "Điện");

        CarPrinter printer = new CarPrinter();
        printer.printDetails(car);
        printer.printDetails(electricCar);

        Engine regularEngine = new RegularEngine();
        Engine electricEngine = new ElectricEngine();

        EngineStart regularStarter = new EngineStart(regularEngine);
        EngineStart electricStarter = new EngineStart(electricEngine);

        regularStarter.startEngine();
        electricStarter.startEngine();
    }
}

interface Engine {
    void start();
}
