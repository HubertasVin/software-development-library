package com.hubertasvin.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Primary  // dekoratorius tampa numatytuoju bean, jei įjungtas
@Profile("decorator")  // aktyvuoti dekoratorių galima įjungus profilį "decorator"
public class OrderServiceDecorator implements OrderService {

    private final OrderService delegate;

    @Autowired
    public OrderServiceDecorator(@Qualifier("orderService") OrderService delegate) {
        this.delegate = delegate;
    }

    @Override
    public void processOrder() {
        System.out.println("Dekoratoriaus veiksmų pradžia");
        delegate.processOrder();
        System.out.println("Dekoratoriaus veiksmų pabaiga");
    }
}