package tech.form3.igorg.interview.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tech.form3.igorg.interview.domain.Form3DomainMarker;
import tech.form3.igorg.interview.infrastructure.Form3InfrastructureMarker;

/**
 * Form3 Payment Spring Boot application.
 */
@SpringBootApplication
@ComponentScan(basePackageClasses = {Form3SpringBootApp.class,  Form3DomainMarker.class, Form3InfrastructureMarker.class})
public class Form3SpringBootApp {

    public static void main(String[] args) {
        SpringApplication.run(Form3SpringBootApp.class, args);
    }

}