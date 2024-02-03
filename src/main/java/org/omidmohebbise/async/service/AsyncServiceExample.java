package org.omidmohebbise.async.service;


import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncServiceExample {
    @Async
    public CompletableFuture<String> asyncMethod() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                return blockingMethod1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                return blockingMethod2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });


        CompletableFuture<String> resultFuture = CompletableFuture.allOf(future1, future2).thenCompose(
                (result) -> {
                    return future1.thenCombine(future2, (result1, result2) -> {
                        return result1 + result2;
                    });
                }
        );

        return resultFuture;
    }


    private String blockingMethod1() throws InterruptedException {
        Thread.sleep(3000);
        return "Hello  ";
    }

    private String blockingMethod2() throws InterruptedException {
        Thread.sleep(3000);
        return "World  \t ";
    }
}
