package com.lib.library_management_react.controller;

import com.lib.library_management_react.model.Book;
import com.lib.library_management_react.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@SpringBootTest
class BookControllerTest {

    @Autowired
    private BookController bookController;

    @MockBean
    private BookService bookService;

    private Book sampleBook;

    @BeforeEach
    void setUp() {
        sampleBook = new Book("Sample Title", "Sample Author", "Fiction", "A great book", 2023);
        sampleBook.setBookid(1);
    }

    @Test
    void testFindAllBooks() {
        List<Book> books = Arrays.asList(sampleBook);
        Mockito.when(bookService.getAllBooks()).thenReturn(books);

        Iterable<Book> result = bookController.findall();

        assertEquals(books, result);
        Mockito.verify(bookService).getAllBooks();
    }

    @Test
    void testFindBookById() {
        Mockito.when(bookService.getBookById(eq(1))).thenReturn(sampleBook);

        Book result = bookController.findById(1);

        assertEquals(sampleBook, result);
        Mockito.verify(bookService).getBookById(1);
    }

    @Test
    void testIsBookRented() {
        Mockito.when(bookService.isRented(eq(1))).thenReturn(true);

        Boolean result = bookController.isRented(1);

        assertEquals(true, result);
        Mockito.verify(bookService).isRented(1);
    }

    @Test
    void testIsValidMember() {
        Mockito.when(bookService.getBookById(eq(1))).thenReturn(sampleBook);

        Boolean result = bookController.isValidMember(1);

        assertEquals(true, result);
        Mockito.verify(bookService).getBookById(1);
    }

    @Test
    void testFindBooksByFilter() {
        List<Book> books = Arrays.asList(sampleBook);
        Mockito.when(bookService.getBookByFilter(eq("Sample"), any())).thenReturn(books);

        Iterable<Book> result = bookController.findByFilter("Sample", Arrays.asList("Fiction"));

        assertEquals(books, result);
        Mockito.verify(bookService).getBookByFilter("Sample", Arrays.asList("Fiction"));
    }

    @Test
    void testPostBook() {
        Mockito.when(bookService.createBook(any())).thenReturn(sampleBook);

        ResponseEntity<Book> response = bookController.postBook(sampleBook);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(sampleBook, response.getBody());
        Mockito.verify(bookService).createBook(sampleBook);
    }

    @Test
    void testEditBook() {
        Mockito.when(bookService.updateBook(eq(1), any())).thenReturn(sampleBook);

        Book result = bookController.editBook(1, sampleBook);

        assertEquals(sampleBook, result);
        Mockito.verify(bookService).updateBook(1, sampleBook);
    }

    @Test
    void testDeleteBook() {
        ResponseEntity<Book> response = bookController.deleteMember(1);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Mockito.verify(bookService).deleteBook(1);
    }
}
