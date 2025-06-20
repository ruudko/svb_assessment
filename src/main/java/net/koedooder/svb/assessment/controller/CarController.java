package net.koedooder.svb.assessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.koedooder.svb.assessment.exception.CarNotFoundException;
import net.koedooder.svb.assessment.exception.CustomerNotFoundException;
import net.koedooder.svb.assessment.model.Car;
import net.koedooder.svb.assessment.repository.CarRepository;

@RestController
@RequestMapping("/cars")
public class CarController {
	@Autowired
	CarRepository repository;

	@GetMapping("/")
	List<Car> all() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	Car one(@PathVariable Long id) {
		return repository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable long id, @RequestBody Car carDetails) {
        Car updateCar = repository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));

        updateCar.setCo2GramsPerKm(carDetails.getCo2GramsPerKm());
        updateCar.setDoors(carDetails.getDoors());
        updateCar.setGrossPrice(carDetails.getGrossPrice());
        updateCar.setMake(carDetails.getMake());
        updateCar.setModel(carDetails.getModel());
        updateCar.setNettPrice(carDetails.getNettPrice());
        updateCar.setVersion(carDetails.getVersion());
        
        repository.save(updateCar);

        return ResponseEntity.ok(updateCar);
    }
	@PostMapping(value = "/")
	public ResponseEntity<Car> createCar(@RequestBody Car carDetails) {
		
		Car saveCar = repository.save(carDetails);
		 
		return ResponseEntity.ok(saveCar);
	}
}
