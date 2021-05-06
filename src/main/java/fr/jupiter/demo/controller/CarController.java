package fr.jupiter.demo.controller;


import fr.jupiter.demo.entity.Car;
import fr.jupiter.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService service;


    @GetMapping("/list")
    public List<Car> getAllCars(){
        return service.getAllCars();
    }

    @PostMapping("/save")
    public @ResponseBody Car saveCar(@RequestBody Car car ){
        return  service.saveCar(car);
    }

     @GetMapping("/{name}")
    public @ResponseBody Car findCarByName(@PathVariable String name){
        return  service.findCarByName(name);
    }

    @GetMapping("/brand/{brand}")
    public @ResponseBody List<Car> findCarByBrand(@PathVariable String brand){
        return service.findCarsByBrand(brand);
    }


    @DeleteMapping("/delete/{name}")
    public @ResponseBody ResponseEntity<HttpStatus> deleteByName(@PathVariable String name){
        service.deleteByName(name);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
