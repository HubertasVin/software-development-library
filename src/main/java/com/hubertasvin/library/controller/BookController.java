package com.hubertasvin.library.controller;

import com.hubertasvin.library.domain.Book;
import com.hubertasvin.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    /*curl -X GET http://localhost:8080/book/name/1*/
    @GetMapping("/name/{id}")
    public ResponseEntity<Book> getBookName(@PathVariable Long id) {
        Optional<Book> bookOpt = bookRepository.findById(id);
        return bookOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /*curl -X POST \
  -H "Content-Type: application/json" \
  -d '{"title": "Nauja knyga", "publisher": {"id": 1, "name": "Leidykla"}}' \
  http://localhost:8080/book*/
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book savedBook = bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    /*curl -X PUT \
  -H "Content-Type: application/json" \
  -d '{"title": "Atnaujintas pavadinimas"}' \
  http://localhost:8080/book/1*/
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Optional<Book> existingOpt = bookRepository.findById(id);
        if (existingOpt.isPresent()) {
            Book existing = existingOpt.get();
            existing.setTitle(updatedBook.getTitle());
            Book savedBook = bookRepository.save(existing);
            return ResponseEntity.ok(savedBook);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
