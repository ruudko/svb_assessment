package net.koedooder.svb.assessment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import net.koedooder.svb.assessment.SVBAssessmentApplication;
import net.koedooder.svb.assessment.model.Customer;
import net.koedooder.svb.assessment.model.User;
import net.koedooder.svb.assessment.model.UserIdToken;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
		webEnvironment = WebEnvironment.RANDOM_PORT,
  classes = SVBAssessmentApplication.class)
public class CustomerControllerIntegrationTest {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	@Test
	public void loginAsLeaseCompanyAndUpdateCustomerDetails_returnUpdatedCustomer() {
		ResponseEntity<UserIdToken> response = restTemplate.postForEntity(
				"http://localhost:" + port + "/lease-car-api/users/login", 
				new User("leasecompany1", "leasecompany1pwd", null), UserIdToken.class);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + response.getBody().getIdToken());
		
		ResponseEntity<Customer> customerResponse = 
				restTemplate.exchange("http://localhost:" + port + "/lease-car-api/customers/1", 
				HttpMethod.GET, new HttpEntity<>(headers), Customer.class);
		Customer updateCustomer = customerResponse.getBody();
		updateCustomer.setStreetNumber("99");;
		
		ResponseEntity<Customer> customerResponseUpdated = 
				restTemplate.exchange("http://localhost:" + port + "/lease-car-api/customers/1", 
				HttpMethod.PUT, new HttpEntity<>(updateCustomer, headers), Customer.class);
		
		assertEquals("99", customerResponseUpdated.getBody().getStreetNumber());
		
		ResponseEntity<Customer> customerResponseUpdatedGet = 
				restTemplate.exchange("http://localhost:" + port + "/lease-car-api/customers/1", 
				HttpMethod.GET, new HttpEntity<>(headers), Customer.class);
		
		assertEquals("99", customerResponseUpdatedGet.getBody().getStreetNumber());		
	}
}
