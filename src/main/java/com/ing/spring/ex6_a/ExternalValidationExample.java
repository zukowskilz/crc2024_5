package com.ing.spring.ex6_a;


import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import lombok.Data;

public class ExternalValidationExample {
    @Data
    static class User {
        private final String username;
        @Email
        private final String email;
        @Max(5)
        private final int age;
    }
    public static void main(String[] args) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        var user = new User("name","email",5);

        var violations = validator.validate(user);
        System.out.println(violations);
    }
}
