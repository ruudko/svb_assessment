package net.koedooder.svb.assessment.exception;

public class CarNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 8280597339836861332L;

	public CarNotFoundException(Long id) {
	    super("Could not find car " + id);
	  }
}
