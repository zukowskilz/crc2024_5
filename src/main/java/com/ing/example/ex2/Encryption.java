package com.ing.example.ex2;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

public class Encryption {

    public static void main(String[] args) {
        String message = "Hello, I am a message";

        try {
            // Asymmetric style

            // generate asymetric key pair
            var kp = generateKeyPair();

            // sign with the private key
            String asymSignature = sign(message.getBytes(), kp.getPrivate());

            // verify with the public key
            boolean asymmetricOK = verify(message.getBytes(), asymSignature, kp.getPublic());

            System.out.println("Asym OK? " + asymmetricOK);


            // generate a shared key, actually any string is ok for this
            // but its generating a hard to guess one is probly better
            String sharedSecretKey = "secret";

            // generate a signature
            String symSignature = generateDigest(message.getBytes(), sharedSecretKey.getBytes());

            // verification of a symmetric key signature is just done by
            // recreating the signature and checking they match.
            System.out.println("symSignature? " + symSignature);

        } catch (Exception e) {
            // TODO This is just a test, don't do this
            e.printStackTrace();
        }
    }

    private static final String KEY_ALGORITHM = "RSA";
    private static final String SIG_ALGORITHM = "SHA1withRSA";

    @SneakyThrows()
    public static KeyPair generateKeyPair() {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        kpg.initialize(1024);
        return kpg.genKeyPair();
    }

    @SneakyThrows
    public static String sign(byte[] messageBytes, PrivateKey privateKey) {
        Signature sig = Signature.getInstance(SIG_ALGORITHM);
        sig.initSign(privateKey);
        sig.update(messageBytes);
        byte[] signatureBytes = sig.sign();
        return new String(Base64.getEncoder().encode(signatureBytes));
    }

    @SneakyThrows
    public static boolean verify(byte[] data, String signature, PublicKey publicKey) {
        var signEncoded = Base64.getDecoder().decode(signature);
        Signature sig = Signature.getInstance(SIG_ALGORITHM);
        sig.initVerify(publicKey);
        sig.update(data);
        return sig.verify(signEncoded);
    }

    private static final String DIGEST_ALGORITHM = "HmacSHA256";
    private static final String SYMMETRIC_KEY_ALGORITHM = "HmacSHA256";

    @SneakyThrows
    public static String generateDigest(byte[] data, byte[] salt) {
        SecretKeySpec secretKey = new SecretKeySpec(salt, DIGEST_ALGORITHM);
        Mac mac = Mac.getInstance(DIGEST_ALGORITHM);
        mac.init(secretKey);
        var hmacData = mac.doFinal(data);
        return new String(Base64.getEncoder().encode(hmacData));
    }
}
