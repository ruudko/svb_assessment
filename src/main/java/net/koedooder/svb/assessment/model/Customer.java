package net.koedooder.svb.assessment.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class Customer {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
	@OneToMany(mappedBy="leasecontract",fetch=FetchType.LAZY,cascade = CascadeType.PERSIST)
	private List<LeaseContract> leaseContracts = new ArrayList<LeaseContract>();
	private String name;
	private String street;
	private String streetNumber;
	private String zipcode;
	private String place;
	private String email;
	private String phonenumber;
}
