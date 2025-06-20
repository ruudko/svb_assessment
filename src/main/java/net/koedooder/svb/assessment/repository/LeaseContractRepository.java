package net.koedooder.svb.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.koedooder.svb.assessment.model.LeaseContract;

public interface LeaseContractRepository extends JpaRepository<LeaseContract, Long> {

}
