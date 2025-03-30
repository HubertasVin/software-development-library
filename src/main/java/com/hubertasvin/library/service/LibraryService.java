package com.hubertasvin.library.service;

import com.hubertasvin.library.domain.Book;

public interface LibraryService {

    Book createBook(String title, String publisherName);

    Book createBookMyBatis(String title, String publisherName);

    void addAuthorToBook(Long bookId, String authorName);
}
