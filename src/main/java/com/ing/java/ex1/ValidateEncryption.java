package com.ing.java.ex1;

import java.util.Base64;

public class ValidateEncryption {

    public static void main(String[] args) {
        String message = "Hello, I am a message";
        // Asymmetric style

        // generate asymetric key pair
        var kp = AsymmetricSigner.generateKeyPair();

        System.out.println("Asym PublicKey " + new String(Base64.getEncoder().encode(kp.getPublic().getEncoded())));
        // sign with the private key
        String asymSignature = AsymmetricSigner.sign(message.getBytes(), kp.getPrivate());
        System.out.println("Asym signature " + asymSignature);

        // verify with the public key
        boolean asymmetricOK = AsymmetricSigner.verify(message.getBytes(), asymSignature, kp.getPublic());

        System.out.println("Asym validation " + asymmetricOK);
        System.out.println("-----");

        String sharedSecret = SymmetricSigner.generateSharedSecret(256);
        System.out.println("Sym shared secret: " + sharedSecret);
        String symSignature = SymmetricSigner.generateHash(message.getBytes(), sharedSecret.getBytes());
        System.out.println("Sym signature " + symSignature);
    }

}
