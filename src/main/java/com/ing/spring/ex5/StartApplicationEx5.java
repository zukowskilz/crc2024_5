package com.ing.spring.ex5;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;

/*
@ConfigurationProperties –strong typed configuration
Secret values
by env
by files eg. Kubernetes
 */
@SpringBootApplication
@Slf4j
public class StartApplicationEx5 {

    public static void main(String[] args) {
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "ex5");
        SpringApplication.run(StartApplicationEx5.class, args);
    }

}
