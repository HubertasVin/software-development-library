package com.hubertasvin.library.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncCalculationService {

    @Async
    public void performLongCalculation() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        CompletableFuture.completedFuture("Skaičiavimas baigtas");
    }
}
