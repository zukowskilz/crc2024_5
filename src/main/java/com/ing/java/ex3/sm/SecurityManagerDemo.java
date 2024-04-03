package com.ing.java.ex3.sm;

import java.io.IOException;
import java.net.URL;

// -Djava.security.manager=allow -Djava.security.policy=src/main/java/com/ing/java/ex3/sm/demo-security.policy
public class SecurityManagerDemo {
    public static void main(String[] args) throws IOException {
//        activateSecurityManager();
        new URL("http://www.google.com").openConnection().connect();
    }

    private static void activateSecurityManager() {
        SecurityManager sm = new SecurityManager();
        System.setSecurityManager(sm);
    }
}
