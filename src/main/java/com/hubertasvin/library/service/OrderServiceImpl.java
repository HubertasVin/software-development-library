package com.hubertasvin.library.service;

import org.springframework.stereotype.Component;

@Component("orderService")
public class OrderServiceImpl implements OrderService {
    @Override
    public void processOrder() {
        System.out.println("Užsakymas apdorojamas");
    }
}