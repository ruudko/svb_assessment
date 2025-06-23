package net.koedooder.svb.assessment.model;

/**
 * Contains the authentication token
 */
public class UserIdToken {
    private String idToken;

    public UserIdToken(String idToken) {
    	this.idToken = idToken;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }
}
