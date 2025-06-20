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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor()
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
	
	public Customer(String name, String street, String streetNumber, String zipcode, String place, String email, String phonenumber) {
		this.name = name;
		this.street = street;
		this.streetNumber = streetNumber;
		this.zipcode = zipcode;
		this.place = place;
		this.email = email;
		this.phonenumber = phonenumber;
	}
}
