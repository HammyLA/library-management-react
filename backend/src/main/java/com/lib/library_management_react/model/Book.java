package com.lib.library_management_react.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Represents a Book entity that maps to the "Book" table in the database.
 */
@Table("Book") // Specifies the table name associated with this entity.
public class Book {

    @Id // Indicates that this field is the primary key for the "Book" table.
    private int bookid; // Unique identifier for the book.
    private String title; // Title of the book.
    private String author; // Author of the book.
    private String genre; // Genre or category of the book.
    private String description; // A brief description or summary of the book.
    private int yearPublished; // Year the book was published.

    /**
     * Constructor to create a Book instance with specified details.
     * 
     * @param title         the title of the book.
     * @param author        the author of the book.
     * @param genre         the genre of the book.
     * @param description   a brief description of the book.
     * @param yearPublished the year the book was published.
     */
    public Book(String title, String author, String genre, String description, int yearPublished) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.description = description;
        this.yearPublished = yearPublished;
    }

    /**
     * Default constructor to create an empty Book instance.
     * Fields are initialized with default values.
     */
    public Book() {
        this.title = null;
        this.author = null;
        this.genre = null;
        this.description = null;
        this.yearPublished = 0;
    }

    // Getter and Setter methods for each field.

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Generates a string representation of the Book object.
     * 
     * @return a string containing the book's details.
     */
    @Override
    public String toString() {
        return "Book [bookid=" + bookid + ", title=" + title + ", author=" + author + ", genre=" + genre
                + ", description=" + description + ", yearPublished=" + yearPublished + "]";
    }
}
