package com.hubertasvin.library.service;

import org.springframework.stereotype.Component;

@Component
public class BaseCalculationService {
    public int calculate(int a, int b) {
        return a + b;
    }
}
