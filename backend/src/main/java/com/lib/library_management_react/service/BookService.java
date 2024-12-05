package com.lib.library_management_react.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lib.library_management_react.model.Book;
import com.lib.library_management_react.repository.BookRepository;

import java.util.List;

@Service
public class BookService {
    
    @Autowired
    private BookRepository BookRepository;

    public List<Book> getAllBooks() {
        return BookRepository.findAll();
    }

    public Book getBookById(Integer id) {
        return BookRepository.findById(id);
    }

    public Book createBook(Book Book) {
        BookRepository.save(Book);
        return Book;
    }

    public Book updateBook(Integer id, Book BookDetails) {
        Book Book = BookRepository.findById(id);
        if (Book != null) {
            Book.setTitle(BookDetails.getTitle());
            Book.setDescription(BookDetails.getTitle());
            Book.setYearPublished(BookDetails.getYearPublished());
            BookRepository.update(Book);
        }
        return Book;
    }

    public void deleteBook(Integer id) {
        BookRepository.deleteById(id);
    }
}
