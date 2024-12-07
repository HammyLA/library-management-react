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

    public List<Book> getBookByFilter(String title, List<String> genres) {
        return BookRepository.findByFilter(title, genres);
    }

    public Book createBook(Book book) {
        BookRepository.save(book);
        return book;
    }

    public Boolean isRented(Integer bookid) {
        return !BookRepository.isRented(bookid).isEmpty();
    }

    public Book updateBook(Integer id, Book BookDetails) {
        Book book = BookRepository.findById(id);
        if (book != null) {
            book.setTitle(BookDetails.getTitle());
            book.setDescription(BookDetails.getTitle());
            book.setYearPublished(BookDetails.getYearPublished());
            BookRepository.update(book);
        }
        return book;
    }

    public void deleteBook(Integer id) {
        BookRepository.deleteById(id);
    }
}
