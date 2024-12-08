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

    /**
     * GET /api/books: Retrieve all books.
     * 
     * @return an iterable list of all books in the library.
     */
    @GetMapping
    public Iterable<Book> findall() {
        return books.getAllBooks();
    }

    /**
     * GET /api/books/{id}: Retrieve a book by its ID.
     *
     * @param id the ID of the book to retrieve.
     * @return the book with the specified ID, or null if not found.
     */
    @GetMapping("/{id}")
    public Book findById(@PathVariable Integer id) {
        return books.getBookById(id);
    }

    /**
     * GET /api/books/{id}/status: Check if a book is rented.
     *
     * @param id the ID of the book to check.
     * @return true if the book is rented, false otherwise.
     */
    @GetMapping("/{id}/status")
    public Boolean isRented(@PathVariable Integer id) {
        return books.isRented(id);
    }
    
    /**
     * GET /api/books/{id}/exists: Check if a book exists by its ID.
     *
     * @param id the ID of the book to check.
     * @return true if the book exists, false otherwise.
     */
    @GetMapping("/{id}/exists")
    public Boolean isValidMember(@PathVariable Integer id) {
        return books.getBookById(id) != null;
    }

    /**
     * GET /api/books/search: Search for books by title and genres.
     *
     * @param title the title of the book to search for.
     * @param genres a list of genres to filter the books by.
     * @return an iterable list of books matching the filter criteria.
     */
    @GetMapping("/search")
    public Iterable<Book> findByFilter(@RequestParam String title, @RequestParam List<String> genres) {
        return books.getBookByFilter(title, genres);
    }

    /**
     * POST /api/books: Create a new book in the library.
     *
     * @param book the book object to be created.
     * @return a response entity with the created book and HTTP status 201 (Created).
     */
    @PostMapping
    public ResponseEntity<Book> postBook(@RequestBody Book book) {
        Book savedBook = books.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    /**
     * DELETE /api/books/{id}: Delete a book by its ID.
     *
     * @param id the ID of the book to be deleted.
     * @return a response entity with HTTP status 201 (Created) if successful, or appropriate error if failed.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteMember(@PathVariable Integer id) {
        books.deleteBook(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
