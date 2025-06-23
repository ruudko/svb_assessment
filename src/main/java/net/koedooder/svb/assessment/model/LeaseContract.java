package net.koedooder.svb.assessment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Lease contract containing a reference to a car and customer
 */
@Entity
@Table(name = "leasecontracts")
public class LeaseContract {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaseContractId;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Car.class)
	@JoinColumn(name="car", referencedColumnName = "carId", nullable = false)
	@JsonBackReference(value="leasecontract-car")
	private Car car;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Customer.class)
	@JoinColumn(name="customer", referencedColumnName = "customerId", nullable = false)
	@JsonBackReference(value="leasecontract-customer")
	private Customer customer;
	
	private int kmPerYear;
	private float percInterest;
	private int durationInMonths;
	private float leaseRate;
	
	public LeaseContract() {
		
	}
	
	public LeaseContract(Customer customer, Car car, float leaseRate) {
		this.customer = customer;
		this.car = car;
		this.leaseRate = leaseRate;
	}

	public Long getLeaseContractId() {
		return leaseContractId;
	}

	public void setLeaseContractId(Long leaseContractId) {
		this.leaseContractId = leaseContractId;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getKmPerYear() {
		return kmPerYear;
	}

	public void setKmPerYear(int kmPerYear) {
		this.kmPerYear = kmPerYear;
	}

	public float getPercInterest() {
		return percInterest;
	}

	public void setPercInterest(float percInterest) {
		this.percInterest = percInterest;
	}

	public int getDurationInMonths() {
		return durationInMonths;
	}

	public void setDurationInMonths(int durationInMonths) {
		this.durationInMonths = durationInMonths;
	}

	public float getLeaseRate() {
		return leaseRate;
	}

	public void setLeaseRate(float leaseRate) {
		this.leaseRate = leaseRate;
	}
}
