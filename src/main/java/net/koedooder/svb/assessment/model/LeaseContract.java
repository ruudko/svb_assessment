package net.koedooder.svb.assessment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "leasecontracts")
@Data
@NoArgsConstructor()
public class LeaseContract {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaseContractId;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Car.class)
	@JoinColumn(name="car", referencedColumnName = "carId", nullable = false)
	private Car car;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Customer.class)
	@JoinColumn(name="customer", referencedColumnName = "customerId", nullable = false)
	private Customer customer;
	
	private int kmPerYear;
	private float percInterest;
	private int durationInMonths;
	private float leaseRate;
	
	public LeaseContract(Customer customer, Car car, float leaseRate) {
		this.customer = customer;
		this.car = car;
		this.leaseRate = leaseRate;
	}
}
