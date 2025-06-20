package net.koedooder.svb.assessment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "authorities")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Authority {
	public static final String AUTHORITY_BROKER = "BROKER";
	public static final String AUTHORITY_LEASECOMPANY = "LEASECOMPANY";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorityId;
    private String authority;
    
    public Authority(String authority) {
    	this.authority = authority;
    }
}
