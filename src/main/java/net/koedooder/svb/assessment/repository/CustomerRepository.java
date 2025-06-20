package net.koedooder.svb.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.koedooder.svb.assessment.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
