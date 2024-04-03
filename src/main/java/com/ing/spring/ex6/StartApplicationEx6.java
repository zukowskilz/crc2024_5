package com.ing.spring.ex6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;

/*
Bean Validation API - Java EE 7
@NotNull(message = "Name cannot be null")
@NotEmpty
@Email
@Max/@Min
 */
@SpringBootApplication
public class StartApplicationEx6 {
    public static void main(String[] args) {
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "ex6");
        SpringApplication.run(StartApplicationEx6.class, args);
    }
}
