package com.lib.library_management_react.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("Book")
public class Book {

    @Id
    private int bookid;
    private String title;
    private String author;
    private String genre;
    private String description;
    private int yearPublished;
    // author
    // comments

    public Book(String title, String author, String genre, String description, int yearPublished) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.description = description;
        this.yearPublished = yearPublished;
    }

    public Book() {
        this.title = null;
        this.author = null;
        this.genre = null;
        this.description = null;
        this.yearPublished = 0;
    }

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

    @Override
    public String toString() {
        return "Book [bookid=" + bookid + ", title=" + title + ", author=" + author + ", genre=" + genre
                + ", description=" + description + ", yearPublished=" + yearPublished + "]";
    }
}