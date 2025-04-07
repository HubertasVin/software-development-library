package com.hubertasvin.library.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary // @Specializes
public class SpecializedCalculationService extends BaseCalculationService {
    @Override
    public int calculate(int a, int b) {
        return super.calculate(a, b) * 2;
    }
}
