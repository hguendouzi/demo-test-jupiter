package fr.jupiter.demo.service;

import fr.jupiter.demo.entity.Car;
import fr.jupiter.demo.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest(showSql = false)
class CarServiceTest {
    @Autowired
    private CarRepository repository;
    private  Car car;
    private Car car1;

    @BeforeEach
    void setUp() {
        car =new Car();
        car.setName("Golf");
        car.setColor("Blue");
        car.setBrand("Volkswagen");
        car.setRegister("XX-000-YL");
        car1 =new Car();
        car1.setName("308");
        car1.setColor("Red");
        car1.setBrand("Peugeot");
        car1.setRegister("XX-000-YL");
    }

    @Test
    void saveCar() {
        repository.save(car);
        assertThat(repository.count()).isNotZero();

    }

    @Test
    void getAllCars() {

        repository.save(car);
        repository.save(car1);
        List<Car> cars = (List<Car>) repository.findAll();
        assertThat(cars).isNotEmpty();

    }

    @Test
    void findCarByName() {
        repository.save(car);
       Car car=repository.findCarByName("Golf");
        assertThat(car).isNotNull();

    }

    @Test
    void findCarsByBrand() {
        repository.save(car);
        List<Car> cars = repository.findCarsByBrand("Volkswagen");
        assertThat(cars).isNotEmpty();
    }

    @Test
    void deleteByName() {
        repository.save(car);
        assertThat(repository.count()).isNotZero();
        repository.deleteByName("Golf");
        assertThat(repository.count()).isZero();
    }
}