package net.koedooder.svb.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.koedooder.svb.assessment.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
