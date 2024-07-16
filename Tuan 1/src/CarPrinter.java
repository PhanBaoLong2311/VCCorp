public class CarPrinter {
    public void printDetails(Car car) {
        System.out.println("Mẫu xe: " + car.getModel());
        System.out.println("Màu sắc: " + car.getColor());
        System.out.println("Năm sản xuất: " + car.getYear());
        if (car instanceof ElectricCar) {
            System.out.println("Loại động cơ: " + ((ElectricCar) car).getEngineType());
        }
    }
}
