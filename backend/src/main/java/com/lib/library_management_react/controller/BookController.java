package com.lib.library_management_react.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.lib.library_management_react.model.Book;
import com.lib.library_management_react.service.BookService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BookController {

    @Autowired
    private BookService books;

    public BookController(BookService books) {
        this.books = books;
    }

    @GetMapping
    public Iterable<Book> findall() {
        return books.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book findById(@PathVariable Integer id) {
        return books.getBookById(id);
    }

    @GetMapping("/{id}/status")
    public Boolean isRented(@PathVariable Integer id) {
        return books.isRented(id);
    }
    

    @GetMapping("/search")
    public Iterable<Book> findByFilter(@RequestParam String title, @RequestParam List<String> genres) {
        return books.getBookByFilter(title, genres);
    }

    @PostMapping
    public ResponseEntity<Book> postBook(@RequestBody Book book) {
        Book savedBook = books.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }
    
}