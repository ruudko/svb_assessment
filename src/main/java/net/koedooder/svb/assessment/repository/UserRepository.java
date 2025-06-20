package net.koedooder.svb.assessment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.koedooder.svb.assessment.model.User;

public interface UserRepository extends JpaRepository<User, Long> { 
	Optional<User> findByUsername(String username);
}