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
 * The car of the lease company
 */
@Entity
@Table(name = "cars")
public class Car {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;
	@OneToMany(mappedBy="leaseContractId", fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
	private List<LeaseContract> leaseContracts = new ArrayList<LeaseContract>();
	private String make;
	private String model;
	private String version;
	private int doors;
	private int co2GramsPerKm;
	private float grossPrice;
	private float nettPrice;
	
	public Car() {}
	
	public Car(String make, String model, String version, int doors, int co2GramsPerKm, float grossPrice, float nettPrice) {
		this.make = make;
		this.model = model;
		this.version = version;
		this.doors = doors;
		this.co2GramsPerKm = co2GramsPerKm;
		this.grossPrice = grossPrice;
		this.nettPrice = nettPrice;
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public List<LeaseContract> getLeaseContracts() {
		return leaseContracts;
	}

	public void setLeaseContracts(List<LeaseContract> leaseContracts) {
		this.leaseContracts = leaseContracts;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getDoors() {
		return doors;
	}

	public void setDoors(int doors) {
		this.doors = doors;
	}

	public int getCo2GramsPerKm() {
		return co2GramsPerKm;
	}

	public void setCo2GramsPerKm(int co2GramsPerKm) {
		this.co2GramsPerKm = co2GramsPerKm;
	}

	public float getGrossPrice() {
		return grossPrice;
	}

	public void setGrossPrice(float grossPrice) {
		this.grossPrice = grossPrice;
	}

	public float getNettPrice() {
		return nettPrice;
	}

	public void setNettPrice(float nettPrice) {
		this.nettPrice = nettPrice;
	}
}

