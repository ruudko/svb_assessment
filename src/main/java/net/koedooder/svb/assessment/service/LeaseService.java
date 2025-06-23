package net.koedooder.svb.assessment.service;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.koedooder.svb.assessment.model.Car;
import net.koedooder.svb.assessment.model.Customer;
import net.koedooder.svb.assessment.model.LeaseContract;
import net.koedooder.svb.assessment.repository.LeaseContractRepository;

/**
 * Manages lease contract details for the lease company
 */
@Service
@AllArgsConstructor
public class LeaseService {
	 private final LeaseContractRepository LeaseContractRepository;
	 
	/**
	 * Creates a new lease contract given the customer and car
	 * 
	 * @param customer
	 * @param car
	 * @return
	 */
	public LeaseContract createLeaseContract(Customer customer, Car car, int kmPerYear, int durationInMonths, float percInterest) {
		LeaseContract leaseContract = new LeaseContract();
		leaseContract.setCustomer(customer);
		leaseContract.setCar(car);
		leaseContract.setKmPerYear(kmPerYear);
		leaseContract.setDurationInMonths(durationInMonths);
		leaseContract.setPercInterest(percInterest);
		leaseContract.setLeaseRate(calcLeaseRate(kmPerYear, durationInMonths, percInterest, car.getNettPrice()));
        return LeaseContractRepository.save(leaseContract);
    }
	/**
	 * Leaserate = ((( mileage / 12 ) * duration ) / Nett price) + ((( Interest rate / 100 ) * Nett price) / 12 )
	 * 
	 * @param kmPerYear
	 * @param durationInMonths
	 * @param percInterest
	 * @param nettPrice
	 * @return
	 */
	public float calcLeaseRate(int kmPerYear, int durationInMonths, float percInterest, float nettPrice) {
		return (((kmPerYear / 12) * durationInMonths) / nettPrice) + 
		(((percInterest / 100) * nettPrice) / 12);
	}
}
