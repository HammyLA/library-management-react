package com.lib.library_management_react.model;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Table;

@Table("Rental")
public class Rental {

    private static final int WEEKS_DUE = 2;

    @Id
    private int rentalid;
    private Integer member;
    private Integer book;
    private LocalDateTime dayOfRental;
    private LocalDateTime dueDate;

    public Rental(Integer member, Integer book) {
        this.member = member;
        this.book = book;
        this.dayOfRental = LocalDateTime.now();
        this.dueDate = LocalDateTime.now().plusWeeks(WEEKS_DUE);
    }

    public Rental() {
        this.member = null;
        this.book = null;
        this.dayOfRental = LocalDateTime.now();
        this.dueDate = LocalDateTime.now().plusWeeks(WEEKS_DUE);
    }

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

    @Override
    public String toString() {
        return "Rental [rentalid=" + rentalid + ", member=" + member + ", book=" + book + ", dayOfRental=" + dayOfRental
                + ", dueDate=" + dueDate + "]";
    }
}