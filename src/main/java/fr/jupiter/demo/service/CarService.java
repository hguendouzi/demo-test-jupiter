package fr.jupiter.demo.service;

import fr.jupiter.demo.entity.Car;
import fr.jupiter.demo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository repository;


    /**
     * save a car
     * @param car
     */
    public Car saveCar(Car car ){
        return repository.save(car);
    }

    /**
     * get all list of car
     * @return list
     */
    public List<Car>getAllCars(){
        return (List<Car>) repository.findAll();
    }

    /**
     * get car by name
     * @param name
     * @return car
     */
    public Car findCarByName(String name){
       return  repository.findCarByName(name);
    }

    /***
     * get list of car by brand
     * @param brand
     * @return list of car
     */
    public List<Car> findCarsByBrand(String brand){
       return repository.findCarsByBrand(brand);
    }

    /**
     * delete car by name
     * @param name
     */
    public void deleteByName(String name){
        repository.deleteByName(name);
    }
}
