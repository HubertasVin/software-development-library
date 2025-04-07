package com.hubertasvin.library.service;

import com.hubertasvin.library.bean.GlobalLibraryStats;
import com.hubertasvin.library.domain.Author;
import com.hubertasvin.library.domain.Book;
import com.hubertasvin.library.domain.Publisher;
import com.hubertasvin.library.mapper.AuthorMapper;
import com.hubertasvin.library.mapper.BookMapper;
import com.hubertasvin.library.repository.AuthorRepository;
import com.hubertasvin.library.repository.BookRepository;
import com.hubertasvin.library.repository.PublisherRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private GlobalLibraryStats globalLibraryStats;

    @Override
    @Transactional
    public Book createBook(String title, String publisherName) {
        Publisher publisher = publisherRepository.findByName(publisherName).orElseGet(() -> {
            Publisher newPublisher = new Publisher(publisherName);
            return publisherRepository.save(newPublisher);
        });

        Book book = new Book(title);
        book.setPublisher(publisher);

        globalLibraryStats.incrementBooks();
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

    @Override
    @Transactional
    public void addAuthorToBook(Long bookId, String authorName) {
        Author author = authorMapper.findByFullName(authorName);
        if (author == null) {
            author = new Author(authorName);
            authorMapper.insert(author);
            globalLibraryStats.incrementAuthors();
        }
        bookMapper.insertBookAuthor(bookId, author.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooksDetails() {
        return bookMapper.selectAllBooksWithDetails();
    }

    @Transactional
    public void simulateOptimisticLocking(Long bookId, String newTitle) {
        Book book = em.find(Book.class, bookId);

        em.detach(book);

        em.createNativeQuery("UPDATE books SET version = version + 1 WHERE id = ?")
          .setParameter(1, bookId)
          .executeUpdate();

        book.setTitle(newTitle);

        em.merge(book);

        try {
            em.flush();
        } catch (OptimisticLockException e) {
            System.out.println("OptimisticLockException pagautas.");
            em.clear();
//            throw e;
        }
    }
}
