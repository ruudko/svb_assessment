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
@Table(name = "cars")
@Data
@NoArgsConstructor()
public class Car {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;
	@OneToMany(mappedBy="leaseContractId",fetch=FetchType.LAZY,cascade = CascadeType.PERSIST)
	private List<LeaseContract> leaseContracts = new ArrayList<LeaseContract>();
	private String make;
	private String model;
	private String version;
	private int doors;
	private int co2GramsPerKm;
	private float grossPrice;
	private float nettPrice;
	
	public Car(String make, String model, String version, int doors, int co2GramsPerKm, float grossPrice, float nettPrice) {
		this.make = make;
		this.model = model;
		this.version = version;
		this.doors = doors;
		this.co2GramsPerKm = co2GramsPerKm;
		this.grossPrice = grossPrice;
		this.nettPrice = nettPrice;
	}
}

