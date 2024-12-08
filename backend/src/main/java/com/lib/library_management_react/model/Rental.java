package com.lib.library_management_react.model;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Represents a Rental entity that maps to the "Rental" table in the database.
 */
@Table("Rental") // Specifies the table name associated with this entity.
public class Rental {

    private static final int WEEKS_DUE = 2; // Default rental period duration in weeks.

    @Id // Indicates that this field is the primary key for the "Rental" table.
    private int rentalid; // Unique identifier for the rental record.
    private Integer member; // ID of the member who rented the book.
    private Integer book; // ID of the book being rented.
    private LocalDateTime dayOfRental; // Date and time the rental started.
    private LocalDateTime dueDate; // Date and time the rental is due.

    /**
     * Constructor to create a Rental instance with specified member and book.
     * The rental date is set to the current time, and the due date is calculated
     * as two weeks from the rental date.
     * 
     * @param member the ID of the member renting the book.
     * @param book   the ID of the book being rented.
     */
    public Rental(Integer member, Integer book) {
        this.member = member;
        this.book = book;
        this.dayOfRental = LocalDateTime.now(); // Set rental start date to current time.
        this.dueDate = LocalDateTime.now().plusWeeks(WEEKS_DUE); // Calculate due date.
    }

    /**
     * Default constructor to create an empty Rental instance.
     * Fields are initialized with default values, and the rental and due dates are set.
     */
    public Rental() {
        this.member = null; // No member assigned initially.
        this.book = null; // No book assigned initially.
        this.dayOfRental = LocalDateTime.now(); // Set rental start date to current time.
        this.dueDate = LocalDateTime.now().plusWeeks(WEEKS_DUE); // Calculate due date.
    }

    // Getter and Setter methods for each field.

    public int getRentalid() {
        return rentalid;
    }

    public void setRentalid(int rentalid) {
        this.rentalid = rentalid;
    }

    public Integer getMember() {
        return member;
    }

    public void setMember(Integer member) {
        this.member = member;
    }

    public Integer getBook() {
        return book;
    }

    public void setBook(Integer book) {
        this.book = book;
    }

    public LocalDateTime getDayOfRental() {
        return dayOfRental;
    }

    public void setDayOfRental(LocalDateTime dayOfRental) {
        this.dayOfRental = dayOfRental;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Generates a string representation of the Rental object.
     * 
     * @return a string containing the rental details.
     */
    @Override
    public String toString() {
        return "Rental [rentalid=" + rentalid + ", member=" + member + ", book=" + book + ", dayOfRental=" + dayOfRental
                + ", dueDate=" + dueDate + "]";
    }
}
