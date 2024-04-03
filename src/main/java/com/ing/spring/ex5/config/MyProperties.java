package com.ing.spring.ex5.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "my.config")
@Data
public class MyProperties {
    private String username;
    private String email;
    private int age;
    private String password;
}
