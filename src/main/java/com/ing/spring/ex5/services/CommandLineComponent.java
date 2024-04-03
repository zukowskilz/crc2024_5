package com.ing.spring.ex5.services;

import com.ing.spring.ex5.config.MyProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommandLineComponent implements CommandLineRunner {

    private final MyProperties properties;

    public CommandLineComponent(MyProperties properties) {
        this.properties = properties;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Username {}", properties.getUsername());
        log.info("Email {}", properties.getEmail());
        log.info("Age {}", properties.getAge());
        log.info("Password {}", properties.getPassword());
    }
}
