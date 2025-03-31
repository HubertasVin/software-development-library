package com.hubertasvin.library.controller;

import com.hubertasvin.library.bean.GlobalLibraryStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatsController {

    @Autowired
    private GlobalLibraryStats globalLibraryStats;

    @GetMapping("/stats")
    public String showStats() {
        return "Total Books: " + globalLibraryStats.getTotalBooks() +
                ", Total Authors: " + globalLibraryStats.getTotalAuthors();
    }
}
