package net.koedooder.svb.assessment.exception;

public class LeaseContractNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 7448892096613199476L;

	public LeaseContractNotFoundException(Long id) {
	    super("Could not find lease contract " + id);
	  }
}
