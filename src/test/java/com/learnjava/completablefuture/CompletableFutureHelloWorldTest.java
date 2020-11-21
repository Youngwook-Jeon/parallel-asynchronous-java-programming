package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static org.junit.jupiter.api.Assertions.*;

class CompletableFutureHelloWorldTest {

    HelloWorldService helloWorldService = new HelloWorldService();
    CompletableFutureHelloWorld cfhw = new CompletableFutureHelloWorld(helloWorldService);

    @Test
    void helloWorld() {
        CompletableFuture<String> completableFuture= cfhw.helloWorld();

        completableFuture.thenAccept(s -> assertEquals("HELLO WORLD", s))
                .join();
    }

    @Test
    void helloWorld_withSize() {
        CompletableFuture<String> completableFuture= cfhw.helloWorld_withSize();

        completableFuture.thenAccept(s -> assertEquals("11-HELLO WORLD", s))
                .join();
    }

    @Test
    void helloWorld_multiple_async_calls() {
        String helloWorld = cfhw.helloWorld_multiple_async_calls();

        assertEquals("HELLO WORLD!", helloWorld);
    }

    @Test
    void helloWorld_3_async_calls() {
        String helloWorld = cfhw.helloWorld_3_async_calls();

        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", helloWorld);
    }

    @Test
    void helloWorld_thenCompose() {
        // [Test worker] - Total Time Taken : 2052
        startTimer();
        CompletableFuture<String> completableFuture= cfhw.helloWorld_thenCompose();

        completableFuture.thenAccept(s -> assertEquals("HELLO WORLD!", s))
                .join();
        timeTaken();
    }

    @Test
    void helloWorld_4_async_calls() {
        String helloWorld = cfhw.helloWorld_4_async_calls();

        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!  BYE!", helloWorld);
    }
}