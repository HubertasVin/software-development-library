package com.hubertasvin.library.service;

import com.hubertasvin.library.domain.Author;
import com.hubertasvin.library.domain.Book;
import com.hubertasvin.library.domain.Publisher;
import com.hubertasvin.library.mapper.BookMapper;
import com.hubertasvin.library.repository.AuthorRepository;
import com.hubertasvin.library.repository.BookRepository;
import com.hubertasvin.library.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private BookRepository bookRepository;      // JPA
    @Autowired
    private AuthorRepository authorRepository;  // JPA
    @Autowired
    private PublisherRepository publisherRepository; // JPA
    @Autowired
    private BookMapper bookMapper; // MyBatis

    @Override
    @Transactional
    public Book createBook(String title, String publisherName) {
        Publisher publisher = new Publisher(publisherName);
        publisher = publisherRepository.save(publisher);

        Book book = new Book(title);
        book.setPublisher(publisher);

        book = bookRepository.save(book);

        return book;
    }

    @Override
    @Transactional
    public Book createBookMyBatis(String title, String publisherName) {
        Publisher publisher = new Publisher(publisherName);
        publisher = publisherRepository.save(publisher);

        Book book = new Book(title);
        book.setPublisher(publisher);

        bookMapper.insert(book);

        return book;
    }

    @Override
    @Transactional
    public void addAuthorToBook(Long bookId, String authorName) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        Author author = new Author(authorName);
        author = authorRepository.save(author);

        book.getAuthors().add(author);
        bookRepository.save(book);
    }

}
