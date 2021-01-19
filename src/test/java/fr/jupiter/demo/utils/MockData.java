package fr.jupiter.demo.utils;

import fr.jupiter.demo.entity.Car;

import java.util.List;

public class MockData {

    public Car mockCar(){
        Car car =new Car();
        car.setBrand("Volkswagen");
        car.setColor("blue");
        car.setName("Golf");
        car.setRegister("XD-777-00");
        return car;
    }

    public List<Car> mockCars(){
      return List.of(mockCar(),new Car(Long.valueOf(1),"308","Peugeot","red","00-777-00"));
    }
}
