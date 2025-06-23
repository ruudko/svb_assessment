package net.koedooder.svb.assessment.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * The customer of the broker company
 */
@Entity
@Table(name = "customers")
public class Customer {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
	@OneToMany(mappedBy="leaseContractId",fetch=FetchType.LAZY,cascade = CascadeType.PERSIST)
	private List<LeaseContract> leaseContracts = new ArrayList<LeaseContract>();
	private String name;
	private String street;
	private String streetNumber;
	private String zipcode;
	private String place;
	private String email;
	private String phonenumber;
	
	public Customer() {}
	
	public Customer(String name, String street, String streetNumber, String zipcode, String place, String email, String phonenumber) {
		this.name = name;
		this.street = street;
		this.streetNumber = streetNumber;
		this.zipcode = zipcode;
		this.place = place;
		this.email = email;
		this.phonenumber = phonenumber;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public List<LeaseContract> getLeaseContracts() {
		return leaseContracts;
	}

	public void setLeaseContracts(List<LeaseContract> leaseContracts) {
		this.leaseContracts = leaseContracts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
}
