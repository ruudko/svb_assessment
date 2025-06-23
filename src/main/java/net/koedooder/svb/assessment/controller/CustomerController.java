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
import net.koedooder.svb.assessment.model.Customer;
import net.koedooder.svb.assessment.repository.CustomerRepository;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	@Autowired CustomerRepository repository;

	@GetMapping("/")
	List<Customer> all() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	Customer one(@PathVariable Long id) {
		return repository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable long id, @RequestBody Customer customerDetails) {
        Customer updateCustomer = repository.findById(id).orElseThrow(() -> new CarNotFoundException(id));

        updateCustomer.setName(customerDetails.getName());
        updateCustomer.setEmail(customerDetails.getEmail());
        updateCustomer.setPhonenumber(customerDetails.getPhonenumber());
        updateCustomer.setPlace(customerDetails.getPlace());
        updateCustomer.setStreet(customerDetails.getStreet());
        updateCustomer.setStreetNumber(customerDetails.getStreetNumber());
        updateCustomer.setZipcode(customerDetails.getZipcode());
        
        repository.save(updateCustomer);

        return ResponseEntity.ok(updateCustomer);
    }
	@PostMapping(value = "/")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customerDetails) {
		
		Customer saveCustomer = repository.save(customerDetails);
		 
		return ResponseEntity.ok(saveCustomer);
	}
}
