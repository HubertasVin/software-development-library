package com.hubertasvin.library.bean;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class GlobalLibraryStats {

    private long totalBooks = 0;
    private long totalAuthors = 0;

    public synchronized void incrementBooks() {
        totalBooks++;
    }

    public synchronized void incrementAuthors() {
        totalAuthors++;
    }

    public long getTotalBooks() {
        return totalBooks;
    }

    public long getTotalAuthors() {
        return totalAuthors;
    }
}
