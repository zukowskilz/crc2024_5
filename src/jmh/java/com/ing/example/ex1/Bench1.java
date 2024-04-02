package com.ing.example.ex1;

import com.ing.example.ex2.Encryption;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import java.security.KeyPair;

@State(Scope.Benchmark)
public class Bench1 {

    @State(Scope.Benchmark)
    public static class EncryptionState {
        public KeyPair kp = Encryption.generateKeyPair();
        public byte[] messageBt = "Some secure data".getBytes();
        public byte[] syncSalt = "secureKey".getBytes();
    }

    @Benchmark
    public void asyncSign(Blackhole bh, EncryptionState state) {
        bh.consume(Encryption.sign(state.messageBt, state.kp.getPrivate()));
    }

    @Benchmark
    public void syncSign(Blackhole bh, EncryptionState state) {
        bh.consume(Encryption.generateDigest(state.messageBt, state.syncSalt));
    }
}
