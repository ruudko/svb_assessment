package net.koedooder.svb.assessment.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Car {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;
	@OneToMany(mappedBy="leasecontracts",fetch=FetchType.LAZY,cascade = CascadeType.PERSIST)
	private List<LeaseContract> leaseContracts = new ArrayList<LeaseContract>();
	private String make;
	private String model;
	private String version;
	private int doors;
	private int co2GramsPerKm;
	private float grossPrice;
	private float nettPrice;
}
