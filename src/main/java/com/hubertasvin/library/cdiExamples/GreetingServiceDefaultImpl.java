package com.hubertasvin.library.cdiExamples;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!alternative")
public class GreetingServiceDefaultImpl implements GreetingService {
    @Override
    public String greet() {
        return "Hello from GreetingServiceDefaultImpl";
    }
}
