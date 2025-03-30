package com.hubertasvin.library.controller;

import com.hubertasvin.library.dto.BookDto;
import com.hubertasvin.library.repository.BookRepository;
import com.hubertasvin.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LibraryController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LibraryService libraryService;

    @GetMapping("/books")
    public String listBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "books";
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
}
