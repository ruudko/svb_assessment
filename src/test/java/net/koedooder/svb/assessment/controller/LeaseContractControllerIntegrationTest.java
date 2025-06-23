package net.koedooder.svb.assessment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
import net.koedooder.svb.assessment.model.LeaseContract;
import net.koedooder.svb.assessment.model.User;
import net.koedooder.svb.assessment.model.UserIdToken;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
		webEnvironment = WebEnvironment.RANDOM_PORT,
  classes = SVBAssessmentApplication.class)
public class LeaseContractControllerIntegrationTest {
	@LocalServerPort private int port;
	@Autowired private TestRestTemplate restTemplate;
	
	@Test
	public void loginAsBrokerCompanyAndCreateLeaseContract_returnCreatedLeaseContract() {
		ResponseEntity<UserIdToken> response = restTemplate.postForEntity(
				"http://localhost:" + port + "/lease-car-api/users/login", 
				new User("broker1", "broker1pwd", null), UserIdToken.class);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + response.getBody().getIdToken());

		LeaseContract leaseContract = new LeaseContract();
		leaseContract.setDurationInMonths(60);
		leaseContract.setKmPerYear(10000);
		leaseContract.setPercInterest(5.5f);
		
		ResponseEntity<LeaseContract> leaseContractResponseCreated = 
				restTemplate.exchange("http://localhost:" + port + "/lease-car-api/leasecontracts/car/1/customer/1", 
				HttpMethod.POST, new HttpEntity<>(leaseContract, headers), LeaseContract.class);
		
		assertNotEquals(0f, leaseContractResponseCreated.getBody().getLeaseRate());
	}
	@Test
	public void loginAsBrokerCompanyAndUpdateLeaseContractDetails_returnUpdatedLeaseContract() {
		ResponseEntity<UserIdToken> response = restTemplate.postForEntity(
				"http://localhost:" + port + "/lease-car-api/users/login", 
				new User("broker1", "broker1pwd", null), UserIdToken.class);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + response.getBody().getIdToken());
		
		ResponseEntity<LeaseContract> leaseContractResponse = 
				restTemplate.exchange("http://localhost:" + port + "/lease-car-api/leasecontracts/1", 
				HttpMethod.GET, new HttpEntity<>(headers), LeaseContract.class);
		
		LeaseContract updateLeaseContract = leaseContractResponse.getBody();
		updateLeaseContract.setKmPerYear(60000);
		
		float oldLeaseRate = updateLeaseContract.getLeaseRate();
		
		ResponseEntity<LeaseContract> leaseContractResponseUpdated = 
				restTemplate.exchange("http://localhost:" + port + "/lease-car-api/leasecontracts/1", 
				HttpMethod.PUT, new HttpEntity<>(updateLeaseContract, headers), LeaseContract.class);
		
		assertEquals(60000, leaseContractResponseUpdated.getBody().getKmPerYear());
		
		ResponseEntity<LeaseContract> leaseContractResponseUpdatedGet = 
				restTemplate.exchange("http://localhost:" + port + "/lease-car-api/leasecontracts/1", 
				HttpMethod.GET, new HttpEntity<>(headers), LeaseContract.class);
		
		float newLeaseRate = leaseContractResponseUpdatedGet.getBody().getLeaseRate();
		
		assertNotEquals(oldLeaseRate, newLeaseRate);
		
		assertEquals(60000, leaseContractResponseUpdatedGet.getBody().getKmPerYear());		
	}
}
