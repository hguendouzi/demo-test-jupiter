package fr.jupiter.demo.repository;

import fr.jupiter.demo.entity.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CarRepository extends CrudRepository<Car,Long> {
    Car findCarByName(String name);
    List<Car> findCarsByBrand(String brand);
    Long deleteByName(String name);
}
