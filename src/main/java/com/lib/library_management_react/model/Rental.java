package com.lib.library_management_react.model;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Table;

@Table("Rental")
public class Rental {
    
    @Id
    private int rentalid;
    private AggregateReference<Member, Integer> member;
    private AggregateReference<Book, Integer> book;
    private LocalDateTime dayOfRental;
    private LocalDateTime dueDate;

    public Rental(AggregateReference<Member, Integer> member, AggregateReference<Book, Integer> book, int weeksTilDue) {
        this.member = member;
        this.book = book;
        this.dayOfRental = LocalDateTime.now();
        this.dueDate = LocalDateTime.now().plusWeeks(weeksTilDue);
    }

    public int getRentalid() {
        return rentalid;
    }

    public void setRentalid(int rentalid) {
        this.rentalid = rentalid;
    }

    public AggregateReference<Member, Integer> getMember() {
        return member;
    }

    public void setMember(AggregateReference<Member, Integer> member) {
        this.member = member;
    }

    public AggregateReference<Book, Integer> getBook() {
        return book;
    }

    public void setBook(AggregateReference<Book, Integer> book) {
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
