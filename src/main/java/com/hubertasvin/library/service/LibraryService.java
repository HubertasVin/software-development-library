package com.hubertasvin.library.service;

import com.hubertasvin.library.domain.Book;

public interface LibraryService {

    /**
     * Creates a new Book entity (and Publisher if needed) using JPA.
     */
    Book createBook(String title, String publisherName);

    /**
     * Creates a new Book entity (and Publisher if needed) using MyBatis.
     */
    Book createBookMyBatis(String title, String publisherName);

    /**
     * Adds a new Author (by authorName) to an existing Book (by bookId) using JPA.
     */
    void addAuthorToBook(Long bookId, String authorName);
}
