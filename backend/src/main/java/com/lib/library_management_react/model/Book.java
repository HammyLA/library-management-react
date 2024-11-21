package com.lib.library_management_react.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("Book")
public class Book {

    @Id
    private int bookid;
    private String title;
    private String description;
    private int yearPublished;
    // author
    // comments

    public Book(String title, String description, int yearPublished) {
        this.title = title;
        this.description = description;
        this.yearPublished = yearPublished;
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

    @Override
    public String toString() {
        return "Book [bookid=" + bookid + ", title=" + title + ", description=" + description + ", yearPublished="
                + yearPublished + "]";
    }

    
}
