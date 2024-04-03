package com.ing.java.ex1;

import lombok.SneakyThrows;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SymmetricSigner {
    private static final String DIGEST_ALGORITHM = "HmacSHA256";

    @SneakyThrows
    public static String generateHash(byte[] data, byte[] salt) {
        SecretKeySpec secretKey = new SecretKeySpec(salt, DIGEST_ALGORITHM);
        Mac mac = Mac.getInstance(DIGEST_ALGORITHM);
        mac.init(secretKey);
        var hmacData = mac.doFinal(data);
        return new String(Base64.getEncoder().encode(hmacData));
    }

    public static String generateSharedSecret(int len) {
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        Random rd = new Random();
        String sharedSecretKey = IntStream.range(0, len)
                .boxed()
                .map(i -> "" + abc.charAt(rd.nextInt(abc.length())))
                .collect(Collectors.joining(""));
        return sharedSecretKey;
    }
}
