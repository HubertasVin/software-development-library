package com.hubertasvin.library.controller;

import com.hubertasvin.library.cdiExamples.GreetingService;
import com.hubertasvin.library.service.AsyncCalculationService;
import com.hubertasvin.library.service.BaseCalculationService;
import com.hubertasvin.library.service.LibraryService;
import com.hubertasvin.library.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimulationController {

    @Autowired
    private LibraryService libraryService;
    @Autowired
    private AsyncCalculationService asyncCalculationService;
    @Autowired
    private GreetingService greetingService;
    @Autowired
    private BaseCalculationService calculationService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/simulateLockException")
    public ResponseEntity<String> simulateLockException(@RequestParam Long bookId,
            @RequestParam String newTitle) {
        libraryService.simulateOptimisticLocking(bookId, newTitle);
        return ResponseEntity.ok("Atnaujinimas įvykdytas (nors turėjo įvykti rollback dėl OptimisticLockException)");
    }

    @GetMapping("/startCalculation")
    public String startCalculation() {
        asyncCalculationService.performLongCalculation();
        return "Ilgas skaičiavimas įgyvendintas asinchroniškai.";
    }



    @GetMapping("/alternative")
    public String example() {
        String greet = greetingService.greet();
        return String.format("Pasisveikinimas: %s", greet);
    }

    @GetMapping("/specialization")
    public String specializationEndpoint() {
        int result = calculationService.calculate(4, 6);
        return "CDI Specialization result: " + result;
    }

    @GetMapping("/interceptor")
    public String interceptorEndpoint() {
        String greet = greetingService.greet();
        return "CDI Interceptor triggered. Greeting: " + greet;
    }

    @GetMapping("/decorator")
    public String decoratorEndpoint() {
        orderService.processOrder();
        return "Patikrinkite konsolę - turėtumėte matyti dekoratoriaus įterptas žinutes.";
    }
}