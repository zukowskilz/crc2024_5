package com.ing.java.ex3.th;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ThreadStorm {
    public static void main(String[] args) throws InterruptedException {
        Instant start = Instant.now();
        systemThreads(1000);
//        virtualThreads(1000);
        Instant finish = Instant.now();
        System.out.println("Elapsed " + Duration.between(start, finish));
    }

    static void systemThreads(int numTasks) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < numTasks; i++) {
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(Duration.ofSeconds(10));
                } catch (InterruptedException e) {
                }
            });
            thread.start();
            threads.add(thread);
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }

    static void virtualThreads(int numTasks) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < numTasks; i++) {
            Thread thread = Thread.startVirtualThread(() -> {
                try {
                    Thread.sleep(Duration.ofSeconds(10));
                } catch (InterruptedException e) {
                }
            });
            threads.add(thread);
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }
}
