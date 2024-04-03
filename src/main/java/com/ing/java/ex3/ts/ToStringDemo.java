package com.ing.java.ex3.ts;

import lombok.Data;

import java.util.logging.Logger;

public class ToStringDemo {
    @Data
    static class AutorizationData {
        private final String userName;
        private final String password;
    }

    public static void main(String[] args) {
        var logger = Logger.getAnonymousLogger();
        AutorizationData user = new AutorizationData("myUser", "mySecurePassword");
        //some authorization eg. via LDAP
        // and then ...
        logger.info("User has bean logged " + user);
    }
}
