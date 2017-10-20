package tech.form3.igorg.interview.infrastructure.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tech.form3.igorg.interview.infrastructure.Form3InfrastructureMarker;
import tech.form3.igorg.interview.model.Form3ModelMarker;

/**
 * Infrastructure configuration.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackageClasses = Form3InfrastructureMarker.class)
@EntityScan(basePackageClasses = {Form3ModelMarker.class, Form3InfrastructureConfig.class})
@EnableJpaRepositories(basePackageClasses = Form3InfrastructureMarker.class)
public class Form3InfrastructureConfig {
}
