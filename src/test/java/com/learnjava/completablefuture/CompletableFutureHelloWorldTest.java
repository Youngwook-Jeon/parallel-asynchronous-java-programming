package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

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
}