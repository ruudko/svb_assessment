package net.koedooder.svb.assessment;

import java.util.Arrays;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.koedooder.svb.assessment.model.Authority;
import net.koedooder.svb.assessment.model.Car;
import net.koedooder.svb.assessment.model.Customer;
import net.koedooder.svb.assessment.model.LeaseContract;
import net.koedooder.svb.assessment.model.User;
import net.koedooder.svb.assessment.repository.AuthorityRepository;
import net.koedooder.svb.assessment.repository.CarRepository;
import net.koedooder.svb.assessment.repository.CustomerRepository;
import net.koedooder.svb.assessment.repository.LeaseContractRepository;
import net.koedooder.svb.assessment.service.LeaseService;
import net.koedooder.svb.assessment.service.UserService;

/**
 * Pre loads the in memory database
 */
@Configuration
public class LoadDatabase {
	
	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Autowired UserService userService;
	@Autowired LeaseService leaseService;
	
	@Bean
	CommandLineRunner initDatabase(
			CustomerRepository customerRepository, 
			CarRepository carRepository, 
			LeaseContractRepository leaseContractRepository,
			AuthorityRepository authorityRepository) {
		return args -> {
			Customer customer = customerRepository.save(new Customer("Peter Peterson", "Petersonroad", "1A",
					"1111AA", "Peterson town", "Peter@Peterson.com", "+7612121212"));
			Car car = carRepository.save(new Car("BMW", "X1", "3", 5,
					300, 30000, 26000));
			
			LeaseContract leaseContract = leaseService.createLeaseContract(customer, car, 30000, 48, 4.5f);
			
			log.info("Preloaded data " + leaseContractRepository.save(leaseContract));
			
			userService.registerUser(new User("broker1", "broker1pwd", 
					new HashSet<>(Arrays.asList(authorityRepository.save(new Authority(Authority.AUTHORITY_BROKER))))));
			userService.registerUser(new User("leasecompany1", "leasecompany1pwd", 
					new HashSet<>(Arrays.asList(authorityRepository.save(new Authority(Authority.AUTHORITY_LEASECOMPANY))))));
		};
	}
}
