package net.koedooder.svb.assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Start up the SVB Assessment rest application
 */
@SpringBootApplication
public class SVBAssessmentApplication {
	public static void main(String[] args) {
		SpringApplication.run(SVBAssessmentApplication.class, args);
	}

	@Bean
	public HealthIndicator helloHealthIndicator() {
		return new HealthIndicator() {
			@Override
			public Health health() {
				return Health.up().withDetail("iam", "up").build();
			}
		};
	}
}
