package com.lib.library_management_react.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.lib.library_management_react.model.Book;
import com.lib.library_management_react.repository.BookRepository;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BookController {

    private final BookRepository books;

    public BookController(BookRepository books) {
        this.books = books;
    }

    @GetMapping
    public Iterable<Book> findall() {
        return books.findAll();
    }

    @GetMapping("/{id}")
    public Book findById(@PathVariable Integer id) {
        return books.findById(id).orElse(null);
    }
}
