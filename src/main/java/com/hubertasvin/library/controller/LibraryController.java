package com.hubertasvin.library.controller;

import com.hubertasvin.library.domain.Book;
import com.hubertasvin.library.dto.AuthorForm;
import com.hubertasvin.library.dto.BookDto;
import com.hubertasvin.library.repository.AuthorRepository;
import com.hubertasvin.library.repository.BookRepository;
import com.hubertasvin.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@Controller
@RequestScope
public class LibraryController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private AuthorRepository authorRepository;

//    @GetMapping("/books")
//    public String listBooks(Model model) {
//        model.addAttribute("books", bookRepository.findAll());
//        return "books";
//    }
    @GetMapping("/books")
    public String listBooks(Model model) {
        List<Book> books = libraryService.getAllBooksDetails();
        model.addAttribute("books", books);
        return "books";  // renders books.html
    }

    @GetMapping("/authors")
    public String listAuthors(Model model) {
        model.addAttribute("authors", authorRepository.findAll());
        return "authors";
    }

    @GetMapping("/books/new")
    public String showNewBookForm(Model model) {
        model.addAttribute("book", new BookDto());
        return "new-book";
    }

    @PostMapping("/books")
    public String createBook(@ModelAttribute("book") BookDto bookDto) {
        libraryService.createBook(bookDto.getTitle(), bookDto.getPublisherName());
        return "redirect:/books";
    }

    @GetMapping("/books/{id}")
    public String showBookDetails(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id)
                                  .orElseThrow(() -> new RuntimeException(
                                          "Book not found with ID: " + id));
        model.addAttribute("book", book);
        return "book-details";
    }

    @GetMapping("/books/{bookId}/authors/new")
    public String showAddAuthorForm(@PathVariable Long bookId, Model model) {
        model.addAttribute("bookId", bookId);
        model.addAttribute("authorForm", new AuthorForm());
        return "add-author";
    }

    @PostMapping("/books/{bookId}/authors")
    public String addAuthorToBook(
            @PathVariable Long bookId,
            @ModelAttribute("authorForm") AuthorForm authorForm
    ) {
        libraryService.addAuthorToBook(bookId, authorForm.getName());
        return "redirect:/books/" + bookId;
    }
}
