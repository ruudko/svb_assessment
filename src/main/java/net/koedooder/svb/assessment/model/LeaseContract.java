package net.koedooder.svb.assessment.model;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class LeaseContract {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Car.class)
	@JoinColumn(name="car", referencedColumnName = "carId", nullable = false)
	private Car car;
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Customer.class)
	@JoinColumn(name="userId", referencedColumnName = "customerId", nullable = false)
	private Customer customer;
	private float leaseRate;
}
