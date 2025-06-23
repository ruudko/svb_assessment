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
import net.koedooder.svb.assessment.exception.LeaseContractNotFoundException;
import net.koedooder.svb.assessment.model.Car;
import net.koedooder.svb.assessment.model.Customer;
import net.koedooder.svb.assessment.model.LeaseContract;
import net.koedooder.svb.assessment.repository.CarRepository;
import net.koedooder.svb.assessment.repository.CustomerRepository;
import net.koedooder.svb.assessment.repository.LeaseContractRepository;
import net.koedooder.svb.assessment.service.LeaseService;

@RestController
@RequestMapping("/leasecontracts")
public class LeaseContractController {
	@Autowired LeaseContractRepository repository;
	@Autowired CarRepository carRepository;
	@Autowired LeaseService leaseService;
	@Autowired CustomerRepository customerRepository;
	
	@GetMapping("/")
	List<LeaseContract> all() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	LeaseContract one(@PathVariable Long id) {
		return repository.findById(id).orElseThrow(() -> new LeaseContractNotFoundException(id));
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<LeaseContract> updateLeaseContract(@PathVariable long id, @RequestBody LeaseContract leaseContractDetails) {
		LeaseContract updateLeaseContract = repository.findById(id).orElseThrow(() -> new LeaseContractNotFoundException(id));

		updateLeaseContract.setDurationInMonths(leaseContractDetails.getDurationInMonths());
		updateLeaseContract.setKmPerYear(leaseContractDetails.getKmPerYear());
		updateLeaseContract.setPercInterest(leaseContractDetails.getPercInterest());
		
		updateLeaseContract.setLeaseRate(leaseService.calcLeaseRate(
				updateLeaseContract.getKmPerYear(), 
				updateLeaseContract.getDurationInMonths(), 
				updateLeaseContract.getPercInterest(), 
				updateLeaseContract.getCar().getNettPrice()));
		
        repository.save(updateLeaseContract);

        return ResponseEntity.ok(updateLeaseContract);
    }
	@PostMapping(value = "car/{carId}/customer/{customerId}")
	public ResponseEntity<LeaseContract> createLeaseContract(
			@PathVariable long carId, 
			@PathVariable long customerId, 
			@RequestBody LeaseContract leaseContractDetails) {
		
		Car car = carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException(carId));
		Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
		
		leaseContractDetails.setCar(car);
		leaseContractDetails.setCustomer(customer);
		
		leaseContractDetails.setLeaseRate(leaseService.calcLeaseRate(
				leaseContractDetails.getKmPerYear(), 
				leaseContractDetails.getDurationInMonths(), 
				leaseContractDetails.getPercInterest(), 
				leaseContractDetails.getCar().getNettPrice()));
		
		LeaseContract saveLeaseContract = repository.save(leaseContractDetails);
		 
		return ResponseEntity.ok(saveLeaseContract);
	}
}
