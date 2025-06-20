package net.koedooder.svb.assessment.exception;

public class CustomerNotFoundException extends RuntimeException{
	private static final long serialVersionUID = -3266837173904349518L;

	public CustomerNotFoundException(Long id) {
	    super("Could not find customer " + id);
	  }
}
