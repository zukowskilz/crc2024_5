package com.ing.java.ex1;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

public class JKSVaultPasswordReader {

    public static final String PASSWORD_TO_VAULT = "ala123";

    //keytool -importpass -alias mypass -keystore myks.jks
    public static void main(String[] args) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableEntryException {
        var ks = KeyStore.getInstance("PKCS12");
        ks.load(new FileInputStream("src/main/resources/secrets/myks.jks"), PASSWORD_TO_VAULT.toCharArray());
        var entry = (KeyStore.SecretKeyEntry) ks.getEntry("mypass", new KeyStore.PasswordProtection(PASSWORD_TO_VAULT.toCharArray()));
        System.out.println(new String(entry.getSecretKey().getEncoded()));
    }
}
