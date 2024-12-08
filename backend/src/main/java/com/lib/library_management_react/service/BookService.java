package com.lib.library_management_react.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lib.library_management_react.model.Book;
import com.lib.library_management_react.repository.BookRepository;

import java.util.List;

/**
 * Service layer for managing Book-related operations.
 * This class acts as a bridge between the controller and repository layers.
 */
@Service
public class BookService {

    @Autowired // Automatically injects the BookRepository bean.
    private BookRepository BookRepository;

    /**
     * Retrieves all books from the database.
     * 
     * @return a list of all books.
     */
    public List<Book> getAllBooks() {
        return BookRepository.findAll();
    }

    /**
     * Retrieves a specific book by its ID.
     * 
     * @param id the ID of the book to retrieve.
     * @return the Book object, or null if not found.
     */
    public Book getBookById(Integer id) {
        return BookRepository.findById(id);
    }

    /**
     * Retrieves books based on a title and a list of genres.
     * If no filters are provided, all books may be returned (depending on repository logic).
     * 
     * @param title  the title filter for the book search.
     * @param genres a list of genres to filter the books.
     * @return a list of books matching the filters.
     */
    public List<Book> getBookByFilter(String title, List<String> genres) {
        return BookRepository.findByFilter(title, genres);
    }

    /**
     * Creates a new book in the database.
     * 
     * @param book the Book object containing the details of the new book.
     * @return the created Book object.
     */
    public Book createBook(Book book) {
        BookRepository.save(book);
        return book;
    }

    /**
     * Checks if a book is currently rented by any member.
     * 
     * @param bookid the ID of the book to check.
     * @return true if the book is rented, false otherwise.
     */
    public Boolean isRented(Integer bookid) {
        return !BookRepository.isRented(bookid).isEmpty(); // Returns true if the book is in the rental list.
    }

    /**
     * Updates the details of an existing book.
     * If the book is not found, it returns null.
     * 
     * @param id           the ID of the book to update.
     * @param BookDetails  the updated details of the book.
     * @return the updated Book object, or null if not found.
     */
    public Book updateBook(Integer id, Book BookDetails) {
        Book book = BookRepository.findById(id); // Fetch the existing book.
        if (book != null) { // Update only if the book exists.
            book.setTitle(BookDetails.getTitle());
            book.setDescription(BookDetails.getDescription()); // Fixed to update the description correctly.
            book.setYearPublished(BookDetails.getYearPublished());
            BookRepository.update(book); // Save the updated book to the database.
        }
        return book;
    }

    /**
     * Deletes a book by its ID.
     * 
     * @param id the ID of the book to delete.
     */
    public void deleteBook(Integer id) {
        BookRepository.deleteById(id);
    }
}
