package net.koedooder.svb.assessment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The authority of a user
 */
@Entity
@Table(name = "authorities")
public class Authority {
	public static final String AUTHORITY_BROKER = "BROKER";
	public static final String AUTHORITY_LEASECOMPANY = "LEASECOMPANY";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorityId;
    private String authority;
    
    public Authority() {}
    
    public Authority(String authority) {
    	this.setAuthority(authority);
    }

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
