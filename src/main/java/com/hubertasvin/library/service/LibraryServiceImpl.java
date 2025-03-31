package com.hubertasvin.library.service;

import com.hubertasvin.library.domain.Author;
import com.hubertasvin.library.domain.Book;
import com.hubertasvin.library.domain.Publisher;
import com.hubertasvin.library.mapper.AuthorMapper;
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
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private AuthorMapper authorMapper;
    @Autowired
    private BookMapper bookMapper;

    @Override
    @Transactional
    public Book createBook(String title, String publisherName) {
        Publisher publisher = publisherRepository.findByName(publisherName).orElseGet(() -> {
            Publisher newPublisher = new Publisher(publisherName);
            return publisherRepository.save(newPublisher);
        });

        Book book = new Book(title);
        book.setPublisher(publisher);

        return bookRepository.save(book);
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

//    @Override
//    @Transactional
//    public void addAuthorToBook(Long bookId, String authorName) {
//        Book book = bookRepository.findById(bookId)
//                                  .orElseThrow(() -> new RuntimeException(
//                                          "Book not found with ID: " + bookId));
//
//        Author author = authorRepository.findByFullName(authorName).orElseGet(() -> {
//            Author newAuthor = new Author(authorName);
//            return authorRepository.save(newAuthor);
//        });
//
//        if (!book.getAuthors().contains(author)) {
//            book.getAuthors().add(author);
//            bookRepository.save(book);
//        }
//    }

//    @Override
//    @Transactional
//    public void addAuthorToBook(Long bookId, String authorName) {
//        Author author = authorMapper.findByFullName(authorName);
//        if (author == null) {
//            author = new Author(authorName);
//            authorMapper.insert(author);
//        }
//        bookMapper.insertBookAuthor(bookId, author.getId());
//    }

    @Override
    @Transactional
    public void addAuthorToBook(Long bookId, String authorName) {
        Author author = authorMapper.findByFullName(authorName);
        if (author == null) {
            author = new Author(authorName);
            authorMapper.insert(author);
        }
        bookMapper.insertBookAuthor(bookId, author.getId());
    }
}
