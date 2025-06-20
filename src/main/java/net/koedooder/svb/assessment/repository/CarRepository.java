package net.koedooder.svb.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.koedooder.svb.assessment.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}
