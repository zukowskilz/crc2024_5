package com.ing.java.ex1;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import java.security.KeyPair;

@State(Scope.Benchmark)
public class SigningBenchmark {

    @State(Scope.Benchmark)
    public static class EncryptionState {
        public KeyPair kp = AsymmetricSigner.generateKeyPair();
        public byte[] messageBt = "Some secure data".getBytes();
        public byte[] syncSalt = SymmetricSigner.generateSharedSecret(256).getBytes();
    }


    @Benchmark
    public void asyncSign(Blackhole bh, EncryptionState state) {
        bh.consume(AsymmetricSigner.sign(state.messageBt, state.kp.getPrivate()));
    }

    @Benchmark
    public void syncSign(Blackhole bh, EncryptionState state) {
        bh.consume(SymmetricSigner.generateHash(state.messageBt, state.syncSalt));
    }
}
