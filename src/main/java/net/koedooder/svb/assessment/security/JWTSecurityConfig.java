package net.koedooder.svb.assessment.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import net.koedooder.svb.assessment.model.Authority;

@Configuration
public class JWTSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	http
        .authorizeHttpRequests((requests) -> requests
        	.requestMatchers("/users/login").permitAll()
        	.requestMatchers("/customers/**")
            .hasAuthority(Authority.AUTHORITY_LEASECOMPANY)
            .requestMatchers("/cars/**")
            .hasAuthority(Authority.AUTHORITY_LEASECOMPANY)
            .requestMatchers("/leasecontracts/**")
            .hasAuthority(Authority.AUTHORITY_BROKER)
            .anyRequest().authenticated()
        );
        return http.build();
    }
    @Bean
    public PasswordEncoder defaultPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager defaultAuthenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
