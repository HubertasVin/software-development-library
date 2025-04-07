package com.hubertasvin.library.cdiExamples;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Profile("alternative")  // @Alternative
public class GreetingServiceAlternativeImpl implements GreetingService {
    @Override
    public String greet() {
        return "Hello from GreetingServiceAlternativeImpl";
    }
}
