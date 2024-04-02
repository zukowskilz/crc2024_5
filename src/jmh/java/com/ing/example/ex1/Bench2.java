package com.ing.example.ex1;

import com.ing.example.ex2.Encryption;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
public class Bench2 {

        @Benchmark
        public void fibClassic(Blackhole bh) {
            bh.consume("bb");
        }

        @Benchmark
        public void fibTailRec(Blackhole bh) {
            bh.consume("");
        }
}
