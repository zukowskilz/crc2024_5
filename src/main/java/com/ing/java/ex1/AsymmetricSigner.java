package com.ing.java.ex1;

import lombok.SneakyThrows;

import java.security.*;
import java.util.Base64;
import java.util.Objects;

public class AsymmetricSigner {
    private static final String KEY_ALGORITHM = "RSA";
    private static final String SIG_ALGORITHM = "SHA1withRSA";
    private static final Signature sig = signatureInstance(SIG_ALGORITHM);

    private static PrivateKey privateKey = null;

    @SneakyThrows()
    public static KeyPair generateKeyPair() {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        kpg.initialize(1024);
        return kpg.genKeyPair();
    }

    @SneakyThrows
    public static String sign(byte[] messageBytes, PrivateKey pk) {
        if (Objects.isNull(privateKey)) {
            privateKey=pk;
            sig.initSign(privateKey);
        }
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

    @SneakyThrows
    private static Signature signatureInstance(String sigAlgorithm) {
      return Signature.getInstance(sigAlgorithm);
    }
}
